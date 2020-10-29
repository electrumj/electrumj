package org.electrumj;

import org.bitcoinj.params.MainNetParams;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UtilTest {

    @Test
    public void scripthash() throws Throwable {
        assertEquals("8b01df4e368ea28f8dc0423bcf7a4923e3a12d307c875e47a0cfbf90b5c39161",
                    Util.scripthash(MainNetParams.get(), "1A1zP1eP5QGefi2DMPTfTL5SLmv7DivfNa"));
    }



}
