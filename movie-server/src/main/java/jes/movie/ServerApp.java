package jes.movie;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import jes.movie.context.ApplicationContextListener;
import jes.movie.domain.Info;
import jes.movie.domain.Member;
import jes.movie.domain.Review;

public class ServerApp {

  Set<ApplicationContextListener> listeners = new HashSet<>();
  Map<String, Object> context = new HashMap<>();
  List<Info> infos;
  List<Member> members;
  List<Review> reviews;

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

    infos = (List<Info>) context.get("infoList");
    members = (List<Member>) context.get("memberList");
    reviews = (List<Review>) context.get("reviewList");

    try (ServerSocket serverSocket = new ServerSocket(9999)) {
      System.out.println("클라이언트 연결 대기중...");

      while (true) {
        Socket socket = serverSocket.accept();
        System.out.println("클라이언트와 연결되었음!");

        if (processRequest(socket) == 9) {
          break;
        }
        System.out.println("----------------------");
      }
    } catch (Exception e) {
      System.out.println("서버 준비 중 오류 발생");
    }

    notifyApplicationDestroyed();
  }

  int processRequest(Socket clientSocket) {
    try (Socket socket = clientSocket;
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream())) {
      System.out.println("통신을 위한 입출력 스트림을 준비하였음!");

      while (true) {
        String request = in.readUTF();
        System.out.println("클라이언트가 보낸 메시지를 수신하였음!");

        switch (request) {
          case "quit":
            quit(out);
            return 0;
          case "/server/stop":
            quit(out);
            return 9;

          case "/info/list":
            listInfo(out);
            break;
          case "/info/add":
            addInfo(in, out);
            break;
          case "/info/detail":
            detailInfo(in, out);
            break;
          case "/info/update":
            updateInfo(in, out);
            break;
          case "/info/delete":
            deleteInfo(in, out);
            break;
          case "/member/list":
            listMember(out);
            break;
          case "/member/add":
            addMember(in, out);
            break;
          case "/member/detail":
            detailMember(in, out);
            break;
          case "/member/update":
            updateMember(in, out);
            break;
          case "/member/delete":
            deleteMember(in, out);
            break;
          case "/review/list":
            listReview(out);
            break;
          case "/review/add":
            addReview(in, out);
            break;
          case "/review/detail":
            detailReview(in, out);
            break;
          case "/review/update":
            updateReview(in, out);
            break;
          case "/review/delete":
            deleteReview(in, out);
            break;
          default:
            notFound(out);
        }
        out.flush();
        System.out.println("클라이언트로 메시지를 전송하였음!");
      }
    } catch (Exception e) {
      System.out.println("예외 발생:");
      e.printStackTrace();
      return -1;
    }
  }

  private void quit(ObjectOutputStream out) throws IOException {
    out.writeUTF("OK");
    out.flush();
  }

  private void notFound(ObjectOutputStream out) throws IOException {
    out.writeUTF("FAIL");
    out.writeUTF("요청한 명령을 처리할 수 없습니다.");
  }

  private void deleteReview(ObjectInputStream in, ObjectOutputStream out) throws IOException {
    try {
      int no = in.readInt();
      int index = -1;
      for (int i = 0; i < reviews.size(); i++) {
        if (reviews.get(i).getNo() == no) {
          index = i;
          break;
        }
      }

      if (index != -1) {
        reviews.remove(index);
        out.writeUTF("OK");

      } else {
        out.writeUTF("FAIL");
        out.writeUTF("해당 번호의 리뷰가 없습니다.");
      }
    } catch (Exception e) {
      out.writeUTF("FAIL");
      out.writeUTF(e.getMessage());
    }
  }

  private void updateReview(ObjectInputStream in, ObjectOutputStream out) throws IOException {
    try {
      Review review = (Review) in.readObject();

      int index = -1;
      for (int i = 0; i < reviews.size(); i++) {
        if (reviews.get(i).getNo() == review.getNo()) {
          index = i;
          break;
        }
      }

      if (index != -1) {
        reviews.set(index, review);
        out.writeUTF("OK");
      } else {
        out.writeUTF("FAIL");
        out.writeUTF("해당 번호의 수업이 없습니다.");
      }

    } catch (Exception e) {
      out.writeUTF("FAIL");
      out.writeUTF(e.getMessage());
    }
  }

  private void detailReview(ObjectInputStream in, ObjectOutputStream out) throws IOException {
    try {
      int no = in.readInt();

      Review review = null;
      for (Review r : reviews) {
        if (r.getNo() == no) {
          review = r;
          break;
        }
      }

      if (review != null) {
        out.writeUTF("OK");
        out.writeObject(review);

      } else {
        out.writeUTF("FAIL");
        out.writeUTF("해당 번호의 리뷰가 없습니다.");
      }

    } catch (Exception e) {
      out.writeUTF("FAIL");
      out.writeUTF(e.getMessage());
    }
  }

  private void addReview(ObjectInputStream in, ObjectOutputStream out) throws IOException {
    try {
      Review review = (Review) in.readObject();

      int i = 0;
      for (; i < reviews.size(); i++) {
        if (reviews.get(i).getNo() == review.getNo()) {
          break;
        }
      }

      if (i == reviews.size()) {
        reviews.add(review);
        out.writeUTF("OK");

      } else {
        out.writeUTF("FAIL");
        out.writeUTF("같은 번호의 리뷰가 있습니다.");
      }


    } catch (Exception e) {
      out.writeUTF("FAIL");
      out.writeUTF(e.getMessage());
    }
  }

  private void listReview(ObjectOutputStream out) throws IOException {
    out.writeUTF("OK");
    out.reset();
    out.writeObject(reviews);
  }

  private void deleteMember(ObjectInputStream in, ObjectOutputStream out) throws IOException {
    try {
      int no = in.readInt();

      int index = -1;
      for (int i = 0; i < members.size(); i++) {
        if (members.get(i).getNo() == no) {
          index = i;
          break;
        }
      }

      if (index != -1) {
        members.remove(index);
        out.writeUTF("OK");

      } else {
        out.writeUTF("FAIL");
        out.writeUTF("해당 번호의 회원이 없습니다.");
      }
    } catch (Exception e) {
      out.writeUTF("FAIL");
      out.writeUTF(e.getMessage());
    }
  }

  private void updateMember(ObjectInputStream in, ObjectOutputStream out) throws IOException {
    try {
      Member member = (Member) in.readObject();

      int index = -1;
      for (int i = 0; i < members.size(); i++) {
        if (members.get(i).getNo() == member.getNo()) {
          index = i;
          break;
        }
      }

      if (index != -1) {
        members.set(index, member);
        out.writeUTF("OK");
      } else {
        out.writeUTF("FAIL");
        out.writeUTF("해당 번호의 회원이 없습니다.");
      }

    } catch (Exception e) {
      out.writeUTF("FAIL");
      out.writeUTF(e.getMessage());
    }
  }

  private void detailMember(ObjectInputStream in, ObjectOutputStream out) throws IOException {
    try {
      int no = in.readInt();

      Member member = null;
      for (Member m : members) {
        if (m.getNo() == no) {
          member = m;
          break;
        }
      }

      if (member != null) {
        out.writeUTF("OK");
        out.writeObject(member);

      } else {
        out.writeUTF("FAIL");
        out.writeUTF("해당 번호의 회원이 없습니다.");
      }

    } catch (Exception e) {
      out.writeUTF("FAIL");
      out.writeUTF(e.getMessage());
    }
  }

  private void addMember(ObjectInputStream in, ObjectOutputStream out) throws IOException {
    try {
      Member member = (Member) in.readObject();

      int i = 0;
      for (; i < members.size(); i++) {
        if (members.get(i).getNo() == member.getNo()) {
          break;
        }
      }

      if (i == members.size()) {
        members.add(member);
        out.writeUTF("OK");

      } else {
        out.writeUTF("FAIL");
        out.writeUTF("같은 번호의 회원이 있습니다.");
      }


    } catch (Exception e) {
      out.writeUTF("FAIL");
      out.writeUTF(e.getMessage());
    }
  }

  private void listMember(ObjectOutputStream out) throws IOException {
    out.writeUTF("OK");
    out.reset();
    out.writeObject(members);
  }


  private void deleteInfo(ObjectInputStream in, ObjectOutputStream out) throws IOException {
    try {
      int no = in.readInt();

      int index = -1;
      for (int i = 0; i < infos.size(); i++) {
        if (infos.get(i).getNo() == no) {
          index = i;
          break;
        }
      }

      if (index != -1) {
        infos.remove(index);
        out.writeUTF("OK");

      } else {
        out.writeUTF("FAIL");
        out.writeUTF("해당 번호의 영화 정보가 없습니다.");
      }
    } catch (Exception e) {
      out.writeUTF("FAIL");
      out.writeUTF(e.getMessage());
    }
  }

  private void updateInfo(ObjectInputStream in, ObjectOutputStream out) throws IOException {
    try {
      Info info = (Info) in.readObject();

      int index = -1;
      for (int i = 0; i < infos.size(); i++) {
        if (infos.get(i).getNo() == info.getNo()) {
          index = i;
          break;
        }
      }

      if (index != -1) {
        infos.set(index, info);
        out.writeUTF("OK");
      } else {
        out.writeUTF("FAIL");
        out.writeUTF("해당 번호의 영화 정보가 없습니다.");
      }

    } catch (Exception e) {
      out.writeUTF("FAIL");
      out.writeUTF(e.getMessage());
    }
  }

  private void detailInfo(ObjectInputStream in, ObjectOutputStream out) throws IOException {
    try {
      int no = in.readInt();
      Info info = null;
      for (Info i : infos) {
        if (i.getNo() == no) {
          info = i;
          break;
        }
      }

      if (info != null) {
        out.writeUTF("OK");
        out.writeObject(info);

      } else {
        out.writeUTF("FAIL");
        out.writeUTF("해당 번호의 영화정보가 없습니다.");
      }

    } catch (Exception e) {
      out.writeUTF("FAIL");
      out.writeUTF(e.getMessage());
    }
  }

  private void addInfo(ObjectInputStream in, ObjectOutputStream out) throws IOException {
    try {
      Info info = (Info) in.readObject();

      int i = 0;
      for (; i < infos.size(); i++) {
        if (infos.get(i).getNo() == info.getNo()) {
          break;
        }
      }

      if (i == infos.size()) {
        infos.add(info);
        out.writeUTF("OK");
      } else {
        out.writeUTF("FAIL");
        out.writeUTF("같은 번호의 영화 정보가 있습니다.");
      }
    } catch (Exception e) {
      out.writeUTF("FAIL");
      out.writeUTF(e.getMessage());
    }
  }

  private void listInfo(ObjectOutputStream out) throws IOException {
    out.writeUTF("OK");
    out.reset();
    out.writeObject(infos);
  }

  public static void main(String[] args) {
    System.out.println("서버 수업관리 시스템");
    ServerApp app = new ServerApp();
    app.addApplicationContextListener(new DataLoaderListener());
    app.service();

  }
}
