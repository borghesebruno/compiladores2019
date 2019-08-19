
import java.io.File;
import java.io.FileInputStream;

public class Evaluator {

    public static void main(String args[]) {
        try {
            MeuLexerExp lexer = new MeuLexerExp(new FileInputStream(new File("input.in")));
            MeuParserExp parser = new MeuParserExp(lexer);
            System.out.println("Starting Expression Parsing");
            parser.expr();
            System.out.println("Parsing finished Sucessfully");

        } catch (Exception ex) {
            System.err.println("ERROR - " + ex.getMessage());
        }
    }
}
