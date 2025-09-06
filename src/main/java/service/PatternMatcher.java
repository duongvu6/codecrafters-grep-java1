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
            throw new RuntimeException("Unhandled pattern: " + pattern);
        }
    }
}
