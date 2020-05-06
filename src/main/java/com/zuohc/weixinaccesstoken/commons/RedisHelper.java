package com.zuohc.weixinaccesstoken.commons;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

@Component
public class RedisHelper {

    @Autowired
    private RedisTemplate<String,Object> template;

    /**
     * 指定缓存失效时间
     * @param key 键
     * @param time 失效时间
     * @param timeUnit 时间单位
     * @return
     */
    public boolean expire(String key,long time,TimeUnit timeUnit){
        try {
            if(time>0) {
                template.expire(key,time, timeUnit);
            }
            return true;
        }
        catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * 获取缓存的失效时间
     * @param key
     * @param timeUnit
     * @return
     */
    public long getExpire(String key,TimeUnit timeUnit){
        return template.getExpire(key,timeUnit);
    }

    /**
     * 判断key是否存在
     * @param key
     * @return
     */
    public boolean hasKey(String key){
        try {
            return  template.hasKey(key);
        }
        catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * 删除缓存
     * @param key
     */
    public void del(String... key){
        if(key!=null && key.length>0){
            if(key.length==1){
                template.delete(key[0]);
            }
            else {
                template.delete(CollectionUtils.arrayToList(key));
            }
        }
    }

    /**
     * string获取缓存
     * @param key
     * @return
     */
    public Object get(String key){
        return key==null?null:template.opsForValue().get(key);
    }

    /**
     * string放入缓存
     * @param key
     * @param obj
     * @return
     */
    public boolean set(String key,Object obj,long time,TimeUnit timeUnit){
        try {
            template.opsForValue().set(key,obj,time,timeUnit);
            return true;
        }
        catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
    }
}
