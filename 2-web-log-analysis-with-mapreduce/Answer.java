import java.io.IOException;
import java.net.URI;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
 
public class Answer {
 
    public static class LogMapper extends Mapper<Object, Text, Text, IntWritable> {
        private Text logIp = new Text();
        private final static IntWritable one = new IntWritable(1);
 
        @Override
        public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
            
            String logRecord = value.toString();
            String[] logField = logRecord.split(" ");
            logIp.set(logField[0]);
            context.write(logIp, one);
            
        }
    }
 
    public static class LogReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
 
        private IntWritable result = new IntWritable();

        @Override
        protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
            int sum = 0;
            for (IntWritable val : values) {
                sum += val.get();
            }
            result.set(sum);
            context.write(key, result);
        }
    }
 
    public static void main(String[] args) throws Exception {
 
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf, "log analysis");
        job.setJarByClass(Answer.class);
        job.setMapperClass(LogMapper.class);
        job.setReducerClass(LogReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        Path outPath = new Path("hdfs://localhost:9000/user/hadoop/web_log_analysis/output");
        FileSystem fs = FileSystem.get(new URI("hdfs://localhost:9000"), conf);
        if(fs.exists(outPath)) {
            fs.delete(outPath,true);
        }
        
        FileInputFormat.addInputPaths(job, "hdfs://localhost:9000/user/hadoop/web_log_analysis/input");
        FileOutputFormat.setOutputPath(job, new Path("hdfs://localhost:9000/user/hadoop/web_log_analysis/output"));
        System.exit(job.waitForCompletion(true) ? 0 : 1);
  
    }
}

