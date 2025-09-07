package service;

public class CharacterMatcher extends RegexMatcher{
    private char character;
    public CharacterMatcher(char character) {
        this.character = character;
    }

    @Override
    public boolean check(char input) {
        return input == character;
    }

    @Override
    public int match(String input) {
        return input.indexOf(character);
    }
}
