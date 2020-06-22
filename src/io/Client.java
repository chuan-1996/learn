package io;
import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;

/**
 * @author qq491
 * 同样是阻塞的
 */
public class Client {

    public static void main(String[] args) throws Exception {
        Socket s = new Socket("localhost", 8080);
        OutputStream out = s.getOutputStream();

        java.util.Scanner scanner = new java.util.Scanner(System.in);
        System.out.println("请输入：");
        String msg = scanner.nextLine();
        // 阻塞，写完成
        out.write(msg.getBytes(StandardCharsets.UTF_8));
        scanner.close();
        s.close();
    }
}

