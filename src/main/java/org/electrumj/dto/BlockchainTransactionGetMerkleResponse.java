package org.electrumj.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class BlockchainTransactionGetMerkleResponse {
    @JsonProperty("block_height")
    private long blockHeight;
    private List<String> merkle;
    private long pos;
}
