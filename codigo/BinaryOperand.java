public class BinaryOperand  extends AbstractOperand{
    private char op;
    private AbstractOperand left;
    private AbstractOperand right;
    
    public BinaryOperand(char op, AbstractOperand l, AbstractOperand r){
        this.op =op;
        this.left = l;
        this.right = r;
    }
    public BinaryOperand(){
        this.left = null;
        this.right = null;
    }
    public BinaryOperand(char op){
        this.op = op;
    }
    
    public char getOp() {
        return op;
    }
    
    public void setOp(char op) {
        this.op = op;
    }
    
    public AbstractOperand getLeft() {
        return left;
    }
    
    public void setLeft(AbstractOperand left) {
        this.left = left;
    }
    
    public AbstractOperand getRight() {
        return right;
    }
    
    public void setRight(AbstractOperand right) {
        this.right = right;
    }

    @Override
    public Float getValue() {
       if (this.op == '+'){
           return left.getValue() + right.getValue();
       }        
       else if (this.op == '-'){
           return left.getValue() - right.getValue();
       }
       else if (this.op == '*'){
           return left.getValue() * right.getValue();
       }
       else if (this.op == '/'){
           if (right.getValue() != 0){
               return left.getValue() / right.getValue();
           }
           else{
               throw new RuntimeException("DIVISION BY ZERO!!!");
           }
       }
       else{
           throw new RuntimeException("Unsupported Operation");
       }
    }
}
