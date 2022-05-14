package com.example.design.facade;

public class Writer {
    private String fileName;

    public Writer(String fileName){
        this.fileName = fileName;
    }
    public void fileConnect(){
        String mag = String.format("Writer %s connect",fileName);
        System.out.println(mag);
    }
    public void fileDisconnect(){
        String mag = String.format("Writer %s read",fileName);
        System.out.println(mag);
    }
    public void write(){
        String mag = String.format("Writer %s Write",fileName);
        System.out.println(mag);
    }
}
