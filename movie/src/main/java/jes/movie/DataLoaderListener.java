package jes.movie;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import jes.movie.context.ApplicationContextListener;
import jes.movie.domain.Info;
import jes.movie.domain.Member;
import jes.movie.domain.Review;

public class DataLoaderListener implements ApplicationContextListener {

  List<Review> reviewList = new ArrayList<>();
  List<Info> infoList = new ArrayList<>();
  List<Member> memberList = new ArrayList<>();

  @Override
  public void contextInitialized(Map<String, Object> context) {
    System.out.println("데이터를 로딩합니다.");
    loadReviewData();
    loadMemberData();
    loadInfoData();

    context.put("reviewList", reviewList);
    context.put("infoList", infoList);
    context.put("memberList", memberList);
  }

  @Override
  public void contextDestroyed(Map<String, Object> context) {
    System.out.println("데이터를 저장합니다.");
    saveReviewData();
    saveMemberData();
    saveInfoData();
  }

  @SuppressWarnings("unchecked")
  private void loadReviewData() {
    File file = new File("./review.ser2");
    try (ObjectInputStream in =
        new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)))) {

      reviewList = (List<Review>) in.readObject();

      System.out.printf("총 %d 개의 리뷰 데이터를 로딩했습니다.\n", reviewList.size());
    } catch (Exception e) {
      System.out.println("파일 읽기 중 오류 발생! - " + e.getMessage());
    }
  }

  @SuppressWarnings("unchecked")
  private void loadMemberData() {
    File file = new File("./member.ser2");
    try (ObjectInputStream in =
        new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)))) {
      memberList = (List<Member>) in.readObject();
      System.out.printf("총 %d 개의 멤버 데이터를 로딩했습니다.\n", memberList.size());
    } catch (Exception e) {
      System.out.println("파일 읽기 중 오류 발생! - " + e.getMessage());
    }
  }

  @SuppressWarnings("unchecked")
  private void loadInfoData() {
    File file = new File("./info.ser2");
    try (ObjectInputStream in =
        new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)))) {

      infoList = (List<Info>) in.readObject();
      System.out.printf("총 %d 개의 영화 정보 데이터를 로딩했습니다.\n", infoList.size());
    } catch (Exception e) {
      System.out.println("파일 읽기 중 오류 발생! - " + e.getMessage());
    }
  }


  private void saveReviewData() {
    File file = new File("./review.ser2");
    try (ObjectOutputStream out =
        new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)))) {
      out.writeObject(reviewList);
      System.out.printf("총 %d 개의 리뷰 데이터를 저장했습니다.\n", reviewList.size());
    } catch (IOException e) {
      System.out.println("파일 쓰기 중 오류 발생! - " + e.getMessage());
    }
  }

  private void saveMemberData() {
    File file = new File("./member.ser2");
    try (ObjectOutputStream out =
        new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)))) {

      out.writeObject(memberList);
      System.out.printf("총 %d 개의 멤버 데이터를 저장했습니다.\n", memberList.size());
    } catch (IOException e) {
      System.out.println("파일 쓰기 중 오류 발생! - " + e.getMessage());
    }
  }

  private void saveInfoData() {
    File file = new File("./info.ser2");
    try (ObjectOutputStream out =
        new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)))) {
      out.writeObject(infoList);
      System.out.printf("총 %d 개의 영화 정보 데이터를 저장했습니다.\n", infoList.size());
    } catch (IOException e) {
      System.out.println("파일 쓰기 중 오류 발생! - " + e.getMessage());
    }
  }
}
