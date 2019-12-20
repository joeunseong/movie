# 08 - 기본 문법의 활용

## 학습 목표

- 변수, 상수, 연산자, 조건문, 반복문, 블록, 배열 등을 사용하여 정보를 입력할 수 있다.

## 실습

### 작업1) 사용자의 명령에 따라 영화 정보 등록 및 영화 정보 목록 출력을 처리하도록 변경하라.

- App.java (App.java.01)
    - 사용자로부터 명령을 입력 받는 부분을 추가한다.
    - 조건문을 사용하여 명령어 따라 처리를 분기하도록 변경한다. (switch문)
    - `quit` 명령이 입력 될 때 실행을 종료하게 한다. (do~while문 사용)
    - 일치하는 명령이 없을 경우 안내 메시지를 출력하게 한다. (switch문)

#### 실행 결과

```
명령>

명령> /info/add
번호? 1
영화명? 겨울왕국2
영화 장르? 애니메이션, 모험, 코미디, 가족, 판타지, 뮤지컬
영화 줄거리? 내 마법의 힘은 어디서 왔을까?
나를 부르는 저 목소리는 누구지?
어느 날 부턴가 의문의 목소리가 엘사를 부르고, 평화로운 아렌델 왕국을 위협한다. 
 트롤은 모든 것은 과거에서 시작되었음을 알려주며 엘사의 힘의 비밀과 진실을 찾아 떠나야한다고 조언한다. 
 위험에 빠진 아렌델 왕국을 구해야만 하는 엘사와 안나는 숨겨진 과거의 진실을 찾아 
 크리스토프, 올라프 그리고 스벤과 함께 위험천만한 놀라운 모험을 떠나게 된다. 
 자신의 힘을 두려워했던 엘사는 이제 이 모험을 헤쳐나가기에 자신의 힘이 충분하다고 믿어야만 하는데… 
 두려움을 깨고 새로운 운명을 만나다!
 감독? 감독크리스 벅, 제니퍼 리 
 출연? 크리스틴 벨(안나 목소리), 이디나 멘젤(엘사 목소리), 조시 게드(울라프 목소리)...
 등급? 전체 관람가
개봉일? 2019.11.21
러닝타임? 103 분
저장되었습니다.

명령> /info/add
번호? 2
영화명? 나 홀로 집에
영화 장르? 모험, 범죄, 가족, 코미디
영화 줄거리?이제 혼자 남은 꼬마 캐빈의 기발한 집지키기 전쟁이 시작된다!
크리스마스 시즌의 시카고. 말썽꾸러기라 집안 가족들로부터 욕을 듣고 따돌림 당하는 케빈은 늘 자신은 혼자 살거라면서 가족들이 모두 없어졌으면 좋겠다고 생각한다. 자기의 치즈 피자를 먹은 형과 싸워 소동을 일으키자 엄마는 케빈을 3층 다락방으로 올려보낸다. 케빈의 가족들과 케빈의 집에 온 손님들은 다음 날에 크리스마스 연휴를 이용해 프랑스의 친척 집으로 떠날 계획이었다. 
 그날 밤, 바람이 세차게 불어 전화선과 전기선이 끊긴다. 케빈의 가족들은 늦잠을 자게 되어 비행기 시간을 맞추기 위해 허둥대다가 그만 3층 다락방에서 잠이 든 케빈을 두고 떠난다. 잠에서 깬 케빈은 혼자 남은 것을 알고 하느님이 자신의 소원을 들어주었다고 기뻐한다. 비행기를 타고 가던 케빈의 어머니는 무엇인가 빠뜨린 기분에 고민하다가 케빈을 두고 왔음에 놀란다. 하지만 전화선이 불통이라, 어쩔 수 없다가 프랑스에 도착한 식구들은 목적지로 가고 엄마는 케빈이 걱정이 되어 집으로 돌아갈 비행기표를 사기 위해 안간힘을 쓰지만 연말연휴라 좌석이 없었다. 
 혼자 집에 남은 케빈은 형과 누나 방을 구경하면서 즐거워한다. 그리고 노래를 부르고 트리도 만들면서 자축한다. 그런데 빈집털이 2인조 도둑이 케빈의 집을 호시탐탐 노리고 있다는 것을 알게 되는데...
 감독? 크리스 콜럼버스
 출연? 맥컬리 컬킨(케빈 맥콜리스터), 조 페시(좀도둑 해리 림), 다니엘 스턴(좀도둑 마브 머챈츠)...
 등급? 전체 관람가
개봉일? 1991 .07.06 
러닝타임? 105 분
저장되었습니다.

명령> /info/add
번호? 3
영화명? 어벤져스:엔드게임
영화 장르? 액션, SF
영화 줄거리? 인피니티 워 이후 절반만 살아남은 지구
 마지막 희망이 된 어벤져스
 먼저 떠난 그들을 위해 모든 것을 걸었다!
 위대한 어벤져스
 운명을 바꿀 최후의 전쟁이 펼쳐진다!
 감독?안소니 루소, 조 루소
 출연? 로버트 다우니 주니어(토니 스타크 / 아이언맨), 크리스 에반스(스티브 로저스 / 캡틴), 스칼렛 요한슨(나타샤 로마노프/ 블랙위도우 역)...
 등급? 12세 관람가 
개봉일?  2019 .04.24 
러닝타임?  181 분
저장되었습니다.

명령> /info/list
1, 겨울왕국2, 애니메이션, 모험...,내 마법의 힘은 어디서 왔을까?..., ... ,  2019.11.21, 103
2, 나 홀로 집에 , 모험, 범죄, 가족, 코미디, 이제 혼자 남은 꼬마 캐빈의 기발한 ..., 1991.07.06 , 105
3, 자바 어벤져스:엔드게임, 액션, SF, 인피니티 워 이후 절반만 살아남은 지구..., 2019 .04.24 , 181

명령> info/view
실행할 수 없는 명령입니다.

명령> quit
종료
```

### 작업2) 사용자의 명령에 따라 회원 등록 및 목록 출력을 처리하도록 변경하라.

- App.java (App.java.02)
    - `App2.java` 클래스에 있는 코드를 `App.java` 로 옮긴다.
    - `App2.java` 는 삭제한다.

#### 실행 결과

```
명령> /member/add
번호? 1
이름? 홍길동
이메일? hong@test.com
암호? 1111
사진? hong.png
전화? 1111-2222
저장되었습니다.

명령> /member/list
1, 홍길동 , hong@test.com       , 1111-2222      , 2019-01-01
2, 임꺽정 , lim@test.com        , 1111-2223      , 2019-01-01
3, 전봉준 , jeon@test.com       , 1111-2224      , 2019-01-01
```

### 작업3) 사용자의 명령에 따라 리뷰글 등록 및 목록 출력을 처리하도록 변경하라.

- App.java
    - `App3.java` 클래스에 있는 코드를 `App.java` 로 옮긴다.
    - `App3.java` 는 삭제한다.

#### 실행 결과

```
명령> /review/add
번호? 1
영화명? ~
내용? 리뷰글입니다.
저장되었습니다.

명령> /review/add
번호? 2
영화명? ~
내용? 게시글입니다.
저장되었습니다.

명령> /review/add
번호? 3
영화명? ~
내용? 게시글입니다.
저장되었습니다.

명령> /review/list
1, 겨울왕국2, 안나와 엘사가..., 2019-01-01, 0
2, 나 홀로 집에, 꼬마 케빈의..., 2019-01-01, 0
3, 어벤져스:엔드게임, 아이언맨의 희생..., 2019-01-01, 0
```