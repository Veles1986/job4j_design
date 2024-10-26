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
                    output.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    Pattern pattern = Pattern.compile("msg=Bye");
                    for (String string = input.readLine(); string != null && !string.isEmpty(); string = input.readLine()) {
                        if (pattern.matcher(string).find()) {
                            server.close();
                        }
                        System.out.println(string);
                    }
                    output.flush();
                }
            }
        }
    }
}