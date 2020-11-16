package org.electrumj;

import org.bitcoinj.core.Address;
import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.core.Sha256Hash;
import org.bitcoinj.params.MainNetParams;
import org.bitcoinj.script.Script;
import org.bitcoinj.script.ScriptBuilder;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Random utility methods.
 */
public class Util {

    /**
     * Obtains the electrum's scriptshash given a mainnet address.
     * See <a href="https://electrumx-spesmilo.readthedocs.io/en/latest/protocol-basics.html#script-hashes"Electrum documentation on script hash</a>.
     * @param address The address
     * @return The scripthash associated with the supplied address
     */
    public static String scripthash(String address) {
        return scripthash(MainNetParams.get(), address);
    }

    /**
     * Obtains the electrum's scriptshash given an address.
     * See <a href="https://electrumx-spesmilo.readthedocs.io/en/latest/protocol-basics.html#script-hashes"Electrum documentation on script hash</a>.
     * @param bitcoinjNetowrkParameters The network being used: e.g. regtest, mainnet, etc.
     * @param address The address
     * @return The scripthash associated with the supplied address
     * @throws Throwable
     */
    public static String scripthash(NetworkParameters bitcoinjNetowrkParameters, String address) {
        Address addressBitcoinj = Address.fromString(bitcoinjNetowrkParameters, address);
        Script script = ScriptBuilder.createOutputScript(addressBitcoinj);
        byte[] scriptArray = script.getProgram();
        byte[] scriptHash = Sha256Hash.hash(scriptArray);
        Sha256Hash reversedHash = Sha256Hash.wrapReversed(scriptHash);
        return reversedHash.toString();
    }

    /**
     * Obtains a Type instance representing a List of the supplied class, e.g.<code>List<String></code>.
     * @param clazz The parametrized class, e.g. String.class
     * @return a Type instance, e.g. representing <code>List<String></code>
     */
    public static Type getParametrizedListType(Class clazz) {
        return new ParameterizedType() {
            @Override
            public String getTypeName() {
                return "java.util.List<" + clazz.getTypeName() + ">";
            }

            @Override
            public Type[] getActualTypeArguments() {
                return new Type[]{clazz};
            }

            @Override
            public Type getRawType() {
                return List.class;
            }

            @Override
            public Type getOwnerType() {
                return null;
            }
        };
    }
}
