package cn.itcast.mapreduce.flow_count_demo1;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class JobMain extends Configured implements Tool {

//    该方法用于指定一个job任务
    @Override
    public int run(String[] strings) throws Exception {
//        创建一个job任务对象
        final Job job = Job.getInstance(super.getConf(), "mapreduce_flowcount");
        //        如果打包运行出错，则需要加该配置
        job.setJarByClass(JobMain.class);

//        配置job任务对象(8个步骤)
//        第一步指定文件的读取方式和读取路径
        job.setInputFormatClass(TextInputFormat.class);
//        TextInputFormat.addInputPath(job,new Path("hdfs://node01:8020/wordcount"));
        TextInputFormat.addInputPath(job,new Path("file:///D:\\input\\flowcount_input"));

//        第二步：指定Map阶段的处理方式
        job.setMapperClass(FlowCountMapper.class);
//        设置Map截断k2 、v2的类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(FlowBean.class);

//        三，4，5，6采用默认的方式处理
//        job.setCombinerClass(MyCombiner.class);
//        第七步 指定reduce阶段的处理方式和数据类型
        job.setReducerClass(FlowCountReducer.class);
//        设置k3和v3的类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(FlowBean.class);

//        第八步 设置输出类型
        job.setOutputFormatClass(TextOutputFormat.class);
//        设置输出路径
//        final Path path = new Path("hdfs://node01:8020/wordcount_out");
        final Path path = new Path("file:///D:\\out\\flowcount_out");
        TextOutputFormat.setOutputPath(job,path);
//        获取filesystem

//        TextOutputFormat.setOutputPath(job,new Path( "file:///D:\\mapreduce\\output"));
//        等待任务结束
        final boolean bl = job.waitForCompletion(true);

        return bl ? 0:1;
    }

    public static void main(String[] args) throws Exception {
        final Configuration configuration = new Configuration();
//        启动job任务
        final int run = ToolRunner.run(configuration, new JobMain(), args);
        System.exit(run);

    }
}
