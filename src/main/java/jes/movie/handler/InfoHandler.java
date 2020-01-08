package jes.movie.handler;

import java.sql.Date;
import java.util.Scanner;
import jes.movie.domain.Info;
import jes.movie.util.ArrayList;

public class InfoHandler {

  public Scanner keyboard;

  ArrayList<Info> infoList;

  public InfoHandler(Scanner keyboard) {
    this.keyboard = keyboard;
    infoList = new ArrayList<>();
  }

  public InfoHandler(Scanner keyboard, int capacity) {
    this.keyboard = keyboard;
    infoList = new ArrayList<>(capacity);
  }

  public void addInfo() {
    Info info = new Info();

    System.out.print("번호? ");
    info.setNo(keyboard.nextInt());

    keyboard.nextLine();

    System.out.print("영화명? ");
    info.setMovieTitle(keyboard.nextLine());

    System.out.print("장르? ");
    info.setGenre(keyboard.nextLine());

    System.out.print("줄거리? ");
    info.setSummary(keyboard.nextLine());

    System.out.print("감독? ");
    info.setDirector(keyboard.nextLine());

    System.out.print("출연? ");
    info.setActor(keyboard.nextLine());

    System.out.print("관람등급? ");
    info.setKmrb(keyboard.nextLine());

    System.out.print("개봉일? ");
    info.setOpenDate(Date.valueOf(keyboard.nextLine()));

    System.out.print("러닝타임? ");
    info.setRunningTime(keyboard.nextInt());
    keyboard.nextLine();

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
    System.out.print("번호? ");
    int no = keyboard.nextInt();
    keyboard.nextLine();
    
    int index = indexOfInfo(no);
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
    System.out.print("번호? ");
    int no = keyboard.nextInt();
    keyboard.nextLine();
    
    int index = indexOfInfo(no);
    if(index == -1) {
      System.out.println("해당 번호의 게시글이 없습니다.");
      return;
    }
    
    Info oldInfo = this.infoList.get(index);
    String inputStr = null;
    Info newInfo = new Info();
    
    System.out.printf("영화명(%s)?", oldInfo.getMovieTitle());
    inputStr = keyboard.nextLine();
    
    if(inputStr.length() == 0) {
      newInfo.setMovieTitle(oldInfo.getMovieTitle());
    } else {
      newInfo.setMovieTitle(inputStr);
    }
    
    newInfo.setNo(oldInfo.getNo());
    
    System.out.printf("장르(%s)? ", oldInfo.getGenre());
    inputStr = keyboard.nextLine();
    
    if(inputStr.length() == 0) {
      newInfo.setGenre(oldInfo.getGenre());
    } else {
      newInfo.setGenre(inputStr);
    }
    
    System.out.printf("줄거리(%s)? ", oldInfo.getSummary());
    inputStr = keyboard.nextLine();
    if(inputStr.length() == 0) {
      newInfo.setSummary(oldInfo.getSummary());
    } else {
      newInfo.setSummary(inputStr);
    }
    
    System.out.printf("감독(%s)? ", oldInfo.getDirector());
    inputStr = keyboard.nextLine();
    
    if(inputStr.length() == 0) {
      newInfo.setDirector(oldInfo.getGenre());
    } else {
      newInfo.setDirector(inputStr);;
    }
    
    System.out.printf("출연(%s)? ", oldInfo.getActor());
    inputStr = keyboard.nextLine();
    
    if(inputStr.length() == 0) {
      newInfo.setActor(oldInfo.getGenre());
    } else {
      newInfo.setActor(inputStr);
    }
    
    System.out.printf("관람등급(%s)? ", oldInfo.getKmrb());
    inputStr = keyboard.nextLine();
    
    if(inputStr.length() == 0) {
      newInfo.setKmrb(oldInfo.getGenre());
    } else {
      newInfo.setKmrb(inputStr);
    }
    
    System.out.printf("개봉일(%s)? ", oldInfo.getOpenDate());
    inputStr = keyboard.nextLine();
    if(inputStr.length() == 0) {
      newInfo.setOpenDate(oldInfo.getOpenDate());
    } else {
      newInfo.setOpenDate(Date.valueOf(inputStr));
    }
    
    System.out.printf("러닝타임: %d\n", oldInfo.getRunningTime());
    inputStr = keyboard.nextLine();
    if(inputStr.length() == 0) {
      newInfo.setRunningTime(oldInfo.getRunningTime());
    } else {
      newInfo.setRunningTime(Integer.parseInt(inputStr));
    }
    
    this.infoList.set(index, newInfo);
    System.out.println("정보가 수정되었습니다.");
  }
  
  public void deleteInfo() {
    System.out.print("번호? ");
    int no = keyboard.nextInt();
    keyboard.nextLine();
    
    int index = indexOfInfo(no);
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
