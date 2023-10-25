package ru.sberbank.edu;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public abstract class StatisticsImpl implements Statistic {

    private String file_path;
    private File file;
    private Reader reader;
    private BufferedReader bufferedReader;

   public void setFile_path(String file_path ){
       this.file_path = file_path;
   }

   public String getFile_path() {
       return file_path;
   }

   public StatisticsImpl(String file_path) {
       setFile_path(file_path);
   }

   private void setup_file_system() throws FileNotFoundException {
       file = new File(getFile_path());
       reader = new FileReader(file);
       bufferedReader = new BufferedReader(reader);
   }

   private void close() throws IOException {
       reader.close();
       bufferedReader.close();
   }

    @Override
    public int getLineCount() throws IOException {
        setup_file_system();
        int lines_count = 0;
        while (bufferedReader.readLine() != null) {
            lines_count += 1;
        }
        close();
        return lines_count;
    }

    @Override
    public int getSpaceCount() throws IOException {
        setup_file_system();
        int space_count = 0;
        int symbol;
        while ((symbol = reader.read()) != -1) {
            if (((char) symbol) == ' ') {
                space_count += 1;
            }
        }
        close();
        return space_count;
    }

    @Override
    public List<List<String>> getLongestLine() throws IOException {
        setup_file_system();
        String line;
        List<List<String>> output_list = new ArrayList<>();
        int max_line_length = 0;
        while ((line = bufferedReader.readLine()) != null) {
            if (line.length() > max_line_length) {
                output_list = new ArrayList<>();
                output_list.add(List.of(line));
                max_line_length = line.length();
            } else if (line.length() == max_line_length) {
                output_list.add(List.of(line));
            }
        }
        close();
        return output_list;
    }

    @Override
    public abstract void save() throws IOException;
}
