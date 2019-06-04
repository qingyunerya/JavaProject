package hadoop;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TalkSever {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try
		{
			ServerSocket server =null;
			try
			{
				server =new ServerSocket(9019);
				
			}catch(Exception e)
			{
				System.out.println("err");
			}
			Socket socket=null;
			try
			{
				socket=server.accept();
				System.out.println("linked");
				
			}catch(Exception e)
			{
				System.out.println("err");
			}
			String line=null;
			BufferedReader is=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter os=new PrintWriter(socket.getOutputStream());
			BufferedReader sin=new BufferedReader(new InputStreamReader(System.in));
			line =sin.readLine();
			int i=0;
			while(!line.equals("bye"))
			{
				os.println(line);
				os.flush();
				is.readLine();
				System.out.println("send"+i);
				i++;
				System.out.println("Server:"+line);
				System.out.println("Client:"+is.readLine());
				System.out.println("accept"+i);
				i++;
				line=sin.readLine();
			}
			System.out.println("stop");
			os.close();
			is.close();
			socket.close();
			server.close();
		}catch(Exception e)
		{
			System.out.println("err");
		}
	}

}
