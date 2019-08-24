public class UnaryOperand extends AbstractOperand{
    private Float fValue;
    
    public UnaryOperand(Float value){
        this.fValue = value;
    }

    @Override
    public Float getValue() {
        return this.fValue;
    }    
}