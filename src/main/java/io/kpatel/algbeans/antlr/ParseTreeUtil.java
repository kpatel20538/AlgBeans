package io.kpatel.algbeans.antlr;

import io.kpatel.algbeans.util.StreamUtils;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ParseTreeUtil {
    public static String toString(ParseTree parseTree) {
        List<String> leaves = descantants(parseTree);
        List<String> symbols = Arrays.asList("+", "-", ">", "<");
        for (int i = leaves.size() - 1; i >= 1; i--) {
            if (isAnySymbolAdjacentAtIndex(leaves, i, symbols)){
                leaves.set(i-1, leaves.get(i-1) + leaves.get(i));
                leaves.set(i, "");
            }
        }
        return String.join(" ", leaves);
    }

    private static boolean isAnySymbolAdjacentAtIndex(List<String> list, int i, List<String> symbols) {
        for (String symbol : symbols) {
            if (list.get(i).startsWith(symbol) && list.get(i - 1).equals(symbol)) {
                return true;
            }
        }
        return false;
    }

    private static Stream<ParseTree> children(ParseTree node) {
        return StreamUtils.reversedRange(node::getChild, node::getChildCount);
    }

    private static List<String> descantants(ParseTree parseTree) {
        return StreamUtils.walk(ParseTreeUtil::children, () -> parseTree)
                .filter(node -> node instanceof TerminalNode)
                .map(ParseTree::getText)
                .collect(Collectors.toList());
    }
}
