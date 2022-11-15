package main.NFA;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class NFAGenerator {
    private String inputFile;
    private List<Integer> nfaNodes;
    private HashMap<Integer, HashMap<String, List<Integer>>> edges;

    public NFAGenerator(String inputFile) {
        this.inputFile = inputFile;
        this.nfaNodes = new ArrayList<>();
        this.edges = new HashMap<>();
    }

    private void setNode(int nodeNum) {
        for(int i=1; i<=nodeNum; i++) {
            nfaNodes.add(i);
        }
    }

    private void addEdge(int node1, String s, int node2) {
        if(!this.edges.containsKey(node1)) {
            this.edges.put(node1, new HashMap<>());
        }
        if(!this.edges.get(node1).containsKey(s)) {
            this.edges.get(node1).put(s, new ArrayList<>());
        }
        this.edges.get(node1).get(s).add(node2);
    }

    public NFAGraph run() throws FileNotFoundException {
        File file = new File(inputFile);
        Scanner scanner = new Scanner(file);
        List<String> inputs = new ArrayList<>();
        int num = Integer.parseInt(scanner.nextLine());
        this.setNode(num);
        while(scanner.hasNext()) {
            String line = scanner.nextLine();
            String[] s = line.split(" ");
            int node1 = Integer.parseInt(s[0]);
            int node2 = Integer.parseInt(s[s.length-1]);
            if(s.length == 2) {
                this.addEdge(node1, "", node2);
            } else {
                String temp = s[1];
                inputs.add(temp);
                this.addEdge(node1, temp, node2);
            }
        }
        return new NFAGraph(nfaNodes, edges, inputs);
    }
}
