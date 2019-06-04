package cn.edu.tjpu.hadoop.rpc;

public interface Hello {

	public static final long versionID = 10010;
	
	public String sayHi(String name);
}
