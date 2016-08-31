package com.github.alokagrawal8.utilityclasses;

import android.support.annotation.CheckResult;
import android.support.annotation.IntDef;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@SuppressWarnings({ "WeakerAccess", "unused" }) public final class StringUtil {

  public static final byte NULL_ARE_EQUAL = 54;
  public static final byte NULL_NOT_EQUAL = 55;

  private StringUtil() {
    throw new AssertionError("No instance please");
  }

  /**
   * Tells if a {@link CharSequence} is blank. Treats null and zero length as blank.
   *
   * @param cs the text which needs to be checked for blankness.
   * @return whether argument is blank or not.
   */
  @CheckResult public static boolean isBlank(@Nullable final CharSequence cs) {
    return (cs == null || cs.toString().trim().length() == 0);
  }

  /**
   * Checks if the first argument is blank then return the second argument else return the first
   * argument. Treats null and zero length as blank.
   *
   * @param string the text which needs to be checked for blankness.
   * @param defaultString the text which will be returned in case first argument is blank.
   * @return first argument if it is not blank else the second argument.
   */
  @CheckResult @NonNull public static String valueOrDefault(@Nullable final String string,
      @NonNull final String defaultString) {
    return isBlank(string) ? defaultString : string;
  }

  /**
   * Truncates the given string and generates a new string of given length, if length argument is
   * greater than the length of string argument whole string is returned.
   *
   * @param string string which needs to be truncated.
   * @param length the length of the truncated string.
   * @return truncated string.
   */
  @CheckResult @NonNull public static String truncateAt(@NonNull final String string,
      @IntRange(from = 1) final int length) {
    return string.length() > length ? string.substring(0, length) : string;
  }

  /**
   * Compares two {@link String} objects and tell if they are equal or not.
   *
   * @param s1 first {@link String} object.
   * @param s2 second {@link String} object.
   * @param isNullEqual specifies whether to treat nulls as equal or not, can be either of {@link
   * #NULL_ARE_EQUAL}, and {@link #NULL_NOT_EQUAL}.
   * @return whether two parameters are equal.
   */
  @CheckResult public static boolean areEqual(@Nullable final String s1, @Nullable final String s2,
      @AreNullEquals final int isNullEqual) {
    if (NULL_ARE_EQUAL == isNullEqual) {
      if (s1 == null) return (s2 == null);
      return s2 != null && s1.equals(s2);
    } else {
      return !(s1 == null || s2 == null) && s1.equals(s2);
    }
  }

  @Retention(RetentionPolicy.SOURCE) @Target(ElementType.PARAMETER)
  @IntDef(value = { NULL_ARE_EQUAL, NULL_NOT_EQUAL }, flag = true)
  private @interface AreNullEquals {

  }
}
