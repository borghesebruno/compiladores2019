public class AnalisadorLexico{
    public static final int ID=0;
    public static final int NUM=1;
    public static final int ERROR=-1 ;
    public static final int OP=2;

    private char[] content;
    private int    pos;
    public AnalisadorLexico(char[] programa){
       this.content = programa;
       pos=0;
    }

    private boolean eof(){
       return pos==content.length;
    }
    private char nextChar(){
        return content[pos++];
    }

    private boolean isLetter(char c){
        return c >= 'a' && c <= 'z';
    }
    private boolean isPontuaction(char c){
        return c==' ' || c=='\n' || c=='\t' || c=='\r';
    }
    private boolean isOperator(char c){
       return c=='+' || c=='-' || c=='*' || c=='/' || c=='^';
    }
    private boolean isDigit(char c){
        return c>='0' && c<='9';
    }
    private void retroceder(){
       pos--;
    }


    public int nextToken(){
       int s=0;
       while(true){
         switch(s){
           case 0:
              char c = nextChar();
              if (isLetter(c)){
                  s = 1;
              }
              else if (isOperator(c)){
                 s = 2;
              }
              else if (isPontuaction(c)){
                 s = 0;
              }  
              else{
                 return ERROR;
              }
           break;
         case 1:
            c = nextChar();
            if (isLetter(c) || isDigit(c)){
               s = 1;
            }
            else if (isPontuaction(c)){
               return ID;
            }
            else if (isOperator(c)){
               retroceder();
               return ID;
            }
            else {
               return ERROR;
            }
            break;
         case 2:
            return OP;
         }
       }
    }
}