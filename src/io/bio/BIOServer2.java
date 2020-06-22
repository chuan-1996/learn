package io.bio;

/*实现了对http协议请求的回应*/
public class BIOServer2 {

    private static java.util.concurrent.ExecutorService threadPool = java.util.concurrent.Executors.newCachedThreadPool();

    public static void main(String[] args) throws Exception {
        java.net.ServerSocket serverSocket = new java.net.ServerSocket(8080);
        System.out.println("服务器启动成功");
        while (!serverSocket.isClosed()) {
            java.net.Socket request = serverSocket.accept();
            System.out.println("收到新连接 : " + request.toString());
            threadPool.execute(() -> {
                try {
                    // 接收数据、打印
                    java.io.InputStream inputStream = request.getInputStream();
                    java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.InputStreamReader(inputStream, "utf-8"));
                    String msg;
                    while ((msg = reader.readLine()) != null) {
                        if (msg.length() == 0) {
                            break;
                        }
                        System.out.println(msg);
                    }

                    System.out.println("收到数据,来自："+ request.toString());
                    // 响应结果 200
                    java.io.OutputStream outputStream = request.getOutputStream();
                    outputStream.write("HTTP/1.1 200 OK\r\n".getBytes());
                    outputStream.write("Content-Length: 11\r\n\r\n".getBytes());
                    outputStream.write("Hello World".getBytes());
                    outputStream.flush();
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
