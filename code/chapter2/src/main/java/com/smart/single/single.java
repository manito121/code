package com.smart.single;

public class single {

    private static single ins = new single();

    private single() {
    }


    public static single creatObj(){
        return ins;
    }

    public void show(){
        System.out.println("output");
    }
}
