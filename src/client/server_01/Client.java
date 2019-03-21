/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.server_01;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author Jairo
 */
public class Client {


    public static void main(String[] args) {
       try{ 
        ObjectInputStream ois = null;
        ObjectOutputStream oos = null;
        Socket client = null;
        
        System.out.println("Client App\n");
        try{
            //Establish the connection with the server by IP and Port
           client = new Socket("127.0.0.1", 9090); 
           
           //Create the streams for data transmission
           oos = new ObjectOutputStream(client.getOutputStream());
           ois = new ObjectInputStream(client.getInputStream());
           
           oos.writeObject("Jairo Martinez");
           
           //Receive the answer form Server
           String ret = (String)ois.readObject();
           
           //Show the message from Server
            System.out.println(ret);
           
        }catch(Exception ex){
            System.out.println(ex.getStackTrace());
        }finally{
            if(ois != null) ois.close();
            if(oos != null) oos.close();
            if(client != null) client.close();
        }
       }catch(IOException ioe){
           ioe.getStackTrace();
       }
        
    }
    
}
