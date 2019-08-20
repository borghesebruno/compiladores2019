public class Compiler {
	public static void main(String args[]) {
		try {
			if (args.length == 0) {
				System.out.println("Uso: java -cp antlr.jar Compiler [filename] < [source file].in");
			} else {
				TheLexer lexer = new TheLexer(System.in);
				TheParser parser = new TheParser(lexer);

				parser.setPrograma(args[0]);

				System.out.println("Iniciando processo de compilacao");

				parser.prog();

				System.out.println("Compilacao Completa");
				System.out.println("Gerando Codigo");

				parser.getPrograma().saveToFile();
			}
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
		}
	}
}