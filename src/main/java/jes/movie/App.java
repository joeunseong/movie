package jes.movie;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import jes.movie.domain.Info;
import jes.movie.domain.Member;
import jes.movie.domain.Review;
import jes.movie.handler.InfoHandler;
import jes.movie.handler.MemberHandler;
import jes.movie.handler.ReviewHandler;
import jes.movie.util.Prompt;

public class App {
  static Scanner keyboard = new Scanner(System.in);
  static Deque<String> commandStack = new ArrayDeque<>();
  static Queue<String> commandQueue = new LinkedList<>();

  public static void main(String[] args) {
    Prompt prompt = new Prompt(keyboard);
    
    LinkedList<Review> reviewList = new LinkedList<>();
    ReviewHandler reviewHandler = new ReviewHandler(prompt, reviewList);
    
    ArrayList<Info> InfoList = new ArrayList<>();
    InfoHandler infoHandler = new InfoHandler(prompt, InfoList);
    
    LinkedList<Member> memberList = new LinkedList<>();
    MemberHandler memberHandler = new MemberHandler(prompt, memberList);

    String command;
    
    do {
      System.out.print("\n명령> ");
      command = keyboard.nextLine();
      
      if (command.length() == 0)
        continue;
      
      commandStack.push(command);
      commandQueue.offer(command);
      
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

        case "history":
          printCommandHistory(commandStack.iterator());
          break;
          
        case "history2":
          printCommandHistory(commandQueue.iterator());
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

  private static void printCommandHistory(Iterator<String> iterator) {
    int count = 0;
    while (iterator.hasNext()) {
      System.out.println(iterator.next());
      count++;
      if ((count % 5) == 0) {
        System.out.print(":");
        String str = keyboard.nextLine();
        if (str.equalsIgnoreCase("q")) {
          break;
        }
      }
    }
  }
}



