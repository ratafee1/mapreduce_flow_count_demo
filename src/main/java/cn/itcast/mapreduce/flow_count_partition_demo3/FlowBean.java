package cn.itcast.mapreduce.flow_count_partition_demo3;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class FlowBean implements Writable {

    private Integer upFlow;
    private Integer downFlow;
    private Integer upCountFlow;
    private Integer downCountFlow;
    @Override
    public String toString() {
        return  upFlow +
                "\t" + downFlow +
                "\t" + upCountFlow +
                "\t" + downCountFlow ;
    }

    public Integer getUpFlow() {
        return upFlow;
    }

    public void setUpFlow(Integer upFlow) {
        this.upFlow = upFlow;
    }

    public Integer getDownFlow() {
        return downFlow;
    }

    public void setDownFlow(Integer downFlow) {
        this.downFlow = downFlow;
    }

    public Integer getUpCountFlow() {
        return upCountFlow;
    }

    public void setUpCountFlow(Integer upCountFlow) {
        this.upCountFlow = upCountFlow;
    }

    public Integer getDownCountFlow() {
        return downCountFlow;
    }

    public void setDownCountFlow(Integer downCountFlow) {
        this.downCountFlow = downCountFlow;
    }


//序列化
    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeInt(this.getUpFlow());
        dataOutput.writeInt(this.getDownFlow());
        dataOutput.writeInt(this.getUpCountFlow());
        dataOutput.writeInt(this.getDownCountFlow());
    }

//反序列化
    @Override
    public void readFields(DataInput dataInput) throws IOException {

        this.upFlow = dataInput.readInt();
        this.downFlow =dataInput.readInt();
        this.upCountFlow =dataInput.readInt();
        this.downCountFlow =dataInput.readInt();
    }
}