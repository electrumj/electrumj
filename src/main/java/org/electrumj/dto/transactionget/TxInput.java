package org.electrumj.dto.transactionget;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class TxInput {
    private String coinbase;
    private ScriptSig scriptSig;
    private long sequence;
    @JsonProperty("txid")
    private String txId;
    @JsonProperty("vout")
    private int index;
    @JsonProperty("txinwitness")
    private List<String> txinwitness;
}
