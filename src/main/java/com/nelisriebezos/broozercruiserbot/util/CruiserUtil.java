/* Copyright (c) 2016 W.T.J. Riezebos
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.nelisriebezos.broozercruiserbot.util;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CruiserUtil {
  private static final int DEFAULT_ADDITIONAL_BUFFERSIZE = 10;

  public static String encodeUrl(String url) {
    try {
      return URLEncoder.encode(url, "UTF-8");
    } catch (UnsupportedEncodingException e) {
    }
    return url;
  }

  public static String getCanonicalPath(String path) {
    if (path == null)
      return null;
    if (path.indexOf('/') == -1)
      return path;

    try {
      URI uri = new URI(null, null, null, 0, normalSlashes(path), null, null);
      String canonical = uri.normalize().getPath();
      if (canonical == null)
        return path;
      return canonical;
    } catch (URISyntaxException e) {
      throw new IllegalArgumentException(e);
    }
  }

  public static String getCanonicalPath(File file) {
    if (file == null)
      return null;
    return getCanonicalPath(file.getAbsolutePath());
  }

  public static String tidyRelativePath(String value) {
    if (value != null) {
      value = normalSlashes(value);
      if (value.startsWith("/"))
        value = value.substring(1);
    }
    return value;
  }

  public static String normalSlashes(String filespec) {
    if (filespec == null)
      return null;
    return filespec.replaceAll("\\\\", "/");
  }

  /**
   * Returns only the name part of a file specification (no path nor extension)
   *
   * @param imagePath
   * @return
   */
  public static String getNameOnly(String imagePath) {
    if (imagePath == null)
      return null;
    String name = getFileName(imagePath);
    int idx = name.lastIndexOf(".");
    if (idx != -1)
      name = name.substring(0, idx);
    return name;
  }

  /**
   * Returns the filename part of a path spec (including extension)
   *
   * @param filespec
   * @return
   */
  public static String getFileName(String filespec) {
    if (filespec == null)
      return null;
    String fileName = normalSlashes(filespec);
    int idx = fileName.lastIndexOf("/");
    if (idx != -1)
      fileName = fileName.substring(idx + 1);
    return fileName;
  }

  public static char[] wrapWithNewLines(char[] source) {
    if (source == null)
      return null;
    char[] src = new char[source.length + 2];
    System.arraycopy(source, 0, src, 1, source.length);
    src[0] = '\n';
    src[source.length + 1] = '\n';
    return src;
  }

  public static String getFolder(String filespec) {
    String path = normalSlashes(filespec);
    if (path != null) {
      int idx = path.lastIndexOf("/");
      if (idx != -1)
        return path.substring(0, idx);
    }
    return path;
  }

  public static String stripSuffix(String value, String suffix) {
    if (value != null && suffix != null && value.endsWith(suffix))
      value = value.substring(0, value.length() - suffix.length());
    return value;
  }

  public static String encodeBookmark(String text, boolean toLowercase) {
    if (text == null)
      return null;
    String bookmark = text.replaceAll("[^\\w\\_]", "");
    if (toLowercase)
      bookmark = bookmark.toLowerCase();
    return bookmark;
  }

  public static String replaceKeywords(String messageTemplate, Map<String, Object> args) {
    if (messageTemplate == null)
      return null;
    for (String expression : extractKeyswords(messageTemplate))
      messageTemplate = replaceKeyword(messageTemplate, args, expression);
    return messageTemplate;
  }

  public static String replaceKeyword(String messageTemplate, Map<String, Object> args, String key) {
    Object value = args.get(key);

    if (value == null)
      value = "";

    return messageTemplate.replaceAll("\\$\\{" + regExpescape(key) + "\\}", regExpescape(String.valueOf(value)));
  }

  public static String regExpescape(String value) {
    StringBuffer sb = new StringBuffer(value.length() + DEFAULT_ADDITIONAL_BUFFERSIZE);
    for (int i = 0; i < value.length(); i++)
      sb.append(regExpescape(value.charAt(i)));

    return sb.toString();
  }

  public static String regExpescape(char c) {
    switch (c) {
    case ',':
    case '*':
    case '+':
    case '?':
    case '{':
    case '}':
    case '$':
    case '.':
    case '^':
    case '(':
    case '[':
    case ']':
    case '|':
    case ')':
      return "\\" + c;
    default:
      return String.valueOf(c);
    }
  }

  public static List<String> extractKeyswords(String messageTemplate) {
    List<String> result = new ArrayList<String>();
    if (messageTemplate != null) {
      Pattern pattern = Pattern.compile("\\$\\{(.*?)\\}");
      Matcher matcher = pattern.matcher(messageTemplate);
      int idx = 0;
      while (matcher.find(idx)) {
        result.add(matcher.group(1));
        idx = matcher.end(1);
      }
    }
    return result;
  }

  public static Pattern specAsRegExp(String spec) {
    spec = fileSpec2regExp(spec);
    return Pattern.compile(spec, Pattern.CASE_INSENSITIVE);
  }

  public static String fileSpec2regExp(String spec) {
    spec = spec.replaceAll("([\\W&&[^\\*]])", "\\\\$1");
    spec = spec.replaceAll("\\*", "(\\.\\*\\?)");
    return spec;
  }

  public static List<String> tokenize(String value) {
    List<String> result = new ArrayList<String>();

    if (value != null) {
      for (String key : value.split("\\,")) {
        if (key.trim().length() > 0)
          result.add(key.trim());
      }
    }

    return result;
  }

  public static <T extends Comparable<T>> List<T> sort(List<T> list) {
    List<T> newList = new ArrayList<T>();
    newList.addAll(list);
    Collections.sort(newList);
    return newList;
  }

  /**
   * Strips any prefixing number. Result will be trimmed from whitespace as well
   *
   * @param title
   * @return
   */
  public static String stripNumericPrefix(String title) {
    if (title == null)
      return null;

    while (title.length() > 0 && Character.isDigit(title.charAt(0)))
      title = title.substring(1);
    return title.trim();
  }

  /**
   * When prefix not found; will return the entire path
   *
   * @param value
   * @param prefix
   * @return
   */
  public static String getPartBeforeFirst(String value, String prefix) {
    if (value != null) {
      int idx = value.indexOf(prefix);
      if (idx != -1)
        value = value.substring(0, idx);
    }
    return value;
  }

  /**
   * When prefix not found; will return empty string
   *
   * @param value
   * @param prefix
   * @return
   */
  public static String getPartAfterFirst(String value, String prefix) {
    if (value != null) {
      int idx = value.indexOf(prefix);
      if (idx != -1)
        value = value.substring(idx + prefix.length());
      else
        value = "";
    }
    return value;
  }

  public static String getPartAfterLast(String value, String prefix) {
    if (value != null) {
      int idx = value.lastIndexOf(prefix);
      if (idx != -1)
        value = value.substring(idx + prefix.length());
    }
    return value;
  }

  public static String getPartBeforeLast(String value, String prefix) {
    if (value != null) {
      int idx = value.lastIndexOf(prefix);
      if (idx != -1)
        value = value.substring(0, idx);
    }
    return value;
  }

  public static String stripPrefix(String value, String prefix) {
    if (value != null) {
      if (value.startsWith(prefix))
        value = value.substring(prefix.length());
    }
    return value;
  }

  public static String getExtension(String path) {
    if (path == null)
      return null;
    int idx = path.lastIndexOf('.');
    if (idx != -1) {
      return path.substring(idx + 1);
    }
    return null;
  }

  public static String prefix(String value, String prefix) {
    if (value == null)
      return prefix;
    if (!value.startsWith(prefix))
      value = prefix + value;
    return value;
  }

  public static String suffix(String value, String prefix) {
    if (value == null)
      return prefix;
    if (!value.endsWith(prefix))
      value += prefix;
    return value;
  }

  public static String absoluteFolder(String folder) {
    return suffix(prefix(normalSlashes(folder), "/"), "/");
  }

  public static String readInputStream(InputStream is) throws IOException {
    if (is == null)
      return null;
    StringBuilder sb = new StringBuilder();
    BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
    String line = br.readLine();
    while (line != null) {
      sb.append(line);
      sb.append("\n");
      line = br.readLine();
    }
    is.close();
    return sb.toString();
  }

  public static String getStack() {
    ByteArrayOutputStream bos = new ByteArrayOutputStream();

    PrintWriter pw = new PrintWriter(bos);
    new Exception().printStackTrace(pw);
    pw.close();
    return bos.toString();
  }

  /**
   * Creates a map of key/value pairs (flags) based on an array of arguments (-key [value] format, value is optional)
   *
   * @param args
   * @return
   */
  public static Map<String, String> getArgumentsMap(String[] args) {
    Map<String, String> arguments = new HashMap<String, String>();

    for (int i = 0; i < args.length; i++) {
      String key = args[i];
      if (key.startsWith("-")) {
        String value = i + 1 < args.length ? args[i + 1] : null;

        if (value != null && value.startsWith("-")) {
          value = null; // A parameter without a value (a flag) detected
        } else
          i++; // Make sure we do not interpret the value as a key now
        arguments.put(key.toLowerCase().trim().substring(1), value);
      }
    }
    return arguments;
  }

  /**
   * Creates a list of the arguments that are not flags
   *
   * @param args
   * @return
   */
  public static List<String> getArgumentsList(String[] args) {
    List<String> arguments = new ArrayList<String>();

    for (int i = 0; i < args.length; i++) {
      String key = args[i];
      if (key.startsWith("-")) {
        String value = i + 1 < args.length ? args[i + 1] : null;

        if (value != null && value.startsWith("-")) {
          value = null; // A parameter without a value (a flag) detected
        } else
          i++; // Make sure we do not interpret the value as a key now
      } else {
        arguments.add(key);
      }
    }
    return arguments;
  }

  public static String replaceWord(String value, String word, String replacement, String separators) {
    StringBuffer sepSpec;
    if (separators == null || separators.length() == 0)
      throw new IllegalArgumentException("No separators specified");
    else {
      sepSpec = new StringBuffer("[");
      for (int i = 0; i < separators.length(); i++)
        sepSpec.append((i > 0 ? "," : "") + escape(separators.charAt(i)));
      sepSpec.append("]");
    }

    int idx = 0;
    String regex = sepSpec + escape(word) + sepSpec;
    Pattern p = Pattern.compile(regex, Pattern.MULTILINE);

    Matcher matcher = p.matcher(value);
    while (idx < value.length() && matcher.find(idx)) {
      int start = matcher.start() + 1;
      int end = matcher.end() - 1;
      value = value.substring(0, start) + replacement + value.substring(end);
      idx = matcher.start() + replacement.length() + 2;
      matcher = p.matcher(value);
    }
    return value;
  }

  public static String escape(String value) {
    StringBuffer sb = new StringBuffer(value.length() + DEFAULT_ADDITIONAL_BUFFERSIZE);
    for (int i = 0; i < value.length(); i++)
      sb.append(escape(value.charAt(i)));

    return sb.toString();
  }

  public static String escape(char c) {
    switch (c) {
    case '\t':
      return "\\t";
    case '\r':
      return "\\r";
    case '\n':
      return "\\n";
    case ',':
    case '*':
    case '+':
    case '?':
    case '{':
    case '}':
    case '$':
    case '.':
    case '^':
    case '(':
    case '[':
    case ']':
    case '|':
    case ')':
      return "\\" + c;
    default:
      return String.valueOf(c);
    }
  }

  public static String appendToLength(String value, String suffix, int length) {
    if (value == null)
      value = "";
    while (value.length() < length)
      value = value + suffix;
    return value;
  }

  @SuppressWarnings({"rawtypes", "unchecked"})
  public static int safeCompare(Comparable c1, Comparable c2) {
    if (c1 == null && c2 != null)
      return -1;
    if (c1 != null && c2 == null)
      return 1;
    if (c1 == null && c2 == null)
      return 0;

    return c1.compareTo(c2);
  }

  public static String formatUnix(long unix) {

    Date date = new Date(unix);
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    return sdf.format(date);

  }

  public static String msToTime(long windowSizeMs) {
    long seconds = windowSizeMs / 1000L;
    long minutes = seconds / 60;
    seconds -= minutes * 60;
    long hours = minutes / 60;
    minutes -= hours * 60;

    return String.format("%02d:%02d:%02d", hours, minutes, seconds);
  }
}
