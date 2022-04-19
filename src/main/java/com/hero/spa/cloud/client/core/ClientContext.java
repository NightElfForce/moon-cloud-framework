package com.hero.spa.cloud.client.core;


import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author yx
 */
public class ClientContext {
    private static Map<String,List<ClientInfo>> clients = new ConcurrentHashMap<>(32);

    public static Map<String,List<ClientInfo>> getClients() {
        return clients;
    }

    public static List<ClientInfo> getClient(String name) {
        return clients.get(name);
    }
}
