package ru.isu.compmodels.imitation;

import com.oracle.tools.packager.Log;

import java.util.ArrayList;
import java.util.Random;

public class RandomBalancer implements Balancer {
        ArrayList<Server> servers;




        @Override
        public void setServerPool(ArrayList<Server> servers) {
        this.servers=servers;
    }

        @Override
        public ArrayList<Server> getServerPool() {
        return servers;
    }

        @Override
        public Server balance(Request request) {
            Random rnd = new Random();
            int r = rnd.nextInt(servers.size());

        return servers.get(r);
    }

        @Override
        public void process(Request request) {
        balance(request).process(request);
    }

    @Override
    public void getServerStatus() {

        System.out.println("---------------------------------------------------------");
        System.out.println("RandomBalancer");
        for (Server server:servers) {
            System.out.println(server.getCurrentLoad()+" "+server.getCurrentLoadUnits());

        }

        System.out.println("---------------------------------------------------------");
    }
    }