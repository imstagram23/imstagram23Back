package com.example.imstagram23back.service;

import com.example.imstagram23back.domain.dto.*;
import com.example.imstagram23back.domain.model.Member;
import com.example.imstagram23back.domain.model.Post;
import com.example.imstagram23back.exception.ApiRequestException;
import com.example.imstagram23back.repository.CommentRepository;
import com.example.imstagram23back.repository.HeartLikeRepository;
import com.example.imstagram23back.repository.MemberRepository;
import com.example.imstagram23back.repository.PostRepository;
import com.example.imstagram23back.util.S3Uploader;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;



@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;
    private final S3Uploader s3Uploader;
    private final MemberRepository memberRepository;
    private final HeartLikeRepository heartLikeRepository;
    private final CommentRepository commentRepository;

    // Post 목록 최신순 조회
    @Transactional
    public List<PostResponseDto> getPostList(){

        return postRepository.findAllByOrderByCreatedAtDesc().stream()
                .map(post -> new PostResponseDto(post))
                .collect(Collectors.toList());
    }

    // 최왕규
    // 총 좋아요 개수도 반환해줄가
    @Transactional
    public List<PostResponseDto2> getPostList2(String memberEmail){
        Member member = memberRepository.findByEmail(memberEmail).orElseThrow(
                () -> new ApiRequestException("좋아요에서 글 불러올때 정확하지않은 이메일")
        );

        List<Post> postList = postRepository.findAllByOrderByCreatedAtDesc();
        List<PostResponseDto2> result = new ArrayList<>();

        for(int i = 0 ; i< postList.size() ; i++){
            Post post = postList.get(i);

            Long hearLikeTotal = heartLikeRepository.countByPost(post);
            Long totalComment = commentRepository.countByPost(post);

            result.add(new PostResponseDto2(post,  hearLikeTotal, totalComment,
                    isAlreadLikeCheck(member, post), checkCreateMember(member.getEmail(), post)));
        }
        return result;
    }

    // 페이지로 조회
    @Transactional
    public PageResponseDto getPage(String memberEmail, int page ){
        Member member = memberRepository.findByEmail(memberEmail).orElseThrow(
                () -> new ApiRequestException("좋아요에서 글 불러올때 정확하지않은 이메일")
        );

        Sort.Direction direction = Sort.Direction.DESC;
        Sort sort = Sort.by(direction,"createdAt");
        page = page - 1;
        Pageable pageable = PageRequest.of(page, 5, sort );
        Page pagelist = postRepository.findAll(pageable);

        List<Post> postList = pagelist.getContent();
        List<PostResponseDto2> result = postList.stream()
                .map(post -> new PostResponseDto2(post, heartLikeRepository.countByPost(post),
                commentRepository.countByPost(post),
                isAlreadLikeCheck(member, post), checkCreateMember(member.getEmail(), post)))
                .collect(Collectors.toList());

        // getOffset()= 이전에 요소몇개, isFirst= 몇페이지인지, isLast = 마지막페이지인지
        long offset = pagelist.getPageable().getOffset();
        boolean fisrtCheck =  pagelist.isFirst();
        boolean lastCheck = pagelist.isLast();
//        pagelist.isEmpty(); 만약필요하다면 보내주는데 거기가 길이가 0 이면 처리할수있잖아...

        PageResponseDto asd = new PageResponseDto(result, offset, fisrtCheck, lastCheck);
        return asd;
    }

    // 계속 조회하는거라 비효율적이라 생각함 다른방법을 생각해보자.
    private boolean isAlreadLikeCheck(Member member, Post post ){
        return heartLikeRepository.findByMemberAndPost(member, post).isPresent();
    }

    // 글자기가 쓴건지 확인하는거
    private boolean checkCreateMember(String memberEmail, Post post){
        return memberEmail.equals(post.getMember().getEmail());
    }




    // Post 작성
    @Transactional
    public PostResponseDto save(MultipartFile image, String content, String userEmail){
        isNullCheck(image, content);

        // 유저 확인
        Member member = memberRepository.findByEmail(userEmail).orElseThrow(
                () -> new ApiRequestException("유저가 아닙니다.")
        );

        // 이미지 업로드
        String imageUrl = s3Uploader.upload(image);

        // post 저장
        Post post = new Post(content, imageUrl, member);
        return new PostResponseDto(postRepository.save(post));
    }

    // 게시글 내용과 이미지가 있는지 확인
    public void isNullCheck(MultipartFile image, String content){
        // 게시글 내용과 이미지는 필수

        if(image == null){ //.isEmpty()도 되는지 확인해보기
            throw new ApiRequestException("이미지는 반드시 있어야합니다.");
        }
        if(content.isEmpty()){
            throw new ApiRequestException("내용은 반드시 있어야합니다.");
        }
    }

    // Post 삭제
    @Transactional
    public void delete(Long id, String userEmail){
        // 게시글 조회
        Post post = postRepository.findById(id).orElseThrow(
                ()-> new ApiRequestException("해당 게시물이 없습니다. id = "+id));

        // 유저 조회
        Member member = memberRepository.findByEmail(userEmail).orElseThrow(
                () -> new ApiRequestException("유저가 아닙니다.")
        );

        // 유저가 쓴 게시글이 맞는지 확인
        if (!post.getMember().equals(member)) {
            throw new ApiRequestException("자신이 쓴 게시글만 수정할 수 있습니다.");
        }

        // 게시글과 연관된 댓글 먼저 삭제
        commentRepository.deleteAllByPost(post);

        // 좋아요 한그것도 삭제를 시켜줘야지 - 순서가중요
        heartLikeRepository.deleteByPost(post);

        // 게시글 삭제
        postRepository.deleteById(id);

        // S3에 업로드했던 이미지도 삭제
        s3Uploader.delete(post.getImageUrl());
    }

    // Post 수정
    @Transactional
    public PostResponseDto update(Long id, PostRequestDto requestDto, String userEmail){
        // 게시글 조회
        Post post = postRepository.findById(id).orElseThrow(
                ()-> new ApiRequestException("해당 게시물이 없습니다. id = "+id));

        // 유저 조회
        Member member = memberRepository.findByEmail(userEmail).orElseThrow(
                () -> new ApiRequestException("유저가 아닙니다.")
        );

        // 유저가 쓴 게시글이 맞는지 확인
        if (!post.getMember().equals(member)) {
            throw new ApiRequestException("자신이 쓴 게시글만 수정할 수 있습니다.");
        }
        post.update(requestDto);
        // 게시글 수정
        return new PostResponseDto(post);
    }

    // Post 하나 조회
    @Transactional
    public PostResponseDto getPost(Long id){
        Post post = postRepository.findById(id).orElseThrow(
                ()-> new ApiRequestException("해당 게시물이 없습니다. id = "+id));
        return new PostResponseDto(post);
    }


    // 유저페이지 조회
    @Transactional
    public MemberpageResponseDto getMemberPage(String nickname){
        // 유저 조회
        Member member = memberRepository.findByNickname(nickname).orElseThrow(
                () -> new ApiRequestException("유저페이지 조회 중 해당 유저는 찾을 수 없습니다.")
        );

        // 유저의 게시글 조회
        List<Post> postList = postRepository.findAllByMember(member);
        List<String> imageList = postList.stream().map(post -> post.getImageUrl()).collect(Collectors.toList());
        return new MemberpageResponseDto(member.getNickname(), imageList);
    }

    // 마이페이지 조회
    @Transactional
    public MemberpageResponseDto getMyPage(String userEmail){
        // 유저 조회
        Member member = memberRepository.findByEmail(userEmail).orElseThrow(
                () -> new ApiRequestException("마이페이지 조회 중 해당 유저는 찾을 수 없습니다.")
        );

        // 유저의 게시글 조회
        List<Post> postList = postRepository.findAllByMember(member);
        List<String> imageList = postList.stream().map(post -> post.getImageUrl()).collect(Collectors.toList());
        return new MemberpageResponseDto(member.getNickname(), imageList);
    }

}
