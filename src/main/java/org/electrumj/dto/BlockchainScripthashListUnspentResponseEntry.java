package org.electrumj.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class BlockchainScripthashListUnspentResponseEntry {
    @JsonProperty("tx_pos")
    private long txPos;
    private long value;
    @JsonProperty("tx_hash")
    private String txHash;
    private long height;
}
