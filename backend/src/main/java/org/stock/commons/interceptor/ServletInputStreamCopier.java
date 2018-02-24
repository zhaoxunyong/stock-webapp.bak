/*
 * 描述： <描述>
 * 修改人： Dave.zhao
 * 修改时间： Jun 16, 2017
 * 项目： app-core
 */
package org.stock.commons.interceptor;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;

/**
 * <功能简述><br>
 * <功能详细描述>
 * 
 * @author Dave.zhao
 * @version [版本号, Jun 16, 2017]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class ServletInputStreamCopier extends ServletInputStream {
    private ByteArrayInputStream bais;

    public ServletInputStreamCopier(byte[] in) {
        this.bais = new ByteArrayInputStream(in);
    }

    @Override
    public boolean isFinished() {
        return bais.available() == 0;
    }

    @Override
    public boolean isReady() {
        return true;
    }

    @Override
    public void setReadListener(ReadListener readListener) {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public int read() throws IOException {
        return this.bais.read();
    }
}
