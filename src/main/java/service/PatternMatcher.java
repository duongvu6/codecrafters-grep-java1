package service;


import java.util.ArrayList;

public class PatternMatcher {
    public static boolean matchPattern(String inputLine, String pattern) {
        ArrayList<RegexMatcher> regex = new ArrayList<>();
        for (int i = 0; i < pattern.length(); i++) {
            if (pattern.charAt(i) == '\\') {
                i++;
                switch (pattern.charAt(i)) {
                    case 'd' -> regex.add(new RangeMatcher("0-9"));
                    case 'w' -> regex.add(new RangeMatcher("a-z", "A-Z", "0-9", "_"));
                    default -> {}
                }
            } else if (pattern.charAt(i) == '[') {
                int endIndex = pattern.indexOf(']', i);
                if (pattern.charAt(i + 1) == '^') {
                    String sub = pattern.substring(i + 2, endIndex);
                    regex.add(new NegativeRangeMatcher(sub));
                } else {
                    String sub  = pattern.substring(i + 1, endIndex);
                    regex.add(new RangeMatcher(sub));
                }
                i = endIndex;
            } else {
                regex.add(new CharacterMatcher(pattern.charAt(i)));
            }
        }
        int testIndex = regex.get(0).match(inputLine);
        for (RegexMatcher matcher : regex) {
            if (testIndex == -1 || !matcher.check(inputLine.charAt(testIndex))) {
                return false;
            }
            testIndex++;
        }
        return true;
    }
}
