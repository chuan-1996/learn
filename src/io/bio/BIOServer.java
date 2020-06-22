package io.bio;
import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;

/**
 * @author qq491
 * 重点为被阻塞的两个位置
 *
 * {@linkplain BIOServer1}为该版本的改进版 将第二个阻塞点改进为非阻塞
 *
 *
 * 五种IO模型
 * 对于一次IO访问，数据会先被拷贝到内核的缓冲区中，然后才会从内核的缓冲区拷贝到应用程序的地址空间。需要经历两个阶段：
 *
 * 准备数据
 * 将数据从内核缓冲区拷贝到进程地址空间
 *
 * 由于存在这两个阶段，Linux产生了下面五种IO模型（以socket为例）
 *
 * 1 阻塞式IO：
 * 当用户进程调用了recvfrom等阻塞方法时，内核进入IO的第1个阶段：准备数据（内核需要等待足够的数据再拷贝）这个过程需要等待，用户进程会被阻塞，等内核将数据准备好，然后拷贝到用户地址空间，内核返回结果，用户进程才从阻塞态进入就绪态
 * Linux中默认情况下所有的socket都是阻塞的
 * 2 非阻塞式IO：
 * 当用户进程发出read操作时，如果kernel中的数据还没有准备好，那么它并不会block用户进程，而是立刻返回一个error。
 * 用户进程判断结果是一个error时，它就知道数据还没有准备好，于是它可以再次发送read操作
 * 一旦kernel中的数据准备好了，并且又再次收到了用户进程的system call，那么它马上就将数据拷贝到了用户内存，然后返回
 * 非阻塞IO模式下用户进程需要不断地询问内核的数据准备好了没有
 * 3 IO多路复用：
 * 通过一种机制，一个进程可以监视多个文件描述符（套接字描述符）一旦某个文件描述符就绪（一般是读就绪或者写就绪），能够通知程序进行相应的读写操作（这样就不需要每个用户进程不断的询问内核数据准备好了没）
 * 常用的IO多路复用方式有select、poll和epoll
 * 4 信号驱动IO：
 * 内核文件描述符就绪后，通过信号通知用户进程，用户进程再通过系统调用读取数据。
 * 此方式属于同步IO（实际读取数据到用户进程缓存的工作仍然是由用户进程自己负责的）
 * 5 异步IO（POSIX的aio_系列函数）
 * 用户进程发起read操作之后，立刻就可以开始去做其它的事。内核收到一个异步IO read之后，会立刻返回，不会阻塞用户进程。
 * 内核会等待数据准备完成，然后将数据拷贝到用户内存，当这一切都完成之后，内核会给用户进程发送一个signal告诉它read操作完成了
 *
 * 无论阻塞式IO还是非阻塞式IO，都是同步IO模型，区别就在与第一步是否完成后才返回，但第二步都需要当前进程去完成
 * 异步IO呢，就是从第一步开始就返回，直到第二步完成后才会返回一个消息
 * 也就是说，非阻塞能够让你在第一步时去做其它的事情，而真正的异步IO能让你第二步的过程也能去做其它事情。
 *
 *  在JDK1.7中，这异步IO被称作NIO.2，主要在Java.nio.channels包下增加了下面四个异步通道：
 *
 * @see java.nio.channels.AsynchronousSocketChannel
 * @see java.nio.channels.AsynchronousServerSocketChannel
 * @see java.nio.channels.AsynchronousFileChannel
 * @see java.nio.channels.AsynchronousDatagramChannel
 *
 * 在AIO socket编程中，服务端通道是AsynchronousServerSocketChannel，这个类提供了一个open()静态工厂，一个bind()方法用于绑定服务端IP地址（还有端口号），
 * 另外还提供了accept()用于接收用户连接请求。在客户端使用的通道是AsynchronousSocketChannel,这个通道处理提供open静态工厂方法外，还提供了read和write方法。
 * 在AIO编程中，发出一个事件（accept read write等）之后要指定事件处理类（回调函数），AIO中的事件处理类是CompletionHandler<V,A>，这个接口定义了如下两个方法，分别在异步操作成功和失败时被回调。
 *
 * void completed(V result, A attachment);
 *
 * void failed(Throwable exc, A attachment);
 */
public class BIOServer {

    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(8080);
        System.out.println("服务器启动成功");
        while (!serverSocket.isClosed()) {
            // 阻塞
            Socket request = serverSocket.accept();
            System.out.println("收到新连接 : " + request.toString());
            try {
                InputStream inputStream = request.getInputStream();
                BufferedReader reader = new java.io.BufferedReader(new java.io.InputStreamReader(inputStream, StandardCharsets.UTF_8));
                String msg;
                // 没有数据，阻塞
                while ((msg = reader.readLine()) != null) {
                    if (msg.length() == 0) {
                        break;
                    }
                    System.out.println(msg);
                }
                System.out.println("收到数据,来自："+ request.toString());
            } catch (java.io.IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    request.close();
                } catch (java.io.IOException e) {
                    e.printStackTrace();
                }
            }
        }
        serverSocket.close();
    }
}
