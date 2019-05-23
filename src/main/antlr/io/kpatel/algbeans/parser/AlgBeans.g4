grammar AlgBeans;

@header{
package io.kpatel.algbeans.parser;
}

/*
 * TODO: Class/Field Annotations
 * TODO: fieldInitializers (Requires Expressions)
 */

fragment JAVA_LETTER        : '$' | '_' | ('A' .. 'Z') | ('a' .. 'z');
fragment JAVA_LETTER_NUMBER : '0' .. '9' | JAVA_LETTER ;

WS               : [\p{White_Space}]+ -> skip;
COMMENT_BLOCK    : '/*' .*? '*/' -> skip;
COMMENT_LINE     : '//' .*? [\n\r]+ -> skip;

PRIMITIVE        : 'float' | 'double' | 'byte' | 'short' | 'int' | 'long' | 'char' | 'boolean';
MODIFIER         : 'final' | 'transient' | 'volatile' | 'synchronized';
PACKAGE          : 'package';
IMPORT           : 'import';
STATIC           : 'static';
EXTENDS          : 'extends';
SUPER            : 'super';
JAVA_IDENTIFIER  : JAVA_LETTER JAVA_LETTER_NUMBER*;


document         : packageLine? importLine* unionLine* EOF;
packageLine      : PACKAGE packageName ';';
importLine       : IMPORT STATIC? packagePattern ';';
unionLine        : unionType '=' productType ('|' productType)* ';';

packageName      : identifier ('.' identifier )*;
packagePattern   : packageName '.*'?;
identifier       : JAVA_IDENTIFIER;

unionType        : identifier typeParameters?;
productType      : identifier '(' fields? ')';
// unionType        : annotation* identifier typeParameters?;
// productType      : annotation* identifier '(' fields? ')';

fields           : fieldDeclaration (',' fieldDeclaration)* ','?;
fieldDeclaration : MODIFIER* typeName identifier;
// fieldDeclaration : fieldModifier* typeName identifier ('=' fieldInit)?;
// fieldModifier    : MODIFIER | annotation;
// fieldInit        : (arrayInit | expressionInit | .)*?
// expressionInit   : '(' (fieldInit|.)*? ')'
// arrayInit        : '{' fieldInit (',' fieldInit)* ','? '}'

// annotation         : '@' typeName annotationElements?
// annotationElements : ( '(' .*? ')' )?

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
