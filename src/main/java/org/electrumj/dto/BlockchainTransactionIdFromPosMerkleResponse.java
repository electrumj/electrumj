package org.electrumj.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class BlockchainTransactionIdFromPosMerkleResponse {
    @JsonProperty("tx_hash")
    private String txHash;
    private List<String> merkle;
}
