package com.example.design.facade;

public class Reader {
    private String fileName;

    public Reader(String fileName){
        this.fileName = fileName;
    }
    public void fileConnect(){
        String mag = String.format("Reader %s connect",fileName);
        System.out.println(mag);
    }
    public void fileRead(){
        String mag = String.format("Reader %s Read",fileName);
        System.out.println(mag);
    }
    public void fileDisconnect(){
        String mag = String.format("Reader %s Disconnect",fileName);
        System.out.println(mag);
    }
}
