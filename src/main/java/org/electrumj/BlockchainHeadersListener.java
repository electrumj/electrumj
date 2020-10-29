package org.electrumj;

import org.electrumj.dto.BlockchainHeader;

/**
 * Classes interested in listening to new blockchain headers should implement this interface.
 */
public interface BlockchainHeadersListener {
    void notifyNewBlockchainHeader(final BlockchainHeader header);
}
