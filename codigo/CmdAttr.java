public class CmdAttr extends Command {
	private String idVar;
	private String val;
	private String type;

	public CmdAttr(String idVar, String val/*String type*/) {
		this.idVar = idVar;
		this.val = val;
		//this.type = type;
	}

	public String toJava() {
		String value = "";
		switch(val) {
			case "verdade":
				value = "true";
				break;
			case "mentira":
				value = "false";
				break;
			default:
				if(val.indexOf('.') != -1) {
					value = val+"f";
				} else {
					value = val;
				}
				break;
		}
		return idVar + " = " + value + ";\n";
	}
}