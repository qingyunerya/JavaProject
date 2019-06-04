package cn.edu.tjpu.hadoop.rpc;

import java.io.IOException;

import org.apache.hadoop.HadoopIllegalArgumentException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;
import org.apache.hadoop.ipc.RPC.Server;

public class RPCServer implements Hello{

	public static void main(String[] args) throws HadoopIllegalArgumentException, IOException {
		Server server = new RPC.Builder(new Configuration())
			.setInstance(new RPCServer())
			.setBindAddress("192.168.40.1")
			.setPort(9001)
			.setProtocol(Hello.class)
			.build();
		server.start();

	}

	@Override
	public String sayHi(String name) {
		// TODO Auto-generated method stub
		return "HI~" + name;
	}

}
