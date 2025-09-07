package service;

public interface RegexMatcher {
    public abstract boolean check(char input);
    //return index that matches
    public abstract int match(String input);

    public abstract int match(String input, int startIndex);
}
