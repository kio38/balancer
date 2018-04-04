package ru.isu.compmodels.imitation;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        // write your code here

        ArrayList<Server> list = getServers();
        MyBalancer myBalancer = new MyBalancer();
        myBalancer.setServerPool(list);

        ArrayList<Server> list2 = getServers();
        RandomBalancer rndBalancer = new RandomBalancer();
        rndBalancer.setServerPool(list2);


        ArrayList<Server> list3 = getServers();
        RoundRobinBalancer rrBalanser = new RoundRobinBalancer();
        rrBalanser.setServerPool(list3);

        ArrayList<Balancer> bList = new ArrayList<>();
        bList.add(myBalancer);
        bList.add(rndBalancer);
        bList.add((rrBalanser));
        Attach attach = new Attach();
        attach.setBalancer(bList);
        Thread d = new Thread(attach);
        d.start();

        Show show = new Show();
        show.setBalancer(bList);
        Thread a = new Thread(show);
        a.start();


    }

    public static ArrayList<Server> getServers() {
        DummyServer server1 = new DummyServer();
        server1.setPerformance(1000);
        DummyServer server2 = new DummyServer();
        server2.setPerformance(1500);
        DummyServer server3 = new DummyServer();
        server3.setPerformance(2000);

        ArrayList<Server> list = new ArrayList<Server>();
        list.add(server1);
        list.add(server2);
        list.add(server3);

        for (Server server : list) {
            Thread th = new Thread(server);
            th.start();
        }
        return list;

    }
}