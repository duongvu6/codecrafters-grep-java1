package service;


import java.util.ArrayList;

public class PatternMatcher {
    public static boolean matchPattern(String inputLine, String pattern) {
        ArrayList<RegexMatcher> regex = new ArrayList<>();
        boolean isStart = false;
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
            } else if (pattern.charAt(i) == '^') {
                isStart = true;
            }
            else {
                regex.add(new CharacterMatcher(pattern.charAt(i)));
            }
        }
        int startMatchIndex = -1;
        while (true) {
            int testIndex = isStart ? 0 : regex.getFirst().match(inputLine, startMatchIndex + 1);
            startMatchIndex = testIndex;
            boolean matches = true;
            for (RegexMatcher matcher : regex) {
                if (testIndex == -1 || testIndex >= inputLine.length() || !matcher.check(inputLine.charAt(testIndex))) {
                    matches = false;
                    break;
                }
                testIndex++;
            }
            if (matches) {
                return true;
            } else {
                if (testIndex == -1 || testIndex >= inputLine.length() || isStart) {
                    return true;
                }
            }
        }
    }
}
