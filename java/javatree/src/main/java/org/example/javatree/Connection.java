package org.example.javatree;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class Connection {
    private static Connection instance;
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;

    private Connection(){
        try {
            this.socket = new Socket("127.0.0.1", 42069);
            System.out.println("Connected to " + "127.0.0.1" + " on port " + 42069);
            this.in = new DataInputStream(this.socket.getInputStream());
            this.out = new DataOutputStream(this.socket.getOutputStream());
        }catch (Exception e){
            System.err.println("skill issue " + e.getMessage());
            System.exit(-1);
        }
    }

    public void write(String data){
        System.out.println("writing '" + data + "'");
        try {
            this.out.writeUTF(data);
            this.out.flush();
        }catch (Exception e){
            System.err.println("skill issue " + e.getMessage());
            System.exit(-1);
        }
    }

    public String next(){

        System.out.println("waiting for data from server");
        try {
            String data = this.in.readUTF();

            System.out.println("got '" + data + "'");
            return data;
        }catch (Exception e){
            System.err.println("skill issue " + e.getMessage());
            System.exit(-1);
        }
        return "";
    }

    public static Connection getInstance(){
        if(instance == null){
            instance = new Connection();
        }
        return instance;
    }



}
