package ru.sberbank.edu;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/***
 * Менеджер текстового файла
 * при создании читает переданный файл в List
 */
public class FileManager implements Statistic {
    public List<String> getContentList() {
        return contentList;
    }

    public void setContentList(List<String> contentList) {
        this.contentList = contentList;
    }

    private String filename;
    private List<String> contentList = new ArrayList<>();

    public FileManager() {
    }
    public FileManager(String filename) {
        this.filename = filename;
        readTextFile();
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public void readTextFile() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));

            while (br.ready()) {
                String data = br.readLine();
                contentList.add(data);
            }
            br.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("Файл не существует.");
            throw new RuntimeException(e);
        }
        catch (IOException e) {
            System.out.println("Ошибка ввода вывода.");
            throw new RuntimeException(e);
        }
    }

    /***
     * Получить количестов строкй файла
     * @return
     */
    public int getLineCount() {
        return getContentList().size();
    }

    /***
     * Получить количестов пробелов в файле
     * @return
     */
    public int getSpaceCount() {
        int spaces = 0;
        for (String s:getContentList()) {
            spaces = spaces + (s.length() - s.replaceAll(" ", "").length());
        }
        return spaces;
    }

    /***
     * Найти самую длинную строку в файле
     * @return
     */
    public String getLongestLine() {
        String longestLine = "";
        for (String s:getContentList()) {
            longestLine = (longestLine.length()<s.length())?s:longestLine;
        }
        return longestLine;
    }

    /***
     * Сохранить статистику в файл статистики
     * @param lineCount
     * @param spaceCount
     * @param line
     */
    public void save(int lineCount, int spaceCount, String line) {

        String statFileName = filename.substring(0,filename.lastIndexOf("/")+1)+
                "statistic"+
                ( new SimpleDateFormat("yyyyMMddHHmm'.txt'").format(new Date()))
                +".txt";

        try {
            FileWriter writer = new FileWriter(statFileName);
            writer.write("Статистика" + System.getProperty("line.separator"));
            writer.write("Количество строк: " + lineCount + System.getProperty("line.separator"));
            writer.write("Количество пробелов: " + lineCount + System.getProperty("line.separator"));
            writer.write("Самая длинная строка: " + System.getProperty("line.separator"));
            writer.write(line + System.getProperty("line.separator"));
            writer.close();
        }
        catch (IOException e) {
            System.out.println("Ошибка ввода вывода.");
            throw new RuntimeException(e);
        }
    }
}
