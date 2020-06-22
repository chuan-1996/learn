package io.bio;

import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.*;

/**
 * @author qq491
 * 线程池是一种优化的方式
 * 古老的方式是每一个客户端连接启用一个线程，将这些线程加入一个List
 * 此后使用死循环轮询List 用非阻塞的方式来处理请求
 * 轮询List会浪费大量的时间   用户态与内核态的切换（socket本质为文件描述符 需要内核态系统调用read）
 * NIO改进了该模型
 */
public class BIOServer1 {

    private static ExecutorService threadPool = new ThreadPoolExecutor(
            2,
            2,
            0,
            TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(512),
            new ThreadPoolExecutor.DiscardPolicy());

    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(8080);
        System.out.println("多线程服务器启动成功");

        while (!serverSocket.isClosed()) {
            //仍然是阻塞的
            Socket request = serverSocket.accept();
            System.out.println("收到新连接 : " + request.toString());

            //注意 读写操作是不阻塞的
            threadPool.execute(() -> {
                try {
                    java.io.InputStream inputStream = request.getInputStream();
                    java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.InputStreamReader(inputStream, StandardCharsets.UTF_8));
                    String msg;
                    while ((msg = reader.readLine()) != null) { // 阻塞
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
            });
        }
        serverSocket.close();
    }
}


