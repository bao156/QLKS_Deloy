package qlks_hdv.util;

import java.util.Date;

public class DateUtils {

  public static Long now() {
    return currentDate().getTime();
  }

  public static Date currentDate() {
    return new Date();
  }
}
