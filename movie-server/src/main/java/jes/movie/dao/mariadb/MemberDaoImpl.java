package jes.movie.dao.mariadb;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import jes.movie.dao.MemberDao;
import jes.movie.domain.Member;

public class MemberDaoImpl implements MemberDao {
  Connection con;

  public MemberDaoImpl(Connection con) {
    this.con = con;
  }

  @Override
  public int insert(Member member) throws Exception {
    try (Statement stmt = con.createStatement()) {
      int result = stmt.executeUpdate(//
          "insert into movie_member(name, email, pwd, tel, photo) values('" + member.getName()
              + "', '" + member.getEmail() + "','" + member.getPassword() + "'," + "'"
              + member.getTel() + "', '" + member.getPhoto() + "')");

      return result;
    }
  }

  @Override
  public List<Member> findAll() throws Exception {
    try (Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(
            "select member_id, name, email, pwd, cdt, photo, tel from movie_member")) {

      ArrayList<Member> list = new ArrayList<>();

      while (rs.next()) {
        Member member = new Member();
        member.setNo(rs.getInt("member_id"));
        member.setName(rs.getString("name"));
        member.setEmail(rs.getString("email"));
        member.setPassword(rs.getString("pwd"));
        member.setRegisterDate(rs.getDate("cdt"));
        member.setPhoto(rs.getString("photo"));
        member.setTel(rs.getString("tel"));
        list.add(member);
      }
      return list;
    }
  }

  @Override
  public Member findByNo(int no) throws Exception {
    try (Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from movie_member where member_id=" + no)) {

      if (rs.next()) {
        Member member = new Member();
        member.setNo(rs.getInt("member_id"));
        member.setName(rs.getString("name"));
        member.setEmail(rs.getString("email"));
        member.setPassword(rs.getString("pwd"));
        member.setRegisterDate(rs.getDate("cdt"));
        member.setTel(rs.getString("tel"));
        member.setPhoto(rs.getString("photo"));
        return member;
      } else {
        return null;
      }
    }

  }

  @Override
  public int update(Member member) throws Exception {
    try (Statement stmt = con.createStatement()) {
      int result = stmt.executeUpdate(//
          "update movie_member set name= '" + member.getName() + "', email='" + member.getEmail()
              + "', pwd='" + member.getPassword() + "', tel='" + member.getTel() + "', photo='"
              + member.getPhoto() + "' where member_id=" + member.getNo());

      return result;
    }
  }

  @Override
  public int delete(int no) throws Exception {
    try (Statement stmt = con.createStatement()) {
      int result = stmt.executeUpdate("delete from movie_member where member_id=" + no);
      return result;
    }
  }
}
