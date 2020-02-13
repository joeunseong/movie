package jes.movie.domain;

import java.io.Serializable;
import java.sql.Date;

public class Review implements Serializable {
  private static final long serialVersionUID = 20200203L;
  private int no;
  private String movieTitle;
  private String reviewSummary;
  private Date updateDay;
  private int viewCount;

  public static Review valueOf(String csv) {
    String[] data = csv.split(",");
    Review review = new Review();
    review.setNo(Integer.parseInt(data[0]));
    review.setMovieTitle(data[1]);
    review.setReviewSummary(data[2]);
    review.setUpdateDay(Date.valueOf(data[3]));
    review.setViewCount(Integer.parseInt(data[4]));
    return review;
  }

  public String toCsvString() {
    return String.format("%d,%s,%s,%s,%d", this.getNo(), this.getMovieTitle(),
        this.getReviewSummary(), this.getUpdateDay(), this.getViewCount());
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Review other = (Review) obj;
    if (movieTitle == null) {
      if (other.movieTitle != null)
        return false;
    } else if (!movieTitle.equals(other.movieTitle))
      return false;
    if (no != other.no)
      return false;
    if (reviewSummary == null) {
      if (other.reviewSummary != null)
        return false;
    } else if (!reviewSummary.equals(other.reviewSummary))
      return false;
    if (viewCount != other.viewCount)
      return false;
    return true;
  }

  public int getNo() {
    return no;
  }

  public void setNo(int no) {
    this.no = no;
  }

  public String getMovieTitle() {
    return movieTitle;
  }

  public void setMovieTitle(String movieTitle) {
    this.movieTitle = movieTitle;
  }

  public String getReviewSummary() {
    return reviewSummary;
  }

  public void setReviewSummary(String reviewSummary) {
    this.reviewSummary = reviewSummary;
  }

  public Date getUpdateDay() {
    return updateDay;
  }

  public void setUpdateDay(Date updateDay) {
    this.updateDay = updateDay;
  }

  public int getViewCount() {
    return viewCount;
  }

  public void setViewCount(int viewCount) {
    this.viewCount = viewCount;
  }


}
