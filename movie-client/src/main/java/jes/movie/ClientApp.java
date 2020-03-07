package jes.movie;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import jes.movie.dao.proxy.InfoDaoProxy;
import jes.movie.dao.proxy.MemberDaoProxy;
import jes.movie.dao.proxy.ReviewDaoProxy;
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

  public void service() {
    String serverAddr = null;
    int port = 0;

    try {
      serverAddr = prompt.inputString("서버? ");
      port = prompt.inputInt("포트? ");

    } catch (Exception e) {
      System.out.println("서버 주소 또는 포트 번호가 유효하지 않습니다.");
      keyboard.close();
      return;
    }
    try (Socket socket = new Socket(serverAddr, port);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {
      System.out.println("서버와 연결되었음!");

      processCommand(out, in);

      System.out.println("서버와 연결을 끊었음!");

    } catch (Exception e) {
      System.out.println("예외발생:");
      e.printStackTrace();
    }
    keyboard.close();
  }

  private void processCommand(ObjectOutputStream out, ObjectInputStream in) {
    Deque<String> commandStack = new ArrayDeque<>();
    Queue<String> commandQueue = new LinkedList<>();

    InfoDaoProxy infoDao = new InfoDaoProxy(in, out);
    MemberDaoProxy memberDao = new MemberDaoProxy(in, out);
    ReviewDaoProxy reviewDao = new ReviewDaoProxy(in, out);
    
    HashMap<String, Command> commandMap = new HashMap<>();

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
    try {
      while (true) {
        String command;
        command = prompt.inputString("\n명령> ");

        if (command.length() == 0) {
          continue;
        }

        if (command.equals("quit") || command.equals("/server/stop")) {
          out.writeUTF(command);
          out.flush();
          System.out.println("서버: " + in.readUTF());
          System.out.println("안녕!");
          break;
        } else if (command.equals("history")) {
          printCommandHistory(commandStack.iterator());
          continue;
        } else if (command.equals("history2")) {
          printCommandHistory(commandQueue.iterator());
          continue;
        }


        commandStack.push(command);
        commandQueue.offer(command);

        Command commandHandler = commandMap.get(command);

        if (commandHandler != null) {
          try {
            commandHandler.execute();
          } catch (Exception e) {
            e.printStackTrace();
            System.out.printf("명령어 실행 중 오류 발생: %s\n", e.getMessage());
          }
        } else {
          System.out.println("실행할 수 없는 명령입니다.");
        }
      }
    } catch (Exception e) {
      System.out.println("프로그램 실행 중 오류 발생!");
    }
    keyboard.close();
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
