package main.DFA;

import java.util.List;

public class Node {
    private String name;
    private List<Integer> content;

    public Node(String name, List<Integer> content) {
        this.content = content;
        this.name = name;
    }

    public List<Integer> getContent() {
        return content;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Node)) {
            return false;
        }
        List<Integer> objContent = ((Node) obj).getContent();
        int len = content.size();
        for(Integer node: objContent) {
            if(!content.contains(node)) {
                return false;
            }
        }
        return len == objContent.size();
    }

    @Override
    public String toString() {
        return name + ": " + content.toString();
    }

    public String getName() {
        return name;
    }
}
