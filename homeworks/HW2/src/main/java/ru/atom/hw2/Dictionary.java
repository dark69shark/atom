package ru.atom.hw2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Dictionary {
    private static final Logger log = LoggerFactory.getLogger(Dictionary.class);
    private int word;
    private String fileName;
    //private InputStreamReader dictStream;


    Dictionary(String fileName) {
        //this.dictStream = getFileFromResources(fileName);
        this.fileName = fileName;
        this.word = linesCounter();

    }


    private static InputStreamReader getFileFromResources(String fileName) {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();

        if (fileName == null) {
            throw new IllegalArgumentException("Set the fileName");
        } else {
            return new InputStreamReader(classLoader.getResourceAsStream(fileName));
        }

    }


    private int linesCounter() {
        int counter = 0;
        String line;
        BufferedReader br = new BufferedReader(getFileFromResources(fileName));

        try {
            br.mark(0);
            do {
                line = br.readLine();
                log.debug(line);
                counter++;
            } while (br.readLine() != null);
            br.close();
        } catch (FileNotFoundException e) {
            log.error("Dictionary FIle not found" + e);
        } catch (IOException e) {
            log.error("Some IO Exception" + e);
        }

        return counter;

    }


    String getWord() {
        int counter = 0;
        int rightBorder = this.word;
        String result = "";
        String line = "";
        BufferedReader br = new BufferedReader(getFileFromResources(fileName));
        try {
            int rnd = (int) (Math.random() *  (double) rightBorder);
            while (counter <= rnd) {
                line = br.readLine();
                counter++;
            }
            br.close();
            result = line;
            br.close();

        } catch (FileNotFoundException e) {
            log.error("Dictionary FIle not found" + e);
        } catch (IOException e) {
            log.error("Some IO Exception" + e);
        }
        return result;
    }
}
