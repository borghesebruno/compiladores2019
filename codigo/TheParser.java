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

	java.util.HashMap<String, String> mapaVar = new java.util.HashMap<String, String>();
	Programa p;
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
						  System.out.println("Variable list assembled...");
					
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
				if ((LA(1)==T_Id||LA(1)==LITERAL_leia||LA(1)==LITERAL_escreva)) {
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
			recover(ex,_tokenSet_2);
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
	
	public final void cmdLeia() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
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
			recover(ex,_tokenSet_4);
		}
	}
	
	public final void cmdEscr() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
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
			recover(ex,_tokenSet_4);
		}
	}
	
	public final void cmdAttr() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(T_Id);
			
				                if (mapaVar.get(LT(0).getText()) == null){
			throw new RuntimeException("ERROR ID "+LT(0).getText()+" not declared!");
			}
			
			match(T_attr);
			expr();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_4);
		}
	}
	
	public final void expr() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			termo();
			{
			_loop19:
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
					break _loop19;
				}
				
			} while (true);
			}
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_5);
		}
	}
	
	public final void termo() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			fator();
			{
			_loop23:
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
	
	public final void fator() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			switch ( LA(1)) {
			case T_Id:
			{
				match(T_Id);
				
					  if (mapaVar.get(LT(0).getText()) == null){
				throw new RuntimeException("ERROR ID "+LT(0).getText()+" not declared!");
				}
				
				break;
			}
			case T_num:
			{
				match(T_num);
				break;
			}
			case T_ap:
			{
				match(T_ap);
				expr();
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
	
	
	public static final String[] _tokenNames = {
		"<0>",
		"EOF",
		"<2>",
		"NULL_TREE_LOOKAHEAD",
		"\"programa\"",
		"\"fimprog\"",
		"\"declare\"",
		"\"inteiro\"",
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
		"T_soma",
		"T_subt",
		"T_mult",
		"T_divi",
		"T_num",
		"T_comm_i",
		"T_comm_f",
		"T_blank"
	};
	
	private static final long[] mk_tokenSet_0() {
		long[] data = { 2L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_0 = new BitSet(mk_tokenSet_0());
	private static final long[] mk_tokenSet_1() {
		long[] data = { 37440L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_1 = new BitSet(mk_tokenSet_1());
	private static final long[] mk_tokenSet_2() {
		long[] data = { 32L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_2 = new BitSet(mk_tokenSet_2());
	private static final long[] mk_tokenSet_3() {
		long[] data = { 37408L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_3 = new BitSet(mk_tokenSet_3());
	private static final long[] mk_tokenSet_4() {
		long[] data = { 2048L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_4 = new BitSet(mk_tokenSet_4());
	private static final long[] mk_tokenSet_5() {
		long[] data = { 18432L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_5 = new BitSet(mk_tokenSet_5());
	private static final long[] mk_tokenSet_6() {
		long[] data = { 804864L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_6 = new BitSet(mk_tokenSet_6());
	private static final long[] mk_tokenSet_7() {
		long[] data = { 3950592L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_7 = new BitSet(mk_tokenSet_7());
	
	}
