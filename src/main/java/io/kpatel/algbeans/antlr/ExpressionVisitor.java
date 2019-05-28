package io.kpatel.algbeans.antlr;

import io.kpatel.algbeans.util.StreamUtils;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ExpressionVisitor {
    public String toString(ParseTree parseTree) {
        return StreamUtils.walk(this::children, () -> parseTree)
                .filter(node -> node instanceof TerminalNode)
                .map(ParseTree::getText)
                .collect(Collectors.joining(" "));
    }

    private Stream<ParseTree> children(ParseTree node) {
        return StreamUtils.reversedRange(node::getChild, node::getChildCount);
    }
}
