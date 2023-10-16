package ru.sberbank.edu.exeption;

public class CreateFileException extends RuntimeException{
    public CreateFileException(){
        System.out.println("An error occurred, the file could not be created");
        printStackTrace();
    }
}
