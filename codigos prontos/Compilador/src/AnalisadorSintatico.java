/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author isidro
 */
public class AnalisadorSintatico {
    private AnalisadorLexico lex;
    
    public AnalisadorSintatico(AnalisadorLexico l){
        this.lex = l;
    }
    
    public void programa(){
        Token t = lex.nextToken();
        if (t.getId() == Token.RESERVERD_WORD && t.getText().equals("programa")){
            t = lex.nextToken();
            if (t.getId() == Token.ID){
                declare();
            }
            else{
                throw new RuntimeException("AS:Parsing Error - Expecting ID");
            }
        }
        else{
            throw new RuntimeException("AS: Parsing Error - Expecting PROGRAMA");
        }
        t = lex.nextToken();
        if (t.getId() == Token.EOF){
            
        }
        else{
            throw new RuntimeException("AS: Program finished but file has content");
        }
        
    }
    public void declare(){
        Token t = lex.nextToken();
        if (t.getId() == Token.RESERVERD_WORD && t.getText().equals("declare")){
           t = lex.nextToken();
           if (t.getId() == Token.ID){
               t = lex.nextToken();
               if (t.getId() == Token.PONTUACTION){
                   bloco();
               }
               else if (t.getId() == Token.VIRGULA){
                   do{
                       t = lex.nextToken();
                       if (t.getId() == Token.ID){
                           t = lex.nextToken();
                       }
                       else{
                           throw new RuntimeException("AS: Parsing Error - Expecting ID");
                       }
                   } while(t.getId() == Token.VIRGULA);
                   if (t.getId() == Token.PONTUACTION){
                       bloco();
                   }
                   
               }
               else{
                   throw new RuntimeException("AS: Parsing Error - Expecting .");
               }
           }
           else{
               throw new RuntimeException("AS: Parsing Error - Expecting ID");
           }
        }
        else{
            throw new RuntimeException("AS: Parsing Error - Expecting DECLARE, "
                    + "found "+t.getText());
        }
        
    }
    
    public void bloco(){
        Token t = lex.nextToken();
        if (t.getId() == Token.RESERVERD_WORD && t.getText().equals("inicio")){
            
        }
        else{
            throw new RuntimeException("AS: Parsing Error - Expecting INICIO");
        }
        
    }
}
