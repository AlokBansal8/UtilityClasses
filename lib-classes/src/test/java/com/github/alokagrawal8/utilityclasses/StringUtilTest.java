package com.github.alokagrawal8.utilityclasses;

import org.junit.Test;

import static com.github.alokagrawal8.utilityclasses.StringUtil.NULL_ARE_EQUAL;
import static com.github.alokagrawal8.utilityclasses.StringUtil.NULL_NOT_EQUAL;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Unit tests for {@link StringUtil} class.
 */
public class StringUtilTest {

  private final String s1 = null;
  private final String s2 = "";
  private final String s3 = "a";
  private final String s4 = "abcd";
  private final String s5 = "abcdefghij";

  @Test public void isBlankTest() throws Exception {
    assertTrue(StringUtil.isBlank(s1));
    assertTrue(StringUtil.isBlank(s2));
    assertFalse(StringUtil.isBlank(s3));
    assertFalse(StringUtil.isBlank(s4));
    assertFalse(StringUtil.isBlank(s5));
  }

  @Test public void valueOrDefaultTest() throws Exception {
    assertThat(StringUtil.valueOrDefault(s1, s5), is(s5));
    assertThat(StringUtil.valueOrDefault(s2, s5), is(s5));
    assertThat(StringUtil.valueOrDefault(s3, s5), is(s3));
    assertThat(StringUtil.valueOrDefault(s4, s5), is(s4));
    assertThat(StringUtil.valueOrDefault(s5, s3), is(s5));
  }

  @Test public void truncateAtTest() throws Exception {
    assertThat(StringUtil.truncateAt(s2, 1), is(s2));
    assertThat(StringUtil.truncateAt(s3, 1), is(s3));
    assertThat(StringUtil.truncateAt(s3, 3), is(s3));
    assertThat(StringUtil.truncateAt(s4, 1), is(s3));
    assertThat(StringUtil.truncateAt(s5, 1), is(s3));
    assertThat(StringUtil.truncateAt(s5, 4), is(s4));
  }

  @Test public void areEqualTest() throws Exception {
    assertTrue(StringUtil.areEqual(s1, s1, NULL_ARE_EQUAL));
    assertFalse(StringUtil.areEqual(s1, s1, NULL_NOT_EQUAL));
    assertFalse(StringUtil.areEqual(s1, s2, NULL_ARE_EQUAL));
    assertTrue(StringUtil.areEqual(s2, s2, NULL_ARE_EQUAL));
    assertTrue(StringUtil.areEqual(s2, s2, NULL_NOT_EQUAL));
    assertTrue(StringUtil.areEqual(s3, StringUtil.truncateAt(s4, 1), NULL_ARE_EQUAL));
    assertTrue(StringUtil.areEqual(s3, StringUtil.truncateAt(s4, 1), NULL_NOT_EQUAL));
  }
}
