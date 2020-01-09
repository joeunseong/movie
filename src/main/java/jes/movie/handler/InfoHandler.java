package jes.movie.handler;

import jes.movie.domain.Info;
import jes.movie.util.ArrayList;
import jes.movie.util.Prompt;

public class InfoHandler {

  public Prompt prompt;
  
  ArrayList<Info> infoList;

  public InfoHandler(Prompt prompt) {
    this.prompt = prompt;
    infoList = new ArrayList<>();
  }

  public InfoHandler(Prompt prompt, int capacity) {
    this.prompt = prompt;
    infoList = new ArrayList<>(capacity);
  }

  public void addInfo() {
    Info info = new Info();

    info.setNo(prompt.inputInt("번호? "));
    info.setMovieTitle(prompt.inputString("영화명? "));
    info.setGenre(prompt.inputString("장르? "));
    info.setSummary(prompt.inputString("줄거리? "));
    info.setDirector(prompt.inputString("감독? "));
    info.setActor(prompt.inputString("출연? "));
    info.setKmrb(prompt.inputString("관람등급? "));
    info.setOpenDate(prompt.inputDate("개봉일? "));
    info.setRunningTime(prompt.inputInt("러닝타임? "));
    infoList.add(info);
    System.out.println("등록되었습니다.");
  }

  public void listInfo() {
    Info[] arr = this.infoList.toArray(new Info[this.infoList.size()]);
    for (Info in : arr) {
      System.out.printf("%d, %s, %s, %s, %s, %s, %s, %s, %d분\n", 
          in.getNo(), in.getMovieTitle(), in.getGenre(), in.getSummary(), 
          in.getDirector(), in.getActor(), in.getKmrb(),
          in.getOpenDate(), in.getRunningTime());
    }
  }
  
  public void detailInfo() {
    int index = indexOfInfo(prompt.inputInt("번호? "));
    if(index == -1) {
      System.out.println("해당 번호의 게시글이 없습니다.");
      return;
    }

    Info info = this.infoList.get(index);
    System.out.printf("영화명 : %s\n", info.getMovieTitle());
    System.out.printf("장르: %s\n", info.getGenre());
    System.out.printf("줄거리: %s\n", info.getSummary());
    System.out.printf("감독: %s\n", info.getDirector());
    System.out.printf("출연: %s\n", info.getActor());
    System.out.printf("관람등급: %s\n", info.getKmrb());
    System.out.printf("개봉일: %s\n", info.getOpenDate());
    System.out.printf("러닝타임: %d\n", info.getRunningTime());
  }
  
  public void updateInfo() {
    int index = indexOfInfo(prompt.inputInt("번호? "));
    if(index == -1) {
      System.out.println("해당 번호의 게시글이 없습니다.");
      return;
    }
    
    Info oldInfo = this.infoList.get(index);
    Info newInfo = new Info();
    newInfo.setNo(oldInfo.getNo());
  
    newInfo.setMovieTitle(prompt.inputString(String.format("영화명(%s)", oldInfo.getMovieTitle()),
        oldInfo.getMovieTitle()));
    
    newInfo.setGenre(prompt.inputString(String.format("장르(%s)", oldInfo.getGenre()),
          oldInfo.getGenre()));
      
    newInfo.setSummary(prompt.inputString(String.format("줄거리", oldInfo.getSummary()),
          oldInfo.getSummary()));
    
    newInfo.setDirector(prompt.inputString(String.format("감독(%s)? ", oldInfo.getDirector()),
          oldInfo.getDirector()));
    
    newInfo.setActor(prompt.inputString(String.format("출연(%s)? ", oldInfo.getActor()),
        oldInfo.getActor()));
    
    newInfo.setKmrb(prompt.inputString(String.format("관람등급(%s)? ", oldInfo.getKmrb()),
        oldInfo.getKmrb()));
    
    newInfo.setOpenDate(prompt.inputDate(String.format("개봉일(%s)? ", oldInfo.getOpenDate()),
          oldInfo.getOpenDate()));
    
    newInfo.setRunningTime(prompt.inputInt(String.format("러닝타임(%s)", oldInfo.getRunningTime()),
        oldInfo.getRunningTime()));
    
    if(oldInfo.equals(newInfo)) {
      System.out.println("정보 변경을 취소하였습니다.");
      return;
    }
    
    this.infoList.set(index, newInfo);
    System.out.println("정보가 수정되었습니다.");
  }
  
  public void deleteInfo() {
    int index = indexOfInfo(prompt.inputInt("번호? "));
    if(index == -1) {
      System.out.println("해당 번호의 게시글이 없습니다.");
      return;
    }
    
    this.infoList.remove(index);
    System.out.println("정보 인덱스를 삭제했습니다.");
  }

  private int indexOfInfo(int no) {
    for(int i =0; i < this.infoList.size(); i++) {
      if(this.infoList.get(i).getNo() == no) {
        return i;
      }
    }
    return -1;
  }
}