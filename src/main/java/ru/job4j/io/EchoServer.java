package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.regex.Pattern;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream output = socket.getOutputStream();
                     BufferedReader input = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    Pattern helloPattern = Pattern.compile("msg=Hello");
                    Pattern exitPattern = Pattern.compile("msg=Exit");
                    String string = input.readLine();
                    if (exitPattern.matcher(string).find()) {
                        output.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                        server.close();
                    } else if (helloPattern.matcher(string).find()) {
                        output.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                        output.write("Hello".getBytes());
                    } else {
                        output.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                        output.write("What".getBytes());
                    }
                    for (string = input.readLine(); string != null && !string.isEmpty(); string = input.readLine()) {
                        System.out.println(string);
                    }
                    output.flush();
                }
            }
        }
    }
}