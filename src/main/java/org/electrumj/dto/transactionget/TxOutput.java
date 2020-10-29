package org.electrumj.dto.transactionget;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class TxOutput {
    private int n;
    private ScriptPubKey scriptPubKey;
    private double value;
}
