package org.electrumj.dto.transactionget;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@JsonIgnoreProperties
public class BlockchainTransactionGetVerboseResponse {
    @JsonProperty("blockhash")
    private String blockHash;
    @JsonProperty("blocktime")
    private long blockTime;
    private long confirmations;
    private String hash;
    private String hex;
    private long locktime;
    private long size;
    private long vsize;
    private long weight;
    private long time;
    @JsonProperty("txid")
    private String txId;
    private byte version;
    @JsonProperty("vin")
    private List<TxInput> inputs;
    @JsonProperty("vout")
    private List<TxOutput> outputs;
    @JsonProperty("txinwitness")
    private List<String> txinwitness;

}
