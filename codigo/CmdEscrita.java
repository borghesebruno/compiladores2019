public class CmdEscrita extends Command{
	private String  conteudo;
	
	public CmdEscrita(String conteudo){		
		this.conteudo = conteudo;
	}
	
	public String toJava(){
		return "System.out.println("+conteudo+");\n";
	}	
}