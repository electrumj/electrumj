package org.electrumj.dto;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class BlockchainScripthashStatus {
    private String scripthash;
    private String status;

    // Used when parsing notifications
    public BlockchainScripthashStatus(List list) {
        scripthash = (String) list.get(0);
        status = (String) list.get(0);
    }

}
