package service;

public abstract class RegexMatcher {
    public abstract boolean check(char input);
    //return index that matches
    public abstract int match(String input);
}
