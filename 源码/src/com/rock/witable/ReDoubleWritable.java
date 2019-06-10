package com.rock.witable;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Objects;

public class ReDoubleWritable implements WritableComparable<ReDoubleWritable> {

    private Double value = new Double(0);

    @Override
    public int compareTo(ReDoubleWritable o) {
        return -this.value.compareTo(o.value);
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeDouble(value);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        this.value = dataInput.readDouble();
    }

    public ReDoubleWritable(Double value) {
        this.value = value;
    }

    public ReDoubleWritable() {
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReDoubleWritable that = (ReDoubleWritable) o;
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
