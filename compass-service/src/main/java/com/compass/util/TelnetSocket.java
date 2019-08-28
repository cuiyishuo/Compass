package com.compass.util;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * java使用socket编程
 *
 * @author sol
 * @create 2019-04-03  18:25
 */
@Slf4j
@Data
public class TelnetSocket {

    /**
     * socket函数
     */
    private Socket socket;

    /**
     * 换行符
     */
    private final static String LINE_SEPARATOR = System.getProperty("line.separator");
    /**
     * 默认读字符编码
     */
    private String readEncoding = "UTF-8";

    /**
     * 默认写字符编码
     */
    private String writeEncoding = "UTF-8";

    /**
     * DubboTelnetClient 构造
     *
     * @param ip zk的ip地址
     * @param port 端口号
     * @throws UnknownHostException
     * @throws IOException
     */
    public TelnetSocket(String ip, int port) throws UnknownHostException,
            IOException {
        this.socket = new Socket(ip, port);
    }

    /**
     * DubboTelnetClient 构造
     *
     * @param ip
     * @param port
     * @param connTimeout
     * @throws UnknownHostException
     * @throws IOException
     */
    public TelnetSocket(String ip, int port, int connTimeout)
            throws UnknownHostException, IOException {
        this(ip, port, connTimeout, 5000);
    }

    public boolean isConnected() {
        return socket.isConnected();
    }

    /**
     * DubboTelnetClient 构造
     *
     * @param ip
     * @param port
     * @param connTimeout
     * @param soTimeout
     * @throws UnknownHostException
     * @throws IOException
     */
    public TelnetSocket(String ip, int port, int connTimeout, int soTimeout)
            throws UnknownHostException, IOException {
        try {
            this.socket = new Socket();
            socket.setSoTimeout(soTimeout);
            socket.connect(new InetSocketAddress(ip, port), connTimeout);
        } catch (Exception e) {
            log.error("连接" + ip + ":" + port + "异常" + e.getMessage(), e);
        }
    }

    /**
     * 发送消息
     *
     * @param msg
     * @param times 读取次数（即遇到dubbo>结尾代表一次）
     * @return 返回消息
     * @throws IOException
     */
    public String send(String msg, int times) throws IOException {
        String result = "";
        InputStream in = null;
        OutputStream out = null;
        ByteArrayOutputStream baout = null;
        try {
            // 初始化通道
            in = socket.getInputStream();
            out = socket.getOutputStream();
            // 发送请求
            out.write(msg.getBytes(writeEncoding));
            out.write(LINE_SEPARATOR.getBytes(writeEncoding));
            out.flush();

            baout = new ByteArrayOutputStream();
            // 解析得到的响应
            StringBuffer sb = new StringBuffer();
            byte[] bs = new byte[1024];
            int len = 0;
            int i = 0;
            while (i < 1024 && (len = in.read(bs)) != -1) {
                //if (i > 1024) { // 防止无限循环 最多取 1M的数据
                //	break;
                //}
                String data = new String(bs, 0, len, readEncoding);
                baout.write(bs, 0, len);
                sb.append(data);

                String last = sb.substring(sb.length() - 6);
                if ("dubbo>".equals(last) || "lnet> ".equals(last)) {
                    if (--times < 1) {
                        break; // 读到这个就断开连接返回
                    }
                }
                i++;
            }
            result = new String(baout.toByteArray(), readEncoding);
            return result;
        } catch (Exception e) {
            log.error("执行socket命令" + msg + "结束, 结果为" + e.getMessage(), e);
        } finally {
            if (baout != null) {
                baout.close();
            }
            if (out != null) {
                out.close();
            }
            if (in != null) {
                in.close();
            }
        }
        return result;
    }

    /**
     * 发送消息
     *
     * @param msg
     * @return 返回消息
     * @throws IOException
     */
    public String send(String msg) throws IOException {
        int time = 1;
        if (msg.startsWith("count")) {
            time = 2; // count命令会返回2次dubbo
        } else if (msg.startsWith("trace")) {
            String[] cmds = msg.split("\\s+"); // trace几次返回几次
            time = cmds.length > 2 ? Integer.valueOf(cmds[cmds.length - 1]
                    .trim()) : 1;
        }
        return send(msg, time);
    }

    /**
     * 关闭连接
     */
    public void close() {
        try {
            if (socket != null && !socket.isClosed())
                socket.close();
        } catch (IOException e) {
            log.error("关闭失败", e);
        }
    }


    public static void main(String[] args) {
        String[] cmds = new String[]{"invoke .AddressBaseService.getAddressesByCode(\"3301\")"};
        TelnetSocket client = null;
        long time0 = System.currentTimeMillis();
        try {
            for (int i = 0; i < cmds.length; i++) {
                client = new TelnetSocket("192.168.192.153", 32000, 5000, 30000);
//				client = new DubboTelnetClient("10.12.120.121", 20880,5000);
                client.setReadEncoding("gbk");
                String res = client.send(cmds[i]);
                System.out.println("命令:" + cmds[i] + "   返回");
                System.out.println(res);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (client != null) {
                client.close();
            }
        }
        long time1 = System.currentTimeMillis();
        System.out.println("耗时：" + (time1 - time0) + "ms");
    }
}

