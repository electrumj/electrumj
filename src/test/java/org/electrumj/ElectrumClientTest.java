package org.electrumj;

import com.google.common.util.concurrent.SettableFuture;
import com.googlecode.jsonrpc4j.JsonRpcClientException;
import org.bitcoinj.core.Address;
import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.params.MainNetParams;
import org.electrumj.dto.*;
import org.electrumj.dto.transactionget.BlockchainTransactionGetVerboseResponse;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Tests the methods in ElectrumClient connecting to a real Electrum server.
 */
public class ElectrumClientTest {

    private static ElectrumClient client;
    private static NetworkParameters bitcoinjNetowrkParameters;

    @BeforeClass
    public static void setUp() throws Throwable {
        bitcoinjNetowrkParameters = MainNetParams.get();
        client = new ElectrumClient(ElectrumServers.CORE_1209K_URL);
//        To test using a local ElectrumX connected to a local bitcoid running in regtest, uncomment
//        the following 2 lines and uncomment the previous 2 lines.
//        bitcoinjNetowrkParameters = RegTestParams.get();
//        server = new ElectrumClient(ElectrumServers.LOCALHOST_URL);
        client.openConnection();
        // Send a "server.version" message to do the handshake.
        ServerVersionResponse serverVersionResponse = client.serverVersion();
        System.out.println("serverVersionResponse = " + serverVersionResponse);
    }

    @AfterClass
    public static void tearDown() throws Throwable {
        client.closeConnection();
    }

    @Test
    public void blockchainBlockHeader() throws Throwable {
        long height = MainNetParams.get().equals(bitcoinjNetowrkParameters) ? 655136 : 10;
        String blockHeaderResponse = client.blockchainBlockHeader( height);
        System.out.println("blockHeaderResponse = " + blockHeaderResponse);
    }

    @Test
    public void blockchainBlockHeaderWithProof() throws Throwable {
        long height = MainNetParams.get().equals(bitcoinjNetowrkParameters) ? 655136 : 10;
        long cpHeight = MainNetParams.get().equals(bitcoinjNetowrkParameters) ? 655140 : 20;
        BlockchainBlockHeaderWithProofResponse blockHeaderResponseWithProof = client.blockchainBlockHeader(height, cpHeight);
        System.out.println("blockHeaderResponseWithProof = " + blockHeaderResponseWithProof);
    }

    @Test
    public void blockchainBlockHeaders() throws Throwable {
        long startHeight = MainNetParams.get().equals(bitcoinjNetowrkParameters) ? 655136 : 10;
        long cpHeight = MainNetParams.get().equals(bitcoinjNetowrkParameters) ? 655140 : 20;
        BlockchainBlockHeadersResponse blockHeadersResponse = client.blockchainBlockHeaders(startHeight, 2, cpHeight);
        System.out.println("blockHeadersResponse = " + blockHeadersResponse);
    }

    @Test
    public void blockchainEstimatefee() throws Throwable {
        double fee = client.blockchainEstimatefee(6);
        System.out.println("fee = " + fee);
    }

    @Test
    public void blockchainHeadersSubscribe() throws Throwable {
        BlockchainHeader blockchainHeadersSubscribeResponse = client.blockchainHeadersSubscribe();
        System.out.println("blockchainHeadersSubscribeResponse = " + blockchainHeadersSubscribeResponse);
    }

    /**
     * Ignored because requires a block to be mined. To test this, switch to regtest and after starting the test
     * mine a block manually.
     */
    @Ignore()
    @Test
    public void blockchainHeadersSubscribeNotifications() throws Throwable {
        SettableFuture<Boolean> future = SettableFuture.create();
        client.setBlockchainHeadersListener(header -> {
            System.out.println("header = " + header);
            future.set(true);
        });
        client.blockchainHeadersSubscribe();
        client.listenNotifications();
        future.get();
    }

    @Test
    public void blockchainRelayfee() throws Throwable {
        double fee = client.blockchainRelayfee();
        System.out.println("fee = " + fee);
    }

    @Test
    public void blockchainScripthashGetBalance() throws Throwable {
        String address = MainNetParams.get().equals(bitcoinjNetowrkParameters) ? "1A1zP1eP5QGefi2DMPTfTL5SLmv7DivfNa" : "2N7dt3RYBgiCbbTvwnBNHbC6CiWhJkWqTvB";
        String scripthash = Util.scripthash(bitcoinjNetowrkParameters, address);
        BlockchainScripthashGetBalanceResponse blockchainScripthashGetBalanceResponse = client.blockchainScripthashGetBalance(scripthash);
        System.out.println("blockchainScripthashGetBalanceResponse = " + blockchainScripthashGetBalanceResponse);
    }

    @Test
    public void blockchainScripthashGetHistory() throws Throwable {
        String address = MainNetParams.get().equals(bitcoinjNetowrkParameters) ? "1A1zP1eP5QGefi2DMPTfTL5SLmv7DivfNa" : "2N7dt3RYBgiCbbTvwnBNHbC6CiWhJkWqTvB";
        String scripthash = Util.scripthash(bitcoinjNetowrkParameters, address);
        List<BlockchainScripthashGetTxsResponseEntry> blockchainScripthashGetHistoryResponse = client.blockchainScripthashGetHistory(scripthash);
        System.out.println("blockchainScripthashGetHistoryResponse = " + blockchainScripthashGetHistoryResponse);
    }

    @Test
    public void blockchainScripthashGetMempool() throws Throwable {
        String address = MainNetParams.get().equals(bitcoinjNetowrkParameters) ? "1A1zP1eP5QGefi2DMPTfTL5SLmv7DivfNa" : "2N7dt3RYBgiCbbTvwnBNHbC6CiWhJkWqTvB";
        String scripthash = Util.scripthash(bitcoinjNetowrkParameters, address);
        List<BlockchainScripthashGetTxsResponseEntry> blockchainScripthashGetMempoolResponse = client.blockchainScripthashGetMempool(scripthash);
        System.out.println("blockchainScripthashGetMempoolResponse = " + blockchainScripthashGetMempoolResponse);
    }

    @Test
    public void blockchainScripthashListUnspent() throws Throwable {
        String address = MainNetParams.get().equals(bitcoinjNetowrkParameters) ? "1A1zP1eP5QGefi2DMPTfTL5SLmv7DivfNa" : "2N7dt3RYBgiCbbTvwnBNHbC6CiWhJkWqTvB";
        String scripthash = Util.scripthash(bitcoinjNetowrkParameters, address);
        List<BlockchainScripthashListUnspentResponseEntry> blockchainScripthashListUnspentResponse = client.blockchainScripthashListUnspent(scripthash);
        System.out.println("blockchainScripthashListUnspentResponse = " + blockchainScripthashListUnspentResponse);
    }

    @Test
    public void blockchainScripthashSubscribeUnsuscribe() throws Throwable {
        String address = MainNetParams.get().equals(bitcoinjNetowrkParameters) ? "1A1zP1eP5QGefi2DMPTfTL5SLmv7DivfNa" : "2N7dt3RYBgiCbbTvwnBNHbC6CiWhJkWqTvB";
        String scripthash = Util.scripthash(bitcoinjNetowrkParameters, address);
        String blockchainScripthashSubscribeResponse = client.blockchainScripthashSubscribe(scripthash);
        System.out.println("blockchainScripthashSubscribeResponse = " + blockchainScripthashSubscribeResponse);
        boolean blockchainScripthashUnsubscribeResponse1 = client.blockchainScripthashUnsubscribe(scripthash);
        assertTrue(blockchainScripthashUnsubscribeResponse1);
        boolean blockchainScripthashUnsubscribeResponse2 = client.blockchainScripthashUnsubscribe(scripthash);
        assertFalse(blockchainScripthashUnsubscribeResponse2);
    }


    /**
     * Ignored because requires a tx to be sent. To test this, switch to regtest and after starting the test
     * send a tx to the specified address.
     */
    @Ignore()
    @Test
    public void blockchainScripthashSubscribeNotifications() throws Throwable {
        SettableFuture<Boolean> future = SettableFuture.create();
        client.setBlockchainScripthashesListener(status -> {
            System.out.println("status = " + status);
            future.set(true);
        });
        String address = MainNetParams.get().equals(bitcoinjNetowrkParameters) ? "1A1zP1eP5QGefi2DMPTfTL5SLmv7DivfNa" : "2N7dt3RYBgiCbbTvwnBNHbC6CiWhJkWqTvB";
        String scripthash = Util.scripthash(bitcoinjNetowrkParameters, address);
        client.blockchainScripthashSubscribe(scripthash);
        client.listenNotifications();
        future.get();
    }

    @Test
    public void blockchainTransactionBroadcast() throws Throwable {
        String rawTx = MainNetParams.get().equals(bitcoinjNetowrkParameters)
                ? "020000000152acfe124f137156e0054e717d0e3b4a52e579325dae16dab47ff9750c2c732c010000006a47304402204827c862a88aeb3185a27e18f8bd85840110caaef182336406291756569f349f02205eda68c2de0ca9a6443a83c49dae500dd66d27d7f2ed201a3a82fe6167fe23ff012102be022793ace2ebc90149fe7e11795addd561352531daf5f65619e4b3deb044b9ffffffff02006cdc020000000017a9140d56d481961e6834e0db55fa13a348ddd2b3c056876131f701000000001976a914f399bb4e1369f9cfaa4d7db583d2c50a4c15dfd388ac00000000"
                : "020000000001010000000000000000000000000000000000000000000000000000000000000000ffffffff04016b0101ffffffff0200f2052a0100000023210307bb3151854f13858f2c55ab34eba0be993563b7e05d2e4174c4ada628c99002ac0000000000000000266a24aa21a9ede2f61c3f71d1defd3fa999dfa36953755c690689799962b48bebd836974e8cf90120000000000000000000000000000000000000000000000000000000000000000000000000";
        try {
            client.blockchainTransactionBroadcast(rawTx);
            fail("tx already included in a block, broadcast should fail");
        } catch (JsonRpcClientException e) {
            assertTrue(e.getMessage().contains("the transaction was rejected by network rules"));
        }
    }

    @Test
    public void blockchainTransactionGetNoVerbose() throws Throwable {
        String txHash = MainNetParams.get().equals(bitcoinjNetowrkParameters)
                ? "bb0e885919c2b99362118c3749da62be2501a2447f9edfd68a35f57a786f9663"
                : "cbb29b4ab05df7c119b160e19ce57ee35c4071587f65d5d830de5e21105d80fa";
        String blockchainTransactionGetNoVerboseResponse = client.blockchainTransactionGetNoVerbose(txHash);
        System.out.println("blockchainTransactionGetNoVerboseResponse = " + blockchainTransactionGetNoVerboseResponse);
    }

    @Test
    public void blockchainTransactionGetVerbose() throws Throwable {
        String txHash = MainNetParams.get().equals(bitcoinjNetowrkParameters)
                ? "bb0e885919c2b99362118c3749da62be2501a2447f9edfd68a35f57a786f9663"
                : "cbb29b4ab05df7c119b160e19ce57ee35c4071587f65d5d830de5e21105d80fa";
        BlockchainTransactionGetVerboseResponse blockchainTransactionGetVerboseResponse = client.blockchainTransactionGetVerbose(txHash);
        System.out.println("blockchainTransactionGetVerboseResponse = " + blockchainTransactionGetVerboseResponse);
    }

    @Test
    public void blockchainTransactionGetMerkle() throws Throwable {
        String txHash = MainNetParams.get().equals(bitcoinjNetowrkParameters)
                ? "bb0e885919c2b99362118c3749da62be2501a2447f9edfd68a35f57a786f9663"
                : "cbb29b4ab05df7c119b160e19ce57ee35c4071587f65d5d830de5e21105d80fa";
        long height = MainNetParams.get().equals(bitcoinjNetowrkParameters) ? 655149 : 107;
        BlockchainTransactionGetMerkleResponse blockchainTransactionGetMerkleResponse = client.blockchainTransactionGetMerkle(txHash, height);
        System.out.println("blockchainTransactionGetMerkleResponse = " + blockchainTransactionGetMerkleResponse);
    }

    @Test
    public void blockchainTransactionIdFromPosNoMerkle() throws Throwable {
        String expectedTxHash = MainNetParams.get().equals(bitcoinjNetowrkParameters)
                ? "bb0e885919c2b99362118c3749da62be2501a2447f9edfd68a35f57a786f9663"
                : "cbb29b4ab05df7c119b160e19ce57ee35c4071587f65d5d830de5e21105d80fa";
        long height = MainNetParams.get().equals(bitcoinjNetowrkParameters) ? 655149 : 107;
        long txPos = MainNetParams.get().equals(bitcoinjNetowrkParameters) ? 1 : 0;
        String blockchainTransactionIdFromPosNoMerkleResponse = client.blockchainTransactionIdFromPosNoMerkle(height, txPos);
        assertEquals(expectedTxHash, blockchainTransactionIdFromPosNoMerkleResponse);
        System.out.println("blockchainTransactionIdFromPosNoMerkle = " + blockchainTransactionIdFromPosNoMerkleResponse);
    }

    @Test
    public void blockchainTransactionIdFromPosMerkle() throws Throwable {
        String expectedTxHash = MainNetParams.get().equals(bitcoinjNetowrkParameters)
                ? "bb0e885919c2b99362118c3749da62be2501a2447f9edfd68a35f57a786f9663"
                : "cbb29b4ab05df7c119b160e19ce57ee35c4071587f65d5d830de5e21105d80fa";
        long height = MainNetParams.get().equals(bitcoinjNetowrkParameters) ? 655149 : 107;
        long txPos = MainNetParams.get().equals(bitcoinjNetowrkParameters) ? 1 : 0;
        BlockchainTransactionIdFromPosMerkleResponse blockchainTransactionIdFromPosMerkleResponse = client.blockchainTransactionIdFromPosMerkle(height, txPos);
        assertEquals(expectedTxHash, blockchainTransactionIdFromPosMerkleResponse.getTxHash());
        System.out.println("blockchainTransactionIdFromPosMerkleResponse = " + blockchainTransactionIdFromPosMerkleResponse);
    }

    @Test
    public void mempoolGetFeeHistogram() throws Throwable {
        List<MempoolGetFeeHistogramResponseEntry> mempoolGetFeeHistogramResponse = client.mempoolGetFeeHistogram();
        System.out.println("mempoolGetFeeHistogramResponse = " + mempoolGetFeeHistogramResponse);
    }

    @Test
    public void serverBanner() throws Throwable {
        String banner = client.serverBanner();
        assertTrue(banner.length() > 0);
        System.out.println("banner = " + banner);
    }

    @Test
    public void serverDonationAddress() throws Throwable {
        String serverDonationAddress = client.serverDonationAddress();
        assertNotNull(Address.fromString(bitcoinjNetowrkParameters, serverDonationAddress));
        System.out.println("serverDonationAddress = " + serverDonationAddress);
    }

    @Test
    public void serverFeatures() throws Throwable {
        ServerFeaturesResponse serverFeaturesResponse = client.serverFeatures();
        System.out.println("serverFeaturesResponse = " + serverFeaturesResponse);
    }

    @Test
    public void serverPeersSubscribe() throws Throwable {
        List<ServerPeersSubscribeResponseEntry> serverPeersSubscribeResponse = client.serverPeersSubscribe();
        System.out.println("serverPeersSubscribeResponse = " + serverPeersSubscribeResponse);
    }

    @Test
    public void serverPing() throws Throwable {
        client.serverPing();
    }
}
