package MqttClient;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;   
 
class MqttClient {   
  public static void printHexString( byte[] b, int k) {      
    for (int i = 0; i < k; i++) {     
      String hex = Integer.toHexString(b[i] & 0xFF);     
      if (hex.length() == 1) {     
        hex = '0' + hex;     
      }     
      System.out.print(hex.toUpperCase() );         }       
}    
     public static void main(String args[]) throws Exception   
     {   
         String sentence;   
         String modifiedSentence;   
         byte[] conn = {0x10, 0x0e, 0x00, 0x04, 0x4d, 0x51, 0x54, 0x54, 0x04, 0x02, 0x00, 0x3c, 
0x00, 0x02, 0x65, 0x32}; 
         byte[] receiveData = new byte[1024];   
       if (args.length != 1) 
          System.out.println("Usage: java TCPclient host "); 
          else 
           { 
 
         BufferedReader inFromUser = new BufferedReader(new 
InputStreamReader(System.in));   
 
         Socket clientSocket = new Socket(args[0], 1884);   
 
         DataOutputStream outToServer =   
           new DataOutputStream(clientSocket.getOutputStream());   
           DataInputStream dis = new DataInputStream(clientSocket.getInputStream()); 
     
         outToServer.write(conn,0,conn.length);   
            
         int i = dis.read(receiveData); 
         //System.out.print("FROM SERVER: ");   
    printHexString(receiveData,i); 
          
 
         clientSocket.close();   
       } 
                     
     }   
}   
 

