public class CmdSe extends Command {
	private LogicExpression expr;

	public CmdSe(LogicExpression expr) {
		this.expr = expr;
	}

	public String toJava() {
		return "if(" + expr.getStringValue() + ") {\n";
	}
}