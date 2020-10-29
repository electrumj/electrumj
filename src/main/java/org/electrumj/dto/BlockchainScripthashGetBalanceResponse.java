package org.electrumj.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class BlockchainScripthashGetBalanceResponse {
    private long confirmed;
    private long unconfirmed;
}
