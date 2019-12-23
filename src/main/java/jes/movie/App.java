
package jes.movie;

import java.util.Scanner;

public class App {
  static Scanner keyboard = new Scanner(System.in);
  static String command; 


  public static void main(String[] args) {
    InfoHandler.keyboard = keyboard;
    MemberHandler.keyboard = keyboard;
    ReviewHandler.keyboard = keyboard;

    do {
      System.out.print("\n명령> ");
      command = keyboard.nextLine();
      switch (command) {
        case "/info/add":
          InfoHandler.addInfo();
          break;
        case "/info/list":
          InfoHandler.listInfo();
          break;
        case "/member/add":
          MemberHandler.addMember();
          break;

        case "/member/list":
          MemberHandler.listMember();
          break;
        case "/review/add":
          ReviewHandler.addReview();
          break;

        case "/review/list":
          ReviewHandler.listReview();
          break;
        default:
          if (!command.equalsIgnoreCase("quit")) {
            System.out.println("실행할 수 없는 명령입니다.");
          }
      }
    } while (!command.equalsIgnoreCase("quit"));
    System.out.println("종료");

    keyboard.close();
  }
}


