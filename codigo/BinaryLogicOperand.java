public class BinaryLogicOperand extends AbstractLogicOperand {
    private String op;
    private AbstractLogicOperand left;
    private AbstractLogicOperand right;

    public BinaryLogicOperand(String op, AbstractLogicOperand l, AbstractLogicOperand r) {
        this.op = op;
        this.left = l;
        this.right = r;
    }

    public BinaryLogicOperand() {
        this.left = null;
        this.right = null;
    }

    public BinaryLogicOperand(String op) {
        this.op = op;
    }

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }

    public AbstractLogicOperand getLeft() {
        return left;
    }

    public void setLeft(AbstractLogicOperand left) {
        this.left = left;
    }

    public AbstractLogicOperand getRight() {
        return right;
    }

    public void setRight(AbstractLogicOperand right) {
        this.right = right;
    }

    @Override
    public Boolean getValue() {
        System.out.println("getOp1 " + this.op);
        if (this.op.equals("==")) {
            System.out.println("getOp21 " + "==");
            System.out.println(left.getStringValue());
            System.out.println(right.getStringValue());
            return left.getValue() == right.getValue();
        } else if (this.op.equals("!=")) {
            System.out.println("getOp21 " + "!=");
            System.out.println(left.getStringValue());
            System.out.println(right.getStringValue());
            return left.getValue() != right.getValue();
        } else {
            throw new RuntimeException("Unsupported Operation");
        }
    }

    @Override
    public String getStringValue() {
        System.out.println("getOp2 " + this.op);
        if (this.op.equals("==")) {
            System.out.println("getOp21 " + "==");
            return left.getStringValue() + "==" + right.getStringValue();
        } else if (this.op.equals("!=")) {
            System.out.println("getOp21 " + "!=");
            return left.getStringValue() + "!=" + right.getStringValue();
        } else {
            throw new RuntimeException("Unsupported Operation");
        }
    }
}
