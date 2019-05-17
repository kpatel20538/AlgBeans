grammar AlgBeans;

@header{
package io.kpatel.algbeans.parser;
}

fragment JAVA_LETTER        : '$' | '_' | ('A' .. 'Z') | ('a' .. 'z');
fragment JAVA_LETTER_NUMBER : '0' .. '9' | JAVA_LETTER ;

WS               : [\p{White_Space}] -> skip;
PRIMITIVE        : 'float' | 'double' | 'byte' | 'short' | 'int' | 'long' | 'char' | 'boolean';
PACKAGE          : 'package';
IMPORT           : 'import';
STATIC           : 'static';
EXTENDS          : 'extends';
SUPER            : 'super';
GLOB             : '*';
JAVA_IDENTIFIER  : JAVA_LETTER JAVA_LETTER_NUMBER*;

document         : packageLine? importLine* unionLine* EOF;
packageLine      : PACKAGE packageName ';';
importLine       : IMPORT STATIC? packagePattern ';';
unionLine        : unionType '=' productType ('|' productType)* ';';

/*
 *  TODO: Class/Field Annotations
 */

unionType        : JAVA_IDENTIFIER typeParameters?;
productType      : JAVA_IDENTIFIER '(' fields? ')';

/*
 *  TODO: fieldInitializers
 */

fields    : fieldDeclaration (',' fieldDeclaration)*;
fieldDeclaration : typeName JAVA_IDENTIFIER;


packageName         : JAVA_IDENTIFIER ('.' JAVA_IDENTIFIER )*;
packagePattern      : packageName '.*'?;

typeParameters   : '<' typeParameter (',' typeParameter)* '>';
typeParameter    : typeVariable typeBounds?;
typeBounds       : EXTENDS referenceType ('&' referenceType)*;

typeName         : (PRIMITIVE | referenceType) arraySuffix?;
referenceType    : typeDecl ('.' typeDecl )*;
typeDecl         : JAVA_IDENTIFIER typeArguments?;
typeVariable     : JAVA_IDENTIFIER;
arraySuffix      : '[' ']';

typeArguments    : '<' typeArgument (',' typeArgument)* '>';
typeArgument     : referenceType | wildcard;
wildcard         : '?' wildcardBounds?;
wildcardBounds   : (EXTENDS referenceType) | (SUPER referenceType);


