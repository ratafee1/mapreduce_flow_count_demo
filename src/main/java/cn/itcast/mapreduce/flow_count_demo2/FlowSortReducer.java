package cn.itcast.mapreduce.flow_count_demo2;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import sun.awt.SunHints;

import java.io.IOException;
import java.util.Iterator;

//k2 flowbean   text 手机号
public class FlowSortReducer extends Reducer<FlowBean, Text,Text,FlowBean> {
    @Override
    protected void reduce(FlowBean key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        for (Text value : values) {
            context.write(value,key);
        }
    }
}
