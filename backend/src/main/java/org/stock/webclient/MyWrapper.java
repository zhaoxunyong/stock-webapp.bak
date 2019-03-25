package org.stock.webclient;

import java.io.IOException;

import com.gargoylesoftware.htmlunit.StringWebResponse;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebConnection;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.WebResponse;
import com.gargoylesoftware.htmlunit.util.WebConnectionWrapper;

public class MyWrapper extends WebConnectionWrapper {

	public static final String[] PATTERNS_TO_SKIP = { "showad.ad" };

	public MyWrapper(WebClient arg0) throws IllegalArgumentException {
		super(arg0);
	}

	@Override
	public WebResponse getResponse(final WebRequest request) throws IOException {
		final java.net.URL urlObject = request.getUrl();
		final String url = urlObject.getPath();
		WebResponse webResponse;
		for (int i = 0; i < PATTERNS_TO_SKIP.length; i++) {
			if (url.endsWith(PATTERNS_TO_SKIP[i])) {
				webResponse = new StringWebResponse("", urlObject);
				return webResponse;
			}
		}
		return super.getResponse(request);
	}

}
