package service;


public class PatternMatcher {
    public static boolean matchPattern(String inputLine, String pattern) {
        if (pattern.length() == 1) {
            return inputLine.contains(pattern);
        } else if (pattern.equals("\\d")) {
            return inputLine.chars().anyMatch(Character::isDigit);
        } else if (pattern.equals("\\w")) {
          return inputLine.chars().anyMatch(c -> Character.isLetterOrDigit(c) || c == '_');
        } else {
            if(pattern.charAt(0) == '[' && pattern.charAt(pattern.length() - 1) == ']') {
                StringBuilder sb = new StringBuilder();
                for (int i = 1; i < pattern.length() - 1; i++) {
                    sb.append(pattern.charAt(i));
                }
                return inputLine.contentEquals(sb);
            }
            throw new RuntimeException("Unhandled pattern: " + pattern);
        }
    }
}
