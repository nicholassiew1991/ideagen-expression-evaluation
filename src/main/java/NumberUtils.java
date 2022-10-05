public class NumberUtils {

  public static boolean isNumeric(String str) {
    try {
      Double.parseDouble(str);
      return true;
    }
    catch (Exception e) {
      return false;
    }
  }

  public static double round(String value, int place) {
    String format = "%." + place + "f";
    String v = String.format(format, Double.parseDouble(value));
    return Double.parseDouble(v);
  }
}
