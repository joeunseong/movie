package jes.movie;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import jes.movie.dao.InfoDao;
import jes.movie.dao.MemberDao;
import jes.movie.dao.ReviewDao;
import jes.movie.dao.mariadb.InfoDaoImpl;
import jes.movie.dao.mariadb.MemberDaoImpl;
import jes.movie.dao.mariadb.ReviewDaoImpl;
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

public class ClientApp {
  Scanner keyboard = new Scanner(System.in);
  Prompt prompt = new Prompt(keyboard);

  Deque<String> commandStack;
  Queue<String> commandQueue;

  Connection con;

  HashMap<String, Command> commandMap = new HashMap<>();

  public ClientApp() throws Exception {
    commandStack = new ArrayDeque<>();
    commandQueue = new LinkedList<>();

    Class.forName("org.mariadb.jdbc.Driver");
    con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/moviedb", "movie", "1111");

    InfoDao infoDao = new InfoDaoImpl(con);
    MemberDao memberDao = new MemberDaoImpl(con);
    ReviewDao reviewDao = new ReviewDaoImpl(con);

    commandMap.put("/info/list", new InfoListCommand(infoDao));
    commandMap.put("/info/add", new InfoAddCommand(infoDao, prompt));
    commandMap.put("/info/delete", new InfoDeleteCommand(infoDao, prompt));
    commandMap.put("/info/detail", new InfoDetailCommand(infoDao, prompt));
    commandMap.put("/info/update", new InfoUpdateCommand(infoDao, prompt));

    commandMap.put("/member/list", new MemberListCommand(memberDao));
    commandMap.put("/member/add", new MemberAddCommand(memberDao, prompt));
    commandMap.put("/member/delete", new MemberDeleteCommand(memberDao, prompt));
    commandMap.put("/member/detail", new MemberDetailCommand(memberDao, prompt));
    commandMap.put("/member/update", new MemberUpdateCommand(memberDao, prompt));

    commandMap.put("/review/list", new ReviewListCommand(reviewDao));
    commandMap.put("/review/add", new ReviewAddCommand(reviewDao, prompt));
    commandMap.put("/review/detail", new ReviewDetailCommand(reviewDao, prompt));
    commandMap.put("/review/delete", new ReviewDeleteCommand(reviewDao, prompt));
    commandMap.put("/review/update", new ReviewUpdateCommand(reviewDao, prompt));

  }

  public void service() {
    while (true) {
      String command;
      command = prompt.inputString("\n명령> ");

      if (command.length() == 0) {
        continue;
      }

      if (command.equals("history")) {
        printCommandHistory(commandStack.iterator());
        continue;
      } else if (command.equals("history2")) {
        printCommandHistory(commandQueue.iterator());
        continue;
      } else if (command.equals("quit")) {
        break;
      }

      commandStack.push(command);
      commandQueue.offer(command);

      processCommand(command);
    }
    keyboard.close();

    try {
      con.close();
    } catch (Exception e) {

    }
  }

  private void processCommand(String command) {
    Command commandHandler = commandMap.get(command);
    if (commandHandler == null) {
      System.out.println("실행할 수 없는 명령입니다.");
      return;
    }
    commandHandler.execute();
  }

  public void printCommandHistory(Iterator<String> iterator) {
    int count = 0;
    while (iterator.hasNext()) {
      System.out.println(iterator.next());
      count++;

      if ((count % 5) == 0) {
        String str = prompt.inputString(":");
        if (str.equalsIgnoreCase("q")) {
          break;
        }
      }
    }
  }

  public static void main(String[] args) throws Exception {
    System.out.println("클라이언트 수업 관리 시스템입니다");
    ClientApp app = new ClientApp();
    app.service();
  }
}
