package ru.isu.compmodels.imitation;

import java.util.ArrayList;
import java.util.Random;

public class RoundRobinBalancer implements Balancer {
    ArrayList<Server> servers;
    int sizeServer;
    int countserver=0;




    @Override
    public void setServerPool(ArrayList<Server> servers) {
        this.servers=servers;
        sizeServer=servers.size();
    }

    @Override
    public ArrayList<Server> getServerPool() {
        return servers;
    }

    @Override
    public Server balance(Request request) {
        if (countserver==sizeServer){
            countserver=0;
        }
        countserver++;
        return servers.get(countserver-1);
    }

    @Override
    public void process(Request request) {
        balance(request).process(request);
    }

    @Override
    public void getServerStatus() {

        System.out.println("---------------------------------------------------------");
        System.out.println("RobinRound");
        for (Server server:servers) {
            System.out.println(server.getCurrentLoad()+" "+server.getCurrentLoadUnits());

        }

        System.out.println("---------------------------------------------------------");
    }
}