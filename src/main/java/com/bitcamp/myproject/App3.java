package com.bitcamp.myproject;

import java.sql.Date;
import java.util.Scanner;

public class App3 {
  public static void main(String[] args) {
    Scanner keyboard = new Scanner(System.in);
    
    System.out.print("번호? ");
    int no = Integer.parseInt(keyboard.nextLine());
    
    System.out.print("내용? ");
    String text = keyboard.nextLine();
    
    keyboard.close();
    
    Date today = new Date(System.currentTimeMillis());
    
    int count = 0;
    
    System.out.println();
    
    System.out.printf("번호: %s\n", no);
    System.out.printf("내용: %s\n", text);
    System.out.printf("작성일: %s\n", today);
    System.out.printf("조회수: %s\n", count);
  }
}



