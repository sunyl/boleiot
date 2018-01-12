package com.boleiot.handle;

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
        MsgHandlerManager.get().sendMessage(id, "hello 2018!!");
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
    }

}