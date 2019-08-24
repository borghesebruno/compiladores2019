class TheParser extends Parser;
{
	Expression expression;
	AbstractOperand num;
	BinaryOperand sumOrSubt;
	AbstractOperand parent;
	BinaryOperand multOrDiv;
	char op;

	LogicExpression logicExpression;
	AbstractLogicOperand logicBoolean;
	BinaryLogicOperand logicEquals;
	AbstractLogicOperand logicParentBoolean;
	String logicOp;

	java.util.HashMap<String, String> mapaVar = new java.util.HashMap<String, String>();
	Programa p;
	
	String varId = "";
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
			  | "decimal" {varType = "Float";}
			  | "booleano" {varType = "Boolean";} )
			T_Id {mapaVar.put(LT(0).getText(), varType);}
			( 
				T_virg 
				T_Id {mapaVar.put(LT(0).getText(), varType);}
			)* 
           
        	T_pontof 
			{
				p.setVariaveis(mapaVar);
				System.out.println("Lista de variaveis do tipo " + varType + " expandida.");
		   	}
		;

bloco   : (cmd)+
		;

cmd		:  cmdLeia    T_pontof  
		|  cmdEscr    T_pontof
		|  cmdAttr    T_pontof
		|  cmdSeEs    T_pontof
        ;   
        

cmdLeia :  { System.out.println("cmdLeia");} "leia" T_ap
			T_Id {
				String var = LT(0).getText();
				
				if (mapaVar.get(var) == null){
					throw new RuntimeException("ERROR ID "+LT(0).getText()+" not declared!");
				}

				p.addCommand(new CmdLeitura(var, mapaVar.get(var)));
			}
            T_fp
		;

cmdEscr :  { System.out.println("cmdEscr");} "escreva" T_ap ( 
				T_texto 
				| T_Id {
					if (mapaVar.get(LT(0).getText()) == null){
						throw new RuntimeException("ERROR ID "+LT(0).getText()+" not declared!");
					}
				}  
		   )  { p.addCommand(new CmdEscrita(LT(0).getText())); }
		   T_fp
		;

cmdAttr :  { System.out.println("cmdAttr");} T_Id {
				varId = LT(0).getText();
				if (mapaVar.get(varId) == null){
					throw new RuntimeException("ERROR ID "+LT(0).getText()+" not declared!");
				}
				//varType = mapaVar.get(LT(0).getText());
		   } 
           T_attr 
           ( T_num | "verdade" | "mentira" ) {
			   p.addCommand(new CmdAttr(varId, LT(0).getText()));
		   }
		;

cmdSeEs :  { System.out.println("CmdSeEs");} "se" T_ap
				logic { p.addCommand(new CmdSe(logicExpression)); }
			T_fp "entao" bloco
			(
				"ss" "senao" { p.addCommand(new CmdSenao()); } bloco
			)?
		   "es" { p.addCommand(new CmdFimSe()); }
		;

logic   :  { logicExpression = new LogicExpression(); }
		   expr_l {
				if (logicExpression.getRoot() == null) {
					logicExpression.setRoot(logicBoolean);
				}
				logicExpression.eval();
		   }
		;

expr_l  :  bool
		   (
			    ( T_igualI | T_difeI ) {
					logicOp = LT(0).getText();
					{ System.out.println("setOp1 " + logicOp);}
					logicEquals = new BinaryLogicOperand(logicOp);
					logicEquals.setLeft(logicBoolean);
				}
				bool {
					if(logicExpression.getRoot() == null) {
						{ System.out.println("setOp21 " + logicOp);}
						logicEquals.setRight(logicBoolean);
						logicExpression.setRoot(logicEquals);
						logicParentBoolean = logicEquals;
					} else {
						{ System.out.println("setOp2 " + logicOp);}
						logicEquals = new BinaryLogicOperand(logicOp);
						logicEquals.setRight(logicBoolean);
						BinaryLogicOperand pai = (BinaryLogicOperand)logicParentBoolean;
						logicEquals.setLeft(pai.getRight());
						pai.setRight(logicEquals);
					}
				}
		   )*
		;
		
bool    :  T_Id {
				String var = LT(0).getText();
							
				if (mapaVar.get(var) == null){
					throw new RuntimeException("ERROR ID "+var+" not declared!");
				}
				logicBoolean = new UnaryLogicOperand(var, true);
		   }
			|  ("verdade"
			|  "mentira"
		   ) {
			   logicBoolean = new UnaryLogicOperand(LT(0).getText(), false);
		   }
		|  T_ap expr_l T_fp
		;

expr_a  :  termo (( T_soma | T_subt ) termo)*
		;

termo   :  fator (( T_mult | T_divi ) fator)*
		;

fator   :  T_Id {
           	  	if (mapaVar.get(LT(0).getText()) == null){
					throw new RuntimeException("ERROR ID "+LT(0).getText()+" not declared!");
				}

				num = new UnaryOperand(Float.parseFloat(LT(0).getText()));
           }  
        |  T_num { num = new UnaryOperand(Float.parseFloat(LT(0).getText()));}
        |  T_ap expr_a T_fp
		;



class TheLexer extends Lexer;
options{
	caseSensitive = true;
	k=2;
}

T_Id options {testLiterals=true;}
		 :  (('a'..'z' | 'A'..'Z') ('a'..'z'| 'A'..'Z'| '0'..'9')*)
		 ;
		 
T_comm_i : "/*"
		 ;

T_comm_f : "*/"
		 ;

T_num	 : ('0'..'9')+ (('.') ('0'..'9')+)?
		 ;

T_soma   : '+'
		 ;

T_subt   : '-'
		 ;

T_mult   : '*'
		 ;

T_divi   : '/'
		 ;

T_menor  : '<'
		 ;

T_maior  : '>'
		 ;

T_menorI : "<="
		 ;

T_maiorI : ">="
		 ;

T_igual  : '='
		 ;

T_igualI : "=="
		 ;

T_excla  : '!'
		 ;

T_difeI : "!="
		 ;

T_virg   : ','
		 ;

T_pontof : ';'
		 ;

T_ap	 : '('
		 ;

T_fp     : ')'
		 ;

T_texto  : '"' ( 'a'..'z' | '0'..'9' | ' ' | 'A'..'Z' | '!' | '?' | '.' | ',' | ':' | '/' )* '"'
		 ;

T_attr	 : ":="
		 ;

T_e      : '&'
		 ;

T_ou     : '|'
		 ;

T_blank  : ( ' ' | '\n' {newline();} | '\r' | '\t' ) {_ttype=Token.SKIP;}
	     ;