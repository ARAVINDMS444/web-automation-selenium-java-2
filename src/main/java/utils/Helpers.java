package utils;

import java.util.List;

public class Helpers {

  /** Checks if a list of numbers is sorted in ascending order. */
  public static <T extends Number & Comparable<T>> boolean isAscending(List<T> list) {
    for (int i = 0; i < list.size() - 1; i++) {
      if (list.get(i).compareTo(list.get(i + 1)) > 0) {
        return false;
      }
    }
    return true;
  }

  /** Checks if a list of numbers is sorted in descending order. */
  public static <T extends Number & Comparable<T>> boolean isDescending(List<T> list) {
    for (int i = 0; i < list.size() - 1; i++) {
      if (list.get(i).compareTo(list.get(i + 1)) < 0) {
        return false;
      }
    }
    return true;
  }

  /** Checks if a list of strings is sorted in ascending (A-Z) order. */
  public static boolean isAscendingStrings(List<String> list) {
    for (int i = 0; i < list.size() - 1; i++) {
      if (list.get(i).compareToIgnoreCase(list.get(i + 1)) > 0) {
        return false;
      }
    }
    return true;
  }

  /** Checks if a list of strings is sorted in descending (Z-A) order. */
  public static boolean isDescendingStrings(List<String> list) {
    for (int i = 0; i < list.size() - 1; i++) {
      if (list.get(i).compareToIgnoreCase(list.get(i + 1)) < 0) {
        return false;
      }
    }
    return true;
  }
}
