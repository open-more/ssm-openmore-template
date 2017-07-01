package org.openmore.filter;

import org.apache.commons.io.IOUtils;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;

/**
 * Created by michael on 2017/6/17.
 */
public class BodyReadHttpServletRequestWrapper extends HttpServletRequestWrapper {

    private String jsonPararms;

    public BodyReadHttpServletRequestWrapper(HttpServletRequest request) throws IOException {
        super(request);
        ServletInputStream stream = this.getRequest().getInputStream();
        jsonPararms = IOUtils.toString(stream, "UTF-8");
    }


    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }

    @Override
    public ServletInputStream getInputStream() {
        byte[] buffer = null;
        try {
            buffer = jsonPararms.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        final ByteArrayInputStream bais = new ByteArrayInputStream(buffer);
        return new ServletInputStream() {
            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener readListener) {
            }

            @Override
            public int read() throws IOException {
                return bais.read();
            }
        };
    }

    public String getJsonPararms() {
        return jsonPararms;
    }

    public void setJsonPararms(String jsonPararms) {
        this.jsonPararms = jsonPararms;
    }

}