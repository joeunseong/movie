package jes.movie.dao.mariadb;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import jes.movie.dao.ReviewDao;
import jes.movie.domain.Review;

public class ReviewDaoImpl implements ReviewDao {
  Connection con;

  public ReviewDaoImpl(Connection con) {
    this.con = con;
  }

  @Override
  public int insert(Review review) throws Exception {
    try (Statement stmt = con.createStatement()) {
      int result = stmt.executeUpdate(//
          "insert into movie_review(mv_titl, conts)" + "values('" + review.getMovieTitle() + "', '"
              + review.getReviewSummary() + "')");

      return result;
    }
  }

  @Override
  public List<Review> findAll() throws Exception {
    try (Statement stmt = con.createStatement();
        ResultSet rs =
            stmt.executeQuery("select review_id, mv_titl, conts, cdt, vw_cnt from movie_review")) {

      ArrayList<Review> list = new ArrayList<>();

      while (rs.next()) {
        Review review = new Review();
        review.setNo(rs.getInt("review_id"));
        review.setMovieTitle(rs.getString("mv_titl"));
        review.setReviewSummary(rs.getString("conts"));
        review.setUpdateDay(rs.getDate("cdt"));
        review.setViewCount(rs.getInt("vw_cnt"));
        list.add(review);
      }
      return list;
    }
  }

  @Override
  public Review findByNo(int no) throws Exception {
    try (Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from movie_review where review_id=" + no)) {

      if (rs.next()) {
        Review review = new Review();
        review.setNo(rs.getInt("review_id"));
        review.setMovieTitle(rs.getString("mv_titl"));
        review.setReviewSummary(rs.getString("conts"));
        review.setUpdateDay(rs.getDate("cdt"));
        review.setViewCount(rs.getInt("vw_cnt"));
        return review;
      } else {
        return null;
      }
    }

  }

  @Override
  public int update(Review review) throws Exception {
    try (Statement stmt = con.createStatement()) {
      int result = stmt.executeUpdate(//
          "update movie_review set mv_titl= '" + review.getMovieTitle() + "', " + "conts='"
              + review.getReviewSummary() + "' where review_id=" + review.getNo());

      return result;
    }
  }

  @Override
  public int delete(int no) throws Exception {
    try (Statement stmt = con.createStatement()) {
      int result = stmt.executeUpdate("delete from movie_review where review_id=" + no);
      return result;
    }
  }
}
