package io.nio;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.Iterator;


/**
 * @author qq491
 * NIO改进了BIO超多线程（每个客户端有一个线程）的模型
 * 计算机内核实现了系统调用select、poll、epoll(主流) 可以帮助我们寻找已准备好的文件描述符socket 然后仅对这些系统调用read
 * 这就是多路复用
 * IO多路复用：
 * 通过一种机制，一个进程可以监视多个文件描述符（套接字描述符）一旦某个文件描述符就绪（一般是读就绪或者写就绪），能够通知程序进行相应的读写操作（这样就不需要每个用户进程不断的询问内核数据准备好了没）
 *channel 虚拟内存 CMA读写 事件驱动 selector轮询
 *
 * NETTY将这些东西全部封装了起来
 * @see io.netty1.ServerNetty
 */
public class NioServer {
    // 本地字符集
    private static final String LocalCharSetName = "UTF-8";

    // 本地服务器监听的端口
    private static final int Listenning_Port = 8080;

    // 缓冲区大小
    private static final int Buffer_Size = 1024;

    // 超时时间,单位毫秒
    private static final int TimeOut = 3000;

    public static void main(String[] args) throws IOException {
        // 创建一个在本地端口进行监听的服务Socket信道.并设置为非阻塞方式
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        serverChannel.socket().bind(new InetSocketAddress(Listenning_Port));
        serverChannel.configureBlocking(false);
        // 创建一个选择器并将serverChannel注册到它上面
        Selector selector = Selector.open();
        //设置为客户端请求连接时，默认客户端已经连接上
        System.out.println("服务端启动，开启一个通道，将该通道注册到选择器selector并对该通道的ACCEPT事件感兴趣");
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            System.out.println("轮询通道感兴趣的事件");
            // 轮询监听key，select是阻塞的，accept()也是阻塞的 阻塞秒数
            if (selector.select(TimeOut) == 0) {
                System.out.println("无感兴趣事件，下一轮(3s TimeOut)");
                continue;
            }

            // 有客户端请求，被轮询监听到 即select有结果了
            Iterator<SelectionKey> keyIter = selector.selectedKeys().iterator();
            while (keyIter.hasNext()) {
                SelectionKey key = keyIter.next();
                if (key.isAcceptable()) {
                    System.out.println("有ACCEPT事件！我接收了请求，并给其开启一条通道，将该通道注册到选择器，对其READ事件感兴趣");
                    SocketChannel clientChannel = ((ServerSocketChannel) key.channel()).accept();
                    clientChannel.configureBlocking(false);
                    //意思是在通过Selector监听Channel时对读事件感兴趣
                    clientChannel.register(selector, SelectionKey.OP_READ,
                            ByteBuffer.allocate(Buffer_Size));
                }
                else if (key.isReadable()) {
                    System.out.println("有READ事件！使用Nio.ByteBuffer处理数据");
                    SocketChannel clientChannel = (SocketChannel) key.channel();
                    // 接下来是java缓冲区io操作，避免io堵塞
                    ByteBuffer buffer = (ByteBuffer) key.attachment();
                    buffer.clear();
                    long bytesRead = clientChannel.read(buffer);
                    if (bytesRead == -1) {
                        // 没有读取到内容的情况
                        clientChannel.close();
                    } else {
                        // 将缓冲区准备为数据传出状态
                        buffer.flip();
                        // 将获得字节字符串(使用Charset进行解码)
                        String receivedString = Charset
                                .forName(LocalCharSetName).newDecoder().decode(buffer).toString();
                        System.out.println("接收到信息:" + receivedString);
                    }
                }
                keyIter.remove();
            }
        }

    }
}

