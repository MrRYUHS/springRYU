package com.example.design;

import com.example.design.adapter.*;
import com.example.design.aop.AopBrowser;
import com.example.design.decorator.*;
import com.example.design.proxy.Browser;
import com.example.design.proxy.BrowserProxy;
import com.example.design.proxy.IBrowser;
import com.example.design.singleton.AClazz;
import com.example.design.singleton.BClazz;
import com.example.design.singleton.SocketClient;

import java.util.concurrent.atomic.AtomicLong;

public class DesignApplication {

    public static void main(String[] args){
        ICar audi = new Audi(1000);
        audi.showPrice();

        // a3
        ICar audi3 = new A3(audi, "A3");
        audi3.showPrice();

        // a4
        ICar audi4 = new A4(audi, "A4");
        audi4.showPrice();

        // a5
        ICar audi5 = new A5(audi, "A5");
        audi5.showPrice();
    }

    public static void connect(Electronic110V electronic110V){
        electronic110V.powerOn();
    }

}
