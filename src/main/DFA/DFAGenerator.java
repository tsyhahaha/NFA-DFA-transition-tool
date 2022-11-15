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

    private List<String> getAllInput(List<Integer> nodes) {
        List<String> inputs = new ArrayList<>();
        for(Integer node: nodes) {
            List<String> tempInputs = nfaGraph.getAllInput(node);
            if(tempInputs == null) {
                continue;
            }
            for(String input: tempInputs) {
                if(!inputs.contains(input)) {
                    inputs.add(input);
                }
            }
        }
        return inputs;
    }

    private Node getNextNode(Node node, String input) {
        List<Integer> resContent = new ArrayList<>();
        List<Integer> content = node.getContent();
        for(Integer dfaNode: content) {
            if(nfaGraph.getAllInput(dfaNode) == null) {
                continue;
            }
            if(nfaGraph.getAllInput(dfaNode).contains(input)) {
                List<Integer> nextContent = nfaGraph.getNextNodes(dfaNode, input);
                for(Integer temp: nextContent) {
                    if(!resContent.contains(temp)) {
                        resContent.add(temp);
                    }
                }
            }
        }
        Node newNode = new Node("", resContent);
        if(this.dfaNodes.contains(newNode)) {
            int index = dfaNodes.indexOf(newNode);
            return dfaNodes.get(index);
        } else {
            return new Node(genName(), resContent);
        }
    }

    private String genName() {
        return "Node"+nodeIndex++;
    }

    private void addEdge(Node node1, String input, Node node2) {
        if(!this.dfaEdges.containsKey(node1)) {
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
        while(nodeUnprocessed.size() > 0) {
            Node nowNode = nodeUnprocessed.pop();
            List<Integer> content = nowNode.getContent();
            List<String> inputs = getAllInput(content);
            Node nextNode;
            for(String input: inputs) {
                if(input.equals("")) {
                    continue;
                }
//                System.out.println(nowNode + " " +input);
                nextNode = getNextNode(nowNode, input);
                addEdge(nowNode, input, nextNode);
                if(!dfaNodes.contains(nextNode)) {
                    dfaNodes.add(nextNode);
                    nodeUnprocessed.push(nextNode);
                }
            }
        }
        return new DFAGraph(dfaNodes, dfaEdges);
    }
}
