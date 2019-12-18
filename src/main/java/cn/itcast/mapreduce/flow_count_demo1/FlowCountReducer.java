package cn.itcast.mapreduce.flow_count_demo1;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class FlowCountReducer extends Reducer<Text,FlowBean,Text,FlowBean> {
    @Override
    protected void reduce(Text key, Iterable<FlowBean> values, Context context) throws IOException, InterruptedException {
         Integer upFlow =0;
         Integer downFlow=0;
         Integer upCountFlow=0;
         Integer downCountFlow=0;
        //        遍历集合，并将集合种的对应四个字段累计
        for (FlowBean value : values) {
            upFlow +=value.getUpFlow();
            downFlow += value.getDownFlow();
            upCountFlow+= value.getUpCountFlow();
            downCountFlow+=value.getDownCountFlow();
        }
        final FlowBean flowBean = new FlowBean();
        flowBean.setUpFlow(upFlow);
        flowBean.setDownFlow(downFlow);
        flowBean.setUpCountFlow(upCountFlow);
        flowBean.setDownCountFlow(downCountFlow);
        context.write(key,flowBean);
    }
}
