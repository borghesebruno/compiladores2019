
public class Main {

    public static void main(String[] args) {
        AnalisadorLexico l;
        l = new AnalisadorLexico("prog.in");
        AnalisadorSintatico as = new AnalisadorSintatico(l);

        try {
            as.programa();
            System.out.println("COMPILATION SUCCESSFULLY");
        } catch (RuntimeException ex) {
            System.out.println(ex.getMessage());

        }

    }
}
