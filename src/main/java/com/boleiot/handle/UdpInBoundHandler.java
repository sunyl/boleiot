package com.boleiot.handle;

import com.boleiot.model.Device;
import com.boleiot.service.DeviceService;
import com.boleiot.service.MessageService;
import com.boleiot.utils.ApplicationContextHelper;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;

public class UdpInBoundHandler extends SimpleChannelInboundHandler<DatagramPacket> {

    private static final Logger logger = LoggerFactory.getLogger(UdpInBoundHandler.class);

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        logger.info("CLIENT: 接入连接");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        logger.info("CLIENT:断开连接");
        ctx.close();
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, DatagramPacket packet) throws Exception {
        String receiveMsg = packet.content().toString(CharsetUtil.UTF_8);
        InetSocketAddress socketAddress = packet.sender();
        String id = socketAddress.getAddress().getHostName() + ":" + socketAddress.getPort();
        MsgHandlerManager.get().put(id, ctx.channel());
        logger.info("Received UDP Msg: address = " + id + " data = " + receiveMsg);
        handleActivate(id, socketAddress.getAddress().getHostName(), socketAddress.getPort(), receiveMsg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
    }

    private void handleActivate(String id, String hostname, int port, String data) {
        //no=aabfaeaf5b3743d89f8468e1fa4963bd&pw=123456
        DeviceService deviceService = ApplicationContextHelper.getBean(DeviceService.class);
        if (data.startsWith("no=") && data.contains("&pw")) {
            String[] split = data.replace("no=", "").replace("&pw=", "-").split("-");
            logger.info("activate: no = " + split[0] + " pw = " + split[1]);
            int row = deviceService.activate(split[0], split[1], hostname, port);
            if (row > 0) {
                MsgHandlerManager.get().sendMessage(id, "注册成功!");
            }
        } else {
            Device device = deviceService.selectByHostNameAndPort(hostname, port);
            MessageService messageService = ApplicationContextHelper.getBean(MessageService.class);
            messageService.sendMessage("admin", device.getName(), data);
        }
    }

}