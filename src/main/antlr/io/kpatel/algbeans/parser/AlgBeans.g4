grammar AlgBeans;

@header{
package io.kpatel.algbeans.parser;
}

fragment WS                 : ' ' | '\n' | '\t' | '\r';
fragment JAVA_LETTER        : '$' | '_' | ('A' .. 'Z') | ('a' .. 'z');
fragment JAVA_LETTER_NUMBER : '0' .. '9' | JAVA_LETTER ;
fragment JAVA_IDENTIFIER    : JAVA_LETTER JAVA_LETTER_NUMBER*;

LINE_END       : WS* ';';
ASSIGN         : WS* '=' ;
UNION          : WS* '|' ;
TP_START       : WS* '<' ;
TP_END         : WS* '>' ;
P_START        : WS* '(' ;
P_END          : WS* ')' ;
COMMA          : WS* ',' ;
SPACE          : ' ' | '\t';
END            : WS* EOF;
PACKAGE        : WS* 'package' SPACE;
IMPORT         : WS* 'import' SPACE;
IDENTIFIER     : WS* JAVA_IDENTIFIER;
QUALIFIED_NAME : WS* JAVA_IDENTIFIER ('.' JAVA_IDENTIFIER)*;

document     : packageLine? importLine* typeLine* END;
packageLine  : PACKAGE QUALIFIED_NAME LINE_END;
importLine   : IMPORT QUALIFIED_NAME LINE_END;

typeLine      : type ASSIGN constructor (UNION constructor)* LINE_END;
type          : IDENTIFIER typeParams?;
typeParams    : TP_START IDENTIFIER (COMMA IDENTIFIER)* TP_END;
constructor   : IDENTIFIER consParams;
consParams    : P_START parameter (COMMA parameter)* P_END;
parameter     : type SPACE IDENTIFIER;


