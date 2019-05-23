grammar AlgBeans;

@header{
package io.kpatel.algbeans.parser;
}

/*
 * TODO: Field Annotations : (Annotion Targeting may not be trivial)
 */

fragment JAVA_LETTER        : '$' | '_' | ('A' .. 'Z') | ('a' .. 'z');
fragment JAVA_NUMBER        : '0' .. '9';
fragment JAVA_LETTER_NUMBER : JAVA_NUMBER | JAVA_LETTER ;
fragment SIGN : '-' | '+' ;
fragment BASE : '0x'|'0o'|'0b' ;
fragment ISUFFIX : 'L' ;
fragment FSUFFIX : 'f' | 'd' ;
fragment EXPONENT : 'e' | 'E' ;

WS               : [\p{White_Space}]+ -> skip;
COMMENT_BLOCK    : '/*' .*? '*/' -> skip;
COMMENT_LINE     : '//' .*? [\n\r]+ -> skip;

PRIMITIVE        : 'float' | 'double' | 'byte' | 'short' | 'int' | 'long' | 'char' | 'boolean';
OPERATOR         : '<' | '!' | '>' | '=' | '+' | '-' | '*' | '/' | '%' | '~' | '&' | '|' | '^';
MODIFIER         : 'final' | 'transient' | 'volatile' | 'synchronized';
PACKAGE          : 'package';
IMPORT           : 'import';
STATIC           : 'static';
EXTENDS          : 'extends';
SUPER            : 'super';
STRING           : '"' ('\\"'|.)*? '"';
CHAR             : '\'' .*? '\'';
JAVA_IDENTIFIER  : JAVA_LETTER JAVA_LETTER_NUMBER*;

INTEGRAL         : SIGN? BASE? JAVA_NUMBER+ ISUFFIX?;
FLOATING         : SIGN? JAVA_NUMBER+ ('.' JAVA_NUMBER+)? (EXPONENT SIGN? JAVA_NUMBER+)? FSUFFIX?;
BOOLVALUE        : 'true' | 'false';
NULLVALUE        : 'null';


document         : packageLine? importLine* unionLine* EOF;
packageLine      : PACKAGE packageName ';';
importLine       : IMPORT STATIC? packagePattern ';';
unionLine        : unionType '=' productType ('|' productType)* ';';

packageName      : identifier ('.' identifier )*;
packagePattern   : packageName '.*'?;
identifier       : JAVA_IDENTIFIER;

unionType        : annotation* identifier typeParameters?;
productType      : identifier '(' fields? ')';
// productType      : annotation* identifier '(' fields? ')';

fields           : fieldDeclaration (',' fieldDeclaration)* ','?;
fieldDeclaration : MODIFIER* typeName identifier ('=' fieldInit)?;
// fieldDeclaration : fieldModifier* typeName identifier ('=' fieldInit)?;
// fieldModifier    : MODIFIER | annotation;
fieldInit        : (arrayInit | expressionInit |.)+?;
expressionInit   : '(' (fieldInit|.)*? ')';
arrayInit        : '{' fieldInit (',' fieldInit)* ','? '}';

annotation         : '@' typeName ('(' annotationElements ')')?;
annotationElements : .*?;

typeParameters   : '<' typeParameter (',' typeParameter)* ','? '>';
typeParameter    : identifier typeBounds?;
typeBounds       : EXTENDS referenceType ('&' referenceType)*;

typeArguments    : '<' typeArgument (',' typeArgument)* ','? '>';
typeArgument     : ('?' ((EXTENDS | SUPER) referenceType)?) | referenceType;

typeName         : (primitiveType | referenceType) arraySuffix?;
referenceType    : typeDecl ('.' typeDecl )*;
primitiveType    : PRIMITIVE;
typeDecl         : identifier typeArguments?;
arraySuffix      : '[' ']';
