package com.wu.parker.netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

/**
 * 回声服务器
 * 参考资料《Netty实战精髓》：https://waylau.com
 * @author wusq
 * @date 2019/4/19
 */
public class EchoServer {

    private final int port;

    public EchoServer(int port){
        this.port = port;
    }

    public void start() throws Exception{
        NioEventLoopGroup group = new NioEventLoopGroup();
        ServerBootstrap strap = new ServerBootstrap();
        try {
            strap.group(group).channel(NioServerSocketChannel.class).localAddress(new InetSocketAddress(port))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel sc) throws Exception{
                            sc.pipeline().addLast(new EchoServerHandler());
                        }
                    });
            ChannelFuture future = strap.bind().sync();
            System.out.println(EchoServer.class.getName() + " started and listen on " + future.channel().localAddress());
            future.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully().sync();
        }
    }

    public static void main(String[] args) throws Exception{
        new EchoServer(8888).start();
    }
}
