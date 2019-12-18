package cn.itcast.mapreduce.flow_count_partition_demo3;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

public class FlowCountPartition extends Partitioner<Text,FlowBean> {
    @Override
//    i 是reducetask的个数
    public int getPartition(Text text, FlowBean flowBean, int i) {
        final String phonenum = text.toString();
        if (phonenum.startsWith("135")){
            return 0;
        }else if (phonenum.startsWith("135")){
            return  1;
        }else if (phonenum.startsWith("137")){
            return 2;
        }else {
            return 3;
        }
    }
}
