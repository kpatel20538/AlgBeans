grammar AlgBeans;

@header{
package io.kpatel.algbeans.parser;
}


fragment JAVA_LETTER        : '$' | '_' | ('A' .. 'Z') | ('a' .. 'z');
fragment JAVA_LETTER_NUMBER : '0' .. '9' | JAVA_LETTER ;


SPACE           : ' ' | '\t';
WS              : ' ' | '\n' | '\t' | '\r';
LINE_END        : WS* ';';
ASSIGN          : WS* '=' WS*;
UNION           : WS* '|' WS*;
TP_START        : '<' WS*;
TP_END          : WS* '>';
P_START         : '(' WS*;
P_END           : WS* ')';
COMMA           : WS* ',' WS*;
PACKAGE         : 'package' SPACE WS*;
IMPORT          : 'import' SPACE WS*;
JAVA_IDENTIFIER : JAVA_LETTER JAVA_LETTER_NUMBER*;
QUALIFIED_NAME  : JAVA_IDENTIFIER ('.' JAVA_IDENTIFIER)*;


document     : WS* (packageLine WS*)? (importLine WS*)* (typeLine WS*)* EOF;
packageLine  : PACKAGE QUALIFIED_NAME LINE_END;
importLine   : IMPORT QUALIFIED_NAME LINE_END;

typeLine      : type ASSIGN constructor (UNION constructor)* LINE_END;
type          : JAVA_IDENTIFIER WS* typeParams?;
typeParams    : TP_START JAVA_IDENTIFIER (COMMA JAVA_IDENTIFIER)* TP_END;
constructor   : JAVA_IDENTIFIER WS* consParams;
consParams    : P_START (parameter (COMMA parameter)*)? P_END;
parameter     : type SPACE WS* JAVA_IDENTIFIER;


