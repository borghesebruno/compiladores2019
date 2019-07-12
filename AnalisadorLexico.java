import java.io.File;
import java.nio.file.Files;
import java.io.IOException;

public class AnalisadorLexico {
    public static final int ID = 5;
    public static final int NUM = 1;
    public static final int ERROR = -1;
    public static final int OP = 2;

    private char[] content;
    private int pos;

    public AnalisadorLexico(String filename) {
        try {
            byte[] bContent = Files.readAllBytes(new File(filename).toPath());
            this.content = new String(bContent).toCharArray();
            this.pos = 0;
        } catch (IOException ex) {
            System.err.println("Erro ao ler arquivo");
        }

    }

    public boolean eof() {
        return pos == content.length;
    }

    private char nextChar() {
        return content[pos++];
    }

    private boolean isLetter(char c) {
        return c >= 'a' && c <= 'z';
    }

    private boolean isPontuaction(char c) {
        return c == ' ' || c == '\n' || c == '\t' || c == '\r';
    }

    private boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '^';
    }

    private boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }

    private void retroceder() {
        pos--;
    }

    public Token nextToken() {
        int s = 0;
        while (true) {
            switch (s) {
                case 0:
                    char c = nextChar();
                    if (isLetter(c)) {
                        s = 1;
                    } else if (isOperator(c)) {
                        s = 2;
                    } else if (isPontuaction(c)) {
                        s = 0;
                    } else {
                        return new Token(ERROR, "");
                    }
                    break;
                case 1:
                    c = nextChar();
                    if (isLetter(c) || isDigit(c)) {
                        s = 1;
                    } else if (isPontuaction(c)) {
                        return new Token(ID, "");
                    } else if (isOperator(c)) {
                        retroceder();
                        return new Token(ID, "");
                    } else {
                        return new Token(ERROR, "");
                    }
                    break;
                case 2:
                    return new Token(OP, "");
                }
        }
    }
}