package com.example.design;

import com.example.design.adapter.*;
import com.example.design.aop.AopBrowser;
import com.example.design.proxy.Browser;
import com.example.design.proxy.BrowserProxy;
import com.example.design.proxy.IBrowser;
import com.example.design.singleton.AClazz;
import com.example.design.singleton.BClazz;
import com.example.design.singleton.SocketClient;

import java.util.concurrent.atomic.AtomicLong;

public class DesignApplication {

    public static void main(String[] args){
        /*
        Browser browser = new Browser("www.naver.com");
        browser.show();
        browser.show();
        browser.show();
        browser.show();

        IBrowser browser = new BrowserProxy("www.naver.com");
        browser.show();
        browser.show();
        browser.show();
        browser.show();
        browser.show();
        */

        AtomicLong start = new AtomicLong();
        AtomicLong end = new AtomicLong();

        IBrowser aopBrowser = new AopBrowser("www.naver.com",
                ()->{
                    System.out.println("before");
                    start.set(System.currentTimeMillis());
                },
                ()->{
                    long now = System.currentTimeMillis();
                    end.set(now - start.get());
                }
        );
        aopBrowser.show();
        System.out.println("loading time : "+end.get());

        aopBrowser.show();
        System.out.println("loading time : "+end.get());
    }

    public static void connect(Electronic110V electronic110V){
        electronic110V.powerOn();
    }

}