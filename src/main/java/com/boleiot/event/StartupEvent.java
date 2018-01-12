package com.boleiot.event;

import com.boleiot.config.UDPConfig;
import com.boleiot.udp.UdpServer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

public class StartupEvent implements ApplicationListener<ContextRefreshedEvent> {

    private static ApplicationContext context;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        try {
            context = contextRefreshedEvent.getApplicationContext();
            UDPConfig sysConfig = context.getBean(UDPConfig.class);
            UdpServer udpServer = (UdpServer) StartupEvent.getBean(UdpServer.class);
            udpServer.run(sysConfig.getUdpReceivePort());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Object getBean(Class beanName) {
        return context != null ? context.getBean(beanName) : null;
    }
}
