package com.lvonce.lightserver.general.debug;


import org.apache.commons.io.IOUtils;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * this wrapper is needed for restoring the input stream after reading it
 *
 * @author eugen
 */
public class RequestDebugWrapper extends HttpServletRequestWrapper {

    private byte[] data;

    public RequestDebugWrapper(HttpServletRequest request) throws IOException {
        super(request);
        data = IOUtils.toByteArray(request.getInputStream());
    }

    public ServletInputStream getInputStream() {
        final ByteArrayInputStream inputStream = new ByteArrayInputStream(data);
        return new ServletInputStream() {

            @Override
            public int read() throws IOException {
                return inputStream.read();
            }

            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener arg0) {
            }
        };
    }

    public byte[] getData() {
        return data;
    }
}