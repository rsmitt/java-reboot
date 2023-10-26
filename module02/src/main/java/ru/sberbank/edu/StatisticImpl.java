package ru.sberbank.edu;

import java.io.*;

public class StatisticImpl implements Statistic{
    private int spaceCount;
    private int lineCount;
    private String longestLine;
    private static char SPACE = ' ';
    public void start() throws IOException {

        File inputFile = new File("input.txt");
        BufferedReader inputReader;
        FileWriter basicWriter;

        if(inputFile.exists()) {
            inputReader = new BufferedReader(new FileReader("input.txt"));
        }
        else{
            basicWriter = new FileWriter("input.txt");
            basicWriter.append("Мы добавляем первую базовую строку" + "\n");
            basicWriter.append("Мы добавляем вторую базовую строку" + "\n");
            basicWriter.append("Мы добавляем третью базовую строку, но чтуь чуть подлинее" + "\n");

            basicWriter.close();

            inputReader = new BufferedReader(new FileReader("input.txt"));
        };


        /***
         * Получили файл на вход, теперь читаем его
         */
        String line;
        while( (line = inputReader.readLine()) != null){
            setLineCount();
            if(line.length() > getLongestLine().length()){
                setLongestLine(line);
            }
            char[] charArray = line.toCharArray();
            for(int i = 0; i < line.length(); i++){

                if(charArray[i] == SPACE){
                    setSpaceCount();
                }
            }
        }

        save(getLinesCount(),getSpaceCount(),getLongestLine());
    }


    @Override
    public int getSpaceCount() {
        return this.spaceCount;
    }

    @Override
    public String getLongestLine() {
        if(this.longestLine == null){
            return "";
        }
        else
            return this.longestLine;
    }

    @Override
    public int getLinesCount() {
        return lineCount;
    }

    @Override
    public void save(int lineCount, int spaceCount, String line) throws IOException {
        FileWriter outputFile = new FileWriter("output.txt");
        outputFile.append("В данном файле строк " + lineCount + "\n");
        outputFile.append("В данном файле пробелов " + spaceCount + "\n");
        outputFile.append("Самая длинная строка в файле '" + line + "'" + "\n");
        outputFile.close();

    }

    @Override
    public void setLineCount() {
        this.lineCount++;
    }

    @Override
    public void setSpaceCount() {
        this.spaceCount++;
    }

    @Override
    public void setLongestLine(String longestLine) {
        this.longestLine = longestLine;
    }
}