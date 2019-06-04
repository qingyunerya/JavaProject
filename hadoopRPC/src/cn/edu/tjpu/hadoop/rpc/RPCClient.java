package cn.edu.tjpu.hadoop.rpc;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;

public class RPCClient {

	public static void main(String[] args) throws IOException {
		Hello proxy = RPC.getProxy(Hello.class, 10010,
				new InetSocketAddress("192.168.40.1", 9001), new Configuration());
		String sayHi = proxy.sayHi("tomcat");
		System.out.println(sayHi);
		
	}

}
