package com.example.design;

import com.example.design.adapter.*;
import com.example.design.singleton.AClazz;
import com.example.design.singleton.BClazz;
import com.example.design.singleton.SocketClient;

public class DesignApplication {

    public static void main(String[] args){

        /*
        AClazz aClazz = new AClazz();
        BClazz bClazz = new BClazz();

        SocketClient aClient = aClazz.getSocketClient();
        SocketClient bClient = bClazz.getSocketClient();

        System.out.println("???");
        System.out.println(aClient.equals(bClient));
        */
        HairDryer hairDryer = new HairDryer();
        connect(hairDryer);

        Cleaner cleaner = new Cleaner();
        Electronic110V adapter = new SocketAdapter(cleaner);
        connect(adapter);

        AirConditioner airConditioner = new AirConditioner();
        Electronic110V airAdapter = new SocketAdapter(airConditioner);
        connect(airAdapter);
    }

    public static void connect(Electronic110V electronic110V){
        electronic110V.powerOn();
    }

}