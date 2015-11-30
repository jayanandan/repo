package proxy;

import static proxy.ElementalReverseProxy.HTTP_CONN_KEEPALIVE;
import static proxy.ElementalReverseProxy.HTTP_OUT_CONN;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.ConnectionReuseStrategy;
import org.apache.http.HttpClientConnection;
import org.apache.http.HttpException;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.DefaultConnectionReuseStrategy;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.HttpCoreContext;
import org.apache.http.protocol.HttpProcessor;
import org.apache.http.protocol.HttpRequestExecutor;
import org.apache.http.protocol.HttpRequestHandler;

public class ProxyRequestHandler implements HttpRequestHandler {

	private final HttpHost target;
	private final HttpProcessor httpproc;
	private final HttpRequestExecutor httpexecutor;
	private final ConnectionReuseStrategy connStrategy;

	public ProxyRequestHandler(final HttpHost target, final HttpProcessor httpproc, final HttpRequestExecutor httpexecutor) {
		super();
		this.target = target;
		this.httpproc = httpproc;
		this.httpexecutor = httpexecutor;
		this.connStrategy = DefaultConnectionReuseStrategy.INSTANCE;
	}

	@Override
	public void handle(final HttpRequest request, final HttpResponse response, final HttpContext context)
			throws HttpException, IOException {

		final HttpClientConnection conn = (HttpClientConnection) context.getAttribute(HTTP_OUT_CONN);

		context.setAttribute(HttpCoreContext.HTTP_CONNECTION, conn);
		context.setAttribute(HttpCoreContext.HTTP_TARGET_HOST, this.target);

		System.out.println(">> Request URI: " + request.getRequestLine().getUri());

		final HttpResponse targetResponse = this.httpexecutor.execute(request, conn, context);
		
		BufferedHttpEntity bufferedHttpEntity = new BufferedHttpEntity(targetResponse.getEntity());

		response.setStatusLine(targetResponse.getStatusLine());
		response.setHeaders(targetResponse.getAllHeaders());
		response.setEntity(bufferedHttpEntity);

		System.out.println("<< Response: " + bufferedHttpEntity.getContentType());
		
		InputStream content = bufferedHttpEntity.getContent();
		byte[] respHTML = new byte[content.available()];
		content.read(respHTML);
		
		System.out.println("<< Response: " + new String(respHTML));

		final boolean keepalive = this.connStrategy.keepAlive(response, context);
		context.setAttribute(HTTP_CONN_KEEPALIVE, new Boolean(keepalive));
	}

}
