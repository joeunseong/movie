package com.bitcamp.myproject;

import java.util.Scanner;

public class App3 {
  public static void main(String[] args) {
    Scanner keyboard = new Scanner(System.in);
    
    System.out.print("번호? ");
    String no = keyboard.nextLine();
    
    System.out.print("내용? ");
    String summary = keyboard.nextLine();
    
    System.out.print("작성일? ");
    String writeDate = keyboard.nextLine();
    
    System.out.print("조회수? ");
    String hit = keyboard.nextLine();
    
    System.out.println();
    
    System.out.printf("번호: %s\n", no);
    System.out.printf("내용: %s\n", summary);
    System.out.printf("작성일: %s\n", writeDate);
    System.out.printf("조회수: %s\n", hit);
    
    keyboard.close();
  }
}



