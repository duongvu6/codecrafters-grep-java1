package service;

import java.util.HashSet;
import java.util.Set;

public class RangeMatcher implements RegexMatcher{
    private static final String lowercaseAlphabet = "abcdefghijklmnopqrstuvwxyz";
    private static final String uppercaseAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String digits = "0123456789";
    protected Set<Character> matchedChars;
    public RangeMatcher(String... ranges) {
        matchedChars = new HashSet<>();
        StringBuilder finalRange = new StringBuilder();
        for (String range : ranges) {
            if (range.contains("-")) {
                String start = String.valueOf(range.charAt(0));
                String end = String.valueOf(range.charAt(2));
                String correctRange = calCorrectRange(range, start, end);
                finalRange.append(correctRange);
            } else {
                finalRange.append(range);
            }
        }
        char[] chars = finalRange.toString().toCharArray();
        for (char x : chars) {
            matchedChars.add(x);
        }
    }

    private static String calCorrectRange(String range, String start, String end) {
        String correctRange;
        if (lowercaseAlphabet.contains(start)) {
            correctRange = lowercaseAlphabet;
        } else if (uppercaseAlphabet.contains(start)) {
            correctRange = uppercaseAlphabet;
        } else if (digits.contains(start)) {
            correctRange = digits;
        } else {
            throw new IllegalArgumentException("Invalid range: " + range);
        }
        if (!correctRange.contains(end)) {
            throw new IllegalArgumentException("Invalid range: " + range);
        }
        return correctRange;
    }

    @Override
    public boolean check(char input) {
        return matchedChars.contains(input);
    }

    @Override
    public int match(String input) {
        return match(input, 0);
    }

    @Override
    public int match(String input, int startIndex) {
        for(int i = startIndex; i < input.length(); i++) {
            if(matchedChars.contains(input.charAt(i))) {
                return i;
            }
        }
        return -1;
    }
}
