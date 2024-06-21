package org.example.javatree;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static final int port = 42069;

    public static void main(String[] args) throws IOException {
        System.out.println("LIstening for incomming connections on port " + String.valueOf(port));

        try(ServerSocket sockin = new ServerSocket(port)){
            while(true){
                new Handle(sockin.accept()).start();
            }
        } catch (IOException e) {
            System.err.println("Could not listen on port " + port);
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }

}



class Handle extends Thread{
    private Node tree;
    private Socket socket;
    public Handle(Socket socket){
        this.socket = socket;
    }

    public void run(){
        System.out.println("accepted connection");
        try (
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                DataInputStream in = new DataInputStream(socket.getInputStream());


        ) {
            String inputLine;
            while ((inputLine = in.readUTF()) != null) {
                // Here you can add any evaluation of the inputLine if needed
                // For now, it simply echoes back the received input
                System.out.println("Received: " + inputLine);
                String[] parts = inputLine.toString().split(":");
                System.out.println("parts[0]=" + parts[0] + "; parts[1]=" + parts[1]);
                if(parts[0].equals("insert")){
                    if(tree == null){
                        tree = new Node(parts[1]);
                    }else{
                        tree.insert(parts[1]);
                    }
                } else if (parts[0].equals("draw")) {
                    System.out.println("writing draw");
                    out.writeUTF(String.format("%s:%s:%s:%s:%s",
                            tree.getRowAsString(0),
                            tree.getRowAsString(1),
                            tree.getRowAsString(2),
                            tree.getRowAsString(3),
                            tree.getRowAsString(4)
                    ));
                    out.flush();
                }
            }
        } catch (IOException e) {
            System.err.println("Error handling client connection");
            System.err.println(e.getMessage());
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                System.err.println("Could not close socket");
                System.err.println(e.getMessage());
            }
        }
    }
}
