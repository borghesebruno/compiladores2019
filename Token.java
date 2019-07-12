public class Token {
    private int id;
    private String text;

    public Token() {
        id = 0;
        text = "";
    }

    public Token(int id, String text) {
        this.id = id;
        this.text = text;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setText(String str) {
        this.text = str;
    }

    public String getText() {
        return this.text;
    }

    public String toString() {
        return "ID: "+this.id + " TEXTO: "+this.text;
    }
}