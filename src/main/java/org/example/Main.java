package org.example;
import classes.Authomat;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        File file = new File("src/main/resources/test.txt");
        String content = "";
        try {
             content = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        List<String> row = Arrays.asList(content.split("\r\n"));
        int[] symbols = new int[Integer.parseInt(row.get(0))];
        for (int i=1; i<=symbols.length; i++){
            symbols[i-1]=i;
        }
        int[] states = new int[Integer.parseInt(row.get(1))];
        for (int i=0; i<states.length; i++){
            states[i]=i;
        }
        int startState = Integer.parseInt(row.get(2));
        //2 2 3
        int[] arr = Arrays.stream(row.get(3).split(" ")).mapToInt(Integer::parseInt)
                .toArray();
        int[] final_states = new int[arr[0]];
        for (int i=1; i<=final_states.length; i++) {
            final_states[i-1] = arr[i];
        }
        List<List<Integer>> transitions = new ArrayList<>();
        for (int i=4; i<row.size(); i++){
            transitions.add(Arrays.stream(row.get(i).split(" "))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList()));
        }

        Authomat authomat = new Authomat(symbols, states, startState, final_states, transitions);

        List<String> words = authomat.getWords(startState, "");
        for (int i=0; i<words.size(); i++){
            System.out.println(words.get(i)+"\n");
        }
    }

}

