grammar AlgBeans;

@header{
package io.kpatel.algbeans.parser;
}

/*
 * TODO: Field Annotations : (Annotion Targeting may not be trivial)
 */

fragment JAVA_LETTER        : '_' | ('A' .. 'Z') | ('a' .. 'z');
fragment JAVA_NUMBER        : '0' .. '9';
fragment JAVA_LETTER_NUMBER : JAVA_NUMBER | JAVA_LETTER ;
fragment SIGN : '-' | '+' ;
fragment BASE : '0x'|'0o'|'0b' ;
fragment ISUFFIX : 'L' ;
fragment FSUFFIX : 'f' | 'd' ;
fragment EXPONENT : 'e' | 'E' ;
fragment DIGIT    : '_' | '0' .. '9';

WS               : [\p{White_Space}]+ -> skip;
COMMENT_BLOCK    : '/*' .*? '*/' -> skip;
COMMENT_LINE     : '//' .*? [\n\r]+ -> skip;

/*
OPERATOR         : '<<=' | '<<' | '<=' | '<' | '!=' | '!' | '>>>=' | '>>>' | '>>=' | '>>' | '>=' | '>'
                 | '==' | '=' | '++' | '+=' | '+' | '--' | '-=' | '-' | '*=' | '*' | '/=' | '/' | '%='
                 | '%' | '~' | '&&' | '&' | '&=' | '|=' | '||'  | '|' | '^=' | '^' | ':' | '?'; */
PRIMITIVE        : 'float' | 'double' | 'byte' | 'short' | 'int' | 'long' | 'char' | 'boolean';
MODIFIER         : 'transient' | 'volatile' | 'synchronized';
FINAL            : 'final';
PACKAGE          : 'package';
IMPORT           : 'import';
STATIC           : 'static';
EXTENDS          : 'extends';
SUPER            : 'super';
THIS             : 'this';
VOID             : 'void' ;
CLASS            : 'class';
BOOLVALUE : 'true' | 'false';
NULLVALUE : 'null';
CHAR : '\'' .*? '\'';
STRING : '"' ('\\"'|.)*? '"';
JAVA_IDENTIFIER  : JAVA_LETTER JAVA_LETTER_NUMBER*;
INTEGRAL         : SIGN? BASE? DIGIT+ ISUFFIX?;
FLOATING         : SIGN? DIGIT+ ('.' DIGIT+)? (EXPONENT SIGN? DIGIT+)? FSUFFIX?;



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
fieldDeclaration : (FINAL | MODIFIER)* typeName identifier ('=' fieldInit)?;
// fieldDeclaration : fieldModifier* typeName identifier ('=' fieldInit)?;
// fieldModifier    : MODIFIER | annotation;
fieldInit        :  arrayInitializer | expression;
arrayInitializer : '{' fieldInit (',' fieldInit)* ','? '}';
expression       : ((expressionName | primary) ('=' | '*=' | '/=' | '%=' | '+=' | '-=' | '<<=' | '>>=' | '>>>=' | '&=' | '^=' | '|='))* (lambdaExpression | conditionalExpression);
lambdaExpression : lambdaParameters '->' lambdaBody;
lambdaParameters : '(' formalParameterList? ')' | '(' identifier (',' identifier)* ')' | identifier;
formalParameterList : (receiverParameter ',') ? formalParameter (',' formalParameter)* (',' varargParameter);
receiverParameter  : annotation* typeName (identifier '.')? THIS;
varargParameter    : variableModifier* typeName annotation* '...' variableDeclaratorId;
formalParameter    : variableModifier* typeName variableDeclaratorId;
variableModifier   : annotation | FINAL;
variableDeclaratorId : identifier dims;
dims : (annotation* '[' ']')+;
dimExpr : annotation* '[' expression ']';
lambdaBody : expression | block;
block : '{' (block|.)*? '}';
expressionName : identifier ('.' identifier)*;
conditionalExpression : conditionalOrExpression ('?' expression ':' (conditionalExpression | lambdaExpression) )*;
conditionalOrExpression : conditionalAndExpression ('||' conditionalAndExpression)*;
conditionalAndExpression : inclusiveOrExpression ('&&' inclusiveOrExpression)*;
inclusiveOrExpression : exclusiveOrExpression ('|' exclusiveOrExpression)*;
exclusiveOrExpression : andExpression ('^' andExpression)*;
andExpression : equalityExpression ('&' equalityExpression)*;
equalityExpression : relationalExpression ( ('==' | '!=') relationalExpression)*;
relationalExpression : shiftExpression ( ( '<=' | '>=' |'<' | '>' | 'instanceof' ) shiftExpression)*;
shiftExpression : additiveExpression ( ('<' '<' | '>' '>' | '>' '>' '>') additiveExpression)*;
additiveExpression : multiplicativeExpression ( ('+' | '-') multiplicativeExpression)*;
multiplicativeExpression : unaryExpression ( ('*' | '/' | '%') unaryExpression)*;
unaryExpression : ('+' | '-')* unaryNotPMExpression;
unaryNotPMExpression : ('~' | '!')* (postfixExpression | castExpression);
postfixExpression : (expressionName | primary) ('+' '+' | '-' '-')*;
castExpression : '(' typeName ('&' typeName)* ')' (unaryNotPMExpression | lambdaExpression);
primary : (literal | typeName | keywordLiterals | ('(' expression ')') | methodInvocationSuffix | classInstanceCreationExpressionSuffix | ('new' typeName ((dimExpr+ dims*) | (dims+ arrayInitializer)) ))
           ( ('.' keywordLiterals) | fieldAccessSuffix | arrayAccessSuffix | ('.' typeArguments? methodInvocationSuffix) | methodReferenceSuffix | classInstanceCreationExpressionSuffix)*;
classInstanceCreationExpressionSuffix : 'new' typeArguments? annotation* identifier ('.' annotation* identifier)* typeArguments? block?;
fieldAccessSuffix : '.' identifier;
arrayAccessSuffix : '[' expression ']';
methodInvocationSuffix : identifier '(' (expression (',' expression)*)? ')';
methodReferenceSuffix : '::' typeArguments? ('new' | identifier);
literal : INTEGRAL | FLOATING | BOOLVALUE  | STRING | CHAR | NULLVALUE;

keywordLiterals : THIS | SUPER | VOID | CLASS;

annotation       : '@' typeName ('(' (elementValue | (elementPair (',' elementPair)*))? ')')?;
elementPair      : identifier '=' elementValue;
elementValue     : conditionalExpression |  elementValueArrayInitializer | annotation;
elementValueArrayInitializer :  '{' (elementValue (',' elementValue)*)? ','? '}' ;

typeParameters   : '<' typeParameter (',' typeParameter)* '>';
typeParameter    : identifier typeBounds?;
typeBounds       : EXTENDS referenceType ('&' referenceType)*;

typeArguments    : '<' typeArgument (',' typeArgument)* '>';
typeArgument     : referenceType | ('?' ((EXTENDS | SUPER) referenceType)?);


typeName         : (primitiveType | referenceType) arraySuffix?;
referenceType    : typeDecl ('.' typeDecl )* typeArguments?;
primitiveType    : PRIMITIVE;
typeDecl         : identifier typeArguments?;
arraySuffix      : '[' ']';
