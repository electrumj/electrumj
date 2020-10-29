package org.electrumj.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

import java.util.List;
import java.util.Map;

@Data
@ToString
public class ServerFeaturesResponse {
// {hosts={electrum.emzy.de={tcp_port=50001, ssl_port=50002}}, pruning=null, server_version=ElectrumX 1.14.0, protocol_min=1.4, protocol_max=1.4.2, genesis_hash=000000000019d6689c085ae165831e934ff763ae46a2a6c172b3f1b60a8ce26f, hash_function=sha256, services=[tcp://electrum.emzy.de:50001, ssl://electrum.emzy.de:50002]}

    private Map<String, Map<String, Integer>> hosts;

    private int pruning;

    @JsonProperty("server_version")
    private String serverVersion;

    @JsonProperty("protocol_min")
    private String protocolMin;

    @JsonProperty("protocol_max")
    private String protocolMax;

    @JsonProperty("genesis_hash")
    private String genesisHash;

    @JsonProperty("hash_function")
    private String hashFunction;

    private List<String> services;
}
