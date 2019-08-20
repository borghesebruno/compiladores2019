

import java.io.File;
import java.nio.file.Files;
import java.io.IOException;

public class AnalisadorLexico {

    private String[] reservedWords = {"programa","declare","escreva","leia", "inicio"
                                       ,"fim", "se", "entao", "senao", "fimse"};
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

    private boolean isReservedWord(String text){
        for (String s: reservedWords){
            if (text.equals(s)){
                return true;
            }
        }
        return false;
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
    private boolean isVirgula(char c){
        return c ==',';
    }
    private boolean isPontuaction(char c){
        return c == '.';
    }

    private boolean isBlank(char c) {
        return c == ' ' || c == '\n' || c == '\t' || c == '\r' || c=='\0';
    }

    private boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '^' || c == '<'
               || c == '>' || c == '=';
    }

    private boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }

    private void retroceder() {
        pos--;
    }

    public Token nextToken() {
        int s = 0;
        String text="";
        Token token;
        while (true) {
            if (eof()) {
                return new Token(Token.EOF,"");
            }
            switch (s) {
                case 0:
                    char c = nextChar();
                    if (isLetter(c)) {
                        s = 1;
                        text += c;
                    } else if (isOperator(c)) {
                        text += c;
                        s = 2;
                    } else if (isBlank(c)) {
                        s = 0;
                    } else if (isVirgula(c)){
                       text += c;
                       return new Token(Token.VIRGULA, text);
                    } else if (isPontuaction(c)){
                        text += c;
                        return new Token(Token.PONTUACTION, text);
                    } else {
                        return null;
                    }
                    break;
                case 1:
                    c = nextChar();
                    if (isLetter(c) || isDigit(c)) {
                        s = 1;
                        text += c;
                    } else if (isBlank(c) ) {
                        
                        if (isReservedWord(text))
                           return new Token(Token.RESERVERD_WORD, text);
                        else
                            return new Token(Token.ID, text);
                    } else if (isOperator(c) || isPontuaction(c) || isVirgula(c)) {
                        retroceder();
                        return new Token(Token.ID, text);
                    } else {
                        return null;
                    }
                    break;
                case 2:
                    return new Token(Token.OPERATOR, text);
            }
        }
    }
}
