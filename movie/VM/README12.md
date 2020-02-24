# 12 - 클래스 필드와 클래스 메서드의 한계

### 작업1) 새 게시판을 추가하라.

- ReviewHandler2.java
    - `/review2/add`, `/review2/list` 명령을 처리할 클래스를 추가한다.
- App.java
    - 새 명령을 처리하는 코드를 추가한다.
    
- ReviewHandler.java, ReviewHandler2.java
    - detailReview()메소드를 추가해서 게시물에 대한 디테일한 정보를 출력할 수 있다.
    
실행 결과:

```
명령> /review2/add
번호? 1
내용? 리뷰글1
저장하였습니다.

명령> /review2/add
번호? 2
내용? 리뷰글2
저장하였습니다.

명령> /review/add
번호? 100
내용? 리뷰글100
저장하였습니다.

명령> /review2/list
1, 리뷰글1                  , 2019-01-01, 0
2, 리뷰글2                  , 2019-01-01, 0

명령> /review/list
100, 리뷰글100              , 2019-01-01, 0

명령> /review1/detail
번호: 100
영화제목: 겨울왕국
리뷰 내용: 정말 재밌습니다~

명령> /review2/detail
번호: 10
영화제목: 해리포터
리뷰 내용: 역시 해리포터!
```
