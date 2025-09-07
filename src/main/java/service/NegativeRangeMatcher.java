package service;

public class NegativeRangeMatcher extends RangeMatcher{
    public NegativeRangeMatcher(String... ranges) {
        super(ranges);
    }
    @Override
    public int match(String input) {
        for (int i = 0; i < input.length(); i++) {
            if(!matchedChars.contains("" + input.charAt(i))) {
                return i;
            }
        }
        return -1;
    }
    @Override
    public boolean check(char input) {
        return !matchedChars.contains("" + input);
    }
}
