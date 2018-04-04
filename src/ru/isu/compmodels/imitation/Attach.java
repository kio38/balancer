package ru.isu.compmodels.imitation;

import java.util.ArrayList;

public class Attach implements Runnable {
    boolean shouldStop=false;
    ArrayList<Balancer> balancers;
    int count=0;
    boolean isStart = true;

    public void setBalancer(ArrayList<Balancer>  list) {
        balancers=list;
    }


    @Override
    public void run() {
        while(!shouldStop){
            try {
                if (isStart){
                    Thread.sleep(2000);
                    isStart=false;
                }

                for (int i=0;i<=160;i++) {
                    SimpleRequest req = new SimpleRequest();
                    for (Balancer balancer:balancers) {
                        balancer.process(req);
                    }
                }
                count+=100;
                if (count>=5000) {
                    for (Balancer balancer:balancers) {
                        balancer.getServerStatus();
                    }
                    count=0;
                }

                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void shutDown() {
        shouldStop = true;
        //Будим, если он уснул по take на пустой очереди
        Thread.currentThread().interrupt();
    }
}
