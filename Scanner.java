import java.util.Arrays;
import java.util.List;

enum TokenType {
    NUMBER, TERM, INVALID
}

class Token {
    TokenType type;
    String value;

    Token(TokenType type, String value) {
        this.type = type;
        this.value = value;
    }

    @Override
    public String toString() {
        return type.toString().toLowerCase() + ": \"" + value + "\"";
    }
}

public class Scanner {
    private final String input;
    private int position;
    private static final List<Character> symbols = Arrays.asList('+', '-', '=');

    public Scanner(String input) {
        this.input = input;
        this.position = 0;
    }

    private boolean isDigit(char ch) {
        return ch >= '0' && ch <= '9';
    }

    private boolean isSymbol(char ch) {
        return symbols.contains(ch);
    }

    private char current() {
        if (position >= input.length()) return '\0'; // End of input marker
        return input.charAt(position);
    }

    private char next() {
        if (position >= input.length()) return '\0'; // Avoid out of bounds
        return input.charAt(position++);
    }

    private void skipWhitespace() {
        while (current() == ' ' || current() == '\n' || current() == '\t') {
            next();
        }
    }

    public Token getToken() {
        skipWhitespace();
        char currentChar = current();

        if (currentChar == '\0') return null; // End of input

        if (isDigit(currentChar)) {
            return new Token(TokenType.NUMBER, String.valueOf(next())); // Consume digit
        }

        if (isSymbol(currentChar)) {
            return new Token(TokenType.SYMBOL, String.valueOf(next())); // Consume symbol
        }

        return new Token(TokenType.ILLEGAL, String.valueOf(next())); // Consume invalid character
    }
}
