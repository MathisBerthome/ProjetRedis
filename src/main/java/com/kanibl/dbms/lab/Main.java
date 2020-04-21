package com.kanibl.dbms.lab;

import java.util.Map;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisShardInfo;

public class Main {

    public static void main(String[] args) throws Exception {
    	
    	Jedis jedis = null;
    	
    	String Host = "redis-12402.c9.us-east-1-2.ec2.cloud.redislabs.com";
		int Port = 12402;
		String Password = "nantes";
		String key = "1A";
    	
		JedisShardInfo shardInfo = new JedisShardInfo(Host, Port);
		shardInfo.setPassword(Password);
		jedis = new Jedis(shardInfo);
		
		try {
    		
    		jedis.connect();
    		System.out.println("Connection Success");
    		
    		jedis.hset(key, "Prenom", "John");
    		jedis.hset(key, "Nom", "Doe");
    		if(jedis.exists(key)) {
    			Map<String, String> map = jedis.hgetAll(key);
    			for(Map.Entry<String, String> entry : map.entrySet()) {
    				System.out.println(entry.getKey() + " = " + entry.getValue());
    			}
    		} else {
    			System.out.println("Error");
    		}
    	} catch (Exception e) {
    		//affichage Exception
    		System.out.println(e);
    	}
    	if(jedis != null) {
    		//fermeture de la connexion
    		jedis.close();
    	}

    }

}
