package org.electrumj;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * OutputStream wrapper that appends a '\n' char after each write invocation.
 * Electrumx expects a '\n' char after each request.
 */
public class AppendNewLineOutputStream extends FilterOutputStream {
    public AppendNewLineOutputStream(OutputStream out) {
        super(out);
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        super.write(b, off, len);
        write('\n');
    }
}
