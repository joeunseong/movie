package jes.movie.dao.mariadb;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import jes.movie.dao.InfoDao;
import jes.movie.domain.Info;

public class InfoDaoImpl implements InfoDao {

  Connection con;

  public InfoDaoImpl(Connection con) {
    this.con = con;
  }

  @Override
  public int insert(Info info) throws Exception {
    try (Statement stmt = con.createStatement()) {

      int result = stmt.executeUpdate(
          "insert into movie_info(mv_titl, gr, conts, dct, act, sort, opn_day, rnt)" + "values('"
              + info.getMovieTitle() + "', '" + info.getGenre() + "','" + info.getSummary() + "',"
              + "'" + info.getDirector() + "', '" + info.getActor() + "', '" + info.getKmrb() + "',"
              + "'" + info.getOpenDate() + "', '" + info.getRunningTime() + "')");

      return result;
    }
  }

  @Override
  public List<Info> findAll() throws Exception {

    try (Statement stmt = con.createStatement();

        ResultSet rs = stmt.executeQuery(
            "select info_id, mv_titl, gr, conts, dct, act, sort, opn_day, rnt from movie_info")) {

      ArrayList<Info> list = new ArrayList<>();

      while (rs.next()) {
        Info info = new Info();
        info.setNo(rs.getInt("info_id"));
        info.setMovieTitle(rs.getString("mv_titl"));
        info.setGenre(rs.getString("gr"));
        info.setSummary(rs.getString("conts"));
        info.setDirector(rs.getString("dct"));
        info.setActor(rs.getString("act"));
        info.setKmrb(rs.getString("sort"));
        info.setOpenDate(rs.getDate("opn_day"));
        info.setRunningTime(rs.getInt("rnt"));
        list.add(info);
      }
      return list;
    }
  }

  @Override
  public Info findByNo(int no) throws Exception {

    try (Statement stmt = con.createStatement();

        ResultSet rs = stmt.executeQuery("select * from movie_info where info_id=" + no)) {

      if (rs.next()) {
        Info info = new Info();
        info.setNo(rs.getInt("info_id"));
        info.setMovieTitle(rs.getString("mv_titl"));
        info.setGenre(rs.getString("gr"));
        info.setSummary(rs.getString("conts"));
        info.setDirector(rs.getString("dct"));
        info.setActor(rs.getString("act"));
        info.setKmrb(rs.getString("sort"));
        info.setOpenDate(rs.getDate("opn_day"));
        info.setRunningTime(rs.getInt("rnt"));
        return info;
      } else {
        return null;
      }
    }

  }

  @Override
  public int update(Info info) throws Exception {
    try (Statement stmt = con.createStatement()) {
      int result = stmt.executeUpdate("update movie_info set mv_titl= '" + info.getMovieTitle()
          + "', gr='" + info.getGenre() + "', conts='" + info.getSummary() + "', dct='"
          + info.getDirector() + "', act='" + info.getActor() + "', sort='" + info.getKmrb() + "', "
          + "opn_day='" + info.getOpenDate() + "', rnt='" + info.getRunningTime() + "' "
          + "where info_id=" + info.getNo());

      return result;
    }
  }

  @Override
  public int delete(int no) throws Exception {
    try (Statement stmt = con.createStatement()) {

      int result = stmt.executeUpdate("delete from movie_info where info_id=" + no);
      return result;
    }
  }
}
