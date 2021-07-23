# Instagram Clone 📷   
인스타그램을 클론코딩하였습니다.    
http://hyoni.shop/

<br>

## 1. 제작 기간 & 팀원 소개
- 2021년 7월 9일 ~ 7월 15일
- 4인 1조 팀프로젝트
	+ 백엔드 (Spring) : 최민서, 최왕규
	+ 프론트엔드 (React) : 정효윤, 박민주

<br>

## 2. 사용 기술
`Back-end`
- Java 8
- SpringBoot 2.5.2
- Spring Security
- Gradle 7.0.2
- JPA
- MySQL 8.0

`Front-end`
- React ([React Repository이동](https://github.com/imstagram23/imstagram23Front))

`deploy`
- AWS EC2 (Ubuntu 18.04 LTS)
- AWS RDS (MySQL 8.0)
- AWS S3

<br>

## 3. 실행화면

<img src="https://user-images.githubusercontent.com/70243735/126725198-a99916e6-f043-45ea-9cd8-595bfd7c6a1c.gif">

자세한 영상 : https://youtu.be/xx5b8UGtNPg

<br>

## 4. ERD 설계

<img src="https://user-images.githubusercontent.com/70243735/126725255-6dfed414-8afe-4d08-a9ab-18e07e7cc47d.png" width="600px">

<br>

## 5. API 설계

<details>

<summary> 로그인 & 회원 가입 API 설계 </summary>
  <img src="https://user-images.githubusercontent.com/70243735/126725936-8b231acb-a017-4c25-94ad-14652a90f27f.png">

</details>


<details>

<summary> 게시글과 좋아요 API 설계 </summary>
  <img src="https://user-images.githubusercontent.com/70243735/126726194-c806d04d-cfb0-412e-8ba5-6dd35f920374.png" width = "800px">

</details>


<details>

<summary> 댓글 API 설계 </summary>
  <img src="https://user-images.githubusercontent.com/70243735/126726592-3edd9e70-8e87-4e83-827b-f0f8b6facfe4.png"  width = "800px">

</details>


<details>

<summary> 메인 페이지와 유저 페이지 API 설계 </summary>
  <img src="https://user-images.githubusercontent.com/70243735/126726595-6f1d7b84-353a-4906-953a-0799b596e757.png"  width = "800px">

</details>


<br>

## 5. 주요 기능
- 로그인, 회원가입
	+ **JWT**의 단점을 극복하려 RefreshToken 도입 ([코드 보러가기](https://github.com/imstagram23/imstagram23Back/tree/master/src/main/java/com/example/imstagram23back/security))
- 게시글 CRUD
	+ **무한스크롤**를 위한 페이징 처리 ([코드 보러가기](https://github.com/imstagram23/imstagram23Back/blob/master/src/main/java/com/example/imstagram23back/service/PostService.java))
	+ AWS **S3에 이미지 업로드** ([코드 보러가기](https://github.com/imstagram23/imstagram23Back/blob/master/src/main/java/com/example/imstagram23back/util/S3Uploader.java)) - [관련 이슈 보러가기](https://github.com/imstagram23/imstagram23Back/issues?q=is%3Aissue+is%3Aclosed)
	+ 자신이 작성했던 게시글만 수정, 삭제를 할 수 있습니다.
- 댓글 CRUD
	+ **댓글과 게시글**의 연관관계 : 단방향 ManyToOne
	+ **댓글과 사용자**의 연관관계 : 단방향 ManyToOne
	+ 자신이 작성했던 댓글만 수정, 삭제를 할 수 있습니다.
- 게시글 좋아요
	+ **좋아요와 게시글**의 연관관계 : 단방향 ManyToOne
	+ **좋아요와 사용자**의 연관관계 : 단방향 ManyToOne
	+ 사용자는 하나의 게시글에 한 번의 좋아요만 할 수 있습니다.
