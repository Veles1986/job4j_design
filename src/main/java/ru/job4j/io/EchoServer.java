package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.regex.Pattern;

public class EchoServer {

    public static final Logger LOG = LoggerFactory.getLogger(EchoServer.class.getName());

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream output = socket.getOutputStream();
                     BufferedReader input = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    output.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    Pattern helloPattern = Pattern.compile("msg=Hello");
                    Pattern exitPattern = Pattern.compile("msg=Exit");
                    String string = input.readLine();
                    if (exitPattern.matcher(string).find()) {
                        server.close();
                    } else if (helloPattern.matcher(string).find()) {
                        output.write("Hello".getBytes());
                    } else {
                        output.write("What".getBytes());
                    }
                    for (string = input.readLine(); string != null && !string.isEmpty(); string = input.readLine()) {
                        System.out.println(string);
                    }
                    output.flush();
                } catch (Exception e) {
                    LOG.error("Exception in IO ", e);
                }
            }
        } catch (Exception e) {
            LOG.error("Exception in ServerSocket ", e);
        }
    }
}