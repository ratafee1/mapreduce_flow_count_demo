package cn.itcast.mapreduce.flow_count_demo2;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class FlowSortMapper extends Mapper<LongWritable, Text,FlowBean,Text> {
//    map方法：将k1 v1 转为k2 v2

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        final String[] split = value.toString().split("\t");
        final FlowBean flowBean = new FlowBean();
        flowBean.setUpFlow( Integer.parseInt( split[1]));
        flowBean.setDownFlow(Integer.parseInt( split[2]));
        flowBean.setUpCountFlow(Integer.parseInt(split[3]));
        flowBean.setDownCountFlow(Integer.parseInt(split[4]));

        String phoneNum = split[0];
        context.write(flowBean,new Text(phoneNum));
    }
}
