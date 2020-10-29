package org.electrumj.dto;

import lombok.Data;
import lombok.ToString;

import java.util.Map;

@Data
@ToString
public class BlockchainHeader {
    private String hex;
    private long height;

    // Used by Jackson
    public BlockchainHeader() {
    }

    // Used when parsing notifications
    public BlockchainHeader(Map map) {
        hex = (String) map.get("hex");
        height = (Integer) map.get("height");
    }

}
