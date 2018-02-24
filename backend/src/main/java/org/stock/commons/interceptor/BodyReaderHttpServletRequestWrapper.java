/*
 * 描述： <描述>
 * 修改人： Dave.zhao
 * 修改时间： Jun 16, 2017
 * 项目： app-core
 */
package org.stock.commons.interceptor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.springframework.util.StreamUtils;

import com.google.common.base.Charsets;

/**
 * BodyReaderHttpServletRequestWrapper
 * 
 * @see http://www.jianshu.com/p/f7f8237861e7
 * @author Dave.zhao
 * @version [版本号, Jun 16, 2017]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class BodyReaderHttpServletRequestWrapper extends HttpServletRequestWrapper {
    private final byte[] bodyCopier;

    public BodyReaderHttpServletRequestWrapper(HttpServletRequest request) throws IOException {
        super(request);   //Anchor1
        bodyCopier = StreamUtils.copyToByteArray(request.getInputStream());
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(this.getInputStream()));
    }

    @Override
    public ServletInputStream getInputStream() throws IOException{
        return new ServletInputStreamCopier(bodyCopier);
    }

    public byte[] getCopy() {
        return this.bodyCopier;
    }

    public String getBody() throws UnsupportedEncodingException {
        return new String(this.bodyCopier, Charsets.UTF_8);
    }
}
