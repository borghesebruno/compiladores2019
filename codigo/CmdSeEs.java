public class CmdSeEs extends Command {
	private String idVar;
	private String type;

	public CmdSeEs(String idVar, String type) {
		this.idVar = idVar;
		this.type = type;
	}

	public String toJava() {
		String input = "";
		switch(type) {
			case "Integer":
				input = ".nextInt();";
				break;
			case "Boolean":
				input = ".nextBoolean();";
				break;
			default:
				break;
		}
		return idVar + " = " + Programa.INPUT + input + "\n";
	}
}