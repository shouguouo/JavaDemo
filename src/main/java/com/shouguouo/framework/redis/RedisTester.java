package com.shouguouo.framework.redis;

import com.alibaba.fastjson.JSON;
import com.shouguouo.tools.utils.Const;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisShardInfo;

/**
 * Created by shouguouo~ on 2018/9/2.
 */
public class RedisTester {
    private static final byte[] LIST = "extractList".getBytes();

    public static void main(String[] args) {

        JedisShardInfo jedisShardInfo = new JedisShardInfo(Const.LINUXIP, 6379);
        jedisShardInfo.setPassword("123456");
        Jedis jedis = new Jedis(jedisShardInfo);
        int i = 0;
        try {
            long start = System.currentTimeMillis(); // 开始毫秒数
            Task task = new Task("估值6.0", "估值6.0接口库", 1);


            jedis.lpush(LIST, JSON.toJSONBytes(task));
            task = new Task(null, null, null);
            jedis.lpush(LIST, JSON.toJSONBytes(task));

            task = new Task("估值6.0", null, 1);
            jedis.lpush(LIST, JSON.toJSONBytes(task));

            task = new Task("估值6.0", "hello", 1);

            jedis.lpush(LIST, JSON.toJSONBytes(task));
            jedis.lpush(LIST, JSON.toJSONBytes(new Task()));

            jedis.lpush(LIST, JSON.toJSONBytes(null));
        } finally { // 关闭连接
            jedis.close();
        }
        // 打印1秒内对Redis的操作次数
        System.out.println("redis每秒操作：" + i + "次");
    }

}
