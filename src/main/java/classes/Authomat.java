package classes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Authomat{
    private int[] symbols;
    private int[] states;
    private int startState;
    private int[] final_states;
    private  List<List<Integer>> transitions;
    private List<String> words = new ArrayList();
    public Authomat(int[] symbols, int[] states, int startState, int[] final_states,List<List<Integer>> transitions){
        this.symbols = symbols;
        this.states = states;
        this.startState = startState;
        this.final_states = final_states;
        this.transitions = transitions;
    }
    public List<String> getWords(int state, String word) {
        if (Arrays.stream(final_states).anyMatch(i -> i == state)) {
            words.add(word);
        }
        List<List<Integer>> depTransitions = new ArrayList<>();

        for (int i=0; i<transitions.size(); i++)
        {
            if (transitions.get(i).get(0) == state)
                depTransitions.add(transitions.get(i));
        }
        for (int i=0; i<depTransitions.size(); i++){
            getWords(depTransitions.get(i).get(2), word+depTransitions.get(i).get(1));
        }
        return this.words;
    }
}