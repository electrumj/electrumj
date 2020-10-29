package org.electrumj.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class BlockchainScripthashGetTxsResponseEntry {
    private long height;
    @JsonProperty("tx_hash")
    private String txHash;
    private long fee;
}
