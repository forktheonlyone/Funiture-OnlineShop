# (11) 12.26 스크럼

### 남은 작업 목록

#### 송한올

- 카테고리별 상품 목록 뷰
- (관리자) 상품 수정 뷰
- 상품 상세 페이지 ( 수정 )



#### 배준혁

- Index 메뉴바

- 상품평 ( 페이먼츠 까지 끝나야 작업 가능 )



#### 홍주형

- ( 유저 ) 장바구니 뷰
- ( 유저 ) 결제 확인 뷰
- ( 유저 ) `orderCheck` 결제된 주문 확인 뷰



#### 이아현

- 게시판
- Payments

## **회고**

### **이슈**

> 오늘자 발생한 이슈 정리
>

### **송한올**

### **상품 상세 페이지 옵션 오류**

> 옵션 정보를 불러오지 못해 500 에러 발생
>

### **솔루션**

- 변경되었던 변수명 `quantity` -> `stockQuantity` 적용
- 옵션에 저장되어있는 `productId` 또한 프론트에서 알 수 있게 수정
- 상품 ID 가져올수 있게끔 HTML 코드 수정

### **상품 상세 페이지 이미지 불러오기 오류**

> 저장된 이미지 정보를 불러오지 못하고 (구) 엑스박스 표시
>

### **솔루션**

- 파일 찾는 `getImage` 메소드를 컨트롤러에 추가
- 파일은 여러개이므로 `Optional` -> `List` 변경
- 파일 찾는 서비스 로직 구현

### 이아현

### 페이먼츠 로직 구현 오류

> 페이먼츠에 함수들을 불러오는 과정에서 어떻게 불러와야할지
>

### **솔루션**

- 각 서비스의 함수들의 변수명을 `OptionDTO`를 만들어 관리
- 옵션의 재고관리 함수를 오더에서 처리할 수 있게 작업

### 게시판 페이징과 세이브 기능 구현 실패

> 계속되는 500에러
>

### **솔루션**

- 배준혁 팀원의 도움으로 같이 처리예정

### 홍주형

### 장바구니 유저의 값이 들어오지않음
>


### 배준혁
> 안되는 팀원들을 도와줌
