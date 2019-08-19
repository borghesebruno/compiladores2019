class MeuParserExp extends Parser;
{
   Expression expression;
   AbstractOperand num;
   BinaryOperand sumOrSubt;
   AbstractOperand parent;
   BinaryOperand multOrDiv;
   char op;
}

expr	: {expression = new Expression();} 
           expr_c T_eof
          {
            if (expression.getRoot() == null) expression.setRoot(num);
            expression.eval();
            System.out.println(expression);
            System.out.println(expression.getRoot().toXml());
          }
	;

expr_c  : termo 
          ((T_plus  | T_minus) {op = LT(0).getText().charAt(0);
                                sumOrSubt = new BinaryOperand(op);
                                sumOrSubt.setLeft(num);
                               }
            termo { 
                     if(expression.getRoot() == null){
                        sumOrSubt.setRight(num);
                        expression.setRoot(sumOrSubt);
                        parent = sumOrSubt;
                     }
                     else{
                         sumOrSubt = new BinaryOperand(op);
                         sumOrSubt.setRight(num);
                         BinaryOperand pai = (BinaryOperand)parent;
                         sumOrSubt.setLeft(pai.getRight());
                         pai.setRight(sumOrSubt);
                     }
                  }
            )* 
		;
		
termo	: fator ((T_times | T_divi) fator)*
		;
		
fator	: T_num { num = new UnaryOperand(Float.parseFloat(LT(0).getText()));
                }
        | T_ap expr_c T_fp
		;


class MeuLexerExp extends Lexer;

T_num 	: ('0'..'9')+ (('.') ('0'..'9')+)?
		;

T_plus	: '+'
		;
		
T_minus : '-'
		;
		
T_times : '*'
		;
		
T_divi	: '/'
		;
		
T_ap	: '('
		;
		
T_eof   : ';'
		;
		
T_fp	: ')'
		;
		
T_blank	: (' ' | '\n' {newline();} | '\r' | '\t' ) {_ttype=Token.SKIP;}
		;