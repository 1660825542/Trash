package com.rock.witable;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Objects;

public class ReLongWritable implements WritableComparable<ReLongWritable> {

    private Long value = new Long(0);

    @Override
    public int compareTo(ReLongWritable o) {
        return -this.value.compareTo(o.value);
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeLong(value);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        this.value = dataInput.readLong();
    }

    public ReLongWritable(Long value) {
        this.value = value;
    }

    public ReLongWritable() {
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReLongWritable that = (ReLongWritable) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {

        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
