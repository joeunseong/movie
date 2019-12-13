package com.bitcamp.myproject;

import java.sql.Date;
import java.util.Scanner;

public class App2 {
  public static void main(String[] args) {
    Scanner keyboard = new Scanner(System.in);
    int[] no = new int[10000];
    String[] name = new String[10000];
    String[] email = new String[10000];
    String[] password = new String[10000];
    String[] photo = new String[10000];
    String[] tel = new String[10000];
    Date[] registerDate = new Date[10000];
    int count = 0;

    for (int i = 0; i < 10000; i++) {
      System.out.print("번호? ");
      no[i] = Integer.parseInt(keyboard.nextLine());

      System.out.print("이름? ");
      name[i] = keyboard.nextLine();

      System.out.print("이메일? ");
      email[i] = keyboard.nextLine();

      System.out.print("암호? ");
      password[i] = keyboard.nextLine();

      System.out.print("사진? ");
      photo[i] = keyboard.nextLine();

      System.out.print("전화? ");
      tel[i] = keyboard.nextLine();

      Date today = new Date(System.currentTimeMillis());
      registerDate[i] = today;
      count++;
      
      System.out.println("계속 등록하시겠습니까?(Y/n)");
      String answer = keyboard.nextLine();

      if (!answer.equalsIgnoreCase("y")) {
        break;
      }
    }
    keyboard.close();
    
    System.out.println();

    for(int i = 0; i< count; i++) {
    System.out.printf("번호: %d\n", no[i]);
    System.out.printf("이름: %s\n", name[i]);
    System.out.printf("이메일: %s\n", email[i]);
    System.out.printf("암호: %s\n", password[i]);
    System.out.printf("사진: %s\n", photo[i]);
    System.out.printf("전화: %s\n", tel[i]);
    System.out.printf("가입일: %s\n", registerDate[i]);
    System.out.println();
    }
  }
}
