# electrumj

Java client for the electrum protocol.



Sample usage:

```
    ElectrumClient client = new ElectrumClient("electrumx-core.1209k.com", 50002);
    client.open();
    String scripthash = Util.scripthash("1A1zP1eP5QGefi2DMPTfTL5SLmv7DivfNa");
    BlockchainScripthashGetBalanceResponse response = client.blockchainScripthashGetBalance(scripthash);
    client.close();
    System.out.println("Confirmed: " + response.getConfirmed());
    System.out.println("Unconfirmed: " + response.getUnconfirmed());
```

Should print something like this:

```
Confirmed: 1834354006
Unconfirmed: 4348
```


See `ElectrumClientTest` for more examples.

See:<br/>
[Protocol documentation](https://electrumx-spesmilo.readthedocs.io/en/latest/protocol-methods.html)
<br/>

Used libraries:<br/>
[jsonrpc4j](https://github.com/briandilley/jsonrpc4j) <br/>
[jackson](https://github.com/FasterXML/jackson)
<br/>

Additional resources:<br/>
[Electrum documentation](https://electrum.readthedocs.io/en/latest/faq.html) <br/>
[electrum source code](https://github.com/spesmilo/electrum) <br/>
[Electrumx documentation](https://electrumx-spesmilo.readthedocs.io/en/latest/) <br/>
[electrumx source code](https://github.com/spesmilo/electrumx) <br/>
[Electrum go client](https://github.com/checksum0/go-electrum)

 