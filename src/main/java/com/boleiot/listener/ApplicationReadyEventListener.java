package com.boleiot.listener;

import com.boleiot.config.UDPConfig;
import com.boleiot.udp.UdpServer;
import com.boleiot.utils.ApplicationContextHelper;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ApplicationReadyEventListener implements ApplicationListener<ApplicationReadyEvent> {
    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        try {
            System.out.println("--->onApplicationEvent ready");
            UDPConfig sysConfig = ApplicationContextHelper.getBean(UDPConfig.class);
            UdpServer udpServer = ApplicationContextHelper.getBean(UdpServer.class);
            udpServer.run(sysConfig.getUdpReceivePort());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
