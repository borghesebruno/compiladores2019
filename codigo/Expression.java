public class Expression {
    private Float value;

    private AbstractOperand root;

    public Expression() {
        this.root = null;
        this.value = 0.0f;
    }

    public Expression(AbstractOperand root) {
        this.root = root;
        this.value = 0.0f;
    }

    public void eval() {
        this.value = this.root.getValue();
    }

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }

    public AbstractOperand getRoot() {
        return root;
    }

    public void setRoot(AbstractOperand root) {
        this.root = root;
    }
}