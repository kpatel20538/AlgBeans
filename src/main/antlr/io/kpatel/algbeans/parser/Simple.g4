grammar Simple;

@header {
    package io.kpatel.algbeans.parser;
}

document: 'hello ' TEXT;
TEXT: LETTER+;
fragment LETTER: [A-Za-z0-9];

