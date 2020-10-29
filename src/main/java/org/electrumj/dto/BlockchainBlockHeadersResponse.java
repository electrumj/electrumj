package org.electrumj.dto;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class BlockchainBlockHeadersResponse {
    private long count;
    private String hex;
    private long max;
    private List<String> branch;
    private String root;
}
