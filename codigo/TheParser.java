// $ANTLR 2.7.6 (2005-12-22): "gramatica.g" -> "TheParser.java"$

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

public class TheParser extends antlr.LLkParser       implements TheParserTokenTypes
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

protected TheParser(TokenBuffer tokenBuf, int k) {
  super(tokenBuf,k);
  tokenNames = _tokenNames;
}

public TheParser(TokenBuffer tokenBuf) {
  this(tokenBuf,1);
}

protected TheParser(TokenStream lexer, int k) {
  super(lexer,k);
  tokenNames = _tokenNames;
}

public TheParser(TokenStream lexer) {
  this(lexer,1);
}

public TheParser(ParserSharedInputState state) {
  super(state,1);
  tokenNames = _tokenNames;
}

	public final void prog() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_programa);
			{
			_loop3:
			do {
				if ((LA(1)==LITERAL_declare)) {
					declara();
				}
				else {
					break _loop3;
				}
				
			} while (true);
			}
			bloco();
			match(LITERAL_fimprog);
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_0);
		}
	}
	
	public final void declara() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_declare);
			{
			switch ( LA(1)) {
			case LITERAL_inteiro:
			{
				match(LITERAL_inteiro);
				varType = "Integer";
				break;
			}
			case LITERAL_decimal:
			{
				match(LITERAL_decimal);
				varType = "Float";
				break;
			}
			case LITERAL_booleano:
			{
				match(LITERAL_booleano);
				varType = "Boolean";
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			match(T_Id);
			mapaVar.put(LT(0).getText(), varType);
			{
			_loop7:
			do {
				if ((LA(1)==T_virg)) {
					match(T_virg);
					match(T_Id);
					mapaVar.put(LT(0).getText(), varType);
				}
				else {
					break _loop7;
				}
				
			} while (true);
			}
			match(T_pontof);
			
							p.setVariaveis(mapaVar);
							System.out.println("Lista de variaveis do tipo " + varType + " expandida.");
					   	
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_1);
		}
	}
	
	public final void bloco() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			{
			int _cnt10=0;
			_loop10:
			do {
				if ((_tokenSet_2.member(LA(1)))) {
					cmd();
				}
				else {
					if ( _cnt10>=1 ) { break _loop10; } else {throw new NoViableAltException(LT(1), getFilename());}
				}
				
				_cnt10++;
			} while (true);
			}
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_3);
		}
	}
	
	public final void cmd() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			switch ( LA(1)) {
			case LITERAL_leia:
			{
				cmdLeia();
				match(T_pontof);
				break;
			}
			case LITERAL_escreva:
			{
				cmdEscr();
				match(T_pontof);
				break;
			}
			case T_Id:
			{
				cmdAttr();
				match(T_pontof);
				break;
			}
			case LITERAL_se:
			{
				cmdSeEs();
				match(T_pontof);
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
			recover(ex,_tokenSet_4);
		}
	}
	
	public final void cmdLeia() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			System.out.println("cmdLeia");
			match(LITERAL_leia);
			match(T_ap);
			match(T_Id);
			
							String var = LT(0).getText();
							
							if (mapaVar.get(var) == null){
								throw new RuntimeException("ERROR ID "+LT(0).getText()+" not declared!");
							}
			
							p.addCommand(new CmdLeitura(var, mapaVar.get(var)));
						
			match(T_fp);
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_5);
		}
	}
	
	public final void cmdEscr() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			System.out.println("cmdEscr");
			match(LITERAL_escreva);
			match(T_ap);
			{
			switch ( LA(1)) {
			case T_texto:
			{
				match(T_texto);
				break;
			}
			case T_Id:
			{
				match(T_Id);
				
									if (mapaVar.get(LT(0).getText()) == null){
										throw new RuntimeException("ERROR ID "+LT(0).getText()+" not declared!");
									}
								
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			p.addCommand(new CmdEscrita(LT(0).getText()));
			match(T_fp);
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_5);
		}
	}
	
	public final void cmdAttr() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			System.out.println("cmdAttr");
			match(T_Id);
			
							varId = LT(0).getText();
							if (mapaVar.get(varId) == null){
								throw new RuntimeException("ERROR ID "+LT(0).getText()+" not declared!");
							}
							//varType = mapaVar.get(LT(0).getText());
					
			match(T_attr);
			{
			switch ( LA(1)) {
			case T_num:
			{
				match(T_num);
				break;
			}
			case LITERAL_verdade:
			{
				match(LITERAL_verdade);
				break;
			}
			case LITERAL_mentira:
			{
				match(LITERAL_mentira);
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			
						   p.addCommand(new CmdAttr(varId, LT(0).getText()));
					
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_5);
		}
	}
	
	public final void cmdSeEs() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			System.out.println("CmdSeEs");
			match(LITERAL_se);
			match(T_ap);
			logic();
			p.addCommand(new CmdSe(logicExpression));
			match(T_fp);
			match(LITERAL_entao);
			bloco();
			{
			switch ( LA(1)) {
			case LITERAL_ss:
			{
				match(LITERAL_ss);
				match(LITERAL_senao);
				p.addCommand(new CmdSenao());
				bloco();
				break;
			}
			case LITERAL_es:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			match(LITERAL_es);
			p.addCommand(new CmdFimSe());
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_5);
		}
	}
	
	public final void logic() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			logicExpression = new LogicExpression();
			expr_l();
			
							if (logicExpression.getRoot() == null) {
								logicExpression.setRoot(logicBoolean);
							}
							logicExpression.eval();
					
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_6);
		}
	}
	
	public final void expr_l() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			bool();
			{
			_loop23:
			do {
				if ((LA(1)==T_igualI||LA(1)==T_difeI)) {
					{
					switch ( LA(1)) {
					case T_igualI:
					{
						match(T_igualI);
						break;
					}
					case T_difeI:
					{
						match(T_difeI);
						break;
					}
					default:
					{
						throw new NoViableAltException(LT(1), getFilename());
					}
					}
					}
					
										logicOp = LT(0).getText();
										{ System.out.println("setOp1 " + logicOp);}
										logicEquals = new BinaryLogicOperand(logicOp);
										logicEquals.setLeft(logicBoolean);
									
					bool();
					
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
				else {
					break _loop23;
				}
				
			} while (true);
			}
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_6);
		}
	}
	
	public final void bool() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			switch ( LA(1)) {
			case T_Id:
			{
				match(T_Id);
				
								String var = LT(0).getText();
											
								if (mapaVar.get(var) == null){
									throw new RuntimeException("ERROR ID "+var+" not declared!");
								}
								logicBoolean = new UnaryLogicOperand(var, true);
						
				break;
			}
			case LITERAL_verdade:
			case LITERAL_mentira:
			{
				{
				switch ( LA(1)) {
				case LITERAL_verdade:
				{
					match(LITERAL_verdade);
					break;
				}
				case LITERAL_mentira:
				{
					match(LITERAL_mentira);
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				
							   logicBoolean = new UnaryLogicOperand(LT(0).getText(), false);
						
				break;
			}
			case T_ap:
			{
				match(T_ap);
				expr_l();
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
			recover(ex,_tokenSet_7);
		}
	}
	
	public final void expr_a() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			termo();
			{
			_loop29:
			do {
				if ((LA(1)==T_soma||LA(1)==T_subt)) {
					{
					switch ( LA(1)) {
					case T_soma:
					{
						match(T_soma);
						break;
					}
					case T_subt:
					{
						match(T_subt);
						break;
					}
					default:
					{
						throw new NoViableAltException(LT(1), getFilename());
					}
					}
					}
					termo();
				}
				else {
					break _loop29;
				}
				
			} while (true);
			}
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_6);
		}
	}
	
	public final void termo() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			fator();
			{
			_loop33:
			do {
				if ((LA(1)==T_mult||LA(1)==T_divi)) {
					{
					switch ( LA(1)) {
					case T_mult:
					{
						match(T_mult);
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
					break _loop33;
				}
				
			} while (true);
			}
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_8);
		}
	}
	
	public final void fator() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			switch ( LA(1)) {
			case T_Id:
			{
				match(T_Id);
				
					  	if (mapaVar.get(LT(0).getText()) == null){
									throw new RuntimeException("ERROR ID "+LT(0).getText()+" not declared!");
								}
				
								num = new UnaryOperand(Float.parseFloat(LT(0).getText()));
				
				break;
			}
			case T_num:
			{
				match(T_num);
				num = new UnaryOperand(Float.parseFloat(LT(0).getText()));
				break;
			}
			case T_ap:
			{
				match(T_ap);
				expr_a();
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
			recover(ex,_tokenSet_9);
		}
	}
	
	
	public static final String[] _tokenNames = {
		"<0>",
		"EOF",
		"<2>",
		"NULL_TREE_LOOKAHEAD",
		"\"programa\"",
		"\"fimprog\"",
		"\"declare\"",
		"\"inteiro\"",
		"\"decimal\"",
		"\"booleano\"",
		"T_Id",
		"T_virg",
		"T_pontof",
		"\"leia\"",
		"T_ap",
		"T_fp",
		"\"escreva\"",
		"T_texto",
		"T_attr",
		"T_num",
		"\"verdade\"",
		"\"mentira\"",
		"\"se\"",
		"\"entao\"",
		"\"ss\"",
		"\"senao\"",
		"\"es\"",
		"T_igualI",
		"T_difeI",
		"T_soma",
		"T_subt",
		"T_mult",
		"T_divi",
		"T_comm_i",
		"T_comm_f",
		"T_menor",
		"T_maior",
		"T_menorI",
		"T_maiorI",
		"T_igual",
		"T_excla",
		"T_e",
		"T_ou",
		"T_blank"
	};
	
	private static final long[] mk_tokenSet_0() {
		long[] data = { 2L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_0 = new BitSet(mk_tokenSet_0());
	private static final long[] mk_tokenSet_1() {
		long[] data = { 4269120L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_1 = new BitSet(mk_tokenSet_1());
	private static final long[] mk_tokenSet_2() {
		long[] data = { 4269056L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_2 = new BitSet(mk_tokenSet_2());
	private static final long[] mk_tokenSet_3() {
		long[] data = { 83886112L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_3 = new BitSet(mk_tokenSet_3());
	private static final long[] mk_tokenSet_4() {
		long[] data = { 88155168L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_4 = new BitSet(mk_tokenSet_4());
	private static final long[] mk_tokenSet_5() {
		long[] data = { 4096L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_5 = new BitSet(mk_tokenSet_5());
	private static final long[] mk_tokenSet_6() {
		long[] data = { 32768L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_6 = new BitSet(mk_tokenSet_6());
	private static final long[] mk_tokenSet_7() {
		long[] data = { 402685952L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_7 = new BitSet(mk_tokenSet_7());
	private static final long[] mk_tokenSet_8() {
		long[] data = { 1610645504L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_8 = new BitSet(mk_tokenSet_8());
	private static final long[] mk_tokenSet_9() {
		long[] data = { 8053096448L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_9 = new BitSet(mk_tokenSet_9());
	
	}
