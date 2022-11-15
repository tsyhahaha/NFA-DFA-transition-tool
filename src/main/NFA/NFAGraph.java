package main.NFA;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class NFAGraph {
    private Integer startNode = 1;  // 默认起始节点为 0 号节点
    private List<Integer> nfaNodes;
    private HashMap<Integer, HashMap<String, List<Integer>>> edges;

    public NFAGraph(List<Integer> nfaNodes, HashMap<Integer, HashMap<String, List<Integer>>> edges) {
        this.nfaNodes = nfaNodes;
        this.edges = edges;
    }

    public Integer getStartNode() {
        return startNode;
    }

    public List<Integer> getClosure(Integer node) {
        List<Integer> result = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        result.add(node);
        stack.push(node);
        while (stack.size() > 0) {
            Integer nowNode = stack.pop();
            if (!edges.containsKey(nowNode) ||
                    !edges.get(nowNode).containsKey("") ||
                    edges.get(nowNode).get("").size() == 0) {
                continue;
            }
            List<Integer> eps_nodes = edges.get(nowNode).get("");
            for (Integer eps_node : eps_nodes) {
                if (!result.contains(eps_node)) {
                    stack.push(eps_node);
                    result.add(eps_node);
                }
            }
        }
        return result;
    }

    public boolean hasEdges(Integer node) {
        return this.edges.containsKey(node) && this.edges.get(node).size() > 0;
    }

    public List<String> getAllInput(Integer node) {
        if(hasEdges(node)) {
            return new ArrayList<>(this.edges.get(node).keySet());
        }
        return null;
    }

    public List<Integer> getNextNodes(Integer node, String input) {
        return this.edges.get(node).get(input);
    }
}
