public class UnaryLogicOperand extends AbstractLogicOperand {
    private Boolean bValue;
    private String var;
    private Boolean isId;

    public UnaryLogicOperand(Boolean value) {
        this.bValue = value;
        this.var = "";
        this.isId = false;
    }

    public UnaryLogicOperand(String value, Boolean isId) {
        switch (value) {
            case "verdade":
                this.bValue = true;
                break;
            case "mentira":
                this.bValue = false;
                break;
            default:
                if(isId) {
                    this.bValue = true;
                    this.var = value;
                    this.isId = isId;
                }
                break;
        }
    }

    @Override
    public Boolean getValue() {
        return this.bValue;
    }

    @Override
    public String getStringValue() {
        String val = "";
        if(this.isId) {
            val = this.var;
        } else if(this.bValue) {
            val = "true";
        } else {
            val = "false";
        }
        return val;
    }
}