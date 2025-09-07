package service;

public class RangeMatcher extends RegexMatcher{
    private static final String lowercaseAlphabet = "abcdefghijklmnopqrstuvwxyz";
    private static final String uppercaseAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String digits = "0123456789";
    protected String matchedChars;
    public RangeMatcher(String... ranges) {
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
        matchedChars = finalRange.toString();
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
        return matchedChars.contains("" + input);
    }

    @Override
    public int match(String input) {
        int firstIndexMatches = Integer.MAX_VALUE;
        for (int i = 0; i < matchedChars.length(); i++) {
            int index = input.indexOf(matchedChars.charAt(i));
            if (index >= 0) {
                firstIndexMatches = Math.min(firstIndexMatches, index);
            }
        }
        if (firstIndexMatches == Integer.MAX_VALUE) {
            return -1;
        }
        return firstIndexMatches;
    }
}
