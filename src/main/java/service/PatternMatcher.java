package service;


public class PatternMatcher {
    public static boolean matchPattern(String inputLine, String pattern) {
        if (pattern.length() == 1) {
            return inputLine.contains(pattern);
        } else if (pattern.equals("\\d")) {
            return inputLine.chars().anyMatch(Character::isDigit);
        } else if (pattern.equals("\\w")) {
          return inputLine.chars().anyMatch(c -> Character.isLetterOrDigit(c) || c == '_');
        } else if (pattern.startsWith("[") && pattern.endsWith("]")) {
            String sub = pattern.substring(1, pattern.length() - 1);
            if (sub.startsWith("^")) {
                String newSub = sub.substring(1);
                return inputLine.chars().allMatch(c -> newSub.indexOf(c) == -1);
            }
            return inputLine.chars().anyMatch(c -> sub.indexOf(c) != -1);
        } else {
            throw new RuntimeException("Unhandled pattern: " + pattern);
        }
    }
}
