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
import com.example.design.strategy.*;

import java.util.concurrent.atomic.AtomicLong;

public class DesignApplication {

    public static void main(String[] args){
        Encoder encoder = new Encoder();

        // base64
        EncodingStrategy base64 = new Base64Strategy();

        // normal
        EncodingStrategy normal = new NormalStrategy();

        String message = "java";

        encoder.setEncodingStrategy(base64);
        String base64Result = encoder.getMessage(message);
        System.out.println(base64Result);

        encoder.setEncodingStrategy(normal);
        String normalResult = encoder.getMessage(message);
        System.out.println(normalResult);

        encoder.setEncodingStrategy(new AppendStrategy());
        String appendResult = encoder.getMessage(message);
        System.out.println(appendResult);
    }

    public static void connect(Electronic110V electronic110V){
        electronic110V.powerOn();
    }

}
