# 가구 브랜드 온라인 스토어
> 그린컴퓨터 아카데미 자율 팀 프로젝트

팀장 - 송한올<br>
팀원 - 이아현, 배준혁, 홍주형

### ERD
![Pasted image 20231214101018](attachments/Pasted%20image%2020231214101018.png)

## ⚙️ 개발환경

**IDE : IntelliJ IDEA Community** : v2023.2  
**Java Development Kit** : v11  
**JAVA** : v17  
**Spring Boot** : v2.7.6  
**MySQL** : v8.0.35  
**Gradle** : Groovy  
**Lombok** : v1.18.24  
**Thymeleaf** : v3.0.15  

## 🗃️ 기술 스택
  
**프론트엔드**
- HTML 5
- CSS
- jQuery
- JavaScript
  
**백엔드**
- JAVA
- MySQL

**기타 도구 및 라이브러리**
- Spring Security
- Spring Data JPA, Spring Web, Spring AOP, Spring Validation
- Gradle
- Notion
- Thymeleaf
- Lombok
- Apache Commons Lang
- Auth0, Java JWT

## 📝 구현 과정

1. **개발 환경 설정**: 사용할 개발 도구와 라이브러리, 프레임워크를 설정하고 데이터베이스와의 연결을 구성합니다.
   
2. **데이터 모델링**: Entity 클래스를 작성하고 관계 매핑을 진행합니다.
   
3. **저장소(Repository) 작성**: 각 Entity에 대한 CRUD 연산을 수행할 수 있는 Repository를 구현합니다.
   
4. **서비스 로직 구현**: 비즈니스 로직을 수행하는 Service 클래스를 구현하고, 필요한 경우 트랜잭션 처리를 합니다.
   
5. **API(Controller) 작성**: 클라이언트의 요청을 처리할 Controller를 작성하고 요청/응답 데이터를 위한 DTO, Response 클래스를 작성합니다.
   
6. **에러 처리**: 예외 처리를 구현하고 사용자 친화적인 에러 메시지를 반환합니다.

## 🔒 적용된 보안 조치들
  
- **Spring Security**
- **Auth0, Java JWT**

---

## 📃 업데이트 프리뷰

_**해당 내용은 예정이며 순서 상관이 없으며 예고없이 추가, 수정, 삭제될 수 있습니다.**_

### 상품 수정시 옵션 생성 기능 추가
> 현재 옵션의 추가 기능은 상품 생성을 통해서 한번에 옵션까지 설정해야 합니다.
> 
> 해당 상품의 옵션이 새로 추가될시 현재 상품 수정에서 옵션의 추가가 불가함으로 불편함을 해소하기 위해 기능을 제공하고자 합니다.

- 해당 상품의 옵션만 따로 추가하는 기능 추가

### 쿠폰 및 할인율 기능 추가 개선
> 쇼핑몰에서 발급하는 쿠폰 혹은 각 상품의 할인 기능이 적용되어 있지 않습니다.
> 
> 사용자의 사정으로 인해 할인이 필요하거나 신규고객 유입 혹은 기존 고객 유지 등
> 여러 용도로 사용하기 위해 해당 기능들을 추가하고자 합니다.

- 회원인 고객님들의 모두 혹은 선택적으로 쿠폰 배포 기능
- 이벤트 페이지를 통하여 클릭시 쿠폰 발급 기능
- 해당 상품의 할인율 구현

### 오프라인 매장 찾기
> 브랜딩된 가구사의 오프라인 매장이 있을 경우를 생각하여 지도 API를 활용해 
> 
> 해당 가구 브랜드의 오프라인 매장의 위치를 사용자가 찾기 편하게끔 구현할
> 예정입니다.

- 카카오 혹은 네이버 지도를 통하여 해당 가구점의 위치 파악
- 리스트 형식의 오프라인 가구점을 나열하여 클릭시 해당 위치로 지도 API가 이동하여 사용자가 단번에 파악할 수 있게끔 기능 구현

## 📚 버전 히스토리

v1.0 ( 23/12/26 )
- 가구 브랜딩 온라인 스토어 신규 개설

---
<br>
<br>
### [테스트 영상](https://github.com/forktheonlyone/Funiture-OnlineShop/tree/main/Java-Funiture-OnlineShop)
