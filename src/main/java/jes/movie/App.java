
package jes.movie;

import java.util.Scanner;
import jes.movie.handler.InfoHandler;
import jes.movie.handler.MemberHandler;
import jes.movie.handler.ReviewHandler;
import jes.movie.util.Prompt;

public class App {
  static Scanner keyboard = new Scanner(System.in);

  public static void main(String[] args) {
    Prompt prompt = new Prompt(keyboard);
    
    ReviewHandler reviewHandler = new ReviewHandler(prompt);
    InfoHandler infoHandler = new InfoHandler(prompt);
    MemberHandler memberHandler = new MemberHandler(prompt);

    String command;

    do {
      System.out.print("\n명령> ");
      command = keyboard.nextLine();
      switch (command) {
        case "/info/add":
          infoHandler.addInfo();
          break;

        case "/info/list":
          infoHandler.listInfo();
          break;

        case "/info/detail":
          infoHandler.detailInfo();
          break;

        case "/info/update":
          infoHandler.updateInfo();
          break;
          
        case "/info/delete":
          infoHandler.deleteInfo();
          break;
          
        case "/member/add":
          memberHandler.addMember();
          break;

        case "/member/list":
          memberHandler.listMember();
          break;

        case "/member/detail":
          memberHandler.detailMember();;
          break;
          
        case "/member/update":
          memberHandler.updateMember();;
          break;
          
        case "/member/delete":
          memberHandler.deleteMember();
          break;

        case "/review/add":
          reviewHandler.addReview();
          break;

        case "/review/list":
          reviewHandler.listReview();
          break;

        case "/review/detail":
          reviewHandler.detailReview();
          break;
          
        case "/review/update":
          reviewHandler.updateReview();
          break;
          
        case "/review/delete":
          reviewHandler.deleteReview();
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


