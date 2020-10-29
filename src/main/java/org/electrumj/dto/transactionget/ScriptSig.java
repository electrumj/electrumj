package org.electrumj.dto.transactionget;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ScriptSig {
    private String asm;
    private String hex;
}
