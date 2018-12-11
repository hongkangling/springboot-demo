package com.example.springboot.redis;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.util.concurrent.TimeUnit;

/**
 * @author linghongkang
 * @description: 分布式锁获取释放
 * @create: 2018-12-10 10:33
 **/
public class RedissonTest {
    public static void main(String[] args) {
        Config config = new Config();
//        // 哨兵模式
        config.useSentinelServers().addSentinelAddress( "redis://127.0.0.1:6379")
                .setMasterName("masterName")
                .setPassword("password").setDatabase(0);
        // 集群模式
//        config.useClusterServers().addNodeAddress("redis://127.0.0.1:6369", "redis://127.0.0.1:6379", "redis://127.0.0.1:6389")
//                .setPassword("123456").setScanInterval(1000);
        RedissonClient redissonClient = Redisson.create(config);

        // 设置锁的重要性是 锁value值的唯一性。 通过线程Id和UUID 保证唯一性。
        RLock redLock = redissonClient.getLock("REDLOCK_KEY");


        boolean isBlock;
        try {
            // 使用默认租约时间  默认30sL
            isBlock=redLock.tryLock();

            // 500ms 获取不到锁就失败  10s 锁过期时间
            isBlock = redLock.tryLock(500, 10000,TimeUnit.MILLISECONDS);
            if(isBlock){
                //TODO if get lock success, do something;
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
//            释放锁 无论锁是否获取成功。
            redLock.unlock();
        }



    }

}
