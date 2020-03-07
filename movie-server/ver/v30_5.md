# 30_5 - 특정 기능을 수행하는 코드를 메소드로 분리하기

## 작업 목표

- 기능별로 코드를 메소드로 분리하기
- "Extract Method" 리팩토링 기법을 사용하기

### 작업 1: 클라이언트의 요청을 처리하는 코드를 기능별로 분리하기

- ServerApp.java 변경
  - if~ else~ 분기문에 작성한 코드를 별도의 메소드로 분리하여 정의한다.
  - listInfo() : 영화 정보 목록 데이터 요청 처리
  - addInfo(): 영화 정보 데이터 등록 요청 처리
  - detailInfo() : 영화 정보 조회 요청 처리
  - updateInfo() : 영화 정보 변경 요청 처리
  - deleteInfo() : 영화 정보 삭제 요청
  - listMember() : 회원 목록 데이터 요청 처리
  - addMember(): 회원 데이터 등록 요청 처리
  - detailMember() : 회원 조회 요청 처리
  - updateMember() : 회원 변경 요청 처리
  - deleteMember() : 회원 삭제 요청
  - listReview() : 리뷰 목록 데이터 요청 처리
  - addReview(): 리뷰 데이터 등록 요청 처리
  - detailReview() : 리뷰 조회 요청 처리
  - updateReview() : 리뷰 변경 요청 처리
  - deleteReview() : 리뷰 삭제 요청
  