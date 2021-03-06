# Instagram Clone ๐ท   
์ธ์คํ๊ทธ๋จ์ ํด๋ก ์ฝ๋ฉํ์์ต๋๋ค.    
http://hyoni.shop/

<br>

## 1. ์ ์ ๊ธฐ๊ฐ & ํ์ ์๊ฐ
- 2021๋ 7์ 9์ผ ~ 7์ 15์ผ
- 4์ธ 1์กฐ ํํ๋ก์ ํธ
	+ ๋ฐฑ์๋ (Spring) : ์ต๋ฏผ์, ์ต์๊ท
	+ ํ๋ก ํธ์๋ (React) : ์ ํจ์ค, ๋ฐ๋ฏผ์ฃผ

<br>

## 2. ์ฌ์ฉ ๊ธฐ์ 
`Back-end`
- Java 8
- SpringBoot 2.5.2
- Spring Security
- Gradle 7.0.2
- JPA
- MySQL 8.0

`Front-end`
- React ([React Repository์ด๋](https://github.com/imstagram23/imstagram23Front))

`deploy`
- AWS EC2 (Ubuntu 18.04 LTS)
- AWS RDS (MySQL 8.0)
- AWS S3

<br>

## 3. ์คํํ๋ฉด

<img src="https://user-images.githubusercontent.com/70243735/126725198-a99916e6-f043-45ea-9cd8-595bfd7c6a1c.gif">

์์ธํ ์์ : https://youtu.be/xx5b8UGtNPg

<br>

## 4. ERD ์ค๊ณ

<img src="https://user-images.githubusercontent.com/70243735/126725255-6dfed414-8afe-4d08-a9ab-18e07e7cc47d.png" width="600px">

<br>

## 5. API ์ค๊ณ

<details>

<summary> ๋ก๊ทธ์ธ & ํ์ ๊ฐ์ API ์ค๊ณ </summary>
  <img src="https://user-images.githubusercontent.com/70243735/126725936-8b231acb-a017-4c25-94ad-14652a90f27f.png">

</details>


<details>

<summary> ๊ฒ์๊ธ๊ณผ ์ข์์ API ์ค๊ณ </summary>
  <img src="https://user-images.githubusercontent.com/70243735/126726194-c806d04d-cfb0-412e-8ba5-6dd35f920374.png" width = "800px">

</details>


<details>

<summary> ๋๊ธ API ์ค๊ณ </summary>
  <img src="https://user-images.githubusercontent.com/70243735/126726592-3edd9e70-8e87-4e83-827b-f0f8b6facfe4.png"  width = "800px">

</details>


<details>

<summary> ๋ฉ์ธ ํ์ด์ง์ ์ ์  ํ์ด์ง API ์ค๊ณ </summary>
  <img src="https://user-images.githubusercontent.com/70243735/126726595-6f1d7b84-353a-4906-953a-0799b596e757.png"  width = "800px">

</details>


<br>

## 5. ์ฃผ์ ๊ธฐ๋ฅ
- ๋ก๊ทธ์ธ, ํ์๊ฐ์
	+ **JWT**์ ๋จ์ ์ ๊ทน๋ณตํ๋ ค RefreshToken ๋์ ([์ฝ๋ ๋ณด๋ฌ๊ฐ๊ธฐ](https://github.com/imstagram23/imstagram23Back/tree/master/src/main/java/com/example/imstagram23back/security))
- ๊ฒ์๊ธ CRUD
	+ **๋ฌดํ์คํฌ๋กค**๋ฅผ ์ํ ํ์ด์ง ์ฒ๋ฆฌ ([์ฝ๋ ๋ณด๋ฌ๊ฐ๊ธฐ](https://github.com/imstagram23/imstagram23Back/blob/master/src/main/java/com/example/imstagram23back/service/PostService.java))
	+ AWS **S3์ ์ด๋ฏธ์ง ์๋ก๋** ([์ฝ๋ ๋ณด๋ฌ๊ฐ๊ธฐ](https://github.com/imstagram23/imstagram23Back/blob/master/src/main/java/com/example/imstagram23back/util/S3Uploader.java)) - [๊ด๋ จ ์ด์ ๋ณด๋ฌ๊ฐ๊ธฐ](https://github.com/imstagram23/imstagram23Back/issues?q=is%3Aissue+is%3Aclosed)
	+ ์์ ์ด ์์ฑํ๋ ๊ฒ์๊ธ๋ง ์์ , ์ญ์ ๋ฅผ ํ  ์ ์์ต๋๋ค.
- ๋๊ธ CRUD
	+ **๋๊ธ๊ณผ ๊ฒ์๊ธ**์ ์ฐ๊ด๊ด๊ณ : ๋จ๋ฐฉํฅ ManyToOne
	+ **๋๊ธ๊ณผ ์ฌ์ฉ์**์ ์ฐ๊ด๊ด๊ณ : ๋จ๋ฐฉํฅ ManyToOne
	+ ์์ ์ด ์์ฑํ๋ ๋๊ธ๋ง ์์ , ์ญ์ ๋ฅผ ํ  ์ ์์ต๋๋ค.
- ๊ฒ์๊ธ ์ข์์
	+ **์ข์์์ ๊ฒ์๊ธ**์ ์ฐ๊ด๊ด๊ณ : ๋จ๋ฐฉํฅ ManyToOne
	+ **์ข์์์ ์ฌ์ฉ์**์ ์ฐ๊ด๊ด๊ณ : ๋จ๋ฐฉํฅ ManyToOne
	+ ์ฌ์ฉ์๋ ํ๋์ ๊ฒ์๊ธ์ ํ ๋ฒ์ ์ข์์๋ง ํ  ์ ์์ต๋๋ค.
