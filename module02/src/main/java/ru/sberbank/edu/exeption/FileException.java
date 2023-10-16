package ru.sberbank.edu.exeption;



public class FileException extends RuntimeException {
     public FileException(){
        System.out.println("Could not find the file.");
         System.out.println("Perhaps the file does not exist or the path is specified incorrectly");
        printStackTrace();
    }
}
