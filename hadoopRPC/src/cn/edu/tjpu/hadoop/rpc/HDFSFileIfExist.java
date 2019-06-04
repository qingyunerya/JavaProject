package cn.edu.tjpu.hadoop.rpc;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
public class HDFSFileIfExist {
    public static void main(String[] args){
        try{
            String fileName = "test";                                  
            Configuration conf = new Configuration();
            conf.set("fs.defaultFS", "hdfs://172.31.158.155:9000");
            conf.set("fs.hdfs.impl", "org.apache.hadoop.hdfs.DistributedFileSystem");
            FileSystem fs = FileSystem.get(conf);
            if(fs.exists(new Path(fileName))){
                System.out.println("�ļ�����");
            }else{
                System.out.println("�ļ�������");
            }
 
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}