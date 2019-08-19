public class Compiler{
	public static void main(String args[]){
		try{
			if (args.length == 0){
				System.out.println("Use: java -cp antlr.jar Compiler [filename] < [source file].in");
			}
			else{
				MeuLexer lexer = new MeuLexer(System.in);
				MeuParser parser = new MeuParser(lexer);
						
				parser.setPrograma(args[0]);

				System.out.println("Starting Compiling Process...");

				parser.prog();
			
				System.out.println("Compilation Finishted!");
				System.out.println("Generating Code");

				parser.getPrograma().saveToFile();
			}
		}
		catch(Exception ex){
			System.err.println(ex.getMessage());
		}
	}
}