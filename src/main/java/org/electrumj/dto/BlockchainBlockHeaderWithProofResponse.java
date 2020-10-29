package org.electrumj.dto;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class BlockchainBlockHeaderWithProofResponse {
    private String header;
    private List<String> branch;
    private String root;
}
