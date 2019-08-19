public class CmdLeitura extends Command{
	private String idVar;
	
	public CmdLeitura(String idVar){
		this.idVar = idVar;
	}
	
	public String toJava(){
		return idVar + "="+Programa.INPUT+".nextInt();";
	}
}