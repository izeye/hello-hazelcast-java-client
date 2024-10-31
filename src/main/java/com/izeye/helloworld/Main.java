package com.izeye.helloworld;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import io.micrometer.core.instrument.binder.cache.HazelcastCacheMetrics;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;

public class Main {

    public static void main(String[] args) {
        SimpleMeterRegistry registry = new SimpleMeterRegistry();

        HazelcastInstance hz = HazelcastClient.newHazelcastClient();

        IMap map = hz.getMap("my-distributed-map");

        HazelcastCacheMetrics.monitor(registry, map);

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

        registry.getMeters().forEach((meter) -> System.out.println(meter.getId() + ": " + meter.measure()));
    }

}