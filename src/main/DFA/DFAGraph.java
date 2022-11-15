package main.DFA;

import java.util.HashMap;
import java.util.List;

public class DFAGraph {
    private List<Node> nodes;
    private HashMap<Node, HashMap<String, Node>> edges;

    public DFAGraph(List<Node> nodes, HashMap<Node, HashMap<String, Node>> edges) {
        this.nodes = nodes;
        this.edges = edges;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("------------------------Nodes-----------------------\n");
        for (int i = 0; i < nodes.size(); i++) {
            sb.append(nodes.get(i).toString()).append("\n");
        }
        sb.append("------------------------Edges-----------------------\n");
        for (int i = 0; i < nodes.size(); i++) {
            Node nowNode = nodes.get(i);
            if (edges.containsKey(nowNode)) {
                for (String input : edges.get(nowNode).keySet()) {
                    sb.append(nowNode.getName())
                            .append(" ").append(input).append(" ")
                            .append(edges.get(nowNode).get(input).getName());
                    sb.append("\n");
                }
            }
        }
        return sb.toString();
    }

}
