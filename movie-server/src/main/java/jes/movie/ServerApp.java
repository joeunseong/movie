package jes.movie;

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

  public void service() {
    notifyApplicationInitailized();
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

  @SuppressWarnings("unchecked")
  int processRequest(Socket clientSocket) {
    try (Socket socket = clientSocket;
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream())) {
      System.out.println("통신을 위한 입출력 스트림을 준비하였음!");

      while (true) {
        String request = in.readUTF();
        System.out.println("클라이언트가 보낸 메시지를 수신하였음!");

        if (request.equals("quit")) {
          out.writeUTF("OK");
          out.flush();
          break;
        }

        if (request.equals("/server/stop")) {
          out.writeUTF("OK");
          out.flush();
          return 9;
        }

        List<Info> infos = (List<Info>) context.get("infoList");
        List<Member> members = (List<Member>) context.get("memberList");
        List<Review> reviews = (List<Review>) context.get("reviewList");

        if (request.equals("/info/list")) {
          out.writeUTF("OK");
          out.reset();
          out.writeObject(infos);

        } else if (request.equals("/info/add")) {
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
        } else if (request.equals("/info/detail")) {
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
        } else if (request.equals("/info/update")) {
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
        } else if (request.equals("/info/delete")) {
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

        } else if (request.equals("/member/list")) {
          out.writeUTF("OK");
          out.reset();
          out.writeObject(members);

        } else if (request.equals("/member/add")) {
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
        } else if (request.equals("/member/detail")) {
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
        } else if (request.equals("/member/update")) {
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
        } else if (request.equals("/member/delete")) {
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

        } else if (request.equals("/review/list")) {
          out.writeUTF("OK");
          out.reset();
          out.writeObject(reviews);

        } else if (request.equals("/review/add")) {
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
        } else if (request.equals("/review/detail")) {
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
        } else if (request.equals("/review/update")) {
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
        } else if (request.equals("/review/delete")) {
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

        } else {
          out.writeUTF("FAIL");
          out.writeUTF("요청한 명령을 처리할 수 없습니다.");
        }
        out.flush();
      }

      System.out.println("클라이언트로 메시지를 전송하였음!");

      return 0;

    } catch (Exception e) {
      System.out.println("예외 발생:");
      e.printStackTrace();
      return -1;
    }
  }

  public static void main(String[] args) {
    System.out.println("서버 수업관리 시스템");
    ServerApp app = new ServerApp();
    app.addApplicationContextListener(new DataLoaderListener());
    app.service();

  }
}
