# 30_8 - DAO 클래스의 공통점을 뽑아 수퍼 클래스로 정의하기(generalization 수행하기)

## 작업목표

- generalization을 구현할 수 있다.


## 작업 소스 및 결과

- src/main/java/jes/movie/dao/AbstractObjectFileDao.java 추가
- src/main/java/jes/movie/dao/InfoObjectFileDao.java 변경
- src/main/java/jes/movie/dao/MemberObjectFileDao.java 변경
- src/main/java/jes/movie/dao/ReviewObjectFileDao.java 변경


### 작업 1: DAO의 공통점을 뽑아 수퍼 클래스로 정의하기.

- jes.movie.dao.AbstractObjectFileDao 클래스를 생성한다.


### 작업 2: InfoObjectFileDao가 위에서 정의한 클래스를 상속 받도록 변경하기.

- jes.movie.dao.InfoObjectFileDao 변경한다.


### 작업 3: MemberObjectFileDao가 위에서 정의한 클래스를 상속 받도록 변경하기.

-com.eomcs.lms.dao.MemberObjectFileDao 변경한다.

  
### 작업 4: ReviewObjectFileDao가 위에서 정의한 클래스를 상속 받도록 변경하기.

-jes.movie.dao.ReviewObjectFileDao 변경한다. 