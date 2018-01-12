package com.boleiot.udp;

import com.boleiot.handle.UdpInBoundHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;


@Component
public class UdpServer {

    private static final Logger log = LoggerFactory.getLogger(UdpServer.class);

    @Async("myTaskAsyncPool")
    public void run(int udpReceivePort) {

        EventLoopGroup group = new NioEventLoopGroup();
        log.info("Server start!  Udp Receive msg Port:" + udpReceivePort);

        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioDatagramChannel.class)
                    .option(ChannelOption.SO_BROADCAST, true)
                    .handler(new UdpInBoundHandler());


            b.bind(udpReceivePort).sync().channel().closeFuture().await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
    }

}
