package ru.sberbank.edu.iofile;

import ru.sberbank.edu.exeption.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReaderFile {

    List<String> list = new ArrayList<>();

    /***
     * readFile reads data from a file and puts it in a collection.
     */
    public void readFile(File file) {
        try (FileReader reader = new FileReader(file);
             BufferedReader bufferedReader = new BufferedReader(reader)) {
            String line;
            while (bufferedReader.ready()) {
                line = bufferedReader.readLine();
                list.add(line);
            }

        } catch (IOException e) {
            throw new FileException();
        }
    }

}
