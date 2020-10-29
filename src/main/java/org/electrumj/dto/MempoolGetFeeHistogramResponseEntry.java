package org.electrumj.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class MempoolGetFeeHistogramResponseEntry {
    private long fee;
    private long vsize;

    @JsonCreator
    public MempoolGetFeeHistogramResponseEntry(List<Integer> input) {
        this.fee = input.get(0);
        this.vsize = input.get(1);
    }
}
