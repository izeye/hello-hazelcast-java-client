package com.izeye.helloworld;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;

public class Main {

    public static void main(String[] args) {
        HazelcastInstance hz = HazelcastClient.newHazelcastClient();

        IMap map = hz.getMap("my-distributed-map");

        Object value = map.get("key");
        System.out.println(value);

        map.put("key", "value");
        value = map.get("key");
        System.out.println(value);

        map.putIfAbsent("somekey", "somevalue");
        value = map.get("somekey");
        System.out.println(value);

        map.replace("key", "value", "newvalue");
        value = map.get("key");
        System.out.println(value);

        hz.shutdown();
    }

}