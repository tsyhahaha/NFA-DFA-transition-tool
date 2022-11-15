package main.DFA;

import main.NFA.NFAGraph;

import java.util.*;

public class DFAGenerator {
    private NFAGraph nfaGraph;
    private int nodeIndex = 0;
    private List<Node> dfaNodes = new ArrayList<>();
    private HashMap<Node, HashMap<String, Node>> dfaEdges = new HashMap<>();

    public DFAGenerator(NFAGraph nfaGraph) {
        this.nfaGraph = nfaGraph;
    }

    private List<String> getAllInput() {
        return this.nfaGraph.getAllInput();
    }

    /*
    求node中所有节点对于input的迁移
     */
    private Node getNextNode(Node node, String input) {
        List<Integer> resContent = new ArrayList<>();
        List<Integer> content = node.getContent();
        for (Integer dfaNode : content) {
            if (nfaGraph.getAllInput() == null ||
                    nfaGraph.getNextNodes(dfaNode, input) == null) {
                continue;
            }
            List<Integer> nextContent = nfaGraph.getNextNodes(dfaNode, input);
            for (Integer temp : nextContent) {
                List<Integer> temp_closure = nfaGraph.getClosure(temp);
                for(Integer temp_node: temp_closure) {
                    if (!resContent.contains(temp_node)) {
                        resContent.add(temp_node);
                    }
                }
            }
        }
        Node newNode = new Node("", resContent);
        if (resContent.size() == 0) {
            return null;
        }
        if (this.dfaNodes.contains(newNode)) {
            int index = dfaNodes.indexOf(newNode);
            return dfaNodes.get(index);
        } else {
            return new Node(genName(), resContent);
        }
    }

    private String genName() {
        return "Node" + nodeIndex++;
    }

    private void addEdge(Node node1, String input, Node node2) {
        if (!this.dfaEdges.containsKey(node1)) {
            this.dfaEdges.put(node1, new HashMap<>());
        }
        this.dfaEdges.get(node1).put(input, node2);
    }

    public DFAGraph run() {
        Integer start = nfaGraph.getStartNode();
        List<Integer> startContent = nfaGraph.getClosure(start);
        Node startNode = new Node(genName(), startContent);
        dfaNodes.add(startNode);

        Stack<Node> nodeUnprocessed = new Stack<>();
        nodeUnprocessed.push(startNode);
        while (nodeUnprocessed.size() > 0) {
            Node nowNode = nodeUnprocessed.pop();
            List<String> inputs = getAllInput();
            Node nextNode;
            for (String input : inputs) {
                nextNode = getNextNode(nowNode, input);
                if (nextNode == null) {
                    continue;   // 如果对所有的输入都没有迁移，则节点出度为0
                }
                addEdge(nowNode, input, nextNode);
                if (!dfaNodes.contains(nextNode)) {
                    dfaNodes.add(nextNode);
                    nodeUnprocessed.push(nextNode);
                }
            }
        }
        return new DFAGraph(dfaNodes, dfaEdges);
    }
}
