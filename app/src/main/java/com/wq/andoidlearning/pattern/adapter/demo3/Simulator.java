package com.wq.andoidlearning.pattern.adapter.demo3;

public class Simulator {
    public static void simulate() {
        Client client1 = new Client();
        client1.addUpdateListener(new DstAdapterImpl());
        Client client2 = new Client();
        client2.addUpdateListener(new DstAdapterImpl());
        client1.getClass();
        client2.getClass();
    }
}
