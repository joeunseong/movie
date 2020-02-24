# 26_3 - 파일 포맷으로 JSON 도입하기

### 작업 1: Gradle 스크립트 파일(build.gradle)에 Google JSON 라이브러리를 추가하라

- mvnrepository.com 에서 라이브러리 검색
  - json.org 사이트에서 자바 라이브러리 확인
  - 'gson' 키워드로 검색
- build.gradle 을 편집한다.
  - 의존 라이브러리 블록(dependencies {})에 gson 정보를 추가한다.
- 이클립스 설정 파일을 갱신한다.
  - 'gradle eclipse' 를 실행
  - 이클립스에서 해당 프로젝트를 'refresh' 한다.
  - 'Referenced Libraries' 노드에서 gson 라이브러리 파일이 추가된 것을 확인한다.
  
### 작업 2: 영화 정보 데이터를 저장하고 읽을 때 JSON 형식을 사용하라.

- App.java
  - saveInfoData()를 변경한다.
  - loadInfoData()를 변경한다.
  
### 작업 3: 회원 데이터를 저장하고 읽을 때 JSON 형식을 사용하라.

- App.java
  - saveMemberData()를 변경한다.
  - loadMemberData()를 변경한다.
  
### 작업 4: 리뷰 데이터를 저장하고 읽을 때 JSON 형식을 사용하라.

- App.java
  - saveReviewData()를 변경한다.
  - loadReviewData()를 변경한다.