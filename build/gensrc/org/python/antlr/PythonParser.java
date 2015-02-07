// $ANTLR 3.1.3 Mar 17, 2009 19:23:44 /usr/local/lib/jython/grammar/Python.g 2015-02-07 14:38:22

package org.python.antlr;

import org.antlr.runtime.CommonToken;

import org.python.antlr.ParseException;
import org.python.antlr.PythonTree;
import org.python.antlr.ast.alias;
import org.python.antlr.ast.arguments;
import org.python.antlr.ast.Assert;
import org.python.antlr.ast.Assign;
import org.python.antlr.ast.Attribute;
import org.python.antlr.ast.AugAssign;
import org.python.antlr.ast.BinOp;
import org.python.antlr.ast.BoolOp;
import org.python.antlr.ast.boolopType;
import org.python.antlr.ast.Break;
import org.python.antlr.ast.Call;
import org.python.antlr.ast.ClassDef;
import org.python.antlr.ast.cmpopType;
import org.python.antlr.ast.Compare;
import org.python.antlr.ast.comprehension;
import org.python.antlr.ast.Context;
import org.python.antlr.ast.Continue;
import org.python.antlr.ast.Delete;
import org.python.antlr.ast.Dict;
import org.python.antlr.ast.DictComp;
import org.python.antlr.ast.Ellipsis;
import org.python.antlr.ast.ErrorMod;
import org.python.antlr.ast.ExceptHandler;
import org.python.antlr.ast.Exec;
import org.python.antlr.ast.Expr;
import org.python.antlr.ast.Expression;
import org.python.antlr.ast.expr_contextType;
import org.python.antlr.ast.ExtSlice;
import org.python.antlr.ast.For;
import org.python.antlr.ast.GeneratorExp;
import org.python.antlr.ast.Global;
import org.python.antlr.ast.If;
import org.python.antlr.ast.IfExp;
import org.python.antlr.ast.Import;
import org.python.antlr.ast.ImportFrom;
import org.python.antlr.ast.Index;
import org.python.antlr.ast.Interactive;
import org.python.antlr.ast.keyword;
import org.python.antlr.ast.ListComp;
import org.python.antlr.ast.Lambda;
import org.python.antlr.ast.Module;
import org.python.antlr.ast.Name;
import org.python.antlr.ast.Num;
import org.python.antlr.ast.operatorType;
import org.python.antlr.ast.Pass;
import org.python.antlr.ast.Print;
import org.python.antlr.ast.Raise;
import org.python.antlr.ast.Repr;
import org.python.antlr.ast.Return;
import org.python.antlr.ast.Set;
import org.python.antlr.ast.SetComp;
import org.python.antlr.ast.Slice;
import org.python.antlr.ast.Str;
import org.python.antlr.ast.Subscript;
import org.python.antlr.ast.TryExcept;
import org.python.antlr.ast.TryFinally;
import org.python.antlr.ast.Tuple;
import org.python.antlr.ast.unaryopType;
import org.python.antlr.ast.UnaryOp;
import org.python.antlr.ast.While;
import org.python.antlr.ast.With;
import org.python.antlr.ast.Yield;
import org.python.antlr.base.excepthandler;
import org.python.antlr.base.expr;
import org.python.antlr.base.mod;
import org.python.antlr.base.slice;
import org.python.antlr.base.stmt;
import org.python.core.Py;
import org.python.core.PyObject;
import org.python.core.PyString;
import org.python.core.PyUnicode;

import java.math.BigInteger;
import java.util.Collections;
import java.util.Iterator;
import java.util.ListIterator;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import org.antlr.runtime.tree.*;

/** Python 2.3.3 Grammar
 *
 *  Terence Parr and Loring Craymer
 *  February 2004
 *
 *  Converted to ANTLR v3 November 2005 by Terence Parr.
 *
 *  This grammar was derived automatically from the Python 2.3.3
 *  parser grammar to get a syntactically correct ANTLR grammar
 *  for Python.  Then Terence hand tweaked it to be semantically
 *  correct; i.e., removed lookahead issues etc...  It is LL(1)
 *  except for the (sometimes optional) trailing commas and semi-colons.
 *  It needs two symbols of lookahead in this case.
 *
 *  Starting with Loring's preliminary lexer for Python, I modified it
 *  to do my version of the whole nasty INDENT/DEDENT issue just so I
 *  could understand the problem better.  This grammar requires
 *  PythonTokenStream.java to work.  Also I used some rules from the
 *  semi-formal grammar on the web for Python (automatically
 *  translated to ANTLR format by an ANTLR grammar, naturally <grin>).
 *  The lexical rules for python are particularly nasty and it took me
 *  a long time to get it 'right'; i.e., think about it in the proper
 *  way.  Resist changing the lexer unless you've used ANTLR a lot. ;)
 *
 *  I (Terence) tested this by running it on the jython-2.1/Lib
 *  directory of 40k lines of Python.
 *
 *  REQUIRES ANTLR v3
 *
 *
 *  Updated the original parser for Python 2.5 features. The parser has been
 *  altered to produce an AST - the AST work started from tne newcompiler
 *  grammar from Jim Baker.  The current parsing and compiling strategy looks
 *  like this:
 *
 *  Python source->Python.g->AST (org/python/parser/ast/*)->CodeCompiler(ASM)->.class
 */
public class PythonParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "INDENT", "DEDENT", "TRAILBACKSLASH", "NEWLINE", "LEADING_WS", "NAME", "DOT", "PRINT", "AND", "AS", "ASSERT", "BREAK", "CLASS", "CONTINUE", "DEF", "DELETE", "ELIF", "EXCEPT", "EXEC", "FINALLY", "FROM", "FOR", "GLOBAL", "IF", "IMPORT", "IN", "IS", "LAMBDA", "NOT", "OR", "ORELSE", "PASS", "RAISE", "RETURN", "TRY", "WHILE", "WITH", "YIELD", "AT", "LPAREN", "RPAREN", "COLON", "ASSIGN", "COMMA", "STAR", "DOUBLESTAR", "SEMI", "PLUSEQUAL", "MINUSEQUAL", "STAREQUAL", "SLASHEQUAL", "PERCENTEQUAL", "AMPEREQUAL", "VBAREQUAL", "CIRCUMFLEXEQUAL", "LEFTSHIFTEQUAL", "RIGHTSHIFTEQUAL", "DOUBLESTAREQUAL", "DOUBLESLASHEQUAL", "RIGHTSHIFT", "LESS", "GREATER", "EQUAL", "GREATEREQUAL", "LESSEQUAL", "ALT_NOTEQUAL", "NOTEQUAL", "VBAR", "CIRCUMFLEX", "AMPER", "LEFTSHIFT", "PLUS", "MINUS", "SLASH", "PERCENT", "DOUBLESLASH", "TILDE", "LBRACK", "RBRACK", "LCURLY", "RCURLY", "BACKQUOTE", "INT", "LONGINT", "FLOAT", "COMPLEX", "STRING", "DIGITS", "Exponent", "TRIAPOS", "TRIQUOTE", "ESC", "COMMENT", "CONTINUED_LINE", "WS"
    };
    public static final int SLASHEQUAL=54;
    public static final int BACKQUOTE=85;
    public static final int STAR=48;
    public static final int CIRCUMFLEXEQUAL=58;
    public static final int WHILE=39;
    public static final int TRIAPOS=93;
    public static final int ORELSE=34;
    public static final int GREATEREQUAL=67;
    public static final int COMPLEX=89;
    public static final int NOT=32;
    public static final int EXCEPT=21;
    public static final int EOF=-1;
    public static final int BREAK=15;
    public static final int PASS=35;
    public static final int LEADING_WS=8;
    public static final int NOTEQUAL=70;
    public static final int MINUSEQUAL=52;
    public static final int VBAR=71;
    public static final int RPAREN=44;
    public static final int IMPORT=28;
    public static final int NAME=9;
    public static final int GREATER=65;
    public static final int DOUBLESTAREQUAL=61;
    public static final int RETURN=37;
    public static final int LESS=64;
    public static final int RAISE=36;
    public static final int COMMENT=96;
    public static final int RBRACK=82;
    public static final int LCURLY=83;
    public static final int INT=86;
    public static final int DELETE=19;
    public static final int RIGHTSHIFT=63;
    public static final int ASSERT=14;
    public static final int TRY=38;
    public static final int DOUBLESLASHEQUAL=62;
    public static final int ELIF=20;
    public static final int WS=98;
    public static final int VBAREQUAL=57;
    public static final int OR=33;
    public static final int LONGINT=87;
    public static final int FROM=24;
    public static final int PERCENTEQUAL=55;
    public static final int LESSEQUAL=68;
    public static final int DOUBLESLASH=79;
    public static final int CLASS=16;
    public static final int CONTINUED_LINE=97;
    public static final int LBRACK=81;
    public static final int DEF=18;
    public static final int DOUBLESTAR=49;
    public static final int ESC=95;
    public static final int DIGITS=91;
    public static final int Exponent=92;
    public static final int FOR=25;
    public static final int DEDENT=5;
    public static final int FLOAT=88;
    public static final int AND=12;
    public static final int RIGHTSHIFTEQUAL=60;
    public static final int INDENT=4;
    public static final int LPAREN=43;
    public static final int IF=27;
    public static final int PLUSEQUAL=51;
    public static final int AT=42;
    public static final int AS=13;
    public static final int SLASH=77;
    public static final int IN=29;
    public static final int CONTINUE=17;
    public static final int COMMA=47;
    public static final int IS=30;
    public static final int AMPER=73;
    public static final int EQUAL=66;
    public static final int YIELD=41;
    public static final int TILDE=80;
    public static final int LEFTSHIFTEQUAL=59;
    public static final int LEFTSHIFT=74;
    public static final int PLUS=75;
    public static final int LAMBDA=31;
    public static final int DOT=10;
    public static final int WITH=40;
    public static final int PERCENT=78;
    public static final int EXEC=22;
    public static final int MINUS=76;
    public static final int SEMI=50;
    public static final int PRINT=11;
    public static final int TRIQUOTE=94;
    public static final int COLON=45;
    public static final int TRAILBACKSLASH=6;
    public static final int NEWLINE=7;
    public static final int AMPEREQUAL=56;
    public static final int FINALLY=23;
    public static final int RCURLY=84;
    public static final int ASSIGN=46;
    public static final int GLOBAL=26;
    public static final int STAREQUAL=53;
    public static final int CIRCUMFLEX=72;
    public static final int STRING=90;
    public static final int ALT_NOTEQUAL=69;

    // delegates
    // delegators


        public PythonParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public PythonParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        
    protected TreeAdaptor adaptor = new CommonTreeAdaptor();

    public void setTreeAdaptor(TreeAdaptor adaptor) {
        this.adaptor = adaptor;
    }
    public TreeAdaptor getTreeAdaptor() {
        return adaptor;
    }

    public String[] getTokenNames() { return PythonParser.tokenNames; }
    public String getGrammarFileName() { return "/usr/local/lib/jython/grammar/Python.g"; }


        private ErrorHandler errorHandler;

        private GrammarActions actions = new GrammarActions();

        private String encoding;

        private boolean printFunction = false;
        private boolean unicodeLiterals = false;

        public void setErrorHandler(ErrorHandler eh) {
            this.errorHandler = eh;
            actions.setErrorHandler(eh);
        }

        protected Object recoverFromMismatchedToken(IntStream input, int ttype, BitSet follow)
            throws RecognitionException {

            Object o = errorHandler.recoverFromMismatchedToken(this, input, ttype, follow);
            if (o != null) {
                return o;
            }
            return super.recoverFromMismatchedToken(input, ttype, follow);
        }

        public PythonParser(TokenStream input, String encoding) {
            this(input);
            this.encoding = encoding;
        }

        @Override
        public void reportError(RecognitionException e) {
          // Update syntax error count and output error.
          super.reportError(e);
          errorHandler.reportError(this, e);
        }

        @Override
        public void displayRecognitionError(String[] tokenNames, RecognitionException e) {
            //Do nothing. We will handle error display elsewhere.
        }


    public static class single_input_return extends ParserRuleReturnScope {
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "single_input"
    // /usr/local/lib/jython/grammar/Python.g:295:1: single_input : ( ( NEWLINE )* EOF | simple_stmt ( NEWLINE )* EOF | compound_stmt ( NEWLINE )+ EOF );
    public final PythonParser.single_input_return single_input() throws RecognitionException {
        PythonParser.single_input_return retval = new PythonParser.single_input_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token NEWLINE1=null;
        Token EOF2=null;
        Token NEWLINE4=null;
        Token EOF5=null;
        Token NEWLINE7=null;
        Token EOF8=null;
        PythonParser.simple_stmt_return simple_stmt3 = null;

        PythonParser.compound_stmt_return compound_stmt6 = null;


        PythonTree NEWLINE1_tree=null;
        PythonTree EOF2_tree=null;
        PythonTree NEWLINE4_tree=null;
        PythonTree EOF5_tree=null;
        PythonTree NEWLINE7_tree=null;
        PythonTree EOF8_tree=null;


            mod mtype = null;

        try {
            // /usr/local/lib/jython/grammar/Python.g:302:5: ( ( NEWLINE )* EOF | simple_stmt ( NEWLINE )* EOF | compound_stmt ( NEWLINE )+ EOF )
            int alt4=3;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==EOF||LA4_0==NEWLINE) ) {
                alt4=1;
            }
            else if ( (LA4_0==NAME||LA4_0==NOT||LA4_0==LPAREN||(LA4_0>=PLUS && LA4_0<=MINUS)||(LA4_0>=TILDE && LA4_0<=LBRACK)||LA4_0==LCURLY||LA4_0==BACKQUOTE) ) {
                alt4=2;
            }
            else if ( (LA4_0==PRINT) && (((!printFunction)||(printFunction)))) {
                alt4=2;
            }
            else if ( ((LA4_0>=ASSERT && LA4_0<=BREAK)||LA4_0==CONTINUE||LA4_0==DELETE||LA4_0==EXEC||LA4_0==FROM||LA4_0==GLOBAL||LA4_0==IMPORT||LA4_0==LAMBDA||(LA4_0>=PASS && LA4_0<=RETURN)||LA4_0==YIELD||(LA4_0>=INT && LA4_0<=STRING)) ) {
                alt4=2;
            }
            else if ( (LA4_0==CLASS||LA4_0==DEF||LA4_0==FOR||LA4_0==IF||(LA4_0>=TRY && LA4_0<=WITH)||LA4_0==AT) ) {
                alt4=3;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;
            }
            switch (alt4) {
                case 1 :
                    // /usr/local/lib/jython/grammar/Python.g:302:7: ( NEWLINE )* EOF
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    // /usr/local/lib/jython/grammar/Python.g:302:7: ( NEWLINE )*
                    loop1:
                    do {
                        int alt1=2;
                        int LA1_0 = input.LA(1);

                        if ( (LA1_0==NEWLINE) ) {
                            alt1=1;
                        }


                        switch (alt1) {
                    	case 1 :
                    	    // /usr/local/lib/jython/grammar/Python.g:302:7: NEWLINE
                    	    {
                    	    NEWLINE1=(Token)match(input,NEWLINE,FOLLOW_NEWLINE_in_single_input118); if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) {
                    	    NEWLINE1_tree = (PythonTree)adaptor.create(NEWLINE1);
                    	    adaptor.addChild(root_0, NEWLINE1_tree);
                    	    }

                    	    }
                    	    break;

                    	default :
                    	    break loop1;
                        }
                    } while (true);

                    EOF2=(Token)match(input,EOF,FOLLOW_EOF_in_single_input121); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    EOF2_tree = (PythonTree)adaptor.create(EOF2);
                    adaptor.addChild(root_0, EOF2_tree);
                    }
                    if ( state.backtracking==0 ) {

                              mtype = new Interactive(((Token)retval.start), new ArrayList<stmt>());
                            
                    }

                    }
                    break;
                case 2 :
                    // /usr/local/lib/jython/grammar/Python.g:306:7: simple_stmt ( NEWLINE )* EOF
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    pushFollow(FOLLOW_simple_stmt_in_single_input137);
                    simple_stmt3=simple_stmt();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, simple_stmt3.getTree());
                    // /usr/local/lib/jython/grammar/Python.g:306:19: ( NEWLINE )*
                    loop2:
                    do {
                        int alt2=2;
                        int LA2_0 = input.LA(1);

                        if ( (LA2_0==NEWLINE) ) {
                            alt2=1;
                        }


                        switch (alt2) {
                    	case 1 :
                    	    // /usr/local/lib/jython/grammar/Python.g:306:19: NEWLINE
                    	    {
                    	    NEWLINE4=(Token)match(input,NEWLINE,FOLLOW_NEWLINE_in_single_input139); if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) {
                    	    NEWLINE4_tree = (PythonTree)adaptor.create(NEWLINE4);
                    	    adaptor.addChild(root_0, NEWLINE4_tree);
                    	    }

                    	    }
                    	    break;

                    	default :
                    	    break loop2;
                        }
                    } while (true);

                    EOF5=(Token)match(input,EOF,FOLLOW_EOF_in_single_input142); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    EOF5_tree = (PythonTree)adaptor.create(EOF5);
                    adaptor.addChild(root_0, EOF5_tree);
                    }
                    if ( state.backtracking==0 ) {

                              mtype = new Interactive(((Token)retval.start), actions.castStmts((simple_stmt3!=null?simple_stmt3.stypes:null)));
                            
                    }

                    }
                    break;
                case 3 :
                    // /usr/local/lib/jython/grammar/Python.g:310:7: compound_stmt ( NEWLINE )+ EOF
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    pushFollow(FOLLOW_compound_stmt_in_single_input158);
                    compound_stmt6=compound_stmt();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, compound_stmt6.getTree());
                    // /usr/local/lib/jython/grammar/Python.g:310:21: ( NEWLINE )+
                    int cnt3=0;
                    loop3:
                    do {
                        int alt3=2;
                        int LA3_0 = input.LA(1);

                        if ( (LA3_0==NEWLINE) ) {
                            alt3=1;
                        }


                        switch (alt3) {
                    	case 1 :
                    	    // /usr/local/lib/jython/grammar/Python.g:310:21: NEWLINE
                    	    {
                    	    NEWLINE7=(Token)match(input,NEWLINE,FOLLOW_NEWLINE_in_single_input160); if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) {
                    	    NEWLINE7_tree = (PythonTree)adaptor.create(NEWLINE7);
                    	    adaptor.addChild(root_0, NEWLINE7_tree);
                    	    }

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt3 >= 1 ) break loop3;
                    	    if (state.backtracking>0) {state.failed=true; return retval;}
                                EarlyExitException eee =
                                    new EarlyExitException(3, input);
                                throw eee;
                        }
                        cnt3++;
                    } while (true);

                    EOF8=(Token)match(input,EOF,FOLLOW_EOF_in_single_input163); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    EOF8_tree = (PythonTree)adaptor.create(EOF8);
                    adaptor.addChild(root_0, EOF8_tree);
                    }
                    if ( state.backtracking==0 ) {

                              mtype = new Interactive(((Token)retval.start), actions.castStmts((compound_stmt6!=null?((PythonTree)compound_stmt6.tree):null)));
                            
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {

                  retval.tree = mtype;

            }
        }
        catch (RecognitionException re) {

                    reportError(re);
                    errorHandler.recover(this, input,re);
                    PythonTree badNode = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
                    retval.tree = new ErrorMod(badNode);
                
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "single_input"

    public static class file_input_return extends ParserRuleReturnScope {
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "file_input"
    // /usr/local/lib/jython/grammar/Python.g:324:1: file_input : ( NEWLINE | stmt )* EOF ;
    public final PythonParser.file_input_return file_input() throws RecognitionException {
        PythonParser.file_input_return retval = new PythonParser.file_input_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token NEWLINE9=null;
        Token EOF11=null;
        PythonParser.stmt_return stmt10 = null;


        PythonTree NEWLINE9_tree=null;
        PythonTree EOF11_tree=null;


            mod mtype = null;
            List stypes = new ArrayList();

        try {
            // /usr/local/lib/jython/grammar/Python.g:342:5: ( ( NEWLINE | stmt )* EOF )
            // /usr/local/lib/jython/grammar/Python.g:342:7: ( NEWLINE | stmt )* EOF
            {
            root_0 = (PythonTree)adaptor.nil();

            // /usr/local/lib/jython/grammar/Python.g:342:7: ( NEWLINE | stmt )*
            loop5:
            do {
                int alt5=3;
                int LA5_0 = input.LA(1);

                if ( (LA5_0==NEWLINE) ) {
                    alt5=1;
                }
                else if ( (LA5_0==NAME||LA5_0==NOT||LA5_0==LPAREN||(LA5_0>=PLUS && LA5_0<=MINUS)||(LA5_0>=TILDE && LA5_0<=LBRACK)||LA5_0==LCURLY||LA5_0==BACKQUOTE) ) {
                    alt5=2;
                }
                else if ( (LA5_0==PRINT) && (((!printFunction)||(printFunction)))) {
                    alt5=2;
                }
                else if ( ((LA5_0>=ASSERT && LA5_0<=DELETE)||LA5_0==EXEC||(LA5_0>=FROM && LA5_0<=IMPORT)||LA5_0==LAMBDA||(LA5_0>=PASS && LA5_0<=AT)||(LA5_0>=INT && LA5_0<=STRING)) ) {
                    alt5=2;
                }


                switch (alt5) {
            	case 1 :
            	    // /usr/local/lib/jython/grammar/Python.g:342:8: NEWLINE
            	    {
            	    NEWLINE9=(Token)match(input,NEWLINE,FOLLOW_NEWLINE_in_file_input215); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    NEWLINE9_tree = (PythonTree)adaptor.create(NEWLINE9);
            	    adaptor.addChild(root_0, NEWLINE9_tree);
            	    }

            	    }
            	    break;
            	case 2 :
            	    // /usr/local/lib/jython/grammar/Python.g:343:9: stmt
            	    {
            	    pushFollow(FOLLOW_stmt_in_file_input225);
            	    stmt10=stmt();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, stmt10.getTree());
            	    if ( state.backtracking==0 ) {

            	                if ((stmt10!=null?stmt10.stypes:null) != null) {
            	                    stypes.addAll((stmt10!=null?stmt10.stypes:null));
            	                }
            	            
            	    }

            	    }
            	    break;

            	default :
            	    break loop5;
                }
            } while (true);

            EOF11=(Token)match(input,EOF,FOLLOW_EOF_in_file_input244); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            EOF11_tree = (PythonTree)adaptor.create(EOF11);
            adaptor.addChild(root_0, EOF11_tree);
            }
            if ( state.backtracking==0 ) {

                           mtype = new Module(((Token)retval.start), actions.castStmts(stypes));
                       
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {

                  if (!stypes.isEmpty()) {
                      //The EOF token messes up the end offsets, so set them manually.
                      //XXX: this may no longer be true now that PythonTokenSource is
                      //     adjusting EOF offsets -- but needs testing before I remove
                      //     this.
                      PythonTree stop = (PythonTree)stypes.get(stypes.size() -1);
                      mtype.setCharStopIndex(stop.getCharStopIndex());
                      mtype.setTokenStopIndex(stop.getTokenStopIndex());
                  }

                  retval.tree = mtype;

            }
        }
        catch (RecognitionException re) {

                    reportError(re);
                    errorHandler.recover(this, input,re);
                    PythonTree badNode = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
                    retval.tree = new ErrorMod(badNode);
                
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "file_input"

    public static class eval_input_return extends ParserRuleReturnScope {
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "eval_input"
    // /usr/local/lib/jython/grammar/Python.g:363:1: eval_input : ( LEADING_WS )? ( NEWLINE )* testlist[expr_contextType.Load] ( NEWLINE )* EOF ;
    public final PythonParser.eval_input_return eval_input() throws RecognitionException {
        PythonParser.eval_input_return retval = new PythonParser.eval_input_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token LEADING_WS12=null;
        Token NEWLINE13=null;
        Token NEWLINE15=null;
        Token EOF16=null;
        PythonParser.testlist_return testlist14 = null;


        PythonTree LEADING_WS12_tree=null;
        PythonTree NEWLINE13_tree=null;
        PythonTree NEWLINE15_tree=null;
        PythonTree EOF16_tree=null;


            mod mtype = null;

        try {
            // /usr/local/lib/jython/grammar/Python.g:370:5: ( ( LEADING_WS )? ( NEWLINE )* testlist[expr_contextType.Load] ( NEWLINE )* EOF )
            // /usr/local/lib/jython/grammar/Python.g:370:7: ( LEADING_WS )? ( NEWLINE )* testlist[expr_contextType.Load] ( NEWLINE )* EOF
            {
            root_0 = (PythonTree)adaptor.nil();

            // /usr/local/lib/jython/grammar/Python.g:370:7: ( LEADING_WS )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==LEADING_WS) ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // /usr/local/lib/jython/grammar/Python.g:370:7: LEADING_WS
                    {
                    LEADING_WS12=(Token)match(input,LEADING_WS,FOLLOW_LEADING_WS_in_eval_input298); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    LEADING_WS12_tree = (PythonTree)adaptor.create(LEADING_WS12);
                    adaptor.addChild(root_0, LEADING_WS12_tree);
                    }

                    }
                    break;

            }

            // /usr/local/lib/jython/grammar/Python.g:370:19: ( NEWLINE )*
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( (LA7_0==NEWLINE) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // /usr/local/lib/jython/grammar/Python.g:370:20: NEWLINE
            	    {
            	    NEWLINE13=(Token)match(input,NEWLINE,FOLLOW_NEWLINE_in_eval_input302); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    NEWLINE13_tree = (PythonTree)adaptor.create(NEWLINE13);
            	    adaptor.addChild(root_0, NEWLINE13_tree);
            	    }

            	    }
            	    break;

            	default :
            	    break loop7;
                }
            } while (true);

            pushFollow(FOLLOW_testlist_in_eval_input306);
            testlist14=testlist(expr_contextType.Load);

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, testlist14.getTree());
            // /usr/local/lib/jython/grammar/Python.g:370:62: ( NEWLINE )*
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( (LA8_0==NEWLINE) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // /usr/local/lib/jython/grammar/Python.g:370:63: NEWLINE
            	    {
            	    NEWLINE15=(Token)match(input,NEWLINE,FOLLOW_NEWLINE_in_eval_input310); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    NEWLINE15_tree = (PythonTree)adaptor.create(NEWLINE15);
            	    adaptor.addChild(root_0, NEWLINE15_tree);
            	    }

            	    }
            	    break;

            	default :
            	    break loop8;
                }
            } while (true);

            EOF16=(Token)match(input,EOF,FOLLOW_EOF_in_eval_input314); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            EOF16_tree = (PythonTree)adaptor.create(EOF16);
            adaptor.addChild(root_0, EOF16_tree);
            }
            if ( state.backtracking==0 ) {

                      mtype = new Expression(((Token)retval.start), actions.castExpr((testlist14!=null?((PythonTree)testlist14.tree):null)));
                    
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {

                  retval.tree = mtype;

            }
        }
        catch (RecognitionException re) {

                    reportError(re);
                    errorHandler.recover(this, input,re);
                    PythonTree badNode = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
                    retval.tree = new ErrorMod(badNode);
                
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "eval_input"

    public static class dotted_attr_return extends ParserRuleReturnScope {
        public expr etype;
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "dotted_attr"
    // /usr/local/lib/jython/grammar/Python.g:385:1: dotted_attr returns [expr etype] : n1= NAME ( ( DOT n2+= NAME )+ | ) ;
    public final PythonParser.dotted_attr_return dotted_attr() throws RecognitionException {
        PythonParser.dotted_attr_return retval = new PythonParser.dotted_attr_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token n1=null;
        Token DOT17=null;
        Token n2=null;
        List list_n2=null;

        PythonTree n1_tree=null;
        PythonTree DOT17_tree=null;
        PythonTree n2_tree=null;

        try {
            // /usr/local/lib/jython/grammar/Python.g:387:5: (n1= NAME ( ( DOT n2+= NAME )+ | ) )
            // /usr/local/lib/jython/grammar/Python.g:387:7: n1= NAME ( ( DOT n2+= NAME )+ | )
            {
            root_0 = (PythonTree)adaptor.nil();

            n1=(Token)match(input,NAME,FOLLOW_NAME_in_dotted_attr366); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            n1_tree = (PythonTree)adaptor.create(n1);
            adaptor.addChild(root_0, n1_tree);
            }
            // /usr/local/lib/jython/grammar/Python.g:388:7: ( ( DOT n2+= NAME )+ | )
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==DOT) ) {
                alt10=1;
            }
            else if ( (LA10_0==NEWLINE||LA10_0==LPAREN) ) {
                alt10=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 10, 0, input);

                throw nvae;
            }
            switch (alt10) {
                case 1 :
                    // /usr/local/lib/jython/grammar/Python.g:388:9: ( DOT n2+= NAME )+
                    {
                    // /usr/local/lib/jython/grammar/Python.g:388:9: ( DOT n2+= NAME )+
                    int cnt9=0;
                    loop9:
                    do {
                        int alt9=2;
                        int LA9_0 = input.LA(1);

                        if ( (LA9_0==DOT) ) {
                            alt9=1;
                        }


                        switch (alt9) {
                    	case 1 :
                    	    // /usr/local/lib/jython/grammar/Python.g:388:10: DOT n2+= NAME
                    	    {
                    	    DOT17=(Token)match(input,DOT,FOLLOW_DOT_in_dotted_attr377); if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) {
                    	    DOT17_tree = (PythonTree)adaptor.create(DOT17);
                    	    adaptor.addChild(root_0, DOT17_tree);
                    	    }
                    	    n2=(Token)match(input,NAME,FOLLOW_NAME_in_dotted_attr381); if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) {
                    	    n2_tree = (PythonTree)adaptor.create(n2);
                    	    adaptor.addChild(root_0, n2_tree);
                    	    }
                    	    if (list_n2==null) list_n2=new ArrayList();
                    	    list_n2.add(n2);


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt9 >= 1 ) break loop9;
                    	    if (state.backtracking>0) {state.failed=true; return retval;}
                                EarlyExitException eee =
                                    new EarlyExitException(9, input);
                                throw eee;
                        }
                        cnt9++;
                    } while (true);

                    if ( state.backtracking==0 ) {

                                  retval.etype = actions.makeDottedAttr(n1, list_n2);
                              
                    }

                    }
                    break;
                case 2 :
                    // /usr/local/lib/jython/grammar/Python.g:393:9: 
                    {
                    if ( state.backtracking==0 ) {

                                  retval.etype = actions.makeNameNode(n1);
                              
                    }

                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        catch (RecognitionException re) {
            reportError(re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "dotted_attr"

    public static class name_or_print_return extends ParserRuleReturnScope {
        public Token tok;
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "name_or_print"
    // /usr/local/lib/jython/grammar/Python.g:401:1: name_or_print returns [Token tok] : ( NAME | {...}? => PRINT );
    public final PythonParser.name_or_print_return name_or_print() throws RecognitionException {
        PythonParser.name_or_print_return retval = new PythonParser.name_or_print_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token NAME18=null;
        Token PRINT19=null;

        PythonTree NAME18_tree=null;
        PythonTree PRINT19_tree=null;

        try {
            // /usr/local/lib/jython/grammar/Python.g:403:5: ( NAME | {...}? => PRINT )
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==NAME) ) {
                alt11=1;
            }
            else if ( (LA11_0==PRINT) && ((printFunction))) {
                alt11=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 11, 0, input);

                throw nvae;
            }
            switch (alt11) {
                case 1 :
                    // /usr/local/lib/jython/grammar/Python.g:403:7: NAME
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    NAME18=(Token)match(input,NAME,FOLLOW_NAME_in_name_or_print446); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    NAME18_tree = (PythonTree)adaptor.create(NAME18);
                    adaptor.addChild(root_0, NAME18_tree);
                    }
                    if ( state.backtracking==0 ) {

                              retval.tok = ((Token)retval.start);
                          
                    }

                    }
                    break;
                case 2 :
                    // /usr/local/lib/jython/grammar/Python.g:406:7: {...}? => PRINT
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    if ( !((printFunction)) ) {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        throw new FailedPredicateException(input, "name_or_print", "printFunction");
                    }
                    PRINT19=(Token)match(input,PRINT,FOLLOW_PRINT_in_name_or_print460); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    PRINT19_tree = (PythonTree)adaptor.create(PRINT19);
                    adaptor.addChild(root_0, PRINT19_tree);
                    }
                    if ( state.backtracking==0 ) {

                              retval.tok = ((Token)retval.start);
                          
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        catch (RecognitionException re) {
            reportError(re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "name_or_print"

    public static class attr_return extends ParserRuleReturnScope {
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "attr"
    // /usr/local/lib/jython/grammar/Python.g:415:1: attr : ( NAME | AND | AS | ASSERT | BREAK | CLASS | CONTINUE | DEF | DELETE | ELIF | EXCEPT | EXEC | FINALLY | FROM | FOR | GLOBAL | IF | IMPORT | IN | IS | LAMBDA | NOT | OR | ORELSE | PASS | PRINT | RAISE | RETURN | TRY | WHILE | WITH | YIELD );
    public final PythonParser.attr_return attr() throws RecognitionException {
        PythonParser.attr_return retval = new PythonParser.attr_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token set20=null;

        PythonTree set20_tree=null;

        try {
            // /usr/local/lib/jython/grammar/Python.g:416:5: ( NAME | AND | AS | ASSERT | BREAK | CLASS | CONTINUE | DEF | DELETE | ELIF | EXCEPT | EXEC | FINALLY | FROM | FOR | GLOBAL | IF | IMPORT | IN | IS | LAMBDA | NOT | OR | ORELSE | PASS | PRINT | RAISE | RETURN | TRY | WHILE | WITH | YIELD )
            // /usr/local/lib/jython/grammar/Python.g:
            {
            root_0 = (PythonTree)adaptor.nil();

            set20=(Token)input.LT(1);
            if ( input.LA(1)==NAME||(input.LA(1)>=PRINT && input.LA(1)<=YIELD) ) {
                input.consume();
                if ( state.backtracking==0 ) adaptor.addChild(root_0, (PythonTree)adaptor.create(set20));
                state.errorRecovery=false;state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        catch (RecognitionException re) {
            reportError(re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "attr"

    public static class decorator_return extends ParserRuleReturnScope {
        public expr etype;
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "decorator"
    // /usr/local/lib/jython/grammar/Python.g:451:1: decorator returns [expr etype] : AT dotted_attr ( LPAREN ( arglist | ) RPAREN | ) NEWLINE ;
    public final PythonParser.decorator_return decorator() throws RecognitionException {
        PythonParser.decorator_return retval = new PythonParser.decorator_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token AT21=null;
        Token LPAREN23=null;
        Token RPAREN25=null;
        Token NEWLINE26=null;
        PythonParser.dotted_attr_return dotted_attr22 = null;

        PythonParser.arglist_return arglist24 = null;


        PythonTree AT21_tree=null;
        PythonTree LPAREN23_tree=null;
        PythonTree RPAREN25_tree=null;
        PythonTree NEWLINE26_tree=null;

        try {
            // /usr/local/lib/jython/grammar/Python.g:456:5: ( AT dotted_attr ( LPAREN ( arglist | ) RPAREN | ) NEWLINE )
            // /usr/local/lib/jython/grammar/Python.g:456:7: AT dotted_attr ( LPAREN ( arglist | ) RPAREN | ) NEWLINE
            {
            root_0 = (PythonTree)adaptor.nil();

            AT21=(Token)match(input,AT,FOLLOW_AT_in_decorator762); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            AT21_tree = (PythonTree)adaptor.create(AT21);
            adaptor.addChild(root_0, AT21_tree);
            }
            pushFollow(FOLLOW_dotted_attr_in_decorator764);
            dotted_attr22=dotted_attr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, dotted_attr22.getTree());
            // /usr/local/lib/jython/grammar/Python.g:457:5: ( LPAREN ( arglist | ) RPAREN | )
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==LPAREN) ) {
                alt13=1;
            }
            else if ( (LA13_0==NEWLINE) ) {
                alt13=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 13, 0, input);

                throw nvae;
            }
            switch (alt13) {
                case 1 :
                    // /usr/local/lib/jython/grammar/Python.g:457:7: LPAREN ( arglist | ) RPAREN
                    {
                    LPAREN23=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_decorator772); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    LPAREN23_tree = (PythonTree)adaptor.create(LPAREN23);
                    adaptor.addChild(root_0, LPAREN23_tree);
                    }
                    // /usr/local/lib/jython/grammar/Python.g:458:7: ( arglist | )
                    int alt12=2;
                    int LA12_0 = input.LA(1);

                    if ( (LA12_0==NAME||LA12_0==NOT||LA12_0==LPAREN||(LA12_0>=PLUS && LA12_0<=MINUS)||(LA12_0>=TILDE && LA12_0<=LBRACK)||LA12_0==LCURLY||LA12_0==BACKQUOTE) ) {
                        alt12=1;
                    }
                    else if ( (LA12_0==PRINT) && ((printFunction))) {
                        alt12=1;
                    }
                    else if ( (LA12_0==LAMBDA||(LA12_0>=STAR && LA12_0<=DOUBLESTAR)||(LA12_0>=INT && LA12_0<=STRING)) ) {
                        alt12=1;
                    }
                    else if ( (LA12_0==RPAREN) ) {
                        alt12=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 12, 0, input);

                        throw nvae;
                    }
                    switch (alt12) {
                        case 1 :
                            // /usr/local/lib/jython/grammar/Python.g:458:9: arglist
                            {
                            pushFollow(FOLLOW_arglist_in_decorator782);
                            arglist24=arglist();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) adaptor.addChild(root_0, arglist24.getTree());
                            if ( state.backtracking==0 ) {

                                          retval.etype = actions.makeCall(LPAREN23, (dotted_attr22!=null?dotted_attr22.etype:null), (arglist24!=null?arglist24.args:null), (arglist24!=null?arglist24.keywords:null),
                                                   (arglist24!=null?arglist24.starargs:null), (arglist24!=null?arglist24.kwargs:null));
                                      
                            }

                            }
                            break;
                        case 2 :
                            // /usr/local/lib/jython/grammar/Python.g:464:9: 
                            {
                            if ( state.backtracking==0 ) {

                                          retval.etype = actions.makeCall(LPAREN23, (dotted_attr22!=null?dotted_attr22.etype:null));
                                      
                            }

                            }
                            break;

                    }

                    RPAREN25=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_decorator826); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    RPAREN25_tree = (PythonTree)adaptor.create(RPAREN25);
                    adaptor.addChild(root_0, RPAREN25_tree);
                    }

                    }
                    break;
                case 2 :
                    // /usr/local/lib/jython/grammar/Python.g:470:7: 
                    {
                    if ( state.backtracking==0 ) {

                                retval.etype = (dotted_attr22!=null?dotted_attr22.etype:null);
                            
                    }

                    }
                    break;

            }

            NEWLINE26=(Token)match(input,NEWLINE,FOLLOW_NEWLINE_in_decorator848); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            NEWLINE26_tree = (PythonTree)adaptor.create(NEWLINE26);
            adaptor.addChild(root_0, NEWLINE26_tree);
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {

                 retval.tree = retval.etype;

            }
        }

        catch (RecognitionException re) {
            reportError(re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "decorator"

    public static class decorators_return extends ParserRuleReturnScope {
        public List etypes;
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "decorators"
    // /usr/local/lib/jython/grammar/Python.g:477:1: decorators returns [List etypes] : (d+= decorator )+ ;
    public final PythonParser.decorators_return decorators() throws RecognitionException {
        PythonParser.decorators_return retval = new PythonParser.decorators_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        List list_d=null;
        PythonParser.decorator_return d = null;
         d = null;

        try {
            // /usr/local/lib/jython/grammar/Python.g:479:5: ( (d+= decorator )+ )
            // /usr/local/lib/jython/grammar/Python.g:479:7: (d+= decorator )+
            {
            root_0 = (PythonTree)adaptor.nil();

            // /usr/local/lib/jython/grammar/Python.g:479:8: (d+= decorator )+
            int cnt14=0;
            loop14:
            do {
                int alt14=2;
                int LA14_0 = input.LA(1);

                if ( (LA14_0==AT) ) {
                    alt14=1;
                }


                switch (alt14) {
            	case 1 :
            	    // /usr/local/lib/jython/grammar/Python.g:479:8: d+= decorator
            	    {
            	    pushFollow(FOLLOW_decorator_in_decorators876);
            	    d=decorator();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, d.getTree());
            	    if (list_d==null) list_d=new ArrayList();
            	    list_d.add(d.getTree());


            	    }
            	    break;

            	default :
            	    if ( cnt14 >= 1 ) break loop14;
            	    if (state.backtracking>0) {state.failed=true; return retval;}
                        EarlyExitException eee =
                            new EarlyExitException(14, input);
                        throw eee;
                }
                cnt14++;
            } while (true);

            if ( state.backtracking==0 ) {

                        retval.etypes = list_d;
                    
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        catch (RecognitionException re) {
            reportError(re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "decorators"

    public static class funcdef_return extends ParserRuleReturnScope {
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "funcdef"
    // /usr/local/lib/jython/grammar/Python.g:486:1: funcdef : ( decorators )? DEF name_or_print parameters COLON suite[false] ;
    public final PythonParser.funcdef_return funcdef() throws RecognitionException {
        PythonParser.funcdef_return retval = new PythonParser.funcdef_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token DEF28=null;
        Token COLON31=null;
        PythonParser.decorators_return decorators27 = null;

        PythonParser.name_or_print_return name_or_print29 = null;

        PythonParser.parameters_return parameters30 = null;

        PythonParser.suite_return suite32 = null;


        PythonTree DEF28_tree=null;
        PythonTree COLON31_tree=null;


            stmt stype = null;

        try {
            // /usr/local/lib/jython/grammar/Python.g:494:5: ( ( decorators )? DEF name_or_print parameters COLON suite[false] )
            // /usr/local/lib/jython/grammar/Python.g:494:7: ( decorators )? DEF name_or_print parameters COLON suite[false]
            {
            root_0 = (PythonTree)adaptor.nil();

            // /usr/local/lib/jython/grammar/Python.g:494:7: ( decorators )?
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==AT) ) {
                alt15=1;
            }
            switch (alt15) {
                case 1 :
                    // /usr/local/lib/jython/grammar/Python.g:494:7: decorators
                    {
                    pushFollow(FOLLOW_decorators_in_funcdef914);
                    decorators27=decorators();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, decorators27.getTree());

                    }
                    break;

            }

            DEF28=(Token)match(input,DEF,FOLLOW_DEF_in_funcdef917); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            DEF28_tree = (PythonTree)adaptor.create(DEF28);
            adaptor.addChild(root_0, DEF28_tree);
            }
            pushFollow(FOLLOW_name_or_print_in_funcdef919);
            name_or_print29=name_or_print();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, name_or_print29.getTree());
            pushFollow(FOLLOW_parameters_in_funcdef921);
            parameters30=parameters();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, parameters30.getTree());
            COLON31=(Token)match(input,COLON,FOLLOW_COLON_in_funcdef923); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            COLON31_tree = (PythonTree)adaptor.create(COLON31);
            adaptor.addChild(root_0, COLON31_tree);
            }
            pushFollow(FOLLOW_suite_in_funcdef925);
            suite32=suite(false);

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, suite32.getTree());
            if ( state.backtracking==0 ) {

                      Token t = DEF28;
                      if ((decorators27!=null?((Token)decorators27.start):null) != null) {
                          t = (decorators27!=null?((Token)decorators27.start):null);
                      }
                      stype = actions.makeFuncdef(t, (name_or_print29!=null?((Token)name_or_print29.start):null), (parameters30!=null?parameters30.args:null), (suite32!=null?suite32.stypes:null), (decorators27!=null?decorators27.etypes:null));
                  
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {

                  retval.tree = stype;

            }
        }

        catch (RecognitionException re) {
            reportError(re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "funcdef"

    public static class parameters_return extends ParserRuleReturnScope {
        public arguments args;
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "parameters"
    // /usr/local/lib/jython/grammar/Python.g:505:1: parameters returns [arguments args] : LPAREN ( varargslist | ) RPAREN ;
    public final PythonParser.parameters_return parameters() throws RecognitionException {
        PythonParser.parameters_return retval = new PythonParser.parameters_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token LPAREN33=null;
        Token RPAREN35=null;
        PythonParser.varargslist_return varargslist34 = null;


        PythonTree LPAREN33_tree=null;
        PythonTree RPAREN35_tree=null;

        try {
            // /usr/local/lib/jython/grammar/Python.g:507:5: ( LPAREN ( varargslist | ) RPAREN )
            // /usr/local/lib/jython/grammar/Python.g:507:7: LPAREN ( varargslist | ) RPAREN
            {
            root_0 = (PythonTree)adaptor.nil();

            LPAREN33=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_parameters958); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            LPAREN33_tree = (PythonTree)adaptor.create(LPAREN33);
            adaptor.addChild(root_0, LPAREN33_tree);
            }
            // /usr/local/lib/jython/grammar/Python.g:508:7: ( varargslist | )
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==NAME||LA16_0==LPAREN||(LA16_0>=STAR && LA16_0<=DOUBLESTAR)) ) {
                alt16=1;
            }
            else if ( (LA16_0==RPAREN) ) {
                alt16=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 16, 0, input);

                throw nvae;
            }
            switch (alt16) {
                case 1 :
                    // /usr/local/lib/jython/grammar/Python.g:508:8: varargslist
                    {
                    pushFollow(FOLLOW_varargslist_in_parameters967);
                    varargslist34=varargslist();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, varargslist34.getTree());
                    if ( state.backtracking==0 ) {

                                    retval.args = (varargslist34!=null?varargslist34.args:null);
                              
                    }

                    }
                    break;
                case 2 :
                    // /usr/local/lib/jython/grammar/Python.g:513:9: 
                    {
                    if ( state.backtracking==0 ) {

                                  retval.args = new arguments(((Token)retval.start), new ArrayList<expr>(), (Name)null, null, new ArrayList<expr>());
                              
                    }

                    }
                    break;

            }

            RPAREN35=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_parameters1011); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            RPAREN35_tree = (PythonTree)adaptor.create(RPAREN35);
            adaptor.addChild(root_0, RPAREN35_tree);
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        catch (RecognitionException re) {
            reportError(re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "parameters"

    public static class defparameter_return extends ParserRuleReturnScope {
        public expr etype;
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "defparameter"
    // /usr/local/lib/jython/grammar/Python.g:521:1: defparameter[List defaults] returns [expr etype] : fpdef[expr_contextType.Param] ( ASSIGN test[expr_contextType.Load] )? ;
    public final PythonParser.defparameter_return defparameter(List defaults) throws RecognitionException {
        PythonParser.defparameter_return retval = new PythonParser.defparameter_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token ASSIGN37=null;
        PythonParser.fpdef_return fpdef36 = null;

        PythonParser.test_return test38 = null;


        PythonTree ASSIGN37_tree=null;

        try {
            // /usr/local/lib/jython/grammar/Python.g:526:5: ( fpdef[expr_contextType.Param] ( ASSIGN test[expr_contextType.Load] )? )
            // /usr/local/lib/jython/grammar/Python.g:526:7: fpdef[expr_contextType.Param] ( ASSIGN test[expr_contextType.Load] )?
            {
            root_0 = (PythonTree)adaptor.nil();

            pushFollow(FOLLOW_fpdef_in_defparameter1044);
            fpdef36=fpdef(expr_contextType.Param);

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, fpdef36.getTree());
            // /usr/local/lib/jython/grammar/Python.g:526:37: ( ASSIGN test[expr_contextType.Load] )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==ASSIGN) ) {
                alt17=1;
            }
            switch (alt17) {
                case 1 :
                    // /usr/local/lib/jython/grammar/Python.g:526:38: ASSIGN test[expr_contextType.Load]
                    {
                    ASSIGN37=(Token)match(input,ASSIGN,FOLLOW_ASSIGN_in_defparameter1048); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    ASSIGN37_tree = (PythonTree)adaptor.create(ASSIGN37);
                    adaptor.addChild(root_0, ASSIGN37_tree);
                    }
                    pushFollow(FOLLOW_test_in_defparameter1050);
                    test38=test(expr_contextType.Load);

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, test38.getTree());

                    }
                    break;

            }

            if ( state.backtracking==0 ) {

                        retval.etype = actions.castExpr((fpdef36!=null?((PythonTree)fpdef36.tree):null));
                        if (ASSIGN37 != null) {
                            defaults.add((test38!=null?((PythonTree)test38.tree):null));
                        } else if (!defaults.isEmpty()) {
                            throw new ParseException("non-default argument follows default argument", (fpdef36!=null?((PythonTree)fpdef36.tree):null));
                        }
                    
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {

                 retval.tree = retval.etype;

            }
        }

        catch (RecognitionException re) {
            reportError(re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "defparameter"

    public static class varargslist_return extends ParserRuleReturnScope {
        public arguments args;
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "varargslist"
    // /usr/local/lib/jython/grammar/Python.g:540:1: varargslist returns [arguments args] : (d+= defparameter[defaults] ( options {greedy=true; } : COMMA d+= defparameter[defaults] )* ( COMMA ( STAR starargs= NAME ( COMMA DOUBLESTAR kwargs= NAME )? | DOUBLESTAR kwargs= NAME )? )? | STAR starargs= NAME ( COMMA DOUBLESTAR kwargs= NAME )? | DOUBLESTAR kwargs= NAME );
    public final PythonParser.varargslist_return varargslist() throws RecognitionException {
        PythonParser.varargslist_return retval = new PythonParser.varargslist_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token starargs=null;
        Token kwargs=null;
        Token COMMA39=null;
        Token COMMA40=null;
        Token STAR41=null;
        Token COMMA42=null;
        Token DOUBLESTAR43=null;
        Token DOUBLESTAR44=null;
        Token STAR45=null;
        Token COMMA46=null;
        Token DOUBLESTAR47=null;
        Token DOUBLESTAR48=null;
        List list_d=null;
        PythonParser.defparameter_return d = null;
         d = null;
        PythonTree starargs_tree=null;
        PythonTree kwargs_tree=null;
        PythonTree COMMA39_tree=null;
        PythonTree COMMA40_tree=null;
        PythonTree STAR41_tree=null;
        PythonTree COMMA42_tree=null;
        PythonTree DOUBLESTAR43_tree=null;
        PythonTree DOUBLESTAR44_tree=null;
        PythonTree STAR45_tree=null;
        PythonTree COMMA46_tree=null;
        PythonTree DOUBLESTAR47_tree=null;
        PythonTree DOUBLESTAR48_tree=null;


            List defaults = new ArrayList();

        try {
            // /usr/local/lib/jython/grammar/Python.g:545:5: (d+= defparameter[defaults] ( options {greedy=true; } : COMMA d+= defparameter[defaults] )* ( COMMA ( STAR starargs= NAME ( COMMA DOUBLESTAR kwargs= NAME )? | DOUBLESTAR kwargs= NAME )? )? | STAR starargs= NAME ( COMMA DOUBLESTAR kwargs= NAME )? | DOUBLESTAR kwargs= NAME )
            int alt23=3;
            switch ( input.LA(1) ) {
            case NAME:
            case LPAREN:
                {
                alt23=1;
                }
                break;
            case STAR:
                {
                alt23=2;
                }
                break;
            case DOUBLESTAR:
                {
                alt23=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 23, 0, input);

                throw nvae;
            }

            switch (alt23) {
                case 1 :
                    // /usr/local/lib/jython/grammar/Python.g:545:7: d+= defparameter[defaults] ( options {greedy=true; } : COMMA d+= defparameter[defaults] )* ( COMMA ( STAR starargs= NAME ( COMMA DOUBLESTAR kwargs= NAME )? | DOUBLESTAR kwargs= NAME )? )?
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    pushFollow(FOLLOW_defparameter_in_varargslist1096);
                    d=defparameter(defaults);

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, d.getTree());
                    if (list_d==null) list_d=new ArrayList();
                    list_d.add(d.getTree());

                    // /usr/local/lib/jython/grammar/Python.g:545:33: ( options {greedy=true; } : COMMA d+= defparameter[defaults] )*
                    loop18:
                    do {
                        int alt18=2;
                        int LA18_0 = input.LA(1);

                        if ( (LA18_0==COMMA) ) {
                            int LA18_1 = input.LA(2);

                            if ( (LA18_1==NAME||LA18_1==LPAREN) ) {
                                alt18=1;
                            }


                        }


                        switch (alt18) {
                    	case 1 :
                    	    // /usr/local/lib/jython/grammar/Python.g:545:57: COMMA d+= defparameter[defaults]
                    	    {
                    	    COMMA39=(Token)match(input,COMMA,FOLLOW_COMMA_in_varargslist1107); if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) {
                    	    COMMA39_tree = (PythonTree)adaptor.create(COMMA39);
                    	    adaptor.addChild(root_0, COMMA39_tree);
                    	    }
                    	    pushFollow(FOLLOW_defparameter_in_varargslist1111);
                    	    d=defparameter(defaults);

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) adaptor.addChild(root_0, d.getTree());
                    	    if (list_d==null) list_d=new ArrayList();
                    	    list_d.add(d.getTree());


                    	    }
                    	    break;

                    	default :
                    	    break loop18;
                        }
                    } while (true);

                    // /usr/local/lib/jython/grammar/Python.g:546:7: ( COMMA ( STAR starargs= NAME ( COMMA DOUBLESTAR kwargs= NAME )? | DOUBLESTAR kwargs= NAME )? )?
                    int alt21=2;
                    int LA21_0 = input.LA(1);

                    if ( (LA21_0==COMMA) ) {
                        alt21=1;
                    }
                    switch (alt21) {
                        case 1 :
                            // /usr/local/lib/jython/grammar/Python.g:546:8: COMMA ( STAR starargs= NAME ( COMMA DOUBLESTAR kwargs= NAME )? | DOUBLESTAR kwargs= NAME )?
                            {
                            COMMA40=(Token)match(input,COMMA,FOLLOW_COMMA_in_varargslist1123); if (state.failed) return retval;
                            if ( state.backtracking==0 ) {
                            COMMA40_tree = (PythonTree)adaptor.create(COMMA40);
                            adaptor.addChild(root_0, COMMA40_tree);
                            }
                            // /usr/local/lib/jython/grammar/Python.g:547:11: ( STAR starargs= NAME ( COMMA DOUBLESTAR kwargs= NAME )? | DOUBLESTAR kwargs= NAME )?
                            int alt20=3;
                            int LA20_0 = input.LA(1);

                            if ( (LA20_0==STAR) ) {
                                alt20=1;
                            }
                            else if ( (LA20_0==DOUBLESTAR) ) {
                                alt20=2;
                            }
                            switch (alt20) {
                                case 1 :
                                    // /usr/local/lib/jython/grammar/Python.g:547:12: STAR starargs= NAME ( COMMA DOUBLESTAR kwargs= NAME )?
                                    {
                                    STAR41=(Token)match(input,STAR,FOLLOW_STAR_in_varargslist1136); if (state.failed) return retval;
                                    if ( state.backtracking==0 ) {
                                    STAR41_tree = (PythonTree)adaptor.create(STAR41);
                                    adaptor.addChild(root_0, STAR41_tree);
                                    }
                                    starargs=(Token)match(input,NAME,FOLLOW_NAME_in_varargslist1140); if (state.failed) return retval;
                                    if ( state.backtracking==0 ) {
                                    starargs_tree = (PythonTree)adaptor.create(starargs);
                                    adaptor.addChild(root_0, starargs_tree);
                                    }
                                    // /usr/local/lib/jython/grammar/Python.g:547:31: ( COMMA DOUBLESTAR kwargs= NAME )?
                                    int alt19=2;
                                    int LA19_0 = input.LA(1);

                                    if ( (LA19_0==COMMA) ) {
                                        alt19=1;
                                    }
                                    switch (alt19) {
                                        case 1 :
                                            // /usr/local/lib/jython/grammar/Python.g:547:32: COMMA DOUBLESTAR kwargs= NAME
                                            {
                                            COMMA42=(Token)match(input,COMMA,FOLLOW_COMMA_in_varargslist1143); if (state.failed) return retval;
                                            if ( state.backtracking==0 ) {
                                            COMMA42_tree = (PythonTree)adaptor.create(COMMA42);
                                            adaptor.addChild(root_0, COMMA42_tree);
                                            }
                                            DOUBLESTAR43=(Token)match(input,DOUBLESTAR,FOLLOW_DOUBLESTAR_in_varargslist1145); if (state.failed) return retval;
                                            if ( state.backtracking==0 ) {
                                            DOUBLESTAR43_tree = (PythonTree)adaptor.create(DOUBLESTAR43);
                                            adaptor.addChild(root_0, DOUBLESTAR43_tree);
                                            }
                                            kwargs=(Token)match(input,NAME,FOLLOW_NAME_in_varargslist1149); if (state.failed) return retval;
                                            if ( state.backtracking==0 ) {
                                            kwargs_tree = (PythonTree)adaptor.create(kwargs);
                                            adaptor.addChild(root_0, kwargs_tree);
                                            }

                                            }
                                            break;

                                    }


                                    }
                                    break;
                                case 2 :
                                    // /usr/local/lib/jython/grammar/Python.g:548:13: DOUBLESTAR kwargs= NAME
                                    {
                                    DOUBLESTAR44=(Token)match(input,DOUBLESTAR,FOLLOW_DOUBLESTAR_in_varargslist1165); if (state.failed) return retval;
                                    if ( state.backtracking==0 ) {
                                    DOUBLESTAR44_tree = (PythonTree)adaptor.create(DOUBLESTAR44);
                                    adaptor.addChild(root_0, DOUBLESTAR44_tree);
                                    }
                                    kwargs=(Token)match(input,NAME,FOLLOW_NAME_in_varargslist1169); if (state.failed) return retval;
                                    if ( state.backtracking==0 ) {
                                    kwargs_tree = (PythonTree)adaptor.create(kwargs);
                                    adaptor.addChild(root_0, kwargs_tree);
                                    }

                                    }
                                    break;

                            }


                            }
                            break;

                    }

                    if ( state.backtracking==0 ) {

                                retval.args = actions.makeArgumentsType(((Token)retval.start), list_d, starargs, kwargs, defaults);
                            
                    }

                    }
                    break;
                case 2 :
                    // /usr/local/lib/jython/grammar/Python.g:554:7: STAR starargs= NAME ( COMMA DOUBLESTAR kwargs= NAME )?
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    STAR45=(Token)match(input,STAR,FOLLOW_STAR_in_varargslist1207); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    STAR45_tree = (PythonTree)adaptor.create(STAR45);
                    adaptor.addChild(root_0, STAR45_tree);
                    }
                    starargs=(Token)match(input,NAME,FOLLOW_NAME_in_varargslist1211); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    starargs_tree = (PythonTree)adaptor.create(starargs);
                    adaptor.addChild(root_0, starargs_tree);
                    }
                    // /usr/local/lib/jython/grammar/Python.g:554:26: ( COMMA DOUBLESTAR kwargs= NAME )?
                    int alt22=2;
                    int LA22_0 = input.LA(1);

                    if ( (LA22_0==COMMA) ) {
                        alt22=1;
                    }
                    switch (alt22) {
                        case 1 :
                            // /usr/local/lib/jython/grammar/Python.g:554:27: COMMA DOUBLESTAR kwargs= NAME
                            {
                            COMMA46=(Token)match(input,COMMA,FOLLOW_COMMA_in_varargslist1214); if (state.failed) return retval;
                            if ( state.backtracking==0 ) {
                            COMMA46_tree = (PythonTree)adaptor.create(COMMA46);
                            adaptor.addChild(root_0, COMMA46_tree);
                            }
                            DOUBLESTAR47=(Token)match(input,DOUBLESTAR,FOLLOW_DOUBLESTAR_in_varargslist1216); if (state.failed) return retval;
                            if ( state.backtracking==0 ) {
                            DOUBLESTAR47_tree = (PythonTree)adaptor.create(DOUBLESTAR47);
                            adaptor.addChild(root_0, DOUBLESTAR47_tree);
                            }
                            kwargs=(Token)match(input,NAME,FOLLOW_NAME_in_varargslist1220); if (state.failed) return retval;
                            if ( state.backtracking==0 ) {
                            kwargs_tree = (PythonTree)adaptor.create(kwargs);
                            adaptor.addChild(root_0, kwargs_tree);
                            }

                            }
                            break;

                    }

                    if ( state.backtracking==0 ) {

                                retval.args = actions.makeArgumentsType(((Token)retval.start), list_d, starargs, kwargs, defaults);
                            
                    }

                    }
                    break;
                case 3 :
                    // /usr/local/lib/jython/grammar/Python.g:558:7: DOUBLESTAR kwargs= NAME
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    DOUBLESTAR48=(Token)match(input,DOUBLESTAR,FOLLOW_DOUBLESTAR_in_varargslist1238); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    DOUBLESTAR48_tree = (PythonTree)adaptor.create(DOUBLESTAR48);
                    adaptor.addChild(root_0, DOUBLESTAR48_tree);
                    }
                    kwargs=(Token)match(input,NAME,FOLLOW_NAME_in_varargslist1242); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    kwargs_tree = (PythonTree)adaptor.create(kwargs);
                    adaptor.addChild(root_0, kwargs_tree);
                    }
                    if ( state.backtracking==0 ) {

                                retval.args = actions.makeArgumentsType(((Token)retval.start), list_d, null, kwargs, defaults);
                            
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        catch (RecognitionException re) {
            reportError(re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "varargslist"

    public static class fpdef_return extends ParserRuleReturnScope {
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "fpdef"
    // /usr/local/lib/jython/grammar/Python.g:565:1: fpdef[expr_contextType ctype] : ( NAME | ( LPAREN fpdef[null] COMMA )=> LPAREN fplist RPAREN | LPAREN fplist RPAREN );
    public final PythonParser.fpdef_return fpdef(expr_contextType ctype) throws RecognitionException {
        PythonParser.fpdef_return retval = new PythonParser.fpdef_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token NAME49=null;
        Token LPAREN50=null;
        Token RPAREN52=null;
        Token LPAREN53=null;
        Token RPAREN55=null;
        PythonParser.fplist_return fplist51 = null;

        PythonParser.fplist_return fplist54 = null;


        PythonTree NAME49_tree=null;
        PythonTree LPAREN50_tree=null;
        PythonTree RPAREN52_tree=null;
        PythonTree LPAREN53_tree=null;
        PythonTree RPAREN55_tree=null;


            expr etype = null;

        try {
            // /usr/local/lib/jython/grammar/Python.g:575:5: ( NAME | ( LPAREN fpdef[null] COMMA )=> LPAREN fplist RPAREN | LPAREN fplist RPAREN )
            int alt24=3;
            int LA24_0 = input.LA(1);

            if ( (LA24_0==NAME) ) {
                alt24=1;
            }
            else if ( (LA24_0==LPAREN) ) {
                int LA24_2 = input.LA(2);

                if ( (synpred1_Python()) ) {
                    alt24=2;
                }
                else if ( (true) ) {
                    alt24=3;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 24, 2, input);

                    throw nvae;
                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 24, 0, input);

                throw nvae;
            }
            switch (alt24) {
                case 1 :
                    // /usr/local/lib/jython/grammar/Python.g:575:7: NAME
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    NAME49=(Token)match(input,NAME,FOLLOW_NAME_in_fpdef1279); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    NAME49_tree = (PythonTree)adaptor.create(NAME49);
                    adaptor.addChild(root_0, NAME49_tree);
                    }
                    if ( state.backtracking==0 ) {

                                etype = new Name(NAME49, (NAME49!=null?NAME49.getText():null), ctype);
                            
                    }

                    }
                    break;
                case 2 :
                    // /usr/local/lib/jython/grammar/Python.g:579:7: ( LPAREN fpdef[null] COMMA )=> LPAREN fplist RPAREN
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    LPAREN50=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_fpdef1306); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    LPAREN50_tree = (PythonTree)adaptor.create(LPAREN50);
                    adaptor.addChild(root_0, LPAREN50_tree);
                    }
                    pushFollow(FOLLOW_fplist_in_fpdef1308);
                    fplist51=fplist();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, fplist51.getTree());
                    RPAREN52=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_fpdef1310); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    RPAREN52_tree = (PythonTree)adaptor.create(RPAREN52);
                    adaptor.addChild(root_0, RPAREN52_tree);
                    }
                    if ( state.backtracking==0 ) {

                                etype = new Tuple((fplist51!=null?((Token)fplist51.start):null), actions.castExprs((fplist51!=null?fplist51.etypes:null)), expr_contextType.Store);
                            
                    }

                    }
                    break;
                case 3 :
                    // /usr/local/lib/jython/grammar/Python.g:583:7: LPAREN fplist RPAREN
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    LPAREN53=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_fpdef1326); if (state.failed) return retval;
                    pushFollow(FOLLOW_fplist_in_fpdef1329);
                    fplist54=fplist();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, fplist54.getTree());
                    RPAREN55=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_fpdef1331); if (state.failed) return retval;

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {

                  if (etype != null) {
                      retval.tree = etype;
                  }
                  actions.checkAssign(actions.castExpr(((PythonTree)retval.tree)));

            }
        }

        catch (RecognitionException re) {
            reportError(re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "fpdef"

    public static class fplist_return extends ParserRuleReturnScope {
        public List etypes;
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "fplist"
    // /usr/local/lib/jython/grammar/Python.g:587:1: fplist returns [List etypes] : f+= fpdef[expr_contextType.Store] ( options {greedy=true; } : COMMA f+= fpdef[expr_contextType.Store] )* ( COMMA )? ;
    public final PythonParser.fplist_return fplist() throws RecognitionException {
        PythonParser.fplist_return retval = new PythonParser.fplist_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token COMMA56=null;
        Token COMMA57=null;
        List list_f=null;
        PythonParser.fpdef_return f = null;
         f = null;
        PythonTree COMMA56_tree=null;
        PythonTree COMMA57_tree=null;

        try {
            // /usr/local/lib/jython/grammar/Python.g:589:5: (f+= fpdef[expr_contextType.Store] ( options {greedy=true; } : COMMA f+= fpdef[expr_contextType.Store] )* ( COMMA )? )
            // /usr/local/lib/jython/grammar/Python.g:589:7: f+= fpdef[expr_contextType.Store] ( options {greedy=true; } : COMMA f+= fpdef[expr_contextType.Store] )* ( COMMA )?
            {
            root_0 = (PythonTree)adaptor.nil();

            pushFollow(FOLLOW_fpdef_in_fplist1360);
            f=fpdef(expr_contextType.Store);

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, f.getTree());
            if (list_f==null) list_f=new ArrayList();
            list_f.add(f.getTree());

            // /usr/local/lib/jython/grammar/Python.g:590:7: ( options {greedy=true; } : COMMA f+= fpdef[expr_contextType.Store] )*
            loop25:
            do {
                int alt25=2;
                int LA25_0 = input.LA(1);

                if ( (LA25_0==COMMA) ) {
                    int LA25_1 = input.LA(2);

                    if ( (LA25_1==NAME||LA25_1==LPAREN) ) {
                        alt25=1;
                    }


                }


                switch (alt25) {
            	case 1 :
            	    // /usr/local/lib/jython/grammar/Python.g:590:31: COMMA f+= fpdef[expr_contextType.Store]
            	    {
            	    COMMA56=(Token)match(input,COMMA,FOLLOW_COMMA_in_fplist1377); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    COMMA56_tree = (PythonTree)adaptor.create(COMMA56);
            	    adaptor.addChild(root_0, COMMA56_tree);
            	    }
            	    pushFollow(FOLLOW_fpdef_in_fplist1381);
            	    f=fpdef(expr_contextType.Store);

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, f.getTree());
            	    if (list_f==null) list_f=new ArrayList();
            	    list_f.add(f.getTree());


            	    }
            	    break;

            	default :
            	    break loop25;
                }
            } while (true);

            // /usr/local/lib/jython/grammar/Python.g:590:72: ( COMMA )?
            int alt26=2;
            int LA26_0 = input.LA(1);

            if ( (LA26_0==COMMA) ) {
                alt26=1;
            }
            switch (alt26) {
                case 1 :
                    // /usr/local/lib/jython/grammar/Python.g:590:73: COMMA
                    {
                    COMMA57=(Token)match(input,COMMA,FOLLOW_COMMA_in_fplist1387); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    COMMA57_tree = (PythonTree)adaptor.create(COMMA57);
                    adaptor.addChild(root_0, COMMA57_tree);
                    }

                    }
                    break;

            }

            if ( state.backtracking==0 ) {

                        retval.etypes = list_f;
                    
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        catch (RecognitionException re) {
            reportError(re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "fplist"

    public static class stmt_return extends ParserRuleReturnScope {
        public List stypes;
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "stmt"
    // /usr/local/lib/jython/grammar/Python.g:597:1: stmt returns [List stypes] : ( simple_stmt | compound_stmt );
    public final PythonParser.stmt_return stmt() throws RecognitionException {
        PythonParser.stmt_return retval = new PythonParser.stmt_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        PythonParser.simple_stmt_return simple_stmt58 = null;

        PythonParser.compound_stmt_return compound_stmt59 = null;



        try {
            // /usr/local/lib/jython/grammar/Python.g:599:5: ( simple_stmt | compound_stmt )
            int alt27=2;
            int LA27_0 = input.LA(1);

            if ( (LA27_0==NAME||LA27_0==NOT||LA27_0==LPAREN||(LA27_0>=PLUS && LA27_0<=MINUS)||(LA27_0>=TILDE && LA27_0<=LBRACK)||LA27_0==LCURLY||LA27_0==BACKQUOTE) ) {
                alt27=1;
            }
            else if ( (LA27_0==PRINT) && (((!printFunction)||(printFunction)))) {
                alt27=1;
            }
            else if ( ((LA27_0>=ASSERT && LA27_0<=BREAK)||LA27_0==CONTINUE||LA27_0==DELETE||LA27_0==EXEC||LA27_0==FROM||LA27_0==GLOBAL||LA27_0==IMPORT||LA27_0==LAMBDA||(LA27_0>=PASS && LA27_0<=RETURN)||LA27_0==YIELD||(LA27_0>=INT && LA27_0<=STRING)) ) {
                alt27=1;
            }
            else if ( (LA27_0==CLASS||LA27_0==DEF||LA27_0==FOR||LA27_0==IF||(LA27_0>=TRY && LA27_0<=WITH)||LA27_0==AT) ) {
                alt27=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 27, 0, input);

                throw nvae;
            }
            switch (alt27) {
                case 1 :
                    // /usr/local/lib/jython/grammar/Python.g:599:7: simple_stmt
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    pushFollow(FOLLOW_simple_stmt_in_stmt1423);
                    simple_stmt58=simple_stmt();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, simple_stmt58.getTree());
                    if ( state.backtracking==0 ) {

                                retval.stypes = (simple_stmt58!=null?simple_stmt58.stypes:null);
                            
                    }

                    }
                    break;
                case 2 :
                    // /usr/local/lib/jython/grammar/Python.g:603:7: compound_stmt
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    pushFollow(FOLLOW_compound_stmt_in_stmt1439);
                    compound_stmt59=compound_stmt();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, compound_stmt59.getTree());
                    if ( state.backtracking==0 ) {

                                retval.stypes = new ArrayList();
                                retval.stypes.add((compound_stmt59!=null?((PythonTree)compound_stmt59.tree):null));
                            
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        catch (RecognitionException re) {
            reportError(re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "stmt"

    public static class simple_stmt_return extends ParserRuleReturnScope {
        public List stypes;
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "simple_stmt"
    // /usr/local/lib/jython/grammar/Python.g:611:1: simple_stmt returns [List stypes] : s+= small_stmt ( options {greedy=true; } : SEMI s+= small_stmt )* ( SEMI )? NEWLINE ;
    public final PythonParser.simple_stmt_return simple_stmt() throws RecognitionException {
        PythonParser.simple_stmt_return retval = new PythonParser.simple_stmt_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token SEMI60=null;
        Token SEMI61=null;
        Token NEWLINE62=null;
        List list_s=null;
        PythonParser.small_stmt_return s = null;
         s = null;
        PythonTree SEMI60_tree=null;
        PythonTree SEMI61_tree=null;
        PythonTree NEWLINE62_tree=null;

        try {
            // /usr/local/lib/jython/grammar/Python.g:613:5: (s+= small_stmt ( options {greedy=true; } : SEMI s+= small_stmt )* ( SEMI )? NEWLINE )
            // /usr/local/lib/jython/grammar/Python.g:613:7: s+= small_stmt ( options {greedy=true; } : SEMI s+= small_stmt )* ( SEMI )? NEWLINE
            {
            root_0 = (PythonTree)adaptor.nil();

            pushFollow(FOLLOW_small_stmt_in_simple_stmt1475);
            s=small_stmt();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, s.getTree());
            if (list_s==null) list_s=new ArrayList();
            list_s.add(s.getTree());

            // /usr/local/lib/jython/grammar/Python.g:613:21: ( options {greedy=true; } : SEMI s+= small_stmt )*
            loop28:
            do {
                int alt28=2;
                int LA28_0 = input.LA(1);

                if ( (LA28_0==SEMI) ) {
                    int LA28_1 = input.LA(2);

                    if ( (LA28_1==NAME||LA28_1==PRINT||(LA28_1>=ASSERT && LA28_1<=BREAK)||LA28_1==CONTINUE||LA28_1==DELETE||LA28_1==EXEC||LA28_1==FROM||LA28_1==GLOBAL||LA28_1==IMPORT||(LA28_1>=LAMBDA && LA28_1<=NOT)||(LA28_1>=PASS && LA28_1<=RETURN)||LA28_1==YIELD||LA28_1==LPAREN||(LA28_1>=PLUS && LA28_1<=MINUS)||(LA28_1>=TILDE && LA28_1<=LBRACK)||LA28_1==LCURLY||(LA28_1>=BACKQUOTE && LA28_1<=STRING)) ) {
                        alt28=1;
                    }


                }


                switch (alt28) {
            	case 1 :
            	    // /usr/local/lib/jython/grammar/Python.g:613:45: SEMI s+= small_stmt
            	    {
            	    SEMI60=(Token)match(input,SEMI,FOLLOW_SEMI_in_simple_stmt1485); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    SEMI60_tree = (PythonTree)adaptor.create(SEMI60);
            	    adaptor.addChild(root_0, SEMI60_tree);
            	    }
            	    pushFollow(FOLLOW_small_stmt_in_simple_stmt1489);
            	    s=small_stmt();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, s.getTree());
            	    if (list_s==null) list_s=new ArrayList();
            	    list_s.add(s.getTree());


            	    }
            	    break;

            	default :
            	    break loop28;
                }
            } while (true);

            // /usr/local/lib/jython/grammar/Python.g:613:66: ( SEMI )?
            int alt29=2;
            int LA29_0 = input.LA(1);

            if ( (LA29_0==SEMI) ) {
                alt29=1;
            }
            switch (alt29) {
                case 1 :
                    // /usr/local/lib/jython/grammar/Python.g:613:67: SEMI
                    {
                    SEMI61=(Token)match(input,SEMI,FOLLOW_SEMI_in_simple_stmt1494); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    SEMI61_tree = (PythonTree)adaptor.create(SEMI61);
                    adaptor.addChild(root_0, SEMI61_tree);
                    }

                    }
                    break;

            }

            NEWLINE62=(Token)match(input,NEWLINE,FOLLOW_NEWLINE_in_simple_stmt1498); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            NEWLINE62_tree = (PythonTree)adaptor.create(NEWLINE62);
            adaptor.addChild(root_0, NEWLINE62_tree);
            }
            if ( state.backtracking==0 ) {

                        retval.stypes = list_s;
                    
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        catch (RecognitionException re) {
            reportError(re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "simple_stmt"

    public static class small_stmt_return extends ParserRuleReturnScope {
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "small_stmt"
    // /usr/local/lib/jython/grammar/Python.g:621:1: small_stmt : ( expr_stmt | del_stmt | pass_stmt | flow_stmt | import_stmt | global_stmt | exec_stmt | assert_stmt | {...}? => print_stmt );
    public final PythonParser.small_stmt_return small_stmt() throws RecognitionException {
        PythonParser.small_stmt_return retval = new PythonParser.small_stmt_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        PythonParser.expr_stmt_return expr_stmt63 = null;

        PythonParser.del_stmt_return del_stmt64 = null;

        PythonParser.pass_stmt_return pass_stmt65 = null;

        PythonParser.flow_stmt_return flow_stmt66 = null;

        PythonParser.import_stmt_return import_stmt67 = null;

        PythonParser.global_stmt_return global_stmt68 = null;

        PythonParser.exec_stmt_return exec_stmt69 = null;

        PythonParser.assert_stmt_return assert_stmt70 = null;

        PythonParser.print_stmt_return print_stmt71 = null;



        try {
            // /usr/local/lib/jython/grammar/Python.g:621:12: ( expr_stmt | del_stmt | pass_stmt | flow_stmt | import_stmt | global_stmt | exec_stmt | assert_stmt | {...}? => print_stmt )
            int alt30=9;
            alt30 = dfa30.predict(input);
            switch (alt30) {
                case 1 :
                    // /usr/local/lib/jython/grammar/Python.g:621:14: expr_stmt
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    pushFollow(FOLLOW_expr_stmt_in_small_stmt1521);
                    expr_stmt63=expr_stmt();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, expr_stmt63.getTree());

                    }
                    break;
                case 2 :
                    // /usr/local/lib/jython/grammar/Python.g:622:14: del_stmt
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    pushFollow(FOLLOW_del_stmt_in_small_stmt1536);
                    del_stmt64=del_stmt();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, del_stmt64.getTree());

                    }
                    break;
                case 3 :
                    // /usr/local/lib/jython/grammar/Python.g:623:14: pass_stmt
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    pushFollow(FOLLOW_pass_stmt_in_small_stmt1551);
                    pass_stmt65=pass_stmt();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, pass_stmt65.getTree());

                    }
                    break;
                case 4 :
                    // /usr/local/lib/jython/grammar/Python.g:624:14: flow_stmt
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    pushFollow(FOLLOW_flow_stmt_in_small_stmt1566);
                    flow_stmt66=flow_stmt();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, flow_stmt66.getTree());

                    }
                    break;
                case 5 :
                    // /usr/local/lib/jython/grammar/Python.g:625:14: import_stmt
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    pushFollow(FOLLOW_import_stmt_in_small_stmt1581);
                    import_stmt67=import_stmt();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, import_stmt67.getTree());

                    }
                    break;
                case 6 :
                    // /usr/local/lib/jython/grammar/Python.g:626:14: global_stmt
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    pushFollow(FOLLOW_global_stmt_in_small_stmt1596);
                    global_stmt68=global_stmt();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, global_stmt68.getTree());

                    }
                    break;
                case 7 :
                    // /usr/local/lib/jython/grammar/Python.g:627:14: exec_stmt
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    pushFollow(FOLLOW_exec_stmt_in_small_stmt1611);
                    exec_stmt69=exec_stmt();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, exec_stmt69.getTree());

                    }
                    break;
                case 8 :
                    // /usr/local/lib/jython/grammar/Python.g:628:14: assert_stmt
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    pushFollow(FOLLOW_assert_stmt_in_small_stmt1626);
                    assert_stmt70=assert_stmt();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, assert_stmt70.getTree());

                    }
                    break;
                case 9 :
                    // /usr/local/lib/jython/grammar/Python.g:629:14: {...}? => print_stmt
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    if ( !((!printFunction)) ) {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        throw new FailedPredicateException(input, "small_stmt", "!printFunction");
                    }
                    pushFollow(FOLLOW_print_stmt_in_small_stmt1645);
                    print_stmt71=print_stmt();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, print_stmt71.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        catch (RecognitionException re) {
            reportError(re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "small_stmt"

    public static class expr_stmt_return extends ParserRuleReturnScope {
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "expr_stmt"
    // /usr/local/lib/jython/grammar/Python.g:634:1: expr_stmt : ( ( testlist[null] augassign )=>lhs= testlist[expr_contextType.AugStore] ( (aay= augassign y1= yield_expr ) | (aat= augassign rhs= testlist[expr_contextType.Load] ) ) | ( testlist[null] ASSIGN )=>lhs= testlist[expr_contextType.Store] ( | ( (at= ASSIGN t+= testlist[expr_contextType.Store] )+ ) | ( (ay= ASSIGN y2+= yield_expr )+ ) ) | lhs= testlist[expr_contextType.Load] ) ;
    public final PythonParser.expr_stmt_return expr_stmt() throws RecognitionException {
        PythonParser.expr_stmt_return retval = new PythonParser.expr_stmt_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token at=null;
        Token ay=null;
        List list_t=null;
        List list_y2=null;
        PythonParser.testlist_return lhs = null;

        PythonParser.augassign_return aay = null;

        PythonParser.yield_expr_return y1 = null;

        PythonParser.augassign_return aat = null;

        PythonParser.testlist_return rhs = null;

        PythonParser.testlist_return t = null;
         t = null;
        PythonParser.yield_expr_return y2 = null;
         y2 = null;
        PythonTree at_tree=null;
        PythonTree ay_tree=null;


            stmt stype = null;

        try {
            // /usr/local/lib/jython/grammar/Python.g:643:5: ( ( ( testlist[null] augassign )=>lhs= testlist[expr_contextType.AugStore] ( (aay= augassign y1= yield_expr ) | (aat= augassign rhs= testlist[expr_contextType.Load] ) ) | ( testlist[null] ASSIGN )=>lhs= testlist[expr_contextType.Store] ( | ( (at= ASSIGN t+= testlist[expr_contextType.Store] )+ ) | ( (ay= ASSIGN y2+= yield_expr )+ ) ) | lhs= testlist[expr_contextType.Load] ) )
            // /usr/local/lib/jython/grammar/Python.g:643:7: ( ( testlist[null] augassign )=>lhs= testlist[expr_contextType.AugStore] ( (aay= augassign y1= yield_expr ) | (aat= augassign rhs= testlist[expr_contextType.Load] ) ) | ( testlist[null] ASSIGN )=>lhs= testlist[expr_contextType.Store] ( | ( (at= ASSIGN t+= testlist[expr_contextType.Store] )+ ) | ( (ay= ASSIGN y2+= yield_expr )+ ) ) | lhs= testlist[expr_contextType.Load] )
            {
            root_0 = (PythonTree)adaptor.nil();

            // /usr/local/lib/jython/grammar/Python.g:643:7: ( ( testlist[null] augassign )=>lhs= testlist[expr_contextType.AugStore] ( (aay= augassign y1= yield_expr ) | (aat= augassign rhs= testlist[expr_contextType.Load] ) ) | ( testlist[null] ASSIGN )=>lhs= testlist[expr_contextType.Store] ( | ( (at= ASSIGN t+= testlist[expr_contextType.Store] )+ ) | ( (ay= ASSIGN y2+= yield_expr )+ ) ) | lhs= testlist[expr_contextType.Load] )
            int alt35=3;
            alt35 = dfa35.predict(input);
            switch (alt35) {
                case 1 :
                    // /usr/local/lib/jython/grammar/Python.g:643:8: ( testlist[null] augassign )=>lhs= testlist[expr_contextType.AugStore] ( (aay= augassign y1= yield_expr ) | (aat= augassign rhs= testlist[expr_contextType.Load] ) )
                    {
                    pushFollow(FOLLOW_testlist_in_expr_stmt1693);
                    lhs=testlist(expr_contextType.AugStore);

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, lhs.getTree());
                    // /usr/local/lib/jython/grammar/Python.g:644:9: ( (aay= augassign y1= yield_expr ) | (aat= augassign rhs= testlist[expr_contextType.Load] ) )
                    int alt31=2;
                    alt31 = dfa31.predict(input);
                    switch (alt31) {
                        case 1 :
                            // /usr/local/lib/jython/grammar/Python.g:644:11: (aay= augassign y1= yield_expr )
                            {
                            // /usr/local/lib/jython/grammar/Python.g:644:11: (aay= augassign y1= yield_expr )
                            // /usr/local/lib/jython/grammar/Python.g:644:12: aay= augassign y1= yield_expr
                            {
                            pushFollow(FOLLOW_augassign_in_expr_stmt1709);
                            aay=augassign();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) adaptor.addChild(root_0, aay.getTree());
                            pushFollow(FOLLOW_yield_expr_in_expr_stmt1713);
                            y1=yield_expr();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) adaptor.addChild(root_0, y1.getTree());
                            if ( state.backtracking==0 ) {

                                             actions.checkAugAssign(actions.castExpr((lhs!=null?((PythonTree)lhs.tree):null)));
                                             stype = new AugAssign((lhs!=null?((PythonTree)lhs.tree):null), actions.castExpr((lhs!=null?((PythonTree)lhs.tree):null)), (aay!=null?aay.op:null), actions.castExpr((y1!=null?y1.etype:null)));
                                         
                            }

                            }


                            }
                            break;
                        case 2 :
                            // /usr/local/lib/jython/grammar/Python.g:650:11: (aat= augassign rhs= testlist[expr_contextType.Load] )
                            {
                            // /usr/local/lib/jython/grammar/Python.g:650:11: (aat= augassign rhs= testlist[expr_contextType.Load] )
                            // /usr/local/lib/jython/grammar/Python.g:650:12: aat= augassign rhs= testlist[expr_contextType.Load]
                            {
                            pushFollow(FOLLOW_augassign_in_expr_stmt1753);
                            aat=augassign();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) adaptor.addChild(root_0, aat.getTree());
                            pushFollow(FOLLOW_testlist_in_expr_stmt1757);
                            rhs=testlist(expr_contextType.Load);

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) adaptor.addChild(root_0, rhs.getTree());
                            if ( state.backtracking==0 ) {

                                             actions.checkAugAssign(actions.castExpr((lhs!=null?((PythonTree)lhs.tree):null)));
                                             stype = new AugAssign((lhs!=null?((PythonTree)lhs.tree):null), actions.castExpr((lhs!=null?((PythonTree)lhs.tree):null)), (aat!=null?aat.op:null), actions.castExpr((rhs!=null?((PythonTree)rhs.tree):null)));
                                         
                            }

                            }


                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // /usr/local/lib/jython/grammar/Python.g:657:7: ( testlist[null] ASSIGN )=>lhs= testlist[expr_contextType.Store] ( | ( (at= ASSIGN t+= testlist[expr_contextType.Store] )+ ) | ( (ay= ASSIGN y2+= yield_expr )+ ) )
                    {
                    pushFollow(FOLLOW_testlist_in_expr_stmt1812);
                    lhs=testlist(expr_contextType.Store);

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, lhs.getTree());
                    // /usr/local/lib/jython/grammar/Python.g:658:9: ( | ( (at= ASSIGN t+= testlist[expr_contextType.Store] )+ ) | ( (ay= ASSIGN y2+= yield_expr )+ ) )
                    int alt34=3;
                    int LA34_0 = input.LA(1);

                    if ( (LA34_0==NEWLINE||LA34_0==SEMI) ) {
                        alt34=1;
                    }
                    else if ( (LA34_0==ASSIGN) ) {
                        int LA34_2 = input.LA(2);

                        if ( (LA34_2==YIELD) ) {
                            alt34=3;
                        }
                        else if ( (LA34_2==NAME||LA34_2==PRINT||(LA34_2>=LAMBDA && LA34_2<=NOT)||LA34_2==LPAREN||(LA34_2>=PLUS && LA34_2<=MINUS)||(LA34_2>=TILDE && LA34_2<=LBRACK)||LA34_2==LCURLY||(LA34_2>=BACKQUOTE && LA34_2<=STRING)) ) {
                            alt34=2;
                        }
                        else {
                            if (state.backtracking>0) {state.failed=true; return retval;}
                            NoViableAltException nvae =
                                new NoViableAltException("", 34, 2, input);

                            throw nvae;
                        }
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 34, 0, input);

                        throw nvae;
                    }
                    switch (alt34) {
                        case 1 :
                            // /usr/local/lib/jython/grammar/Python.g:659:9: 
                            {
                            }
                            break;
                        case 2 :
                            // /usr/local/lib/jython/grammar/Python.g:659:11: ( (at= ASSIGN t+= testlist[expr_contextType.Store] )+ )
                            {
                            // /usr/local/lib/jython/grammar/Python.g:659:11: ( (at= ASSIGN t+= testlist[expr_contextType.Store] )+ )
                            // /usr/local/lib/jython/grammar/Python.g:659:12: (at= ASSIGN t+= testlist[expr_contextType.Store] )+
                            {
                            // /usr/local/lib/jython/grammar/Python.g:659:12: (at= ASSIGN t+= testlist[expr_contextType.Store] )+
                            int cnt32=0;
                            loop32:
                            do {
                                int alt32=2;
                                int LA32_0 = input.LA(1);

                                if ( (LA32_0==ASSIGN) ) {
                                    alt32=1;
                                }


                                switch (alt32) {
                            	case 1 :
                            	    // /usr/local/lib/jython/grammar/Python.g:659:13: at= ASSIGN t+= testlist[expr_contextType.Store]
                            	    {
                            	    at=(Token)match(input,ASSIGN,FOLLOW_ASSIGN_in_expr_stmt1839); if (state.failed) return retval;
                            	    if ( state.backtracking==0 ) {
                            	    at_tree = (PythonTree)adaptor.create(at);
                            	    adaptor.addChild(root_0, at_tree);
                            	    }
                            	    pushFollow(FOLLOW_testlist_in_expr_stmt1843);
                            	    t=testlist(expr_contextType.Store);

                            	    state._fsp--;
                            	    if (state.failed) return retval;
                            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, t.getTree());
                            	    if (list_t==null) list_t=new ArrayList();
                            	    list_t.add(t.getTree());


                            	    }
                            	    break;

                            	default :
                            	    if ( cnt32 >= 1 ) break loop32;
                            	    if (state.backtracking>0) {state.failed=true; return retval;}
                                        EarlyExitException eee =
                                            new EarlyExitException(32, input);
                                        throw eee;
                                }
                                cnt32++;
                            } while (true);

                            if ( state.backtracking==0 ) {

                                              stype = new Assign((lhs!=null?((PythonTree)lhs.tree):null), actions.makeAssignTargets(
                                                  actions.castExpr((lhs!=null?((PythonTree)lhs.tree):null)), list_t), actions.makeAssignValue(list_t));
                                          
                            }

                            }


                            }
                            break;
                        case 3 :
                            // /usr/local/lib/jython/grammar/Python.g:665:11: ( (ay= ASSIGN y2+= yield_expr )+ )
                            {
                            // /usr/local/lib/jython/grammar/Python.g:665:11: ( (ay= ASSIGN y2+= yield_expr )+ )
                            // /usr/local/lib/jython/grammar/Python.g:665:12: (ay= ASSIGN y2+= yield_expr )+
                            {
                            // /usr/local/lib/jython/grammar/Python.g:665:12: (ay= ASSIGN y2+= yield_expr )+
                            int cnt33=0;
                            loop33:
                            do {
                                int alt33=2;
                                int LA33_0 = input.LA(1);

                                if ( (LA33_0==ASSIGN) ) {
                                    alt33=1;
                                }


                                switch (alt33) {
                            	case 1 :
                            	    // /usr/local/lib/jython/grammar/Python.g:665:13: ay= ASSIGN y2+= yield_expr
                            	    {
                            	    ay=(Token)match(input,ASSIGN,FOLLOW_ASSIGN_in_expr_stmt1888); if (state.failed) return retval;
                            	    if ( state.backtracking==0 ) {
                            	    ay_tree = (PythonTree)adaptor.create(ay);
                            	    adaptor.addChild(root_0, ay_tree);
                            	    }
                            	    pushFollow(FOLLOW_yield_expr_in_expr_stmt1892);
                            	    y2=yield_expr();

                            	    state._fsp--;
                            	    if (state.failed) return retval;
                            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, y2.getTree());
                            	    if (list_y2==null) list_y2=new ArrayList();
                            	    list_y2.add(y2.getTree());


                            	    }
                            	    break;

                            	default :
                            	    if ( cnt33 >= 1 ) break loop33;
                            	    if (state.backtracking>0) {state.failed=true; return retval;}
                                        EarlyExitException eee =
                                            new EarlyExitException(33, input);
                                        throw eee;
                                }
                                cnt33++;
                            } while (true);

                            if ( state.backtracking==0 ) {

                                              stype = new Assign((lhs!=null?((Token)lhs.start):null), actions.makeAssignTargets(
                                                  actions.castExpr((lhs!=null?((PythonTree)lhs.tree):null)), list_y2), actions.makeAssignValue(list_y2));
                                          
                            }

                            }


                            }
                            break;

                    }


                    }
                    break;
                case 3 :
                    // /usr/local/lib/jython/grammar/Python.g:672:7: lhs= testlist[expr_contextType.Load]
                    {
                    pushFollow(FOLLOW_testlist_in_expr_stmt1940);
                    lhs=testlist(expr_contextType.Load);

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, lhs.getTree());
                    if ( state.backtracking==0 ) {

                                stype = new Expr((lhs!=null?((Token)lhs.start):null), actions.castExpr((lhs!=null?((PythonTree)lhs.tree):null)));
                            
                    }

                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {

                  if (stype != null) {
                      retval.tree = stype;
                  }

            }
        }

        catch (RecognitionException re) {
            reportError(re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "expr_stmt"

    public static class augassign_return extends ParserRuleReturnScope {
        public operatorType op;
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "augassign"
    // /usr/local/lib/jython/grammar/Python.g:681:1: augassign returns [operatorType op] : ( PLUSEQUAL | MINUSEQUAL | STAREQUAL | SLASHEQUAL | PERCENTEQUAL | AMPEREQUAL | VBAREQUAL | CIRCUMFLEXEQUAL | LEFTSHIFTEQUAL | RIGHTSHIFTEQUAL | DOUBLESTAREQUAL | DOUBLESLASHEQUAL );
    public final PythonParser.augassign_return augassign() throws RecognitionException {
        PythonParser.augassign_return retval = new PythonParser.augassign_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token PLUSEQUAL72=null;
        Token MINUSEQUAL73=null;
        Token STAREQUAL74=null;
        Token SLASHEQUAL75=null;
        Token PERCENTEQUAL76=null;
        Token AMPEREQUAL77=null;
        Token VBAREQUAL78=null;
        Token CIRCUMFLEXEQUAL79=null;
        Token LEFTSHIFTEQUAL80=null;
        Token RIGHTSHIFTEQUAL81=null;
        Token DOUBLESTAREQUAL82=null;
        Token DOUBLESLASHEQUAL83=null;

        PythonTree PLUSEQUAL72_tree=null;
        PythonTree MINUSEQUAL73_tree=null;
        PythonTree STAREQUAL74_tree=null;
        PythonTree SLASHEQUAL75_tree=null;
        PythonTree PERCENTEQUAL76_tree=null;
        PythonTree AMPEREQUAL77_tree=null;
        PythonTree VBAREQUAL78_tree=null;
        PythonTree CIRCUMFLEXEQUAL79_tree=null;
        PythonTree LEFTSHIFTEQUAL80_tree=null;
        PythonTree RIGHTSHIFTEQUAL81_tree=null;
        PythonTree DOUBLESTAREQUAL82_tree=null;
        PythonTree DOUBLESLASHEQUAL83_tree=null;

        try {
            // /usr/local/lib/jython/grammar/Python.g:683:5: ( PLUSEQUAL | MINUSEQUAL | STAREQUAL | SLASHEQUAL | PERCENTEQUAL | AMPEREQUAL | VBAREQUAL | CIRCUMFLEXEQUAL | LEFTSHIFTEQUAL | RIGHTSHIFTEQUAL | DOUBLESTAREQUAL | DOUBLESLASHEQUAL )
            int alt36=12;
            switch ( input.LA(1) ) {
            case PLUSEQUAL:
                {
                alt36=1;
                }
                break;
            case MINUSEQUAL:
                {
                alt36=2;
                }
                break;
            case STAREQUAL:
                {
                alt36=3;
                }
                break;
            case SLASHEQUAL:
                {
                alt36=4;
                }
                break;
            case PERCENTEQUAL:
                {
                alt36=5;
                }
                break;
            case AMPEREQUAL:
                {
                alt36=6;
                }
                break;
            case VBAREQUAL:
                {
                alt36=7;
                }
                break;
            case CIRCUMFLEXEQUAL:
                {
                alt36=8;
                }
                break;
            case LEFTSHIFTEQUAL:
                {
                alt36=9;
                }
                break;
            case RIGHTSHIFTEQUAL:
                {
                alt36=10;
                }
                break;
            case DOUBLESTAREQUAL:
                {
                alt36=11;
                }
                break;
            case DOUBLESLASHEQUAL:
                {
                alt36=12;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 36, 0, input);

                throw nvae;
            }

            switch (alt36) {
                case 1 :
                    // /usr/local/lib/jython/grammar/Python.g:683:7: PLUSEQUAL
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    PLUSEQUAL72=(Token)match(input,PLUSEQUAL,FOLLOW_PLUSEQUAL_in_augassign1982); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    PLUSEQUAL72_tree = (PythonTree)adaptor.create(PLUSEQUAL72);
                    adaptor.addChild(root_0, PLUSEQUAL72_tree);
                    }
                    if ( state.backtracking==0 ) {

                                  retval.op = operatorType.Add;
                              
                    }

                    }
                    break;
                case 2 :
                    // /usr/local/lib/jython/grammar/Python.g:687:7: MINUSEQUAL
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    MINUSEQUAL73=(Token)match(input,MINUSEQUAL,FOLLOW_MINUSEQUAL_in_augassign2000); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    MINUSEQUAL73_tree = (PythonTree)adaptor.create(MINUSEQUAL73);
                    adaptor.addChild(root_0, MINUSEQUAL73_tree);
                    }
                    if ( state.backtracking==0 ) {

                                  retval.op = operatorType.Sub;
                              
                    }

                    }
                    break;
                case 3 :
                    // /usr/local/lib/jython/grammar/Python.g:691:7: STAREQUAL
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    STAREQUAL74=(Token)match(input,STAREQUAL,FOLLOW_STAREQUAL_in_augassign2018); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    STAREQUAL74_tree = (PythonTree)adaptor.create(STAREQUAL74);
                    adaptor.addChild(root_0, STAREQUAL74_tree);
                    }
                    if ( state.backtracking==0 ) {

                                  retval.op = operatorType.Mult;
                              
                    }

                    }
                    break;
                case 4 :
                    // /usr/local/lib/jython/grammar/Python.g:695:7: SLASHEQUAL
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    SLASHEQUAL75=(Token)match(input,SLASHEQUAL,FOLLOW_SLASHEQUAL_in_augassign2036); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    SLASHEQUAL75_tree = (PythonTree)adaptor.create(SLASHEQUAL75);
                    adaptor.addChild(root_0, SLASHEQUAL75_tree);
                    }
                    if ( state.backtracking==0 ) {

                                  retval.op = operatorType.Div;
                              
                    }

                    }
                    break;
                case 5 :
                    // /usr/local/lib/jython/grammar/Python.g:699:7: PERCENTEQUAL
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    PERCENTEQUAL76=(Token)match(input,PERCENTEQUAL,FOLLOW_PERCENTEQUAL_in_augassign2054); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    PERCENTEQUAL76_tree = (PythonTree)adaptor.create(PERCENTEQUAL76);
                    adaptor.addChild(root_0, PERCENTEQUAL76_tree);
                    }
                    if ( state.backtracking==0 ) {

                                  retval.op = operatorType.Mod;
                              
                    }

                    }
                    break;
                case 6 :
                    // /usr/local/lib/jython/grammar/Python.g:703:7: AMPEREQUAL
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    AMPEREQUAL77=(Token)match(input,AMPEREQUAL,FOLLOW_AMPEREQUAL_in_augassign2072); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    AMPEREQUAL77_tree = (PythonTree)adaptor.create(AMPEREQUAL77);
                    adaptor.addChild(root_0, AMPEREQUAL77_tree);
                    }
                    if ( state.backtracking==0 ) {

                                  retval.op = operatorType.BitAnd;
                              
                    }

                    }
                    break;
                case 7 :
                    // /usr/local/lib/jython/grammar/Python.g:707:7: VBAREQUAL
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    VBAREQUAL78=(Token)match(input,VBAREQUAL,FOLLOW_VBAREQUAL_in_augassign2090); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    VBAREQUAL78_tree = (PythonTree)adaptor.create(VBAREQUAL78);
                    adaptor.addChild(root_0, VBAREQUAL78_tree);
                    }
                    if ( state.backtracking==0 ) {

                                  retval.op = operatorType.BitOr;
                              
                    }

                    }
                    break;
                case 8 :
                    // /usr/local/lib/jython/grammar/Python.g:711:7: CIRCUMFLEXEQUAL
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    CIRCUMFLEXEQUAL79=(Token)match(input,CIRCUMFLEXEQUAL,FOLLOW_CIRCUMFLEXEQUAL_in_augassign2108); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    CIRCUMFLEXEQUAL79_tree = (PythonTree)adaptor.create(CIRCUMFLEXEQUAL79);
                    adaptor.addChild(root_0, CIRCUMFLEXEQUAL79_tree);
                    }
                    if ( state.backtracking==0 ) {

                                  retval.op = operatorType.BitXor;
                              
                    }

                    }
                    break;
                case 9 :
                    // /usr/local/lib/jython/grammar/Python.g:715:7: LEFTSHIFTEQUAL
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    LEFTSHIFTEQUAL80=(Token)match(input,LEFTSHIFTEQUAL,FOLLOW_LEFTSHIFTEQUAL_in_augassign2126); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    LEFTSHIFTEQUAL80_tree = (PythonTree)adaptor.create(LEFTSHIFTEQUAL80);
                    adaptor.addChild(root_0, LEFTSHIFTEQUAL80_tree);
                    }
                    if ( state.backtracking==0 ) {

                                  retval.op = operatorType.LShift;
                              
                    }

                    }
                    break;
                case 10 :
                    // /usr/local/lib/jython/grammar/Python.g:719:7: RIGHTSHIFTEQUAL
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    RIGHTSHIFTEQUAL81=(Token)match(input,RIGHTSHIFTEQUAL,FOLLOW_RIGHTSHIFTEQUAL_in_augassign2144); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    RIGHTSHIFTEQUAL81_tree = (PythonTree)adaptor.create(RIGHTSHIFTEQUAL81);
                    adaptor.addChild(root_0, RIGHTSHIFTEQUAL81_tree);
                    }
                    if ( state.backtracking==0 ) {

                                  retval.op = operatorType.RShift;
                              
                    }

                    }
                    break;
                case 11 :
                    // /usr/local/lib/jython/grammar/Python.g:723:7: DOUBLESTAREQUAL
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    DOUBLESTAREQUAL82=(Token)match(input,DOUBLESTAREQUAL,FOLLOW_DOUBLESTAREQUAL_in_augassign2162); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    DOUBLESTAREQUAL82_tree = (PythonTree)adaptor.create(DOUBLESTAREQUAL82);
                    adaptor.addChild(root_0, DOUBLESTAREQUAL82_tree);
                    }
                    if ( state.backtracking==0 ) {

                                  retval.op = operatorType.Pow;
                              
                    }

                    }
                    break;
                case 12 :
                    // /usr/local/lib/jython/grammar/Python.g:727:7: DOUBLESLASHEQUAL
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    DOUBLESLASHEQUAL83=(Token)match(input,DOUBLESLASHEQUAL,FOLLOW_DOUBLESLASHEQUAL_in_augassign2180); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    DOUBLESLASHEQUAL83_tree = (PythonTree)adaptor.create(DOUBLESLASHEQUAL83);
                    adaptor.addChild(root_0, DOUBLESLASHEQUAL83_tree);
                    }
                    if ( state.backtracking==0 ) {

                                  retval.op = operatorType.FloorDiv;
                              
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        catch (RecognitionException re) {
            reportError(re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "augassign"

    public static class print_stmt_return extends ParserRuleReturnScope {
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "print_stmt"
    // /usr/local/lib/jython/grammar/Python.g:735:1: print_stmt : PRINT (t1= printlist | RIGHTSHIFT t2= printlist2 | ) ;
    public final PythonParser.print_stmt_return print_stmt() throws RecognitionException {
        PythonParser.print_stmt_return retval = new PythonParser.print_stmt_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token PRINT84=null;
        Token RIGHTSHIFT85=null;
        PythonParser.printlist_return t1 = null;

        PythonParser.printlist2_return t2 = null;


        PythonTree PRINT84_tree=null;
        PythonTree RIGHTSHIFT85_tree=null;


            stmt stype = null;

        try {
            // /usr/local/lib/jython/grammar/Python.g:743:5: ( PRINT (t1= printlist | RIGHTSHIFT t2= printlist2 | ) )
            // /usr/local/lib/jython/grammar/Python.g:743:7: PRINT (t1= printlist | RIGHTSHIFT t2= printlist2 | )
            {
            root_0 = (PythonTree)adaptor.nil();

            PRINT84=(Token)match(input,PRINT,FOLLOW_PRINT_in_print_stmt2220); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            PRINT84_tree = (PythonTree)adaptor.create(PRINT84);
            adaptor.addChild(root_0, PRINT84_tree);
            }
            // /usr/local/lib/jython/grammar/Python.g:744:7: (t1= printlist | RIGHTSHIFT t2= printlist2 | )
            int alt37=3;
            int LA37_0 = input.LA(1);

            if ( (LA37_0==NAME||LA37_0==NOT||LA37_0==LPAREN||(LA37_0>=PLUS && LA37_0<=MINUS)||(LA37_0>=TILDE && LA37_0<=LBRACK)||LA37_0==LCURLY||LA37_0==BACKQUOTE) ) {
                alt37=1;
            }
            else if ( (LA37_0==PRINT) && ((printFunction))) {
                alt37=1;
            }
            else if ( (LA37_0==LAMBDA||(LA37_0>=INT && LA37_0<=STRING)) ) {
                alt37=1;
            }
            else if ( (LA37_0==RIGHTSHIFT) ) {
                alt37=2;
            }
            else if ( (LA37_0==NEWLINE||LA37_0==SEMI) ) {
                alt37=3;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 37, 0, input);

                throw nvae;
            }
            switch (alt37) {
                case 1 :
                    // /usr/local/lib/jython/grammar/Python.g:744:8: t1= printlist
                    {
                    pushFollow(FOLLOW_printlist_in_print_stmt2231);
                    t1=printlist();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, t1.getTree());
                    if ( state.backtracking==0 ) {

                                 stype = new Print(PRINT84, null, actions.castExprs((t1!=null?t1.elts:null)), (t1!=null?t1.newline:false));
                             
                    }

                    }
                    break;
                case 2 :
                    // /usr/local/lib/jython/grammar/Python.g:748:9: RIGHTSHIFT t2= printlist2
                    {
                    RIGHTSHIFT85=(Token)match(input,RIGHTSHIFT,FOLLOW_RIGHTSHIFT_in_print_stmt2250); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    RIGHTSHIFT85_tree = (PythonTree)adaptor.create(RIGHTSHIFT85);
                    adaptor.addChild(root_0, RIGHTSHIFT85_tree);
                    }
                    pushFollow(FOLLOW_printlist2_in_print_stmt2254);
                    t2=printlist2();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, t2.getTree());
                    if ( state.backtracking==0 ) {

                                 stype = new Print(PRINT84, actions.castExpr((t2!=null?t2.elts:null).get(0)), actions.castExprs((t2!=null?t2.elts:null), 1), (t2!=null?t2.newline:false));
                             
                    }

                    }
                    break;
                case 3 :
                    // /usr/local/lib/jython/grammar/Python.g:753:8: 
                    {
                    if ( state.backtracking==0 ) {

                                 stype = new Print(PRINT84, null, new ArrayList<expr>(), true);
                             
                    }

                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {

                  retval.tree = stype;

            }
        }

        catch (RecognitionException re) {
            reportError(re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "print_stmt"

    public static class printlist_return extends ParserRuleReturnScope {
        public boolean newline;
        public List elts;
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "printlist"
    // /usr/local/lib/jython/grammar/Python.g:760:1: printlist returns [boolean newline, List elts] : ( ( test[null] COMMA )=>t+= test[expr_contextType.Load] ( options {k=2; } : COMMA t+= test[expr_contextType.Load] )* (trailcomma= COMMA )? | t+= test[expr_contextType.Load] );
    public final PythonParser.printlist_return printlist() throws RecognitionException {
        PythonParser.printlist_return retval = new PythonParser.printlist_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token trailcomma=null;
        Token COMMA86=null;
        List list_t=null;
        PythonParser.test_return t = null;
         t = null;
        PythonTree trailcomma_tree=null;
        PythonTree COMMA86_tree=null;

        try {
            // /usr/local/lib/jython/grammar/Python.g:762:5: ( ( test[null] COMMA )=>t+= test[expr_contextType.Load] ( options {k=2; } : COMMA t+= test[expr_contextType.Load] )* (trailcomma= COMMA )? | t+= test[expr_contextType.Load] )
            int alt40=2;
            alt40 = dfa40.predict(input);
            switch (alt40) {
                case 1 :
                    // /usr/local/lib/jython/grammar/Python.g:762:7: ( test[null] COMMA )=>t+= test[expr_contextType.Load] ( options {k=2; } : COMMA t+= test[expr_contextType.Load] )* (trailcomma= COMMA )?
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    pushFollow(FOLLOW_test_in_printlist2334);
                    t=test(expr_contextType.Load);

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, t.getTree());
                    if (list_t==null) list_t=new ArrayList();
                    list_t.add(t.getTree());

                    // /usr/local/lib/jython/grammar/Python.g:763:39: ( options {k=2; } : COMMA t+= test[expr_contextType.Load] )*
                    loop38:
                    do {
                        int alt38=2;
                        alt38 = dfa38.predict(input);
                        switch (alt38) {
                    	case 1 :
                    	    // /usr/local/lib/jython/grammar/Python.g:763:56: COMMA t+= test[expr_contextType.Load]
                    	    {
                    	    COMMA86=(Token)match(input,COMMA,FOLLOW_COMMA_in_printlist2346); if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) {
                    	    COMMA86_tree = (PythonTree)adaptor.create(COMMA86);
                    	    adaptor.addChild(root_0, COMMA86_tree);
                    	    }
                    	    pushFollow(FOLLOW_test_in_printlist2350);
                    	    t=test(expr_contextType.Load);

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) adaptor.addChild(root_0, t.getTree());
                    	    if (list_t==null) list_t=new ArrayList();
                    	    list_t.add(t.getTree());


                    	    }
                    	    break;

                    	default :
                    	    break loop38;
                        }
                    } while (true);

                    // /usr/local/lib/jython/grammar/Python.g:763:95: (trailcomma= COMMA )?
                    int alt39=2;
                    int LA39_0 = input.LA(1);

                    if ( (LA39_0==COMMA) ) {
                        alt39=1;
                    }
                    switch (alt39) {
                        case 1 :
                            // /usr/local/lib/jython/grammar/Python.g:763:96: trailcomma= COMMA
                            {
                            trailcomma=(Token)match(input,COMMA,FOLLOW_COMMA_in_printlist2358); if (state.failed) return retval;
                            if ( state.backtracking==0 ) {
                            trailcomma_tree = (PythonTree)adaptor.create(trailcomma);
                            adaptor.addChild(root_0, trailcomma_tree);
                            }

                            }
                            break;

                    }

                    if ( state.backtracking==0 ) {

                                 retval.elts =list_t;
                                 if (trailcomma == null) {
                                     retval.newline = true;
                                 } else {
                                     retval.newline = false;
                                 }
                             
                    }

                    }
                    break;
                case 2 :
                    // /usr/local/lib/jython/grammar/Python.g:772:7: t+= test[expr_contextType.Load]
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    pushFollow(FOLLOW_test_in_printlist2379);
                    t=test(expr_contextType.Load);

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, t.getTree());
                    if (list_t==null) list_t=new ArrayList();
                    list_t.add(t.getTree());

                    if ( state.backtracking==0 ) {

                                retval.elts =list_t;
                                retval.newline = true;
                            
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        catch (RecognitionException re) {
            reportError(re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "printlist"

    public static class printlist2_return extends ParserRuleReturnScope {
        public boolean newline;
        public List elts;
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "printlist2"
    // /usr/local/lib/jython/grammar/Python.g:781:1: printlist2 returns [boolean newline, List elts] : ( ( test[null] COMMA test[null] )=>t+= test[expr_contextType.Load] ( options {k=2; } : COMMA t+= test[expr_contextType.Load] )* (trailcomma= COMMA )? | t+= test[expr_contextType.Load] );
    public final PythonParser.printlist2_return printlist2() throws RecognitionException {
        PythonParser.printlist2_return retval = new PythonParser.printlist2_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token trailcomma=null;
        Token COMMA87=null;
        List list_t=null;
        PythonParser.test_return t = null;
         t = null;
        PythonTree trailcomma_tree=null;
        PythonTree COMMA87_tree=null;

        try {
            // /usr/local/lib/jython/grammar/Python.g:783:5: ( ( test[null] COMMA test[null] )=>t+= test[expr_contextType.Load] ( options {k=2; } : COMMA t+= test[expr_contextType.Load] )* (trailcomma= COMMA )? | t+= test[expr_contextType.Load] )
            int alt43=2;
            alt43 = dfa43.predict(input);
            switch (alt43) {
                case 1 :
                    // /usr/local/lib/jython/grammar/Python.g:783:7: ( test[null] COMMA test[null] )=>t+= test[expr_contextType.Load] ( options {k=2; } : COMMA t+= test[expr_contextType.Load] )* (trailcomma= COMMA )?
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    pushFollow(FOLLOW_test_in_printlist22436);
                    t=test(expr_contextType.Load);

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, t.getTree());
                    if (list_t==null) list_t=new ArrayList();
                    list_t.add(t.getTree());

                    // /usr/local/lib/jython/grammar/Python.g:784:39: ( options {k=2; } : COMMA t+= test[expr_contextType.Load] )*
                    loop41:
                    do {
                        int alt41=2;
                        alt41 = dfa41.predict(input);
                        switch (alt41) {
                    	case 1 :
                    	    // /usr/local/lib/jython/grammar/Python.g:784:56: COMMA t+= test[expr_contextType.Load]
                    	    {
                    	    COMMA87=(Token)match(input,COMMA,FOLLOW_COMMA_in_printlist22448); if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) {
                    	    COMMA87_tree = (PythonTree)adaptor.create(COMMA87);
                    	    adaptor.addChild(root_0, COMMA87_tree);
                    	    }
                    	    pushFollow(FOLLOW_test_in_printlist22452);
                    	    t=test(expr_contextType.Load);

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) adaptor.addChild(root_0, t.getTree());
                    	    if (list_t==null) list_t=new ArrayList();
                    	    list_t.add(t.getTree());


                    	    }
                    	    break;

                    	default :
                    	    break loop41;
                        }
                    } while (true);

                    // /usr/local/lib/jython/grammar/Python.g:784:95: (trailcomma= COMMA )?
                    int alt42=2;
                    int LA42_0 = input.LA(1);

                    if ( (LA42_0==COMMA) ) {
                        alt42=1;
                    }
                    switch (alt42) {
                        case 1 :
                            // /usr/local/lib/jython/grammar/Python.g:784:96: trailcomma= COMMA
                            {
                            trailcomma=(Token)match(input,COMMA,FOLLOW_COMMA_in_printlist22460); if (state.failed) return retval;
                            if ( state.backtracking==0 ) {
                            trailcomma_tree = (PythonTree)adaptor.create(trailcomma);
                            adaptor.addChild(root_0, trailcomma_tree);
                            }

                            }
                            break;

                    }

                    if ( state.backtracking==0 ) {
                       retval.elts =list_t;
                                 if (trailcomma == null) {
                                     retval.newline = true;
                                 } else {
                                     retval.newline = false;
                                 }
                             
                    }

                    }
                    break;
                case 2 :
                    // /usr/local/lib/jython/grammar/Python.g:792:7: t+= test[expr_contextType.Load]
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    pushFollow(FOLLOW_test_in_printlist22481);
                    t=test(expr_contextType.Load);

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, t.getTree());
                    if (list_t==null) list_t=new ArrayList();
                    list_t.add(t.getTree());

                    if ( state.backtracking==0 ) {

                                retval.elts =list_t;
                                retval.newline = true;
                            
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        catch (RecognitionException re) {
            reportError(re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "printlist2"

    public static class del_stmt_return extends ParserRuleReturnScope {
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "del_stmt"
    // /usr/local/lib/jython/grammar/Python.g:800:1: del_stmt : DELETE del_list ;
    public final PythonParser.del_stmt_return del_stmt() throws RecognitionException {
        PythonParser.del_stmt_return retval = new PythonParser.del_stmt_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token DELETE88=null;
        PythonParser.del_list_return del_list89 = null;


        PythonTree DELETE88_tree=null;


            stmt stype = null;

        try {
            // /usr/local/lib/jython/grammar/Python.g:807:5: ( DELETE del_list )
            // /usr/local/lib/jython/grammar/Python.g:807:7: DELETE del_list
            {
            root_0 = (PythonTree)adaptor.nil();

            DELETE88=(Token)match(input,DELETE,FOLLOW_DELETE_in_del_stmt2518); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            DELETE88_tree = (PythonTree)adaptor.create(DELETE88);
            adaptor.addChild(root_0, DELETE88_tree);
            }
            pushFollow(FOLLOW_del_list_in_del_stmt2520);
            del_list89=del_list();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, del_list89.getTree());
            if ( state.backtracking==0 ) {

                        stype = new Delete(DELETE88, (del_list89!=null?del_list89.etypes:null));
                    
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {

                 retval.tree = stype;

            }
        }

        catch (RecognitionException re) {
            reportError(re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "del_stmt"

    public static class pass_stmt_return extends ParserRuleReturnScope {
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "pass_stmt"
    // /usr/local/lib/jython/grammar/Python.g:814:1: pass_stmt : PASS ;
    public final PythonParser.pass_stmt_return pass_stmt() throws RecognitionException {
        PythonParser.pass_stmt_return retval = new PythonParser.pass_stmt_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token PASS90=null;

        PythonTree PASS90_tree=null;


            stmt stype = null;

        try {
            // /usr/local/lib/jython/grammar/Python.g:821:5: ( PASS )
            // /usr/local/lib/jython/grammar/Python.g:821:7: PASS
            {
            root_0 = (PythonTree)adaptor.nil();

            PASS90=(Token)match(input,PASS,FOLLOW_PASS_in_pass_stmt2556); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            PASS90_tree = (PythonTree)adaptor.create(PASS90);
            adaptor.addChild(root_0, PASS90_tree);
            }
            if ( state.backtracking==0 ) {

                        stype = new Pass(PASS90);
                    
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {

                 retval.tree = stype;

            }
        }

        catch (RecognitionException re) {
            reportError(re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "pass_stmt"

    public static class flow_stmt_return extends ParserRuleReturnScope {
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "flow_stmt"
    // /usr/local/lib/jython/grammar/Python.g:828:1: flow_stmt : ( break_stmt | continue_stmt | return_stmt | raise_stmt | yield_stmt );
    public final PythonParser.flow_stmt_return flow_stmt() throws RecognitionException {
        PythonParser.flow_stmt_return retval = new PythonParser.flow_stmt_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        PythonParser.break_stmt_return break_stmt91 = null;

        PythonParser.continue_stmt_return continue_stmt92 = null;

        PythonParser.return_stmt_return return_stmt93 = null;

        PythonParser.raise_stmt_return raise_stmt94 = null;

        PythonParser.yield_stmt_return yield_stmt95 = null;



        try {
            // /usr/local/lib/jython/grammar/Python.g:829:5: ( break_stmt | continue_stmt | return_stmt | raise_stmt | yield_stmt )
            int alt44=5;
            switch ( input.LA(1) ) {
            case BREAK:
                {
                alt44=1;
                }
                break;
            case CONTINUE:
                {
                alt44=2;
                }
                break;
            case RETURN:
                {
                alt44=3;
                }
                break;
            case RAISE:
                {
                alt44=4;
                }
                break;
            case YIELD:
                {
                alt44=5;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 44, 0, input);

                throw nvae;
            }

            switch (alt44) {
                case 1 :
                    // /usr/local/lib/jython/grammar/Python.g:829:7: break_stmt
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    pushFollow(FOLLOW_break_stmt_in_flow_stmt2582);
                    break_stmt91=break_stmt();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, break_stmt91.getTree());

                    }
                    break;
                case 2 :
                    // /usr/local/lib/jython/grammar/Python.g:830:7: continue_stmt
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    pushFollow(FOLLOW_continue_stmt_in_flow_stmt2590);
                    continue_stmt92=continue_stmt();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, continue_stmt92.getTree());

                    }
                    break;
                case 3 :
                    // /usr/local/lib/jython/grammar/Python.g:831:7: return_stmt
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    pushFollow(FOLLOW_return_stmt_in_flow_stmt2598);
                    return_stmt93=return_stmt();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, return_stmt93.getTree());

                    }
                    break;
                case 4 :
                    // /usr/local/lib/jython/grammar/Python.g:832:7: raise_stmt
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    pushFollow(FOLLOW_raise_stmt_in_flow_stmt2606);
                    raise_stmt94=raise_stmt();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, raise_stmt94.getTree());

                    }
                    break;
                case 5 :
                    // /usr/local/lib/jython/grammar/Python.g:833:7: yield_stmt
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    pushFollow(FOLLOW_yield_stmt_in_flow_stmt2614);
                    yield_stmt95=yield_stmt();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, yield_stmt95.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        catch (RecognitionException re) {
            reportError(re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "flow_stmt"

    public static class break_stmt_return extends ParserRuleReturnScope {
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "break_stmt"
    // /usr/local/lib/jython/grammar/Python.g:837:1: break_stmt : BREAK ;
    public final PythonParser.break_stmt_return break_stmt() throws RecognitionException {
        PythonParser.break_stmt_return retval = new PythonParser.break_stmt_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token BREAK96=null;

        PythonTree BREAK96_tree=null;


            stmt stype = null;

        try {
            // /usr/local/lib/jython/grammar/Python.g:844:5: ( BREAK )
            // /usr/local/lib/jython/grammar/Python.g:844:7: BREAK
            {
            root_0 = (PythonTree)adaptor.nil();

            BREAK96=(Token)match(input,BREAK,FOLLOW_BREAK_in_break_stmt2642); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            BREAK96_tree = (PythonTree)adaptor.create(BREAK96);
            adaptor.addChild(root_0, BREAK96_tree);
            }
            if ( state.backtracking==0 ) {

                        stype = new Break(BREAK96);
                    
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {

                 retval.tree = stype;

            }
        }

        catch (RecognitionException re) {
            reportError(re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "break_stmt"

    public static class continue_stmt_return extends ParserRuleReturnScope {
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "continue_stmt"
    // /usr/local/lib/jython/grammar/Python.g:851:1: continue_stmt : CONTINUE ;
    public final PythonParser.continue_stmt_return continue_stmt() throws RecognitionException {
        PythonParser.continue_stmt_return retval = new PythonParser.continue_stmt_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token CONTINUE97=null;

        PythonTree CONTINUE97_tree=null;


            stmt stype = null;

        try {
            // /usr/local/lib/jython/grammar/Python.g:858:5: ( CONTINUE )
            // /usr/local/lib/jython/grammar/Python.g:858:7: CONTINUE
            {
            root_0 = (PythonTree)adaptor.nil();

            CONTINUE97=(Token)match(input,CONTINUE,FOLLOW_CONTINUE_in_continue_stmt2678); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            CONTINUE97_tree = (PythonTree)adaptor.create(CONTINUE97);
            adaptor.addChild(root_0, CONTINUE97_tree);
            }
            if ( state.backtracking==0 ) {

                        if (!suite_stack.isEmpty() && ((suite_scope)suite_stack.peek()).continueIllegal) {
                            errorHandler.error("'continue' not supported inside 'finally' clause", new PythonTree(((Token)retval.start)));
                        }
                        stype = new Continue(CONTINUE97);
                    
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {

                 retval.tree = stype;

            }
        }

        catch (RecognitionException re) {
            reportError(re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "continue_stmt"

    public static class return_stmt_return extends ParserRuleReturnScope {
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "return_stmt"
    // /usr/local/lib/jython/grammar/Python.g:868:1: return_stmt : RETURN ( testlist[expr_contextType.Load] | ) ;
    public final PythonParser.return_stmt_return return_stmt() throws RecognitionException {
        PythonParser.return_stmt_return retval = new PythonParser.return_stmt_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token RETURN98=null;
        PythonParser.testlist_return testlist99 = null;


        PythonTree RETURN98_tree=null;


            stmt stype = null;

        try {
            // /usr/local/lib/jython/grammar/Python.g:875:5: ( RETURN ( testlist[expr_contextType.Load] | ) )
            // /usr/local/lib/jython/grammar/Python.g:875:7: RETURN ( testlist[expr_contextType.Load] | )
            {
            root_0 = (PythonTree)adaptor.nil();

            RETURN98=(Token)match(input,RETURN,FOLLOW_RETURN_in_return_stmt2714); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            RETURN98_tree = (PythonTree)adaptor.create(RETURN98);
            adaptor.addChild(root_0, RETURN98_tree);
            }
            // /usr/local/lib/jython/grammar/Python.g:876:7: ( testlist[expr_contextType.Load] | )
            int alt45=2;
            int LA45_0 = input.LA(1);

            if ( (LA45_0==NAME||LA45_0==NOT||LA45_0==LPAREN||(LA45_0>=PLUS && LA45_0<=MINUS)||(LA45_0>=TILDE && LA45_0<=LBRACK)||LA45_0==LCURLY||LA45_0==BACKQUOTE) ) {
                alt45=1;
            }
            else if ( (LA45_0==PRINT) && ((printFunction))) {
                alt45=1;
            }
            else if ( (LA45_0==LAMBDA||(LA45_0>=INT && LA45_0<=STRING)) ) {
                alt45=1;
            }
            else if ( (LA45_0==NEWLINE||LA45_0==SEMI) ) {
                alt45=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 45, 0, input);

                throw nvae;
            }
            switch (alt45) {
                case 1 :
                    // /usr/local/lib/jython/grammar/Python.g:876:8: testlist[expr_contextType.Load]
                    {
                    pushFollow(FOLLOW_testlist_in_return_stmt2723);
                    testlist99=testlist(expr_contextType.Load);

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, testlist99.getTree());
                    if ( state.backtracking==0 ) {

                                 stype = new Return(RETURN98, actions.castExpr((testlist99!=null?((PythonTree)testlist99.tree):null)));
                             
                    }

                    }
                    break;
                case 2 :
                    // /usr/local/lib/jython/grammar/Python.g:881:8: 
                    {
                    if ( state.backtracking==0 ) {

                                 stype = new Return(RETURN98, null);
                             
                    }

                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {

                 retval.tree = stype;

            }
        }

        catch (RecognitionException re) {
            reportError(re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "return_stmt"

    public static class yield_stmt_return extends ParserRuleReturnScope {
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "yield_stmt"
    // /usr/local/lib/jython/grammar/Python.g:888:1: yield_stmt : yield_expr ;
    public final PythonParser.yield_stmt_return yield_stmt() throws RecognitionException {
        PythonParser.yield_stmt_return retval = new PythonParser.yield_stmt_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        PythonParser.yield_expr_return yield_expr100 = null;




            stmt stype = null;

        try {
            // /usr/local/lib/jython/grammar/Python.g:895:5: ( yield_expr )
            // /usr/local/lib/jython/grammar/Python.g:895:7: yield_expr
            {
            root_0 = (PythonTree)adaptor.nil();

            pushFollow(FOLLOW_yield_expr_in_yield_stmt2788);
            yield_expr100=yield_expr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, yield_expr100.getTree());
            if ( state.backtracking==0 ) {

                      stype = new Expr((yield_expr100!=null?((Token)yield_expr100.start):null), actions.castExpr((yield_expr100!=null?yield_expr100.etype:null)));
                    
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {

                 retval.tree = stype;

            }
        }

        catch (RecognitionException re) {
            reportError(re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "yield_stmt"

    public static class raise_stmt_return extends ParserRuleReturnScope {
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "raise_stmt"
    // /usr/local/lib/jython/grammar/Python.g:902:1: raise_stmt : RAISE (t1= test[expr_contextType.Load] ( COMMA t2= test[expr_contextType.Load] ( COMMA t3= test[expr_contextType.Load] )? )? )? ;
    public final PythonParser.raise_stmt_return raise_stmt() throws RecognitionException {
        PythonParser.raise_stmt_return retval = new PythonParser.raise_stmt_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token RAISE101=null;
        Token COMMA102=null;
        Token COMMA103=null;
        PythonParser.test_return t1 = null;

        PythonParser.test_return t2 = null;

        PythonParser.test_return t3 = null;


        PythonTree RAISE101_tree=null;
        PythonTree COMMA102_tree=null;
        PythonTree COMMA103_tree=null;


            stmt stype = null;

        try {
            // /usr/local/lib/jython/grammar/Python.g:909:5: ( RAISE (t1= test[expr_contextType.Load] ( COMMA t2= test[expr_contextType.Load] ( COMMA t3= test[expr_contextType.Load] )? )? )? )
            // /usr/local/lib/jython/grammar/Python.g:909:7: RAISE (t1= test[expr_contextType.Load] ( COMMA t2= test[expr_contextType.Load] ( COMMA t3= test[expr_contextType.Load] )? )? )?
            {
            root_0 = (PythonTree)adaptor.nil();

            RAISE101=(Token)match(input,RAISE,FOLLOW_RAISE_in_raise_stmt2824); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            RAISE101_tree = (PythonTree)adaptor.create(RAISE101);
            adaptor.addChild(root_0, RAISE101_tree);
            }
            // /usr/local/lib/jython/grammar/Python.g:909:13: (t1= test[expr_contextType.Load] ( COMMA t2= test[expr_contextType.Load] ( COMMA t3= test[expr_contextType.Load] )? )? )?
            int alt48=2;
            int LA48_0 = input.LA(1);

            if ( (LA48_0==NAME||LA48_0==NOT||LA48_0==LPAREN||(LA48_0>=PLUS && LA48_0<=MINUS)||(LA48_0>=TILDE && LA48_0<=LBRACK)||LA48_0==LCURLY||LA48_0==BACKQUOTE) ) {
                alt48=1;
            }
            else if ( (LA48_0==PRINT) && ((printFunction))) {
                alt48=1;
            }
            else if ( (LA48_0==LAMBDA||(LA48_0>=INT && LA48_0<=STRING)) ) {
                alt48=1;
            }
            switch (alt48) {
                case 1 :
                    // /usr/local/lib/jython/grammar/Python.g:909:14: t1= test[expr_contextType.Load] ( COMMA t2= test[expr_contextType.Load] ( COMMA t3= test[expr_contextType.Load] )? )?
                    {
                    pushFollow(FOLLOW_test_in_raise_stmt2829);
                    t1=test(expr_contextType.Load);

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, t1.getTree());
                    // /usr/local/lib/jython/grammar/Python.g:909:45: ( COMMA t2= test[expr_contextType.Load] ( COMMA t3= test[expr_contextType.Load] )? )?
                    int alt47=2;
                    int LA47_0 = input.LA(1);

                    if ( (LA47_0==COMMA) ) {
                        alt47=1;
                    }
                    switch (alt47) {
                        case 1 :
                            // /usr/local/lib/jython/grammar/Python.g:909:46: COMMA t2= test[expr_contextType.Load] ( COMMA t3= test[expr_contextType.Load] )?
                            {
                            COMMA102=(Token)match(input,COMMA,FOLLOW_COMMA_in_raise_stmt2833); if (state.failed) return retval;
                            if ( state.backtracking==0 ) {
                            COMMA102_tree = (PythonTree)adaptor.create(COMMA102);
                            adaptor.addChild(root_0, COMMA102_tree);
                            }
                            pushFollow(FOLLOW_test_in_raise_stmt2837);
                            t2=test(expr_contextType.Load);

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) adaptor.addChild(root_0, t2.getTree());
                            // /usr/local/lib/jython/grammar/Python.g:910:9: ( COMMA t3= test[expr_contextType.Load] )?
                            int alt46=2;
                            int LA46_0 = input.LA(1);

                            if ( (LA46_0==COMMA) ) {
                                alt46=1;
                            }
                            switch (alt46) {
                                case 1 :
                                    // /usr/local/lib/jython/grammar/Python.g:910:10: COMMA t3= test[expr_contextType.Load]
                                    {
                                    COMMA103=(Token)match(input,COMMA,FOLLOW_COMMA_in_raise_stmt2849); if (state.failed) return retval;
                                    if ( state.backtracking==0 ) {
                                    COMMA103_tree = (PythonTree)adaptor.create(COMMA103);
                                    adaptor.addChild(root_0, COMMA103_tree);
                                    }
                                    pushFollow(FOLLOW_test_in_raise_stmt2853);
                                    t3=test(expr_contextType.Load);

                                    state._fsp--;
                                    if (state.failed) return retval;
                                    if ( state.backtracking==0 ) adaptor.addChild(root_0, t3.getTree());

                                    }
                                    break;

                            }


                            }
                            break;

                    }


                    }
                    break;

            }

            if ( state.backtracking==0 ) {

                        stype = new Raise(RAISE101, actions.castExpr((t1!=null?((PythonTree)t1.tree):null)), actions.castExpr((t2!=null?((PythonTree)t2.tree):null)), actions.castExpr((t3!=null?((PythonTree)t3.tree):null)));
                    
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {

                 retval.tree = stype;

            }
        }

        catch (RecognitionException re) {
            reportError(re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "raise_stmt"

    public static class import_stmt_return extends ParserRuleReturnScope {
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "import_stmt"
    // /usr/local/lib/jython/grammar/Python.g:917:1: import_stmt : ( import_name | import_from );
    public final PythonParser.import_stmt_return import_stmt() throws RecognitionException {
        PythonParser.import_stmt_return retval = new PythonParser.import_stmt_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        PythonParser.import_name_return import_name104 = null;

        PythonParser.import_from_return import_from105 = null;



        try {
            // /usr/local/lib/jython/grammar/Python.g:918:5: ( import_name | import_from )
            int alt49=2;
            int LA49_0 = input.LA(1);

            if ( (LA49_0==IMPORT) ) {
                alt49=1;
            }
            else if ( (LA49_0==FROM) ) {
                alt49=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 49, 0, input);

                throw nvae;
            }
            switch (alt49) {
                case 1 :
                    // /usr/local/lib/jython/grammar/Python.g:918:7: import_name
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    pushFollow(FOLLOW_import_name_in_import_stmt2886);
                    import_name104=import_name();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, import_name104.getTree());

                    }
                    break;
                case 2 :
                    // /usr/local/lib/jython/grammar/Python.g:919:7: import_from
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    pushFollow(FOLLOW_import_from_in_import_stmt2894);
                    import_from105=import_from();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, import_from105.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        catch (RecognitionException re) {
            reportError(re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "import_stmt"

    public static class import_name_return extends ParserRuleReturnScope {
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "import_name"
    // /usr/local/lib/jython/grammar/Python.g:923:1: import_name : IMPORT dotted_as_names ;
    public final PythonParser.import_name_return import_name() throws RecognitionException {
        PythonParser.import_name_return retval = new PythonParser.import_name_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token IMPORT106=null;
        PythonParser.dotted_as_names_return dotted_as_names107 = null;


        PythonTree IMPORT106_tree=null;


            stmt stype = null;

        try {
            // /usr/local/lib/jython/grammar/Python.g:930:5: ( IMPORT dotted_as_names )
            // /usr/local/lib/jython/grammar/Python.g:930:7: IMPORT dotted_as_names
            {
            root_0 = (PythonTree)adaptor.nil();

            IMPORT106=(Token)match(input,IMPORT,FOLLOW_IMPORT_in_import_name2922); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            IMPORT106_tree = (PythonTree)adaptor.create(IMPORT106);
            adaptor.addChild(root_0, IMPORT106_tree);
            }
            pushFollow(FOLLOW_dotted_as_names_in_import_name2924);
            dotted_as_names107=dotted_as_names();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, dotted_as_names107.getTree());
            if ( state.backtracking==0 ) {

                        stype = new Import(IMPORT106, (dotted_as_names107!=null?dotted_as_names107.atypes:null));
                    
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {

                 retval.tree = stype;

            }
        }

        catch (RecognitionException re) {
            reportError(re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "import_name"

    public static class import_from_return extends ParserRuleReturnScope {
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "import_from"
    // /usr/local/lib/jython/grammar/Python.g:938:1: import_from : FROM ( (d+= DOT )* dotted_name | (d+= DOT )+ ) IMPORT ( STAR | i1= import_as_names | LPAREN i2= import_as_names ( COMMA )? RPAREN ) ;
    public final PythonParser.import_from_return import_from() throws RecognitionException {
        PythonParser.import_from_return retval = new PythonParser.import_from_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token FROM108=null;
        Token IMPORT110=null;
        Token STAR111=null;
        Token LPAREN112=null;
        Token COMMA113=null;
        Token RPAREN114=null;
        Token d=null;
        List list_d=null;
        PythonParser.import_as_names_return i1 = null;

        PythonParser.import_as_names_return i2 = null;

        PythonParser.dotted_name_return dotted_name109 = null;


        PythonTree FROM108_tree=null;
        PythonTree IMPORT110_tree=null;
        PythonTree STAR111_tree=null;
        PythonTree LPAREN112_tree=null;
        PythonTree COMMA113_tree=null;
        PythonTree RPAREN114_tree=null;
        PythonTree d_tree=null;


            stmt stype = null;

        try {
            // /usr/local/lib/jython/grammar/Python.g:945:5: ( FROM ( (d+= DOT )* dotted_name | (d+= DOT )+ ) IMPORT ( STAR | i1= import_as_names | LPAREN i2= import_as_names ( COMMA )? RPAREN ) )
            // /usr/local/lib/jython/grammar/Python.g:945:7: FROM ( (d+= DOT )* dotted_name | (d+= DOT )+ ) IMPORT ( STAR | i1= import_as_names | LPAREN i2= import_as_names ( COMMA )? RPAREN )
            {
            root_0 = (PythonTree)adaptor.nil();

            FROM108=(Token)match(input,FROM,FOLLOW_FROM_in_import_from2961); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            FROM108_tree = (PythonTree)adaptor.create(FROM108);
            adaptor.addChild(root_0, FROM108_tree);
            }
            // /usr/local/lib/jython/grammar/Python.g:945:12: ( (d+= DOT )* dotted_name | (d+= DOT )+ )
            int alt52=2;
            alt52 = dfa52.predict(input);
            switch (alt52) {
                case 1 :
                    // /usr/local/lib/jython/grammar/Python.g:945:13: (d+= DOT )* dotted_name
                    {
                    // /usr/local/lib/jython/grammar/Python.g:945:14: (d+= DOT )*
                    loop50:
                    do {
                        int alt50=2;
                        int LA50_0 = input.LA(1);

                        if ( (LA50_0==DOT) ) {
                            alt50=1;
                        }


                        switch (alt50) {
                    	case 1 :
                    	    // /usr/local/lib/jython/grammar/Python.g:945:14: d+= DOT
                    	    {
                    	    d=(Token)match(input,DOT,FOLLOW_DOT_in_import_from2966); if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) {
                    	    d_tree = (PythonTree)adaptor.create(d);
                    	    adaptor.addChild(root_0, d_tree);
                    	    }
                    	    if (list_d==null) list_d=new ArrayList();
                    	    list_d.add(d);


                    	    }
                    	    break;

                    	default :
                    	    break loop50;
                        }
                    } while (true);

                    pushFollow(FOLLOW_dotted_name_in_import_from2969);
                    dotted_name109=dotted_name();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, dotted_name109.getTree());

                    }
                    break;
                case 2 :
                    // /usr/local/lib/jython/grammar/Python.g:945:35: (d+= DOT )+
                    {
                    // /usr/local/lib/jython/grammar/Python.g:945:36: (d+= DOT )+
                    int cnt51=0;
                    loop51:
                    do {
                        int alt51=2;
                        int LA51_0 = input.LA(1);

                        if ( (LA51_0==DOT) ) {
                            alt51=1;
                        }


                        switch (alt51) {
                    	case 1 :
                    	    // /usr/local/lib/jython/grammar/Python.g:945:36: d+= DOT
                    	    {
                    	    d=(Token)match(input,DOT,FOLLOW_DOT_in_import_from2975); if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) {
                    	    d_tree = (PythonTree)adaptor.create(d);
                    	    adaptor.addChild(root_0, d_tree);
                    	    }
                    	    if (list_d==null) list_d=new ArrayList();
                    	    list_d.add(d);


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt51 >= 1 ) break loop51;
                    	    if (state.backtracking>0) {state.failed=true; return retval;}
                                EarlyExitException eee =
                                    new EarlyExitException(51, input);
                                throw eee;
                        }
                        cnt51++;
                    } while (true);


                    }
                    break;

            }

            IMPORT110=(Token)match(input,IMPORT,FOLLOW_IMPORT_in_import_from2979); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            IMPORT110_tree = (PythonTree)adaptor.create(IMPORT110);
            adaptor.addChild(root_0, IMPORT110_tree);
            }
            // /usr/local/lib/jython/grammar/Python.g:946:9: ( STAR | i1= import_as_names | LPAREN i2= import_as_names ( COMMA )? RPAREN )
            int alt54=3;
            switch ( input.LA(1) ) {
            case STAR:
                {
                alt54=1;
                }
                break;
            case NAME:
                {
                alt54=2;
                }
                break;
            case LPAREN:
                {
                alt54=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 54, 0, input);

                throw nvae;
            }

            switch (alt54) {
                case 1 :
                    // /usr/local/lib/jython/grammar/Python.g:946:10: STAR
                    {
                    STAR111=(Token)match(input,STAR,FOLLOW_STAR_in_import_from2990); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    STAR111_tree = (PythonTree)adaptor.create(STAR111);
                    adaptor.addChild(root_0, STAR111_tree);
                    }
                    if ( state.backtracking==0 ) {

                                   stype = new ImportFrom(FROM108, actions.makeFromText(list_d, (dotted_name109!=null?dotted_name109.names:null)),
                                       actions.makeModuleNameNode(list_d, (dotted_name109!=null?dotted_name109.names:null)),
                                       actions.makeStarAlias(STAR111), actions.makeLevel(list_d));
                               
                    }

                    }
                    break;
                case 2 :
                    // /usr/local/lib/jython/grammar/Python.g:952:11: i1= import_as_names
                    {
                    pushFollow(FOLLOW_import_as_names_in_import_from3015);
                    i1=import_as_names();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, i1.getTree());
                    if ( state.backtracking==0 ) {

                                   String dottedText = (dotted_name109!=null?input.toString(dotted_name109.start,dotted_name109.stop):null);
                                   if (dottedText != null && dottedText.equals("__future__")) {
                                       List<alias> aliases = (i1!=null?i1.atypes:null);
                                       for(alias a: aliases) {
                                           if (a != null) {
                                               if (a.getInternalName().equals("print_function")) {
                                                   printFunction = true;
                                               } else if (a.getInternalName().equals("unicode_literals")) {
                                                   unicodeLiterals = true;
                                               }
                                           }
                                       }
                                   }
                                   stype = new ImportFrom(FROM108, actions.makeFromText(list_d, (dotted_name109!=null?dotted_name109.names:null)),
                                       actions.makeModuleNameNode(list_d, (dotted_name109!=null?dotted_name109.names:null)),
                                       actions.makeAliases((i1!=null?i1.atypes:null)), actions.makeLevel(list_d));
                               
                    }

                    }
                    break;
                case 3 :
                    // /usr/local/lib/jython/grammar/Python.g:971:11: LPAREN i2= import_as_names ( COMMA )? RPAREN
                    {
                    LPAREN112=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_import_from3038); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    LPAREN112_tree = (PythonTree)adaptor.create(LPAREN112);
                    adaptor.addChild(root_0, LPAREN112_tree);
                    }
                    pushFollow(FOLLOW_import_as_names_in_import_from3042);
                    i2=import_as_names();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, i2.getTree());
                    // /usr/local/lib/jython/grammar/Python.g:971:37: ( COMMA )?
                    int alt53=2;
                    int LA53_0 = input.LA(1);

                    if ( (LA53_0==COMMA) ) {
                        alt53=1;
                    }
                    switch (alt53) {
                        case 1 :
                            // /usr/local/lib/jython/grammar/Python.g:971:37: COMMA
                            {
                            COMMA113=(Token)match(input,COMMA,FOLLOW_COMMA_in_import_from3044); if (state.failed) return retval;
                            if ( state.backtracking==0 ) {
                            COMMA113_tree = (PythonTree)adaptor.create(COMMA113);
                            adaptor.addChild(root_0, COMMA113_tree);
                            }

                            }
                            break;

                    }

                    RPAREN114=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_import_from3047); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    RPAREN114_tree = (PythonTree)adaptor.create(RPAREN114);
                    adaptor.addChild(root_0, RPAREN114_tree);
                    }
                    if ( state.backtracking==0 ) {

                                   //XXX: this is almost a complete C&P of the code above - is there some way
                                   //     to factor it out?
                                   String dottedText = (dotted_name109!=null?input.toString(dotted_name109.start,dotted_name109.stop):null);
                                   if (dottedText != null && dottedText.equals("__future__")) {
                                       List<alias> aliases = (i2!=null?i2.atypes:null);
                                       for(alias a: aliases) {
                                           if (a != null) {
                                               if (a.getInternalName().equals("print_function")) {
                                                   printFunction = true;
                                               } else if (a.getInternalName().equals("unicode_literals")) {
                                                   unicodeLiterals = true;
                                               }
                                           }
                                       }
                                   }
                                   stype = new ImportFrom(FROM108, actions.makeFromText(list_d, (dotted_name109!=null?dotted_name109.names:null)),
                                       actions.makeModuleNameNode(list_d, (dotted_name109!=null?dotted_name109.names:null)),
                                       actions.makeAliases((i2!=null?i2.atypes:null)), actions.makeLevel(list_d));
                               
                    }

                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {

                 retval.tree = stype;

            }
        }

        catch (RecognitionException re) {
            reportError(re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "import_from"

    public static class import_as_names_return extends ParserRuleReturnScope {
        public List<alias> atypes;
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "import_as_names"
    // /usr/local/lib/jython/grammar/Python.g:996:1: import_as_names returns [List<alias> atypes] : n+= import_as_name ( COMMA n+= import_as_name )* ;
    public final PythonParser.import_as_names_return import_as_names() throws RecognitionException {
        PythonParser.import_as_names_return retval = new PythonParser.import_as_names_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token COMMA115=null;
        List list_n=null;
        PythonParser.import_as_name_return n = null;
         n = null;
        PythonTree COMMA115_tree=null;

        try {
            // /usr/local/lib/jython/grammar/Python.g:998:5: (n+= import_as_name ( COMMA n+= import_as_name )* )
            // /usr/local/lib/jython/grammar/Python.g:998:7: n+= import_as_name ( COMMA n+= import_as_name )*
            {
            root_0 = (PythonTree)adaptor.nil();

            pushFollow(FOLLOW_import_as_name_in_import_as_names3096);
            n=import_as_name();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, n.getTree());
            if (list_n==null) list_n=new ArrayList();
            list_n.add(n.getTree());

            // /usr/local/lib/jython/grammar/Python.g:998:25: ( COMMA n+= import_as_name )*
            loop55:
            do {
                int alt55=2;
                int LA55_0 = input.LA(1);

                if ( (LA55_0==COMMA) ) {
                    int LA55_2 = input.LA(2);

                    if ( (LA55_2==NAME) ) {
                        alt55=1;
                    }


                }


                switch (alt55) {
            	case 1 :
            	    // /usr/local/lib/jython/grammar/Python.g:998:26: COMMA n+= import_as_name
            	    {
            	    COMMA115=(Token)match(input,COMMA,FOLLOW_COMMA_in_import_as_names3099); if (state.failed) return retval;
            	    pushFollow(FOLLOW_import_as_name_in_import_as_names3104);
            	    n=import_as_name();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, n.getTree());
            	    if (list_n==null) list_n=new ArrayList();
            	    list_n.add(n.getTree());


            	    }
            	    break;

            	default :
            	    break loop55;
                }
            } while (true);

            if ( state.backtracking==0 ) {

                      retval.atypes = list_n;
                  
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        catch (RecognitionException re) {
            reportError(re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "import_as_names"

    public static class import_as_name_return extends ParserRuleReturnScope {
        public alias atype;
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "import_as_name"
    // /usr/local/lib/jython/grammar/Python.g:1005:1: import_as_name returns [alias atype] : name= NAME ( AS asname= NAME )? ;
    public final PythonParser.import_as_name_return import_as_name() throws RecognitionException {
        PythonParser.import_as_name_return retval = new PythonParser.import_as_name_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token name=null;
        Token asname=null;
        Token AS116=null;

        PythonTree name_tree=null;
        PythonTree asname_tree=null;
        PythonTree AS116_tree=null;

        try {
            // /usr/local/lib/jython/grammar/Python.g:1010:5: (name= NAME ( AS asname= NAME )? )
            // /usr/local/lib/jython/grammar/Python.g:1010:7: name= NAME ( AS asname= NAME )?
            {
            root_0 = (PythonTree)adaptor.nil();

            name=(Token)match(input,NAME,FOLLOW_NAME_in_import_as_name3145); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            name_tree = (PythonTree)adaptor.create(name);
            adaptor.addChild(root_0, name_tree);
            }
            // /usr/local/lib/jython/grammar/Python.g:1010:17: ( AS asname= NAME )?
            int alt56=2;
            int LA56_0 = input.LA(1);

            if ( (LA56_0==AS) ) {
                alt56=1;
            }
            switch (alt56) {
                case 1 :
                    // /usr/local/lib/jython/grammar/Python.g:1010:18: AS asname= NAME
                    {
                    AS116=(Token)match(input,AS,FOLLOW_AS_in_import_as_name3148); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    AS116_tree = (PythonTree)adaptor.create(AS116);
                    adaptor.addChild(root_0, AS116_tree);
                    }
                    asname=(Token)match(input,NAME,FOLLOW_NAME_in_import_as_name3152); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    asname_tree = (PythonTree)adaptor.create(asname);
                    adaptor.addChild(root_0, asname_tree);
                    }

                    }
                    break;

            }

            if ( state.backtracking==0 ) {

                      retval.atype = new alias(actions.makeNameNode(name), actions.makeNameNode(asname));
                  
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {

                  retval.tree = retval.atype;

            }
        }

        catch (RecognitionException re) {
            reportError(re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "import_as_name"

    public static class dotted_as_name_return extends ParserRuleReturnScope {
        public alias atype;
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "dotted_as_name"
    // /usr/local/lib/jython/grammar/Python.g:1018:1: dotted_as_name returns [alias atype] : dotted_name ( AS asname= NAME )? ;
    public final PythonParser.dotted_as_name_return dotted_as_name() throws RecognitionException {
        PythonParser.dotted_as_name_return retval = new PythonParser.dotted_as_name_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token asname=null;
        Token AS118=null;
        PythonParser.dotted_name_return dotted_name117 = null;


        PythonTree asname_tree=null;
        PythonTree AS118_tree=null;

        try {
            // /usr/local/lib/jython/grammar/Python.g:1023:5: ( dotted_name ( AS asname= NAME )? )
            // /usr/local/lib/jython/grammar/Python.g:1023:7: dotted_name ( AS asname= NAME )?
            {
            root_0 = (PythonTree)adaptor.nil();

            pushFollow(FOLLOW_dotted_name_in_dotted_as_name3192);
            dotted_name117=dotted_name();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, dotted_name117.getTree());
            // /usr/local/lib/jython/grammar/Python.g:1023:19: ( AS asname= NAME )?
            int alt57=2;
            int LA57_0 = input.LA(1);

            if ( (LA57_0==AS) ) {
                alt57=1;
            }
            switch (alt57) {
                case 1 :
                    // /usr/local/lib/jython/grammar/Python.g:1023:20: AS asname= NAME
                    {
                    AS118=(Token)match(input,AS,FOLLOW_AS_in_dotted_as_name3195); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    AS118_tree = (PythonTree)adaptor.create(AS118);
                    adaptor.addChild(root_0, AS118_tree);
                    }
                    asname=(Token)match(input,NAME,FOLLOW_NAME_in_dotted_as_name3199); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    asname_tree = (PythonTree)adaptor.create(asname);
                    adaptor.addChild(root_0, asname_tree);
                    }

                    }
                    break;

            }

            if ( state.backtracking==0 ) {

                      retval.atype = new alias((dotted_name117!=null?dotted_name117.names:null), actions.makeNameNode(asname));
                  
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {

                  retval.tree = retval.atype;

            }
        }

        catch (RecognitionException re) {
            reportError(re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "dotted_as_name"

    public static class dotted_as_names_return extends ParserRuleReturnScope {
        public List<alias> atypes;
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "dotted_as_names"
    // /usr/local/lib/jython/grammar/Python.g:1030:1: dotted_as_names returns [List<alias> atypes] : d+= dotted_as_name ( COMMA d+= dotted_as_name )* ;
    public final PythonParser.dotted_as_names_return dotted_as_names() throws RecognitionException {
        PythonParser.dotted_as_names_return retval = new PythonParser.dotted_as_names_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token COMMA119=null;
        List list_d=null;
        PythonParser.dotted_as_name_return d = null;
         d = null;
        PythonTree COMMA119_tree=null;

        try {
            // /usr/local/lib/jython/grammar/Python.g:1032:5: (d+= dotted_as_name ( COMMA d+= dotted_as_name )* )
            // /usr/local/lib/jython/grammar/Python.g:1032:7: d+= dotted_as_name ( COMMA d+= dotted_as_name )*
            {
            root_0 = (PythonTree)adaptor.nil();

            pushFollow(FOLLOW_dotted_as_name_in_dotted_as_names3235);
            d=dotted_as_name();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, d.getTree());
            if (list_d==null) list_d=new ArrayList();
            list_d.add(d.getTree());

            // /usr/local/lib/jython/grammar/Python.g:1032:25: ( COMMA d+= dotted_as_name )*
            loop58:
            do {
                int alt58=2;
                int LA58_0 = input.LA(1);

                if ( (LA58_0==COMMA) ) {
                    alt58=1;
                }


                switch (alt58) {
            	case 1 :
            	    // /usr/local/lib/jython/grammar/Python.g:1032:26: COMMA d+= dotted_as_name
            	    {
            	    COMMA119=(Token)match(input,COMMA,FOLLOW_COMMA_in_dotted_as_names3238); if (state.failed) return retval;
            	    pushFollow(FOLLOW_dotted_as_name_in_dotted_as_names3243);
            	    d=dotted_as_name();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, d.getTree());
            	    if (list_d==null) list_d=new ArrayList();
            	    list_d.add(d.getTree());


            	    }
            	    break;

            	default :
            	    break loop58;
                }
            } while (true);

            if ( state.backtracking==0 ) {

                      retval.atypes = list_d;
                  
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        catch (RecognitionException re) {
            reportError(re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "dotted_as_names"

    public static class dotted_name_return extends ParserRuleReturnScope {
        public List<Name> names;
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "dotted_name"
    // /usr/local/lib/jython/grammar/Python.g:1039:1: dotted_name returns [List<Name> names] : NAME ( DOT dn+= attr )* ;
    public final PythonParser.dotted_name_return dotted_name() throws RecognitionException {
        PythonParser.dotted_name_return retval = new PythonParser.dotted_name_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token NAME120=null;
        Token DOT121=null;
        List list_dn=null;
        PythonParser.attr_return dn = null;
         dn = null;
        PythonTree NAME120_tree=null;
        PythonTree DOT121_tree=null;

        try {
            // /usr/local/lib/jython/grammar/Python.g:1041:5: ( NAME ( DOT dn+= attr )* )
            // /usr/local/lib/jython/grammar/Python.g:1041:7: NAME ( DOT dn+= attr )*
            {
            root_0 = (PythonTree)adaptor.nil();

            NAME120=(Token)match(input,NAME,FOLLOW_NAME_in_dotted_name3277); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            NAME120_tree = (PythonTree)adaptor.create(NAME120);
            adaptor.addChild(root_0, NAME120_tree);
            }
            // /usr/local/lib/jython/grammar/Python.g:1041:12: ( DOT dn+= attr )*
            loop59:
            do {
                int alt59=2;
                int LA59_0 = input.LA(1);

                if ( (LA59_0==DOT) ) {
                    alt59=1;
                }


                switch (alt59) {
            	case 1 :
            	    // /usr/local/lib/jython/grammar/Python.g:1041:13: DOT dn+= attr
            	    {
            	    DOT121=(Token)match(input,DOT,FOLLOW_DOT_in_dotted_name3280); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    DOT121_tree = (PythonTree)adaptor.create(DOT121);
            	    adaptor.addChild(root_0, DOT121_tree);
            	    }
            	    pushFollow(FOLLOW_attr_in_dotted_name3284);
            	    dn=attr();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, dn.getTree());
            	    if (list_dn==null) list_dn=new ArrayList();
            	    list_dn.add(dn.getTree());


            	    }
            	    break;

            	default :
            	    break loop59;
                }
            } while (true);

            if ( state.backtracking==0 ) {

                      retval.names = actions.makeDottedName(NAME120, list_dn);
                  
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        catch (RecognitionException re) {
            reportError(re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "dotted_name"

    public static class global_stmt_return extends ParserRuleReturnScope {
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "global_stmt"
    // /usr/local/lib/jython/grammar/Python.g:1048:1: global_stmt : GLOBAL n+= NAME ( COMMA n+= NAME )* ;
    public final PythonParser.global_stmt_return global_stmt() throws RecognitionException {
        PythonParser.global_stmt_return retval = new PythonParser.global_stmt_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token GLOBAL122=null;
        Token COMMA123=null;
        Token n=null;
        List list_n=null;

        PythonTree GLOBAL122_tree=null;
        PythonTree COMMA123_tree=null;
        PythonTree n_tree=null;


            stmt stype = null;

        try {
            // /usr/local/lib/jython/grammar/Python.g:1055:5: ( GLOBAL n+= NAME ( COMMA n+= NAME )* )
            // /usr/local/lib/jython/grammar/Python.g:1055:7: GLOBAL n+= NAME ( COMMA n+= NAME )*
            {
            root_0 = (PythonTree)adaptor.nil();

            GLOBAL122=(Token)match(input,GLOBAL,FOLLOW_GLOBAL_in_global_stmt3320); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            GLOBAL122_tree = (PythonTree)adaptor.create(GLOBAL122);
            adaptor.addChild(root_0, GLOBAL122_tree);
            }
            n=(Token)match(input,NAME,FOLLOW_NAME_in_global_stmt3324); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            n_tree = (PythonTree)adaptor.create(n);
            adaptor.addChild(root_0, n_tree);
            }
            if (list_n==null) list_n=new ArrayList();
            list_n.add(n);

            // /usr/local/lib/jython/grammar/Python.g:1055:22: ( COMMA n+= NAME )*
            loop60:
            do {
                int alt60=2;
                int LA60_0 = input.LA(1);

                if ( (LA60_0==COMMA) ) {
                    alt60=1;
                }


                switch (alt60) {
            	case 1 :
            	    // /usr/local/lib/jython/grammar/Python.g:1055:23: COMMA n+= NAME
            	    {
            	    COMMA123=(Token)match(input,COMMA,FOLLOW_COMMA_in_global_stmt3327); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    COMMA123_tree = (PythonTree)adaptor.create(COMMA123);
            	    adaptor.addChild(root_0, COMMA123_tree);
            	    }
            	    n=(Token)match(input,NAME,FOLLOW_NAME_in_global_stmt3331); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    n_tree = (PythonTree)adaptor.create(n);
            	    adaptor.addChild(root_0, n_tree);
            	    }
            	    if (list_n==null) list_n=new ArrayList();
            	    list_n.add(n);


            	    }
            	    break;

            	default :
            	    break loop60;
                }
            } while (true);

            if ( state.backtracking==0 ) {

                        stype = new Global(GLOBAL122, actions.makeNames(list_n), actions.makeNameNodes(list_n));
                    
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {

                 retval.tree = stype;

            }
        }

        catch (RecognitionException re) {
            reportError(re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "global_stmt"

    public static class exec_stmt_return extends ParserRuleReturnScope {
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "exec_stmt"
    // /usr/local/lib/jython/grammar/Python.g:1062:1: exec_stmt : EXEC expr[expr_contextType.Load] ( IN t1= test[expr_contextType.Load] ( COMMA t2= test[expr_contextType.Load] )? )? ;
    public final PythonParser.exec_stmt_return exec_stmt() throws RecognitionException {
        PythonParser.exec_stmt_return retval = new PythonParser.exec_stmt_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token EXEC124=null;
        Token IN126=null;
        Token COMMA127=null;
        PythonParser.test_return t1 = null;

        PythonParser.test_return t2 = null;

        PythonParser.expr_return expr125 = null;


        PythonTree EXEC124_tree=null;
        PythonTree IN126_tree=null;
        PythonTree COMMA127_tree=null;


            stmt stype = null;

        try {
            // /usr/local/lib/jython/grammar/Python.g:1069:5: ( EXEC expr[expr_contextType.Load] ( IN t1= test[expr_contextType.Load] ( COMMA t2= test[expr_contextType.Load] )? )? )
            // /usr/local/lib/jython/grammar/Python.g:1069:7: EXEC expr[expr_contextType.Load] ( IN t1= test[expr_contextType.Load] ( COMMA t2= test[expr_contextType.Load] )? )?
            {
            root_0 = (PythonTree)adaptor.nil();

            EXEC124=(Token)match(input,EXEC,FOLLOW_EXEC_in_exec_stmt3369); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            EXEC124_tree = (PythonTree)adaptor.create(EXEC124);
            adaptor.addChild(root_0, EXEC124_tree);
            }
            pushFollow(FOLLOW_expr_in_exec_stmt3371);
            expr125=expr(expr_contextType.Load);

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, expr125.getTree());
            // /usr/local/lib/jython/grammar/Python.g:1069:40: ( IN t1= test[expr_contextType.Load] ( COMMA t2= test[expr_contextType.Load] )? )?
            int alt62=2;
            int LA62_0 = input.LA(1);

            if ( (LA62_0==IN) ) {
                alt62=1;
            }
            switch (alt62) {
                case 1 :
                    // /usr/local/lib/jython/grammar/Python.g:1069:41: IN t1= test[expr_contextType.Load] ( COMMA t2= test[expr_contextType.Load] )?
                    {
                    IN126=(Token)match(input,IN,FOLLOW_IN_in_exec_stmt3375); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    IN126_tree = (PythonTree)adaptor.create(IN126);
                    adaptor.addChild(root_0, IN126_tree);
                    }
                    pushFollow(FOLLOW_test_in_exec_stmt3379);
                    t1=test(expr_contextType.Load);

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, t1.getTree());
                    // /usr/local/lib/jython/grammar/Python.g:1069:75: ( COMMA t2= test[expr_contextType.Load] )?
                    int alt61=2;
                    int LA61_0 = input.LA(1);

                    if ( (LA61_0==COMMA) ) {
                        alt61=1;
                    }
                    switch (alt61) {
                        case 1 :
                            // /usr/local/lib/jython/grammar/Python.g:1069:76: COMMA t2= test[expr_contextType.Load]
                            {
                            COMMA127=(Token)match(input,COMMA,FOLLOW_COMMA_in_exec_stmt3383); if (state.failed) return retval;
                            if ( state.backtracking==0 ) {
                            COMMA127_tree = (PythonTree)adaptor.create(COMMA127);
                            adaptor.addChild(root_0, COMMA127_tree);
                            }
                            pushFollow(FOLLOW_test_in_exec_stmt3387);
                            t2=test(expr_contextType.Load);

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) adaptor.addChild(root_0, t2.getTree());

                            }
                            break;

                    }


                    }
                    break;

            }

            if ( state.backtracking==0 ) {

                       stype = new Exec(EXEC124, actions.castExpr((expr125!=null?((PythonTree)expr125.tree):null)), actions.castExpr((t1!=null?((PythonTree)t1.tree):null)), actions.castExpr((t2!=null?((PythonTree)t2.tree):null)));
                    
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {

                 retval.tree = stype;

            }
        }

        catch (RecognitionException re) {
            reportError(re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "exec_stmt"

    public static class assert_stmt_return extends ParserRuleReturnScope {
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "assert_stmt"
    // /usr/local/lib/jython/grammar/Python.g:1076:1: assert_stmt : ASSERT t1= test[expr_contextType.Load] ( COMMA t2= test[expr_contextType.Load] )? ;
    public final PythonParser.assert_stmt_return assert_stmt() throws RecognitionException {
        PythonParser.assert_stmt_return retval = new PythonParser.assert_stmt_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token ASSERT128=null;
        Token COMMA129=null;
        PythonParser.test_return t1 = null;

        PythonParser.test_return t2 = null;


        PythonTree ASSERT128_tree=null;
        PythonTree COMMA129_tree=null;


            stmt stype = null;

        try {
            // /usr/local/lib/jython/grammar/Python.g:1083:5: ( ASSERT t1= test[expr_contextType.Load] ( COMMA t2= test[expr_contextType.Load] )? )
            // /usr/local/lib/jython/grammar/Python.g:1083:7: ASSERT t1= test[expr_contextType.Load] ( COMMA t2= test[expr_contextType.Load] )?
            {
            root_0 = (PythonTree)adaptor.nil();

            ASSERT128=(Token)match(input,ASSERT,FOLLOW_ASSERT_in_assert_stmt3428); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            ASSERT128_tree = (PythonTree)adaptor.create(ASSERT128);
            adaptor.addChild(root_0, ASSERT128_tree);
            }
            pushFollow(FOLLOW_test_in_assert_stmt3432);
            t1=test(expr_contextType.Load);

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, t1.getTree());
            // /usr/local/lib/jython/grammar/Python.g:1083:45: ( COMMA t2= test[expr_contextType.Load] )?
            int alt63=2;
            int LA63_0 = input.LA(1);

            if ( (LA63_0==COMMA) ) {
                alt63=1;
            }
            switch (alt63) {
                case 1 :
                    // /usr/local/lib/jython/grammar/Python.g:1083:46: COMMA t2= test[expr_contextType.Load]
                    {
                    COMMA129=(Token)match(input,COMMA,FOLLOW_COMMA_in_assert_stmt3436); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    COMMA129_tree = (PythonTree)adaptor.create(COMMA129);
                    adaptor.addChild(root_0, COMMA129_tree);
                    }
                    pushFollow(FOLLOW_test_in_assert_stmt3440);
                    t2=test(expr_contextType.Load);

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, t2.getTree());

                    }
                    break;

            }

            if ( state.backtracking==0 ) {

                        stype = new Assert(ASSERT128, actions.castExpr((t1!=null?((PythonTree)t1.tree):null)), actions.castExpr((t2!=null?((PythonTree)t2.tree):null)));
                    
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {

                 retval.tree = stype;

            }
        }

        catch (RecognitionException re) {
            reportError(re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "assert_stmt"

    public static class compound_stmt_return extends ParserRuleReturnScope {
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "compound_stmt"
    // /usr/local/lib/jython/grammar/Python.g:1090:1: compound_stmt : ( if_stmt | while_stmt | for_stmt | try_stmt | with_stmt | ( ( decorators )? DEF )=> funcdef | classdef );
    public final PythonParser.compound_stmt_return compound_stmt() throws RecognitionException {
        PythonParser.compound_stmt_return retval = new PythonParser.compound_stmt_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        PythonParser.if_stmt_return if_stmt130 = null;

        PythonParser.while_stmt_return while_stmt131 = null;

        PythonParser.for_stmt_return for_stmt132 = null;

        PythonParser.try_stmt_return try_stmt133 = null;

        PythonParser.with_stmt_return with_stmt134 = null;

        PythonParser.funcdef_return funcdef135 = null;

        PythonParser.classdef_return classdef136 = null;



        try {
            // /usr/local/lib/jython/grammar/Python.g:1091:5: ( if_stmt | while_stmt | for_stmt | try_stmt | with_stmt | ( ( decorators )? DEF )=> funcdef | classdef )
            int alt64=7;
            int LA64_0 = input.LA(1);

            if ( (LA64_0==IF) ) {
                alt64=1;
            }
            else if ( (LA64_0==WHILE) ) {
                alt64=2;
            }
            else if ( (LA64_0==FOR) ) {
                alt64=3;
            }
            else if ( (LA64_0==TRY) ) {
                alt64=4;
            }
            else if ( (LA64_0==WITH) ) {
                alt64=5;
            }
            else if ( (LA64_0==AT) ) {
                int LA64_6 = input.LA(2);

                if ( (synpred6_Python()) ) {
                    alt64=6;
                }
                else if ( (true) ) {
                    alt64=7;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 64, 6, input);

                    throw nvae;
                }
            }
            else if ( (LA64_0==DEF) && (synpred6_Python())) {
                alt64=6;
            }
            else if ( (LA64_0==CLASS) ) {
                alt64=7;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 64, 0, input);

                throw nvae;
            }
            switch (alt64) {
                case 1 :
                    // /usr/local/lib/jython/grammar/Python.g:1091:7: if_stmt
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    pushFollow(FOLLOW_if_stmt_in_compound_stmt3469);
                    if_stmt130=if_stmt();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, if_stmt130.getTree());

                    }
                    break;
                case 2 :
                    // /usr/local/lib/jython/grammar/Python.g:1092:7: while_stmt
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    pushFollow(FOLLOW_while_stmt_in_compound_stmt3477);
                    while_stmt131=while_stmt();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, while_stmt131.getTree());

                    }
                    break;
                case 3 :
                    // /usr/local/lib/jython/grammar/Python.g:1093:7: for_stmt
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    pushFollow(FOLLOW_for_stmt_in_compound_stmt3485);
                    for_stmt132=for_stmt();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, for_stmt132.getTree());

                    }
                    break;
                case 4 :
                    // /usr/local/lib/jython/grammar/Python.g:1094:7: try_stmt
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    pushFollow(FOLLOW_try_stmt_in_compound_stmt3493);
                    try_stmt133=try_stmt();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, try_stmt133.getTree());

                    }
                    break;
                case 5 :
                    // /usr/local/lib/jython/grammar/Python.g:1095:7: with_stmt
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    pushFollow(FOLLOW_with_stmt_in_compound_stmt3501);
                    with_stmt134=with_stmt();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, with_stmt134.getTree());

                    }
                    break;
                case 6 :
                    // /usr/local/lib/jython/grammar/Python.g:1096:7: ( ( decorators )? DEF )=> funcdef
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    pushFollow(FOLLOW_funcdef_in_compound_stmt3518);
                    funcdef135=funcdef();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, funcdef135.getTree());

                    }
                    break;
                case 7 :
                    // /usr/local/lib/jython/grammar/Python.g:1097:7: classdef
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    pushFollow(FOLLOW_classdef_in_compound_stmt3526);
                    classdef136=classdef();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, classdef136.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        catch (RecognitionException re) {
            reportError(re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "compound_stmt"

    public static class if_stmt_return extends ParserRuleReturnScope {
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "if_stmt"
    // /usr/local/lib/jython/grammar/Python.g:1101:1: if_stmt : IF test[expr_contextType.Load] COLON ifsuite= suite[false] ( elif_clause )? ;
    public final PythonParser.if_stmt_return if_stmt() throws RecognitionException {
        PythonParser.if_stmt_return retval = new PythonParser.if_stmt_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token IF137=null;
        Token COLON139=null;
        PythonParser.suite_return ifsuite = null;

        PythonParser.test_return test138 = null;

        PythonParser.elif_clause_return elif_clause140 = null;


        PythonTree IF137_tree=null;
        PythonTree COLON139_tree=null;


            stmt stype = null;

        try {
            // /usr/local/lib/jython/grammar/Python.g:1108:5: ( IF test[expr_contextType.Load] COLON ifsuite= suite[false] ( elif_clause )? )
            // /usr/local/lib/jython/grammar/Python.g:1108:7: IF test[expr_contextType.Load] COLON ifsuite= suite[false] ( elif_clause )?
            {
            root_0 = (PythonTree)adaptor.nil();

            IF137=(Token)match(input,IF,FOLLOW_IF_in_if_stmt3554); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            IF137_tree = (PythonTree)adaptor.create(IF137);
            adaptor.addChild(root_0, IF137_tree);
            }
            pushFollow(FOLLOW_test_in_if_stmt3556);
            test138=test(expr_contextType.Load);

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, test138.getTree());
            COLON139=(Token)match(input,COLON,FOLLOW_COLON_in_if_stmt3559); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            COLON139_tree = (PythonTree)adaptor.create(COLON139);
            adaptor.addChild(root_0, COLON139_tree);
            }
            pushFollow(FOLLOW_suite_in_if_stmt3563);
            ifsuite=suite(false);

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, ifsuite.getTree());
            // /usr/local/lib/jython/grammar/Python.g:1108:65: ( elif_clause )?
            int alt65=2;
            int LA65_0 = input.LA(1);

            if ( (LA65_0==ELIF||LA65_0==ORELSE) ) {
                alt65=1;
            }
            switch (alt65) {
                case 1 :
                    // /usr/local/lib/jython/grammar/Python.g:1108:65: elif_clause
                    {
                    pushFollow(FOLLOW_elif_clause_in_if_stmt3566);
                    elif_clause140=elif_clause();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, elif_clause140.getTree());

                    }
                    break;

            }

            if ( state.backtracking==0 ) {

                        stype = new If(IF137, actions.castExpr((test138!=null?((PythonTree)test138.tree):null)), actions.castStmts((ifsuite!=null?ifsuite.stypes:null)),
                            actions.makeElse((elif_clause140!=null?elif_clause140.stypes:null), (elif_clause140!=null?((PythonTree)elif_clause140.tree):null)));
                    
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {

                 retval.tree = stype;

            }
        }

        catch (RecognitionException re) {
            reportError(re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "if_stmt"

    public static class elif_clause_return extends ParserRuleReturnScope {
        public List stypes;
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "elif_clause"
    // /usr/local/lib/jython/grammar/Python.g:1116:1: elif_clause returns [List stypes] : ( else_clause | ELIF test[expr_contextType.Load] COLON suite[false] (e2= elif_clause | ) );
    public final PythonParser.elif_clause_return elif_clause() throws RecognitionException {
        PythonParser.elif_clause_return retval = new PythonParser.elif_clause_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token ELIF142=null;
        Token COLON144=null;
        PythonParser.elif_clause_return e2 = null;

        PythonParser.else_clause_return else_clause141 = null;

        PythonParser.test_return test143 = null;

        PythonParser.suite_return suite145 = null;


        PythonTree ELIF142_tree=null;
        PythonTree COLON144_tree=null;


            stmt stype = null;

        try {
            // /usr/local/lib/jython/grammar/Python.g:1126:5: ( else_clause | ELIF test[expr_contextType.Load] COLON suite[false] (e2= elif_clause | ) )
            int alt67=2;
            int LA67_0 = input.LA(1);

            if ( (LA67_0==ORELSE) ) {
                alt67=1;
            }
            else if ( (LA67_0==ELIF) ) {
                alt67=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 67, 0, input);

                throw nvae;
            }
            switch (alt67) {
                case 1 :
                    // /usr/local/lib/jython/grammar/Python.g:1126:7: else_clause
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    pushFollow(FOLLOW_else_clause_in_elif_clause3611);
                    else_clause141=else_clause();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, else_clause141.getTree());
                    if ( state.backtracking==0 ) {

                                retval.stypes = (else_clause141!=null?else_clause141.stypes:null);
                            
                    }

                    }
                    break;
                case 2 :
                    // /usr/local/lib/jython/grammar/Python.g:1130:7: ELIF test[expr_contextType.Load] COLON suite[false] (e2= elif_clause | )
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    ELIF142=(Token)match(input,ELIF,FOLLOW_ELIF_in_elif_clause3627); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    ELIF142_tree = (PythonTree)adaptor.create(ELIF142);
                    adaptor.addChild(root_0, ELIF142_tree);
                    }
                    pushFollow(FOLLOW_test_in_elif_clause3629);
                    test143=test(expr_contextType.Load);

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, test143.getTree());
                    COLON144=(Token)match(input,COLON,FOLLOW_COLON_in_elif_clause3632); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    COLON144_tree = (PythonTree)adaptor.create(COLON144);
                    adaptor.addChild(root_0, COLON144_tree);
                    }
                    pushFollow(FOLLOW_suite_in_elif_clause3634);
                    suite145=suite(false);

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, suite145.getTree());
                    // /usr/local/lib/jython/grammar/Python.g:1131:7: (e2= elif_clause | )
                    int alt66=2;
                    int LA66_0 = input.LA(1);

                    if ( (LA66_0==ELIF||LA66_0==ORELSE) ) {
                        alt66=1;
                    }
                    else if ( (LA66_0==EOF||LA66_0==DEDENT||LA66_0==NEWLINE||LA66_0==NAME||LA66_0==PRINT||(LA66_0>=ASSERT && LA66_0<=DELETE)||LA66_0==EXEC||(LA66_0>=FROM && LA66_0<=IMPORT)||(LA66_0>=LAMBDA && LA66_0<=NOT)||(LA66_0>=PASS && LA66_0<=LPAREN)||(LA66_0>=PLUS && LA66_0<=MINUS)||(LA66_0>=TILDE && LA66_0<=LBRACK)||LA66_0==LCURLY||(LA66_0>=BACKQUOTE && LA66_0<=STRING)) ) {
                        alt66=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 66, 0, input);

                        throw nvae;
                    }
                    switch (alt66) {
                        case 1 :
                            // /usr/local/lib/jython/grammar/Python.g:1131:8: e2= elif_clause
                            {
                            pushFollow(FOLLOW_elif_clause_in_elif_clause3646);
                            e2=elif_clause();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) adaptor.addChild(root_0, e2.getTree());
                            if ( state.backtracking==0 ) {

                                         stype = new If((test143!=null?((Token)test143.start):null), actions.castExpr((test143!=null?((PythonTree)test143.tree):null)), actions.castStmts((suite145!=null?suite145.stypes:null)), actions.makeElse((e2!=null?e2.stypes:null), (e2!=null?((PythonTree)e2.tree):null)));
                                     
                            }

                            }
                            break;
                        case 2 :
                            // /usr/local/lib/jython/grammar/Python.g:1136:8: 
                            {
                            if ( state.backtracking==0 ) {

                                         stype = new If((test143!=null?((Token)test143.start):null), actions.castExpr((test143!=null?((PythonTree)test143.tree):null)), actions.castStmts((suite145!=null?suite145.stypes:null)), new ArrayList<stmt>());
                                     
                            }

                            }
                            break;

                    }


                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {

                 if (stype != null) {
                     retval.tree = stype;
                 }

            }
        }

        catch (RecognitionException re) {
            reportError(re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "elif_clause"

    public static class else_clause_return extends ParserRuleReturnScope {
        public List stypes;
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "else_clause"
    // /usr/local/lib/jython/grammar/Python.g:1143:1: else_clause returns [List stypes] : ORELSE COLON elsesuite= suite[false] ;
    public final PythonParser.else_clause_return else_clause() throws RecognitionException {
        PythonParser.else_clause_return retval = new PythonParser.else_clause_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token ORELSE146=null;
        Token COLON147=null;
        PythonParser.suite_return elsesuite = null;


        PythonTree ORELSE146_tree=null;
        PythonTree COLON147_tree=null;

        try {
            // /usr/local/lib/jython/grammar/Python.g:1145:5: ( ORELSE COLON elsesuite= suite[false] )
            // /usr/local/lib/jython/grammar/Python.g:1145:7: ORELSE COLON elsesuite= suite[false]
            {
            root_0 = (PythonTree)adaptor.nil();

            ORELSE146=(Token)match(input,ORELSE,FOLLOW_ORELSE_in_else_clause3706); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            ORELSE146_tree = (PythonTree)adaptor.create(ORELSE146);
            adaptor.addChild(root_0, ORELSE146_tree);
            }
            COLON147=(Token)match(input,COLON,FOLLOW_COLON_in_else_clause3708); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            COLON147_tree = (PythonTree)adaptor.create(COLON147);
            adaptor.addChild(root_0, COLON147_tree);
            }
            pushFollow(FOLLOW_suite_in_else_clause3712);
            elsesuite=suite(false);

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, elsesuite.getTree());
            if ( state.backtracking==0 ) {

                        retval.stypes = (elsesuite!=null?elsesuite.stypes:null);
                    
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        catch (RecognitionException re) {
            reportError(re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "else_clause"

    public static class while_stmt_return extends ParserRuleReturnScope {
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "while_stmt"
    // /usr/local/lib/jython/grammar/Python.g:1152:1: while_stmt : WHILE test[expr_contextType.Load] COLON s1= suite[false] ( ORELSE COLON s2= suite[false] )? ;
    public final PythonParser.while_stmt_return while_stmt() throws RecognitionException {
        PythonParser.while_stmt_return retval = new PythonParser.while_stmt_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token WHILE148=null;
        Token COLON150=null;
        Token ORELSE151=null;
        Token COLON152=null;
        PythonParser.suite_return s1 = null;

        PythonParser.suite_return s2 = null;

        PythonParser.test_return test149 = null;


        PythonTree WHILE148_tree=null;
        PythonTree COLON150_tree=null;
        PythonTree ORELSE151_tree=null;
        PythonTree COLON152_tree=null;


            stmt stype = null;

        try {
            // /usr/local/lib/jython/grammar/Python.g:1159:5: ( WHILE test[expr_contextType.Load] COLON s1= suite[false] ( ORELSE COLON s2= suite[false] )? )
            // /usr/local/lib/jython/grammar/Python.g:1159:7: WHILE test[expr_contextType.Load] COLON s1= suite[false] ( ORELSE COLON s2= suite[false] )?
            {
            root_0 = (PythonTree)adaptor.nil();

            WHILE148=(Token)match(input,WHILE,FOLLOW_WHILE_in_while_stmt3749); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            WHILE148_tree = (PythonTree)adaptor.create(WHILE148);
            adaptor.addChild(root_0, WHILE148_tree);
            }
            pushFollow(FOLLOW_test_in_while_stmt3751);
            test149=test(expr_contextType.Load);

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, test149.getTree());
            COLON150=(Token)match(input,COLON,FOLLOW_COLON_in_while_stmt3754); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            COLON150_tree = (PythonTree)adaptor.create(COLON150);
            adaptor.addChild(root_0, COLON150_tree);
            }
            pushFollow(FOLLOW_suite_in_while_stmt3758);
            s1=suite(false);

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, s1.getTree());
            // /usr/local/lib/jython/grammar/Python.g:1159:63: ( ORELSE COLON s2= suite[false] )?
            int alt68=2;
            int LA68_0 = input.LA(1);

            if ( (LA68_0==ORELSE) ) {
                alt68=1;
            }
            switch (alt68) {
                case 1 :
                    // /usr/local/lib/jython/grammar/Python.g:1159:64: ORELSE COLON s2= suite[false]
                    {
                    ORELSE151=(Token)match(input,ORELSE,FOLLOW_ORELSE_in_while_stmt3762); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    ORELSE151_tree = (PythonTree)adaptor.create(ORELSE151);
                    adaptor.addChild(root_0, ORELSE151_tree);
                    }
                    COLON152=(Token)match(input,COLON,FOLLOW_COLON_in_while_stmt3764); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    COLON152_tree = (PythonTree)adaptor.create(COLON152);
                    adaptor.addChild(root_0, COLON152_tree);
                    }
                    pushFollow(FOLLOW_suite_in_while_stmt3768);
                    s2=suite(false);

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, s2.getTree());

                    }
                    break;

            }

            if ( state.backtracking==0 ) {

                        stype = actions.makeWhile(WHILE148, actions.castExpr((test149!=null?((PythonTree)test149.tree):null)), (s1!=null?s1.stypes:null), (s2!=null?s2.stypes:null));
                    
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {

                 retval.tree = stype;

            }
        }

        catch (RecognitionException re) {
            reportError(re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "while_stmt"

    public static class for_stmt_return extends ParserRuleReturnScope {
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "for_stmt"
    // /usr/local/lib/jython/grammar/Python.g:1166:1: for_stmt : FOR exprlist[expr_contextType.Store] IN testlist[expr_contextType.Load] COLON s1= suite[false] ( ORELSE COLON s2= suite[false] )? ;
    public final PythonParser.for_stmt_return for_stmt() throws RecognitionException {
        PythonParser.for_stmt_return retval = new PythonParser.for_stmt_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token FOR153=null;
        Token IN155=null;
        Token COLON157=null;
        Token ORELSE158=null;
        Token COLON159=null;
        PythonParser.suite_return s1 = null;

        PythonParser.suite_return s2 = null;

        PythonParser.exprlist_return exprlist154 = null;

        PythonParser.testlist_return testlist156 = null;


        PythonTree FOR153_tree=null;
        PythonTree IN155_tree=null;
        PythonTree COLON157_tree=null;
        PythonTree ORELSE158_tree=null;
        PythonTree COLON159_tree=null;


            stmt stype = null;

        try {
            // /usr/local/lib/jython/grammar/Python.g:1173:5: ( FOR exprlist[expr_contextType.Store] IN testlist[expr_contextType.Load] COLON s1= suite[false] ( ORELSE COLON s2= suite[false] )? )
            // /usr/local/lib/jython/grammar/Python.g:1173:7: FOR exprlist[expr_contextType.Store] IN testlist[expr_contextType.Load] COLON s1= suite[false] ( ORELSE COLON s2= suite[false] )?
            {
            root_0 = (PythonTree)adaptor.nil();

            FOR153=(Token)match(input,FOR,FOLLOW_FOR_in_for_stmt3807); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            FOR153_tree = (PythonTree)adaptor.create(FOR153);
            adaptor.addChild(root_0, FOR153_tree);
            }
            pushFollow(FOLLOW_exprlist_in_for_stmt3809);
            exprlist154=exprlist(expr_contextType.Store);

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, exprlist154.getTree());
            IN155=(Token)match(input,IN,FOLLOW_IN_in_for_stmt3812); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            IN155_tree = (PythonTree)adaptor.create(IN155);
            adaptor.addChild(root_0, IN155_tree);
            }
            pushFollow(FOLLOW_testlist_in_for_stmt3814);
            testlist156=testlist(expr_contextType.Load);

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, testlist156.getTree());
            COLON157=(Token)match(input,COLON,FOLLOW_COLON_in_for_stmt3817); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            COLON157_tree = (PythonTree)adaptor.create(COLON157);
            adaptor.addChild(root_0, COLON157_tree);
            }
            pushFollow(FOLLOW_suite_in_for_stmt3821);
            s1=suite(false);

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, s1.getTree());
            // /usr/local/lib/jython/grammar/Python.g:1174:9: ( ORELSE COLON s2= suite[false] )?
            int alt69=2;
            int LA69_0 = input.LA(1);

            if ( (LA69_0==ORELSE) ) {
                alt69=1;
            }
            switch (alt69) {
                case 1 :
                    // /usr/local/lib/jython/grammar/Python.g:1174:10: ORELSE COLON s2= suite[false]
                    {
                    ORELSE158=(Token)match(input,ORELSE,FOLLOW_ORELSE_in_for_stmt3833); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    ORELSE158_tree = (PythonTree)adaptor.create(ORELSE158);
                    adaptor.addChild(root_0, ORELSE158_tree);
                    }
                    COLON159=(Token)match(input,COLON,FOLLOW_COLON_in_for_stmt3835); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    COLON159_tree = (PythonTree)adaptor.create(COLON159);
                    adaptor.addChild(root_0, COLON159_tree);
                    }
                    pushFollow(FOLLOW_suite_in_for_stmt3839);
                    s2=suite(false);

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, s2.getTree());

                    }
                    break;

            }

            if ( state.backtracking==0 ) {

                        stype = actions.makeFor(FOR153, (exprlist154!=null?exprlist154.etype:null), actions.castExpr((testlist156!=null?((PythonTree)testlist156.tree):null)), (s1!=null?s1.stypes:null), (s2!=null?s2.stypes:null));
                    
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {

                 retval.tree = stype;

            }
        }

        catch (RecognitionException re) {
            reportError(re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "for_stmt"

    public static class try_stmt_return extends ParserRuleReturnScope {
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "try_stmt"
    // /usr/local/lib/jython/grammar/Python.g:1185:1: try_stmt : TRY COLON trysuite= suite[!$suite.isEmpty() && $suite::continueIllegal] ( (e+= except_clause )+ ( ORELSE COLON elsesuite= suite[!$suite.isEmpty() && $suite::continueIllegal] )? ( FINALLY COLON finalsuite= suite[true] )? | FINALLY COLON finalsuite= suite[true] ) ;
    public final PythonParser.try_stmt_return try_stmt() throws RecognitionException {
        PythonParser.try_stmt_return retval = new PythonParser.try_stmt_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token TRY160=null;
        Token COLON161=null;
        Token ORELSE162=null;
        Token COLON163=null;
        Token FINALLY164=null;
        Token COLON165=null;
        Token FINALLY166=null;
        Token COLON167=null;
        List list_e=null;
        PythonParser.suite_return trysuite = null;

        PythonParser.suite_return elsesuite = null;

        PythonParser.suite_return finalsuite = null;

        PythonParser.except_clause_return e = null;
         e = null;
        PythonTree TRY160_tree=null;
        PythonTree COLON161_tree=null;
        PythonTree ORELSE162_tree=null;
        PythonTree COLON163_tree=null;
        PythonTree FINALLY164_tree=null;
        PythonTree COLON165_tree=null;
        PythonTree FINALLY166_tree=null;
        PythonTree COLON167_tree=null;


            stmt stype = null;

        try {
            // /usr/local/lib/jython/grammar/Python.g:1192:5: ( TRY COLON trysuite= suite[!$suite.isEmpty() && $suite::continueIllegal] ( (e+= except_clause )+ ( ORELSE COLON elsesuite= suite[!$suite.isEmpty() && $suite::continueIllegal] )? ( FINALLY COLON finalsuite= suite[true] )? | FINALLY COLON finalsuite= suite[true] ) )
            // /usr/local/lib/jython/grammar/Python.g:1192:7: TRY COLON trysuite= suite[!$suite.isEmpty() && $suite::continueIllegal] ( (e+= except_clause )+ ( ORELSE COLON elsesuite= suite[!$suite.isEmpty() && $suite::continueIllegal] )? ( FINALLY COLON finalsuite= suite[true] )? | FINALLY COLON finalsuite= suite[true] )
            {
            root_0 = (PythonTree)adaptor.nil();

            TRY160=(Token)match(input,TRY,FOLLOW_TRY_in_try_stmt3882); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            TRY160_tree = (PythonTree)adaptor.create(TRY160);
            adaptor.addChild(root_0, TRY160_tree);
            }
            COLON161=(Token)match(input,COLON,FOLLOW_COLON_in_try_stmt3884); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            COLON161_tree = (PythonTree)adaptor.create(COLON161);
            adaptor.addChild(root_0, COLON161_tree);
            }
            pushFollow(FOLLOW_suite_in_try_stmt3888);
            trysuite=suite(!suite_stack.isEmpty() && ((suite_scope)suite_stack.peek()).continueIllegal);

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, trysuite.getTree());
            // /usr/local/lib/jython/grammar/Python.g:1193:7: ( (e+= except_clause )+ ( ORELSE COLON elsesuite= suite[!$suite.isEmpty() && $suite::continueIllegal] )? ( FINALLY COLON finalsuite= suite[true] )? | FINALLY COLON finalsuite= suite[true] )
            int alt73=2;
            int LA73_0 = input.LA(1);

            if ( (LA73_0==EXCEPT) ) {
                alt73=1;
            }
            else if ( (LA73_0==FINALLY) ) {
                alt73=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 73, 0, input);

                throw nvae;
            }
            switch (alt73) {
                case 1 :
                    // /usr/local/lib/jython/grammar/Python.g:1193:9: (e+= except_clause )+ ( ORELSE COLON elsesuite= suite[!$suite.isEmpty() && $suite::continueIllegal] )? ( FINALLY COLON finalsuite= suite[true] )?
                    {
                    // /usr/local/lib/jython/grammar/Python.g:1193:10: (e+= except_clause )+
                    int cnt70=0;
                    loop70:
                    do {
                        int alt70=2;
                        int LA70_0 = input.LA(1);

                        if ( (LA70_0==EXCEPT) ) {
                            alt70=1;
                        }


                        switch (alt70) {
                    	case 1 :
                    	    // /usr/local/lib/jython/grammar/Python.g:1193:10: e+= except_clause
                    	    {
                    	    pushFollow(FOLLOW_except_clause_in_try_stmt3901);
                    	    e=except_clause();

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) adaptor.addChild(root_0, e.getTree());
                    	    if (list_e==null) list_e=new ArrayList();
                    	    list_e.add(e.getTree());


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt70 >= 1 ) break loop70;
                    	    if (state.backtracking>0) {state.failed=true; return retval;}
                                EarlyExitException eee =
                                    new EarlyExitException(70, input);
                                throw eee;
                        }
                        cnt70++;
                    } while (true);

                    // /usr/local/lib/jython/grammar/Python.g:1193:27: ( ORELSE COLON elsesuite= suite[!$suite.isEmpty() && $suite::continueIllegal] )?
                    int alt71=2;
                    int LA71_0 = input.LA(1);

                    if ( (LA71_0==ORELSE) ) {
                        alt71=1;
                    }
                    switch (alt71) {
                        case 1 :
                            // /usr/local/lib/jython/grammar/Python.g:1193:28: ORELSE COLON elsesuite= suite[!$suite.isEmpty() && $suite::continueIllegal]
                            {
                            ORELSE162=(Token)match(input,ORELSE,FOLLOW_ORELSE_in_try_stmt3905); if (state.failed) return retval;
                            if ( state.backtracking==0 ) {
                            ORELSE162_tree = (PythonTree)adaptor.create(ORELSE162);
                            adaptor.addChild(root_0, ORELSE162_tree);
                            }
                            COLON163=(Token)match(input,COLON,FOLLOW_COLON_in_try_stmt3907); if (state.failed) return retval;
                            if ( state.backtracking==0 ) {
                            COLON163_tree = (PythonTree)adaptor.create(COLON163);
                            adaptor.addChild(root_0, COLON163_tree);
                            }
                            pushFollow(FOLLOW_suite_in_try_stmt3911);
                            elsesuite=suite(!suite_stack.isEmpty() && ((suite_scope)suite_stack.peek()).continueIllegal);

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) adaptor.addChild(root_0, elsesuite.getTree());

                            }
                            break;

                    }

                    // /usr/local/lib/jython/grammar/Python.g:1193:105: ( FINALLY COLON finalsuite= suite[true] )?
                    int alt72=2;
                    int LA72_0 = input.LA(1);

                    if ( (LA72_0==FINALLY) ) {
                        alt72=1;
                    }
                    switch (alt72) {
                        case 1 :
                            // /usr/local/lib/jython/grammar/Python.g:1193:106: FINALLY COLON finalsuite= suite[true]
                            {
                            FINALLY164=(Token)match(input,FINALLY,FOLLOW_FINALLY_in_try_stmt3917); if (state.failed) return retval;
                            if ( state.backtracking==0 ) {
                            FINALLY164_tree = (PythonTree)adaptor.create(FINALLY164);
                            adaptor.addChild(root_0, FINALLY164_tree);
                            }
                            COLON165=(Token)match(input,COLON,FOLLOW_COLON_in_try_stmt3919); if (state.failed) return retval;
                            if ( state.backtracking==0 ) {
                            COLON165_tree = (PythonTree)adaptor.create(COLON165);
                            adaptor.addChild(root_0, COLON165_tree);
                            }
                            pushFollow(FOLLOW_suite_in_try_stmt3923);
                            finalsuite=suite(true);

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) adaptor.addChild(root_0, finalsuite.getTree());

                            }
                            break;

                    }

                    if ( state.backtracking==0 ) {

                                  stype = actions.makeTryExcept(TRY160, (trysuite!=null?trysuite.stypes:null), list_e, (elsesuite!=null?elsesuite.stypes:null), (finalsuite!=null?finalsuite.stypes:null));
                              
                    }

                    }
                    break;
                case 2 :
                    // /usr/local/lib/jython/grammar/Python.g:1197:9: FINALLY COLON finalsuite= suite[true]
                    {
                    FINALLY166=(Token)match(input,FINALLY,FOLLOW_FINALLY_in_try_stmt3946); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    FINALLY166_tree = (PythonTree)adaptor.create(FINALLY166);
                    adaptor.addChild(root_0, FINALLY166_tree);
                    }
                    COLON167=(Token)match(input,COLON,FOLLOW_COLON_in_try_stmt3948); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    COLON167_tree = (PythonTree)adaptor.create(COLON167);
                    adaptor.addChild(root_0, COLON167_tree);
                    }
                    pushFollow(FOLLOW_suite_in_try_stmt3952);
                    finalsuite=suite(true);

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, finalsuite.getTree());
                    if ( state.backtracking==0 ) {

                                  stype = actions.makeTryFinally(TRY160, (trysuite!=null?trysuite.stypes:null), (finalsuite!=null?finalsuite.stypes:null));
                              
                    }

                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {

                 retval.tree = stype;

            }
        }

        catch (RecognitionException re) {
            reportError(re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "try_stmt"

    public static class with_stmt_return extends ParserRuleReturnScope {
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "with_stmt"
    // /usr/local/lib/jython/grammar/Python.g:1205:1: with_stmt : WITH w+= with_item ( options {greedy=true; } : COMMA w+= with_item )* COLON suite[false] ;
    public final PythonParser.with_stmt_return with_stmt() throws RecognitionException {
        PythonParser.with_stmt_return retval = new PythonParser.with_stmt_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token WITH168=null;
        Token COMMA169=null;
        Token COLON170=null;
        List list_w=null;
        PythonParser.suite_return suite171 = null;

        PythonParser.with_item_return w = null;
         w = null;
        PythonTree WITH168_tree=null;
        PythonTree COMMA169_tree=null;
        PythonTree COLON170_tree=null;


            stmt stype = null;

        try {
            // /usr/local/lib/jython/grammar/Python.g:1212:5: ( WITH w+= with_item ( options {greedy=true; } : COMMA w+= with_item )* COLON suite[false] )
            // /usr/local/lib/jython/grammar/Python.g:1212:7: WITH w+= with_item ( options {greedy=true; } : COMMA w+= with_item )* COLON suite[false]
            {
            root_0 = (PythonTree)adaptor.nil();

            WITH168=(Token)match(input,WITH,FOLLOW_WITH_in_with_stmt4001); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            WITH168_tree = (PythonTree)adaptor.create(WITH168);
            adaptor.addChild(root_0, WITH168_tree);
            }
            pushFollow(FOLLOW_with_item_in_with_stmt4005);
            w=with_item();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, w.getTree());
            if (list_w==null) list_w=new ArrayList();
            list_w.add(w.getTree());

            // /usr/local/lib/jython/grammar/Python.g:1212:25: ( options {greedy=true; } : COMMA w+= with_item )*
            loop74:
            do {
                int alt74=2;
                int LA74_0 = input.LA(1);

                if ( (LA74_0==COMMA) ) {
                    alt74=1;
                }


                switch (alt74) {
            	case 1 :
            	    // /usr/local/lib/jython/grammar/Python.g:1212:49: COMMA w+= with_item
            	    {
            	    COMMA169=(Token)match(input,COMMA,FOLLOW_COMMA_in_with_stmt4015); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    COMMA169_tree = (PythonTree)adaptor.create(COMMA169);
            	    adaptor.addChild(root_0, COMMA169_tree);
            	    }
            	    pushFollow(FOLLOW_with_item_in_with_stmt4019);
            	    w=with_item();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, w.getTree());
            	    if (list_w==null) list_w=new ArrayList();
            	    list_w.add(w.getTree());


            	    }
            	    break;

            	default :
            	    break loop74;
                }
            } while (true);

            COLON170=(Token)match(input,COLON,FOLLOW_COLON_in_with_stmt4023); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            COLON170_tree = (PythonTree)adaptor.create(COLON170);
            adaptor.addChild(root_0, COLON170_tree);
            }
            pushFollow(FOLLOW_suite_in_with_stmt4025);
            suite171=suite(false);

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, suite171.getTree());
            if ( state.backtracking==0 ) {

                        stype = actions.makeWith(WITH168, list_w, (suite171!=null?suite171.stypes:null));
                    
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {

                 retval.tree = stype;

            }
        }

        catch (RecognitionException re) {
            reportError(re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "with_stmt"

    public static class with_item_return extends ParserRuleReturnScope {
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "with_item"
    // /usr/local/lib/jython/grammar/Python.g:1219:1: with_item : test[expr_contextType.Load] ( AS expr[expr_contextType.Store] )? ;
    public final PythonParser.with_item_return with_item() throws RecognitionException {
        PythonParser.with_item_return retval = new PythonParser.with_item_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token AS173=null;
        PythonParser.test_return test172 = null;

        PythonParser.expr_return expr174 = null;


        PythonTree AS173_tree=null;


            stmt stype = null;

        try {
            // /usr/local/lib/jython/grammar/Python.g:1226:5: ( test[expr_contextType.Load] ( AS expr[expr_contextType.Store] )? )
            // /usr/local/lib/jython/grammar/Python.g:1226:7: test[expr_contextType.Load] ( AS expr[expr_contextType.Store] )?
            {
            root_0 = (PythonTree)adaptor.nil();

            pushFollow(FOLLOW_test_in_with_item4062);
            test172=test(expr_contextType.Load);

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, test172.getTree());
            // /usr/local/lib/jython/grammar/Python.g:1226:35: ( AS expr[expr_contextType.Store] )?
            int alt75=2;
            int LA75_0 = input.LA(1);

            if ( (LA75_0==AS) ) {
                alt75=1;
            }
            switch (alt75) {
                case 1 :
                    // /usr/local/lib/jython/grammar/Python.g:1226:36: AS expr[expr_contextType.Store]
                    {
                    AS173=(Token)match(input,AS,FOLLOW_AS_in_with_item4066); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    AS173_tree = (PythonTree)adaptor.create(AS173);
                    adaptor.addChild(root_0, AS173_tree);
                    }
                    pushFollow(FOLLOW_expr_in_with_item4068);
                    expr174=expr(expr_contextType.Store);

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, expr174.getTree());

                    }
                    break;

            }

            if ( state.backtracking==0 ) {

                        expr item = actions.castExpr((test172!=null?((PythonTree)test172.tree):null));
                        expr var = null;
                        if ((expr174!=null?((Token)expr174.start):null) != null) {
                            var = actions.castExpr((expr174!=null?((PythonTree)expr174.tree):null));
                            actions.checkAssign(var);
                        }
                        stype = new With((test172!=null?((Token)test172.start):null), item, var, null);
                    
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {

                 retval.tree = stype;

            }
        }

        catch (RecognitionException re) {
            reportError(re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "with_item"

    public static class except_clause_return extends ParserRuleReturnScope {
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "except_clause"
    // /usr/local/lib/jython/grammar/Python.g:1239:1: except_clause : EXCEPT (t1= test[expr_contextType.Load] ( ( COMMA | AS ) t2= test[expr_contextType.Store] )? )? COLON suite[!$suite.isEmpty() && $suite::continueIllegal] ;
    public final PythonParser.except_clause_return except_clause() throws RecognitionException {
        PythonParser.except_clause_return retval = new PythonParser.except_clause_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token EXCEPT175=null;
        Token set176=null;
        Token COLON177=null;
        PythonParser.test_return t1 = null;

        PythonParser.test_return t2 = null;

        PythonParser.suite_return suite178 = null;


        PythonTree EXCEPT175_tree=null;
        PythonTree set176_tree=null;
        PythonTree COLON177_tree=null;


            excepthandler extype = null;

        try {
            // /usr/local/lib/jython/grammar/Python.g:1246:5: ( EXCEPT (t1= test[expr_contextType.Load] ( ( COMMA | AS ) t2= test[expr_contextType.Store] )? )? COLON suite[!$suite.isEmpty() && $suite::continueIllegal] )
            // /usr/local/lib/jython/grammar/Python.g:1246:7: EXCEPT (t1= test[expr_contextType.Load] ( ( COMMA | AS ) t2= test[expr_contextType.Store] )? )? COLON suite[!$suite.isEmpty() && $suite::continueIllegal]
            {
            root_0 = (PythonTree)adaptor.nil();

            EXCEPT175=(Token)match(input,EXCEPT,FOLLOW_EXCEPT_in_except_clause4107); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            EXCEPT175_tree = (PythonTree)adaptor.create(EXCEPT175);
            adaptor.addChild(root_0, EXCEPT175_tree);
            }
            // /usr/local/lib/jython/grammar/Python.g:1246:14: (t1= test[expr_contextType.Load] ( ( COMMA | AS ) t2= test[expr_contextType.Store] )? )?
            int alt77=2;
            int LA77_0 = input.LA(1);

            if ( (LA77_0==NAME||LA77_0==NOT||LA77_0==LPAREN||(LA77_0>=PLUS && LA77_0<=MINUS)||(LA77_0>=TILDE && LA77_0<=LBRACK)||LA77_0==LCURLY||LA77_0==BACKQUOTE) ) {
                alt77=1;
            }
            else if ( (LA77_0==PRINT) && ((printFunction))) {
                alt77=1;
            }
            else if ( (LA77_0==LAMBDA||(LA77_0>=INT && LA77_0<=STRING)) ) {
                alt77=1;
            }
            switch (alt77) {
                case 1 :
                    // /usr/local/lib/jython/grammar/Python.g:1246:15: t1= test[expr_contextType.Load] ( ( COMMA | AS ) t2= test[expr_contextType.Store] )?
                    {
                    pushFollow(FOLLOW_test_in_except_clause4112);
                    t1=test(expr_contextType.Load);

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, t1.getTree());
                    // /usr/local/lib/jython/grammar/Python.g:1246:46: ( ( COMMA | AS ) t2= test[expr_contextType.Store] )?
                    int alt76=2;
                    int LA76_0 = input.LA(1);

                    if ( (LA76_0==AS||LA76_0==COMMA) ) {
                        alt76=1;
                    }
                    switch (alt76) {
                        case 1 :
                            // /usr/local/lib/jython/grammar/Python.g:1246:47: ( COMMA | AS ) t2= test[expr_contextType.Store]
                            {
                            set176=(Token)input.LT(1);
                            if ( input.LA(1)==AS||input.LA(1)==COMMA ) {
                                input.consume();
                                if ( state.backtracking==0 ) adaptor.addChild(root_0, (PythonTree)adaptor.create(set176));
                                state.errorRecovery=false;state.failed=false;
                            }
                            else {
                                if (state.backtracking>0) {state.failed=true; return retval;}
                                MismatchedSetException mse = new MismatchedSetException(null,input);
                                throw mse;
                            }

                            pushFollow(FOLLOW_test_in_except_clause4126);
                            t2=test(expr_contextType.Store);

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) adaptor.addChild(root_0, t2.getTree());

                            }
                            break;

                    }


                    }
                    break;

            }

            COLON177=(Token)match(input,COLON,FOLLOW_COLON_in_except_clause4133); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            COLON177_tree = (PythonTree)adaptor.create(COLON177);
            adaptor.addChild(root_0, COLON177_tree);
            }
            pushFollow(FOLLOW_suite_in_except_clause4135);
            suite178=suite(!suite_stack.isEmpty() && ((suite_scope)suite_stack.peek()).continueIllegal);

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, suite178.getTree());
            if ( state.backtracking==0 ) {

                        extype = new ExceptHandler(EXCEPT175, actions.castExpr((t1!=null?((PythonTree)t1.tree):null)), actions.castExpr((t2!=null?((PythonTree)t2.tree):null)),
                            actions.castStmts((suite178!=null?suite178.stypes:null)));
                    
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {

                 retval.tree = extype;

            }
        }

        catch (RecognitionException re) {
            reportError(re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "except_clause"

    protected static class suite_scope {
        boolean continueIllegal;
    }
    protected Stack suite_stack = new Stack();

    public static class suite_return extends ParserRuleReturnScope {
        public List stypes;
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "suite"
    // /usr/local/lib/jython/grammar/Python.g:1254:1: suite[boolean fromFinally] returns [List stypes] : ( simple_stmt | NEWLINE INDENT ( stmt )+ DEDENT );
    public final PythonParser.suite_return suite(boolean fromFinally) throws RecognitionException {
        suite_stack.push(new suite_scope());
        PythonParser.suite_return retval = new PythonParser.suite_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token NEWLINE180=null;
        Token INDENT181=null;
        Token DEDENT183=null;
        PythonParser.simple_stmt_return simple_stmt179 = null;

        PythonParser.stmt_return stmt182 = null;


        PythonTree NEWLINE180_tree=null;
        PythonTree INDENT181_tree=null;
        PythonTree DEDENT183_tree=null;


            if (((suite_scope)suite_stack.peek()).continueIllegal || fromFinally) {
                ((suite_scope)suite_stack.peek()).continueIllegal = true;
            } else {
                ((suite_scope)suite_stack.peek()).continueIllegal = false;
            }
            retval.stypes = new ArrayList();

        try {
            // /usr/local/lib/jython/grammar/Python.g:1267:5: ( simple_stmt | NEWLINE INDENT ( stmt )+ DEDENT )
            int alt79=2;
            int LA79_0 = input.LA(1);

            if ( (LA79_0==NAME||LA79_0==NOT||LA79_0==LPAREN||(LA79_0>=PLUS && LA79_0<=MINUS)||(LA79_0>=TILDE && LA79_0<=LBRACK)||LA79_0==LCURLY||LA79_0==BACKQUOTE) ) {
                alt79=1;
            }
            else if ( (LA79_0==PRINT) && (((!printFunction)||(printFunction)))) {
                alt79=1;
            }
            else if ( ((LA79_0>=ASSERT && LA79_0<=BREAK)||LA79_0==CONTINUE||LA79_0==DELETE||LA79_0==EXEC||LA79_0==FROM||LA79_0==GLOBAL||LA79_0==IMPORT||LA79_0==LAMBDA||(LA79_0>=PASS && LA79_0<=RETURN)||LA79_0==YIELD||(LA79_0>=INT && LA79_0<=STRING)) ) {
                alt79=1;
            }
            else if ( (LA79_0==NEWLINE) ) {
                alt79=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 79, 0, input);

                throw nvae;
            }
            switch (alt79) {
                case 1 :
                    // /usr/local/lib/jython/grammar/Python.g:1267:7: simple_stmt
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    pushFollow(FOLLOW_simple_stmt_in_suite4181);
                    simple_stmt179=simple_stmt();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, simple_stmt179.getTree());
                    if ( state.backtracking==0 ) {

                                retval.stypes = (simple_stmt179!=null?simple_stmt179.stypes:null);
                            
                    }

                    }
                    break;
                case 2 :
                    // /usr/local/lib/jython/grammar/Python.g:1271:7: NEWLINE INDENT ( stmt )+ DEDENT
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    NEWLINE180=(Token)match(input,NEWLINE,FOLLOW_NEWLINE_in_suite4197); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    NEWLINE180_tree = (PythonTree)adaptor.create(NEWLINE180);
                    adaptor.addChild(root_0, NEWLINE180_tree);
                    }
                    INDENT181=(Token)match(input,INDENT,FOLLOW_INDENT_in_suite4199); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    INDENT181_tree = (PythonTree)adaptor.create(INDENT181);
                    adaptor.addChild(root_0, INDENT181_tree);
                    }
                    // /usr/local/lib/jython/grammar/Python.g:1272:7: ( stmt )+
                    int cnt78=0;
                    loop78:
                    do {
                        int alt78=2;
                        int LA78_0 = input.LA(1);

                        if ( (LA78_0==NAME||LA78_0==NOT||LA78_0==LPAREN||(LA78_0>=PLUS && LA78_0<=MINUS)||(LA78_0>=TILDE && LA78_0<=LBRACK)||LA78_0==LCURLY||LA78_0==BACKQUOTE) ) {
                            alt78=1;
                        }
                        else if ( (LA78_0==PRINT) && (((!printFunction)||(printFunction)))) {
                            alt78=1;
                        }
                        else if ( ((LA78_0>=ASSERT && LA78_0<=DELETE)||LA78_0==EXEC||(LA78_0>=FROM && LA78_0<=IMPORT)||LA78_0==LAMBDA||(LA78_0>=PASS && LA78_0<=AT)||(LA78_0>=INT && LA78_0<=STRING)) ) {
                            alt78=1;
                        }


                        switch (alt78) {
                    	case 1 :
                    	    // /usr/local/lib/jython/grammar/Python.g:1272:8: stmt
                    	    {
                    	    pushFollow(FOLLOW_stmt_in_suite4208);
                    	    stmt182=stmt();

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) adaptor.addChild(root_0, stmt182.getTree());
                    	    if ( state.backtracking==0 ) {

                    	                 if ((stmt182!=null?stmt182.stypes:null) != null) {
                    	                     retval.stypes.addAll((stmt182!=null?stmt182.stypes:null));
                    	                 }
                    	             
                    	    }

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt78 >= 1 ) break loop78;
                    	    if (state.backtracking>0) {state.failed=true; return retval;}
                                EarlyExitException eee =
                                    new EarlyExitException(78, input);
                                throw eee;
                        }
                        cnt78++;
                    } while (true);

                    DEDENT183=(Token)match(input,DEDENT,FOLLOW_DEDENT_in_suite4228); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    DEDENT183_tree = (PythonTree)adaptor.create(DEDENT183);
                    adaptor.addChild(root_0, DEDENT183_tree);
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        catch (RecognitionException re) {
            reportError(re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
            suite_stack.pop();
        }
        return retval;
    }
    // $ANTLR end "suite"

    public static class test_return extends ParserRuleReturnScope {
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "test"
    // /usr/local/lib/jython/grammar/Python.g:1282:1: test[expr_contextType ctype] : (o1= or_test[ctype] ( ( IF or_test[null] ORELSE )=> IF o2= or_test[ctype] ORELSE e= test[expr_contextType.Load] | -> or_test ) | lambdef );
    public final PythonParser.test_return test(expr_contextType ctype) throws RecognitionException {
        PythonParser.test_return retval = new PythonParser.test_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token IF184=null;
        Token ORELSE185=null;
        PythonParser.or_test_return o1 = null;

        PythonParser.or_test_return o2 = null;

        PythonParser.test_return e = null;

        PythonParser.lambdef_return lambdef186 = null;


        PythonTree IF184_tree=null;
        PythonTree ORELSE185_tree=null;
        RewriteRuleTokenStream stream_ORELSE=new RewriteRuleTokenStream(adaptor,"token ORELSE");
        RewriteRuleTokenStream stream_IF=new RewriteRuleTokenStream(adaptor,"token IF");
        RewriteRuleSubtreeStream stream_test=new RewriteRuleSubtreeStream(adaptor,"rule test");
        RewriteRuleSubtreeStream stream_or_test=new RewriteRuleSubtreeStream(adaptor,"rule or_test");

            expr etype = null;

        try {
            // /usr/local/lib/jython/grammar/Python.g:1291:5: (o1= or_test[ctype] ( ( IF or_test[null] ORELSE )=> IF o2= or_test[ctype] ORELSE e= test[expr_contextType.Load] | -> or_test ) | lambdef )
            int alt81=2;
            int LA81_0 = input.LA(1);

            if ( (LA81_0==NAME||LA81_0==NOT||LA81_0==LPAREN||(LA81_0>=PLUS && LA81_0<=MINUS)||(LA81_0>=TILDE && LA81_0<=LBRACK)||LA81_0==LCURLY||LA81_0==BACKQUOTE) ) {
                alt81=1;
            }
            else if ( (LA81_0==PRINT) && ((printFunction))) {
                alt81=1;
            }
            else if ( ((LA81_0>=INT && LA81_0<=STRING)) ) {
                alt81=1;
            }
            else if ( (LA81_0==LAMBDA) ) {
                alt81=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 81, 0, input);

                throw nvae;
            }
            switch (alt81) {
                case 1 :
                    // /usr/local/lib/jython/grammar/Python.g:1291:6: o1= or_test[ctype] ( ( IF or_test[null] ORELSE )=> IF o2= or_test[ctype] ORELSE e= test[expr_contextType.Load] | -> or_test )
                    {
                    pushFollow(FOLLOW_or_test_in_test4258);
                    o1=or_test(ctype);

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_or_test.add(o1.getTree());
                    // /usr/local/lib/jython/grammar/Python.g:1292:7: ( ( IF or_test[null] ORELSE )=> IF o2= or_test[ctype] ORELSE e= test[expr_contextType.Load] | -> or_test )
                    int alt80=2;
                    alt80 = dfa80.predict(input);
                    switch (alt80) {
                        case 1 :
                            // /usr/local/lib/jython/grammar/Python.g:1292:9: ( IF or_test[null] ORELSE )=> IF o2= or_test[ctype] ORELSE e= test[expr_contextType.Load]
                            {
                            IF184=(Token)match(input,IF,FOLLOW_IF_in_test4280); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_IF.add(IF184);

                            pushFollow(FOLLOW_or_test_in_test4284);
                            o2=or_test(ctype);

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_or_test.add(o2.getTree());
                            ORELSE185=(Token)match(input,ORELSE,FOLLOW_ORELSE_in_test4287); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_ORELSE.add(ORELSE185);

                            pushFollow(FOLLOW_test_in_test4291);
                            e=test(expr_contextType.Load);

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_test.add(e.getTree());
                            if ( state.backtracking==0 ) {

                                           etype = new IfExp((o1!=null?((Token)o1.start):null), actions.castExpr((o2!=null?((PythonTree)o2.tree):null)), actions.castExpr((o1!=null?((PythonTree)o1.tree):null)), actions.castExpr((e!=null?((PythonTree)e.tree):null)));
                                       
                            }

                            }
                            break;
                        case 2 :
                            // /usr/local/lib/jython/grammar/Python.g:1297:6: 
                            {

                            // AST REWRITE
                            // elements: or_test
                            // token labels: 
                            // rule labels: retval
                            // token list labels: 
                            // rule list labels: 
                            // wildcard labels: 
                            if ( state.backtracking==0 ) {
                            retval.tree = root_0;
                            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                            root_0 = (PythonTree)adaptor.nil();
                            // 1297:6: -> or_test
                            {
                                adaptor.addChild(root_0, stream_or_test.nextTree());

                            }

                            retval.tree = root_0;}
                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // /usr/local/lib/jython/grammar/Python.g:1299:7: lambdef
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    pushFollow(FOLLOW_lambdef_in_test4336);
                    lambdef186=lambdef();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, lambdef186.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {

                 if (etype != null) {
                     retval.tree = etype;
                 }

            }
        }

        catch (RecognitionException re) {
            reportError(re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "test"

    public static class or_test_return extends ParserRuleReturnScope {
        public Token leftTok;
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "or_test"
    // /usr/local/lib/jython/grammar/Python.g:1303:1: or_test[expr_contextType ctype] returns [Token leftTok] : left= and_test[ctype] ( (or= OR right+= and_test[ctype] )+ | -> $left) ;
    public final PythonParser.or_test_return or_test(expr_contextType ctype) throws RecognitionException {
        PythonParser.or_test_return retval = new PythonParser.or_test_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token or=null;
        List list_right=null;
        PythonParser.and_test_return left = null;

        PythonParser.and_test_return right = null;
         right = null;
        PythonTree or_tree=null;
        RewriteRuleTokenStream stream_OR=new RewriteRuleTokenStream(adaptor,"token OR");
        RewriteRuleSubtreeStream stream_and_test=new RewriteRuleSubtreeStream(adaptor,"rule and_test");
        try {
            // /usr/local/lib/jython/grammar/Python.g:1314:5: (left= and_test[ctype] ( (or= OR right+= and_test[ctype] )+ | -> $left) )
            // /usr/local/lib/jython/grammar/Python.g:1314:7: left= and_test[ctype] ( (or= OR right+= and_test[ctype] )+ | -> $left)
            {
            pushFollow(FOLLOW_and_test_in_or_test4371);
            left=and_test(ctype);

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_and_test.add(left.getTree());
            // /usr/local/lib/jython/grammar/Python.g:1315:9: ( (or= OR right+= and_test[ctype] )+ | -> $left)
            int alt83=2;
            int LA83_0 = input.LA(1);

            if ( (LA83_0==OR) ) {
                alt83=1;
            }
            else if ( (LA83_0==EOF||LA83_0==NEWLINE||LA83_0==AS||LA83_0==FOR||LA83_0==IF||LA83_0==ORELSE||(LA83_0>=RPAREN && LA83_0<=COMMA)||(LA83_0>=SEMI && LA83_0<=DOUBLESLASHEQUAL)||LA83_0==RBRACK||(LA83_0>=RCURLY && LA83_0<=BACKQUOTE)) ) {
                alt83=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 83, 0, input);

                throw nvae;
            }
            switch (alt83) {
                case 1 :
                    // /usr/local/lib/jython/grammar/Python.g:1315:11: (or= OR right+= and_test[ctype] )+
                    {
                    // /usr/local/lib/jython/grammar/Python.g:1315:11: (or= OR right+= and_test[ctype] )+
                    int cnt82=0;
                    loop82:
                    do {
                        int alt82=2;
                        int LA82_0 = input.LA(1);

                        if ( (LA82_0==OR) ) {
                            alt82=1;
                        }


                        switch (alt82) {
                    	case 1 :
                    	    // /usr/local/lib/jython/grammar/Python.g:1315:12: or= OR right+= and_test[ctype]
                    	    {
                    	    or=(Token)match(input,OR,FOLLOW_OR_in_or_test4387); if (state.failed) return retval; 
                    	    if ( state.backtracking==0 ) stream_OR.add(or);

                    	    pushFollow(FOLLOW_and_test_in_or_test4391);
                    	    right=and_test(ctype);

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) stream_and_test.add(right.getTree());
                    	    if (list_right==null) list_right=new ArrayList();
                    	    list_right.add(right.getTree());


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt82 >= 1 ) break loop82;
                    	    if (state.backtracking>0) {state.failed=true; return retval;}
                                EarlyExitException eee =
                                    new EarlyExitException(82, input);
                                throw eee;
                        }
                        cnt82++;
                    } while (true);


                    }
                    break;
                case 2 :
                    // /usr/local/lib/jython/grammar/Python.g:1318:8: 
                    {

                    // AST REWRITE
                    // elements: left
                    // token labels: 
                    // rule labels: retval, left
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
                    RewriteRuleSubtreeStream stream_left=new RewriteRuleSubtreeStream(adaptor,"rule left",left!=null?left.tree:null);

                    root_0 = (PythonTree)adaptor.nil();
                    // 1318:8: -> $left
                    {
                        adaptor.addChild(root_0, stream_left.nextTree());

                    }

                    retval.tree = root_0;}
                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {

                  if (or != null) {
                      Token tok = (left!=null?((Token)left.start):null);
                      if ((left!=null?left.leftTok:null) != null) {
                          tok = (left!=null?left.leftTok:null);
                      }
                      retval.tree = actions.makeBoolOp(tok, (left!=null?((PythonTree)left.tree):null), boolopType.Or, list_right);
                  }

            }
        }

        catch (RecognitionException re) {
            reportError(re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "or_test"

    public static class and_test_return extends ParserRuleReturnScope {
        public Token leftTok;
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "and_test"
    // /usr/local/lib/jython/grammar/Python.g:1323:1: and_test[expr_contextType ctype] returns [Token leftTok] : left= not_test[ctype] ( (and= AND right+= not_test[ctype] )+ | -> $left) ;
    public final PythonParser.and_test_return and_test(expr_contextType ctype) throws RecognitionException {
        PythonParser.and_test_return retval = new PythonParser.and_test_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token and=null;
        List list_right=null;
        PythonParser.not_test_return left = null;

        PythonParser.not_test_return right = null;
         right = null;
        PythonTree and_tree=null;
        RewriteRuleTokenStream stream_AND=new RewriteRuleTokenStream(adaptor,"token AND");
        RewriteRuleSubtreeStream stream_not_test=new RewriteRuleSubtreeStream(adaptor,"rule not_test");
        try {
            // /usr/local/lib/jython/grammar/Python.g:1334:5: (left= not_test[ctype] ( (and= AND right+= not_test[ctype] )+ | -> $left) )
            // /usr/local/lib/jython/grammar/Python.g:1334:7: left= not_test[ctype] ( (and= AND right+= not_test[ctype] )+ | -> $left)
            {
            pushFollow(FOLLOW_not_test_in_and_test4472);
            left=not_test(ctype);

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_not_test.add(left.getTree());
            // /usr/local/lib/jython/grammar/Python.g:1335:9: ( (and= AND right+= not_test[ctype] )+ | -> $left)
            int alt85=2;
            int LA85_0 = input.LA(1);

            if ( (LA85_0==AND) ) {
                alt85=1;
            }
            else if ( (LA85_0==EOF||LA85_0==NEWLINE||LA85_0==AS||LA85_0==FOR||LA85_0==IF||(LA85_0>=OR && LA85_0<=ORELSE)||(LA85_0>=RPAREN && LA85_0<=COMMA)||(LA85_0>=SEMI && LA85_0<=DOUBLESLASHEQUAL)||LA85_0==RBRACK||(LA85_0>=RCURLY && LA85_0<=BACKQUOTE)) ) {
                alt85=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 85, 0, input);

                throw nvae;
            }
            switch (alt85) {
                case 1 :
                    // /usr/local/lib/jython/grammar/Python.g:1335:11: (and= AND right+= not_test[ctype] )+
                    {
                    // /usr/local/lib/jython/grammar/Python.g:1335:11: (and= AND right+= not_test[ctype] )+
                    int cnt84=0;
                    loop84:
                    do {
                        int alt84=2;
                        int LA84_0 = input.LA(1);

                        if ( (LA84_0==AND) ) {
                            alt84=1;
                        }


                        switch (alt84) {
                    	case 1 :
                    	    // /usr/local/lib/jython/grammar/Python.g:1335:12: and= AND right+= not_test[ctype]
                    	    {
                    	    and=(Token)match(input,AND,FOLLOW_AND_in_and_test4488); if (state.failed) return retval; 
                    	    if ( state.backtracking==0 ) stream_AND.add(and);

                    	    pushFollow(FOLLOW_not_test_in_and_test4492);
                    	    right=not_test(ctype);

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) stream_not_test.add(right.getTree());
                    	    if (list_right==null) list_right=new ArrayList();
                    	    list_right.add(right.getTree());


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt84 >= 1 ) break loop84;
                    	    if (state.backtracking>0) {state.failed=true; return retval;}
                                EarlyExitException eee =
                                    new EarlyExitException(84, input);
                                throw eee;
                        }
                        cnt84++;
                    } while (true);


                    }
                    break;
                case 2 :
                    // /usr/local/lib/jython/grammar/Python.g:1338:8: 
                    {

                    // AST REWRITE
                    // elements: left
                    // token labels: 
                    // rule labels: retval, left
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
                    RewriteRuleSubtreeStream stream_left=new RewriteRuleSubtreeStream(adaptor,"rule left",left!=null?left.tree:null);

                    root_0 = (PythonTree)adaptor.nil();
                    // 1338:8: -> $left
                    {
                        adaptor.addChild(root_0, stream_left.nextTree());

                    }

                    retval.tree = root_0;}
                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {

                  if (and != null) {
                      Token tok = (left!=null?((Token)left.start):null);
                      if ((left!=null?left.leftTok:null) != null) {
                          tok = (left!=null?left.leftTok:null);
                      }
                      retval.tree = actions.makeBoolOp(tok, (left!=null?((PythonTree)left.tree):null), boolopType.And, list_right);
                  }

            }
        }

        catch (RecognitionException re) {
            reportError(re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "and_test"

    public static class not_test_return extends ParserRuleReturnScope {
        public Token leftTok;
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "not_test"
    // /usr/local/lib/jython/grammar/Python.g:1343:1: not_test[expr_contextType ctype] returns [Token leftTok] : ( NOT nt= not_test[ctype] | comparison[ctype] );
    public final PythonParser.not_test_return not_test(expr_contextType ctype) throws RecognitionException {
        PythonParser.not_test_return retval = new PythonParser.not_test_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token NOT187=null;
        PythonParser.not_test_return nt = null;

        PythonParser.comparison_return comparison188 = null;


        PythonTree NOT187_tree=null;


            expr etype = null;

        try {
            // /usr/local/lib/jython/grammar/Python.g:1353:5: ( NOT nt= not_test[ctype] | comparison[ctype] )
            int alt86=2;
            int LA86_0 = input.LA(1);

            if ( (LA86_0==NOT) ) {
                alt86=1;
            }
            else if ( (LA86_0==NAME||LA86_0==LPAREN||(LA86_0>=PLUS && LA86_0<=MINUS)||(LA86_0>=TILDE && LA86_0<=LBRACK)||LA86_0==LCURLY||LA86_0==BACKQUOTE) ) {
                alt86=2;
            }
            else if ( (LA86_0==PRINT) && ((printFunction))) {
                alt86=2;
            }
            else if ( ((LA86_0>=INT && LA86_0<=STRING)) ) {
                alt86=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 86, 0, input);

                throw nvae;
            }
            switch (alt86) {
                case 1 :
                    // /usr/local/lib/jython/grammar/Python.g:1353:7: NOT nt= not_test[ctype]
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    NOT187=(Token)match(input,NOT,FOLLOW_NOT_in_not_test4576); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    NOT187_tree = (PythonTree)adaptor.create(NOT187);
                    adaptor.addChild(root_0, NOT187_tree);
                    }
                    pushFollow(FOLLOW_not_test_in_not_test4580);
                    nt=not_test(ctype);

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, nt.getTree());
                    if ( state.backtracking==0 ) {

                                etype = new UnaryOp(NOT187, unaryopType.Not, actions.castExpr((nt!=null?((PythonTree)nt.tree):null)));
                            
                    }

                    }
                    break;
                case 2 :
                    // /usr/local/lib/jython/grammar/Python.g:1357:7: comparison[ctype]
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    pushFollow(FOLLOW_comparison_in_not_test4597);
                    comparison188=comparison(ctype);

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, comparison188.getTree());
                    if ( state.backtracking==0 ) {

                                retval.leftTok = (comparison188!=null?comparison188.leftTok:null);
                            
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {

                 if (etype != null) {
                     retval.tree = etype;
                 }

            }
        }

        catch (RecognitionException re) {
            reportError(re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "not_test"

    public static class comparison_return extends ParserRuleReturnScope {
        public Token leftTok;
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "comparison"
    // /usr/local/lib/jython/grammar/Python.g:1364:1: comparison[expr_contextType ctype] returns [Token leftTok] : left= expr[ctype] ( ( comp_op right+= expr[ctype] )+ | -> $left) ;
    public final PythonParser.comparison_return comparison(expr_contextType ctype) throws RecognitionException {
        PythonParser.comparison_return retval = new PythonParser.comparison_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        List list_right=null;
        PythonParser.expr_return left = null;

        PythonParser.comp_op_return comp_op189 = null;

        PythonParser.expr_return right = null;
         right = null;
        RewriteRuleSubtreeStream stream_expr=new RewriteRuleSubtreeStream(adaptor,"rule expr");
        RewriteRuleSubtreeStream stream_comp_op=new RewriteRuleSubtreeStream(adaptor,"rule comp_op");

            List cmps = new ArrayList();

        try {
            // /usr/local/lib/jython/grammar/Python.g:1376:5: (left= expr[ctype] ( ( comp_op right+= expr[ctype] )+ | -> $left) )
            // /usr/local/lib/jython/grammar/Python.g:1376:7: left= expr[ctype] ( ( comp_op right+= expr[ctype] )+ | -> $left)
            {
            pushFollow(FOLLOW_expr_in_comparison4646);
            left=expr(ctype);

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_expr.add(left.getTree());
            // /usr/local/lib/jython/grammar/Python.g:1377:8: ( ( comp_op right+= expr[ctype] )+ | -> $left)
            int alt88=2;
            int LA88_0 = input.LA(1);

            if ( ((LA88_0>=IN && LA88_0<=IS)||LA88_0==NOT||(LA88_0>=LESS && LA88_0<=NOTEQUAL)) ) {
                alt88=1;
            }
            else if ( (LA88_0==EOF||LA88_0==NEWLINE||(LA88_0>=AND && LA88_0<=AS)||LA88_0==FOR||LA88_0==IF||(LA88_0>=OR && LA88_0<=ORELSE)||(LA88_0>=RPAREN && LA88_0<=COMMA)||(LA88_0>=SEMI && LA88_0<=DOUBLESLASHEQUAL)||LA88_0==RBRACK||(LA88_0>=RCURLY && LA88_0<=BACKQUOTE)) ) {
                alt88=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 88, 0, input);

                throw nvae;
            }
            switch (alt88) {
                case 1 :
                    // /usr/local/lib/jython/grammar/Python.g:1377:10: ( comp_op right+= expr[ctype] )+
                    {
                    // /usr/local/lib/jython/grammar/Python.g:1377:10: ( comp_op right+= expr[ctype] )+
                    int cnt87=0;
                    loop87:
                    do {
                        int alt87=2;
                        int LA87_0 = input.LA(1);

                        if ( ((LA87_0>=IN && LA87_0<=IS)||LA87_0==NOT||(LA87_0>=LESS && LA87_0<=NOTEQUAL)) ) {
                            alt87=1;
                        }


                        switch (alt87) {
                    	case 1 :
                    	    // /usr/local/lib/jython/grammar/Python.g:1377:12: comp_op right+= expr[ctype]
                    	    {
                    	    pushFollow(FOLLOW_comp_op_in_comparison4660);
                    	    comp_op189=comp_op();

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) stream_comp_op.add(comp_op189.getTree());
                    	    pushFollow(FOLLOW_expr_in_comparison4664);
                    	    right=expr(ctype);

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) stream_expr.add(right.getTree());
                    	    if (list_right==null) list_right=new ArrayList();
                    	    list_right.add(right.getTree());

                    	    if ( state.backtracking==0 ) {

                    	                     cmps.add((comp_op189!=null?comp_op189.op:null));
                    	                 
                    	    }

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt87 >= 1 ) break loop87;
                    	    if (state.backtracking>0) {state.failed=true; return retval;}
                                EarlyExitException eee =
                                    new EarlyExitException(87, input);
                                throw eee;
                        }
                        cnt87++;
                    } while (true);


                    }
                    break;
                case 2 :
                    // /usr/local/lib/jython/grammar/Python.g:1383:7: 
                    {

                    // AST REWRITE
                    // elements: left
                    // token labels: 
                    // rule labels: retval, left
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
                    RewriteRuleSubtreeStream stream_left=new RewriteRuleSubtreeStream(adaptor,"rule left",left!=null?left.tree:null);

                    root_0 = (PythonTree)adaptor.nil();
                    // 1383:7: -> $left
                    {
                        adaptor.addChild(root_0, stream_left.nextTree());

                    }

                    retval.tree = root_0;}
                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {

                  retval.leftTok = (left!=null?left.leftTok:null);
                  if (!cmps.isEmpty()) {
                      retval.tree = new Compare((left!=null?((Token)left.start):null), actions.castExpr((left!=null?((PythonTree)left.tree):null)), actions.makeCmpOps(cmps),
                          actions.castExprs(list_right));
                  }

            }
        }

        catch (RecognitionException re) {
            reportError(re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "comparison"

    public static class comp_op_return extends ParserRuleReturnScope {
        public cmpopType op;
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "comp_op"
    // /usr/local/lib/jython/grammar/Python.g:1388:1: comp_op returns [cmpopType op] : ( LESS | GREATER | EQUAL | GREATEREQUAL | LESSEQUAL | ALT_NOTEQUAL | NOTEQUAL | IN | NOT IN | IS | IS NOT );
    public final PythonParser.comp_op_return comp_op() throws RecognitionException {
        PythonParser.comp_op_return retval = new PythonParser.comp_op_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token LESS190=null;
        Token GREATER191=null;
        Token EQUAL192=null;
        Token GREATEREQUAL193=null;
        Token LESSEQUAL194=null;
        Token ALT_NOTEQUAL195=null;
        Token NOTEQUAL196=null;
        Token IN197=null;
        Token NOT198=null;
        Token IN199=null;
        Token IS200=null;
        Token IS201=null;
        Token NOT202=null;

        PythonTree LESS190_tree=null;
        PythonTree GREATER191_tree=null;
        PythonTree EQUAL192_tree=null;
        PythonTree GREATEREQUAL193_tree=null;
        PythonTree LESSEQUAL194_tree=null;
        PythonTree ALT_NOTEQUAL195_tree=null;
        PythonTree NOTEQUAL196_tree=null;
        PythonTree IN197_tree=null;
        PythonTree NOT198_tree=null;
        PythonTree IN199_tree=null;
        PythonTree IS200_tree=null;
        PythonTree IS201_tree=null;
        PythonTree NOT202_tree=null;

        try {
            // /usr/local/lib/jython/grammar/Python.g:1390:5: ( LESS | GREATER | EQUAL | GREATEREQUAL | LESSEQUAL | ALT_NOTEQUAL | NOTEQUAL | IN | NOT IN | IS | IS NOT )
            int alt89=11;
            alt89 = dfa89.predict(input);
            switch (alt89) {
                case 1 :
                    // /usr/local/lib/jython/grammar/Python.g:1390:7: LESS
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    LESS190=(Token)match(input,LESS,FOLLOW_LESS_in_comp_op4745); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    LESS190_tree = (PythonTree)adaptor.create(LESS190);
                    adaptor.addChild(root_0, LESS190_tree);
                    }
                    if ( state.backtracking==0 ) {

                                retval.op = cmpopType.Lt;
                            
                    }

                    }
                    break;
                case 2 :
                    // /usr/local/lib/jython/grammar/Python.g:1394:7: GREATER
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    GREATER191=(Token)match(input,GREATER,FOLLOW_GREATER_in_comp_op4761); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    GREATER191_tree = (PythonTree)adaptor.create(GREATER191);
                    adaptor.addChild(root_0, GREATER191_tree);
                    }
                    if ( state.backtracking==0 ) {

                                retval.op = cmpopType.Gt;
                            
                    }

                    }
                    break;
                case 3 :
                    // /usr/local/lib/jython/grammar/Python.g:1398:7: EQUAL
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    EQUAL192=(Token)match(input,EQUAL,FOLLOW_EQUAL_in_comp_op4777); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    EQUAL192_tree = (PythonTree)adaptor.create(EQUAL192);
                    adaptor.addChild(root_0, EQUAL192_tree);
                    }
                    if ( state.backtracking==0 ) {

                                retval.op = cmpopType.Eq;
                            
                    }

                    }
                    break;
                case 4 :
                    // /usr/local/lib/jython/grammar/Python.g:1402:7: GREATEREQUAL
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    GREATEREQUAL193=(Token)match(input,GREATEREQUAL,FOLLOW_GREATEREQUAL_in_comp_op4793); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    GREATEREQUAL193_tree = (PythonTree)adaptor.create(GREATEREQUAL193);
                    adaptor.addChild(root_0, GREATEREQUAL193_tree);
                    }
                    if ( state.backtracking==0 ) {

                                retval.op = cmpopType.GtE;
                            
                    }

                    }
                    break;
                case 5 :
                    // /usr/local/lib/jython/grammar/Python.g:1406:7: LESSEQUAL
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    LESSEQUAL194=(Token)match(input,LESSEQUAL,FOLLOW_LESSEQUAL_in_comp_op4809); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    LESSEQUAL194_tree = (PythonTree)adaptor.create(LESSEQUAL194);
                    adaptor.addChild(root_0, LESSEQUAL194_tree);
                    }
                    if ( state.backtracking==0 ) {

                                retval.op = cmpopType.LtE;
                            
                    }

                    }
                    break;
                case 6 :
                    // /usr/local/lib/jython/grammar/Python.g:1410:7: ALT_NOTEQUAL
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    ALT_NOTEQUAL195=(Token)match(input,ALT_NOTEQUAL,FOLLOW_ALT_NOTEQUAL_in_comp_op4825); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    ALT_NOTEQUAL195_tree = (PythonTree)adaptor.create(ALT_NOTEQUAL195);
                    adaptor.addChild(root_0, ALT_NOTEQUAL195_tree);
                    }
                    if ( state.backtracking==0 ) {

                                retval.op = cmpopType.NotEq;
                            
                    }

                    }
                    break;
                case 7 :
                    // /usr/local/lib/jython/grammar/Python.g:1414:7: NOTEQUAL
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    NOTEQUAL196=(Token)match(input,NOTEQUAL,FOLLOW_NOTEQUAL_in_comp_op4841); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    NOTEQUAL196_tree = (PythonTree)adaptor.create(NOTEQUAL196);
                    adaptor.addChild(root_0, NOTEQUAL196_tree);
                    }
                    if ( state.backtracking==0 ) {

                                retval.op = cmpopType.NotEq;
                            
                    }

                    }
                    break;
                case 8 :
                    // /usr/local/lib/jython/grammar/Python.g:1418:7: IN
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    IN197=(Token)match(input,IN,FOLLOW_IN_in_comp_op4857); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    IN197_tree = (PythonTree)adaptor.create(IN197);
                    adaptor.addChild(root_0, IN197_tree);
                    }
                    if ( state.backtracking==0 ) {

                                retval.op = cmpopType.In;
                            
                    }

                    }
                    break;
                case 9 :
                    // /usr/local/lib/jython/grammar/Python.g:1422:7: NOT IN
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    NOT198=(Token)match(input,NOT,FOLLOW_NOT_in_comp_op4873); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    NOT198_tree = (PythonTree)adaptor.create(NOT198);
                    adaptor.addChild(root_0, NOT198_tree);
                    }
                    IN199=(Token)match(input,IN,FOLLOW_IN_in_comp_op4875); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    IN199_tree = (PythonTree)adaptor.create(IN199);
                    adaptor.addChild(root_0, IN199_tree);
                    }
                    if ( state.backtracking==0 ) {

                                retval.op = cmpopType.NotIn;
                            
                    }

                    }
                    break;
                case 10 :
                    // /usr/local/lib/jython/grammar/Python.g:1426:7: IS
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    IS200=(Token)match(input,IS,FOLLOW_IS_in_comp_op4891); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    IS200_tree = (PythonTree)adaptor.create(IS200);
                    adaptor.addChild(root_0, IS200_tree);
                    }
                    if ( state.backtracking==0 ) {

                                retval.op = cmpopType.Is;
                            
                    }

                    }
                    break;
                case 11 :
                    // /usr/local/lib/jython/grammar/Python.g:1430:7: IS NOT
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    IS201=(Token)match(input,IS,FOLLOW_IS_in_comp_op4907); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    IS201_tree = (PythonTree)adaptor.create(IS201);
                    adaptor.addChild(root_0, IS201_tree);
                    }
                    NOT202=(Token)match(input,NOT,FOLLOW_NOT_in_comp_op4909); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    NOT202_tree = (PythonTree)adaptor.create(NOT202);
                    adaptor.addChild(root_0, NOT202_tree);
                    }
                    if ( state.backtracking==0 ) {

                                retval.op = cmpopType.IsNot;
                            
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        catch (RecognitionException re) {
            reportError(re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "comp_op"

    protected static class expr_scope {
        expr_contextType ctype;
    }
    protected Stack expr_stack = new Stack();

    public static class expr_return extends ParserRuleReturnScope {
        public Token leftTok;
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "expr"
    // /usr/local/lib/jython/grammar/Python.g:1437:1: expr[expr_contextType ect] returns [Token leftTok] : left= xor_expr ( (op= VBAR right+= xor_expr )+ | -> $left) ;
    public final PythonParser.expr_return expr(expr_contextType ect) throws RecognitionException {
        expr_stack.push(new expr_scope());
        PythonParser.expr_return retval = new PythonParser.expr_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token op=null;
        List list_right=null;
        PythonParser.xor_expr_return left = null;

        PythonParser.xor_expr_return right = null;
         right = null;
        PythonTree op_tree=null;
        RewriteRuleTokenStream stream_VBAR=new RewriteRuleTokenStream(adaptor,"token VBAR");
        RewriteRuleSubtreeStream stream_xor_expr=new RewriteRuleSubtreeStream(adaptor,"rule xor_expr");

            ((expr_scope)expr_stack.peek()).ctype = ect;

        try {
            // /usr/local/lib/jython/grammar/Python.g:1455:5: (left= xor_expr ( (op= VBAR right+= xor_expr )+ | -> $left) )
            // /usr/local/lib/jython/grammar/Python.g:1455:7: left= xor_expr ( (op= VBAR right+= xor_expr )+ | -> $left)
            {
            pushFollow(FOLLOW_xor_expr_in_expr4961);
            left=xor_expr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_xor_expr.add(left.getTree());
            // /usr/local/lib/jython/grammar/Python.g:1456:9: ( (op= VBAR right+= xor_expr )+ | -> $left)
            int alt91=2;
            int LA91_0 = input.LA(1);

            if ( (LA91_0==VBAR) ) {
                alt91=1;
            }
            else if ( (LA91_0==EOF||LA91_0==NEWLINE||(LA91_0>=AND && LA91_0<=AS)||LA91_0==FOR||LA91_0==IF||(LA91_0>=IN && LA91_0<=IS)||(LA91_0>=NOT && LA91_0<=ORELSE)||(LA91_0>=RPAREN && LA91_0<=COMMA)||(LA91_0>=SEMI && LA91_0<=DOUBLESLASHEQUAL)||(LA91_0>=LESS && LA91_0<=NOTEQUAL)||LA91_0==RBRACK||(LA91_0>=RCURLY && LA91_0<=BACKQUOTE)) ) {
                alt91=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 91, 0, input);

                throw nvae;
            }
            switch (alt91) {
                case 1 :
                    // /usr/local/lib/jython/grammar/Python.g:1456:11: (op= VBAR right+= xor_expr )+
                    {
                    // /usr/local/lib/jython/grammar/Python.g:1456:11: (op= VBAR right+= xor_expr )+
                    int cnt90=0;
                    loop90:
                    do {
                        int alt90=2;
                        int LA90_0 = input.LA(1);

                        if ( (LA90_0==VBAR) ) {
                            alt90=1;
                        }


                        switch (alt90) {
                    	case 1 :
                    	    // /usr/local/lib/jython/grammar/Python.g:1456:12: op= VBAR right+= xor_expr
                    	    {
                    	    op=(Token)match(input,VBAR,FOLLOW_VBAR_in_expr4976); if (state.failed) return retval; 
                    	    if ( state.backtracking==0 ) stream_VBAR.add(op);

                    	    pushFollow(FOLLOW_xor_expr_in_expr4980);
                    	    right=xor_expr();

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) stream_xor_expr.add(right.getTree());
                    	    if (list_right==null) list_right=new ArrayList();
                    	    list_right.add(right.getTree());


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt90 >= 1 ) break loop90;
                    	    if (state.backtracking>0) {state.failed=true; return retval;}
                                EarlyExitException eee =
                                    new EarlyExitException(90, input);
                                throw eee;
                        }
                        cnt90++;
                    } while (true);


                    }
                    break;
                case 2 :
                    // /usr/local/lib/jython/grammar/Python.g:1459:8: 
                    {

                    // AST REWRITE
                    // elements: left
                    // token labels: 
                    // rule labels: retval, left
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
                    RewriteRuleSubtreeStream stream_left=new RewriteRuleSubtreeStream(adaptor,"rule left",left!=null?left.tree:null);

                    root_0 = (PythonTree)adaptor.nil();
                    // 1459:8: -> $left
                    {
                        adaptor.addChild(root_0, stream_left.nextTree());

                    }

                    retval.tree = root_0;}
                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {

                  retval.leftTok = (left!=null?left.lparen:null);
                  if (op != null) {
                      Token tok = (left!=null?((Token)left.start):null);
                      if ((left!=null?left.lparen:null) != null) {
                          tok = (left!=null?left.lparen:null);
                      }
                      retval.tree = actions.makeBinOp(tok, (left!=null?((PythonTree)left.tree):null), operatorType.BitOr, list_right);
                  }

            }
        }

        catch (RecognitionException re) {
            reportError(re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
            expr_stack.pop();
        }
        return retval;
    }
    // $ANTLR end "expr"

    public static class xor_expr_return extends ParserRuleReturnScope {
        public Token lparen = null;
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "xor_expr"
    // /usr/local/lib/jython/grammar/Python.g:1465:1: xor_expr returns [Token lparen = null] : left= and_expr ( (op= CIRCUMFLEX right+= and_expr )+ | -> $left) ;
    public final PythonParser.xor_expr_return xor_expr() throws RecognitionException {
        PythonParser.xor_expr_return retval = new PythonParser.xor_expr_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token op=null;
        List list_right=null;
        PythonParser.and_expr_return left = null;

        PythonParser.and_expr_return right = null;
         right = null;
        PythonTree op_tree=null;
        RewriteRuleTokenStream stream_CIRCUMFLEX=new RewriteRuleTokenStream(adaptor,"token CIRCUMFLEX");
        RewriteRuleSubtreeStream stream_and_expr=new RewriteRuleSubtreeStream(adaptor,"rule and_expr");
        try {
            // /usr/local/lib/jython/grammar/Python.g:1477:5: (left= and_expr ( (op= CIRCUMFLEX right+= and_expr )+ | -> $left) )
            // /usr/local/lib/jython/grammar/Python.g:1477:7: left= and_expr ( (op= CIRCUMFLEX right+= and_expr )+ | -> $left)
            {
            pushFollow(FOLLOW_and_expr_in_xor_expr5059);
            left=and_expr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_and_expr.add(left.getTree());
            // /usr/local/lib/jython/grammar/Python.g:1478:9: ( (op= CIRCUMFLEX right+= and_expr )+ | -> $left)
            int alt93=2;
            int LA93_0 = input.LA(1);

            if ( (LA93_0==CIRCUMFLEX) ) {
                alt93=1;
            }
            else if ( (LA93_0==EOF||LA93_0==NEWLINE||(LA93_0>=AND && LA93_0<=AS)||LA93_0==FOR||LA93_0==IF||(LA93_0>=IN && LA93_0<=IS)||(LA93_0>=NOT && LA93_0<=ORELSE)||(LA93_0>=RPAREN && LA93_0<=COMMA)||(LA93_0>=SEMI && LA93_0<=DOUBLESLASHEQUAL)||(LA93_0>=LESS && LA93_0<=VBAR)||LA93_0==RBRACK||(LA93_0>=RCURLY && LA93_0<=BACKQUOTE)) ) {
                alt93=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 93, 0, input);

                throw nvae;
            }
            switch (alt93) {
                case 1 :
                    // /usr/local/lib/jython/grammar/Python.g:1478:11: (op= CIRCUMFLEX right+= and_expr )+
                    {
                    // /usr/local/lib/jython/grammar/Python.g:1478:11: (op= CIRCUMFLEX right+= and_expr )+
                    int cnt92=0;
                    loop92:
                    do {
                        int alt92=2;
                        int LA92_0 = input.LA(1);

                        if ( (LA92_0==CIRCUMFLEX) ) {
                            alt92=1;
                        }


                        switch (alt92) {
                    	case 1 :
                    	    // /usr/local/lib/jython/grammar/Python.g:1478:12: op= CIRCUMFLEX right+= and_expr
                    	    {
                    	    op=(Token)match(input,CIRCUMFLEX,FOLLOW_CIRCUMFLEX_in_xor_expr5074); if (state.failed) return retval; 
                    	    if ( state.backtracking==0 ) stream_CIRCUMFLEX.add(op);

                    	    pushFollow(FOLLOW_and_expr_in_xor_expr5078);
                    	    right=and_expr();

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) stream_and_expr.add(right.getTree());
                    	    if (list_right==null) list_right=new ArrayList();
                    	    list_right.add(right.getTree());


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt92 >= 1 ) break loop92;
                    	    if (state.backtracking>0) {state.failed=true; return retval;}
                                EarlyExitException eee =
                                    new EarlyExitException(92, input);
                                throw eee;
                        }
                        cnt92++;
                    } while (true);


                    }
                    break;
                case 2 :
                    // /usr/local/lib/jython/grammar/Python.g:1481:8: 
                    {

                    // AST REWRITE
                    // elements: left
                    // token labels: 
                    // rule labels: retval, left
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
                    RewriteRuleSubtreeStream stream_left=new RewriteRuleSubtreeStream(adaptor,"rule left",left!=null?left.tree:null);

                    root_0 = (PythonTree)adaptor.nil();
                    // 1481:8: -> $left
                    {
                        adaptor.addChild(root_0, stream_left.nextTree());

                    }

                    retval.tree = root_0;}
                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {

                  if (op != null) {
                      Token tok = (left!=null?((Token)left.start):null);
                      if ((left!=null?left.lparen:null) != null) {
                          tok = (left!=null?left.lparen:null);
                      }
                      retval.tree = actions.makeBinOp(tok, (left!=null?((PythonTree)left.tree):null), operatorType.BitXor, list_right);
                  }
                  retval.lparen = (left!=null?left.lparen:null);

            }
        }

        catch (RecognitionException re) {
            reportError(re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "xor_expr"

    public static class and_expr_return extends ParserRuleReturnScope {
        public Token lparen = null;
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "and_expr"
    // /usr/local/lib/jython/grammar/Python.g:1486:1: and_expr returns [Token lparen = null] : left= shift_expr ( (op= AMPER right+= shift_expr )+ | -> $left) ;
    public final PythonParser.and_expr_return and_expr() throws RecognitionException {
        PythonParser.and_expr_return retval = new PythonParser.and_expr_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token op=null;
        List list_right=null;
        PythonParser.shift_expr_return left = null;

        PythonParser.shift_expr_return right = null;
         right = null;
        PythonTree op_tree=null;
        RewriteRuleTokenStream stream_AMPER=new RewriteRuleTokenStream(adaptor,"token AMPER");
        RewriteRuleSubtreeStream stream_shift_expr=new RewriteRuleSubtreeStream(adaptor,"rule shift_expr");
        try {
            // /usr/local/lib/jython/grammar/Python.g:1498:5: (left= shift_expr ( (op= AMPER right+= shift_expr )+ | -> $left) )
            // /usr/local/lib/jython/grammar/Python.g:1498:7: left= shift_expr ( (op= AMPER right+= shift_expr )+ | -> $left)
            {
            pushFollow(FOLLOW_shift_expr_in_and_expr5156);
            left=shift_expr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_shift_expr.add(left.getTree());
            // /usr/local/lib/jython/grammar/Python.g:1499:9: ( (op= AMPER right+= shift_expr )+ | -> $left)
            int alt95=2;
            int LA95_0 = input.LA(1);

            if ( (LA95_0==AMPER) ) {
                alt95=1;
            }
            else if ( (LA95_0==EOF||LA95_0==NEWLINE||(LA95_0>=AND && LA95_0<=AS)||LA95_0==FOR||LA95_0==IF||(LA95_0>=IN && LA95_0<=IS)||(LA95_0>=NOT && LA95_0<=ORELSE)||(LA95_0>=RPAREN && LA95_0<=COMMA)||(LA95_0>=SEMI && LA95_0<=DOUBLESLASHEQUAL)||(LA95_0>=LESS && LA95_0<=CIRCUMFLEX)||LA95_0==RBRACK||(LA95_0>=RCURLY && LA95_0<=BACKQUOTE)) ) {
                alt95=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 95, 0, input);

                throw nvae;
            }
            switch (alt95) {
                case 1 :
                    // /usr/local/lib/jython/grammar/Python.g:1499:11: (op= AMPER right+= shift_expr )+
                    {
                    // /usr/local/lib/jython/grammar/Python.g:1499:11: (op= AMPER right+= shift_expr )+
                    int cnt94=0;
                    loop94:
                    do {
                        int alt94=2;
                        int LA94_0 = input.LA(1);

                        if ( (LA94_0==AMPER) ) {
                            alt94=1;
                        }


                        switch (alt94) {
                    	case 1 :
                    	    // /usr/local/lib/jython/grammar/Python.g:1499:12: op= AMPER right+= shift_expr
                    	    {
                    	    op=(Token)match(input,AMPER,FOLLOW_AMPER_in_and_expr5171); if (state.failed) return retval; 
                    	    if ( state.backtracking==0 ) stream_AMPER.add(op);

                    	    pushFollow(FOLLOW_shift_expr_in_and_expr5175);
                    	    right=shift_expr();

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) stream_shift_expr.add(right.getTree());
                    	    if (list_right==null) list_right=new ArrayList();
                    	    list_right.add(right.getTree());


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt94 >= 1 ) break loop94;
                    	    if (state.backtracking>0) {state.failed=true; return retval;}
                                EarlyExitException eee =
                                    new EarlyExitException(94, input);
                                throw eee;
                        }
                        cnt94++;
                    } while (true);


                    }
                    break;
                case 2 :
                    // /usr/local/lib/jython/grammar/Python.g:1502:8: 
                    {

                    // AST REWRITE
                    // elements: left
                    // token labels: 
                    // rule labels: retval, left
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
                    RewriteRuleSubtreeStream stream_left=new RewriteRuleSubtreeStream(adaptor,"rule left",left!=null?left.tree:null);

                    root_0 = (PythonTree)adaptor.nil();
                    // 1502:8: -> $left
                    {
                        adaptor.addChild(root_0, stream_left.nextTree());

                    }

                    retval.tree = root_0;}
                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {

                  if (op != null) {
                      Token tok = (left!=null?((Token)left.start):null);
                      if ((left!=null?left.lparen:null) != null) {
                          tok = (left!=null?left.lparen:null);
                      }
                      retval.tree = actions.makeBinOp(tok, (left!=null?((PythonTree)left.tree):null), operatorType.BitAnd, list_right);
                  }
                  retval.lparen = (left!=null?left.lparen:null);

            }
        }

        catch (RecognitionException re) {
            reportError(re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "and_expr"

    public static class shift_expr_return extends ParserRuleReturnScope {
        public Token lparen = null;
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "shift_expr"
    // /usr/local/lib/jython/grammar/Python.g:1507:1: shift_expr returns [Token lparen = null] : left= arith_expr ( ( shift_op right+= arith_expr )+ | -> $left) ;
    public final PythonParser.shift_expr_return shift_expr() throws RecognitionException {
        PythonParser.shift_expr_return retval = new PythonParser.shift_expr_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        List list_right=null;
        PythonParser.arith_expr_return left = null;

        PythonParser.shift_op_return shift_op203 = null;

        PythonParser.arith_expr_return right = null;
         right = null;
        RewriteRuleSubtreeStream stream_arith_expr=new RewriteRuleSubtreeStream(adaptor,"rule arith_expr");
        RewriteRuleSubtreeStream stream_shift_op=new RewriteRuleSubtreeStream(adaptor,"rule shift_op");

            List ops = new ArrayList();
            List toks = new ArrayList();

        try {
            // /usr/local/lib/jython/grammar/Python.g:1523:5: (left= arith_expr ( ( shift_op right+= arith_expr )+ | -> $left) )
            // /usr/local/lib/jython/grammar/Python.g:1523:7: left= arith_expr ( ( shift_op right+= arith_expr )+ | -> $left)
            {
            pushFollow(FOLLOW_arith_expr_in_shift_expr5258);
            left=arith_expr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_arith_expr.add(left.getTree());
            // /usr/local/lib/jython/grammar/Python.g:1524:9: ( ( shift_op right+= arith_expr )+ | -> $left)
            int alt97=2;
            int LA97_0 = input.LA(1);

            if ( (LA97_0==RIGHTSHIFT||LA97_0==LEFTSHIFT) ) {
                alt97=1;
            }
            else if ( (LA97_0==EOF||LA97_0==NEWLINE||(LA97_0>=AND && LA97_0<=AS)||LA97_0==FOR||LA97_0==IF||(LA97_0>=IN && LA97_0<=IS)||(LA97_0>=NOT && LA97_0<=ORELSE)||(LA97_0>=RPAREN && LA97_0<=COMMA)||(LA97_0>=SEMI && LA97_0<=DOUBLESLASHEQUAL)||(LA97_0>=LESS && LA97_0<=AMPER)||LA97_0==RBRACK||(LA97_0>=RCURLY && LA97_0<=BACKQUOTE)) ) {
                alt97=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 97, 0, input);

                throw nvae;
            }
            switch (alt97) {
                case 1 :
                    // /usr/local/lib/jython/grammar/Python.g:1524:11: ( shift_op right+= arith_expr )+
                    {
                    // /usr/local/lib/jython/grammar/Python.g:1524:11: ( shift_op right+= arith_expr )+
                    int cnt96=0;
                    loop96:
                    do {
                        int alt96=2;
                        int LA96_0 = input.LA(1);

                        if ( (LA96_0==RIGHTSHIFT||LA96_0==LEFTSHIFT) ) {
                            alt96=1;
                        }


                        switch (alt96) {
                    	case 1 :
                    	    // /usr/local/lib/jython/grammar/Python.g:1524:13: shift_op right+= arith_expr
                    	    {
                    	    pushFollow(FOLLOW_shift_op_in_shift_expr5272);
                    	    shift_op203=shift_op();

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) stream_shift_op.add(shift_op203.getTree());
                    	    pushFollow(FOLLOW_arith_expr_in_shift_expr5276);
                    	    right=arith_expr();

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) stream_arith_expr.add(right.getTree());
                    	    if (list_right==null) list_right=new ArrayList();
                    	    list_right.add(right.getTree());

                    	    if ( state.backtracking==0 ) {

                    	                      ops.add((shift_op203!=null?shift_op203.op:null));
                    	                      toks.add((shift_op203!=null?((Token)shift_op203.start):null));
                    	                  
                    	    }

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt96 >= 1 ) break loop96;
                    	    if (state.backtracking>0) {state.failed=true; return retval;}
                                EarlyExitException eee =
                                    new EarlyExitException(96, input);
                                throw eee;
                        }
                        cnt96++;
                    } while (true);


                    }
                    break;
                case 2 :
                    // /usr/local/lib/jython/grammar/Python.g:1531:8: 
                    {

                    // AST REWRITE
                    // elements: left
                    // token labels: 
                    // rule labels: retval, left
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
                    RewriteRuleSubtreeStream stream_left=new RewriteRuleSubtreeStream(adaptor,"rule left",left!=null?left.tree:null);

                    root_0 = (PythonTree)adaptor.nil();
                    // 1531:8: -> $left
                    {
                        adaptor.addChild(root_0, stream_left.nextTree());

                    }

                    retval.tree = root_0;}
                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {

                  if (!ops.isEmpty()) {
                      Token tok = (left!=null?((Token)left.start):null);
                      if ((left!=null?left.lparen:null) != null) {
                          tok = (left!=null?left.lparen:null);
                      }
                      retval.tree = actions.makeBinOp(tok, (left!=null?((PythonTree)left.tree):null), ops, list_right, toks);
                  }
                  retval.lparen = (left!=null?left.lparen:null);

            }
        }

        catch (RecognitionException re) {
            reportError(re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "shift_expr"

    public static class shift_op_return extends ParserRuleReturnScope {
        public operatorType op;
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "shift_op"
    // /usr/local/lib/jython/grammar/Python.g:1535:1: shift_op returns [operatorType op] : ( LEFTSHIFT | RIGHTSHIFT );
    public final PythonParser.shift_op_return shift_op() throws RecognitionException {
        PythonParser.shift_op_return retval = new PythonParser.shift_op_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token LEFTSHIFT204=null;
        Token RIGHTSHIFT205=null;

        PythonTree LEFTSHIFT204_tree=null;
        PythonTree RIGHTSHIFT205_tree=null;

        try {
            // /usr/local/lib/jython/grammar/Python.g:1537:5: ( LEFTSHIFT | RIGHTSHIFT )
            int alt98=2;
            int LA98_0 = input.LA(1);

            if ( (LA98_0==LEFTSHIFT) ) {
                alt98=1;
            }
            else if ( (LA98_0==RIGHTSHIFT) ) {
                alt98=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 98, 0, input);

                throw nvae;
            }
            switch (alt98) {
                case 1 :
                    // /usr/local/lib/jython/grammar/Python.g:1537:7: LEFTSHIFT
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    LEFTSHIFT204=(Token)match(input,LEFTSHIFT,FOLLOW_LEFTSHIFT_in_shift_op5360); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    LEFTSHIFT204_tree = (PythonTree)adaptor.create(LEFTSHIFT204);
                    adaptor.addChild(root_0, LEFTSHIFT204_tree);
                    }
                    if ( state.backtracking==0 ) {

                                retval.op = operatorType.LShift;
                            
                    }

                    }
                    break;
                case 2 :
                    // /usr/local/lib/jython/grammar/Python.g:1541:7: RIGHTSHIFT
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    RIGHTSHIFT205=(Token)match(input,RIGHTSHIFT,FOLLOW_RIGHTSHIFT_in_shift_op5376); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    RIGHTSHIFT205_tree = (PythonTree)adaptor.create(RIGHTSHIFT205);
                    adaptor.addChild(root_0, RIGHTSHIFT205_tree);
                    }
                    if ( state.backtracking==0 ) {

                                retval.op = operatorType.RShift;
                            
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        catch (RecognitionException re) {
            reportError(re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "shift_op"

    public static class arith_expr_return extends ParserRuleReturnScope {
        public Token lparen = null;
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "arith_expr"
    // /usr/local/lib/jython/grammar/Python.g:1548:1: arith_expr returns [Token lparen = null] : left= term ( ( arith_op right+= term )+ | -> $left) ;
    public final PythonParser.arith_expr_return arith_expr() throws RecognitionException {
        PythonParser.arith_expr_return retval = new PythonParser.arith_expr_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        List list_right=null;
        PythonParser.term_return left = null;

        PythonParser.arith_op_return arith_op206 = null;

        PythonParser.term_return right = null;
         right = null;
        RewriteRuleSubtreeStream stream_arith_op=new RewriteRuleSubtreeStream(adaptor,"rule arith_op");
        RewriteRuleSubtreeStream stream_term=new RewriteRuleSubtreeStream(adaptor,"rule term");

            List ops = new ArrayList();
            List toks = new ArrayList();

        try {
            // /usr/local/lib/jython/grammar/Python.g:1564:5: (left= term ( ( arith_op right+= term )+ | -> $left) )
            // /usr/local/lib/jython/grammar/Python.g:1564:7: left= term ( ( arith_op right+= term )+ | -> $left)
            {
            pushFollow(FOLLOW_term_in_arith_expr5422);
            left=term();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_term.add(left.getTree());
            // /usr/local/lib/jython/grammar/Python.g:1565:9: ( ( arith_op right+= term )+ | -> $left)
            int alt100=2;
            int LA100_0 = input.LA(1);

            if ( ((LA100_0>=PLUS && LA100_0<=MINUS)) ) {
                alt100=1;
            }
            else if ( (LA100_0==EOF||LA100_0==NEWLINE||(LA100_0>=AND && LA100_0<=AS)||LA100_0==FOR||LA100_0==IF||(LA100_0>=IN && LA100_0<=IS)||(LA100_0>=NOT && LA100_0<=ORELSE)||(LA100_0>=RPAREN && LA100_0<=COMMA)||(LA100_0>=SEMI && LA100_0<=LEFTSHIFT)||LA100_0==RBRACK||(LA100_0>=RCURLY && LA100_0<=BACKQUOTE)) ) {
                alt100=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 100, 0, input);

                throw nvae;
            }
            switch (alt100) {
                case 1 :
                    // /usr/local/lib/jython/grammar/Python.g:1565:11: ( arith_op right+= term )+
                    {
                    // /usr/local/lib/jython/grammar/Python.g:1565:11: ( arith_op right+= term )+
                    int cnt99=0;
                    loop99:
                    do {
                        int alt99=2;
                        int LA99_0 = input.LA(1);

                        if ( ((LA99_0>=PLUS && LA99_0<=MINUS)) ) {
                            alt99=1;
                        }


                        switch (alt99) {
                    	case 1 :
                    	    // /usr/local/lib/jython/grammar/Python.g:1565:12: arith_op right+= term
                    	    {
                    	    pushFollow(FOLLOW_arith_op_in_arith_expr5435);
                    	    arith_op206=arith_op();

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) stream_arith_op.add(arith_op206.getTree());
                    	    pushFollow(FOLLOW_term_in_arith_expr5439);
                    	    right=term();

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) stream_term.add(right.getTree());
                    	    if (list_right==null) list_right=new ArrayList();
                    	    list_right.add(right.getTree());

                    	    if ( state.backtracking==0 ) {

                    	                     ops.add((arith_op206!=null?arith_op206.op:null));
                    	                     toks.add((arith_op206!=null?((Token)arith_op206.start):null));
                    	                 
                    	    }

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt99 >= 1 ) break loop99;
                    	    if (state.backtracking>0) {state.failed=true; return retval;}
                                EarlyExitException eee =
                                    new EarlyExitException(99, input);
                                throw eee;
                        }
                        cnt99++;
                    } while (true);


                    }
                    break;
                case 2 :
                    // /usr/local/lib/jython/grammar/Python.g:1572:8: 
                    {

                    // AST REWRITE
                    // elements: left
                    // token labels: 
                    // rule labels: retval, left
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
                    RewriteRuleSubtreeStream stream_left=new RewriteRuleSubtreeStream(adaptor,"rule left",left!=null?left.tree:null);

                    root_0 = (PythonTree)adaptor.nil();
                    // 1572:8: -> $left
                    {
                        adaptor.addChild(root_0, stream_left.nextTree());

                    }

                    retval.tree = root_0;}
                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {

                  if (!ops.isEmpty()) {
                      Token tok = (left!=null?((Token)left.start):null);
                      if ((left!=null?left.lparen:null) != null) {
                          tok = (left!=null?left.lparen:null);
                      }
                      retval.tree = actions.makeBinOp(tok, (left!=null?((PythonTree)left.tree):null), ops, list_right, toks);
                  }
                  retval.lparen = (left!=null?left.lparen:null);

            }
        }
        catch (RewriteCardinalityException rce) {

                    PythonTree badNode = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), null);
                    retval.tree = badNode;
                    errorHandler.error("Internal Parser Error", badNode);
                
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "arith_expr"

    public static class arith_op_return extends ParserRuleReturnScope {
        public operatorType op;
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "arith_op"
    // /usr/local/lib/jython/grammar/Python.g:1584:1: arith_op returns [operatorType op] : ( PLUS | MINUS );
    public final PythonParser.arith_op_return arith_op() throws RecognitionException {
        PythonParser.arith_op_return retval = new PythonParser.arith_op_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token PLUS207=null;
        Token MINUS208=null;

        PythonTree PLUS207_tree=null;
        PythonTree MINUS208_tree=null;

        try {
            // /usr/local/lib/jython/grammar/Python.g:1586:5: ( PLUS | MINUS )
            int alt101=2;
            int LA101_0 = input.LA(1);

            if ( (LA101_0==PLUS) ) {
                alt101=1;
            }
            else if ( (LA101_0==MINUS) ) {
                alt101=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 101, 0, input);

                throw nvae;
            }
            switch (alt101) {
                case 1 :
                    // /usr/local/lib/jython/grammar/Python.g:1586:7: PLUS
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    PLUS207=(Token)match(input,PLUS,FOLLOW_PLUS_in_arith_op5547); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    PLUS207_tree = (PythonTree)adaptor.create(PLUS207);
                    adaptor.addChild(root_0, PLUS207_tree);
                    }
                    if ( state.backtracking==0 ) {

                                retval.op = operatorType.Add;
                            
                    }

                    }
                    break;
                case 2 :
                    // /usr/local/lib/jython/grammar/Python.g:1590:7: MINUS
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    MINUS208=(Token)match(input,MINUS,FOLLOW_MINUS_in_arith_op5563); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    MINUS208_tree = (PythonTree)adaptor.create(MINUS208);
                    adaptor.addChild(root_0, MINUS208_tree);
                    }
                    if ( state.backtracking==0 ) {

                                retval.op = operatorType.Sub;
                            
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        catch (RecognitionException re) {
            reportError(re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "arith_op"

    public static class term_return extends ParserRuleReturnScope {
        public Token lparen = null;
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "term"
    // /usr/local/lib/jython/grammar/Python.g:1597:1: term returns [Token lparen = null] : left= factor ( ( term_op right+= factor )+ | -> $left) ;
    public final PythonParser.term_return term() throws RecognitionException {
        PythonParser.term_return retval = new PythonParser.term_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        List list_right=null;
        PythonParser.factor_return left = null;

        PythonParser.term_op_return term_op209 = null;

        PythonParser.factor_return right = null;
         right = null;
        RewriteRuleSubtreeStream stream_term_op=new RewriteRuleSubtreeStream(adaptor,"rule term_op");
        RewriteRuleSubtreeStream stream_factor=new RewriteRuleSubtreeStream(adaptor,"rule factor");

            List ops = new ArrayList();
            List toks = new ArrayList();

        try {
            // /usr/local/lib/jython/grammar/Python.g:1613:5: (left= factor ( ( term_op right+= factor )+ | -> $left) )
            // /usr/local/lib/jython/grammar/Python.g:1613:7: left= factor ( ( term_op right+= factor )+ | -> $left)
            {
            pushFollow(FOLLOW_factor_in_term5609);
            left=factor();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_factor.add(left.getTree());
            // /usr/local/lib/jython/grammar/Python.g:1614:9: ( ( term_op right+= factor )+ | -> $left)
            int alt103=2;
            int LA103_0 = input.LA(1);

            if ( (LA103_0==STAR||(LA103_0>=SLASH && LA103_0<=DOUBLESLASH)) ) {
                alt103=1;
            }
            else if ( (LA103_0==EOF||LA103_0==NEWLINE||(LA103_0>=AND && LA103_0<=AS)||LA103_0==FOR||LA103_0==IF||(LA103_0>=IN && LA103_0<=IS)||(LA103_0>=NOT && LA103_0<=ORELSE)||(LA103_0>=RPAREN && LA103_0<=COMMA)||(LA103_0>=SEMI && LA103_0<=MINUS)||LA103_0==RBRACK||(LA103_0>=RCURLY && LA103_0<=BACKQUOTE)) ) {
                alt103=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 103, 0, input);

                throw nvae;
            }
            switch (alt103) {
                case 1 :
                    // /usr/local/lib/jython/grammar/Python.g:1614:11: ( term_op right+= factor )+
                    {
                    // /usr/local/lib/jython/grammar/Python.g:1614:11: ( term_op right+= factor )+
                    int cnt102=0;
                    loop102:
                    do {
                        int alt102=2;
                        int LA102_0 = input.LA(1);

                        if ( (LA102_0==STAR||(LA102_0>=SLASH && LA102_0<=DOUBLESLASH)) ) {
                            alt102=1;
                        }


                        switch (alt102) {
                    	case 1 :
                    	    // /usr/local/lib/jython/grammar/Python.g:1614:12: term_op right+= factor
                    	    {
                    	    pushFollow(FOLLOW_term_op_in_term5622);
                    	    term_op209=term_op();

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) stream_term_op.add(term_op209.getTree());
                    	    pushFollow(FOLLOW_factor_in_term5626);
                    	    right=factor();

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) stream_factor.add(right.getTree());
                    	    if (list_right==null) list_right=new ArrayList();
                    	    list_right.add(right.getTree());

                    	    if ( state.backtracking==0 ) {

                    	                    ops.add((term_op209!=null?term_op209.op:null));
                    	                    toks.add((term_op209!=null?((Token)term_op209.start):null));
                    	                
                    	    }

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt102 >= 1 ) break loop102;
                    	    if (state.backtracking>0) {state.failed=true; return retval;}
                                EarlyExitException eee =
                                    new EarlyExitException(102, input);
                                throw eee;
                        }
                        cnt102++;
                    } while (true);


                    }
                    break;
                case 2 :
                    // /usr/local/lib/jython/grammar/Python.g:1621:8: 
                    {

                    // AST REWRITE
                    // elements: left
                    // token labels: 
                    // rule labels: retval, left
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
                    RewriteRuleSubtreeStream stream_left=new RewriteRuleSubtreeStream(adaptor,"rule left",left!=null?left.tree:null);

                    root_0 = (PythonTree)adaptor.nil();
                    // 1621:8: -> $left
                    {
                        adaptor.addChild(root_0, stream_left.nextTree());

                    }

                    retval.tree = root_0;}
                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {

                  retval.lparen = (left!=null?left.lparen:null);
                  if (!ops.isEmpty()) {
                      Token tok = (left!=null?((Token)left.start):null);
                      if ((left!=null?left.lparen:null) != null) {
                          tok = (left!=null?left.lparen:null);
                      }
                      retval.tree = actions.makeBinOp(tok, (left!=null?((PythonTree)left.tree):null), ops, list_right, toks);
                  }

            }
        }

        catch (RecognitionException re) {
            reportError(re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "term"

    public static class term_op_return extends ParserRuleReturnScope {
        public operatorType op;
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "term_op"
    // /usr/local/lib/jython/grammar/Python.g:1625:1: term_op returns [operatorType op] : ( STAR | SLASH | PERCENT | DOUBLESLASH );
    public final PythonParser.term_op_return term_op() throws RecognitionException {
        PythonParser.term_op_return retval = new PythonParser.term_op_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token STAR210=null;
        Token SLASH211=null;
        Token PERCENT212=null;
        Token DOUBLESLASH213=null;

        PythonTree STAR210_tree=null;
        PythonTree SLASH211_tree=null;
        PythonTree PERCENT212_tree=null;
        PythonTree DOUBLESLASH213_tree=null;

        try {
            // /usr/local/lib/jython/grammar/Python.g:1627:5: ( STAR | SLASH | PERCENT | DOUBLESLASH )
            int alt104=4;
            switch ( input.LA(1) ) {
            case STAR:
                {
                alt104=1;
                }
                break;
            case SLASH:
                {
                alt104=2;
                }
                break;
            case PERCENT:
                {
                alt104=3;
                }
                break;
            case DOUBLESLASH:
                {
                alt104=4;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 104, 0, input);

                throw nvae;
            }

            switch (alt104) {
                case 1 :
                    // /usr/local/lib/jython/grammar/Python.g:1627:7: STAR
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    STAR210=(Token)match(input,STAR,FOLLOW_STAR_in_term_op5708); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    STAR210_tree = (PythonTree)adaptor.create(STAR210);
                    adaptor.addChild(root_0, STAR210_tree);
                    }
                    if ( state.backtracking==0 ) {

                                retval.op = operatorType.Mult;
                            
                    }

                    }
                    break;
                case 2 :
                    // /usr/local/lib/jython/grammar/Python.g:1631:7: SLASH
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    SLASH211=(Token)match(input,SLASH,FOLLOW_SLASH_in_term_op5724); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    SLASH211_tree = (PythonTree)adaptor.create(SLASH211);
                    adaptor.addChild(root_0, SLASH211_tree);
                    }
                    if ( state.backtracking==0 ) {

                                retval.op = operatorType.Div;
                            
                    }

                    }
                    break;
                case 3 :
                    // /usr/local/lib/jython/grammar/Python.g:1635:7: PERCENT
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    PERCENT212=(Token)match(input,PERCENT,FOLLOW_PERCENT_in_term_op5740); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    PERCENT212_tree = (PythonTree)adaptor.create(PERCENT212);
                    adaptor.addChild(root_0, PERCENT212_tree);
                    }
                    if ( state.backtracking==0 ) {

                                retval.op = operatorType.Mod;
                            
                    }

                    }
                    break;
                case 4 :
                    // /usr/local/lib/jython/grammar/Python.g:1639:7: DOUBLESLASH
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    DOUBLESLASH213=(Token)match(input,DOUBLESLASH,FOLLOW_DOUBLESLASH_in_term_op5756); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    DOUBLESLASH213_tree = (PythonTree)adaptor.create(DOUBLESLASH213);
                    adaptor.addChild(root_0, DOUBLESLASH213_tree);
                    }
                    if ( state.backtracking==0 ) {

                                retval.op = operatorType.FloorDiv;
                            
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        catch (RecognitionException re) {
            reportError(re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "term_op"

    public static class factor_return extends ParserRuleReturnScope {
        public expr etype;
        public Token lparen = null;
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "factor"
    // /usr/local/lib/jython/grammar/Python.g:1646:1: factor returns [expr etype, Token lparen = null] : ( PLUS p= factor | MINUS m= factor | TILDE t= factor | power );
    public final PythonParser.factor_return factor() throws RecognitionException {
        PythonParser.factor_return retval = new PythonParser.factor_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token PLUS214=null;
        Token MINUS215=null;
        Token TILDE216=null;
        PythonParser.factor_return p = null;

        PythonParser.factor_return m = null;

        PythonParser.factor_return t = null;

        PythonParser.power_return power217 = null;


        PythonTree PLUS214_tree=null;
        PythonTree MINUS215_tree=null;
        PythonTree TILDE216_tree=null;

        try {
            // /usr/local/lib/jython/grammar/Python.g:1651:5: ( PLUS p= factor | MINUS m= factor | TILDE t= factor | power )
            int alt105=4;
            int LA105_0 = input.LA(1);

            if ( (LA105_0==PLUS) ) {
                alt105=1;
            }
            else if ( (LA105_0==MINUS) ) {
                alt105=2;
            }
            else if ( (LA105_0==TILDE) ) {
                alt105=3;
            }
            else if ( (LA105_0==NAME||LA105_0==LPAREN||LA105_0==LBRACK||LA105_0==LCURLY||LA105_0==BACKQUOTE) ) {
                alt105=4;
            }
            else if ( (LA105_0==PRINT) && ((printFunction))) {
                alt105=4;
            }
            else if ( ((LA105_0>=INT && LA105_0<=STRING)) ) {
                alt105=4;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 105, 0, input);

                throw nvae;
            }
            switch (alt105) {
                case 1 :
                    // /usr/local/lib/jython/grammar/Python.g:1651:7: PLUS p= factor
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    PLUS214=(Token)match(input,PLUS,FOLLOW_PLUS_in_factor5795); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    PLUS214_tree = (PythonTree)adaptor.create(PLUS214);
                    adaptor.addChild(root_0, PLUS214_tree);
                    }
                    pushFollow(FOLLOW_factor_in_factor5799);
                    p=factor();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, p.getTree());
                    if ( state.backtracking==0 ) {

                                retval.etype = new UnaryOp(PLUS214, unaryopType.UAdd, (p!=null?p.etype:null));
                            
                    }

                    }
                    break;
                case 2 :
                    // /usr/local/lib/jython/grammar/Python.g:1655:7: MINUS m= factor
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    MINUS215=(Token)match(input,MINUS,FOLLOW_MINUS_in_factor5815); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    MINUS215_tree = (PythonTree)adaptor.create(MINUS215);
                    adaptor.addChild(root_0, MINUS215_tree);
                    }
                    pushFollow(FOLLOW_factor_in_factor5819);
                    m=factor();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, m.getTree());
                    if ( state.backtracking==0 ) {

                                retval.etype = actions.negate(MINUS215, (m!=null?m.etype:null));
                            
                    }

                    }
                    break;
                case 3 :
                    // /usr/local/lib/jython/grammar/Python.g:1659:7: TILDE t= factor
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    TILDE216=(Token)match(input,TILDE,FOLLOW_TILDE_in_factor5835); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    TILDE216_tree = (PythonTree)adaptor.create(TILDE216);
                    adaptor.addChild(root_0, TILDE216_tree);
                    }
                    pushFollow(FOLLOW_factor_in_factor5839);
                    t=factor();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, t.getTree());
                    if ( state.backtracking==0 ) {

                                retval.etype = new UnaryOp(TILDE216, unaryopType.Invert, (t!=null?t.etype:null));
                            
                    }

                    }
                    break;
                case 4 :
                    // /usr/local/lib/jython/grammar/Python.g:1663:7: power
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    pushFollow(FOLLOW_power_in_factor5855);
                    power217=power();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, power217.getTree());
                    if ( state.backtracking==0 ) {

                                retval.etype = actions.castExpr((power217!=null?((PythonTree)power217.tree):null));
                                retval.lparen = (power217!=null?power217.lparen:null);
                            
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {

                  retval.tree = retval.etype;

            }
        }

        catch (RecognitionException re) {
            reportError(re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "factor"

    public static class power_return extends ParserRuleReturnScope {
        public expr etype;
        public Token lparen = null;
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "power"
    // /usr/local/lib/jython/grammar/Python.g:1671:1: power returns [expr etype, Token lparen = null] : atom (t+= trailer[$atom.start, $atom.tree] )* ( options {greedy=true; } : d= DOUBLESTAR factor )? ;
    public final PythonParser.power_return power() throws RecognitionException {
        PythonParser.power_return retval = new PythonParser.power_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token d=null;
        List list_t=null;
        PythonParser.atom_return atom218 = null;

        PythonParser.factor_return factor219 = null;

        PythonParser.trailer_return t = null;
         t = null;
        PythonTree d_tree=null;

        try {
            // /usr/local/lib/jython/grammar/Python.g:1676:5: ( atom (t+= trailer[$atom.start, $atom.tree] )* ( options {greedy=true; } : d= DOUBLESTAR factor )? )
            // /usr/local/lib/jython/grammar/Python.g:1676:7: atom (t+= trailer[$atom.start, $atom.tree] )* ( options {greedy=true; } : d= DOUBLESTAR factor )?
            {
            root_0 = (PythonTree)adaptor.nil();

            pushFollow(FOLLOW_atom_in_power5894);
            atom218=atom();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, atom218.getTree());
            // /usr/local/lib/jython/grammar/Python.g:1676:12: (t+= trailer[$atom.start, $atom.tree] )*
            loop106:
            do {
                int alt106=2;
                int LA106_0 = input.LA(1);

                if ( (LA106_0==DOT||LA106_0==LPAREN||LA106_0==LBRACK) ) {
                    alt106=1;
                }


                switch (alt106) {
            	case 1 :
            	    // /usr/local/lib/jython/grammar/Python.g:1676:13: t+= trailer[$atom.start, $atom.tree]
            	    {
            	    pushFollow(FOLLOW_trailer_in_power5899);
            	    t=trailer((atom218!=null?((Token)atom218.start):null), (atom218!=null?((PythonTree)atom218.tree):null));

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, t.getTree());
            	    if (list_t==null) list_t=new ArrayList();
            	    list_t.add(t.getTree());


            	    }
            	    break;

            	default :
            	    break loop106;
                }
            } while (true);

            // /usr/local/lib/jython/grammar/Python.g:1676:51: ( options {greedy=true; } : d= DOUBLESTAR factor )?
            int alt107=2;
            int LA107_0 = input.LA(1);

            if ( (LA107_0==DOUBLESTAR) ) {
                alt107=1;
            }
            switch (alt107) {
                case 1 :
                    // /usr/local/lib/jython/grammar/Python.g:1676:75: d= DOUBLESTAR factor
                    {
                    d=(Token)match(input,DOUBLESTAR,FOLLOW_DOUBLESTAR_in_power5914); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    d_tree = (PythonTree)adaptor.create(d);
                    adaptor.addChild(root_0, d_tree);
                    }
                    pushFollow(FOLLOW_factor_in_power5916);
                    factor219=factor();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, factor219.getTree());

                    }
                    break;

            }

            if ( state.backtracking==0 ) {

                        retval.lparen = (atom218!=null?atom218.lparen:null);
                        //XXX: This could be better.
                        retval.etype = actions.castExpr((atom218!=null?((PythonTree)atom218.tree):null));
                        if (list_t != null) {
                            for(Object o : list_t) {
                                actions.recurseSetContext(retval.etype, expr_contextType.Load);
                                if (o instanceof Call) {
                                    Call c = (Call)o;
                                    c.setFunc((PyObject)retval.etype);
                                    retval.etype = c;
                                } else if (o instanceof Subscript) {
                                    Subscript c = (Subscript)o;
                                    c.setValue((PyObject)retval.etype);
                                    retval.etype = c;
                                } else if (o instanceof Attribute) {
                                    Attribute c = (Attribute)o;
                                    c.setCharStartIndex(retval.etype.getCharStartIndex());
                                    c.setValue((PyObject)retval.etype);
                                    retval.etype = c;
                                }
                            }
                        }
                        if (d != null) {
                            List right = new ArrayList();
                            right.add((factor219!=null?((PythonTree)factor219.tree):null));
                            retval.etype = actions.makeBinOp((atom218!=null?((Token)atom218.start):null), retval.etype, operatorType.Pow, right);
                        }
                    
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {

                  retval.tree = retval.etype;

            }
        }

        catch (RecognitionException re) {
            reportError(re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "power"

    public static class atom_return extends ParserRuleReturnScope {
        public Token lparen = null;
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "atom"
    // /usr/local/lib/jython/grammar/Python.g:1713:1: atom returns [Token lparen = null] : ( LPAREN ( yield_expr | testlist_gexp -> testlist_gexp | ) RPAREN | LBRACK ( listmaker[$LBRACK] -> listmaker | ) RBRACK | LCURLY ( dictorsetmaker[$LCURLY] -> dictorsetmaker | ) RCURLY | lb= BACKQUOTE testlist[expr_contextType.Load] rb= BACKQUOTE | name_or_print | INT | LONGINT | FLOAT | COMPLEX | (S+= STRING )+ );
    public final PythonParser.atom_return atom() throws RecognitionException {
        PythonParser.atom_return retval = new PythonParser.atom_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token lb=null;
        Token rb=null;
        Token LPAREN220=null;
        Token RPAREN223=null;
        Token LBRACK224=null;
        Token RBRACK226=null;
        Token LCURLY227=null;
        Token RCURLY229=null;
        Token INT232=null;
        Token LONGINT233=null;
        Token FLOAT234=null;
        Token COMPLEX235=null;
        Token S=null;
        List list_S=null;
        PythonParser.yield_expr_return yield_expr221 = null;

        PythonParser.testlist_gexp_return testlist_gexp222 = null;

        PythonParser.listmaker_return listmaker225 = null;

        PythonParser.dictorsetmaker_return dictorsetmaker228 = null;

        PythonParser.testlist_return testlist230 = null;

        PythonParser.name_or_print_return name_or_print231 = null;


        PythonTree lb_tree=null;
        PythonTree rb_tree=null;
        PythonTree LPAREN220_tree=null;
        PythonTree RPAREN223_tree=null;
        PythonTree LBRACK224_tree=null;
        PythonTree RBRACK226_tree=null;
        PythonTree LCURLY227_tree=null;
        PythonTree RCURLY229_tree=null;
        PythonTree INT232_tree=null;
        PythonTree LONGINT233_tree=null;
        PythonTree FLOAT234_tree=null;
        PythonTree COMPLEX235_tree=null;
        PythonTree S_tree=null;
        RewriteRuleTokenStream stream_RBRACK=new RewriteRuleTokenStream(adaptor,"token RBRACK");
        RewriteRuleTokenStream stream_RPAREN=new RewriteRuleTokenStream(adaptor,"token RPAREN");
        RewriteRuleTokenStream stream_LCURLY=new RewriteRuleTokenStream(adaptor,"token LCURLY");
        RewriteRuleTokenStream stream_LBRACK=new RewriteRuleTokenStream(adaptor,"token LBRACK");
        RewriteRuleTokenStream stream_LPAREN=new RewriteRuleTokenStream(adaptor,"token LPAREN");
        RewriteRuleTokenStream stream_RCURLY=new RewriteRuleTokenStream(adaptor,"token RCURLY");
        RewriteRuleSubtreeStream stream_testlist_gexp=new RewriteRuleSubtreeStream(adaptor,"rule testlist_gexp");
        RewriteRuleSubtreeStream stream_yield_expr=new RewriteRuleSubtreeStream(adaptor,"rule yield_expr");
        RewriteRuleSubtreeStream stream_listmaker=new RewriteRuleSubtreeStream(adaptor,"rule listmaker");
        RewriteRuleSubtreeStream stream_dictorsetmaker=new RewriteRuleSubtreeStream(adaptor,"rule dictorsetmaker");

            expr etype = null;

        try {
            // /usr/local/lib/jython/grammar/Python.g:1723:5: ( LPAREN ( yield_expr | testlist_gexp -> testlist_gexp | ) RPAREN | LBRACK ( listmaker[$LBRACK] -> listmaker | ) RBRACK | LCURLY ( dictorsetmaker[$LCURLY] -> dictorsetmaker | ) RCURLY | lb= BACKQUOTE testlist[expr_contextType.Load] rb= BACKQUOTE | name_or_print | INT | LONGINT | FLOAT | COMPLEX | (S+= STRING )+ )
            int alt112=10;
            alt112 = dfa112.predict(input);
            switch (alt112) {
                case 1 :
                    // /usr/local/lib/jython/grammar/Python.g:1723:7: LPAREN ( yield_expr | testlist_gexp -> testlist_gexp | ) RPAREN
                    {
                    LPAREN220=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_atom5966); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LPAREN.add(LPAREN220);

                    if ( state.backtracking==0 ) {

                                retval.lparen = LPAREN220;
                            
                    }
                    // /usr/local/lib/jython/grammar/Python.g:1727:7: ( yield_expr | testlist_gexp -> testlist_gexp | )
                    int alt108=3;
                    int LA108_0 = input.LA(1);

                    if ( (LA108_0==YIELD) ) {
                        alt108=1;
                    }
                    else if ( (LA108_0==NAME||LA108_0==NOT||LA108_0==LPAREN||(LA108_0>=PLUS && LA108_0<=MINUS)||(LA108_0>=TILDE && LA108_0<=LBRACK)||LA108_0==LCURLY||LA108_0==BACKQUOTE) ) {
                        alt108=2;
                    }
                    else if ( (LA108_0==PRINT) && ((printFunction))) {
                        alt108=2;
                    }
                    else if ( (LA108_0==LAMBDA||(LA108_0>=INT && LA108_0<=STRING)) ) {
                        alt108=2;
                    }
                    else if ( (LA108_0==RPAREN) ) {
                        alt108=3;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 108, 0, input);

                        throw nvae;
                    }
                    switch (alt108) {
                        case 1 :
                            // /usr/local/lib/jython/grammar/Python.g:1727:9: yield_expr
                            {
                            pushFollow(FOLLOW_yield_expr_in_atom5984);
                            yield_expr221=yield_expr();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_yield_expr.add(yield_expr221.getTree());
                            if ( state.backtracking==0 ) {

                                          etype = (yield_expr221!=null?yield_expr221.etype:null);
                                      
                            }

                            }
                            break;
                        case 2 :
                            // /usr/local/lib/jython/grammar/Python.g:1731:9: testlist_gexp
                            {
                            pushFollow(FOLLOW_testlist_gexp_in_atom6004);
                            testlist_gexp222=testlist_gexp();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_testlist_gexp.add(testlist_gexp222.getTree());


                            // AST REWRITE
                            // elements: testlist_gexp
                            // token labels: 
                            // rule labels: retval
                            // token list labels: 
                            // rule list labels: 
                            // wildcard labels: 
                            if ( state.backtracking==0 ) {
                            retval.tree = root_0;
                            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                            root_0 = (PythonTree)adaptor.nil();
                            // 1732:6: -> testlist_gexp
                            {
                                adaptor.addChild(root_0, stream_testlist_gexp.nextTree());

                            }

                            retval.tree = root_0;}
                            }
                            break;
                        case 3 :
                            // /usr/local/lib/jython/grammar/Python.g:1734:9: 
                            {
                            if ( state.backtracking==0 ) {

                                          etype = new Tuple(LPAREN220, new ArrayList<expr>(), ((expr_scope)expr_stack.peek()).ctype);
                                      
                            }

                            }
                            break;

                    }

                    RPAREN223=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_atom6047); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RPAREN.add(RPAREN223);


                    }
                    break;
                case 2 :
                    // /usr/local/lib/jython/grammar/Python.g:1739:7: LBRACK ( listmaker[$LBRACK] -> listmaker | ) RBRACK
                    {
                    LBRACK224=(Token)match(input,LBRACK,FOLLOW_LBRACK_in_atom6055); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LBRACK.add(LBRACK224);

                    // /usr/local/lib/jython/grammar/Python.g:1740:7: ( listmaker[$LBRACK] -> listmaker | )
                    int alt109=2;
                    int LA109_0 = input.LA(1);

                    if ( (LA109_0==NAME||LA109_0==NOT||LA109_0==LPAREN||(LA109_0>=PLUS && LA109_0<=MINUS)||(LA109_0>=TILDE && LA109_0<=LBRACK)||LA109_0==LCURLY||LA109_0==BACKQUOTE) ) {
                        alt109=1;
                    }
                    else if ( (LA109_0==PRINT) && ((printFunction))) {
                        alt109=1;
                    }
                    else if ( (LA109_0==LAMBDA||(LA109_0>=INT && LA109_0<=STRING)) ) {
                        alt109=1;
                    }
                    else if ( (LA109_0==RBRACK) ) {
                        alt109=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 109, 0, input);

                        throw nvae;
                    }
                    switch (alt109) {
                        case 1 :
                            // /usr/local/lib/jython/grammar/Python.g:1740:8: listmaker[$LBRACK]
                            {
                            pushFollow(FOLLOW_listmaker_in_atom6064);
                            listmaker225=listmaker(LBRACK224);

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_listmaker.add(listmaker225.getTree());


                            // AST REWRITE
                            // elements: listmaker
                            // token labels: 
                            // rule labels: retval
                            // token list labels: 
                            // rule list labels: 
                            // wildcard labels: 
                            if ( state.backtracking==0 ) {
                            retval.tree = root_0;
                            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                            root_0 = (PythonTree)adaptor.nil();
                            // 1741:6: -> listmaker
                            {
                                adaptor.addChild(root_0, stream_listmaker.nextTree());

                            }

                            retval.tree = root_0;}
                            }
                            break;
                        case 2 :
                            // /usr/local/lib/jython/grammar/Python.g:1743:8: 
                            {
                            if ( state.backtracking==0 ) {

                                         etype = new org.python.antlr.ast.List(LBRACK224, new ArrayList<expr>(), ((expr_scope)expr_stack.peek()).ctype);
                                     
                            }

                            }
                            break;

                    }

                    RBRACK226=(Token)match(input,RBRACK,FOLLOW_RBRACK_in_atom6107); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RBRACK.add(RBRACK226);


                    }
                    break;
                case 3 :
                    // /usr/local/lib/jython/grammar/Python.g:1748:7: LCURLY ( dictorsetmaker[$LCURLY] -> dictorsetmaker | ) RCURLY
                    {
                    LCURLY227=(Token)match(input,LCURLY,FOLLOW_LCURLY_in_atom6115); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LCURLY.add(LCURLY227);

                    // /usr/local/lib/jython/grammar/Python.g:1749:8: ( dictorsetmaker[$LCURLY] -> dictorsetmaker | )
                    int alt110=2;
                    int LA110_0 = input.LA(1);

                    if ( (LA110_0==NAME||LA110_0==NOT||LA110_0==LPAREN||(LA110_0>=PLUS && LA110_0<=MINUS)||(LA110_0>=TILDE && LA110_0<=LBRACK)||LA110_0==LCURLY||LA110_0==BACKQUOTE) ) {
                        alt110=1;
                    }
                    else if ( (LA110_0==PRINT) && ((printFunction))) {
                        alt110=1;
                    }
                    else if ( (LA110_0==LAMBDA||(LA110_0>=INT && LA110_0<=STRING)) ) {
                        alt110=1;
                    }
                    else if ( (LA110_0==RCURLY) ) {
                        alt110=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 110, 0, input);

                        throw nvae;
                    }
                    switch (alt110) {
                        case 1 :
                            // /usr/local/lib/jython/grammar/Python.g:1749:9: dictorsetmaker[$LCURLY]
                            {
                            pushFollow(FOLLOW_dictorsetmaker_in_atom6125);
                            dictorsetmaker228=dictorsetmaker(LCURLY227);

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_dictorsetmaker.add(dictorsetmaker228.getTree());


                            // AST REWRITE
                            // elements: dictorsetmaker
                            // token labels: 
                            // rule labels: retval
                            // token list labels: 
                            // rule list labels: 
                            // wildcard labels: 
                            if ( state.backtracking==0 ) {
                            retval.tree = root_0;
                            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                            root_0 = (PythonTree)adaptor.nil();
                            // 1750:7: -> dictorsetmaker
                            {
                                adaptor.addChild(root_0, stream_dictorsetmaker.nextTree());

                            }

                            retval.tree = root_0;}
                            }
                            break;
                        case 2 :
                            // /usr/local/lib/jython/grammar/Python.g:1752:9: 
                            {
                            if ( state.backtracking==0 ) {

                                          etype = new Dict(LCURLY227, new ArrayList<expr>(), new ArrayList<expr>());
                                      
                            }

                            }
                            break;

                    }

                    RCURLY229=(Token)match(input,RCURLY,FOLLOW_RCURLY_in_atom6173); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RCURLY.add(RCURLY229);


                    }
                    break;
                case 4 :
                    // /usr/local/lib/jython/grammar/Python.g:1757:8: lb= BACKQUOTE testlist[expr_contextType.Load] rb= BACKQUOTE
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    lb=(Token)match(input,BACKQUOTE,FOLLOW_BACKQUOTE_in_atom6184); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    lb_tree = (PythonTree)adaptor.create(lb);
                    adaptor.addChild(root_0, lb_tree);
                    }
                    pushFollow(FOLLOW_testlist_in_atom6186);
                    testlist230=testlist(expr_contextType.Load);

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, testlist230.getTree());
                    rb=(Token)match(input,BACKQUOTE,FOLLOW_BACKQUOTE_in_atom6191); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    rb_tree = (PythonTree)adaptor.create(rb);
                    adaptor.addChild(root_0, rb_tree);
                    }
                    if ( state.backtracking==0 ) {

                                 etype = new Repr(lb, actions.castExpr((testlist230!=null?((PythonTree)testlist230.tree):null)));
                             
                    }

                    }
                    break;
                case 5 :
                    // /usr/local/lib/jython/grammar/Python.g:1761:8: name_or_print
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    pushFollow(FOLLOW_name_or_print_in_atom6209);
                    name_or_print231=name_or_print();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, name_or_print231.getTree());
                    if ( state.backtracking==0 ) {

                                 etype = new Name((name_or_print231!=null?((Token)name_or_print231.start):null), (name_or_print231!=null?input.toString(name_or_print231.start,name_or_print231.stop):null), ((expr_scope)expr_stack.peek()).ctype);
                             
                    }

                    }
                    break;
                case 6 :
                    // /usr/local/lib/jython/grammar/Python.g:1765:8: INT
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    INT232=(Token)match(input,INT,FOLLOW_INT_in_atom6227); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    INT232_tree = (PythonTree)adaptor.create(INT232);
                    adaptor.addChild(root_0, INT232_tree);
                    }
                    if ( state.backtracking==0 ) {

                                 etype = new Num(INT232, actions.makeInt(INT232));
                             
                    }

                    }
                    break;
                case 7 :
                    // /usr/local/lib/jython/grammar/Python.g:1769:8: LONGINT
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    LONGINT233=(Token)match(input,LONGINT,FOLLOW_LONGINT_in_atom6245); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    LONGINT233_tree = (PythonTree)adaptor.create(LONGINT233);
                    adaptor.addChild(root_0, LONGINT233_tree);
                    }
                    if ( state.backtracking==0 ) {

                                 etype = new Num(LONGINT233, actions.makeInt(LONGINT233));
                             
                    }

                    }
                    break;
                case 8 :
                    // /usr/local/lib/jython/grammar/Python.g:1773:8: FLOAT
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    FLOAT234=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_atom6263); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    FLOAT234_tree = (PythonTree)adaptor.create(FLOAT234);
                    adaptor.addChild(root_0, FLOAT234_tree);
                    }
                    if ( state.backtracking==0 ) {

                                 etype = new Num(FLOAT234, actions.makeFloat(FLOAT234));
                             
                    }

                    }
                    break;
                case 9 :
                    // /usr/local/lib/jython/grammar/Python.g:1777:8: COMPLEX
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    COMPLEX235=(Token)match(input,COMPLEX,FOLLOW_COMPLEX_in_atom6281); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    COMPLEX235_tree = (PythonTree)adaptor.create(COMPLEX235);
                    adaptor.addChild(root_0, COMPLEX235_tree);
                    }
                    if ( state.backtracking==0 ) {

                                 etype = new Num(COMPLEX235, actions.makeComplex(COMPLEX235));
                             
                    }

                    }
                    break;
                case 10 :
                    // /usr/local/lib/jython/grammar/Python.g:1781:8: (S+= STRING )+
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    // /usr/local/lib/jython/grammar/Python.g:1781:8: (S+= STRING )+
                    int cnt111=0;
                    loop111:
                    do {
                        int alt111=2;
                        int LA111_0 = input.LA(1);

                        if ( (LA111_0==STRING) ) {
                            alt111=1;
                        }


                        switch (alt111) {
                    	case 1 :
                    	    // /usr/local/lib/jython/grammar/Python.g:1781:9: S+= STRING
                    	    {
                    	    S=(Token)match(input,STRING,FOLLOW_STRING_in_atom6302); if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) {
                    	    S_tree = (PythonTree)adaptor.create(S);
                    	    adaptor.addChild(root_0, S_tree);
                    	    }
                    	    if (list_S==null) list_S=new ArrayList();
                    	    list_S.add(S);


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt111 >= 1 ) break loop111;
                    	    if (state.backtracking>0) {state.failed=true; return retval;}
                                EarlyExitException eee =
                                    new EarlyExitException(111, input);
                                throw eee;
                        }
                        cnt111++;
                    } while (true);

                    if ( state.backtracking==0 ) {

                                 etype = new Str(actions.extractStringToken(list_S), actions.extractStrings(list_S, encoding, unicodeLiterals));
                             
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {

                 if (etype != null) {
                     retval.tree = etype;
                 }

            }
        }

        catch (RecognitionException re) {
            reportError(re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "atom"

    public static class listmaker_return extends ParserRuleReturnScope {
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "listmaker"
    // /usr/local/lib/jython/grammar/Python.g:1788:1: listmaker[Token lbrack] : t+= test[$expr::ctype] ( list_for[gens] | ( options {greedy=true; } : COMMA t+= test[$expr::ctype] )* ) ( COMMA )? ;
    public final PythonParser.listmaker_return listmaker(Token lbrack) throws RecognitionException {
        PythonParser.listmaker_return retval = new PythonParser.listmaker_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token COMMA237=null;
        Token COMMA238=null;
        List list_t=null;
        PythonParser.list_for_return list_for236 = null;

        PythonParser.test_return t = null;
         t = null;
        PythonTree COMMA237_tree=null;
        PythonTree COMMA238_tree=null;


            List gens = new ArrayList();
            expr etype = null;

        try {
            // /usr/local/lib/jython/grammar/Python.g:1796:5: (t+= test[$expr::ctype] ( list_for[gens] | ( options {greedy=true; } : COMMA t+= test[$expr::ctype] )* ) ( COMMA )? )
            // /usr/local/lib/jython/grammar/Python.g:1796:7: t+= test[$expr::ctype] ( list_for[gens] | ( options {greedy=true; } : COMMA t+= test[$expr::ctype] )* ) ( COMMA )?
            {
            root_0 = (PythonTree)adaptor.nil();

            pushFollow(FOLLOW_test_in_listmaker6345);
            t=test(((expr_scope)expr_stack.peek()).ctype);

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, t.getTree());
            if (list_t==null) list_t=new ArrayList();
            list_t.add(t.getTree());

            // /usr/local/lib/jython/grammar/Python.g:1797:9: ( list_for[gens] | ( options {greedy=true; } : COMMA t+= test[$expr::ctype] )* )
            int alt114=2;
            int LA114_0 = input.LA(1);

            if ( (LA114_0==FOR) ) {
                alt114=1;
            }
            else if ( (LA114_0==COMMA||LA114_0==RBRACK) ) {
                alt114=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 114, 0, input);

                throw nvae;
            }
            switch (alt114) {
                case 1 :
                    // /usr/local/lib/jython/grammar/Python.g:1797:10: list_for[gens]
                    {
                    pushFollow(FOLLOW_list_for_in_listmaker6357);
                    list_for236=list_for(gens);

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, list_for236.getTree());
                    if ( state.backtracking==0 ) {

                                   Collections.reverse(gens);
                                   List<comprehension> c = gens;
                                   etype = new ListComp(((Token)retval.start), actions.castExpr(list_t.get(0)), c);
                               
                    }

                    }
                    break;
                case 2 :
                    // /usr/local/lib/jython/grammar/Python.g:1803:11: ( options {greedy=true; } : COMMA t+= test[$expr::ctype] )*
                    {
                    // /usr/local/lib/jython/grammar/Python.g:1803:11: ( options {greedy=true; } : COMMA t+= test[$expr::ctype] )*
                    loop113:
                    do {
                        int alt113=2;
                        int LA113_0 = input.LA(1);

                        if ( (LA113_0==COMMA) ) {
                            int LA113_1 = input.LA(2);

                            if ( (LA113_1==NAME||LA113_1==PRINT||(LA113_1>=LAMBDA && LA113_1<=NOT)||LA113_1==LPAREN||(LA113_1>=PLUS && LA113_1<=MINUS)||(LA113_1>=TILDE && LA113_1<=LBRACK)||LA113_1==LCURLY||(LA113_1>=BACKQUOTE && LA113_1<=STRING)) ) {
                                alt113=1;
                            }


                        }


                        switch (alt113) {
                    	case 1 :
                    	    // /usr/local/lib/jython/grammar/Python.g:1803:35: COMMA t+= test[$expr::ctype]
                    	    {
                    	    COMMA237=(Token)match(input,COMMA,FOLLOW_COMMA_in_listmaker6389); if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) {
                    	    COMMA237_tree = (PythonTree)adaptor.create(COMMA237);
                    	    adaptor.addChild(root_0, COMMA237_tree);
                    	    }
                    	    pushFollow(FOLLOW_test_in_listmaker6393);
                    	    t=test(((expr_scope)expr_stack.peek()).ctype);

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) adaptor.addChild(root_0, t.getTree());
                    	    if (list_t==null) list_t=new ArrayList();
                    	    list_t.add(t.getTree());


                    	    }
                    	    break;

                    	default :
                    	    break loop113;
                        }
                    } while (true);

                    if ( state.backtracking==0 ) {

                                     etype = new org.python.antlr.ast.List(lbrack, actions.castExprs(list_t), ((expr_scope)expr_stack.peek()).ctype);
                                 
                    }

                    }
                    break;

            }

            // /usr/local/lib/jython/grammar/Python.g:1807:11: ( COMMA )?
            int alt115=2;
            int LA115_0 = input.LA(1);

            if ( (LA115_0==COMMA) ) {
                alt115=1;
            }
            switch (alt115) {
                case 1 :
                    // /usr/local/lib/jython/grammar/Python.g:1807:12: COMMA
                    {
                    COMMA238=(Token)match(input,COMMA,FOLLOW_COMMA_in_listmaker6422); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    COMMA238_tree = (PythonTree)adaptor.create(COMMA238);
                    adaptor.addChild(root_0, COMMA238_tree);
                    }

                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {

                 retval.tree = etype;

            }
        }

        catch (RecognitionException re) {
            reportError(re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "listmaker"

    public static class testlist_gexp_return extends ParserRuleReturnScope {
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "testlist_gexp"
    // /usr/local/lib/jython/grammar/Python.g:1811:1: testlist_gexp : t+= test[$expr::ctype] ( ( options {k=2; } : c1= COMMA t+= test[$expr::ctype] )* (c2= COMMA )? {...}? | -> test | ( comp_for[gens] ) ) ;
    public final PythonParser.testlist_gexp_return testlist_gexp() throws RecognitionException {
        PythonParser.testlist_gexp_return retval = new PythonParser.testlist_gexp_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token c1=null;
        Token c2=null;
        List list_t=null;
        PythonParser.comp_for_return comp_for239 = null;

        PythonParser.test_return t = null;
         t = null;
        PythonTree c1_tree=null;
        PythonTree c2_tree=null;
        RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
        RewriteRuleSubtreeStream stream_test=new RewriteRuleSubtreeStream(adaptor,"rule test");
        RewriteRuleSubtreeStream stream_comp_for=new RewriteRuleSubtreeStream(adaptor,"rule comp_for");

            expr etype = null;
            List gens = new ArrayList();

        try {
            // /usr/local/lib/jython/grammar/Python.g:1821:5: (t+= test[$expr::ctype] ( ( options {k=2; } : c1= COMMA t+= test[$expr::ctype] )* (c2= COMMA )? {...}? | -> test | ( comp_for[gens] ) ) )
            // /usr/local/lib/jython/grammar/Python.g:1821:7: t+= test[$expr::ctype] ( ( options {k=2; } : c1= COMMA t+= test[$expr::ctype] )* (c2= COMMA )? {...}? | -> test | ( comp_for[gens] ) )
            {
            pushFollow(FOLLOW_test_in_testlist_gexp6454);
            t=test(((expr_scope)expr_stack.peek()).ctype);

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_test.add(t.getTree());
            if (list_t==null) list_t=new ArrayList();
            list_t.add(t.getTree());

            // /usr/local/lib/jython/grammar/Python.g:1822:9: ( ( options {k=2; } : c1= COMMA t+= test[$expr::ctype] )* (c2= COMMA )? {...}? | -> test | ( comp_for[gens] ) )
            int alt118=3;
            switch ( input.LA(1) ) {
            case COMMA:
                {
                alt118=1;
                }
                break;
            case RPAREN:
                {
                int LA118_2 = input.LA(2);

                if ( (( c1 != null || c2 != null )) ) {
                    alt118=1;
                }
                else if ( (true) ) {
                    alt118=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 118, 2, input);

                    throw nvae;
                }
                }
                break;
            case FOR:
                {
                alt118=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 118, 0, input);

                throw nvae;
            }

            switch (alt118) {
                case 1 :
                    // /usr/local/lib/jython/grammar/Python.g:1822:11: ( options {k=2; } : c1= COMMA t+= test[$expr::ctype] )* (c2= COMMA )? {...}?
                    {
                    // /usr/local/lib/jython/grammar/Python.g:1822:11: ( options {k=2; } : c1= COMMA t+= test[$expr::ctype] )*
                    loop116:
                    do {
                        int alt116=2;
                        alt116 = dfa116.predict(input);
                        switch (alt116) {
                    	case 1 :
                    	    // /usr/local/lib/jython/grammar/Python.g:1822:28: c1= COMMA t+= test[$expr::ctype]
                    	    {
                    	    c1=(Token)match(input,COMMA,FOLLOW_COMMA_in_testlist_gexp6478); if (state.failed) return retval; 
                    	    if ( state.backtracking==0 ) stream_COMMA.add(c1);

                    	    pushFollow(FOLLOW_test_in_testlist_gexp6482);
                    	    t=test(((expr_scope)expr_stack.peek()).ctype);

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) stream_test.add(t.getTree());
                    	    if (list_t==null) list_t=new ArrayList();
                    	    list_t.add(t.getTree());


                    	    }
                    	    break;

                    	default :
                    	    break loop116;
                        }
                    } while (true);

                    // /usr/local/lib/jython/grammar/Python.g:1822:61: (c2= COMMA )?
                    int alt117=2;
                    int LA117_0 = input.LA(1);

                    if ( (LA117_0==COMMA) ) {
                        alt117=1;
                    }
                    switch (alt117) {
                        case 1 :
                            // /usr/local/lib/jython/grammar/Python.g:1822:62: c2= COMMA
                            {
                            c2=(Token)match(input,COMMA,FOLLOW_COMMA_in_testlist_gexp6490); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_COMMA.add(c2);


                            }
                            break;

                    }

                    if ( !(( c1 != null || c2 != null )) ) {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        throw new FailedPredicateException(input, "testlist_gexp", " $c1 != null || $c2 != null ");
                    }
                    if ( state.backtracking==0 ) {

                                     etype = new Tuple(((Token)retval.start), actions.castExprs(list_t), ((expr_scope)expr_stack.peek()).ctype);
                                 
                    }

                    }
                    break;
                case 2 :
                    // /usr/local/lib/jython/grammar/Python.g:1827:11: 
                    {

                    // AST REWRITE
                    // elements: test
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (PythonTree)adaptor.nil();
                    // 1827:11: -> test
                    {
                        adaptor.addChild(root_0, stream_test.nextTree());

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 3 :
                    // /usr/local/lib/jython/grammar/Python.g:1828:11: ( comp_for[gens] )
                    {
                    // /usr/local/lib/jython/grammar/Python.g:1828:11: ( comp_for[gens] )
                    // /usr/local/lib/jython/grammar/Python.g:1828:12: comp_for[gens]
                    {
                    pushFollow(FOLLOW_comp_for_in_testlist_gexp6544);
                    comp_for239=comp_for(gens);

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_comp_for.add(comp_for239.getTree());
                    if ( state.backtracking==0 ) {

                                     Collections.reverse(gens);
                                     List<comprehension> c = gens;
                                     expr e = actions.castExpr(list_t.get(0));
                                     if (e instanceof Context) {
                                         ((Context)e).setContext(expr_contextType.Load);
                                     }
                                     etype = new GeneratorExp(((Token)retval.start), actions.castExpr(list_t.get(0)), c);
                                 
                    }

                    }


                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {

                  if (etype != null) {
                      retval.tree = etype;
                  }

            }
        }

        catch (RecognitionException re) {
            reportError(re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "testlist_gexp"

    public static class lambdef_return extends ParserRuleReturnScope {
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "lambdef"
    // /usr/local/lib/jython/grammar/Python.g:1843:1: lambdef : LAMBDA ( varargslist )? COLON test[expr_contextType.Load] ;
    public final PythonParser.lambdef_return lambdef() throws RecognitionException {
        PythonParser.lambdef_return retval = new PythonParser.lambdef_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token LAMBDA240=null;
        Token COLON242=null;
        PythonParser.varargslist_return varargslist241 = null;

        PythonParser.test_return test243 = null;


        PythonTree LAMBDA240_tree=null;
        PythonTree COLON242_tree=null;


            expr etype = null;

        try {
            // /usr/local/lib/jython/grammar/Python.g:1850:5: ( LAMBDA ( varargslist )? COLON test[expr_contextType.Load] )
            // /usr/local/lib/jython/grammar/Python.g:1850:7: LAMBDA ( varargslist )? COLON test[expr_contextType.Load]
            {
            root_0 = (PythonTree)adaptor.nil();

            LAMBDA240=(Token)match(input,LAMBDA,FOLLOW_LAMBDA_in_lambdef6608); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            LAMBDA240_tree = (PythonTree)adaptor.create(LAMBDA240);
            adaptor.addChild(root_0, LAMBDA240_tree);
            }
            // /usr/local/lib/jython/grammar/Python.g:1850:14: ( varargslist )?
            int alt119=2;
            int LA119_0 = input.LA(1);

            if ( (LA119_0==NAME||LA119_0==LPAREN||(LA119_0>=STAR && LA119_0<=DOUBLESTAR)) ) {
                alt119=1;
            }
            switch (alt119) {
                case 1 :
                    // /usr/local/lib/jython/grammar/Python.g:1850:15: varargslist
                    {
                    pushFollow(FOLLOW_varargslist_in_lambdef6611);
                    varargslist241=varargslist();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, varargslist241.getTree());

                    }
                    break;

            }

            COLON242=(Token)match(input,COLON,FOLLOW_COLON_in_lambdef6615); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            COLON242_tree = (PythonTree)adaptor.create(COLON242);
            adaptor.addChild(root_0, COLON242_tree);
            }
            pushFollow(FOLLOW_test_in_lambdef6617);
            test243=test(expr_contextType.Load);

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, test243.getTree());
            if ( state.backtracking==0 ) {

                        arguments a = (varargslist241!=null?varargslist241.args:null);
                        if (a == null) {
                            a = new arguments(LAMBDA240, new ArrayList<expr>(), (Name)null, null, new ArrayList<expr>());
                        }
                        etype = new Lambda(LAMBDA240, a, actions.castExpr((test243!=null?((PythonTree)test243.tree):null)));
                    
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {

                  retval.tree = etype;

            }
        }

        catch (RecognitionException re) {
            reportError(re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "lambdef"

    public static class trailer_return extends ParserRuleReturnScope {
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "trailer"
    // /usr/local/lib/jython/grammar/Python.g:1861:1: trailer[Token begin, PythonTree ptree] : ( LPAREN ( arglist | ) RPAREN | LBRACK subscriptlist[$begin] RBRACK | DOT attr );
    public final PythonParser.trailer_return trailer(Token begin, PythonTree ptree) throws RecognitionException {
        PythonParser.trailer_return retval = new PythonParser.trailer_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token LPAREN244=null;
        Token RPAREN246=null;
        Token LBRACK247=null;
        Token RBRACK249=null;
        Token DOT250=null;
        PythonParser.arglist_return arglist245 = null;

        PythonParser.subscriptlist_return subscriptlist248 = null;

        PythonParser.attr_return attr251 = null;


        PythonTree LPAREN244_tree=null;
        PythonTree RPAREN246_tree=null;
        PythonTree LBRACK247_tree=null;
        PythonTree RBRACK249_tree=null;
        PythonTree DOT250_tree=null;


            expr etype = null;

        try {
            // /usr/local/lib/jython/grammar/Python.g:1870:5: ( LPAREN ( arglist | ) RPAREN | LBRACK subscriptlist[$begin] RBRACK | DOT attr )
            int alt121=3;
            switch ( input.LA(1) ) {
            case LPAREN:
                {
                alt121=1;
                }
                break;
            case LBRACK:
                {
                alt121=2;
                }
                break;
            case DOT:
                {
                alt121=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 121, 0, input);

                throw nvae;
            }

            switch (alt121) {
                case 1 :
                    // /usr/local/lib/jython/grammar/Python.g:1870:7: LPAREN ( arglist | ) RPAREN
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    LPAREN244=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_trailer6656); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    LPAREN244_tree = (PythonTree)adaptor.create(LPAREN244);
                    adaptor.addChild(root_0, LPAREN244_tree);
                    }
                    // /usr/local/lib/jython/grammar/Python.g:1871:7: ( arglist | )
                    int alt120=2;
                    int LA120_0 = input.LA(1);

                    if ( (LA120_0==NAME||LA120_0==NOT||LA120_0==LPAREN||(LA120_0>=PLUS && LA120_0<=MINUS)||(LA120_0>=TILDE && LA120_0<=LBRACK)||LA120_0==LCURLY||LA120_0==BACKQUOTE) ) {
                        alt120=1;
                    }
                    else if ( (LA120_0==PRINT) && ((printFunction))) {
                        alt120=1;
                    }
                    else if ( (LA120_0==LAMBDA||(LA120_0>=STAR && LA120_0<=DOUBLESTAR)||(LA120_0>=INT && LA120_0<=STRING)) ) {
                        alt120=1;
                    }
                    else if ( (LA120_0==RPAREN) ) {
                        alt120=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 120, 0, input);

                        throw nvae;
                    }
                    switch (alt120) {
                        case 1 :
                            // /usr/local/lib/jython/grammar/Python.g:1871:8: arglist
                            {
                            pushFollow(FOLLOW_arglist_in_trailer6665);
                            arglist245=arglist();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) adaptor.addChild(root_0, arglist245.getTree());
                            if ( state.backtracking==0 ) {

                                         etype = new Call(begin, actions.castExpr(ptree), actions.castExprs((arglist245!=null?arglist245.args:null)),
                                           actions.makeKeywords((arglist245!=null?arglist245.keywords:null)), (arglist245!=null?arglist245.starargs:null), (arglist245!=null?arglist245.kwargs:null));
                                     
                            }

                            }
                            break;
                        case 2 :
                            // /usr/local/lib/jython/grammar/Python.g:1877:8: 
                            {
                            if ( state.backtracking==0 ) {

                                         etype = new Call(begin, actions.castExpr(ptree), new ArrayList<expr>(), new ArrayList<keyword>(), null, null);
                                     
                            }

                            }
                            break;

                    }

                    RPAREN246=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_trailer6707); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    RPAREN246_tree = (PythonTree)adaptor.create(RPAREN246);
                    adaptor.addChild(root_0, RPAREN246_tree);
                    }

                    }
                    break;
                case 2 :
                    // /usr/local/lib/jython/grammar/Python.g:1882:7: LBRACK subscriptlist[$begin] RBRACK
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    LBRACK247=(Token)match(input,LBRACK,FOLLOW_LBRACK_in_trailer6715); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    LBRACK247_tree = (PythonTree)adaptor.create(LBRACK247);
                    adaptor.addChild(root_0, LBRACK247_tree);
                    }
                    pushFollow(FOLLOW_subscriptlist_in_trailer6717);
                    subscriptlist248=subscriptlist(begin);

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, subscriptlist248.getTree());
                    RBRACK249=(Token)match(input,RBRACK,FOLLOW_RBRACK_in_trailer6720); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    RBRACK249_tree = (PythonTree)adaptor.create(RBRACK249);
                    adaptor.addChild(root_0, RBRACK249_tree);
                    }
                    if ( state.backtracking==0 ) {

                                etype = new Subscript(begin, actions.castExpr(ptree), actions.castSlice((subscriptlist248!=null?((PythonTree)subscriptlist248.tree):null)), ((expr_scope)expr_stack.peek()).ctype);
                            
                    }

                    }
                    break;
                case 3 :
                    // /usr/local/lib/jython/grammar/Python.g:1886:7: DOT attr
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    DOT250=(Token)match(input,DOT,FOLLOW_DOT_in_trailer6736); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    DOT250_tree = (PythonTree)adaptor.create(DOT250);
                    adaptor.addChild(root_0, DOT250_tree);
                    }
                    pushFollow(FOLLOW_attr_in_trailer6738);
                    attr251=attr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, attr251.getTree());
                    if ( state.backtracking==0 ) {

                                etype = new Attribute(begin, actions.castExpr(ptree), new Name((attr251!=null?((PythonTree)attr251.tree):null), (attr251!=null?input.toString(attr251.start,attr251.stop):null), expr_contextType.Load), ((expr_scope)expr_stack.peek()).ctype);
                            
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {

                  if (etype != null) {
                      retval.tree = etype;
                  }

            }
        }

        catch (RecognitionException re) {
            reportError(re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "trailer"

    public static class subscriptlist_return extends ParserRuleReturnScope {
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "subscriptlist"
    // /usr/local/lib/jython/grammar/Python.g:1893:1: subscriptlist[Token begin] : sub+= subscript ( options {greedy=true; } : c1= COMMA sub+= subscript )* (c2= COMMA )? ;
    public final PythonParser.subscriptlist_return subscriptlist(Token begin) throws RecognitionException {
        PythonParser.subscriptlist_return retval = new PythonParser.subscriptlist_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token c1=null;
        Token c2=null;
        List list_sub=null;
        PythonParser.subscript_return sub = null;
         sub = null;
        PythonTree c1_tree=null;
        PythonTree c2_tree=null;


            slice sltype = null;

        try {
            // /usr/local/lib/jython/grammar/Python.g:1900:5: (sub+= subscript ( options {greedy=true; } : c1= COMMA sub+= subscript )* (c2= COMMA )? )
            // /usr/local/lib/jython/grammar/Python.g:1900:7: sub+= subscript ( options {greedy=true; } : c1= COMMA sub+= subscript )* (c2= COMMA )?
            {
            root_0 = (PythonTree)adaptor.nil();

            pushFollow(FOLLOW_subscript_in_subscriptlist6777);
            sub=subscript();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, sub.getTree());
            if (list_sub==null) list_sub=new ArrayList();
            list_sub.add(sub.getTree());

            // /usr/local/lib/jython/grammar/Python.g:1900:22: ( options {greedy=true; } : c1= COMMA sub+= subscript )*
            loop122:
            do {
                int alt122=2;
                int LA122_0 = input.LA(1);

                if ( (LA122_0==COMMA) ) {
                    int LA122_1 = input.LA(2);

                    if ( ((LA122_1>=NAME && LA122_1<=PRINT)||(LA122_1>=LAMBDA && LA122_1<=NOT)||LA122_1==LPAREN||LA122_1==COLON||(LA122_1>=PLUS && LA122_1<=MINUS)||(LA122_1>=TILDE && LA122_1<=LBRACK)||LA122_1==LCURLY||(LA122_1>=BACKQUOTE && LA122_1<=STRING)) ) {
                        alt122=1;
                    }


                }


                switch (alt122) {
            	case 1 :
            	    // /usr/local/lib/jython/grammar/Python.g:1900:46: c1= COMMA sub+= subscript
            	    {
            	    c1=(Token)match(input,COMMA,FOLLOW_COMMA_in_subscriptlist6789); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    c1_tree = (PythonTree)adaptor.create(c1);
            	    adaptor.addChild(root_0, c1_tree);
            	    }
            	    pushFollow(FOLLOW_subscript_in_subscriptlist6793);
            	    sub=subscript();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, sub.getTree());
            	    if (list_sub==null) list_sub=new ArrayList();
            	    list_sub.add(sub.getTree());


            	    }
            	    break;

            	default :
            	    break loop122;
                }
            } while (true);

            // /usr/local/lib/jython/grammar/Python.g:1900:72: (c2= COMMA )?
            int alt123=2;
            int LA123_0 = input.LA(1);

            if ( (LA123_0==COMMA) ) {
                alt123=1;
            }
            switch (alt123) {
                case 1 :
                    // /usr/local/lib/jython/grammar/Python.g:1900:73: c2= COMMA
                    {
                    c2=(Token)match(input,COMMA,FOLLOW_COMMA_in_subscriptlist6800); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    c2_tree = (PythonTree)adaptor.create(c2);
                    adaptor.addChild(root_0, c2_tree);
                    }

                    }
                    break;

            }

            if ( state.backtracking==0 ) {

                        sltype = actions.makeSliceType(begin, c1, c2, list_sub);
                    
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {

                 retval.tree = sltype;

            }
        }

        catch (RecognitionException re) {
            reportError(re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "subscriptlist"

    public static class subscript_return extends ParserRuleReturnScope {
        public slice sltype;
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "subscript"
    // /usr/local/lib/jython/grammar/Python.g:1907:1: subscript returns [slice sltype] : (d1= DOT DOT DOT | ( test[null] COLON )=>lower= test[expr_contextType.Load] (c1= COLON (upper1= test[expr_contextType.Load] )? ( sliceop )? )? | ( COLON )=>c2= COLON (upper2= test[expr_contextType.Load] )? ( sliceop )? | test[expr_contextType.Load] );
    public final PythonParser.subscript_return subscript() throws RecognitionException {
        PythonParser.subscript_return retval = new PythonParser.subscript_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token d1=null;
        Token c1=null;
        Token c2=null;
        Token DOT252=null;
        Token DOT253=null;
        PythonParser.test_return lower = null;

        PythonParser.test_return upper1 = null;

        PythonParser.test_return upper2 = null;

        PythonParser.sliceop_return sliceop254 = null;

        PythonParser.sliceop_return sliceop255 = null;

        PythonParser.test_return test256 = null;


        PythonTree d1_tree=null;
        PythonTree c1_tree=null;
        PythonTree c2_tree=null;
        PythonTree DOT252_tree=null;
        PythonTree DOT253_tree=null;

        try {
            // /usr/local/lib/jython/grammar/Python.g:1912:5: (d1= DOT DOT DOT | ( test[null] COLON )=>lower= test[expr_contextType.Load] (c1= COLON (upper1= test[expr_contextType.Load] )? ( sliceop )? )? | ( COLON )=>c2= COLON (upper2= test[expr_contextType.Load] )? ( sliceop )? | test[expr_contextType.Load] )
            int alt129=4;
            alt129 = dfa129.predict(input);
            switch (alt129) {
                case 1 :
                    // /usr/local/lib/jython/grammar/Python.g:1912:7: d1= DOT DOT DOT
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    d1=(Token)match(input,DOT,FOLLOW_DOT_in_subscript6843); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    d1_tree = (PythonTree)adaptor.create(d1);
                    adaptor.addChild(root_0, d1_tree);
                    }
                    DOT252=(Token)match(input,DOT,FOLLOW_DOT_in_subscript6845); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    DOT252_tree = (PythonTree)adaptor.create(DOT252);
                    adaptor.addChild(root_0, DOT252_tree);
                    }
                    DOT253=(Token)match(input,DOT,FOLLOW_DOT_in_subscript6847); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    DOT253_tree = (PythonTree)adaptor.create(DOT253);
                    adaptor.addChild(root_0, DOT253_tree);
                    }
                    if ( state.backtracking==0 ) {

                                retval.sltype = new Ellipsis(d1);
                            
                    }

                    }
                    break;
                case 2 :
                    // /usr/local/lib/jython/grammar/Python.g:1916:7: ( test[null] COLON )=>lower= test[expr_contextType.Load] (c1= COLON (upper1= test[expr_contextType.Load] )? ( sliceop )? )?
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    pushFollow(FOLLOW_test_in_subscript6877);
                    lower=test(expr_contextType.Load);

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, lower.getTree());
                    // /usr/local/lib/jython/grammar/Python.g:1917:41: (c1= COLON (upper1= test[expr_contextType.Load] )? ( sliceop )? )?
                    int alt126=2;
                    int LA126_0 = input.LA(1);

                    if ( (LA126_0==COLON) ) {
                        alt126=1;
                    }
                    switch (alt126) {
                        case 1 :
                            // /usr/local/lib/jython/grammar/Python.g:1917:42: c1= COLON (upper1= test[expr_contextType.Load] )? ( sliceop )?
                            {
                            c1=(Token)match(input,COLON,FOLLOW_COLON_in_subscript6883); if (state.failed) return retval;
                            if ( state.backtracking==0 ) {
                            c1_tree = (PythonTree)adaptor.create(c1);
                            adaptor.addChild(root_0, c1_tree);
                            }
                            // /usr/local/lib/jython/grammar/Python.g:1917:51: (upper1= test[expr_contextType.Load] )?
                            int alt124=2;
                            int LA124_0 = input.LA(1);

                            if ( (LA124_0==NAME||LA124_0==NOT||LA124_0==LPAREN||(LA124_0>=PLUS && LA124_0<=MINUS)||(LA124_0>=TILDE && LA124_0<=LBRACK)||LA124_0==LCURLY||LA124_0==BACKQUOTE) ) {
                                alt124=1;
                            }
                            else if ( (LA124_0==PRINT) && ((printFunction))) {
                                alt124=1;
                            }
                            else if ( (LA124_0==LAMBDA||(LA124_0>=INT && LA124_0<=STRING)) ) {
                                alt124=1;
                            }
                            switch (alt124) {
                                case 1 :
                                    // /usr/local/lib/jython/grammar/Python.g:1917:52: upper1= test[expr_contextType.Load]
                                    {
                                    pushFollow(FOLLOW_test_in_subscript6888);
                                    upper1=test(expr_contextType.Load);

                                    state._fsp--;
                                    if (state.failed) return retval;
                                    if ( state.backtracking==0 ) adaptor.addChild(root_0, upper1.getTree());

                                    }
                                    break;

                            }

                            // /usr/local/lib/jython/grammar/Python.g:1917:89: ( sliceop )?
                            int alt125=2;
                            int LA125_0 = input.LA(1);

                            if ( (LA125_0==COLON) ) {
                                alt125=1;
                            }
                            switch (alt125) {
                                case 1 :
                                    // /usr/local/lib/jython/grammar/Python.g:1917:90: sliceop
                                    {
                                    pushFollow(FOLLOW_sliceop_in_subscript6894);
                                    sliceop254=sliceop();

                                    state._fsp--;
                                    if (state.failed) return retval;
                                    if ( state.backtracking==0 ) adaptor.addChild(root_0, sliceop254.getTree());

                                    }
                                    break;

                            }


                            }
                            break;

                    }

                    if ( state.backtracking==0 ) {

                                retval.sltype = actions.makeSubscript((lower!=null?((PythonTree)lower.tree):null), c1, (upper1!=null?((PythonTree)upper1.tree):null), (sliceop254!=null?((PythonTree)sliceop254.tree):null));
                            
                    }

                    }
                    break;
                case 3 :
                    // /usr/local/lib/jython/grammar/Python.g:1921:7: ( COLON )=>c2= COLON (upper2= test[expr_contextType.Load] )? ( sliceop )?
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    c2=(Token)match(input,COLON,FOLLOW_COLON_in_subscript6925); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    c2_tree = (PythonTree)adaptor.create(c2);
                    adaptor.addChild(root_0, c2_tree);
                    }
                    // /usr/local/lib/jython/grammar/Python.g:1922:16: (upper2= test[expr_contextType.Load] )?
                    int alt127=2;
                    int LA127_0 = input.LA(1);

                    if ( (LA127_0==NAME||LA127_0==NOT||LA127_0==LPAREN||(LA127_0>=PLUS && LA127_0<=MINUS)||(LA127_0>=TILDE && LA127_0<=LBRACK)||LA127_0==LCURLY||LA127_0==BACKQUOTE) ) {
                        alt127=1;
                    }
                    else if ( (LA127_0==PRINT) && ((printFunction))) {
                        alt127=1;
                    }
                    else if ( (LA127_0==LAMBDA||(LA127_0>=INT && LA127_0<=STRING)) ) {
                        alt127=1;
                    }
                    switch (alt127) {
                        case 1 :
                            // /usr/local/lib/jython/grammar/Python.g:1922:17: upper2= test[expr_contextType.Load]
                            {
                            pushFollow(FOLLOW_test_in_subscript6930);
                            upper2=test(expr_contextType.Load);

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) adaptor.addChild(root_0, upper2.getTree());

                            }
                            break;

                    }

                    // /usr/local/lib/jython/grammar/Python.g:1922:54: ( sliceop )?
                    int alt128=2;
                    int LA128_0 = input.LA(1);

                    if ( (LA128_0==COLON) ) {
                        alt128=1;
                    }
                    switch (alt128) {
                        case 1 :
                            // /usr/local/lib/jython/grammar/Python.g:1922:55: sliceop
                            {
                            pushFollow(FOLLOW_sliceop_in_subscript6936);
                            sliceop255=sliceop();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) adaptor.addChild(root_0, sliceop255.getTree());

                            }
                            break;

                    }

                    if ( state.backtracking==0 ) {

                                retval.sltype = actions.makeSubscript(null, c2, (upper2!=null?((PythonTree)upper2.tree):null), (sliceop255!=null?((PythonTree)sliceop255.tree):null));
                            
                    }

                    }
                    break;
                case 4 :
                    // /usr/local/lib/jython/grammar/Python.g:1926:7: test[expr_contextType.Load]
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    pushFollow(FOLLOW_test_in_subscript6954);
                    test256=test(expr_contextType.Load);

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, test256.getTree());
                    if ( state.backtracking==0 ) {

                                retval.sltype = new Index((test256!=null?((Token)test256.start):null), actions.castExpr((test256!=null?((PythonTree)test256.tree):null)));
                            
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {

                  retval.tree = retval.sltype;

            }
        }

        catch (RecognitionException re) {
            reportError(re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "subscript"

    public static class sliceop_return extends ParserRuleReturnScope {
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "sliceop"
    // /usr/local/lib/jython/grammar/Python.g:1933:1: sliceop : COLON ( test[expr_contextType.Load] -> test | ) ;
    public final PythonParser.sliceop_return sliceop() throws RecognitionException {
        PythonParser.sliceop_return retval = new PythonParser.sliceop_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token COLON257=null;
        PythonParser.test_return test258 = null;


        PythonTree COLON257_tree=null;
        RewriteRuleTokenStream stream_COLON=new RewriteRuleTokenStream(adaptor,"token COLON");
        RewriteRuleSubtreeStream stream_test=new RewriteRuleSubtreeStream(adaptor,"rule test");

            expr etype = null;

        try {
            // /usr/local/lib/jython/grammar/Python.g:1942:5: ( COLON ( test[expr_contextType.Load] -> test | ) )
            // /usr/local/lib/jython/grammar/Python.g:1942:7: COLON ( test[expr_contextType.Load] -> test | )
            {
            COLON257=(Token)match(input,COLON,FOLLOW_COLON_in_sliceop6991); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_COLON.add(COLON257);

            // /usr/local/lib/jython/grammar/Python.g:1943:6: ( test[expr_contextType.Load] -> test | )
            int alt130=2;
            int LA130_0 = input.LA(1);

            if ( (LA130_0==NAME||LA130_0==NOT||LA130_0==LPAREN||(LA130_0>=PLUS && LA130_0<=MINUS)||(LA130_0>=TILDE && LA130_0<=LBRACK)||LA130_0==LCURLY||LA130_0==BACKQUOTE) ) {
                alt130=1;
            }
            else if ( (LA130_0==PRINT) && ((printFunction))) {
                alt130=1;
            }
            else if ( (LA130_0==LAMBDA||(LA130_0>=INT && LA130_0<=STRING)) ) {
                alt130=1;
            }
            else if ( (LA130_0==COMMA||LA130_0==RBRACK) ) {
                alt130=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 130, 0, input);

                throw nvae;
            }
            switch (alt130) {
                case 1 :
                    // /usr/local/lib/jython/grammar/Python.g:1943:7: test[expr_contextType.Load]
                    {
                    pushFollow(FOLLOW_test_in_sliceop6999);
                    test258=test(expr_contextType.Load);

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_test.add(test258.getTree());


                    // AST REWRITE
                    // elements: test
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (PythonTree)adaptor.nil();
                    // 1944:5: -> test
                    {
                        adaptor.addChild(root_0, stream_test.nextTree());

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 2 :
                    // /usr/local/lib/jython/grammar/Python.g:1946:8: 
                    {
                    if ( state.backtracking==0 ) {

                                 etype = new Name(COLON257, "None", expr_contextType.Load);
                             
                    }

                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {

                  if (etype != null) {
                      retval.tree = etype;
                  }

            }
        }

        catch (RecognitionException re) {
            reportError(re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "sliceop"

    public static class exprlist_return extends ParserRuleReturnScope {
        public expr etype;
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "exprlist"
    // /usr/local/lib/jython/grammar/Python.g:1953:1: exprlist[expr_contextType ctype] returns [expr etype] : ( ( expr[null] COMMA )=>e+= expr[ctype] ( options {k=2; } : COMMA e+= expr[ctype] )* ( COMMA )? | expr[ctype] );
    public final PythonParser.exprlist_return exprlist(expr_contextType ctype) throws RecognitionException {
        PythonParser.exprlist_return retval = new PythonParser.exprlist_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token COMMA259=null;
        Token COMMA260=null;
        List list_e=null;
        PythonParser.expr_return expr261 = null;

        PythonParser.expr_return e = null;
         e = null;
        PythonTree COMMA259_tree=null;
        PythonTree COMMA260_tree=null;

        try {
            // /usr/local/lib/jython/grammar/Python.g:1955:5: ( ( expr[null] COMMA )=>e+= expr[ctype] ( options {k=2; } : COMMA e+= expr[ctype] )* ( COMMA )? | expr[ctype] )
            int alt133=2;
            alt133 = dfa133.predict(input);
            switch (alt133) {
                case 1 :
                    // /usr/local/lib/jython/grammar/Python.g:1955:7: ( expr[null] COMMA )=>e+= expr[ctype] ( options {k=2; } : COMMA e+= expr[ctype] )* ( COMMA )?
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    pushFollow(FOLLOW_expr_in_exprlist7070);
                    e=expr(ctype);

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, e.getTree());
                    if (list_e==null) list_e=new ArrayList();
                    list_e.add(e.getTree());

                    // /usr/local/lib/jython/grammar/Python.g:1955:44: ( options {k=2; } : COMMA e+= expr[ctype] )*
                    loop131:
                    do {
                        int alt131=2;
                        alt131 = dfa131.predict(input);
                        switch (alt131) {
                    	case 1 :
                    	    // /usr/local/lib/jython/grammar/Python.g:1955:61: COMMA e+= expr[ctype]
                    	    {
                    	    COMMA259=(Token)match(input,COMMA,FOLLOW_COMMA_in_exprlist7082); if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) {
                    	    COMMA259_tree = (PythonTree)adaptor.create(COMMA259);
                    	    adaptor.addChild(root_0, COMMA259_tree);
                    	    }
                    	    pushFollow(FOLLOW_expr_in_exprlist7086);
                    	    e=expr(ctype);

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) adaptor.addChild(root_0, e.getTree());
                    	    if (list_e==null) list_e=new ArrayList();
                    	    list_e.add(e.getTree());


                    	    }
                    	    break;

                    	default :
                    	    break loop131;
                        }
                    } while (true);

                    // /usr/local/lib/jython/grammar/Python.g:1955:84: ( COMMA )?
                    int alt132=2;
                    int LA132_0 = input.LA(1);

                    if ( (LA132_0==COMMA) ) {
                        alt132=1;
                    }
                    switch (alt132) {
                        case 1 :
                            // /usr/local/lib/jython/grammar/Python.g:1955:85: COMMA
                            {
                            COMMA260=(Token)match(input,COMMA,FOLLOW_COMMA_in_exprlist7092); if (state.failed) return retval;
                            if ( state.backtracking==0 ) {
                            COMMA260_tree = (PythonTree)adaptor.create(COMMA260);
                            adaptor.addChild(root_0, COMMA260_tree);
                            }

                            }
                            break;

                    }

                    if ( state.backtracking==0 ) {

                                 retval.etype = new Tuple(((Token)retval.start), actions.castExprs(list_e), ctype);
                             
                    }

                    }
                    break;
                case 2 :
                    // /usr/local/lib/jython/grammar/Python.g:1959:7: expr[ctype]
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    pushFollow(FOLLOW_expr_in_exprlist7111);
                    expr261=expr(ctype);

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, expr261.getTree());
                    if ( state.backtracking==0 ) {

                              retval.etype = actions.castExpr((expr261!=null?((PythonTree)expr261.tree):null));
                            
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        catch (RecognitionException re) {
            reportError(re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "exprlist"

    public static class del_list_return extends ParserRuleReturnScope {
        public List<expr> etypes;
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "del_list"
    // /usr/local/lib/jython/grammar/Python.g:1967:1: del_list returns [List<expr> etypes] : e+= expr[expr_contextType.Del] ( options {k=2; } : COMMA e+= expr[expr_contextType.Del] )* ( COMMA )? ;
    public final PythonParser.del_list_return del_list() throws RecognitionException {
        PythonParser.del_list_return retval = new PythonParser.del_list_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token COMMA262=null;
        Token COMMA263=null;
        List list_e=null;
        PythonParser.expr_return e = null;
         e = null;
        PythonTree COMMA262_tree=null;
        PythonTree COMMA263_tree=null;

        try {
            // /usr/local/lib/jython/grammar/Python.g:1969:5: (e+= expr[expr_contextType.Del] ( options {k=2; } : COMMA e+= expr[expr_contextType.Del] )* ( COMMA )? )
            // /usr/local/lib/jython/grammar/Python.g:1969:7: e+= expr[expr_contextType.Del] ( options {k=2; } : COMMA e+= expr[expr_contextType.Del] )* ( COMMA )?
            {
            root_0 = (PythonTree)adaptor.nil();

            pushFollow(FOLLOW_expr_in_del_list7149);
            e=expr(expr_contextType.Del);

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, e.getTree());
            if (list_e==null) list_e=new ArrayList();
            list_e.add(e.getTree());

            // /usr/local/lib/jython/grammar/Python.g:1969:37: ( options {k=2; } : COMMA e+= expr[expr_contextType.Del] )*
            loop134:
            do {
                int alt134=2;
                alt134 = dfa134.predict(input);
                switch (alt134) {
            	case 1 :
            	    // /usr/local/lib/jython/grammar/Python.g:1969:54: COMMA e+= expr[expr_contextType.Del]
            	    {
            	    COMMA262=(Token)match(input,COMMA,FOLLOW_COMMA_in_del_list7161); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    COMMA262_tree = (PythonTree)adaptor.create(COMMA262);
            	    adaptor.addChild(root_0, COMMA262_tree);
            	    }
            	    pushFollow(FOLLOW_expr_in_del_list7165);
            	    e=expr(expr_contextType.Del);

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, e.getTree());
            	    if (list_e==null) list_e=new ArrayList();
            	    list_e.add(e.getTree());


            	    }
            	    break;

            	default :
            	    break loop134;
                }
            } while (true);

            // /usr/local/lib/jython/grammar/Python.g:1969:92: ( COMMA )?
            int alt135=2;
            int LA135_0 = input.LA(1);

            if ( (LA135_0==COMMA) ) {
                alt135=1;
            }
            switch (alt135) {
                case 1 :
                    // /usr/local/lib/jython/grammar/Python.g:1969:93: COMMA
                    {
                    COMMA263=(Token)match(input,COMMA,FOLLOW_COMMA_in_del_list7171); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    COMMA263_tree = (PythonTree)adaptor.create(COMMA263);
                    adaptor.addChild(root_0, COMMA263_tree);
                    }

                    }
                    break;

            }

            if ( state.backtracking==0 ) {

                        retval.etypes = actions.makeDeleteList(list_e);
                    
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        catch (RecognitionException re) {
            reportError(re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "del_list"

    public static class testlist_return extends ParserRuleReturnScope {
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "testlist"
    // /usr/local/lib/jython/grammar/Python.g:1976:1: testlist[expr_contextType ctype] : ( ( test[null] COMMA )=>t+= test[ctype] ( options {k=2; } : COMMA t+= test[ctype] )* ( COMMA )? | test[ctype] );
    public final PythonParser.testlist_return testlist(expr_contextType ctype) throws RecognitionException {
        PythonParser.testlist_return retval = new PythonParser.testlist_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token COMMA264=null;
        Token COMMA265=null;
        List list_t=null;
        PythonParser.test_return test266 = null;

        PythonParser.test_return t = null;
         t = null;
        PythonTree COMMA264_tree=null;
        PythonTree COMMA265_tree=null;


            expr etype = null;

        try {
            // /usr/local/lib/jython/grammar/Python.g:1985:5: ( ( test[null] COMMA )=>t+= test[ctype] ( options {k=2; } : COMMA t+= test[ctype] )* ( COMMA )? | test[ctype] )
            int alt138=2;
            alt138 = dfa138.predict(input);
            switch (alt138) {
                case 1 :
                    // /usr/local/lib/jython/grammar/Python.g:1985:7: ( test[null] COMMA )=>t+= test[ctype] ( options {k=2; } : COMMA t+= test[ctype] )* ( COMMA )?
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    pushFollow(FOLLOW_test_in_testlist7224);
                    t=test(ctype);

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, t.getTree());
                    if (list_t==null) list_t=new ArrayList();
                    list_t.add(t.getTree());

                    // /usr/local/lib/jython/grammar/Python.g:1986:22: ( options {k=2; } : COMMA t+= test[ctype] )*
                    loop136:
                    do {
                        int alt136=2;
                        alt136 = dfa136.predict(input);
                        switch (alt136) {
                    	case 1 :
                    	    // /usr/local/lib/jython/grammar/Python.g:1986:39: COMMA t+= test[ctype]
                    	    {
                    	    COMMA264=(Token)match(input,COMMA,FOLLOW_COMMA_in_testlist7236); if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) {
                    	    COMMA264_tree = (PythonTree)adaptor.create(COMMA264);
                    	    adaptor.addChild(root_0, COMMA264_tree);
                    	    }
                    	    pushFollow(FOLLOW_test_in_testlist7240);
                    	    t=test(ctype);

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) adaptor.addChild(root_0, t.getTree());
                    	    if (list_t==null) list_t=new ArrayList();
                    	    list_t.add(t.getTree());


                    	    }
                    	    break;

                    	default :
                    	    break loop136;
                        }
                    } while (true);

                    // /usr/local/lib/jython/grammar/Python.g:1986:62: ( COMMA )?
                    int alt137=2;
                    int LA137_0 = input.LA(1);

                    if ( (LA137_0==COMMA) ) {
                        alt137=1;
                    }
                    switch (alt137) {
                        case 1 :
                            // /usr/local/lib/jython/grammar/Python.g:1986:63: COMMA
                            {
                            COMMA265=(Token)match(input,COMMA,FOLLOW_COMMA_in_testlist7246); if (state.failed) return retval;
                            if ( state.backtracking==0 ) {
                            COMMA265_tree = (PythonTree)adaptor.create(COMMA265);
                            adaptor.addChild(root_0, COMMA265_tree);
                            }

                            }
                            break;

                    }

                    if ( state.backtracking==0 ) {

                                etype = new Tuple(((Token)retval.start), actions.castExprs(list_t), ctype);
                            
                    }

                    }
                    break;
                case 2 :
                    // /usr/local/lib/jython/grammar/Python.g:1990:7: test[ctype]
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    pushFollow(FOLLOW_test_in_testlist7264);
                    test266=test(ctype);

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, test266.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {

                  if (etype != null) {
                      retval.tree = etype;
                  }

            }
        }

        catch (RecognitionException re) {
            reportError(re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "testlist"

    public static class dictorsetmaker_return extends ParserRuleReturnScope {
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "dictorsetmaker"
    // /usr/local/lib/jython/grammar/Python.g:1995:1: dictorsetmaker[Token lcurly] : k+= test[expr_contextType.Load] ( ( COLON v+= test[expr_contextType.Load] ( comp_for[gens] | ( options {k=2; } : COMMA k+= test[expr_contextType.Load] COLON v+= test[expr_contextType.Load] )* ) | ( COMMA k+= test[expr_contextType.Load] )* ) ( COMMA )? | comp_for[gens] ) ;
    public final PythonParser.dictorsetmaker_return dictorsetmaker(Token lcurly) throws RecognitionException {
        PythonParser.dictorsetmaker_return retval = new PythonParser.dictorsetmaker_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token COLON267=null;
        Token COMMA269=null;
        Token COLON270=null;
        Token COMMA271=null;
        Token COMMA272=null;
        List list_k=null;
        List list_v=null;
        PythonParser.comp_for_return comp_for268 = null;

        PythonParser.comp_for_return comp_for273 = null;

        PythonParser.test_return k = null;
         k = null;
        PythonParser.test_return v = null;
         v = null;
        PythonTree COLON267_tree=null;
        PythonTree COMMA269_tree=null;
        PythonTree COLON270_tree=null;
        PythonTree COMMA271_tree=null;
        PythonTree COMMA272_tree=null;


            List gens = new ArrayList();
            expr etype = null;

        try {
            // /usr/local/lib/jython/grammar/Python.g:2005:5: (k+= test[expr_contextType.Load] ( ( COLON v+= test[expr_contextType.Load] ( comp_for[gens] | ( options {k=2; } : COMMA k+= test[expr_contextType.Load] COLON v+= test[expr_contextType.Load] )* ) | ( COMMA k+= test[expr_contextType.Load] )* ) ( COMMA )? | comp_for[gens] ) )
            // /usr/local/lib/jython/grammar/Python.g:2005:7: k+= test[expr_contextType.Load] ( ( COLON v+= test[expr_contextType.Load] ( comp_for[gens] | ( options {k=2; } : COMMA k+= test[expr_contextType.Load] COLON v+= test[expr_contextType.Load] )* ) | ( COMMA k+= test[expr_contextType.Load] )* ) ( COMMA )? | comp_for[gens] )
            {
            root_0 = (PythonTree)adaptor.nil();

            pushFollow(FOLLOW_test_in_dictorsetmaker7297);
            k=test(expr_contextType.Load);

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, k.getTree());
            if (list_k==null) list_k=new ArrayList();
            list_k.add(k.getTree());

            // /usr/local/lib/jython/grammar/Python.g:2006:10: ( ( COLON v+= test[expr_contextType.Load] ( comp_for[gens] | ( options {k=2; } : COMMA k+= test[expr_contextType.Load] COLON v+= test[expr_contextType.Load] )* ) | ( COMMA k+= test[expr_contextType.Load] )* ) ( COMMA )? | comp_for[gens] )
            int alt144=2;
            int LA144_0 = input.LA(1);

            if ( (LA144_0==COLON||LA144_0==COMMA||LA144_0==RCURLY) ) {
                alt144=1;
            }
            else if ( (LA144_0==FOR) ) {
                alt144=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 144, 0, input);

                throw nvae;
            }
            switch (alt144) {
                case 1 :
                    // /usr/local/lib/jython/grammar/Python.g:2007:14: ( COLON v+= test[expr_contextType.Load] ( comp_for[gens] | ( options {k=2; } : COMMA k+= test[expr_contextType.Load] COLON v+= test[expr_contextType.Load] )* ) | ( COMMA k+= test[expr_contextType.Load] )* ) ( COMMA )?
                    {
                    // /usr/local/lib/jython/grammar/Python.g:2007:14: ( COLON v+= test[expr_contextType.Load] ( comp_for[gens] | ( options {k=2; } : COMMA k+= test[expr_contextType.Load] COLON v+= test[expr_contextType.Load] )* ) | ( COMMA k+= test[expr_contextType.Load] )* )
                    int alt142=2;
                    int LA142_0 = input.LA(1);

                    if ( (LA142_0==COLON) ) {
                        alt142=1;
                    }
                    else if ( (LA142_0==COMMA||LA142_0==RCURLY) ) {
                        alt142=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 142, 0, input);

                        throw nvae;
                    }
                    switch (alt142) {
                        case 1 :
                            // /usr/local/lib/jython/grammar/Python.g:2007:15: COLON v+= test[expr_contextType.Load] ( comp_for[gens] | ( options {k=2; } : COMMA k+= test[expr_contextType.Load] COLON v+= test[expr_contextType.Load] )* )
                            {
                            COLON267=(Token)match(input,COLON,FOLLOW_COLON_in_dictorsetmaker7325); if (state.failed) return retval;
                            if ( state.backtracking==0 ) {
                            COLON267_tree = (PythonTree)adaptor.create(COLON267);
                            adaptor.addChild(root_0, COLON267_tree);
                            }
                            pushFollow(FOLLOW_test_in_dictorsetmaker7329);
                            v=test(expr_contextType.Load);

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) adaptor.addChild(root_0, v.getTree());
                            if (list_v==null) list_v=new ArrayList();
                            list_v.add(v.getTree());

                            // /usr/local/lib/jython/grammar/Python.g:2008:16: ( comp_for[gens] | ( options {k=2; } : COMMA k+= test[expr_contextType.Load] COLON v+= test[expr_contextType.Load] )* )
                            int alt140=2;
                            int LA140_0 = input.LA(1);

                            if ( (LA140_0==FOR) ) {
                                alt140=1;
                            }
                            else if ( (LA140_0==COMMA||LA140_0==RCURLY) ) {
                                alt140=2;
                            }
                            else {
                                if (state.backtracking>0) {state.failed=true; return retval;}
                                NoViableAltException nvae =
                                    new NoViableAltException("", 140, 0, input);

                                throw nvae;
                            }
                            switch (alt140) {
                                case 1 :
                                    // /usr/local/lib/jython/grammar/Python.g:2008:18: comp_for[gens]
                                    {
                                    pushFollow(FOLLOW_comp_for_in_dictorsetmaker7349);
                                    comp_for268=comp_for(gens);

                                    state._fsp--;
                                    if (state.failed) return retval;
                                    if ( state.backtracking==0 ) adaptor.addChild(root_0, comp_for268.getTree());
                                    if ( state.backtracking==0 ) {

                                                           Collections.reverse(gens);
                                                           List<comprehension> c = gens;
                                                           etype = new DictComp(((Token)retval.start), actions.castExpr(list_k.get(0)), actions.castExpr(list_v.get(0)), c);
                                                       
                                    }

                                    }
                                    break;
                                case 2 :
                                    // /usr/local/lib/jython/grammar/Python.g:2014:18: ( options {k=2; } : COMMA k+= test[expr_contextType.Load] COLON v+= test[expr_contextType.Load] )*
                                    {
                                    // /usr/local/lib/jython/grammar/Python.g:2014:18: ( options {k=2; } : COMMA k+= test[expr_contextType.Load] COLON v+= test[expr_contextType.Load] )*
                                    loop139:
                                    do {
                                        int alt139=2;
                                        alt139 = dfa139.predict(input);
                                        switch (alt139) {
                                    	case 1 :
                                    	    // /usr/local/lib/jython/grammar/Python.g:2014:34: COMMA k+= test[expr_contextType.Load] COLON v+= test[expr_contextType.Load]
                                    	    {
                                    	    COMMA269=(Token)match(input,COMMA,FOLLOW_COMMA_in_dictorsetmaker7396); if (state.failed) return retval;
                                    	    if ( state.backtracking==0 ) {
                                    	    COMMA269_tree = (PythonTree)adaptor.create(COMMA269);
                                    	    adaptor.addChild(root_0, COMMA269_tree);
                                    	    }
                                    	    pushFollow(FOLLOW_test_in_dictorsetmaker7400);
                                    	    k=test(expr_contextType.Load);

                                    	    state._fsp--;
                                    	    if (state.failed) return retval;
                                    	    if ( state.backtracking==0 ) adaptor.addChild(root_0, k.getTree());
                                    	    if (list_k==null) list_k=new ArrayList();
                                    	    list_k.add(k.getTree());

                                    	    COLON270=(Token)match(input,COLON,FOLLOW_COLON_in_dictorsetmaker7403); if (state.failed) return retval;
                                    	    if ( state.backtracking==0 ) {
                                    	    COLON270_tree = (PythonTree)adaptor.create(COLON270);
                                    	    adaptor.addChild(root_0, COLON270_tree);
                                    	    }
                                    	    pushFollow(FOLLOW_test_in_dictorsetmaker7407);
                                    	    v=test(expr_contextType.Load);

                                    	    state._fsp--;
                                    	    if (state.failed) return retval;
                                    	    if ( state.backtracking==0 ) adaptor.addChild(root_0, v.getTree());
                                    	    if (list_v==null) list_v=new ArrayList();
                                    	    list_v.add(v.getTree());


                                    	    }
                                    	    break;

                                    	default :
                                    	    break loop139;
                                        }
                                    } while (true);

                                    if ( state.backtracking==0 ) {

                                                           etype = new Dict(lcurly, actions.castExprs(list_k), actions.castExprs(list_v));
                                                       
                                    }

                                    }
                                    break;

                            }


                            }
                            break;
                        case 2 :
                            // /usr/local/lib/jython/grammar/Python.g:2019:15: ( COMMA k+= test[expr_contextType.Load] )*
                            {
                            // /usr/local/lib/jython/grammar/Python.g:2019:15: ( COMMA k+= test[expr_contextType.Load] )*
                            loop141:
                            do {
                                int alt141=2;
                                int LA141_0 = input.LA(1);

                                if ( (LA141_0==COMMA) ) {
                                    int LA141_1 = input.LA(2);

                                    if ( (LA141_1==NAME||LA141_1==PRINT||(LA141_1>=LAMBDA && LA141_1<=NOT)||LA141_1==LPAREN||(LA141_1>=PLUS && LA141_1<=MINUS)||(LA141_1>=TILDE && LA141_1<=LBRACK)||LA141_1==LCURLY||(LA141_1>=BACKQUOTE && LA141_1<=STRING)) ) {
                                        alt141=1;
                                    }


                                }


                                switch (alt141) {
                            	case 1 :
                            	    // /usr/local/lib/jython/grammar/Python.g:2019:16: COMMA k+= test[expr_contextType.Load]
                            	    {
                            	    COMMA271=(Token)match(input,COMMA,FOLLOW_COMMA_in_dictorsetmaker7463); if (state.failed) return retval;
                            	    if ( state.backtracking==0 ) {
                            	    COMMA271_tree = (PythonTree)adaptor.create(COMMA271);
                            	    adaptor.addChild(root_0, COMMA271_tree);
                            	    }
                            	    pushFollow(FOLLOW_test_in_dictorsetmaker7467);
                            	    k=test(expr_contextType.Load);

                            	    state._fsp--;
                            	    if (state.failed) return retval;
                            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, k.getTree());
                            	    if (list_k==null) list_k=new ArrayList();
                            	    list_k.add(k.getTree());


                            	    }
                            	    break;

                            	default :
                            	    break loop141;
                                }
                            } while (true);

                            if ( state.backtracking==0 ) {

                                                etype = new Set(lcurly, actions.castExprs(list_k));
                                            
                            }

                            }
                            break;

                    }

                    // /usr/local/lib/jython/grammar/Python.g:2024:14: ( COMMA )?
                    int alt143=2;
                    int LA143_0 = input.LA(1);

                    if ( (LA143_0==COMMA) ) {
                        alt143=1;
                    }
                    switch (alt143) {
                        case 1 :
                            // /usr/local/lib/jython/grammar/Python.g:2024:15: COMMA
                            {
                            COMMA272=(Token)match(input,COMMA,FOLLOW_COMMA_in_dictorsetmaker7517); if (state.failed) return retval;
                            if ( state.backtracking==0 ) {
                            COMMA272_tree = (PythonTree)adaptor.create(COMMA272);
                            adaptor.addChild(root_0, COMMA272_tree);
                            }

                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // /usr/local/lib/jython/grammar/Python.g:2025:12: comp_for[gens]
                    {
                    pushFollow(FOLLOW_comp_for_in_dictorsetmaker7532);
                    comp_for273=comp_for(gens);

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, comp_for273.getTree());
                    if ( state.backtracking==0 ) {

                                     Collections.reverse(gens);
                                     List<comprehension> c = gens;
                                     expr e = actions.castExpr(list_k.get(0));
                                     if (e instanceof Context) {
                                         ((Context)e).setContext(expr_contextType.Load);
                                     }
                                     etype = new SetComp(lcurly, actions.castExpr(list_k.get(0)), c);
                                 
                    }

                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {

                  if (etype != null) {
                      retval.tree = etype;
                  }

            }
        }

        catch (RecognitionException re) {
            reportError(re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "dictorsetmaker"

    public static class classdef_return extends ParserRuleReturnScope {
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "classdef"
    // /usr/local/lib/jython/grammar/Python.g:2039:1: classdef : ( decorators )? CLASS NAME ( LPAREN ( testlist[expr_contextType.Load] )? RPAREN )? COLON suite[false] ;
    public final PythonParser.classdef_return classdef() throws RecognitionException {
        PythonParser.classdef_return retval = new PythonParser.classdef_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token CLASS275=null;
        Token NAME276=null;
        Token LPAREN277=null;
        Token RPAREN279=null;
        Token COLON280=null;
        PythonParser.decorators_return decorators274 = null;

        PythonParser.testlist_return testlist278 = null;

        PythonParser.suite_return suite281 = null;


        PythonTree CLASS275_tree=null;
        PythonTree NAME276_tree=null;
        PythonTree LPAREN277_tree=null;
        PythonTree RPAREN279_tree=null;
        PythonTree COLON280_tree=null;


            stmt stype = null;

        try {
            // /usr/local/lib/jython/grammar/Python.g:2046:5: ( ( decorators )? CLASS NAME ( LPAREN ( testlist[expr_contextType.Load] )? RPAREN )? COLON suite[false] )
            // /usr/local/lib/jython/grammar/Python.g:2046:7: ( decorators )? CLASS NAME ( LPAREN ( testlist[expr_contextType.Load] )? RPAREN )? COLON suite[false]
            {
            root_0 = (PythonTree)adaptor.nil();

            // /usr/local/lib/jython/grammar/Python.g:2046:7: ( decorators )?
            int alt145=2;
            int LA145_0 = input.LA(1);

            if ( (LA145_0==AT) ) {
                alt145=1;
            }
            switch (alt145) {
                case 1 :
                    // /usr/local/lib/jython/grammar/Python.g:2046:7: decorators
                    {
                    pushFollow(FOLLOW_decorators_in_classdef7585);
                    decorators274=decorators();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, decorators274.getTree());

                    }
                    break;

            }

            CLASS275=(Token)match(input,CLASS,FOLLOW_CLASS_in_classdef7588); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            CLASS275_tree = (PythonTree)adaptor.create(CLASS275);
            adaptor.addChild(root_0, CLASS275_tree);
            }
            NAME276=(Token)match(input,NAME,FOLLOW_NAME_in_classdef7590); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            NAME276_tree = (PythonTree)adaptor.create(NAME276);
            adaptor.addChild(root_0, NAME276_tree);
            }
            // /usr/local/lib/jython/grammar/Python.g:2046:30: ( LPAREN ( testlist[expr_contextType.Load] )? RPAREN )?
            int alt147=2;
            int LA147_0 = input.LA(1);

            if ( (LA147_0==LPAREN) ) {
                alt147=1;
            }
            switch (alt147) {
                case 1 :
                    // /usr/local/lib/jython/grammar/Python.g:2046:31: LPAREN ( testlist[expr_contextType.Load] )? RPAREN
                    {
                    LPAREN277=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_classdef7593); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    LPAREN277_tree = (PythonTree)adaptor.create(LPAREN277);
                    adaptor.addChild(root_0, LPAREN277_tree);
                    }
                    // /usr/local/lib/jython/grammar/Python.g:2046:38: ( testlist[expr_contextType.Load] )?
                    int alt146=2;
                    int LA146_0 = input.LA(1);

                    if ( (LA146_0==NAME||LA146_0==NOT||LA146_0==LPAREN||(LA146_0>=PLUS && LA146_0<=MINUS)||(LA146_0>=TILDE && LA146_0<=LBRACK)||LA146_0==LCURLY||LA146_0==BACKQUOTE) ) {
                        alt146=1;
                    }
                    else if ( (LA146_0==PRINT) && ((printFunction))) {
                        alt146=1;
                    }
                    else if ( (LA146_0==LAMBDA||(LA146_0>=INT && LA146_0<=STRING)) ) {
                        alt146=1;
                    }
                    switch (alt146) {
                        case 1 :
                            // /usr/local/lib/jython/grammar/Python.g:2046:38: testlist[expr_contextType.Load]
                            {
                            pushFollow(FOLLOW_testlist_in_classdef7595);
                            testlist278=testlist(expr_contextType.Load);

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) adaptor.addChild(root_0, testlist278.getTree());

                            }
                            break;

                    }

                    RPAREN279=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_classdef7599); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    RPAREN279_tree = (PythonTree)adaptor.create(RPAREN279);
                    adaptor.addChild(root_0, RPAREN279_tree);
                    }

                    }
                    break;

            }

            COLON280=(Token)match(input,COLON,FOLLOW_COLON_in_classdef7603); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            COLON280_tree = (PythonTree)adaptor.create(COLON280);
            adaptor.addChild(root_0, COLON280_tree);
            }
            pushFollow(FOLLOW_suite_in_classdef7605);
            suite281=suite(false);

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, suite281.getTree());
            if ( state.backtracking==0 ) {

                        Token t = CLASS275;
                        if ((decorators274!=null?((Token)decorators274.start):null) != null) {
                            t = (decorators274!=null?((Token)decorators274.start):null);
                        }
                        stype = new ClassDef(t, actions.cantBeNoneName(NAME276),
                            actions.makeBases(actions.castExpr((testlist278!=null?((PythonTree)testlist278.tree):null))),
                            actions.castStmts((suite281!=null?suite281.stypes:null)),
                            actions.castExprs((decorators274!=null?decorators274.etypes:null)));
                    
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {

                 retval.tree = stype;

            }
        }

        catch (RecognitionException re) {
            reportError(re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "classdef"

    public static class arglist_return extends ParserRuleReturnScope {
        public List args;
        public List keywords;
        public expr starargs;
        public expr kwargs;
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "arglist"
    // /usr/local/lib/jython/grammar/Python.g:2062:1: arglist returns [List args, List keywords, expr starargs, expr kwargs] : ( argument[arguments, kws, gens, true, false] ( COMMA argument[arguments, kws, gens, false, false] )* ( COMMA ( STAR s= test[expr_contextType.Load] ( COMMA argument[arguments, kws, gens, false, true] )* ( COMMA DOUBLESTAR k= test[expr_contextType.Load] )? | DOUBLESTAR k= test[expr_contextType.Load] )? )? | STAR s= test[expr_contextType.Load] ( COMMA argument[arguments, kws, gens, false, true] )* ( COMMA DOUBLESTAR k= test[expr_contextType.Load] )? | DOUBLESTAR k= test[expr_contextType.Load] );
    public final PythonParser.arglist_return arglist() throws RecognitionException {
        PythonParser.arglist_return retval = new PythonParser.arglist_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token COMMA283=null;
        Token COMMA285=null;
        Token STAR286=null;
        Token COMMA287=null;
        Token COMMA289=null;
        Token DOUBLESTAR290=null;
        Token DOUBLESTAR291=null;
        Token STAR292=null;
        Token COMMA293=null;
        Token COMMA295=null;
        Token DOUBLESTAR296=null;
        Token DOUBLESTAR297=null;
        PythonParser.test_return s = null;

        PythonParser.test_return k = null;

        PythonParser.argument_return argument282 = null;

        PythonParser.argument_return argument284 = null;

        PythonParser.argument_return argument288 = null;

        PythonParser.argument_return argument294 = null;


        PythonTree COMMA283_tree=null;
        PythonTree COMMA285_tree=null;
        PythonTree STAR286_tree=null;
        PythonTree COMMA287_tree=null;
        PythonTree COMMA289_tree=null;
        PythonTree DOUBLESTAR290_tree=null;
        PythonTree DOUBLESTAR291_tree=null;
        PythonTree STAR292_tree=null;
        PythonTree COMMA293_tree=null;
        PythonTree COMMA295_tree=null;
        PythonTree DOUBLESTAR296_tree=null;
        PythonTree DOUBLESTAR297_tree=null;


            List arguments = new ArrayList();
            List kws = new ArrayList();
            List gens = new ArrayList();

        try {
            // /usr/local/lib/jython/grammar/Python.g:2069:5: ( argument[arguments, kws, gens, true, false] ( COMMA argument[arguments, kws, gens, false, false] )* ( COMMA ( STAR s= test[expr_contextType.Load] ( COMMA argument[arguments, kws, gens, false, true] )* ( COMMA DOUBLESTAR k= test[expr_contextType.Load] )? | DOUBLESTAR k= test[expr_contextType.Load] )? )? | STAR s= test[expr_contextType.Load] ( COMMA argument[arguments, kws, gens, false, true] )* ( COMMA DOUBLESTAR k= test[expr_contextType.Load] )? | DOUBLESTAR k= test[expr_contextType.Load] )
            int alt155=3;
            int LA155_0 = input.LA(1);

            if ( (LA155_0==NAME||LA155_0==NOT||LA155_0==LPAREN||(LA155_0>=PLUS && LA155_0<=MINUS)||(LA155_0>=TILDE && LA155_0<=LBRACK)||LA155_0==LCURLY||LA155_0==BACKQUOTE) ) {
                alt155=1;
            }
            else if ( (LA155_0==PRINT) && ((printFunction))) {
                alt155=1;
            }
            else if ( (LA155_0==LAMBDA||(LA155_0>=INT && LA155_0<=STRING)) ) {
                alt155=1;
            }
            else if ( (LA155_0==STAR) ) {
                alt155=2;
            }
            else if ( (LA155_0==DOUBLESTAR) ) {
                alt155=3;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 155, 0, input);

                throw nvae;
            }
            switch (alt155) {
                case 1 :
                    // /usr/local/lib/jython/grammar/Python.g:2069:7: argument[arguments, kws, gens, true, false] ( COMMA argument[arguments, kws, gens, false, false] )* ( COMMA ( STAR s= test[expr_contextType.Load] ( COMMA argument[arguments, kws, gens, false, true] )* ( COMMA DOUBLESTAR k= test[expr_contextType.Load] )? | DOUBLESTAR k= test[expr_contextType.Load] )? )?
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    pushFollow(FOLLOW_argument_in_arglist7647);
                    argument282=argument(arguments, kws, gens, true, false);

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, argument282.getTree());
                    // /usr/local/lib/jython/grammar/Python.g:2069:51: ( COMMA argument[arguments, kws, gens, false, false] )*
                    loop148:
                    do {
                        int alt148=2;
                        int LA148_0 = input.LA(1);

                        if ( (LA148_0==COMMA) ) {
                            int LA148_1 = input.LA(2);

                            if ( (LA148_1==NAME||LA148_1==PRINT||(LA148_1>=LAMBDA && LA148_1<=NOT)||LA148_1==LPAREN||(LA148_1>=PLUS && LA148_1<=MINUS)||(LA148_1>=TILDE && LA148_1<=LBRACK)||LA148_1==LCURLY||(LA148_1>=BACKQUOTE && LA148_1<=STRING)) ) {
                                alt148=1;
                            }


                        }


                        switch (alt148) {
                    	case 1 :
                    	    // /usr/local/lib/jython/grammar/Python.g:2069:52: COMMA argument[arguments, kws, gens, false, false]
                    	    {
                    	    COMMA283=(Token)match(input,COMMA,FOLLOW_COMMA_in_arglist7651); if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) {
                    	    COMMA283_tree = (PythonTree)adaptor.create(COMMA283);
                    	    adaptor.addChild(root_0, COMMA283_tree);
                    	    }
                    	    pushFollow(FOLLOW_argument_in_arglist7653);
                    	    argument284=argument(arguments, kws, gens, false, false);

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) adaptor.addChild(root_0, argument284.getTree());

                    	    }
                    	    break;

                    	default :
                    	    break loop148;
                        }
                    } while (true);

                    // /usr/local/lib/jython/grammar/Python.g:2070:11: ( COMMA ( STAR s= test[expr_contextType.Load] ( COMMA argument[arguments, kws, gens, false, true] )* ( COMMA DOUBLESTAR k= test[expr_contextType.Load] )? | DOUBLESTAR k= test[expr_contextType.Load] )? )?
                    int alt152=2;
                    int LA152_0 = input.LA(1);

                    if ( (LA152_0==COMMA) ) {
                        alt152=1;
                    }
                    switch (alt152) {
                        case 1 :
                            // /usr/local/lib/jython/grammar/Python.g:2070:12: COMMA ( STAR s= test[expr_contextType.Load] ( COMMA argument[arguments, kws, gens, false, true] )* ( COMMA DOUBLESTAR k= test[expr_contextType.Load] )? | DOUBLESTAR k= test[expr_contextType.Load] )?
                            {
                            COMMA285=(Token)match(input,COMMA,FOLLOW_COMMA_in_arglist7669); if (state.failed) return retval;
                            if ( state.backtracking==0 ) {
                            COMMA285_tree = (PythonTree)adaptor.create(COMMA285);
                            adaptor.addChild(root_0, COMMA285_tree);
                            }
                            // /usr/local/lib/jython/grammar/Python.g:2071:15: ( STAR s= test[expr_contextType.Load] ( COMMA argument[arguments, kws, gens, false, true] )* ( COMMA DOUBLESTAR k= test[expr_contextType.Load] )? | DOUBLESTAR k= test[expr_contextType.Load] )?
                            int alt151=3;
                            int LA151_0 = input.LA(1);

                            if ( (LA151_0==STAR) ) {
                                alt151=1;
                            }
                            else if ( (LA151_0==DOUBLESTAR) ) {
                                alt151=2;
                            }
                            switch (alt151) {
                                case 1 :
                                    // /usr/local/lib/jython/grammar/Python.g:2071:17: STAR s= test[expr_contextType.Load] ( COMMA argument[arguments, kws, gens, false, true] )* ( COMMA DOUBLESTAR k= test[expr_contextType.Load] )?
                                    {
                                    STAR286=(Token)match(input,STAR,FOLLOW_STAR_in_arglist7687); if (state.failed) return retval;
                                    if ( state.backtracking==0 ) {
                                    STAR286_tree = (PythonTree)adaptor.create(STAR286);
                                    adaptor.addChild(root_0, STAR286_tree);
                                    }
                                    pushFollow(FOLLOW_test_in_arglist7691);
                                    s=test(expr_contextType.Load);

                                    state._fsp--;
                                    if (state.failed) return retval;
                                    if ( state.backtracking==0 ) adaptor.addChild(root_0, s.getTree());
                                    // /usr/local/lib/jython/grammar/Python.g:2071:52: ( COMMA argument[arguments, kws, gens, false, true] )*
                                    loop149:
                                    do {
                                        int alt149=2;
                                        int LA149_0 = input.LA(1);

                                        if ( (LA149_0==COMMA) ) {
                                            int LA149_1 = input.LA(2);

                                            if ( (LA149_1==NAME||LA149_1==PRINT||(LA149_1>=LAMBDA && LA149_1<=NOT)||LA149_1==LPAREN||(LA149_1>=PLUS && LA149_1<=MINUS)||(LA149_1>=TILDE && LA149_1<=LBRACK)||LA149_1==LCURLY||(LA149_1>=BACKQUOTE && LA149_1<=STRING)) ) {
                                                alt149=1;
                                            }


                                        }


                                        switch (alt149) {
                                    	case 1 :
                                    	    // /usr/local/lib/jython/grammar/Python.g:2071:53: COMMA argument[arguments, kws, gens, false, true]
                                    	    {
                                    	    COMMA287=(Token)match(input,COMMA,FOLLOW_COMMA_in_arglist7695); if (state.failed) return retval;
                                    	    if ( state.backtracking==0 ) {
                                    	    COMMA287_tree = (PythonTree)adaptor.create(COMMA287);
                                    	    adaptor.addChild(root_0, COMMA287_tree);
                                    	    }
                                    	    pushFollow(FOLLOW_argument_in_arglist7697);
                                    	    argument288=argument(arguments, kws, gens, false, true);

                                    	    state._fsp--;
                                    	    if (state.failed) return retval;
                                    	    if ( state.backtracking==0 ) adaptor.addChild(root_0, argument288.getTree());

                                    	    }
                                    	    break;

                                    	default :
                                    	    break loop149;
                                        }
                                    } while (true);

                                    // /usr/local/lib/jython/grammar/Python.g:2071:105: ( COMMA DOUBLESTAR k= test[expr_contextType.Load] )?
                                    int alt150=2;
                                    int LA150_0 = input.LA(1);

                                    if ( (LA150_0==COMMA) ) {
                                        alt150=1;
                                    }
                                    switch (alt150) {
                                        case 1 :
                                            // /usr/local/lib/jython/grammar/Python.g:2071:106: COMMA DOUBLESTAR k= test[expr_contextType.Load]
                                            {
                                            COMMA289=(Token)match(input,COMMA,FOLLOW_COMMA_in_arglist7703); if (state.failed) return retval;
                                            if ( state.backtracking==0 ) {
                                            COMMA289_tree = (PythonTree)adaptor.create(COMMA289);
                                            adaptor.addChild(root_0, COMMA289_tree);
                                            }
                                            DOUBLESTAR290=(Token)match(input,DOUBLESTAR,FOLLOW_DOUBLESTAR_in_arglist7705); if (state.failed) return retval;
                                            if ( state.backtracking==0 ) {
                                            DOUBLESTAR290_tree = (PythonTree)adaptor.create(DOUBLESTAR290);
                                            adaptor.addChild(root_0, DOUBLESTAR290_tree);
                                            }
                                            pushFollow(FOLLOW_test_in_arglist7709);
                                            k=test(expr_contextType.Load);

                                            state._fsp--;
                                            if (state.failed) return retval;
                                            if ( state.backtracking==0 ) adaptor.addChild(root_0, k.getTree());

                                            }
                                            break;

                                    }


                                    }
                                    break;
                                case 2 :
                                    // /usr/local/lib/jython/grammar/Python.g:2072:17: DOUBLESTAR k= test[expr_contextType.Load]
                                    {
                                    DOUBLESTAR291=(Token)match(input,DOUBLESTAR,FOLLOW_DOUBLESTAR_in_arglist7730); if (state.failed) return retval;
                                    if ( state.backtracking==0 ) {
                                    DOUBLESTAR291_tree = (PythonTree)adaptor.create(DOUBLESTAR291);
                                    adaptor.addChild(root_0, DOUBLESTAR291_tree);
                                    }
                                    pushFollow(FOLLOW_test_in_arglist7734);
                                    k=test(expr_contextType.Load);

                                    state._fsp--;
                                    if (state.failed) return retval;
                                    if ( state.backtracking==0 ) adaptor.addChild(root_0, k.getTree());

                                    }
                                    break;

                            }


                            }
                            break;

                    }

                    if ( state.backtracking==0 ) {

                                if (arguments.size() > 1 && gens.size() > 0) {
                                    actions.errorGenExpNotSoleArg(new PythonTree(((Token)retval.start)));
                                }
                                retval.args =arguments;
                                retval.keywords =kws;
                                retval.starargs =actions.castExpr((s!=null?((PythonTree)s.tree):null));
                                retval.kwargs =actions.castExpr((k!=null?((PythonTree)k.tree):null));
                            
                    }

                    }
                    break;
                case 2 :
                    // /usr/local/lib/jython/grammar/Python.g:2084:7: STAR s= test[expr_contextType.Load] ( COMMA argument[arguments, kws, gens, false, true] )* ( COMMA DOUBLESTAR k= test[expr_contextType.Load] )?
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    STAR292=(Token)match(input,STAR,FOLLOW_STAR_in_arglist7781); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    STAR292_tree = (PythonTree)adaptor.create(STAR292);
                    adaptor.addChild(root_0, STAR292_tree);
                    }
                    pushFollow(FOLLOW_test_in_arglist7785);
                    s=test(expr_contextType.Load);

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, s.getTree());
                    // /usr/local/lib/jython/grammar/Python.g:2084:42: ( COMMA argument[arguments, kws, gens, false, true] )*
                    loop153:
                    do {
                        int alt153=2;
                        int LA153_0 = input.LA(1);

                        if ( (LA153_0==COMMA) ) {
                            int LA153_1 = input.LA(2);

                            if ( (LA153_1==NAME||LA153_1==PRINT||(LA153_1>=LAMBDA && LA153_1<=NOT)||LA153_1==LPAREN||(LA153_1>=PLUS && LA153_1<=MINUS)||(LA153_1>=TILDE && LA153_1<=LBRACK)||LA153_1==LCURLY||(LA153_1>=BACKQUOTE && LA153_1<=STRING)) ) {
                                alt153=1;
                            }


                        }


                        switch (alt153) {
                    	case 1 :
                    	    // /usr/local/lib/jython/grammar/Python.g:2084:43: COMMA argument[arguments, kws, gens, false, true]
                    	    {
                    	    COMMA293=(Token)match(input,COMMA,FOLLOW_COMMA_in_arglist7789); if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) {
                    	    COMMA293_tree = (PythonTree)adaptor.create(COMMA293);
                    	    adaptor.addChild(root_0, COMMA293_tree);
                    	    }
                    	    pushFollow(FOLLOW_argument_in_arglist7791);
                    	    argument294=argument(arguments, kws, gens, false, true);

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) adaptor.addChild(root_0, argument294.getTree());

                    	    }
                    	    break;

                    	default :
                    	    break loop153;
                        }
                    } while (true);

                    // /usr/local/lib/jython/grammar/Python.g:2084:95: ( COMMA DOUBLESTAR k= test[expr_contextType.Load] )?
                    int alt154=2;
                    int LA154_0 = input.LA(1);

                    if ( (LA154_0==COMMA) ) {
                        alt154=1;
                    }
                    switch (alt154) {
                        case 1 :
                            // /usr/local/lib/jython/grammar/Python.g:2084:96: COMMA DOUBLESTAR k= test[expr_contextType.Load]
                            {
                            COMMA295=(Token)match(input,COMMA,FOLLOW_COMMA_in_arglist7797); if (state.failed) return retval;
                            if ( state.backtracking==0 ) {
                            COMMA295_tree = (PythonTree)adaptor.create(COMMA295);
                            adaptor.addChild(root_0, COMMA295_tree);
                            }
                            DOUBLESTAR296=(Token)match(input,DOUBLESTAR,FOLLOW_DOUBLESTAR_in_arglist7799); if (state.failed) return retval;
                            if ( state.backtracking==0 ) {
                            DOUBLESTAR296_tree = (PythonTree)adaptor.create(DOUBLESTAR296);
                            adaptor.addChild(root_0, DOUBLESTAR296_tree);
                            }
                            pushFollow(FOLLOW_test_in_arglist7803);
                            k=test(expr_contextType.Load);

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) adaptor.addChild(root_0, k.getTree());

                            }
                            break;

                    }

                    if ( state.backtracking==0 ) {

                                retval.starargs =actions.castExpr((s!=null?((PythonTree)s.tree):null));
                                retval.keywords =kws;
                                retval.kwargs =actions.castExpr((k!=null?((PythonTree)k.tree):null));
                            
                    }

                    }
                    break;
                case 3 :
                    // /usr/local/lib/jython/grammar/Python.g:2090:7: DOUBLESTAR k= test[expr_contextType.Load]
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    DOUBLESTAR297=(Token)match(input,DOUBLESTAR,FOLLOW_DOUBLESTAR_in_arglist7822); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    DOUBLESTAR297_tree = (PythonTree)adaptor.create(DOUBLESTAR297);
                    adaptor.addChild(root_0, DOUBLESTAR297_tree);
                    }
                    pushFollow(FOLLOW_test_in_arglist7826);
                    k=test(expr_contextType.Load);

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, k.getTree());
                    if ( state.backtracking==0 ) {

                                retval.kwargs =actions.castExpr((k!=null?((PythonTree)k.tree):null));
                            
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        catch (RecognitionException re) {
            reportError(re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "arglist"

    public static class argument_return extends ParserRuleReturnScope {
        public boolean genarg;
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "argument"
    // /usr/local/lib/jython/grammar/Python.g:2097:1: argument[List arguments, List kws, List gens, boolean first, boolean afterStar] returns [boolean genarg] : t1= test[expr_contextType.Load] ( ( ASSIGN t2= test[expr_contextType.Load] ) | comp_for[$gens] | ) ;
    public final PythonParser.argument_return argument(List arguments, List kws, List gens, boolean first, boolean afterStar) throws RecognitionException {
        PythonParser.argument_return retval = new PythonParser.argument_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token ASSIGN298=null;
        PythonParser.test_return t1 = null;

        PythonParser.test_return t2 = null;

        PythonParser.comp_for_return comp_for299 = null;


        PythonTree ASSIGN298_tree=null;

        try {
            // /usr/local/lib/jython/grammar/Python.g:2099:5: (t1= test[expr_contextType.Load] ( ( ASSIGN t2= test[expr_contextType.Load] ) | comp_for[$gens] | ) )
            // /usr/local/lib/jython/grammar/Python.g:2099:7: t1= test[expr_contextType.Load] ( ( ASSIGN t2= test[expr_contextType.Load] ) | comp_for[$gens] | )
            {
            root_0 = (PythonTree)adaptor.nil();

            pushFollow(FOLLOW_test_in_argument7865);
            t1=test(expr_contextType.Load);

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, t1.getTree());
            // /usr/local/lib/jython/grammar/Python.g:2100:9: ( ( ASSIGN t2= test[expr_contextType.Load] ) | comp_for[$gens] | )
            int alt156=3;
            switch ( input.LA(1) ) {
            case ASSIGN:
                {
                alt156=1;
                }
                break;
            case FOR:
                {
                alt156=2;
                }
                break;
            case RPAREN:
            case COMMA:
                {
                alt156=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 156, 0, input);

                throw nvae;
            }

            switch (alt156) {
                case 1 :
                    // /usr/local/lib/jython/grammar/Python.g:2100:10: ( ASSIGN t2= test[expr_contextType.Load] )
                    {
                    // /usr/local/lib/jython/grammar/Python.g:2100:10: ( ASSIGN t2= test[expr_contextType.Load] )
                    // /usr/local/lib/jython/grammar/Python.g:2100:11: ASSIGN t2= test[expr_contextType.Load]
                    {
                    ASSIGN298=(Token)match(input,ASSIGN,FOLLOW_ASSIGN_in_argument7878); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    ASSIGN298_tree = (PythonTree)adaptor.create(ASSIGN298);
                    adaptor.addChild(root_0, ASSIGN298_tree);
                    }
                    pushFollow(FOLLOW_test_in_argument7882);
                    t2=test(expr_contextType.Load);

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, t2.getTree());

                    }

                    if ( state.backtracking==0 ) {

                                    expr newkey = actions.castExpr((t1!=null?((PythonTree)t1.tree):null));
                                    //Loop through all current keys and fail on duplicate.
                                    for(Object o: kws) {
                                        List list = (List)o;
                                        Object oldkey = list.get(0);
                                        if (oldkey instanceof Name && newkey instanceof Name) {
                                            if (((Name)oldkey).getId().equals(((Name)newkey).getId())) {
                                                errorHandler.error("keyword arguments repeated", (t1!=null?((PythonTree)t1.tree):null));
                                            }
                                        }
                                    }
                                    List<expr> exprs = new ArrayList<expr>();
                                    exprs.add(newkey);
                                    exprs.add(actions.castExpr((t2!=null?((PythonTree)t2.tree):null)));
                                    kws.add(exprs);
                                
                    }

                    }
                    break;
                case 2 :
                    // /usr/local/lib/jython/grammar/Python.g:2118:11: comp_for[$gens]
                    {
                    pushFollow(FOLLOW_comp_for_in_argument7908);
                    comp_for299=comp_for(gens);

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, comp_for299.getTree());
                    if ( state.backtracking==0 ) {

                                    if (!first) {
                                        actions.errorGenExpNotSoleArg((comp_for299!=null?((PythonTree)comp_for299.tree):null));
                                    }
                                    retval.genarg = true;
                                    Collections.reverse(gens);
                                    List<comprehension> c = gens;
                                    arguments.add(new GeneratorExp((t1!=null?((Token)t1.start):null), actions.castExpr((t1!=null?((PythonTree)t1.tree):null)), c));
                                
                    }

                    }
                    break;
                case 3 :
                    // /usr/local/lib/jython/grammar/Python.g:2129:11: 
                    {
                    if ( state.backtracking==0 ) {

                                    if (kws.size() > 0) {
                                        errorHandler.error("non-keyword arg after keyword arg", (t1!=null?((PythonTree)t1.tree):null));
                                    } else if (afterStar) {
                                        errorHandler.error("only named arguments may follow *expression", (t1!=null?((PythonTree)t1.tree):null));
                                    }
                                    arguments.add((t1!=null?((PythonTree)t1.tree):null));
                                
                    }

                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        catch (RecognitionException re) {
            reportError(re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "argument"

    public static class list_iter_return extends ParserRuleReturnScope {
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "list_iter"
    // /usr/local/lib/jython/grammar/Python.g:2141:1: list_iter[List gens, List ifs] : ( list_for[gens] | list_if[gens, ifs] );
    public final PythonParser.list_iter_return list_iter(List gens, List ifs) throws RecognitionException {
        PythonParser.list_iter_return retval = new PythonParser.list_iter_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        PythonParser.list_for_return list_for300 = null;

        PythonParser.list_if_return list_if301 = null;



        try {
            // /usr/local/lib/jython/grammar/Python.g:2142:5: ( list_for[gens] | list_if[gens, ifs] )
            int alt157=2;
            int LA157_0 = input.LA(1);

            if ( (LA157_0==FOR) ) {
                alt157=1;
            }
            else if ( (LA157_0==IF) ) {
                alt157=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 157, 0, input);

                throw nvae;
            }
            switch (alt157) {
                case 1 :
                    // /usr/local/lib/jython/grammar/Python.g:2142:7: list_for[gens]
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    pushFollow(FOLLOW_list_for_in_list_iter7973);
                    list_for300=list_for(gens);

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, list_for300.getTree());

                    }
                    break;
                case 2 :
                    // /usr/local/lib/jython/grammar/Python.g:2143:7: list_if[gens, ifs]
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    pushFollow(FOLLOW_list_if_in_list_iter7982);
                    list_if301=list_if(gens, ifs);

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, list_if301.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        catch (RecognitionException re) {
            reportError(re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "list_iter"

    public static class list_for_return extends ParserRuleReturnScope {
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "list_for"
    // /usr/local/lib/jython/grammar/Python.g:2147:1: list_for[List gens] : FOR exprlist[expr_contextType.Store] IN testlist[expr_contextType.Load] ( list_iter[gens, ifs] )? ;
    public final PythonParser.list_for_return list_for(List gens) throws RecognitionException {
        PythonParser.list_for_return retval = new PythonParser.list_for_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token FOR302=null;
        Token IN304=null;
        PythonParser.exprlist_return exprlist303 = null;

        PythonParser.testlist_return testlist305 = null;

        PythonParser.list_iter_return list_iter306 = null;


        PythonTree FOR302_tree=null;
        PythonTree IN304_tree=null;


            List ifs = new ArrayList();

        try {
            // /usr/local/lib/jython/grammar/Python.g:2151:5: ( FOR exprlist[expr_contextType.Store] IN testlist[expr_contextType.Load] ( list_iter[gens, ifs] )? )
            // /usr/local/lib/jython/grammar/Python.g:2151:7: FOR exprlist[expr_contextType.Store] IN testlist[expr_contextType.Load] ( list_iter[gens, ifs] )?
            {
            root_0 = (PythonTree)adaptor.nil();

            FOR302=(Token)match(input,FOR,FOLLOW_FOR_in_list_for8008); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            FOR302_tree = (PythonTree)adaptor.create(FOR302);
            adaptor.addChild(root_0, FOR302_tree);
            }
            pushFollow(FOLLOW_exprlist_in_list_for8010);
            exprlist303=exprlist(expr_contextType.Store);

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, exprlist303.getTree());
            IN304=(Token)match(input,IN,FOLLOW_IN_in_list_for8013); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            IN304_tree = (PythonTree)adaptor.create(IN304);
            adaptor.addChild(root_0, IN304_tree);
            }
            pushFollow(FOLLOW_testlist_in_list_for8015);
            testlist305=testlist(expr_contextType.Load);

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, testlist305.getTree());
            // /usr/local/lib/jython/grammar/Python.g:2151:79: ( list_iter[gens, ifs] )?
            int alt158=2;
            int LA158_0 = input.LA(1);

            if ( (LA158_0==FOR||LA158_0==IF) ) {
                alt158=1;
            }
            switch (alt158) {
                case 1 :
                    // /usr/local/lib/jython/grammar/Python.g:2151:80: list_iter[gens, ifs]
                    {
                    pushFollow(FOLLOW_list_iter_in_list_for8019);
                    list_iter306=list_iter(gens, ifs);

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, list_iter306.getTree());

                    }
                    break;

            }

            if ( state.backtracking==0 ) {

                        Collections.reverse(ifs);
                        gens.add(new comprehension(FOR302, (exprlist303!=null?exprlist303.etype:null), actions.castExpr((testlist305!=null?((PythonTree)testlist305.tree):null)), ifs));
                    
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        catch (RecognitionException re) {
            reportError(re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "list_for"

    public static class list_if_return extends ParserRuleReturnScope {
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "list_if"
    // /usr/local/lib/jython/grammar/Python.g:2159:1: list_if[List gens, List ifs] : IF test[expr_contextType.Load] ( list_iter[gens, ifs] )? ;
    public final PythonParser.list_if_return list_if(List gens, List ifs) throws RecognitionException {
        PythonParser.list_if_return retval = new PythonParser.list_if_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token IF307=null;
        PythonParser.test_return test308 = null;

        PythonParser.list_iter_return list_iter309 = null;


        PythonTree IF307_tree=null;

        try {
            // /usr/local/lib/jython/grammar/Python.g:2160:5: ( IF test[expr_contextType.Load] ( list_iter[gens, ifs] )? )
            // /usr/local/lib/jython/grammar/Python.g:2160:7: IF test[expr_contextType.Load] ( list_iter[gens, ifs] )?
            {
            root_0 = (PythonTree)adaptor.nil();

            IF307=(Token)match(input,IF,FOLLOW_IF_in_list_if8049); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            IF307_tree = (PythonTree)adaptor.create(IF307);
            adaptor.addChild(root_0, IF307_tree);
            }
            pushFollow(FOLLOW_test_in_list_if8051);
            test308=test(expr_contextType.Load);

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, test308.getTree());
            // /usr/local/lib/jython/grammar/Python.g:2160:38: ( list_iter[gens, ifs] )?
            int alt159=2;
            int LA159_0 = input.LA(1);

            if ( (LA159_0==FOR||LA159_0==IF) ) {
                alt159=1;
            }
            switch (alt159) {
                case 1 :
                    // /usr/local/lib/jython/grammar/Python.g:2160:39: list_iter[gens, ifs]
                    {
                    pushFollow(FOLLOW_list_iter_in_list_if8055);
                    list_iter309=list_iter(gens, ifs);

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, list_iter309.getTree());

                    }
                    break;

            }

            if ( state.backtracking==0 ) {

                      ifs.add(actions.castExpr((test308!=null?((PythonTree)test308.tree):null)));
                    
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        catch (RecognitionException re) {
            reportError(re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "list_if"

    public static class comp_iter_return extends ParserRuleReturnScope {
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "comp_iter"
    // /usr/local/lib/jython/grammar/Python.g:2167:1: comp_iter[List gens, List ifs] : ( comp_for[gens] | comp_if[gens, ifs] );
    public final PythonParser.comp_iter_return comp_iter(List gens, List ifs) throws RecognitionException {
        PythonParser.comp_iter_return retval = new PythonParser.comp_iter_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        PythonParser.comp_for_return comp_for310 = null;

        PythonParser.comp_if_return comp_if311 = null;



        try {
            // /usr/local/lib/jython/grammar/Python.g:2168:5: ( comp_for[gens] | comp_if[gens, ifs] )
            int alt160=2;
            int LA160_0 = input.LA(1);

            if ( (LA160_0==FOR) ) {
                alt160=1;
            }
            else if ( (LA160_0==IF) ) {
                alt160=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 160, 0, input);

                throw nvae;
            }
            switch (alt160) {
                case 1 :
                    // /usr/local/lib/jython/grammar/Python.g:2168:7: comp_for[gens]
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    pushFollow(FOLLOW_comp_for_in_comp_iter8086);
                    comp_for310=comp_for(gens);

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, comp_for310.getTree());

                    }
                    break;
                case 2 :
                    // /usr/local/lib/jython/grammar/Python.g:2169:7: comp_if[gens, ifs]
                    {
                    root_0 = (PythonTree)adaptor.nil();

                    pushFollow(FOLLOW_comp_if_in_comp_iter8095);
                    comp_if311=comp_if(gens, ifs);

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, comp_if311.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        catch (RecognitionException re) {
            reportError(re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "comp_iter"

    public static class comp_for_return extends ParserRuleReturnScope {
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "comp_for"
    // /usr/local/lib/jython/grammar/Python.g:2173:1: comp_for[List gens] : FOR exprlist[expr_contextType.Store] IN or_test[expr_contextType.Load] ( comp_iter[gens, ifs] )? ;
    public final PythonParser.comp_for_return comp_for(List gens) throws RecognitionException {
        PythonParser.comp_for_return retval = new PythonParser.comp_for_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token FOR312=null;
        Token IN314=null;
        PythonParser.exprlist_return exprlist313 = null;

        PythonParser.or_test_return or_test315 = null;

        PythonParser.comp_iter_return comp_iter316 = null;


        PythonTree FOR312_tree=null;
        PythonTree IN314_tree=null;


            List ifs = new ArrayList();

        try {
            // /usr/local/lib/jython/grammar/Python.g:2177:5: ( FOR exprlist[expr_contextType.Store] IN or_test[expr_contextType.Load] ( comp_iter[gens, ifs] )? )
            // /usr/local/lib/jython/grammar/Python.g:2177:7: FOR exprlist[expr_contextType.Store] IN or_test[expr_contextType.Load] ( comp_iter[gens, ifs] )?
            {
            root_0 = (PythonTree)adaptor.nil();

            FOR312=(Token)match(input,FOR,FOLLOW_FOR_in_comp_for8121); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            FOR312_tree = (PythonTree)adaptor.create(FOR312);
            adaptor.addChild(root_0, FOR312_tree);
            }
            pushFollow(FOLLOW_exprlist_in_comp_for8123);
            exprlist313=exprlist(expr_contextType.Store);

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, exprlist313.getTree());
            IN314=(Token)match(input,IN,FOLLOW_IN_in_comp_for8126); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            IN314_tree = (PythonTree)adaptor.create(IN314);
            adaptor.addChild(root_0, IN314_tree);
            }
            pushFollow(FOLLOW_or_test_in_comp_for8128);
            or_test315=or_test(expr_contextType.Load);

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, or_test315.getTree());
            // /usr/local/lib/jython/grammar/Python.g:2177:78: ( comp_iter[gens, ifs] )?
            int alt161=2;
            int LA161_0 = input.LA(1);

            if ( (LA161_0==FOR||LA161_0==IF) ) {
                alt161=1;
            }
            switch (alt161) {
                case 1 :
                    // /usr/local/lib/jython/grammar/Python.g:2177:78: comp_iter[gens, ifs]
                    {
                    pushFollow(FOLLOW_comp_iter_in_comp_for8131);
                    comp_iter316=comp_iter(gens, ifs);

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, comp_iter316.getTree());

                    }
                    break;

            }

            if ( state.backtracking==0 ) {

                        Collections.reverse(ifs);
                        gens.add(new comprehension(FOR312, (exprlist313!=null?exprlist313.etype:null), actions.castExpr((or_test315!=null?((PythonTree)or_test315.tree):null)), ifs));
                    
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        catch (RecognitionException re) {
            reportError(re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "comp_for"

    public static class comp_if_return extends ParserRuleReturnScope {
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "comp_if"
    // /usr/local/lib/jython/grammar/Python.g:2185:1: comp_if[List gens, List ifs] : IF test[expr_contextType.Load] ( comp_iter[gens, ifs] )? ;
    public final PythonParser.comp_if_return comp_if(List gens, List ifs) throws RecognitionException {
        PythonParser.comp_if_return retval = new PythonParser.comp_if_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token IF317=null;
        PythonParser.test_return test318 = null;

        PythonParser.comp_iter_return comp_iter319 = null;


        PythonTree IF317_tree=null;

        try {
            // /usr/local/lib/jython/grammar/Python.g:2186:5: ( IF test[expr_contextType.Load] ( comp_iter[gens, ifs] )? )
            // /usr/local/lib/jython/grammar/Python.g:2186:7: IF test[expr_contextType.Load] ( comp_iter[gens, ifs] )?
            {
            root_0 = (PythonTree)adaptor.nil();

            IF317=(Token)match(input,IF,FOLLOW_IF_in_comp_if8160); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            IF317_tree = (PythonTree)adaptor.create(IF317);
            adaptor.addChild(root_0, IF317_tree);
            }
            pushFollow(FOLLOW_test_in_comp_if8162);
            test318=test(expr_contextType.Load);

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, test318.getTree());
            // /usr/local/lib/jython/grammar/Python.g:2186:38: ( comp_iter[gens, ifs] )?
            int alt162=2;
            int LA162_0 = input.LA(1);

            if ( (LA162_0==FOR||LA162_0==IF) ) {
                alt162=1;
            }
            switch (alt162) {
                case 1 :
                    // /usr/local/lib/jython/grammar/Python.g:2186:38: comp_iter[gens, ifs]
                    {
                    pushFollow(FOLLOW_comp_iter_in_comp_if8165);
                    comp_iter319=comp_iter(gens, ifs);

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, comp_iter319.getTree());

                    }
                    break;

            }

            if ( state.backtracking==0 ) {

                      ifs.add(actions.castExpr((test318!=null?((PythonTree)test318.tree):null)));
                    
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        catch (RecognitionException re) {
            reportError(re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "comp_if"

    public static class yield_expr_return extends ParserRuleReturnScope {
        public expr etype;
        PythonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "yield_expr"
    // /usr/local/lib/jython/grammar/Python.g:2193:1: yield_expr returns [expr etype] : YIELD ( testlist[expr_contextType.Load] )? ;
    public final PythonParser.yield_expr_return yield_expr() throws RecognitionException {
        PythonParser.yield_expr_return retval = new PythonParser.yield_expr_return();
        retval.start = input.LT(1);

        PythonTree root_0 = null;

        Token YIELD320=null;
        PythonParser.testlist_return testlist321 = null;


        PythonTree YIELD320_tree=null;

        try {
            // /usr/local/lib/jython/grammar/Python.g:2199:5: ( YIELD ( testlist[expr_contextType.Load] )? )
            // /usr/local/lib/jython/grammar/Python.g:2199:7: YIELD ( testlist[expr_contextType.Load] )?
            {
            root_0 = (PythonTree)adaptor.nil();

            YIELD320=(Token)match(input,YIELD,FOLLOW_YIELD_in_yield_expr8206); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            YIELD320_tree = (PythonTree)adaptor.create(YIELD320);
            adaptor.addChild(root_0, YIELD320_tree);
            }
            // /usr/local/lib/jython/grammar/Python.g:2199:13: ( testlist[expr_contextType.Load] )?
            int alt163=2;
            int LA163_0 = input.LA(1);

            if ( (LA163_0==NAME||LA163_0==NOT||LA163_0==LPAREN||(LA163_0>=PLUS && LA163_0<=MINUS)||(LA163_0>=TILDE && LA163_0<=LBRACK)||LA163_0==LCURLY||LA163_0==BACKQUOTE) ) {
                alt163=1;
            }
            else if ( (LA163_0==PRINT) && ((printFunction))) {
                alt163=1;
            }
            else if ( (LA163_0==LAMBDA||(LA163_0>=INT && LA163_0<=STRING)) ) {
                alt163=1;
            }
            switch (alt163) {
                case 1 :
                    // /usr/local/lib/jython/grammar/Python.g:2199:13: testlist[expr_contextType.Load]
                    {
                    pushFollow(FOLLOW_testlist_in_yield_expr8208);
                    testlist321=testlist(expr_contextType.Load);

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, testlist321.getTree());

                    }
                    break;

            }

            if ( state.backtracking==0 ) {

                        retval.etype = new Yield(YIELD320, actions.castExpr((testlist321!=null?((PythonTree)testlist321.tree):null)));
                    
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {

                  //needed for y2+=yield_expr
                  retval.tree = retval.etype;

            }
        }

        catch (RecognitionException re) {
            reportError(re);
            errorHandler.recover(this, input,re);
            retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "yield_expr"

    // $ANTLR start synpred1_Python
    public final void synpred1_Python_fragment() throws RecognitionException {   
        // /usr/local/lib/jython/grammar/Python.g:579:7: ( LPAREN fpdef[null] COMMA )
        // /usr/local/lib/jython/grammar/Python.g:579:8: LPAREN fpdef[null] COMMA
        {
        match(input,LPAREN,FOLLOW_LPAREN_in_synpred1_Python1296); if (state.failed) return ;
        pushFollow(FOLLOW_fpdef_in_synpred1_Python1298);
        fpdef(null);

        state._fsp--;
        if (state.failed) return ;
        match(input,COMMA,FOLLOW_COMMA_in_synpred1_Python1301); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred1_Python

    // $ANTLR start synpred2_Python
    public final void synpred2_Python_fragment() throws RecognitionException {   
        // /usr/local/lib/jython/grammar/Python.g:643:8: ( testlist[null] augassign )
        // /usr/local/lib/jython/grammar/Python.g:643:9: testlist[null] augassign
        {
        pushFollow(FOLLOW_testlist_in_synpred2_Python1683);
        testlist(null);

        state._fsp--;
        if (state.failed) return ;
        pushFollow(FOLLOW_augassign_in_synpred2_Python1686);
        augassign();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred2_Python

    // $ANTLR start synpred3_Python
    public final void synpred3_Python_fragment() throws RecognitionException {   
        // /usr/local/lib/jython/grammar/Python.g:657:7: ( testlist[null] ASSIGN )
        // /usr/local/lib/jython/grammar/Python.g:657:8: testlist[null] ASSIGN
        {
        pushFollow(FOLLOW_testlist_in_synpred3_Python1802);
        testlist(null);

        state._fsp--;
        if (state.failed) return ;
        match(input,ASSIGN,FOLLOW_ASSIGN_in_synpred3_Python1805); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred3_Python

    // $ANTLR start synpred4_Python
    public final void synpred4_Python_fragment() throws RecognitionException {   
        // /usr/local/lib/jython/grammar/Python.g:762:7: ( test[null] COMMA )
        // /usr/local/lib/jython/grammar/Python.g:762:8: test[null] COMMA
        {
        pushFollow(FOLLOW_test_in_synpred4_Python2317);
        test(null);

        state._fsp--;
        if (state.failed) return ;
        match(input,COMMA,FOLLOW_COMMA_in_synpred4_Python2320); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred4_Python

    // $ANTLR start synpred5_Python
    public final void synpred5_Python_fragment() throws RecognitionException {   
        // /usr/local/lib/jython/grammar/Python.g:783:7: ( test[null] COMMA test[null] )
        // /usr/local/lib/jython/grammar/Python.g:783:8: test[null] COMMA test[null]
        {
        pushFollow(FOLLOW_test_in_synpred5_Python2416);
        test(null);

        state._fsp--;
        if (state.failed) return ;
        match(input,COMMA,FOLLOW_COMMA_in_synpred5_Python2419); if (state.failed) return ;
        pushFollow(FOLLOW_test_in_synpred5_Python2421);
        test(null);

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred5_Python

    // $ANTLR start synpred6_Python
    public final void synpred6_Python_fragment() throws RecognitionException {   
        // /usr/local/lib/jython/grammar/Python.g:1096:7: ( ( decorators )? DEF )
        // /usr/local/lib/jython/grammar/Python.g:1096:8: ( decorators )? DEF
        {
        // /usr/local/lib/jython/grammar/Python.g:1096:8: ( decorators )?
        int alt164=2;
        int LA164_0 = input.LA(1);

        if ( (LA164_0==AT) ) {
            alt164=1;
        }
        switch (alt164) {
            case 1 :
                // /usr/local/lib/jython/grammar/Python.g:1096:8: decorators
                {
                pushFollow(FOLLOW_decorators_in_synpred6_Python3510);
                decorators();

                state._fsp--;
                if (state.failed) return ;

                }
                break;

        }

        match(input,DEF,FOLLOW_DEF_in_synpred6_Python3513); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred6_Python

    // $ANTLR start synpred7_Python
    public final void synpred7_Python_fragment() throws RecognitionException {   
        // /usr/local/lib/jython/grammar/Python.g:1292:9: ( IF or_test[null] ORELSE )
        // /usr/local/lib/jython/grammar/Python.g:1292:10: IF or_test[null] ORELSE
        {
        match(input,IF,FOLLOW_IF_in_synpred7_Python4270); if (state.failed) return ;
        pushFollow(FOLLOW_or_test_in_synpred7_Python4272);
        or_test(null);

        state._fsp--;
        if (state.failed) return ;
        match(input,ORELSE,FOLLOW_ORELSE_in_synpred7_Python4275); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred7_Python

    // $ANTLR start synpred8_Python
    public final void synpred8_Python_fragment() throws RecognitionException {   
        // /usr/local/lib/jython/grammar/Python.g:1916:7: ( test[null] COLON )
        // /usr/local/lib/jython/grammar/Python.g:1916:8: test[null] COLON
        {
        pushFollow(FOLLOW_test_in_synpred8_Python6864);
        test(null);

        state._fsp--;
        if (state.failed) return ;
        match(input,COLON,FOLLOW_COLON_in_synpred8_Python6867); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred8_Python

    // $ANTLR start synpred9_Python
    public final void synpred9_Python_fragment() throws RecognitionException {   
        // /usr/local/lib/jython/grammar/Python.g:1921:7: ( COLON )
        // /usr/local/lib/jython/grammar/Python.g:1921:8: COLON
        {
        match(input,COLON,FOLLOW_COLON_in_synpred9_Python6915); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred9_Python

    // $ANTLR start synpred10_Python
    public final void synpred10_Python_fragment() throws RecognitionException {   
        // /usr/local/lib/jython/grammar/Python.g:1955:7: ( expr[null] COMMA )
        // /usr/local/lib/jython/grammar/Python.g:1955:8: expr[null] COMMA
        {
        pushFollow(FOLLOW_expr_in_synpred10_Python7060);
        expr(null);

        state._fsp--;
        if (state.failed) return ;
        match(input,COMMA,FOLLOW_COMMA_in_synpred10_Python7063); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred10_Python

    // $ANTLR start synpred11_Python
    public final void synpred11_Python_fragment() throws RecognitionException {   
        // /usr/local/lib/jython/grammar/Python.g:1985:7: ( test[null] COMMA )
        // /usr/local/lib/jython/grammar/Python.g:1985:8: test[null] COMMA
        {
        pushFollow(FOLLOW_test_in_synpred11_Python7211);
        test(null);

        state._fsp--;
        if (state.failed) return ;
        match(input,COMMA,FOLLOW_COMMA_in_synpred11_Python7214); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred11_Python

    // Delegated rules

    public final boolean synpred5_Python() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred5_Python_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred7_Python() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred7_Python_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred11_Python() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred11_Python_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred4_Python() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred4_Python_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred3_Python() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred3_Python_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred1_Python() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred1_Python_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred10_Python() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred10_Python_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred9_Python() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred9_Python_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred6_Python() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred6_Python_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred2_Python() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred2_Python_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred8_Python() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred8_Python_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }


    protected DFA30 dfa30 = new DFA30(this);
    protected DFA35 dfa35 = new DFA35(this);
    protected DFA31 dfa31 = new DFA31(this);
    protected DFA40 dfa40 = new DFA40(this);
    protected DFA38 dfa38 = new DFA38(this);
    protected DFA43 dfa43 = new DFA43(this);
    protected DFA41 dfa41 = new DFA41(this);
    protected DFA52 dfa52 = new DFA52(this);
    protected DFA80 dfa80 = new DFA80(this);
    protected DFA89 dfa89 = new DFA89(this);
    protected DFA112 dfa112 = new DFA112(this);
    protected DFA116 dfa116 = new DFA116(this);
    protected DFA129 dfa129 = new DFA129(this);
    protected DFA133 dfa133 = new DFA133(this);
    protected DFA131 dfa131 = new DFA131(this);
    protected DFA134 dfa134 = new DFA134(this);
    protected DFA138 dfa138 = new DFA138(this);
    protected DFA136 dfa136 = new DFA136(this);
    protected DFA139 dfa139 = new DFA139(this);
    static final String DFA30_eotS =
        "\13\uffff";
    static final String DFA30_eofS =
        "\13\uffff";
    static final String DFA30_minS =
        "\1\11\1\uffff\1\0\10\uffff";
    static final String DFA30_maxS =
        "\1\132\1\uffff\1\0\10\uffff";
    static final String DFA30_acceptS =
        "\1\uffff\1\1\1\uffff\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11";
    static final String DFA30_specialS =
        "\1\0\1\uffff\1\1\10\uffff}>";
    static final String[] DFA30_transitionS = {
            "\1\1\1\uffff\1\2\2\uffff\1\11\1\5\1\uffff\1\5\1\uffff\1\3\2"+
            "\uffff\1\10\1\uffff\1\6\1\uffff\1\7\1\uffff\1\6\2\uffff\2\1"+
            "\2\uffff\1\4\2\5\3\uffff\1\5\1\uffff\1\1\37\uffff\2\1\3\uffff"+
            "\2\1\1\uffff\1\1\1\uffff\6\1",
            "",
            "\1\uffff",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA30_eot = DFA.unpackEncodedString(DFA30_eotS);
    static final short[] DFA30_eof = DFA.unpackEncodedString(DFA30_eofS);
    static final char[] DFA30_min = DFA.unpackEncodedStringToUnsignedChars(DFA30_minS);
    static final char[] DFA30_max = DFA.unpackEncodedStringToUnsignedChars(DFA30_maxS);
    static final short[] DFA30_accept = DFA.unpackEncodedString(DFA30_acceptS);
    static final short[] DFA30_special = DFA.unpackEncodedString(DFA30_specialS);
    static final short[][] DFA30_transition;

    static {
        int numStates = DFA30_transitionS.length;
        DFA30_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA30_transition[i] = DFA.unpackEncodedString(DFA30_transitionS[i]);
        }
    }

    class DFA30 extends DFA {

        public DFA30(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 30;
            this.eot = DFA30_eot;
            this.eof = DFA30_eof;
            this.min = DFA30_min;
            this.max = DFA30_max;
            this.accept = DFA30_accept;
            this.special = DFA30_special;
            this.transition = DFA30_transition;
        }
        public String getDescription() {
            return "621:1: small_stmt : ( expr_stmt | del_stmt | pass_stmt | flow_stmt | import_stmt | global_stmt | exec_stmt | assert_stmt | {...}? => print_stmt );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA30_0 = input.LA(1);

                         
                        int index30_0 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA30_0==NAME||(LA30_0>=LAMBDA && LA30_0<=NOT)||LA30_0==LPAREN||(LA30_0>=PLUS && LA30_0<=MINUS)||(LA30_0>=TILDE && LA30_0<=LBRACK)||LA30_0==LCURLY||(LA30_0>=BACKQUOTE && LA30_0<=STRING)) ) {s = 1;}

                        else if ( (LA30_0==PRINT) && (((!printFunction)||(printFunction)))) {s = 2;}

                        else if ( (LA30_0==DELETE) ) {s = 3;}

                        else if ( (LA30_0==PASS) ) {s = 4;}

                        else if ( (LA30_0==BREAK||LA30_0==CONTINUE||(LA30_0>=RAISE && LA30_0<=RETURN)||LA30_0==YIELD) ) {s = 5;}

                        else if ( (LA30_0==FROM||LA30_0==IMPORT) ) {s = 6;}

                        else if ( (LA30_0==GLOBAL) ) {s = 7;}

                        else if ( (LA30_0==EXEC) ) {s = 8;}

                        else if ( (LA30_0==ASSERT) ) {s = 9;}

                         
                        input.seek(index30_0);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA30_2 = input.LA(1);

                         
                        int index30_2 = input.index();
                        input.rewind();
                        s = -1;
                        if ( ((printFunction)) ) {s = 1;}

                        else if ( ((!printFunction)) ) {s = 10;}

                         
                        input.seek(index30_2);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 30, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA35_eotS =
        "\24\uffff";
    static final String DFA35_eofS =
        "\24\uffff";
    static final String DFA35_minS =
        "\1\11\20\0\3\uffff";
    static final String DFA35_maxS =
        "\1\132\20\0\3\uffff";
    static final String DFA35_acceptS =
        "\21\uffff\1\1\1\2\1\3";
    static final String DFA35_specialS =
        "\1\0\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\13\1\14\1\15\1"+
        "\16\1\17\1\20\3\uffff}>";
    static final String[] DFA35_transitionS = {
            "\1\11\1\uffff\1\12\23\uffff\1\20\1\1\12\uffff\1\5\37\uffff\1"+
            "\2\1\3\3\uffff\1\4\1\6\1\uffff\1\7\1\uffff\1\10\1\13\1\14\1"+
            "\15\1\16\1\17",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "",
            "",
            ""
    };

    static final short[] DFA35_eot = DFA.unpackEncodedString(DFA35_eotS);
    static final short[] DFA35_eof = DFA.unpackEncodedString(DFA35_eofS);
    static final char[] DFA35_min = DFA.unpackEncodedStringToUnsignedChars(DFA35_minS);
    static final char[] DFA35_max = DFA.unpackEncodedStringToUnsignedChars(DFA35_maxS);
    static final short[] DFA35_accept = DFA.unpackEncodedString(DFA35_acceptS);
    static final short[] DFA35_special = DFA.unpackEncodedString(DFA35_specialS);
    static final short[][] DFA35_transition;

    static {
        int numStates = DFA35_transitionS.length;
        DFA35_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA35_transition[i] = DFA.unpackEncodedString(DFA35_transitionS[i]);
        }
    }

    class DFA35 extends DFA {

        public DFA35(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 35;
            this.eot = DFA35_eot;
            this.eof = DFA35_eof;
            this.min = DFA35_min;
            this.max = DFA35_max;
            this.accept = DFA35_accept;
            this.special = DFA35_special;
            this.transition = DFA35_transition;
        }
        public String getDescription() {
            return "643:7: ( ( testlist[null] augassign )=>lhs= testlist[expr_contextType.AugStore] ( (aay= augassign y1= yield_expr ) | (aat= augassign rhs= testlist[expr_contextType.Load] ) ) | ( testlist[null] ASSIGN )=>lhs= testlist[expr_contextType.Store] ( | ( (at= ASSIGN t+= testlist[expr_contextType.Store] )+ ) | ( (ay= ASSIGN y2+= yield_expr )+ ) ) | lhs= testlist[expr_contextType.Load] )";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA35_0 = input.LA(1);

                         
                        int index35_0 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA35_0==NOT) ) {s = 1;}

                        else if ( (LA35_0==PLUS) ) {s = 2;}

                        else if ( (LA35_0==MINUS) ) {s = 3;}

                        else if ( (LA35_0==TILDE) ) {s = 4;}

                        else if ( (LA35_0==LPAREN) ) {s = 5;}

                        else if ( (LA35_0==LBRACK) ) {s = 6;}

                        else if ( (LA35_0==LCURLY) ) {s = 7;}

                        else if ( (LA35_0==BACKQUOTE) ) {s = 8;}

                        else if ( (LA35_0==NAME) ) {s = 9;}

                        else if ( (LA35_0==PRINT) && ((printFunction))) {s = 10;}

                        else if ( (LA35_0==INT) ) {s = 11;}

                        else if ( (LA35_0==LONGINT) ) {s = 12;}

                        else if ( (LA35_0==FLOAT) ) {s = 13;}

                        else if ( (LA35_0==COMPLEX) ) {s = 14;}

                        else if ( (LA35_0==STRING) ) {s = 15;}

                        else if ( (LA35_0==LAMBDA) ) {s = 16;}

                         
                        input.seek(index35_0);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA35_1 = input.LA(1);

                         
                        int index35_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred2_Python()) ) {s = 17;}

                        else if ( (synpred3_Python()) ) {s = 18;}

                        else if ( (true) ) {s = 19;}

                         
                        input.seek(index35_1);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA35_2 = input.LA(1);

                         
                        int index35_2 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred2_Python()) ) {s = 17;}

                        else if ( (synpred3_Python()) ) {s = 18;}

                        else if ( (true) ) {s = 19;}

                         
                        input.seek(index35_2);
                        if ( s>=0 ) return s;
                        break;
                    case 3 : 
                        int LA35_3 = input.LA(1);

                         
                        int index35_3 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred2_Python()) ) {s = 17;}

                        else if ( (synpred3_Python()) ) {s = 18;}

                        else if ( (true) ) {s = 19;}

                         
                        input.seek(index35_3);
                        if ( s>=0 ) return s;
                        break;
                    case 4 : 
                        int LA35_4 = input.LA(1);

                         
                        int index35_4 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred2_Python()) ) {s = 17;}

                        else if ( (synpred3_Python()) ) {s = 18;}

                        else if ( (true) ) {s = 19;}

                         
                        input.seek(index35_4);
                        if ( s>=0 ) return s;
                        break;
                    case 5 : 
                        int LA35_5 = input.LA(1);

                         
                        int index35_5 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred2_Python()) ) {s = 17;}

                        else if ( (synpred3_Python()) ) {s = 18;}

                        else if ( (true) ) {s = 19;}

                         
                        input.seek(index35_5);
                        if ( s>=0 ) return s;
                        break;
                    case 6 : 
                        int LA35_6 = input.LA(1);

                         
                        int index35_6 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred2_Python()) ) {s = 17;}

                        else if ( (synpred3_Python()) ) {s = 18;}

                        else if ( (true) ) {s = 19;}

                         
                        input.seek(index35_6);
                        if ( s>=0 ) return s;
                        break;
                    case 7 : 
                        int LA35_7 = input.LA(1);

                         
                        int index35_7 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred2_Python()) ) {s = 17;}

                        else if ( (synpred3_Python()) ) {s = 18;}

                        else if ( (true) ) {s = 19;}

                         
                        input.seek(index35_7);
                        if ( s>=0 ) return s;
                        break;
                    case 8 : 
                        int LA35_8 = input.LA(1);

                         
                        int index35_8 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred2_Python()) ) {s = 17;}

                        else if ( (synpred3_Python()) ) {s = 18;}

                        else if ( (true) ) {s = 19;}

                         
                        input.seek(index35_8);
                        if ( s>=0 ) return s;
                        break;
                    case 9 : 
                        int LA35_9 = input.LA(1);

                         
                        int index35_9 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred2_Python()) ) {s = 17;}

                        else if ( (synpred3_Python()) ) {s = 18;}

                        else if ( (true) ) {s = 19;}

                         
                        input.seek(index35_9);
                        if ( s>=0 ) return s;
                        break;
                    case 10 : 
                        int LA35_10 = input.LA(1);

                         
                        int index35_10 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (((synpred2_Python()&&(printFunction))||(synpred2_Python()&&(printFunction)))) ) {s = 17;}

                        else if ( (((synpred3_Python()&&(printFunction))||(synpred3_Python()&&(printFunction)))) ) {s = 18;}

                        else if ( ((printFunction)) ) {s = 19;}

                         
                        input.seek(index35_10);
                        if ( s>=0 ) return s;
                        break;
                    case 11 : 
                        int LA35_11 = input.LA(1);

                         
                        int index35_11 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred2_Python()) ) {s = 17;}

                        else if ( (synpred3_Python()) ) {s = 18;}

                        else if ( (true) ) {s = 19;}

                         
                        input.seek(index35_11);
                        if ( s>=0 ) return s;
                        break;
                    case 12 : 
                        int LA35_12 = input.LA(1);

                         
                        int index35_12 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred2_Python()) ) {s = 17;}

                        else if ( (synpred3_Python()) ) {s = 18;}

                        else if ( (true) ) {s = 19;}

                         
                        input.seek(index35_12);
                        if ( s>=0 ) return s;
                        break;
                    case 13 : 
                        int LA35_13 = input.LA(1);

                         
                        int index35_13 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred2_Python()) ) {s = 17;}

                        else if ( (synpred3_Python()) ) {s = 18;}

                        else if ( (true) ) {s = 19;}

                         
                        input.seek(index35_13);
                        if ( s>=0 ) return s;
                        break;
                    case 14 : 
                        int LA35_14 = input.LA(1);

                         
                        int index35_14 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred2_Python()) ) {s = 17;}

                        else if ( (synpred3_Python()) ) {s = 18;}

                        else if ( (true) ) {s = 19;}

                         
                        input.seek(index35_14);
                        if ( s>=0 ) return s;
                        break;
                    case 15 : 
                        int LA35_15 = input.LA(1);

                         
                        int index35_15 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred2_Python()) ) {s = 17;}

                        else if ( (synpred3_Python()) ) {s = 18;}

                        else if ( (true) ) {s = 19;}

                         
                        input.seek(index35_15);
                        if ( s>=0 ) return s;
                        break;
                    case 16 : 
                        int LA35_16 = input.LA(1);

                         
                        int index35_16 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred2_Python()) ) {s = 17;}

                        else if ( (synpred3_Python()) ) {s = 18;}

                        else if ( (true) ) {s = 19;}

                         
                        input.seek(index35_16);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 35, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA31_eotS =
        "\17\uffff";
    static final String DFA31_eofS =
        "\17\uffff";
    static final String DFA31_minS =
        "\1\63\14\11\2\uffff";
    static final String DFA31_maxS =
        "\1\76\14\132\2\uffff";
    static final String DFA31_acceptS =
        "\15\uffff\1\2\1\1";
    static final String DFA31_specialS =
        "\17\uffff}>";
    static final String[] DFA31_transitionS = {
            "\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\13\1\14",
            "\1\15\1\uffff\1\15\23\uffff\2\15\10\uffff\1\16\1\uffff\1\15"+
            "\37\uffff\2\15\3\uffff\2\15\1\uffff\1\15\1\uffff\6\15",
            "\1\15\1\uffff\1\15\23\uffff\2\15\10\uffff\1\16\1\uffff\1\15"+
            "\37\uffff\2\15\3\uffff\2\15\1\uffff\1\15\1\uffff\6\15",
            "\1\15\1\uffff\1\15\23\uffff\2\15\10\uffff\1\16\1\uffff\1\15"+
            "\37\uffff\2\15\3\uffff\2\15\1\uffff\1\15\1\uffff\6\15",
            "\1\15\1\uffff\1\15\23\uffff\2\15\10\uffff\1\16\1\uffff\1\15"+
            "\37\uffff\2\15\3\uffff\2\15\1\uffff\1\15\1\uffff\6\15",
            "\1\15\1\uffff\1\15\23\uffff\2\15\10\uffff\1\16\1\uffff\1\15"+
            "\37\uffff\2\15\3\uffff\2\15\1\uffff\1\15\1\uffff\6\15",
            "\1\15\1\uffff\1\15\23\uffff\2\15\10\uffff\1\16\1\uffff\1\15"+
            "\37\uffff\2\15\3\uffff\2\15\1\uffff\1\15\1\uffff\6\15",
            "\1\15\1\uffff\1\15\23\uffff\2\15\10\uffff\1\16\1\uffff\1\15"+
            "\37\uffff\2\15\3\uffff\2\15\1\uffff\1\15\1\uffff\6\15",
            "\1\15\1\uffff\1\15\23\uffff\2\15\10\uffff\1\16\1\uffff\1\15"+
            "\37\uffff\2\15\3\uffff\2\15\1\uffff\1\15\1\uffff\6\15",
            "\1\15\1\uffff\1\15\23\uffff\2\15\10\uffff\1\16\1\uffff\1\15"+
            "\37\uffff\2\15\3\uffff\2\15\1\uffff\1\15\1\uffff\6\15",
            "\1\15\1\uffff\1\15\23\uffff\2\15\10\uffff\1\16\1\uffff\1\15"+
            "\37\uffff\2\15\3\uffff\2\15\1\uffff\1\15\1\uffff\6\15",
            "\1\15\1\uffff\1\15\23\uffff\2\15\10\uffff\1\16\1\uffff\1\15"+
            "\37\uffff\2\15\3\uffff\2\15\1\uffff\1\15\1\uffff\6\15",
            "\1\15\1\uffff\1\15\23\uffff\2\15\10\uffff\1\16\1\uffff\1\15"+
            "\37\uffff\2\15\3\uffff\2\15\1\uffff\1\15\1\uffff\6\15",
            "",
            ""
    };

    static final short[] DFA31_eot = DFA.unpackEncodedString(DFA31_eotS);
    static final short[] DFA31_eof = DFA.unpackEncodedString(DFA31_eofS);
    static final char[] DFA31_min = DFA.unpackEncodedStringToUnsignedChars(DFA31_minS);
    static final char[] DFA31_max = DFA.unpackEncodedStringToUnsignedChars(DFA31_maxS);
    static final short[] DFA31_accept = DFA.unpackEncodedString(DFA31_acceptS);
    static final short[] DFA31_special = DFA.unpackEncodedString(DFA31_specialS);
    static final short[][] DFA31_transition;

    static {
        int numStates = DFA31_transitionS.length;
        DFA31_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA31_transition[i] = DFA.unpackEncodedString(DFA31_transitionS[i]);
        }
    }

    class DFA31 extends DFA {

        public DFA31(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 31;
            this.eot = DFA31_eot;
            this.eof = DFA31_eof;
            this.min = DFA31_min;
            this.max = DFA31_max;
            this.accept = DFA31_accept;
            this.special = DFA31_special;
            this.transition = DFA31_transition;
        }
        public String getDescription() {
            return "644:9: ( (aay= augassign y1= yield_expr ) | (aat= augassign rhs= testlist[expr_contextType.Load] ) )";
        }
    }
    static final String DFA40_eotS =
        "\23\uffff";
    static final String DFA40_eofS =
        "\23\uffff";
    static final String DFA40_minS =
        "\1\11\20\0\2\uffff";
    static final String DFA40_maxS =
        "\1\132\20\0\2\uffff";
    static final String DFA40_acceptS =
        "\21\uffff\1\1\1\2";
    static final String DFA40_specialS =
        "\1\0\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\13\1\14\1\15\1"+
        "\16\1\17\1\20\2\uffff}>";
    static final String[] DFA40_transitionS = {
            "\1\11\1\uffff\1\12\23\uffff\1\20\1\1\12\uffff\1\5\37\uffff\1"+
            "\2\1\3\3\uffff\1\4\1\6\1\uffff\1\7\1\uffff\1\10\1\13\1\14\1"+
            "\15\1\16\1\17",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "",
            ""
    };

    static final short[] DFA40_eot = DFA.unpackEncodedString(DFA40_eotS);
    static final short[] DFA40_eof = DFA.unpackEncodedString(DFA40_eofS);
    static final char[] DFA40_min = DFA.unpackEncodedStringToUnsignedChars(DFA40_minS);
    static final char[] DFA40_max = DFA.unpackEncodedStringToUnsignedChars(DFA40_maxS);
    static final short[] DFA40_accept = DFA.unpackEncodedString(DFA40_acceptS);
    static final short[] DFA40_special = DFA.unpackEncodedString(DFA40_specialS);
    static final short[][] DFA40_transition;

    static {
        int numStates = DFA40_transitionS.length;
        DFA40_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA40_transition[i] = DFA.unpackEncodedString(DFA40_transitionS[i]);
        }
    }

    class DFA40 extends DFA {

        public DFA40(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 40;
            this.eot = DFA40_eot;
            this.eof = DFA40_eof;
            this.min = DFA40_min;
            this.max = DFA40_max;
            this.accept = DFA40_accept;
            this.special = DFA40_special;
            this.transition = DFA40_transition;
        }
        public String getDescription() {
            return "760:1: printlist returns [boolean newline, List elts] : ( ( test[null] COMMA )=>t+= test[expr_contextType.Load] ( options {k=2; } : COMMA t+= test[expr_contextType.Load] )* (trailcomma= COMMA )? | t+= test[expr_contextType.Load] );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA40_0 = input.LA(1);

                         
                        int index40_0 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA40_0==NOT) ) {s = 1;}

                        else if ( (LA40_0==PLUS) ) {s = 2;}

                        else if ( (LA40_0==MINUS) ) {s = 3;}

                        else if ( (LA40_0==TILDE) ) {s = 4;}

                        else if ( (LA40_0==LPAREN) ) {s = 5;}

                        else if ( (LA40_0==LBRACK) ) {s = 6;}

                        else if ( (LA40_0==LCURLY) ) {s = 7;}

                        else if ( (LA40_0==BACKQUOTE) ) {s = 8;}

                        else if ( (LA40_0==NAME) ) {s = 9;}

                        else if ( (LA40_0==PRINT) && ((printFunction))) {s = 10;}

                        else if ( (LA40_0==INT) ) {s = 11;}

                        else if ( (LA40_0==LONGINT) ) {s = 12;}

                        else if ( (LA40_0==FLOAT) ) {s = 13;}

                        else if ( (LA40_0==COMPLEX) ) {s = 14;}

                        else if ( (LA40_0==STRING) ) {s = 15;}

                        else if ( (LA40_0==LAMBDA) ) {s = 16;}

                         
                        input.seek(index40_0);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA40_1 = input.LA(1);

                         
                        int index40_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred4_Python()) ) {s = 17;}

                        else if ( (true) ) {s = 18;}

                         
                        input.seek(index40_1);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA40_2 = input.LA(1);

                         
                        int index40_2 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred4_Python()) ) {s = 17;}

                        else if ( (true) ) {s = 18;}

                         
                        input.seek(index40_2);
                        if ( s>=0 ) return s;
                        break;
                    case 3 : 
                        int LA40_3 = input.LA(1);

                         
                        int index40_3 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred4_Python()) ) {s = 17;}

                        else if ( (true) ) {s = 18;}

                         
                        input.seek(index40_3);
                        if ( s>=0 ) return s;
                        break;
                    case 4 : 
                        int LA40_4 = input.LA(1);

                         
                        int index40_4 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred4_Python()) ) {s = 17;}

                        else if ( (true) ) {s = 18;}

                         
                        input.seek(index40_4);
                        if ( s>=0 ) return s;
                        break;
                    case 5 : 
                        int LA40_5 = input.LA(1);

                         
                        int index40_5 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred4_Python()) ) {s = 17;}

                        else if ( (true) ) {s = 18;}

                         
                        input.seek(index40_5);
                        if ( s>=0 ) return s;
                        break;
                    case 6 : 
                        int LA40_6 = input.LA(1);

                         
                        int index40_6 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred4_Python()) ) {s = 17;}

                        else if ( (true) ) {s = 18;}

                         
                        input.seek(index40_6);
                        if ( s>=0 ) return s;
                        break;
                    case 7 : 
                        int LA40_7 = input.LA(1);

                         
                        int index40_7 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred4_Python()) ) {s = 17;}

                        else if ( (true) ) {s = 18;}

                         
                        input.seek(index40_7);
                        if ( s>=0 ) return s;
                        break;
                    case 8 : 
                        int LA40_8 = input.LA(1);

                         
                        int index40_8 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred4_Python()) ) {s = 17;}

                        else if ( (true) ) {s = 18;}

                         
                        input.seek(index40_8);
                        if ( s>=0 ) return s;
                        break;
                    case 9 : 
                        int LA40_9 = input.LA(1);

                         
                        int index40_9 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred4_Python()) ) {s = 17;}

                        else if ( (true) ) {s = 18;}

                         
                        input.seek(index40_9);
                        if ( s>=0 ) return s;
                        break;
                    case 10 : 
                        int LA40_10 = input.LA(1);

                         
                        int index40_10 = input.index();
                        input.rewind();
                        s = -1;
                        if ( ((synpred4_Python()&&(printFunction))) ) {s = 17;}

                        else if ( ((printFunction)) ) {s = 18;}

                         
                        input.seek(index40_10);
                        if ( s>=0 ) return s;
                        break;
                    case 11 : 
                        int LA40_11 = input.LA(1);

                         
                        int index40_11 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred4_Python()) ) {s = 17;}

                        else if ( (true) ) {s = 18;}

                         
                        input.seek(index40_11);
                        if ( s>=0 ) return s;
                        break;
                    case 12 : 
                        int LA40_12 = input.LA(1);

                         
                        int index40_12 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred4_Python()) ) {s = 17;}

                        else if ( (true) ) {s = 18;}

                         
                        input.seek(index40_12);
                        if ( s>=0 ) return s;
                        break;
                    case 13 : 
                        int LA40_13 = input.LA(1);

                         
                        int index40_13 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred4_Python()) ) {s = 17;}

                        else if ( (true) ) {s = 18;}

                         
                        input.seek(index40_13);
                        if ( s>=0 ) return s;
                        break;
                    case 14 : 
                        int LA40_14 = input.LA(1);

                         
                        int index40_14 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred4_Python()) ) {s = 17;}

                        else if ( (true) ) {s = 18;}

                         
                        input.seek(index40_14);
                        if ( s>=0 ) return s;
                        break;
                    case 15 : 
                        int LA40_15 = input.LA(1);

                         
                        int index40_15 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred4_Python()) ) {s = 17;}

                        else if ( (true) ) {s = 18;}

                         
                        input.seek(index40_15);
                        if ( s>=0 ) return s;
                        break;
                    case 16 : 
                        int LA40_16 = input.LA(1);

                         
                        int index40_16 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred4_Python()) ) {s = 17;}

                        else if ( (true) ) {s = 18;}

                         
                        input.seek(index40_16);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 40, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA38_eotS =
        "\26\uffff";
    static final String DFA38_eofS =
        "\26\uffff";
    static final String DFA38_minS =
        "\2\7\24\uffff";
    static final String DFA38_maxS =
        "\1\62\1\132\24\uffff";
    static final String DFA38_acceptS =
        "\2\uffff\1\2\3\uffff\1\1\17\uffff";
    static final String DFA38_specialS =
        "\26\uffff}>";
    static final String[] DFA38_transitionS = {
            "\1\2\47\uffff\1\1\2\uffff\1\2",
            "\1\2\1\uffff\1\6\1\uffff\1\6\23\uffff\2\6\12\uffff\1\6\6\uffff"+
            "\1\2\30\uffff\2\6\3\uffff\2\6\1\uffff\1\6\1\uffff\6\6",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA38_eot = DFA.unpackEncodedString(DFA38_eotS);
    static final short[] DFA38_eof = DFA.unpackEncodedString(DFA38_eofS);
    static final char[] DFA38_min = DFA.unpackEncodedStringToUnsignedChars(DFA38_minS);
    static final char[] DFA38_max = DFA.unpackEncodedStringToUnsignedChars(DFA38_maxS);
    static final short[] DFA38_accept = DFA.unpackEncodedString(DFA38_acceptS);
    static final short[] DFA38_special = DFA.unpackEncodedString(DFA38_specialS);
    static final short[][] DFA38_transition;

    static {
        int numStates = DFA38_transitionS.length;
        DFA38_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA38_transition[i] = DFA.unpackEncodedString(DFA38_transitionS[i]);
        }
    }

    class DFA38 extends DFA {

        public DFA38(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 38;
            this.eot = DFA38_eot;
            this.eof = DFA38_eof;
            this.min = DFA38_min;
            this.max = DFA38_max;
            this.accept = DFA38_accept;
            this.special = DFA38_special;
            this.transition = DFA38_transition;
        }
        public String getDescription() {
            return "()* loopback of 763:39: ( options {k=2; } : COMMA t+= test[expr_contextType.Load] )*";
        }
    }
    static final String DFA43_eotS =
        "\23\uffff";
    static final String DFA43_eofS =
        "\23\uffff";
    static final String DFA43_minS =
        "\1\11\20\0\2\uffff";
    static final String DFA43_maxS =
        "\1\132\20\0\2\uffff";
    static final String DFA43_acceptS =
        "\21\uffff\1\1\1\2";
    static final String DFA43_specialS =
        "\1\0\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\13\1\14\1\15\1"+
        "\16\1\17\1\20\2\uffff}>";
    static final String[] DFA43_transitionS = {
            "\1\11\1\uffff\1\12\23\uffff\1\20\1\1\12\uffff\1\5\37\uffff\1"+
            "\2\1\3\3\uffff\1\4\1\6\1\uffff\1\7\1\uffff\1\10\1\13\1\14\1"+
            "\15\1\16\1\17",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "",
            ""
    };

    static final short[] DFA43_eot = DFA.unpackEncodedString(DFA43_eotS);
    static final short[] DFA43_eof = DFA.unpackEncodedString(DFA43_eofS);
    static final char[] DFA43_min = DFA.unpackEncodedStringToUnsignedChars(DFA43_minS);
    static final char[] DFA43_max = DFA.unpackEncodedStringToUnsignedChars(DFA43_maxS);
    static final short[] DFA43_accept = DFA.unpackEncodedString(DFA43_acceptS);
    static final short[] DFA43_special = DFA.unpackEncodedString(DFA43_specialS);
    static final short[][] DFA43_transition;

    static {
        int numStates = DFA43_transitionS.length;
        DFA43_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA43_transition[i] = DFA.unpackEncodedString(DFA43_transitionS[i]);
        }
    }

    class DFA43 extends DFA {

        public DFA43(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 43;
            this.eot = DFA43_eot;
            this.eof = DFA43_eof;
            this.min = DFA43_min;
            this.max = DFA43_max;
            this.accept = DFA43_accept;
            this.special = DFA43_special;
            this.transition = DFA43_transition;
        }
        public String getDescription() {
            return "781:1: printlist2 returns [boolean newline, List elts] : ( ( test[null] COMMA test[null] )=>t+= test[expr_contextType.Load] ( options {k=2; } : COMMA t+= test[expr_contextType.Load] )* (trailcomma= COMMA )? | t+= test[expr_contextType.Load] );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA43_0 = input.LA(1);

                         
                        int index43_0 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA43_0==NOT) ) {s = 1;}

                        else if ( (LA43_0==PLUS) ) {s = 2;}

                        else if ( (LA43_0==MINUS) ) {s = 3;}

                        else if ( (LA43_0==TILDE) ) {s = 4;}

                        else if ( (LA43_0==LPAREN) ) {s = 5;}

                        else if ( (LA43_0==LBRACK) ) {s = 6;}

                        else if ( (LA43_0==LCURLY) ) {s = 7;}

                        else if ( (LA43_0==BACKQUOTE) ) {s = 8;}

                        else if ( (LA43_0==NAME) ) {s = 9;}

                        else if ( (LA43_0==PRINT) && ((printFunction))) {s = 10;}

                        else if ( (LA43_0==INT) ) {s = 11;}

                        else if ( (LA43_0==LONGINT) ) {s = 12;}

                        else if ( (LA43_0==FLOAT) ) {s = 13;}

                        else if ( (LA43_0==COMPLEX) ) {s = 14;}

                        else if ( (LA43_0==STRING) ) {s = 15;}

                        else if ( (LA43_0==LAMBDA) ) {s = 16;}

                         
                        input.seek(index43_0);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA43_1 = input.LA(1);

                         
                        int index43_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred5_Python()) ) {s = 17;}

                        else if ( (true) ) {s = 18;}

                         
                        input.seek(index43_1);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA43_2 = input.LA(1);

                         
                        int index43_2 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred5_Python()) ) {s = 17;}

                        else if ( (true) ) {s = 18;}

                         
                        input.seek(index43_2);
                        if ( s>=0 ) return s;
                        break;
                    case 3 : 
                        int LA43_3 = input.LA(1);

                         
                        int index43_3 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred5_Python()) ) {s = 17;}

                        else if ( (true) ) {s = 18;}

                         
                        input.seek(index43_3);
                        if ( s>=0 ) return s;
                        break;
                    case 4 : 
                        int LA43_4 = input.LA(1);

                         
                        int index43_4 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred5_Python()) ) {s = 17;}

                        else if ( (true) ) {s = 18;}

                         
                        input.seek(index43_4);
                        if ( s>=0 ) return s;
                        break;
                    case 5 : 
                        int LA43_5 = input.LA(1);

                         
                        int index43_5 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred5_Python()) ) {s = 17;}

                        else if ( (true) ) {s = 18;}

                         
                        input.seek(index43_5);
                        if ( s>=0 ) return s;
                        break;
                    case 6 : 
                        int LA43_6 = input.LA(1);

                         
                        int index43_6 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred5_Python()) ) {s = 17;}

                        else if ( (true) ) {s = 18;}

                         
                        input.seek(index43_6);
                        if ( s>=0 ) return s;
                        break;
                    case 7 : 
                        int LA43_7 = input.LA(1);

                         
                        int index43_7 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred5_Python()) ) {s = 17;}

                        else if ( (true) ) {s = 18;}

                         
                        input.seek(index43_7);
                        if ( s>=0 ) return s;
                        break;
                    case 8 : 
                        int LA43_8 = input.LA(1);

                         
                        int index43_8 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred5_Python()) ) {s = 17;}

                        else if ( (true) ) {s = 18;}

                         
                        input.seek(index43_8);
                        if ( s>=0 ) return s;
                        break;
                    case 9 : 
                        int LA43_9 = input.LA(1);

                         
                        int index43_9 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred5_Python()) ) {s = 17;}

                        else if ( (true) ) {s = 18;}

                         
                        input.seek(index43_9);
                        if ( s>=0 ) return s;
                        break;
                    case 10 : 
                        int LA43_10 = input.LA(1);

                         
                        int index43_10 = input.index();
                        input.rewind();
                        s = -1;
                        if ( ((synpred5_Python()&&(printFunction))) ) {s = 17;}

                        else if ( ((printFunction)) ) {s = 18;}

                         
                        input.seek(index43_10);
                        if ( s>=0 ) return s;
                        break;
                    case 11 : 
                        int LA43_11 = input.LA(1);

                         
                        int index43_11 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred5_Python()) ) {s = 17;}

                        else if ( (true) ) {s = 18;}

                         
                        input.seek(index43_11);
                        if ( s>=0 ) return s;
                        break;
                    case 12 : 
                        int LA43_12 = input.LA(1);

                         
                        int index43_12 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred5_Python()) ) {s = 17;}

                        else if ( (true) ) {s = 18;}

                         
                        input.seek(index43_12);
                        if ( s>=0 ) return s;
                        break;
                    case 13 : 
                        int LA43_13 = input.LA(1);

                         
                        int index43_13 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred5_Python()) ) {s = 17;}

                        else if ( (true) ) {s = 18;}

                         
                        input.seek(index43_13);
                        if ( s>=0 ) return s;
                        break;
                    case 14 : 
                        int LA43_14 = input.LA(1);

                         
                        int index43_14 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred5_Python()) ) {s = 17;}

                        else if ( (true) ) {s = 18;}

                         
                        input.seek(index43_14);
                        if ( s>=0 ) return s;
                        break;
                    case 15 : 
                        int LA43_15 = input.LA(1);

                         
                        int index43_15 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred5_Python()) ) {s = 17;}

                        else if ( (true) ) {s = 18;}

                         
                        input.seek(index43_15);
                        if ( s>=0 ) return s;
                        break;
                    case 16 : 
                        int LA43_16 = input.LA(1);

                         
                        int index43_16 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred5_Python()) ) {s = 17;}

                        else if ( (true) ) {s = 18;}

                         
                        input.seek(index43_16);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 43, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA41_eotS =
        "\26\uffff";
    static final String DFA41_eofS =
        "\26\uffff";
    static final String DFA41_minS =
        "\2\7\24\uffff";
    static final String DFA41_maxS =
        "\1\62\1\132\24\uffff";
    static final String DFA41_acceptS =
        "\2\uffff\1\2\1\uffff\1\1\21\uffff";
    static final String DFA41_specialS =
        "\26\uffff}>";
    static final String[] DFA41_transitionS = {
            "\1\2\47\uffff\1\1\2\uffff\1\2",
            "\1\2\1\uffff\1\4\1\uffff\1\4\23\uffff\2\4\12\uffff\1\4\6\uffff"+
            "\1\2\30\uffff\2\4\3\uffff\2\4\1\uffff\1\4\1\uffff\6\4",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA41_eot = DFA.unpackEncodedString(DFA41_eotS);
    static final short[] DFA41_eof = DFA.unpackEncodedString(DFA41_eofS);
    static final char[] DFA41_min = DFA.unpackEncodedStringToUnsignedChars(DFA41_minS);
    static final char[] DFA41_max = DFA.unpackEncodedStringToUnsignedChars(DFA41_maxS);
    static final short[] DFA41_accept = DFA.unpackEncodedString(DFA41_acceptS);
    static final short[] DFA41_special = DFA.unpackEncodedString(DFA41_specialS);
    static final short[][] DFA41_transition;

    static {
        int numStates = DFA41_transitionS.length;
        DFA41_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA41_transition[i] = DFA.unpackEncodedString(DFA41_transitionS[i]);
        }
    }

    class DFA41 extends DFA {

        public DFA41(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 41;
            this.eot = DFA41_eot;
            this.eof = DFA41_eof;
            this.min = DFA41_min;
            this.max = DFA41_max;
            this.accept = DFA41_accept;
            this.special = DFA41_special;
            this.transition = DFA41_transition;
        }
        public String getDescription() {
            return "()* loopback of 784:39: ( options {k=2; } : COMMA t+= test[expr_contextType.Load] )*";
        }
    }
    static final String DFA52_eotS =
        "\4\uffff";
    static final String DFA52_eofS =
        "\4\uffff";
    static final String DFA52_minS =
        "\2\11\2\uffff";
    static final String DFA52_maxS =
        "\1\12\1\34\2\uffff";
    static final String DFA52_acceptS =
        "\2\uffff\1\1\1\2";
    static final String DFA52_specialS =
        "\4\uffff}>";
    static final String[] DFA52_transitionS = {
            "\1\2\1\1",
            "\1\2\1\1\21\uffff\1\3",
            "",
            ""
    };

    static final short[] DFA52_eot = DFA.unpackEncodedString(DFA52_eotS);
    static final short[] DFA52_eof = DFA.unpackEncodedString(DFA52_eofS);
    static final char[] DFA52_min = DFA.unpackEncodedStringToUnsignedChars(DFA52_minS);
    static final char[] DFA52_max = DFA.unpackEncodedStringToUnsignedChars(DFA52_maxS);
    static final short[] DFA52_accept = DFA.unpackEncodedString(DFA52_acceptS);
    static final short[] DFA52_special = DFA.unpackEncodedString(DFA52_specialS);
    static final short[][] DFA52_transition;

    static {
        int numStates = DFA52_transitionS.length;
        DFA52_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA52_transition[i] = DFA.unpackEncodedString(DFA52_transitionS[i]);
        }
    }

    class DFA52 extends DFA {

        public DFA52(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 52;
            this.eot = DFA52_eot;
            this.eof = DFA52_eof;
            this.min = DFA52_min;
            this.max = DFA52_max;
            this.accept = DFA52_accept;
            this.special = DFA52_special;
            this.transition = DFA52_transition;
        }
        public String getDescription() {
            return "945:12: ( (d+= DOT )* dotted_name | (d+= DOT )+ )";
        }
    }
    static final String DFA80_eotS =
        "\33\uffff";
    static final String DFA80_eofS =
        "\1\2\32\uffff";
    static final String DFA80_minS =
        "\1\7\1\0\31\uffff";
    static final String DFA80_maxS =
        "\1\125\1\0\31\uffff";
    static final String DFA80_acceptS =
        "\2\uffff\1\2\27\uffff\1\1";
    static final String DFA80_specialS =
        "\1\uffff\1\0\31\uffff}>";
    static final String[] DFA80_transitionS = {
            "\1\2\5\uffff\1\2\13\uffff\1\2\1\uffff\1\1\20\uffff\4\2\2\uffff"+
            "\15\2\23\uffff\1\2\1\uffff\2\2",
            "\1\uffff",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA80_eot = DFA.unpackEncodedString(DFA80_eotS);
    static final short[] DFA80_eof = DFA.unpackEncodedString(DFA80_eofS);
    static final char[] DFA80_min = DFA.unpackEncodedStringToUnsignedChars(DFA80_minS);
    static final char[] DFA80_max = DFA.unpackEncodedStringToUnsignedChars(DFA80_maxS);
    static final short[] DFA80_accept = DFA.unpackEncodedString(DFA80_acceptS);
    static final short[] DFA80_special = DFA.unpackEncodedString(DFA80_specialS);
    static final short[][] DFA80_transition;

    static {
        int numStates = DFA80_transitionS.length;
        DFA80_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA80_transition[i] = DFA.unpackEncodedString(DFA80_transitionS[i]);
        }
    }

    class DFA80 extends DFA {

        public DFA80(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 80;
            this.eot = DFA80_eot;
            this.eof = DFA80_eof;
            this.min = DFA80_min;
            this.max = DFA80_max;
            this.accept = DFA80_accept;
            this.special = DFA80_special;
            this.transition = DFA80_transition;
        }
        public String getDescription() {
            return "1292:7: ( ( IF or_test[null] ORELSE )=> IF o2= or_test[ctype] ORELSE e= test[expr_contextType.Load] | -> or_test )";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA80_1 = input.LA(1);

                         
                        int index80_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred7_Python()) ) {s = 26;}

                        else if ( (true) ) {s = 2;}

                         
                        input.seek(index80_1);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 80, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA89_eotS =
        "\15\uffff";
    static final String DFA89_eofS =
        "\15\uffff";
    static final String DFA89_minS =
        "\1\35\11\uffff\1\11\2\uffff";
    static final String DFA89_maxS =
        "\1\106\11\uffff\1\132\2\uffff";
    static final String DFA89_acceptS =
        "\1\uffff\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\uffff\1\13\1\12";
    static final String DFA89_specialS =
        "\15\uffff}>";
    static final String[] DFA89_transitionS = {
            "\1\10\1\12\1\uffff\1\11\37\uffff\1\1\1\2\1\3\1\4\1\5\1\6\1\7",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\14\1\uffff\1\14\24\uffff\1\13\12\uffff\1\14\37\uffff\2\14"+
            "\3\uffff\2\14\1\uffff\1\14\1\uffff\6\14",
            "",
            ""
    };

    static final short[] DFA89_eot = DFA.unpackEncodedString(DFA89_eotS);
    static final short[] DFA89_eof = DFA.unpackEncodedString(DFA89_eofS);
    static final char[] DFA89_min = DFA.unpackEncodedStringToUnsignedChars(DFA89_minS);
    static final char[] DFA89_max = DFA.unpackEncodedStringToUnsignedChars(DFA89_maxS);
    static final short[] DFA89_accept = DFA.unpackEncodedString(DFA89_acceptS);
    static final short[] DFA89_special = DFA.unpackEncodedString(DFA89_specialS);
    static final short[][] DFA89_transition;

    static {
        int numStates = DFA89_transitionS.length;
        DFA89_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA89_transition[i] = DFA.unpackEncodedString(DFA89_transitionS[i]);
        }
    }

    class DFA89 extends DFA {

        public DFA89(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 89;
            this.eot = DFA89_eot;
            this.eof = DFA89_eof;
            this.min = DFA89_min;
            this.max = DFA89_max;
            this.accept = DFA89_accept;
            this.special = DFA89_special;
            this.transition = DFA89_transition;
        }
        public String getDescription() {
            return "1388:1: comp_op returns [cmpopType op] : ( LESS | GREATER | EQUAL | GREATEREQUAL | LESSEQUAL | ALT_NOTEQUAL | NOTEQUAL | IN | NOT IN | IS | IS NOT );";
        }
    }
    static final String DFA112_eotS =
        "\14\uffff";
    static final String DFA112_eofS =
        "\14\uffff";
    static final String DFA112_minS =
        "\1\11\13\uffff";
    static final String DFA112_maxS =
        "\1\132\13\uffff";
    static final String DFA112_acceptS =
        "\1\uffff\1\1\1\2\1\3\1\4\2\5\1\6\1\7\1\10\1\11\1\12";
    static final String DFA112_specialS =
        "\1\0\13\uffff}>";
    static final String[] DFA112_transitionS = {
            "\1\5\1\uffff\1\6\37\uffff\1\1\45\uffff\1\2\1\uffff\1\3\1\uffff"+
            "\1\4\1\7\1\10\1\11\1\12\1\13",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA112_eot = DFA.unpackEncodedString(DFA112_eotS);
    static final short[] DFA112_eof = DFA.unpackEncodedString(DFA112_eofS);
    static final char[] DFA112_min = DFA.unpackEncodedStringToUnsignedChars(DFA112_minS);
    static final char[] DFA112_max = DFA.unpackEncodedStringToUnsignedChars(DFA112_maxS);
    static final short[] DFA112_accept = DFA.unpackEncodedString(DFA112_acceptS);
    static final short[] DFA112_special = DFA.unpackEncodedString(DFA112_specialS);
    static final short[][] DFA112_transition;

    static {
        int numStates = DFA112_transitionS.length;
        DFA112_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA112_transition[i] = DFA.unpackEncodedString(DFA112_transitionS[i]);
        }
    }

    class DFA112 extends DFA {

        public DFA112(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 112;
            this.eot = DFA112_eot;
            this.eof = DFA112_eof;
            this.min = DFA112_min;
            this.max = DFA112_max;
            this.accept = DFA112_accept;
            this.special = DFA112_special;
            this.transition = DFA112_transition;
        }
        public String getDescription() {
            return "1713:1: atom returns [Token lparen = null] : ( LPAREN ( yield_expr | testlist_gexp -> testlist_gexp | ) RPAREN | LBRACK ( listmaker[$LBRACK] -> listmaker | ) RBRACK | LCURLY ( dictorsetmaker[$LCURLY] -> dictorsetmaker | ) RCURLY | lb= BACKQUOTE testlist[expr_contextType.Load] rb= BACKQUOTE | name_or_print | INT | LONGINT | FLOAT | COMPLEX | (S+= STRING )+ );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA112_0 = input.LA(1);

                         
                        int index112_0 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA112_0==LPAREN) ) {s = 1;}

                        else if ( (LA112_0==LBRACK) ) {s = 2;}

                        else if ( (LA112_0==LCURLY) ) {s = 3;}

                        else if ( (LA112_0==BACKQUOTE) ) {s = 4;}

                        else if ( (LA112_0==NAME) ) {s = 5;}

                        else if ( (LA112_0==PRINT) && ((printFunction))) {s = 6;}

                        else if ( (LA112_0==INT) ) {s = 7;}

                        else if ( (LA112_0==LONGINT) ) {s = 8;}

                        else if ( (LA112_0==FLOAT) ) {s = 9;}

                        else if ( (LA112_0==COMPLEX) ) {s = 10;}

                        else if ( (LA112_0==STRING) ) {s = 11;}

                         
                        input.seek(index112_0);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 112, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA116_eotS =
        "\24\uffff";
    static final String DFA116_eofS =
        "\24\uffff";
    static final String DFA116_minS =
        "\1\54\1\11\22\uffff";
    static final String DFA116_maxS =
        "\1\57\1\132\22\uffff";
    static final String DFA116_acceptS =
        "\2\uffff\1\2\1\uffff\1\1\17\uffff";
    static final String DFA116_specialS =
        "\24\uffff}>";
    static final String[] DFA116_transitionS = {
            "\1\2\2\uffff\1\1",
            "\1\4\1\uffff\1\4\23\uffff\2\4\12\uffff\1\4\1\2\36\uffff\2\4"+
            "\3\uffff\2\4\1\uffff\1\4\1\uffff\6\4",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA116_eot = DFA.unpackEncodedString(DFA116_eotS);
    static final short[] DFA116_eof = DFA.unpackEncodedString(DFA116_eofS);
    static final char[] DFA116_min = DFA.unpackEncodedStringToUnsignedChars(DFA116_minS);
    static final char[] DFA116_max = DFA.unpackEncodedStringToUnsignedChars(DFA116_maxS);
    static final short[] DFA116_accept = DFA.unpackEncodedString(DFA116_acceptS);
    static final short[] DFA116_special = DFA.unpackEncodedString(DFA116_specialS);
    static final short[][] DFA116_transition;

    static {
        int numStates = DFA116_transitionS.length;
        DFA116_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA116_transition[i] = DFA.unpackEncodedString(DFA116_transitionS[i]);
        }
    }

    class DFA116 extends DFA {

        public DFA116(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 116;
            this.eot = DFA116_eot;
            this.eof = DFA116_eof;
            this.min = DFA116_min;
            this.max = DFA116_max;
            this.accept = DFA116_accept;
            this.special = DFA116_special;
            this.transition = DFA116_transition;
        }
        public String getDescription() {
            return "()* loopback of 1822:11: ( options {k=2; } : c1= COMMA t+= test[$expr::ctype] )*";
        }
    }
    static final String DFA129_eotS =
        "\25\uffff";
    static final String DFA129_eofS =
        "\25\uffff";
    static final String DFA129_minS =
        "\1\11\1\uffff\20\0\3\uffff";
    static final String DFA129_maxS =
        "\1\132\1\uffff\20\0\3\uffff";
    static final String DFA129_acceptS =
        "\1\uffff\1\1\20\uffff\1\3\1\2\1\4";
    static final String DFA129_specialS =
        "\1\0\1\uffff\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\13\1\14"+
        "\1\15\1\16\1\17\1\20\3\uffff}>";
    static final String[] DFA129_transitionS = {
            "\1\12\1\1\1\13\23\uffff\1\21\1\2\12\uffff\1\6\1\uffff\1\22\35"+
            "\uffff\1\3\1\4\3\uffff\1\5\1\7\1\uffff\1\10\1\uffff\1\11\1\14"+
            "\1\15\1\16\1\17\1\20",
            "",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "",
            "",
            ""
    };

    static final short[] DFA129_eot = DFA.unpackEncodedString(DFA129_eotS);
    static final short[] DFA129_eof = DFA.unpackEncodedString(DFA129_eofS);
    static final char[] DFA129_min = DFA.unpackEncodedStringToUnsignedChars(DFA129_minS);
    static final char[] DFA129_max = DFA.unpackEncodedStringToUnsignedChars(DFA129_maxS);
    static final short[] DFA129_accept = DFA.unpackEncodedString(DFA129_acceptS);
    static final short[] DFA129_special = DFA.unpackEncodedString(DFA129_specialS);
    static final short[][] DFA129_transition;

    static {
        int numStates = DFA129_transitionS.length;
        DFA129_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA129_transition[i] = DFA.unpackEncodedString(DFA129_transitionS[i]);
        }
    }

    class DFA129 extends DFA {

        public DFA129(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 129;
            this.eot = DFA129_eot;
            this.eof = DFA129_eof;
            this.min = DFA129_min;
            this.max = DFA129_max;
            this.accept = DFA129_accept;
            this.special = DFA129_special;
            this.transition = DFA129_transition;
        }
        public String getDescription() {
            return "1907:1: subscript returns [slice sltype] : (d1= DOT DOT DOT | ( test[null] COLON )=>lower= test[expr_contextType.Load] (c1= COLON (upper1= test[expr_contextType.Load] )? ( sliceop )? )? | ( COLON )=>c2= COLON (upper2= test[expr_contextType.Load] )? ( sliceop )? | test[expr_contextType.Load] );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA129_0 = input.LA(1);

                         
                        int index129_0 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA129_0==DOT) ) {s = 1;}

                        else if ( (LA129_0==NOT) ) {s = 2;}

                        else if ( (LA129_0==PLUS) ) {s = 3;}

                        else if ( (LA129_0==MINUS) ) {s = 4;}

                        else if ( (LA129_0==TILDE) ) {s = 5;}

                        else if ( (LA129_0==LPAREN) ) {s = 6;}

                        else if ( (LA129_0==LBRACK) ) {s = 7;}

                        else if ( (LA129_0==LCURLY) ) {s = 8;}

                        else if ( (LA129_0==BACKQUOTE) ) {s = 9;}

                        else if ( (LA129_0==NAME) ) {s = 10;}

                        else if ( (LA129_0==PRINT) && ((printFunction))) {s = 11;}

                        else if ( (LA129_0==INT) ) {s = 12;}

                        else if ( (LA129_0==LONGINT) ) {s = 13;}

                        else if ( (LA129_0==FLOAT) ) {s = 14;}

                        else if ( (LA129_0==COMPLEX) ) {s = 15;}

                        else if ( (LA129_0==STRING) ) {s = 16;}

                        else if ( (LA129_0==LAMBDA) ) {s = 17;}

                        else if ( (LA129_0==COLON) && (synpred9_Python())) {s = 18;}

                         
                        input.seek(index129_0);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA129_2 = input.LA(1);

                         
                        int index129_2 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred8_Python()) ) {s = 19;}

                        else if ( (true) ) {s = 20;}

                         
                        input.seek(index129_2);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA129_3 = input.LA(1);

                         
                        int index129_3 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred8_Python()) ) {s = 19;}

                        else if ( (true) ) {s = 20;}

                         
                        input.seek(index129_3);
                        if ( s>=0 ) return s;
                        break;
                    case 3 : 
                        int LA129_4 = input.LA(1);

                         
                        int index129_4 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred8_Python()) ) {s = 19;}

                        else if ( (true) ) {s = 20;}

                         
                        input.seek(index129_4);
                        if ( s>=0 ) return s;
                        break;
                    case 4 : 
                        int LA129_5 = input.LA(1);

                         
                        int index129_5 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred8_Python()) ) {s = 19;}

                        else if ( (true) ) {s = 20;}

                         
                        input.seek(index129_5);
                        if ( s>=0 ) return s;
                        break;
                    case 5 : 
                        int LA129_6 = input.LA(1);

                         
                        int index129_6 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred8_Python()) ) {s = 19;}

                        else if ( (true) ) {s = 20;}

                         
                        input.seek(index129_6);
                        if ( s>=0 ) return s;
                        break;
                    case 6 : 
                        int LA129_7 = input.LA(1);

                         
                        int index129_7 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred8_Python()) ) {s = 19;}

                        else if ( (true) ) {s = 20;}

                         
                        input.seek(index129_7);
                        if ( s>=0 ) return s;
                        break;
                    case 7 : 
                        int LA129_8 = input.LA(1);

                         
                        int index129_8 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred8_Python()) ) {s = 19;}

                        else if ( (true) ) {s = 20;}

                         
                        input.seek(index129_8);
                        if ( s>=0 ) return s;
                        break;
                    case 8 : 
                        int LA129_9 = input.LA(1);

                         
                        int index129_9 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred8_Python()) ) {s = 19;}

                        else if ( (true) ) {s = 20;}

                         
                        input.seek(index129_9);
                        if ( s>=0 ) return s;
                        break;
                    case 9 : 
                        int LA129_10 = input.LA(1);

                         
                        int index129_10 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred8_Python()) ) {s = 19;}

                        else if ( (true) ) {s = 20;}

                         
                        input.seek(index129_10);
                        if ( s>=0 ) return s;
                        break;
                    case 10 : 
                        int LA129_11 = input.LA(1);

                         
                        int index129_11 = input.index();
                        input.rewind();
                        s = -1;
                        if ( ((synpred8_Python()&&(printFunction))) ) {s = 19;}

                        else if ( ((printFunction)) ) {s = 20;}

                         
                        input.seek(index129_11);
                        if ( s>=0 ) return s;
                        break;
                    case 11 : 
                        int LA129_12 = input.LA(1);

                         
                        int index129_12 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred8_Python()) ) {s = 19;}

                        else if ( (true) ) {s = 20;}

                         
                        input.seek(index129_12);
                        if ( s>=0 ) return s;
                        break;
                    case 12 : 
                        int LA129_13 = input.LA(1);

                         
                        int index129_13 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred8_Python()) ) {s = 19;}

                        else if ( (true) ) {s = 20;}

                         
                        input.seek(index129_13);
                        if ( s>=0 ) return s;
                        break;
                    case 13 : 
                        int LA129_14 = input.LA(1);

                         
                        int index129_14 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred8_Python()) ) {s = 19;}

                        else if ( (true) ) {s = 20;}

                         
                        input.seek(index129_14);
                        if ( s>=0 ) return s;
                        break;
                    case 14 : 
                        int LA129_15 = input.LA(1);

                         
                        int index129_15 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred8_Python()) ) {s = 19;}

                        else if ( (true) ) {s = 20;}

                         
                        input.seek(index129_15);
                        if ( s>=0 ) return s;
                        break;
                    case 15 : 
                        int LA129_16 = input.LA(1);

                         
                        int index129_16 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred8_Python()) ) {s = 19;}

                        else if ( (true) ) {s = 20;}

                         
                        input.seek(index129_16);
                        if ( s>=0 ) return s;
                        break;
                    case 16 : 
                        int LA129_17 = input.LA(1);

                         
                        int index129_17 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred8_Python()) ) {s = 19;}

                        else if ( (true) ) {s = 20;}

                         
                        input.seek(index129_17);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 129, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA133_eotS =
        "\21\uffff";
    static final String DFA133_eofS =
        "\21\uffff";
    static final String DFA133_minS =
        "\1\11\16\0\2\uffff";
    static final String DFA133_maxS =
        "\1\132\16\0\2\uffff";
    static final String DFA133_acceptS =
        "\17\uffff\1\1\1\2";
    static final String DFA133_specialS =
        "\1\0\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\13\1\14\1\15\1"+
        "\16\2\uffff}>";
    static final String[] DFA133_transitionS = {
            "\1\10\1\uffff\1\11\37\uffff\1\4\37\uffff\1\1\1\2\3\uffff\1\3"+
            "\1\5\1\uffff\1\6\1\uffff\1\7\1\12\1\13\1\14\1\15\1\16",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "",
            ""
    };

    static final short[] DFA133_eot = DFA.unpackEncodedString(DFA133_eotS);
    static final short[] DFA133_eof = DFA.unpackEncodedString(DFA133_eofS);
    static final char[] DFA133_min = DFA.unpackEncodedStringToUnsignedChars(DFA133_minS);
    static final char[] DFA133_max = DFA.unpackEncodedStringToUnsignedChars(DFA133_maxS);
    static final short[] DFA133_accept = DFA.unpackEncodedString(DFA133_acceptS);
    static final short[] DFA133_special = DFA.unpackEncodedString(DFA133_specialS);
    static final short[][] DFA133_transition;

    static {
        int numStates = DFA133_transitionS.length;
        DFA133_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA133_transition[i] = DFA.unpackEncodedString(DFA133_transitionS[i]);
        }
    }

    class DFA133 extends DFA {

        public DFA133(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 133;
            this.eot = DFA133_eot;
            this.eof = DFA133_eof;
            this.min = DFA133_min;
            this.max = DFA133_max;
            this.accept = DFA133_accept;
            this.special = DFA133_special;
            this.transition = DFA133_transition;
        }
        public String getDescription() {
            return "1953:1: exprlist[expr_contextType ctype] returns [expr etype] : ( ( expr[null] COMMA )=>e+= expr[ctype] ( options {k=2; } : COMMA e+= expr[ctype] )* ( COMMA )? | expr[ctype] );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA133_0 = input.LA(1);

                         
                        int index133_0 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA133_0==PLUS) ) {s = 1;}

                        else if ( (LA133_0==MINUS) ) {s = 2;}

                        else if ( (LA133_0==TILDE) ) {s = 3;}

                        else if ( (LA133_0==LPAREN) ) {s = 4;}

                        else if ( (LA133_0==LBRACK) ) {s = 5;}

                        else if ( (LA133_0==LCURLY) ) {s = 6;}

                        else if ( (LA133_0==BACKQUOTE) ) {s = 7;}

                        else if ( (LA133_0==NAME) ) {s = 8;}

                        else if ( (LA133_0==PRINT) && ((printFunction))) {s = 9;}

                        else if ( (LA133_0==INT) ) {s = 10;}

                        else if ( (LA133_0==LONGINT) ) {s = 11;}

                        else if ( (LA133_0==FLOAT) ) {s = 12;}

                        else if ( (LA133_0==COMPLEX) ) {s = 13;}

                        else if ( (LA133_0==STRING) ) {s = 14;}

                         
                        input.seek(index133_0);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA133_1 = input.LA(1);

                         
                        int index133_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred10_Python()) ) {s = 15;}

                        else if ( (true) ) {s = 16;}

                         
                        input.seek(index133_1);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA133_2 = input.LA(1);

                         
                        int index133_2 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred10_Python()) ) {s = 15;}

                        else if ( (true) ) {s = 16;}

                         
                        input.seek(index133_2);
                        if ( s>=0 ) return s;
                        break;
                    case 3 : 
                        int LA133_3 = input.LA(1);

                         
                        int index133_3 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred10_Python()) ) {s = 15;}

                        else if ( (true) ) {s = 16;}

                         
                        input.seek(index133_3);
                        if ( s>=0 ) return s;
                        break;
                    case 4 : 
                        int LA133_4 = input.LA(1);

                         
                        int index133_4 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred10_Python()) ) {s = 15;}

                        else if ( (true) ) {s = 16;}

                         
                        input.seek(index133_4);
                        if ( s>=0 ) return s;
                        break;
                    case 5 : 
                        int LA133_5 = input.LA(1);

                         
                        int index133_5 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred10_Python()) ) {s = 15;}

                        else if ( (true) ) {s = 16;}

                         
                        input.seek(index133_5);
                        if ( s>=0 ) return s;
                        break;
                    case 6 : 
                        int LA133_6 = input.LA(1);

                         
                        int index133_6 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred10_Python()) ) {s = 15;}

                        else if ( (true) ) {s = 16;}

                         
                        input.seek(index133_6);
                        if ( s>=0 ) return s;
                        break;
                    case 7 : 
                        int LA133_7 = input.LA(1);

                         
                        int index133_7 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred10_Python()) ) {s = 15;}

                        else if ( (true) ) {s = 16;}

                         
                        input.seek(index133_7);
                        if ( s>=0 ) return s;
                        break;
                    case 8 : 
                        int LA133_8 = input.LA(1);

                         
                        int index133_8 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred10_Python()) ) {s = 15;}

                        else if ( (true) ) {s = 16;}

                         
                        input.seek(index133_8);
                        if ( s>=0 ) return s;
                        break;
                    case 9 : 
                        int LA133_9 = input.LA(1);

                         
                        int index133_9 = input.index();
                        input.rewind();
                        s = -1;
                        if ( ((synpred10_Python()&&(printFunction))) ) {s = 15;}

                        else if ( ((printFunction)) ) {s = 16;}

                         
                        input.seek(index133_9);
                        if ( s>=0 ) return s;
                        break;
                    case 10 : 
                        int LA133_10 = input.LA(1);

                         
                        int index133_10 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred10_Python()) ) {s = 15;}

                        else if ( (true) ) {s = 16;}

                         
                        input.seek(index133_10);
                        if ( s>=0 ) return s;
                        break;
                    case 11 : 
                        int LA133_11 = input.LA(1);

                         
                        int index133_11 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred10_Python()) ) {s = 15;}

                        else if ( (true) ) {s = 16;}

                         
                        input.seek(index133_11);
                        if ( s>=0 ) return s;
                        break;
                    case 12 : 
                        int LA133_12 = input.LA(1);

                         
                        int index133_12 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred10_Python()) ) {s = 15;}

                        else if ( (true) ) {s = 16;}

                         
                        input.seek(index133_12);
                        if ( s>=0 ) return s;
                        break;
                    case 13 : 
                        int LA133_13 = input.LA(1);

                         
                        int index133_13 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred10_Python()) ) {s = 15;}

                        else if ( (true) ) {s = 16;}

                         
                        input.seek(index133_13);
                        if ( s>=0 ) return s;
                        break;
                    case 14 : 
                        int LA133_14 = input.LA(1);

                         
                        int index133_14 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred10_Python()) ) {s = 15;}

                        else if ( (true) ) {s = 16;}

                         
                        input.seek(index133_14);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 133, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA131_eotS =
        "\22\uffff";
    static final String DFA131_eofS =
        "\22\uffff";
    static final String DFA131_minS =
        "\1\35\1\11\20\uffff";
    static final String DFA131_maxS =
        "\1\57\1\132\20\uffff";
    static final String DFA131_acceptS =
        "\2\uffff\1\2\1\uffff\1\1\15\uffff";
    static final String DFA131_specialS =
        "\22\uffff}>";
    static final String[] DFA131_transitionS = {
            "\1\2\21\uffff\1\1",
            "\1\4\1\uffff\1\4\21\uffff\1\2\15\uffff\1\4\37\uffff\2\4\3\uffff"+
            "\2\4\1\uffff\1\4\1\uffff\6\4",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA131_eot = DFA.unpackEncodedString(DFA131_eotS);
    static final short[] DFA131_eof = DFA.unpackEncodedString(DFA131_eofS);
    static final char[] DFA131_min = DFA.unpackEncodedStringToUnsignedChars(DFA131_minS);
    static final char[] DFA131_max = DFA.unpackEncodedStringToUnsignedChars(DFA131_maxS);
    static final short[] DFA131_accept = DFA.unpackEncodedString(DFA131_acceptS);
    static final short[] DFA131_special = DFA.unpackEncodedString(DFA131_specialS);
    static final short[][] DFA131_transition;

    static {
        int numStates = DFA131_transitionS.length;
        DFA131_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA131_transition[i] = DFA.unpackEncodedString(DFA131_transitionS[i]);
        }
    }

    class DFA131 extends DFA {

        public DFA131(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 131;
            this.eot = DFA131_eot;
            this.eof = DFA131_eof;
            this.min = DFA131_min;
            this.max = DFA131_max;
            this.accept = DFA131_accept;
            this.special = DFA131_special;
            this.transition = DFA131_transition;
        }
        public String getDescription() {
            return "()* loopback of 1955:44: ( options {k=2; } : COMMA e+= expr[ctype] )*";
        }
    }
    static final String DFA134_eotS =
        "\24\uffff";
    static final String DFA134_eofS =
        "\24\uffff";
    static final String DFA134_minS =
        "\2\7\22\uffff";
    static final String DFA134_maxS =
        "\1\62\1\132\22\uffff";
    static final String DFA134_acceptS =
        "\2\uffff\1\2\1\uffff\1\1\17\uffff";
    static final String DFA134_specialS =
        "\24\uffff}>";
    static final String[] DFA134_transitionS = {
            "\1\2\47\uffff\1\1\2\uffff\1\2",
            "\1\2\1\uffff\1\4\1\uffff\1\4\37\uffff\1\4\6\uffff\1\2\30\uffff"+
            "\2\4\3\uffff\2\4\1\uffff\1\4\1\uffff\6\4",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA134_eot = DFA.unpackEncodedString(DFA134_eotS);
    static final short[] DFA134_eof = DFA.unpackEncodedString(DFA134_eofS);
    static final char[] DFA134_min = DFA.unpackEncodedStringToUnsignedChars(DFA134_minS);
    static final char[] DFA134_max = DFA.unpackEncodedStringToUnsignedChars(DFA134_maxS);
    static final short[] DFA134_accept = DFA.unpackEncodedString(DFA134_acceptS);
    static final short[] DFA134_special = DFA.unpackEncodedString(DFA134_specialS);
    static final short[][] DFA134_transition;

    static {
        int numStates = DFA134_transitionS.length;
        DFA134_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA134_transition[i] = DFA.unpackEncodedString(DFA134_transitionS[i]);
        }
    }

    class DFA134 extends DFA {

        public DFA134(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 134;
            this.eot = DFA134_eot;
            this.eof = DFA134_eof;
            this.min = DFA134_min;
            this.max = DFA134_max;
            this.accept = DFA134_accept;
            this.special = DFA134_special;
            this.transition = DFA134_transition;
        }
        public String getDescription() {
            return "()* loopback of 1969:37: ( options {k=2; } : COMMA e+= expr[expr_contextType.Del] )*";
        }
    }
    static final String DFA138_eotS =
        "\23\uffff";
    static final String DFA138_eofS =
        "\23\uffff";
    static final String DFA138_minS =
        "\1\11\20\0\2\uffff";
    static final String DFA138_maxS =
        "\1\132\20\0\2\uffff";
    static final String DFA138_acceptS =
        "\21\uffff\1\1\1\2";
    static final String DFA138_specialS =
        "\1\0\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\13\1\14\1\15\1"+
        "\16\1\17\1\20\2\uffff}>";
    static final String[] DFA138_transitionS = {
            "\1\11\1\uffff\1\12\23\uffff\1\20\1\1\12\uffff\1\5\37\uffff\1"+
            "\2\1\3\3\uffff\1\4\1\6\1\uffff\1\7\1\uffff\1\10\1\13\1\14\1"+
            "\15\1\16\1\17",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "",
            ""
    };

    static final short[] DFA138_eot = DFA.unpackEncodedString(DFA138_eotS);
    static final short[] DFA138_eof = DFA.unpackEncodedString(DFA138_eofS);
    static final char[] DFA138_min = DFA.unpackEncodedStringToUnsignedChars(DFA138_minS);
    static final char[] DFA138_max = DFA.unpackEncodedStringToUnsignedChars(DFA138_maxS);
    static final short[] DFA138_accept = DFA.unpackEncodedString(DFA138_acceptS);
    static final short[] DFA138_special = DFA.unpackEncodedString(DFA138_specialS);
    static final short[][] DFA138_transition;

    static {
        int numStates = DFA138_transitionS.length;
        DFA138_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA138_transition[i] = DFA.unpackEncodedString(DFA138_transitionS[i]);
        }
    }

    class DFA138 extends DFA {

        public DFA138(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 138;
            this.eot = DFA138_eot;
            this.eof = DFA138_eof;
            this.min = DFA138_min;
            this.max = DFA138_max;
            this.accept = DFA138_accept;
            this.special = DFA138_special;
            this.transition = DFA138_transition;
        }
        public String getDescription() {
            return "1976:1: testlist[expr_contextType ctype] : ( ( test[null] COMMA )=>t+= test[ctype] ( options {k=2; } : COMMA t+= test[ctype] )* ( COMMA )? | test[ctype] );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA138_0 = input.LA(1);

                         
                        int index138_0 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA138_0==NOT) ) {s = 1;}

                        else if ( (LA138_0==PLUS) ) {s = 2;}

                        else if ( (LA138_0==MINUS) ) {s = 3;}

                        else if ( (LA138_0==TILDE) ) {s = 4;}

                        else if ( (LA138_0==LPAREN) ) {s = 5;}

                        else if ( (LA138_0==LBRACK) ) {s = 6;}

                        else if ( (LA138_0==LCURLY) ) {s = 7;}

                        else if ( (LA138_0==BACKQUOTE) ) {s = 8;}

                        else if ( (LA138_0==NAME) ) {s = 9;}

                        else if ( (LA138_0==PRINT) && ((printFunction))) {s = 10;}

                        else if ( (LA138_0==INT) ) {s = 11;}

                        else if ( (LA138_0==LONGINT) ) {s = 12;}

                        else if ( (LA138_0==FLOAT) ) {s = 13;}

                        else if ( (LA138_0==COMPLEX) ) {s = 14;}

                        else if ( (LA138_0==STRING) ) {s = 15;}

                        else if ( (LA138_0==LAMBDA) ) {s = 16;}

                         
                        input.seek(index138_0);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA138_1 = input.LA(1);

                         
                        int index138_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred11_Python()) ) {s = 17;}

                        else if ( (true) ) {s = 18;}

                         
                        input.seek(index138_1);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA138_2 = input.LA(1);

                         
                        int index138_2 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred11_Python()) ) {s = 17;}

                        else if ( (true) ) {s = 18;}

                         
                        input.seek(index138_2);
                        if ( s>=0 ) return s;
                        break;
                    case 3 : 
                        int LA138_3 = input.LA(1);

                         
                        int index138_3 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred11_Python()) ) {s = 17;}

                        else if ( (true) ) {s = 18;}

                         
                        input.seek(index138_3);
                        if ( s>=0 ) return s;
                        break;
                    case 4 : 
                        int LA138_4 = input.LA(1);

                         
                        int index138_4 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred11_Python()) ) {s = 17;}

                        else if ( (true) ) {s = 18;}

                         
                        input.seek(index138_4);
                        if ( s>=0 ) return s;
                        break;
                    case 5 : 
                        int LA138_5 = input.LA(1);

                         
                        int index138_5 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred11_Python()) ) {s = 17;}

                        else if ( (true) ) {s = 18;}

                         
                        input.seek(index138_5);
                        if ( s>=0 ) return s;
                        break;
                    case 6 : 
                        int LA138_6 = input.LA(1);

                         
                        int index138_6 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred11_Python()) ) {s = 17;}

                        else if ( (true) ) {s = 18;}

                         
                        input.seek(index138_6);
                        if ( s>=0 ) return s;
                        break;
                    case 7 : 
                        int LA138_7 = input.LA(1);

                         
                        int index138_7 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred11_Python()) ) {s = 17;}

                        else if ( (true) ) {s = 18;}

                         
                        input.seek(index138_7);
                        if ( s>=0 ) return s;
                        break;
                    case 8 : 
                        int LA138_8 = input.LA(1);

                         
                        int index138_8 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred11_Python()) ) {s = 17;}

                        else if ( (true) ) {s = 18;}

                         
                        input.seek(index138_8);
                        if ( s>=0 ) return s;
                        break;
                    case 9 : 
                        int LA138_9 = input.LA(1);

                         
                        int index138_9 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred11_Python()) ) {s = 17;}

                        else if ( (true) ) {s = 18;}

                         
                        input.seek(index138_9);
                        if ( s>=0 ) return s;
                        break;
                    case 10 : 
                        int LA138_10 = input.LA(1);

                         
                        int index138_10 = input.index();
                        input.rewind();
                        s = -1;
                        if ( ((synpred11_Python()&&(printFunction))) ) {s = 17;}

                        else if ( ((printFunction)) ) {s = 18;}

                         
                        input.seek(index138_10);
                        if ( s>=0 ) return s;
                        break;
                    case 11 : 
                        int LA138_11 = input.LA(1);

                         
                        int index138_11 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred11_Python()) ) {s = 17;}

                        else if ( (true) ) {s = 18;}

                         
                        input.seek(index138_11);
                        if ( s>=0 ) return s;
                        break;
                    case 12 : 
                        int LA138_12 = input.LA(1);

                         
                        int index138_12 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred11_Python()) ) {s = 17;}

                        else if ( (true) ) {s = 18;}

                         
                        input.seek(index138_12);
                        if ( s>=0 ) return s;
                        break;
                    case 13 : 
                        int LA138_13 = input.LA(1);

                         
                        int index138_13 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred11_Python()) ) {s = 17;}

                        else if ( (true) ) {s = 18;}

                         
                        input.seek(index138_13);
                        if ( s>=0 ) return s;
                        break;
                    case 14 : 
                        int LA138_14 = input.LA(1);

                         
                        int index138_14 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred11_Python()) ) {s = 17;}

                        else if ( (true) ) {s = 18;}

                         
                        input.seek(index138_14);
                        if ( s>=0 ) return s;
                        break;
                    case 15 : 
                        int LA138_15 = input.LA(1);

                         
                        int index138_15 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred11_Python()) ) {s = 17;}

                        else if ( (true) ) {s = 18;}

                         
                        input.seek(index138_15);
                        if ( s>=0 ) return s;
                        break;
                    case 16 : 
                        int LA138_16 = input.LA(1);

                         
                        int index138_16 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred11_Python()) ) {s = 17;}

                        else if ( (true) ) {s = 18;}

                         
                        input.seek(index138_16);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 138, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA136_eotS =
        "\76\uffff";
    static final String DFA136_eofS =
        "\2\2\74\uffff";
    static final String DFA136_minS =
        "\2\7\74\uffff";
    static final String DFA136_maxS =
        "\1\125\1\132\74\uffff";
    static final String DFA136_acceptS =
        "\2\uffff\1\2\46\uffff\1\1\5\uffff\1\1\16\uffff";
    static final String DFA136_specialS =
        "\76\uffff}>";
    static final String[] DFA136_transitionS = {
            "\1\2\21\uffff\1\2\1\uffff\1\2\20\uffff\3\2\1\1\2\uffff\15\2"+
            "\23\uffff\1\2\2\uffff\1\2",
            "\1\2\1\uffff\1\57\1\uffff\1\57\15\uffff\1\2\1\uffff\1\2\3\uffff"+
            "\2\57\12\uffff\1\57\4\2\2\uffff\15\2\14\uffff\2\57\3\uffff\2"+
            "\57\1\2\1\57\1\uffff\1\51\5\57",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA136_eot = DFA.unpackEncodedString(DFA136_eotS);
    static final short[] DFA136_eof = DFA.unpackEncodedString(DFA136_eofS);
    static final char[] DFA136_min = DFA.unpackEncodedStringToUnsignedChars(DFA136_minS);
    static final char[] DFA136_max = DFA.unpackEncodedStringToUnsignedChars(DFA136_maxS);
    static final short[] DFA136_accept = DFA.unpackEncodedString(DFA136_acceptS);
    static final short[] DFA136_special = DFA.unpackEncodedString(DFA136_specialS);
    static final short[][] DFA136_transition;

    static {
        int numStates = DFA136_transitionS.length;
        DFA136_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA136_transition[i] = DFA.unpackEncodedString(DFA136_transitionS[i]);
        }
    }

    class DFA136 extends DFA {

        public DFA136(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 136;
            this.eot = DFA136_eot;
            this.eof = DFA136_eof;
            this.min = DFA136_min;
            this.max = DFA136_max;
            this.accept = DFA136_accept;
            this.special = DFA136_special;
            this.transition = DFA136_transition;
        }
        public String getDescription() {
            return "()* loopback of 1986:22: ( options {k=2; } : COMMA t+= test[ctype] )*";
        }
    }
    static final String DFA139_eotS =
        "\24\uffff";
    static final String DFA139_eofS =
        "\24\uffff";
    static final String DFA139_minS =
        "\1\57\1\11\22\uffff";
    static final String DFA139_maxS =
        "\1\124\1\132\22\uffff";
    static final String DFA139_acceptS =
        "\2\uffff\1\2\1\uffff\1\1\17\uffff";
    static final String DFA139_specialS =
        "\24\uffff}>";
    static final String[] DFA139_transitionS = {
            "\1\1\44\uffff\1\2",
            "\1\4\1\uffff\1\4\23\uffff\2\4\12\uffff\1\4\37\uffff\2\4\3\uffff"+
            "\2\4\1\uffff\1\4\1\2\6\4",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA139_eot = DFA.unpackEncodedString(DFA139_eotS);
    static final short[] DFA139_eof = DFA.unpackEncodedString(DFA139_eofS);
    static final char[] DFA139_min = DFA.unpackEncodedStringToUnsignedChars(DFA139_minS);
    static final char[] DFA139_max = DFA.unpackEncodedStringToUnsignedChars(DFA139_maxS);
    static final short[] DFA139_accept = DFA.unpackEncodedString(DFA139_acceptS);
    static final short[] DFA139_special = DFA.unpackEncodedString(DFA139_specialS);
    static final short[][] DFA139_transition;

    static {
        int numStates = DFA139_transitionS.length;
        DFA139_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA139_transition[i] = DFA.unpackEncodedString(DFA139_transitionS[i]);
        }
    }

    class DFA139 extends DFA {

        public DFA139(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 139;
            this.eot = DFA139_eot;
            this.eof = DFA139_eof;
            this.min = DFA139_min;
            this.max = DFA139_max;
            this.accept = DFA139_accept;
            this.special = DFA139_special;
            this.transition = DFA139_transition;
        }
        public String getDescription() {
            return "()* loopback of 2014:18: ( options {k=2; } : COMMA k+= test[expr_contextType.Load] COLON v+= test[expr_contextType.Load] )*";
        }
    }
 

    public static final BitSet FOLLOW_NEWLINE_in_single_input118 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_EOF_in_single_input121 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simple_stmt_in_single_input137 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_NEWLINE_in_single_input139 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_EOF_in_single_input142 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_compound_stmt_in_single_input158 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_NEWLINE_in_single_input160 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_EOF_in_single_input163 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NEWLINE_in_file_input215 = new BitSet(new long[]{0x00000FF99F4FCA80L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_stmt_in_file_input225 = new BitSet(new long[]{0x00000FF99F4FCA80L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_EOF_in_file_input244 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LEADING_WS_in_eval_input298 = new BitSet(new long[]{0x0000080180000A80L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_NEWLINE_in_eval_input302 = new BitSet(new long[]{0x0000080180000A80L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_testlist_in_eval_input306 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_NEWLINE_in_eval_input310 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_EOF_in_eval_input314 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NAME_in_dotted_attr366 = new BitSet(new long[]{0x0000000000000402L});
    public static final BitSet FOLLOW_DOT_in_dotted_attr377 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_NAME_in_dotted_attr381 = new BitSet(new long[]{0x0000000000000402L});
    public static final BitSet FOLLOW_NAME_in_name_or_print446 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PRINT_in_name_or_print460 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_attr0 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_AT_in_decorator762 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_dotted_attr_in_decorator764 = new BitSet(new long[]{0x0000080000000080L});
    public static final BitSet FOLLOW_LPAREN_in_decorator772 = new BitSet(new long[]{0x0003180180000A00L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_arglist_in_decorator782 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_RPAREN_in_decorator826 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_NEWLINE_in_decorator848 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_decorator_in_decorators876 = new BitSet(new long[]{0x0000040000000002L});
    public static final BitSet FOLLOW_decorators_in_funcdef914 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_DEF_in_funcdef917 = new BitSet(new long[]{0x0000000000000A00L});
    public static final BitSet FOLLOW_name_or_print_in_funcdef919 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_parameters_in_funcdef921 = new BitSet(new long[]{0x0000200000000000L});
    public static final BitSet FOLLOW_COLON_in_funcdef923 = new BitSet(new long[]{0x00000A39954ACA80L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_suite_in_funcdef925 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_parameters958 = new BitSet(new long[]{0x0003180000000200L});
    public static final BitSet FOLLOW_varargslist_in_parameters967 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_RPAREN_in_parameters1011 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_fpdef_in_defparameter1044 = new BitSet(new long[]{0x0000400000000002L});
    public static final BitSet FOLLOW_ASSIGN_in_defparameter1048 = new BitSet(new long[]{0x0000080180000A00L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_test_in_defparameter1050 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_defparameter_in_varargslist1096 = new BitSet(new long[]{0x0000800000000002L});
    public static final BitSet FOLLOW_COMMA_in_varargslist1107 = new BitSet(new long[]{0x0000080000000200L});
    public static final BitSet FOLLOW_defparameter_in_varargslist1111 = new BitSet(new long[]{0x0000800000000002L});
    public static final BitSet FOLLOW_COMMA_in_varargslist1123 = new BitSet(new long[]{0x0003000000000002L});
    public static final BitSet FOLLOW_STAR_in_varargslist1136 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_NAME_in_varargslist1140 = new BitSet(new long[]{0x0000800000000002L});
    public static final BitSet FOLLOW_COMMA_in_varargslist1143 = new BitSet(new long[]{0x0002000000000000L});
    public static final BitSet FOLLOW_DOUBLESTAR_in_varargslist1145 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_NAME_in_varargslist1149 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DOUBLESTAR_in_varargslist1165 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_NAME_in_varargslist1169 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STAR_in_varargslist1207 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_NAME_in_varargslist1211 = new BitSet(new long[]{0x0000800000000002L});
    public static final BitSet FOLLOW_COMMA_in_varargslist1214 = new BitSet(new long[]{0x0002000000000000L});
    public static final BitSet FOLLOW_DOUBLESTAR_in_varargslist1216 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_NAME_in_varargslist1220 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DOUBLESTAR_in_varargslist1238 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_NAME_in_varargslist1242 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NAME_in_fpdef1279 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_fpdef1306 = new BitSet(new long[]{0x0000080000000200L});
    public static final BitSet FOLLOW_fplist_in_fpdef1308 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_RPAREN_in_fpdef1310 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_fpdef1326 = new BitSet(new long[]{0x0000080000000200L});
    public static final BitSet FOLLOW_fplist_in_fpdef1329 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_RPAREN_in_fpdef1331 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_fpdef_in_fplist1360 = new BitSet(new long[]{0x0000800000000002L});
    public static final BitSet FOLLOW_COMMA_in_fplist1377 = new BitSet(new long[]{0x0000080000000200L});
    public static final BitSet FOLLOW_fpdef_in_fplist1381 = new BitSet(new long[]{0x0000800000000002L});
    public static final BitSet FOLLOW_COMMA_in_fplist1387 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simple_stmt_in_stmt1423 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_compound_stmt_in_stmt1439 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_small_stmt_in_simple_stmt1475 = new BitSet(new long[]{0x0004000000000080L});
    public static final BitSet FOLLOW_SEMI_in_simple_stmt1485 = new BitSet(new long[]{0x00000A39954ACA00L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_small_stmt_in_simple_stmt1489 = new BitSet(new long[]{0x0004000000000080L});
    public static final BitSet FOLLOW_SEMI_in_simple_stmt1494 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_NEWLINE_in_simple_stmt1498 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expr_stmt_in_small_stmt1521 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_del_stmt_in_small_stmt1536 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_pass_stmt_in_small_stmt1551 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_flow_stmt_in_small_stmt1566 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_import_stmt_in_small_stmt1581 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_global_stmt_in_small_stmt1596 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_exec_stmt_in_small_stmt1611 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_assert_stmt_in_small_stmt1626 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_print_stmt_in_small_stmt1645 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_testlist_in_expr_stmt1693 = new BitSet(new long[]{0x7FF8000000000000L});
    public static final BitSet FOLLOW_augassign_in_expr_stmt1709 = new BitSet(new long[]{0x0000023000028000L});
    public static final BitSet FOLLOW_yield_expr_in_expr_stmt1713 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_augassign_in_expr_stmt1753 = new BitSet(new long[]{0x0000080180000A00L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_testlist_in_expr_stmt1757 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_testlist_in_expr_stmt1812 = new BitSet(new long[]{0x0000400000000002L});
    public static final BitSet FOLLOW_ASSIGN_in_expr_stmt1839 = new BitSet(new long[]{0x0000080180000A00L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_testlist_in_expr_stmt1843 = new BitSet(new long[]{0x0000400000000002L});
    public static final BitSet FOLLOW_ASSIGN_in_expr_stmt1888 = new BitSet(new long[]{0x0000023000028000L});
    public static final BitSet FOLLOW_yield_expr_in_expr_stmt1892 = new BitSet(new long[]{0x0000400000000002L});
    public static final BitSet FOLLOW_testlist_in_expr_stmt1940 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PLUSEQUAL_in_augassign1982 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUSEQUAL_in_augassign2000 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STAREQUAL_in_augassign2018 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SLASHEQUAL_in_augassign2036 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PERCENTEQUAL_in_augassign2054 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_AMPEREQUAL_in_augassign2072 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VBAREQUAL_in_augassign2090 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CIRCUMFLEXEQUAL_in_augassign2108 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LEFTSHIFTEQUAL_in_augassign2126 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RIGHTSHIFTEQUAL_in_augassign2144 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DOUBLESTAREQUAL_in_augassign2162 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DOUBLESLASHEQUAL_in_augassign2180 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PRINT_in_print_stmt2220 = new BitSet(new long[]{0x8000080180000A02L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_printlist_in_print_stmt2231 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RIGHTSHIFT_in_print_stmt2250 = new BitSet(new long[]{0x0000080180000A00L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_printlist2_in_print_stmt2254 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_test_in_printlist2334 = new BitSet(new long[]{0x0000800000000002L});
    public static final BitSet FOLLOW_COMMA_in_printlist2346 = new BitSet(new long[]{0x0000080180000A00L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_test_in_printlist2350 = new BitSet(new long[]{0x0000800000000002L});
    public static final BitSet FOLLOW_COMMA_in_printlist2358 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_test_in_printlist2379 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_test_in_printlist22436 = new BitSet(new long[]{0x0000800000000002L});
    public static final BitSet FOLLOW_COMMA_in_printlist22448 = new BitSet(new long[]{0x0000080180000A00L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_test_in_printlist22452 = new BitSet(new long[]{0x0000800000000002L});
    public static final BitSet FOLLOW_COMMA_in_printlist22460 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_test_in_printlist22481 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DELETE_in_del_stmt2518 = new BitSet(new long[]{0x0000080100000A00L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_del_list_in_del_stmt2520 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PASS_in_pass_stmt2556 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_break_stmt_in_flow_stmt2582 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_continue_stmt_in_flow_stmt2590 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_return_stmt_in_flow_stmt2598 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_raise_stmt_in_flow_stmt2606 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_yield_stmt_in_flow_stmt2614 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BREAK_in_break_stmt2642 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CONTINUE_in_continue_stmt2678 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RETURN_in_return_stmt2714 = new BitSet(new long[]{0x0000080180000A02L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_testlist_in_return_stmt2723 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_yield_expr_in_yield_stmt2788 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RAISE_in_raise_stmt2824 = new BitSet(new long[]{0x0000080180000A02L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_test_in_raise_stmt2829 = new BitSet(new long[]{0x0000800000000002L});
    public static final BitSet FOLLOW_COMMA_in_raise_stmt2833 = new BitSet(new long[]{0x0000080180000A00L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_test_in_raise_stmt2837 = new BitSet(new long[]{0x0000800000000002L});
    public static final BitSet FOLLOW_COMMA_in_raise_stmt2849 = new BitSet(new long[]{0x0000080180000A00L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_test_in_raise_stmt2853 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_import_name_in_import_stmt2886 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_import_from_in_import_stmt2894 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IMPORT_in_import_name2922 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_dotted_as_names_in_import_name2924 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FROM_in_import_from2961 = new BitSet(new long[]{0x0000000000000600L});
    public static final BitSet FOLLOW_DOT_in_import_from2966 = new BitSet(new long[]{0x0000000000000600L});
    public static final BitSet FOLLOW_dotted_name_in_import_from2969 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_DOT_in_import_from2975 = new BitSet(new long[]{0x0000000010000400L});
    public static final BitSet FOLLOW_IMPORT_in_import_from2979 = new BitSet(new long[]{0x0001080000000200L});
    public static final BitSet FOLLOW_STAR_in_import_from2990 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_import_as_names_in_import_from3015 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_import_from3038 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_import_as_names_in_import_from3042 = new BitSet(new long[]{0x0000900000000000L});
    public static final BitSet FOLLOW_COMMA_in_import_from3044 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_RPAREN_in_import_from3047 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_import_as_name_in_import_as_names3096 = new BitSet(new long[]{0x0000800000000002L});
    public static final BitSet FOLLOW_COMMA_in_import_as_names3099 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_import_as_name_in_import_as_names3104 = new BitSet(new long[]{0x0000800000000002L});
    public static final BitSet FOLLOW_NAME_in_import_as_name3145 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_AS_in_import_as_name3148 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_NAME_in_import_as_name3152 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_dotted_name_in_dotted_as_name3192 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_AS_in_dotted_as_name3195 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_NAME_in_dotted_as_name3199 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_dotted_as_name_in_dotted_as_names3235 = new BitSet(new long[]{0x0000800000000002L});
    public static final BitSet FOLLOW_COMMA_in_dotted_as_names3238 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_dotted_as_name_in_dotted_as_names3243 = new BitSet(new long[]{0x0000800000000002L});
    public static final BitSet FOLLOW_NAME_in_dotted_name3277 = new BitSet(new long[]{0x0000000000000402L});
    public static final BitSet FOLLOW_DOT_in_dotted_name3280 = new BitSet(new long[]{0x000003FFFFFFFA00L});
    public static final BitSet FOLLOW_attr_in_dotted_name3284 = new BitSet(new long[]{0x0000000000000402L});
    public static final BitSet FOLLOW_GLOBAL_in_global_stmt3320 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_NAME_in_global_stmt3324 = new BitSet(new long[]{0x0000800000000002L});
    public static final BitSet FOLLOW_COMMA_in_global_stmt3327 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_NAME_in_global_stmt3331 = new BitSet(new long[]{0x0000800000000002L});
    public static final BitSet FOLLOW_EXEC_in_exec_stmt3369 = new BitSet(new long[]{0x0000080100000A00L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_expr_in_exec_stmt3371 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_IN_in_exec_stmt3375 = new BitSet(new long[]{0x0000080180000A00L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_test_in_exec_stmt3379 = new BitSet(new long[]{0x0000800000000002L});
    public static final BitSet FOLLOW_COMMA_in_exec_stmt3383 = new BitSet(new long[]{0x0000080180000A00L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_test_in_exec_stmt3387 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ASSERT_in_assert_stmt3428 = new BitSet(new long[]{0x0000080180000A00L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_test_in_assert_stmt3432 = new BitSet(new long[]{0x0000800000000002L});
    public static final BitSet FOLLOW_COMMA_in_assert_stmt3436 = new BitSet(new long[]{0x0000080180000A00L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_test_in_assert_stmt3440 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_if_stmt_in_compound_stmt3469 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_while_stmt_in_compound_stmt3477 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_for_stmt_in_compound_stmt3485 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_try_stmt_in_compound_stmt3493 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_with_stmt_in_compound_stmt3501 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_funcdef_in_compound_stmt3518 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_classdef_in_compound_stmt3526 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IF_in_if_stmt3554 = new BitSet(new long[]{0x0000080180000A00L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_test_in_if_stmt3556 = new BitSet(new long[]{0x0000200000000000L});
    public static final BitSet FOLLOW_COLON_in_if_stmt3559 = new BitSet(new long[]{0x00000A39954ACA80L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_suite_in_if_stmt3563 = new BitSet(new long[]{0x0000000400100002L});
    public static final BitSet FOLLOW_elif_clause_in_if_stmt3566 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_else_clause_in_elif_clause3611 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ELIF_in_elif_clause3627 = new BitSet(new long[]{0x0000080180000A00L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_test_in_elif_clause3629 = new BitSet(new long[]{0x0000200000000000L});
    public static final BitSet FOLLOW_COLON_in_elif_clause3632 = new BitSet(new long[]{0x00000A39954ACA80L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_suite_in_elif_clause3634 = new BitSet(new long[]{0x0000000400100002L});
    public static final BitSet FOLLOW_elif_clause_in_elif_clause3646 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ORELSE_in_else_clause3706 = new BitSet(new long[]{0x0000200000000000L});
    public static final BitSet FOLLOW_COLON_in_else_clause3708 = new BitSet(new long[]{0x00000A39954ACA80L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_suite_in_else_clause3712 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_WHILE_in_while_stmt3749 = new BitSet(new long[]{0x0000080180000A00L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_test_in_while_stmt3751 = new BitSet(new long[]{0x0000200000000000L});
    public static final BitSet FOLLOW_COLON_in_while_stmt3754 = new BitSet(new long[]{0x00000A39954ACA80L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_suite_in_while_stmt3758 = new BitSet(new long[]{0x0000000400000002L});
    public static final BitSet FOLLOW_ORELSE_in_while_stmt3762 = new BitSet(new long[]{0x0000200000000000L});
    public static final BitSet FOLLOW_COLON_in_while_stmt3764 = new BitSet(new long[]{0x00000A39954ACA80L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_suite_in_while_stmt3768 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FOR_in_for_stmt3807 = new BitSet(new long[]{0x0000080100000A00L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_exprlist_in_for_stmt3809 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_IN_in_for_stmt3812 = new BitSet(new long[]{0x0000080180000A00L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_testlist_in_for_stmt3814 = new BitSet(new long[]{0x0000200000000000L});
    public static final BitSet FOLLOW_COLON_in_for_stmt3817 = new BitSet(new long[]{0x00000A39954ACA80L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_suite_in_for_stmt3821 = new BitSet(new long[]{0x0000000400000002L});
    public static final BitSet FOLLOW_ORELSE_in_for_stmt3833 = new BitSet(new long[]{0x0000200000000000L});
    public static final BitSet FOLLOW_COLON_in_for_stmt3835 = new BitSet(new long[]{0x00000A39954ACA80L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_suite_in_for_stmt3839 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TRY_in_try_stmt3882 = new BitSet(new long[]{0x0000200000000000L});
    public static final BitSet FOLLOW_COLON_in_try_stmt3884 = new BitSet(new long[]{0x00000A39954ACA80L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_suite_in_try_stmt3888 = new BitSet(new long[]{0x0000000000A00000L});
    public static final BitSet FOLLOW_except_clause_in_try_stmt3901 = new BitSet(new long[]{0x0000000400A00002L});
    public static final BitSet FOLLOW_ORELSE_in_try_stmt3905 = new BitSet(new long[]{0x0000200000000000L});
    public static final BitSet FOLLOW_COLON_in_try_stmt3907 = new BitSet(new long[]{0x00000A39954ACA80L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_suite_in_try_stmt3911 = new BitSet(new long[]{0x0000000000800002L});
    public static final BitSet FOLLOW_FINALLY_in_try_stmt3917 = new BitSet(new long[]{0x0000200000000000L});
    public static final BitSet FOLLOW_COLON_in_try_stmt3919 = new BitSet(new long[]{0x00000A39954ACA80L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_suite_in_try_stmt3923 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FINALLY_in_try_stmt3946 = new BitSet(new long[]{0x0000200000000000L});
    public static final BitSet FOLLOW_COLON_in_try_stmt3948 = new BitSet(new long[]{0x00000A39954ACA80L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_suite_in_try_stmt3952 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_WITH_in_with_stmt4001 = new BitSet(new long[]{0x0000080180000A00L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_with_item_in_with_stmt4005 = new BitSet(new long[]{0x0000A00000000000L});
    public static final BitSet FOLLOW_COMMA_in_with_stmt4015 = new BitSet(new long[]{0x0000080180000A00L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_with_item_in_with_stmt4019 = new BitSet(new long[]{0x0000A00000000000L});
    public static final BitSet FOLLOW_COLON_in_with_stmt4023 = new BitSet(new long[]{0x00000A39954ACA80L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_suite_in_with_stmt4025 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_test_in_with_item4062 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_AS_in_with_item4066 = new BitSet(new long[]{0x0000080100000A00L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_expr_in_with_item4068 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EXCEPT_in_except_clause4107 = new BitSet(new long[]{0x0000280180000A00L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_test_in_except_clause4112 = new BitSet(new long[]{0x0000A00000002000L});
    public static final BitSet FOLLOW_set_in_except_clause4116 = new BitSet(new long[]{0x0000080180000A00L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_test_in_except_clause4126 = new BitSet(new long[]{0x0000200000000000L});
    public static final BitSet FOLLOW_COLON_in_except_clause4133 = new BitSet(new long[]{0x00000A39954ACA80L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_suite_in_except_clause4135 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simple_stmt_in_suite4181 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NEWLINE_in_suite4197 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_INDENT_in_suite4199 = new BitSet(new long[]{0x00000FF99F4FCA80L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_stmt_in_suite4208 = new BitSet(new long[]{0x00000FF99F4FCAA0L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_DEDENT_in_suite4228 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_or_test_in_test4258 = new BitSet(new long[]{0x0000000008000002L});
    public static final BitSet FOLLOW_IF_in_test4280 = new BitSet(new long[]{0x0000080100000A00L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_or_test_in_test4284 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_ORELSE_in_test4287 = new BitSet(new long[]{0x0000080180000A00L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_test_in_test4291 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_lambdef_in_test4336 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_and_test_in_or_test4371 = new BitSet(new long[]{0x0000000200000002L});
    public static final BitSet FOLLOW_OR_in_or_test4387 = new BitSet(new long[]{0x0000080100000A00L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_and_test_in_or_test4391 = new BitSet(new long[]{0x0000000200000002L});
    public static final BitSet FOLLOW_not_test_in_and_test4472 = new BitSet(new long[]{0x0000000000001002L});
    public static final BitSet FOLLOW_AND_in_and_test4488 = new BitSet(new long[]{0x0000080100000A00L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_not_test_in_and_test4492 = new BitSet(new long[]{0x0000000000001002L});
    public static final BitSet FOLLOW_NOT_in_not_test4576 = new BitSet(new long[]{0x0000080100000A00L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_not_test_in_not_test4580 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_comparison_in_not_test4597 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expr_in_comparison4646 = new BitSet(new long[]{0x0000000160000002L,0x000000000000007FL});
    public static final BitSet FOLLOW_comp_op_in_comparison4660 = new BitSet(new long[]{0x0000080100000A00L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_expr_in_comparison4664 = new BitSet(new long[]{0x0000000160000002L,0x000000000000007FL});
    public static final BitSet FOLLOW_LESS_in_comp_op4745 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GREATER_in_comp_op4761 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EQUAL_in_comp_op4777 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GREATEREQUAL_in_comp_op4793 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LESSEQUAL_in_comp_op4809 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ALT_NOTEQUAL_in_comp_op4825 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NOTEQUAL_in_comp_op4841 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IN_in_comp_op4857 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NOT_in_comp_op4873 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_IN_in_comp_op4875 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IS_in_comp_op4891 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IS_in_comp_op4907 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_NOT_in_comp_op4909 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_xor_expr_in_expr4961 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000080L});
    public static final BitSet FOLLOW_VBAR_in_expr4976 = new BitSet(new long[]{0x0000080100000A00L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_xor_expr_in_expr4980 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000080L});
    public static final BitSet FOLLOW_and_expr_in_xor_expr5059 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000100L});
    public static final BitSet FOLLOW_CIRCUMFLEX_in_xor_expr5074 = new BitSet(new long[]{0x0000080100000A00L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_and_expr_in_xor_expr5078 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000100L});
    public static final BitSet FOLLOW_shift_expr_in_and_expr5156 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000200L});
    public static final BitSet FOLLOW_AMPER_in_and_expr5171 = new BitSet(new long[]{0x0000080100000A00L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_shift_expr_in_and_expr5175 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000200L});
    public static final BitSet FOLLOW_arith_expr_in_shift_expr5258 = new BitSet(new long[]{0x8000000000000002L,0x0000000000000400L});
    public static final BitSet FOLLOW_shift_op_in_shift_expr5272 = new BitSet(new long[]{0x0000080100000A00L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_arith_expr_in_shift_expr5276 = new BitSet(new long[]{0x8000000000000002L,0x0000000000000400L});
    public static final BitSet FOLLOW_LEFTSHIFT_in_shift_op5360 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RIGHTSHIFT_in_shift_op5376 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_term_in_arith_expr5422 = new BitSet(new long[]{0x0000000000000002L,0x0000000000001800L});
    public static final BitSet FOLLOW_arith_op_in_arith_expr5435 = new BitSet(new long[]{0x0000080100000A00L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_term_in_arith_expr5439 = new BitSet(new long[]{0x0000000000000002L,0x0000000000001800L});
    public static final BitSet FOLLOW_PLUS_in_arith_op5547 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_arith_op5563 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_factor_in_term5609 = new BitSet(new long[]{0x0001000000000002L,0x000000000000E000L});
    public static final BitSet FOLLOW_term_op_in_term5622 = new BitSet(new long[]{0x0000080100000A00L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_factor_in_term5626 = new BitSet(new long[]{0x0001000000000002L,0x000000000000E000L});
    public static final BitSet FOLLOW_STAR_in_term_op5708 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SLASH_in_term_op5724 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PERCENT_in_term_op5740 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DOUBLESLASH_in_term_op5756 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PLUS_in_factor5795 = new BitSet(new long[]{0x0000080100000A00L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_factor_in_factor5799 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_factor5815 = new BitSet(new long[]{0x0000080100000A00L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_factor_in_factor5819 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TILDE_in_factor5835 = new BitSet(new long[]{0x0000080100000A00L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_factor_in_factor5839 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_power_in_factor5855 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_atom_in_power5894 = new BitSet(new long[]{0x0002080000000402L,0x0000000000020000L});
    public static final BitSet FOLLOW_trailer_in_power5899 = new BitSet(new long[]{0x0002080000000402L,0x0000000000020000L});
    public static final BitSet FOLLOW_DOUBLESTAR_in_power5914 = new BitSet(new long[]{0x0000080100000A00L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_factor_in_power5916 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_atom5966 = new BitSet(new long[]{0x00001A3180028A00L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_yield_expr_in_atom5984 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_testlist_gexp_in_atom6004 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_RPAREN_in_atom6047 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LBRACK_in_atom6055 = new BitSet(new long[]{0x0000080180000A00L,0x0000000007EF1800L});
    public static final BitSet FOLLOW_listmaker_in_atom6064 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_RBRACK_in_atom6107 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LCURLY_in_atom6115 = new BitSet(new long[]{0x0000080180000A00L,0x0000000007FB1800L});
    public static final BitSet FOLLOW_dictorsetmaker_in_atom6125 = new BitSet(new long[]{0x0000000000000000L,0x0000000000100000L});
    public static final BitSet FOLLOW_RCURLY_in_atom6173 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BACKQUOTE_in_atom6184 = new BitSet(new long[]{0x0000080180000A00L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_testlist_in_atom6186 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_BACKQUOTE_in_atom6191 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_name_or_print_in_atom6209 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INT_in_atom6227 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LONGINT_in_atom6245 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FLOAT_in_atom6263 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COMPLEX_in_atom6281 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_in_atom6302 = new BitSet(new long[]{0x0000000000000002L,0x0000000004000000L});
    public static final BitSet FOLLOW_test_in_listmaker6345 = new BitSet(new long[]{0x0000800002000002L});
    public static final BitSet FOLLOW_list_for_in_listmaker6357 = new BitSet(new long[]{0x0000800000000002L});
    public static final BitSet FOLLOW_COMMA_in_listmaker6389 = new BitSet(new long[]{0x0000080180000A00L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_test_in_listmaker6393 = new BitSet(new long[]{0x0000800000000002L});
    public static final BitSet FOLLOW_COMMA_in_listmaker6422 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_test_in_testlist_gexp6454 = new BitSet(new long[]{0x0000800002000002L});
    public static final BitSet FOLLOW_COMMA_in_testlist_gexp6478 = new BitSet(new long[]{0x0000080180000A00L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_test_in_testlist_gexp6482 = new BitSet(new long[]{0x0000800000000002L});
    public static final BitSet FOLLOW_COMMA_in_testlist_gexp6490 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_comp_for_in_testlist_gexp6544 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LAMBDA_in_lambdef6608 = new BitSet(new long[]{0x0003280000000200L});
    public static final BitSet FOLLOW_varargslist_in_lambdef6611 = new BitSet(new long[]{0x0000200000000000L});
    public static final BitSet FOLLOW_COLON_in_lambdef6615 = new BitSet(new long[]{0x0000080180000A00L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_test_in_lambdef6617 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_trailer6656 = new BitSet(new long[]{0x0003180180000A00L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_arglist_in_trailer6665 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_RPAREN_in_trailer6707 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LBRACK_in_trailer6715 = new BitSet(new long[]{0x0000280180000E00L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_subscriptlist_in_trailer6717 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_RBRACK_in_trailer6720 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DOT_in_trailer6736 = new BitSet(new long[]{0x000003FFFFFFFA00L});
    public static final BitSet FOLLOW_attr_in_trailer6738 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_subscript_in_subscriptlist6777 = new BitSet(new long[]{0x0000800000000002L});
    public static final BitSet FOLLOW_COMMA_in_subscriptlist6789 = new BitSet(new long[]{0x0000280180000E00L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_subscript_in_subscriptlist6793 = new BitSet(new long[]{0x0000800000000002L});
    public static final BitSet FOLLOW_COMMA_in_subscriptlist6800 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DOT_in_subscript6843 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_DOT_in_subscript6845 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_DOT_in_subscript6847 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_test_in_subscript6877 = new BitSet(new long[]{0x0000200000000002L});
    public static final BitSet FOLLOW_COLON_in_subscript6883 = new BitSet(new long[]{0x0000280180000A02L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_test_in_subscript6888 = new BitSet(new long[]{0x0000200000000002L});
    public static final BitSet FOLLOW_sliceop_in_subscript6894 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COLON_in_subscript6925 = new BitSet(new long[]{0x0000280180000A02L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_test_in_subscript6930 = new BitSet(new long[]{0x0000200000000002L});
    public static final BitSet FOLLOW_sliceop_in_subscript6936 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_test_in_subscript6954 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COLON_in_sliceop6991 = new BitSet(new long[]{0x0000080180000A02L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_test_in_sliceop6999 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expr_in_exprlist7070 = new BitSet(new long[]{0x0000800000000002L});
    public static final BitSet FOLLOW_COMMA_in_exprlist7082 = new BitSet(new long[]{0x0000080100000A00L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_expr_in_exprlist7086 = new BitSet(new long[]{0x0000800000000002L});
    public static final BitSet FOLLOW_COMMA_in_exprlist7092 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expr_in_exprlist7111 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expr_in_del_list7149 = new BitSet(new long[]{0x0000800000000002L});
    public static final BitSet FOLLOW_COMMA_in_del_list7161 = new BitSet(new long[]{0x0000080100000A00L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_expr_in_del_list7165 = new BitSet(new long[]{0x0000800000000002L});
    public static final BitSet FOLLOW_COMMA_in_del_list7171 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_test_in_testlist7224 = new BitSet(new long[]{0x0000800000000002L});
    public static final BitSet FOLLOW_COMMA_in_testlist7236 = new BitSet(new long[]{0x0000080180000A00L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_test_in_testlist7240 = new BitSet(new long[]{0x0000800000000002L});
    public static final BitSet FOLLOW_COMMA_in_testlist7246 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_test_in_testlist7264 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_test_in_dictorsetmaker7297 = new BitSet(new long[]{0x0000A00002000002L});
    public static final BitSet FOLLOW_COLON_in_dictorsetmaker7325 = new BitSet(new long[]{0x0000080180000A00L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_test_in_dictorsetmaker7329 = new BitSet(new long[]{0x0000800002000000L});
    public static final BitSet FOLLOW_comp_for_in_dictorsetmaker7349 = new BitSet(new long[]{0x0000800000000002L});
    public static final BitSet FOLLOW_COMMA_in_dictorsetmaker7396 = new BitSet(new long[]{0x0000080180000A00L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_test_in_dictorsetmaker7400 = new BitSet(new long[]{0x0000200000000000L});
    public static final BitSet FOLLOW_COLON_in_dictorsetmaker7403 = new BitSet(new long[]{0x0000080180000A00L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_test_in_dictorsetmaker7407 = new BitSet(new long[]{0x0000800000000002L});
    public static final BitSet FOLLOW_COMMA_in_dictorsetmaker7463 = new BitSet(new long[]{0x0000080180000A00L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_test_in_dictorsetmaker7467 = new BitSet(new long[]{0x0000800000000002L});
    public static final BitSet FOLLOW_COMMA_in_dictorsetmaker7517 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_comp_for_in_dictorsetmaker7532 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_decorators_in_classdef7585 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_CLASS_in_classdef7588 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_NAME_in_classdef7590 = new BitSet(new long[]{0x0000280000000000L});
    public static final BitSet FOLLOW_LPAREN_in_classdef7593 = new BitSet(new long[]{0x0000180180000A00L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_testlist_in_classdef7595 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_RPAREN_in_classdef7599 = new BitSet(new long[]{0x0000200000000000L});
    public static final BitSet FOLLOW_COLON_in_classdef7603 = new BitSet(new long[]{0x00000A39954ACA80L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_suite_in_classdef7605 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_argument_in_arglist7647 = new BitSet(new long[]{0x0000800000000002L});
    public static final BitSet FOLLOW_COMMA_in_arglist7651 = new BitSet(new long[]{0x0000080180000A00L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_argument_in_arglist7653 = new BitSet(new long[]{0x0000800000000002L});
    public static final BitSet FOLLOW_COMMA_in_arglist7669 = new BitSet(new long[]{0x0003000000000002L});
    public static final BitSet FOLLOW_STAR_in_arglist7687 = new BitSet(new long[]{0x0000080180000A00L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_test_in_arglist7691 = new BitSet(new long[]{0x0000800000000002L});
    public static final BitSet FOLLOW_COMMA_in_arglist7695 = new BitSet(new long[]{0x0000080180000A00L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_argument_in_arglist7697 = new BitSet(new long[]{0x0000800000000002L});
    public static final BitSet FOLLOW_COMMA_in_arglist7703 = new BitSet(new long[]{0x0002000000000000L});
    public static final BitSet FOLLOW_DOUBLESTAR_in_arglist7705 = new BitSet(new long[]{0x0000080180000A00L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_test_in_arglist7709 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DOUBLESTAR_in_arglist7730 = new BitSet(new long[]{0x0000080180000A00L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_test_in_arglist7734 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STAR_in_arglist7781 = new BitSet(new long[]{0x0000080180000A00L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_test_in_arglist7785 = new BitSet(new long[]{0x0000800000000002L});
    public static final BitSet FOLLOW_COMMA_in_arglist7789 = new BitSet(new long[]{0x0000080180000A00L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_argument_in_arglist7791 = new BitSet(new long[]{0x0000800000000002L});
    public static final BitSet FOLLOW_COMMA_in_arglist7797 = new BitSet(new long[]{0x0002000000000000L});
    public static final BitSet FOLLOW_DOUBLESTAR_in_arglist7799 = new BitSet(new long[]{0x0000080180000A00L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_test_in_arglist7803 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DOUBLESTAR_in_arglist7822 = new BitSet(new long[]{0x0000080180000A00L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_test_in_arglist7826 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_test_in_argument7865 = new BitSet(new long[]{0x0000C00002000000L});
    public static final BitSet FOLLOW_ASSIGN_in_argument7878 = new BitSet(new long[]{0x0000080180000A00L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_test_in_argument7882 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_comp_for_in_argument7908 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_list_for_in_list_iter7973 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_list_if_in_list_iter7982 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FOR_in_list_for8008 = new BitSet(new long[]{0x0000080100000A00L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_exprlist_in_list_for8010 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_IN_in_list_for8013 = new BitSet(new long[]{0x0000080180000A00L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_testlist_in_list_for8015 = new BitSet(new long[]{0x000000000A000002L});
    public static final BitSet FOLLOW_list_iter_in_list_for8019 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IF_in_list_if8049 = new BitSet(new long[]{0x0000080180000A00L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_test_in_list_if8051 = new BitSet(new long[]{0x000000000A000002L});
    public static final BitSet FOLLOW_list_iter_in_list_if8055 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_comp_for_in_comp_iter8086 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_comp_if_in_comp_iter8095 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FOR_in_comp_for8121 = new BitSet(new long[]{0x0000080100000A00L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_exprlist_in_comp_for8123 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_IN_in_comp_for8126 = new BitSet(new long[]{0x0000080100000A00L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_or_test_in_comp_for8128 = new BitSet(new long[]{0x000080000A000002L});
    public static final BitSet FOLLOW_comp_iter_in_comp_for8131 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IF_in_comp_if8160 = new BitSet(new long[]{0x0000080180000A00L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_test_in_comp_if8162 = new BitSet(new long[]{0x000080000A000002L});
    public static final BitSet FOLLOW_comp_iter_in_comp_if8165 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_YIELD_in_yield_expr8206 = new BitSet(new long[]{0x0000080180000A02L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_testlist_in_yield_expr8208 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_synpred1_Python1296 = new BitSet(new long[]{0x0000080000000200L});
    public static final BitSet FOLLOW_fpdef_in_synpred1_Python1298 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_COMMA_in_synpred1_Python1301 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_testlist_in_synpred2_Python1683 = new BitSet(new long[]{0x7FF8000000000000L});
    public static final BitSet FOLLOW_augassign_in_synpred2_Python1686 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_testlist_in_synpred3_Python1802 = new BitSet(new long[]{0x0000400000000000L});
    public static final BitSet FOLLOW_ASSIGN_in_synpred3_Python1805 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_test_in_synpred4_Python2317 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_COMMA_in_synpred4_Python2320 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_test_in_synpred5_Python2416 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_COMMA_in_synpred5_Python2419 = new BitSet(new long[]{0x0000080180000A00L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_test_in_synpred5_Python2421 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_decorators_in_synpred6_Python3510 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_DEF_in_synpred6_Python3513 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IF_in_synpred7_Python4270 = new BitSet(new long[]{0x0000080100000A00L,0x0000000007EB1800L});
    public static final BitSet FOLLOW_or_test_in_synpred7_Python4272 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_ORELSE_in_synpred7_Python4275 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_test_in_synpred8_Python6864 = new BitSet(new long[]{0x0000200000000000L});
    public static final BitSet FOLLOW_COLON_in_synpred8_Python6867 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COLON_in_synpred9_Python6915 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expr_in_synpred10_Python7060 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_COMMA_in_synpred10_Python7063 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_test_in_synpred11_Python7211 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_COMMA_in_synpred11_Python7214 = new BitSet(new long[]{0x0000000000000002L});

}