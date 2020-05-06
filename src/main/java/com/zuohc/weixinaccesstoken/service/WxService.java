package com.zuohc.weixinaccesstoken.service;

import com.sun.deploy.net.HttpUtils;
import com.zuohc.weixinaccesstoken.commons.HttpHelper;
import com.zuohc.weixinaccesstoken.commons.JsonHelper;
import com.zuohc.weixinaccesstoken.commons.RedisHelper;
import com.zuohc.weixinaccesstoken.model.AccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.SocketTimeoutException;
import java.util.concurrent.TimeUnit;


/**
 * 微信服务
 */
@Service
public class WxService {

    @Value("${weixinaccesstoken.appid}")
    private String appid;
    @Value("${weixinaccesstoken.appsecret}")
    private String appsecret;
    @Value("${weixinaccesstoken.sleep-time}")
    private String sleepTime;
    @Value("${weixinaccesstoken.wx-access-token-url}")
    private String wxAccessTokenUrl;
    @Value("${weixinaccesstoken.wx_ticket-url}")
    private String wxTicketUrl;
    @Value("${weixinaccesstoken.wx-access-token-key}")
    private String wxAccessTokenKey;
    @Value("${weixinaccesstoken.wx_ticket-key}")
    private String wxTicketKey;

    @Autowired
    private RedisHelper redisHelper;
    @Autowired
    private HttpHelper httpHelper;

    /**
     * 作业入口
     */
    public void doWork() {
        System.out.println("微信AccessToken自动作业");

        while (true) {
            getAccessToken();

            getTicket();

            try {
                System.out.println("暂停服务...Time:[" + sleepTime + "]");
                Thread.sleep(Long.parseLong(sleepTime));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取微信AccessToken
     */
    private void getAccessToken() {
        System.out.println("开始获取AccessToken");

        String accessToken = (String) redisHelper.get(wxAccessTokenKey);
        System.out.println(accessToken);
        if (accessToken == null || accessToken.equals("")) {
            System.out.println("AccessToken失效，重新刷新AccessToken");

            String s = httpHelper.doGet(String.format(wxAccessTokenUrl, appid, appsecret));
            System.out.println(s);

            redisHelper.set(wxAccessTokenKey, JsonHelper.ToOject(s,AccessResponse.class), 90, TimeUnit.MINUTES);
        }


        System.out.println("结束获取AccessToken");
    }

    /**
     * 获取微信Ticket
     */
    private void getTicket() {
        System.out.println("开始获取Ticket");


        System.out.println("结束获取Ticket");
    }

}
