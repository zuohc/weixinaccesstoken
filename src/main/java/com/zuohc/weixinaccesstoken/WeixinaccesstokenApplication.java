package com.zuohc.weixinaccesstoken;

import com.zuohc.weixinaccesstoken.service.WxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WeixinaccesstokenApplication implements CommandLineRunner {

    @Autowired
    private WxService wxService;

    public static void main(String[] args) {
        SpringApplication.run(WeixinaccesstokenApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        wxService.doWork();
    }
}
