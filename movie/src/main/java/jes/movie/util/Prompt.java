package jes.movie.util;

import java.sql.Date;
import java.util.Scanner;

public class Prompt {
  Scanner keyboard;

  public Prompt(Scanner keyboard) {
    this.keyboard = keyboard;
  }

  public String inputString(String label) {
    System.out.print(label);
    return keyboard.nextLine();
  }

  public String inputString(String label, String defaultValue) {
    System.out.print(label);
    String value = keyboard.nextLine();
    if (value.length() == 0) {
      return defaultValue;
    }
    return value;
  }

  public int inputInt(String label) {
    System.out.print(label);
    return Integer.parseInt(keyboard.nextLine());
  }

  public int inputInt(String label, int defaultValue) {
    System.out.print(label);
    String value = keyboard.nextLine();
    if (value.length() == 0) {
      return defaultValue;
    }
    return Integer.parseInt(value);
  }

  public Date inputDate(String label) {
    System.out.print(label);
    return Date.valueOf(keyboard.nextLine());
  }

  public Date inputDate(String label, Date defaultValue) {
    System.out.print(label);
    String value = keyboard.nextLine();
    if (value.length() == 0) {
      return defaultValue;
    }
    return Date.valueOf(value);
  }
}
