package jes.movie;

import java.util.Map;
import jes.movie.context.ApplicationContextListener;
import jes.movie.dao.InfoObjectFileDao;
import jes.movie.dao.MemberObjectFileDao;
import jes.movie.dao.ReviewObjectFileDao;

public class DataLoaderListener implements ApplicationContextListener {

  @Override
  public void contextInitialized(Map<String, Object> context) {
    System.out.println("데이터를 로딩합니다.");
    
    InfoObjectFileDao infoDao = new InfoObjectFileDao("./info.ser2");
    MemberObjectFileDao memberDao = new MemberObjectFileDao("./member.ser2");
    ReviewObjectFileDao reviewDao = new ReviewObjectFileDao("./review.ser2");

    context.put("infoDao", infoDao);
    context.put("memberDao", memberDao);
    context.put("reviewDao", reviewDao);
  }

  @Override
  public void contextDestroyed(Map<String, Object> context) {
    System.out.println("데이터를 저장합니다.");
  }

}
