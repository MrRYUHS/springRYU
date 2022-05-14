package com.example.design;

import com.example.design.adapter.*;
import com.example.design.aop.AopBrowser;
import com.example.design.decorator.*;
import com.example.design.facade.Ftp;
import com.example.design.facade.Reader;
import com.example.design.facade.SftpClient;
import com.example.design.facade.Writer;
import com.example.design.observer.Button;
import com.example.design.observer.IButtonListener;
import com.example.design.proxy.Browser;
import com.example.design.proxy.BrowserProxy;
import com.example.design.proxy.IBrowser;
import com.example.design.singleton.AClazz;
import com.example.design.singleton.BClazz;
import com.example.design.singleton.SocketClient;

import java.util.concurrent.atomic.AtomicLong;

public class DesignApplication {

    public static void main(String[] args){
        Ftp ftpclient = new Ftp("www.foo.co.kr",22,"/home/etc");
        ftpclient.connect();
        ftpclient.moveDirectory();

        Writer writer = new Writer("text.tmp");
        writer.fileConnect();
        writer.write();

        Reader reader = new Reader("text.tmp");
        reader.fileConnect();
        reader.fileRead();

        reader.fileDisconnect();
        writer.fileDisconnect();
        ftpclient.disConnect();

        SftpClient sftpClient = new SftpClient("www.foo.co.kr",22,"/home/etc","text.tmp");
        sftpClient.connect();
        sftpClient.write();
        sftpClient.read();
        sftpClient.disConnect();
    }

    public static void connect(Electronic110V electronic110V){
        electronic110V.powerOn();
    }

}
