package ru.isu.compmodels.imitation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MyBalancer implements Balancer
{
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
        int currentServer = 0;
        double cServer;
        double server;
            cServer = servers.get(0).getCurrentLoad();
            for (int i = 1; i < servers.size(); i++) {
                server=(int) servers.get(i).getCurrentLoad();
                if (server<cServer){
                    cServer=server;
                    currentServer=i;
                }
            }
        return servers.get(currentServer);
    }

    @Override
    public void process(Request request) {
        balance(request).process(request);
    }

    @Override
    public void getServerStatus() {

        System.out.println("---------------------------------------------------------");
        System.out.println("myBalancer");
        for (Server server:servers) {
            System.out.println(server.getCurrentLoad()+" "+server.getCurrentLoadUnits());

        }

        System.out.println("---------------------------------------------------------");
    }
}
