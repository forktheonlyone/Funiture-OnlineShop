# (13) 12.28 스크럼

## 남은 작업

### 송한올

> 카테고리별 상품 리스트 페이지(상품 이미지 불러오기), 상품 상세페이지(댓글식 상품 리뷰), 상품 수정 뷰



### 홍주형

> 장바구니 페이지(결제하기 버튼), 주문확인(`order`) 뷰

  

### 배준혁

> 장바구니 페이지(결제하기 버튼) 지원, *상품 리뷰 지원

  

### 이아현

> 병가

  



## **회고**

### **이슈**

> 오늘자 발생한 이슈 정리

### 송한올

#### 상품 상세페이지 `reviewList` 부재 오류

> 상품 상세페이지 리뷰 추가함으로써 발생된 복합적 오류

##### 솔루션

- 리뷰까지 받도록 DTO 수정

  

#### 상품 수정 페이지 등록된 상품 정보 받기

> 해당하는 카테고리의 상품 정보를 받아 텍스트박스에 자동 입력 기능 구현 방법 이슈

  

---

### 배준혁

#### 메뉴바에 로그인, 로그아웃 등 구현

> `index` 에서 메뉴html의 스크립트에 있는 함수를 가져와서 index에서 사용해야 한다.

##### 솔루션

- `evar` 함수,  구현이 어려운점은 `index`에서 직접 작성

  

---

### 홍주형

#### `order`의 `cartId`가 생김으로써 파생되는 이슈

> `cartId` 가 여러개가 생겨서 `order`에 담기지 않음

  

---

### 이아현

> 병가 

