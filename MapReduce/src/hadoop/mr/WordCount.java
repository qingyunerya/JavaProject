package hadoop.mr;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
public class WordCount {

 public static void main(String[] args) throws Exception {
 Configuration conf = new Configuration();
  //构建job对象
  Job job = Job.getInstance(conf);
  
  //注意：main方法所在的类
  job.setJarByClass(WordCount.class);
   
  //设置Mapper相关属性
  job.setMapperClass(WCMapper.class);
  job.setMapOutputKeyClass(Text.class);
  job.setMapOutputValueClass(LongWritable.class);
  FileInputFormat.setInputPaths(job, new Path("/words.txt"));
  
  //设置Reducer相关的属性
  job.setReducerClass(WCReducer.class);
  job.setOutputKeyClass(Text.class);
  job.setOutputValueClass(LongWritable.class);
  FileOutputFormat.setOutputPath(job, new Path("/wcout"));    //提交任务
  job.waitForCompletion(true);
 }

}
