package org.electrumj.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class ServerVersionResponse {
    private String serverSoftwareVersion;
    private String protocolVersion;

    @JsonCreator
    public ServerVersionResponse(List<String> input) {
        serverSoftwareVersion = input.get(0);
        protocolVersion = input.get(1);
    }

}
