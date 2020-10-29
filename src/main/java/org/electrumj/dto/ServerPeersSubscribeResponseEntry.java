package org.electrumj.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class ServerPeersSubscribeResponseEntry {
    private String ip;
    private String address;
    private List<String> features;

    @JsonCreator
    public ServerPeersSubscribeResponseEntry(List input) {
        this.ip = (String) input.get(0);
        this.address = (String)input.get(1);
        this.features = (List<String>) input.get(2);
    }
}
