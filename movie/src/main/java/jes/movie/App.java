
package jes.movie;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import jes.movie.context.ApplicationContextListener;
import jes.movie.domain.Info;
import jes.movie.domain.Member;
import jes.movie.domain.Review;
import jes.movie.handler.Command;
import jes.movie.handler.InfoAddCommand;
import jes.movie.handler.InfoDeleteCommand;
import jes.movie.handler.InfoDetailCommand;
import jes.movie.handler.InfoListCommand;
import jes.movie.handler.InfoUpdateCommand;
import jes.movie.handler.MemberAddCommand;
import jes.movie.handler.MemberDeleteCommand;
import jes.movie.handler.MemberDetailCommand;
import jes.movie.handler.MemberListCommand;
import jes.movie.handler.MemberUpdateCommand;
import jes.movie.handler.ReviewAddCommand;
import jes.movie.handler.ReviewDeleteCommand;
import jes.movie.handler.ReviewDetailCommand;
import jes.movie.handler.ReviewListCommand;
import jes.movie.handler.ReviewUpdateCommand;
import jes.movie.util.Prompt;

public class App {
  Scanner keyboard = new Scanner(System.in);
  Deque<String> commandStack = new ArrayDeque<>();
  Queue<String> commandQueue = new LinkedList<>();

  Set<ApplicationContextListener> listeners = new HashSet<>();

  Map<String, Object> context = new HashMap<>();

  public void addApplicationContextListener(ApplicationContextListener listener) {
    listeners.add(listener);
  }

  public void removeApplicationContextListener(ApplicationContextListener listener) {
    listeners.remove(listener);
  }

  private void notifyApplicationInitailized() {
    for (ApplicationContextListener listener : listeners) {
      listener.contextInitialized(context);
    }
  }

  private void notifyApplicationDestroyed() {
    for (ApplicationContextListener listener : listeners) {
      listener.contextDestroyed(context);
    }
  }

  @SuppressWarnings("unchecked")
  public void service() {
    notifyApplicationInitailized();

    List<Review> reviewList = (List<Review>) context.get("reviewList");
    List<Info> infoList = (List<Info>) context.get("infoList");
    List<Member> memberList = (List<Member>) context.get("memberList");

    Prompt prompt = new Prompt(keyboard);
    HashMap<String, Command> commandMap = new HashMap<>();

    commandMap.put("/review/add", new ReviewAddCommand(prompt, reviewList));
    commandMap.put("/review/detail", new ReviewDetailCommand(prompt, reviewList));
    commandMap.put("/review/delete", new ReviewDeleteCommand(prompt, reviewList));
    commandMap.put("/review/list", new ReviewListCommand(reviewList));
    commandMap.put("/review/update", new ReviewUpdateCommand(prompt, reviewList));

    commandMap.put("/info/add", new InfoAddCommand(prompt, infoList));
    commandMap.put("/info/delete", new InfoDeleteCommand(prompt, infoList));
    commandMap.put("/info/detail", new InfoDetailCommand(prompt, infoList));
    commandMap.put("/info/update", new InfoUpdateCommand(prompt, infoList));
    commandMap.put("/info/list", new InfoListCommand(infoList));

    commandMap.put("/member/add", new MemberAddCommand(prompt, memberList));
    commandMap.put("/member/delete", new MemberDeleteCommand(prompt, memberList));
    commandMap.put("/member/detail", new MemberDetailCommand(prompt, memberList));
    commandMap.put("/member/list", new MemberListCommand(memberList));
    commandMap.put("/member/update", new MemberUpdateCommand(prompt, memberList));

    String command;

    while (true) {
      System.out.print("\n명령> ");
      command = keyboard.nextLine();

      if (command.equals("quit")) {
        System.out.println("종료");
        break;
      } else if (command.equals("history")) {
        printCommandHistory(commandStack.iterator());
        continue;
      } else if (command.equals("history2")) {
        printCommandHistory(commandQueue.iterator());
        continue;
      }

      if (command.length() == 0)
        continue;

      commandStack.push(command);

      commandQueue.offer(command);

      Command commandHandler = commandMap.get(command);

      if (commandHandler != null) {
        try {
          commandHandler.excute();
        } catch (Exception e) {
          System.out.printf("명령어 실행 중 오류 발생: %s\n", e.getMessage());
        }
      } else {
        System.out.println("실행할 수 없는 명령입니다.");
      }
    }
    keyboard.close();


    notifyApplicationDestroyed();

  }

  private void printCommandHistory(Iterator<String> iterator) {
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

  public static void main(String[] args) {
    App app = new App();
    app.addApplicationContextListener(new DataLoaderListener());
    app.addApplicationContextListener(new GreetingListener());
    app.service();
  }
}

