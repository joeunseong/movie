-- 영화 정보 예제 데이터 
insert into movie_info(info_id, mv_titl, conts, gr, dct, act, sort, opn_day, rnt)
  values(101, '세 얼간이', '‘알 이즈 웰’을 외치던 유쾌한 세 남자가 돌아왔다!', '코미디', 
  '라지쿠마르 히라니', '아미르 칸(란초), 마드하반(파르한), 셔먼 조쉬(라주), 카리나 카푸르(피아)',
  '12세 관람가', '2011-08-18', 141);
  
insert into movie_info(info_id, mv_titl, conts, gr, dct, act, sort, opn_day, rnt)
  values(102, '겨울왕국1', '얼어붙은 세상을 녹일 자매가 온다!', '애니메이션, 모험', 
  '크리스 벅, 제니퍼 리 ', '크리스틴 벨(안나 목소리), 이디나 멘젤(엘사 목소리)',
  '전체 관람가', '2014-01-16', 108);
  
insert into movie_info(info_id, mv_titl, conts, gr, dct, act, sort, opn_day, rnt)
  values(103, '아이 캔 스피크', '꼭…하고 싶은 말이 있고, 듣고 싶은 말이 있다!', '드라마', 
  '김현석', '나문희(나옥분), 이제훈(박민재)', '12세 관람가', '2017-09-21', 119);
  
insert into movie_info(info_id, mv_titl, conts, gr, dct, act, sort, opn_day, rnt)
  values(104, '겨울왕국2', '나를 부르는 저 목소리는 누구지?', '애니메이션, 모험',
  '크리스 벅, 제니퍼 리 ', '크리스틴 벨(안나 목소리), 이디나 멘젤(엘사 목소리)',
  '전체 관람가', '2019-11-21', 103);
  
insert into movie_info(info_id, mv_titl, conts, gr, dct, act, sort, opn_day, rnt)
  values(105, '정직한 후보', '어제까진 뻥쟁이, 오늘부턴 정직한 후보?!', '코미디', 
  '장유정', '라미란(주상숙), 김무열(박희철), 나문희(김옥희)','12세 관람가', '2020-02-12', 104);
  

-- 회원 예제 데이터
insert into movie_member(member_id, name, email, pwd) 
  values(11, 'user1', 'user1@test.com', password('1111'));
  
insert into movie_member(member_id, name, email, pwd) 
  values(12, 'user2', 'user2@test.com', password('1111'));
  
insert into movie_member(member_id, name, email, pwd) 
  values(13, 'user3', 'user3@test.com', password('1111'));

-- 게시물 예제 데이터
insert into movie_review(review_id, mv_titl, conts, cdt, vw_cnt) 
  values(1, '세 얼간이', '내용1', '2011-09-10', 0);
  
insert into movie_review(review_id, mv_titl, conts, cdt, vw_cnt)  
  values(2, '겨울왕국1', '내용2', '2014-02-02', 0);
  
insert into movie_review(review_id, mv_titl, conts, cdt, vw_cnt)  
  values(3, '아이 캔 스피크', '내용3', '2018-03-05', 0);
  
insert into movie_review(review_id, mv_titl, conts, cdt, vw_cnt)  
  values(4, '겨울왕국2', '내용4', '2020-12-08', 0);
  
insert into movie_review(review_id, mv_titl, conts, cdt, vw_cnt)  
  values(5, '정직한 후보', '내용5', '2020-03-10', 0);

-- 수업 사진 게시물 예제 데이터
insert into movie_photo(photo_id, info_id, titl) 
  values(1, 101, '세 얼간이 포스터');
  
insert into movie_photo(photo_id, info_id, titl) 
  values(2, 102, '겨울왕국1 포스터');
  
insert into movie_photo(photo_id, info_id, titl)  
  values(3, 103, '아이 캔 스피크 포스터');
  
insert into movie_photo(photo_id, info_id, titl) 
  values(4, 104, '겨울왕국2 포스터');
  
insert into movie_photo(photo_id, info_id, titl)  
  values(5, 105, '정직한 후보 포스터');

-- 수업 사진 게시물 첨부 파일 예제 데이터
insert into movie_photo_file(photo_file_id, photo_id, file_path)
  values(1, 1, 'a1.gif');
insert into movie_photo_file(photo_file_id, photo_id, file_path)
  values(2, 1, 'a2.gif');
insert into movie_photo_file(photo_file_id, photo_id, file_path)
  values(3, 1, 'a3.gif');
insert into movie_photo_file(photo_file_id, photo_id, file_path)
  values(4, 2, 'b1.gif');
insert into movie_photo_file(photo_file_id, photo_id, file_path)
  values(5, 3, 'c1.gif');
insert into movie_photo_file(photo_file_id, photo_id, file_path)
  values(6, 3, 'c2.gif');
insert into movie_photo_file(photo_file_id, photo_id, file_path)
  values(7, 4, 'd1.gif');
insert into movie_photo_file(photo_file_id, photo_id, file_path)
  values(8, 5, 'e1.gif');
insert into movie_photo_file(photo_file_id, photo_id, file_path)
  values(9, 5, 'e2.gif');
insert into movie_photo_file(photo_file_id, photo_id, file_path)
  values(10, 5, 'e3.gif');
insert into movie_photo_file(photo_file_id, photo_id, file_path)
  values(11, 5, 'e4.gif');

