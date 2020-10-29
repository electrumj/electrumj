package org.electrumj;

import org.electrumj.dto.BlockchainScripthashStatus;

/**
 * Classes interested in listening to new scripthashes status changes should implement this interface.
 */
public interface BlockchainScripthashesListener {
    void notifyNewScripthashStatus(final BlockchainScripthashStatus status);
}
