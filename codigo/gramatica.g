class TheParser extends Parser;
{
	java.util.HashMap<String, String> mapaVar = new java.util.HashMap<String, String>();
	Programa p;
	String varType = "";

    public void setPrograma(String name){
    	p = new Programa(name);
    }
    public Programa getPrograma(){
    	return p;
    }
}

prog	: "programa" (declara)* bloco "fimprog"
		;

declara : "declare" 
			  ( "inteiro" {varType = "Integer";}
			  | "booleano" {varType = "Boolean";} )
              T_Id {mapaVar.put(LT(0).getText(), varType);}
              ( 
              	T_virg 
              	T_Id {mapaVar.put(LT(0).getText(), varType);}
              )* 
           
           T_pontof 
		   {
		      p.setVariaveis(mapaVar);
			  System.out.println("Variable list assembled...");
		   }
		;

bloco   : (cmd)+
		;

cmd		:  cmdLeia    T_pontof  
		|  cmdEscr    T_pontof
		|  cmdAttr    T_pontof
        ;   
        

cmdLeia :  "leia" T_ap
			T_Id {
				String var = LT(0).getText();
				
				if (mapaVar.get(var) == null){
					throw new RuntimeException("ERROR ID "+LT(0).getText()+" not declared!");
				}

				p.addCommand(new CmdLeitura(var, mapaVar.get(var)));
			}
            T_fp
		;

cmdEscr :  "escreva" T_ap ( 
	                        T_texto 
	                        | 

	                        T_Id {
	                        	   if (mapaVar.get(LT(0).getText()) == null){
                                       throw new RuntimeException("ERROR ID "+LT(0).getText()+" not declared!");
                                   }
                                 }  
           
		   
		   )  { p.addCommand(new CmdEscrita(LT(0).getText())); }
		   T_fp
		;

cmdAttr :  T_Id  {
	                if (mapaVar.get(LT(0).getText()) == null){
                       throw new RuntimeException("ERROR ID "+LT(0).getText()+" not declared!");
                   }
                 } 
           T_attr 
           expr
		;

expr    :  termo (( T_soma | T_subt ) termo)*
		;

termo   :  fator (( T_mult | T_divi ) fator)*
		;

fator   :  T_Id 
           {
           	  if (mapaVar.get(LT(0).getText()) == null){
                 throw new RuntimeException("ERROR ID "+LT(0).getText()+" not declared!");
             }
           }  
        |  T_num 
        |  T_ap expr T_fp
		;



class TheLexer extends Lexer;
options{
	caseSensitive = true;
	k=2;
}

T_Id	 : ('a'..'z' | 'A'..'Z') ('a'..'z'| 'A'..'Z'| '0'..'9')*
		 ;
		 
T_comm_i : "/*"
		 ;

T_comm_f : "*/"
		 ;

T_num	 : ('0'..'9')+
		 ;

T_soma   : '+'
		 ;

T_subt   : '-'
		 ;

T_mult   : '*'
		 ;

T_divi   : '/'
		 ;

T_virg   : ','
		 ;

T_pontof : '.'
		 ;

T_ap	 : '('
		 ;

T_fp     : ')'
		 ;

T_texto  : '"' ( 'a'..'z' | '0'..'9' | ' ' | 'A'..'Z' | '!' | '?' | '.' | ',' | ':' | ';' | '/' )* '"'
		 ;

T_attr	 : ":="
		 ;

T_blank  : ( ' ' | '\n' {newline();}| '\r' | '\t') {_ttype=Token.SKIP;}
	     ;