// $ANTLR 2.7.6 (2005-12-22): "gramatica.g" -> "MeuParserExp.java"$

import antlr.TokenBuffer;
import antlr.TokenStreamException;
import antlr.TokenStreamIOException;
import antlr.ANTLRException;
import antlr.LLkParser;
import antlr.Token;
import antlr.TokenStream;
import antlr.RecognitionException;
import antlr.NoViableAltException;
import antlr.MismatchedTokenException;
import antlr.SemanticException;
import antlr.ParserSharedInputState;
import antlr.collections.impl.BitSet;

public class MeuParserExp extends antlr.LLkParser       implements MeuParserExpTokenTypes
 {

   Expression expression;
   AbstractOperand num;
   BinaryOperand sumOrSubt;
   AbstractOperand parent;
   BinaryOperand multOrDiv;
   char op;

protected MeuParserExp(TokenBuffer tokenBuf, int k) {
  super(tokenBuf,k);
  tokenNames = _tokenNames;
}

public MeuParserExp(TokenBuffer tokenBuf) {
  this(tokenBuf,1);
}

protected MeuParserExp(TokenStream lexer, int k) {
  super(lexer,k);
  tokenNames = _tokenNames;
}

public MeuParserExp(TokenStream lexer) {
  this(lexer,1);
}

public MeuParserExp(ParserSharedInputState state) {
  super(state,1);
  tokenNames = _tokenNames;
}

	public final void expr() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			expression = new Expression();
			expr_c();
			match(T_eof);
			
			if (expression.getRoot() == null) expression.setRoot(num);
			expression.eval();
			System.out.println(expression);
			System.out.println(expression.getRoot().toXml());
			
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_0);
		}
	}
	
	public final void expr_c() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			termo();
			{
			_loop5:
			do {
				if ((LA(1)==T_plus||LA(1)==T_minus)) {
					{
					switch ( LA(1)) {
					case T_plus:
					{
						match(T_plus);
						break;
					}
					case T_minus:
					{
						match(T_minus);
						break;
					}
					default:
					{
						throw new NoViableAltException(LT(1), getFilename());
					}
					}
					}
					op = LT(0).getText().charAt(0);
					sumOrSubt = new BinaryOperand(op);
					sumOrSubt.setLeft(num);
					
					termo();
					
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
				else {
					break _loop5;
				}
				
			} while (true);
			}
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_1);
		}
	}
	
	public final void termo() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			fator();
			{
			_loop9:
			do {
				if ((LA(1)==T_times||LA(1)==T_divi)) {
					{
					switch ( LA(1)) {
					case T_times:
					{
						match(T_times);
						break;
					}
					case T_divi:
					{
						match(T_divi);
						break;
					}
					default:
					{
						throw new NoViableAltException(LT(1), getFilename());
					}
					}
					}
					fator();
				}
				else {
					break _loop9;
				}
				
			} while (true);
			}
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_2);
		}
	}
	
	public final void fator() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			switch ( LA(1)) {
			case T_num:
			{
				match(T_num);
				num = new UnaryOperand(Float.parseFloat(LT(0).getText()));
				
				break;
			}
			case T_ap:
			{
				match(T_ap);
				expr_c();
				match(T_fp);
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_3);
		}
	}
	
	
	public static final String[] _tokenNames = {
		"<0>",
		"EOF",
		"<2>",
		"NULL_TREE_LOOKAHEAD",
		"T_eof",
		"T_plus",
		"T_minus",
		"T_times",
		"T_divi",
		"T_num",
		"T_ap",
		"T_fp",
		"T_blank"
	};
	
	private static final long[] mk_tokenSet_0() {
		long[] data = { 2L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_0 = new BitSet(mk_tokenSet_0());
	private static final long[] mk_tokenSet_1() {
		long[] data = { 2064L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_1 = new BitSet(mk_tokenSet_1());
	private static final long[] mk_tokenSet_2() {
		long[] data = { 2160L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_2 = new BitSet(mk_tokenSet_2());
	private static final long[] mk_tokenSet_3() {
		long[] data = { 2544L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_3 = new BitSet(mk_tokenSet_3());
	
	}
