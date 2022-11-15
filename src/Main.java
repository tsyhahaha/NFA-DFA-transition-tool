import main.DFA.DFAGenerator;
import main.DFA.DFAGraph;
import main.NFA.NFAGenerator;
import main.NFA.NFAGraph;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        String inputFile = "E:\\大三上高工\\编译技术\\NFA2DFA\\src\\test.txt";  // your file place
        NFAGenerator nfaGenerator = new NFAGenerator(inputFile);
        NFAGraph nfaGraph = nfaGenerator.run();
        System.out.println("NFA Graph generated!");
        DFAGenerator dfaGenerator = new DFAGenerator(nfaGraph);
        System.out.println("DFA Graph begin to generate...");
        DFAGraph dfaGraph = dfaGenerator.run();
        System.out.println("DFA Graph generated!");
        System.out.println(dfaGraph.toString());
    }
}