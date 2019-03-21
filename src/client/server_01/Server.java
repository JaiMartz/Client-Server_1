/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.server_01;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Jairo
 */
public class Server {
    public static void main(String[] args) {
        try{
            ObjectInputStream ois = null;
            ObjectOutputStream oos = null;

            Socket client = null;
            ServerSocket server= new ServerSocket(9090);
            System.out.println("Server App\n");
            while(true){
            try{
            
                //Accept the connection
                client = server.accept();

                //We're printin in console the IP which connected with the server.
                System.out.println("Establishing connection with: "+client.getInetAddress());

                ois = new ObjectInputStream(client.getInputStream());
                oos = new ObjectOutputStream(client.getOutputStream());

                //Read the name from Client
                String nom = (String) ois.readObject();
                String greeting = "Hi! I'm happy to see you "+nom+" "+System.currentTimeMillis();


                //Sending the salute to Client.
                oos.writeObject(greeting);
                System.out.println("Greeting sended");
                
            }catch(Exception e){
                    e.printStackTrace();
            }finally{
                    if ( oos != null) oos.close();
                    if (ois != null) ois.close();
                    if(client != null) client.close();
                    System.out.println("Connection closed!");
                }
            }
            }catch(IOException ioe){
                ioe.getStackTrace();
        }


    }
}
