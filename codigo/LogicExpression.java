public class LogicExpression {
    private Boolean value;
    private String stringValue;

    private AbstractLogicOperand root;

    public LogicExpression() {
        this.root = null;
        this.value = false;
        this.stringValue = "";
    }

    public LogicExpression(AbstractLogicOperand root) {
        this.root = root;
        this.value = false;
        this.stringValue = "";
    }

    public void eval() {
        //this.value = this.root.getValue();
        this.stringValue = this.root.getStringValue();
    }

    public Boolean getValue() {
        return value;
    }

    public void setValue(Boolean value) {
        this.value = value;
    }

    public String getStringValue() {
        return stringValue;
    }

    public void setStringValue(String value) {
        this.stringValue = value;
    }

    public AbstractLogicOperand getRoot() {
        return root;
    }

    public void setRoot(AbstractLogicOperand root) {
        this.root = root;
    }
}