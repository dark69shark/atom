package ru.atom.hw2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.HashMap;
import java.util.Scanner;

public class BullsAndCows {
    private static final Logger log = LoggerFactory.getLogger(BullsAndCows.class);

    private static HashMap<String, Integer> countBullsAndCows(String input, String word) {
        HashMap<String, Integer> bc = new HashMap<String, Integer>();
        int bulls = 0;
        int cows = 0;

        for (int i = 0; i < input.length(); i++) {
            if (input.toCharArray()[i] == word.toCharArray()[i]) {
                bulls++;
            } else if (word.indexOf(input.toCharArray()[i]) > -1) {
                cows++;
            }
        }
        bc.put("Bulls", bulls);
        bc.put("Cows",cows);
        return bc;
    }

    public static void main(String[] args) {
        int exit = 0;
        int exitRound;
        Scanner console = new Scanner(System.in);
        String input ;
        Dictionary dict = new Dictionary("dictionary.txt");
        String word;
        HashMap bullsAndCows;

        System.out.println("Welcome To Game Bulls And Cows!");
        while (exit == 0) {
            word = dict.getWord();
            log.debug("Word: " + word);
            exitRound = 0;
            System.out.printf("I chose a word which has %s chars, try to guess%n", word.length());
            while (exitRound == 0) {
                try {
                    System.out.print("For EXIST type x\nYour try: ");
                    input = console.nextLine();
                    if (input.length() == 1 && input.toLowerCase().charAt(0) == 'x') {
                        System.out.println("See you soon!");
                        exit = 1;
                        exitRound = 1;
                    } else if (input != null && (input.length() == word.length())) {

                        System.out.println("Your input: " + input);

                        if (word.equals(input)) {
                            while (exitRound == 0) {
                                System.out.println("You're right! For play again press Y for Exit press X");
                                input = console.nextLine();
                                if (input.length() == 1 && input.toLowerCase().charAt(0) == 'y') {
                                    exitRound = 1;
                                } else if (input.length() == 1 && input.toLowerCase().charAt(0) == 'x') {
                                    exit = 1;
                                    exitRound = 1;
                                    System.out.println("See you soon!");
                                } else {
                                    continue;
                                }
                            }
                        } else {
                            bullsAndCows = countBullsAndCows(input, word);
                            System.out.printf("Bulls: %s  Cows: %s%n", bullsAndCows.get("Bulls"),
                                                                                            bullsAndCows.get("Cows"));

                        }
                    } else {
                        System.out.printf("Your input: %s and it has %s chars but you must input %s chars word%n",
                                                                                input, input.length(), word.length());
                        continue;
                    }

                } catch (Exception e) {
                    log.error(e.toString());
                }

            }
        }
    }
}