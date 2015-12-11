package proxy;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.http.HttpHost;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.impl.DefaultBHttpClientConnection;
import org.apache.http.impl.DefaultBHttpServerConnection;
import org.apache.http.protocol.HttpRequestExecutor;
import org.apache.http.protocol.HttpService;
import org.apache.http.protocol.ImmutableHttpProcessor;
import org.apache.http.protocol.UriHttpRequestHandlerMapper;

public class RequestListenerThread extends Thread {

	private final HttpHost target;
	private final ServerSocket serversocket;
	 private final HttpService httpService;

	public RequestListenerThread(final int port, final HttpHost target) throws IOException {

		this.serversocket = new ServerSocket(port);
		this.target = target;

		final ImmutableHttpProcessor inHttpProcessor = new ImmutableHttpProcessor(
				new HttpRequestInterceptor[] { /*new RequestContent(), new RequestTargetHost(), new RequestConnControl(),
						new RequestUserAgent("Test/1.1"), new RequestExpectContinue(true)*/ });

		final ImmutableHttpProcessor outHttpProcessor = new ImmutableHttpProcessor(new HttpResponseInterceptor[] { /*new ResponseDate(), new ResponseServer("Test/1.1"),
				new ResponseContent(), new ResponseConnControl()*/ });
		
		HttpRequestExecutor executor = new HttpRequestExecutor();
		
		final UriHttpRequestHandlerMapper mapper = new UriHttpRequestHandlerMapper();
		mapper.register("*", new ProxyRequestHandler(target, outHttpProcessor, executor));

		this.httpService = new HttpService(inHttpProcessor, mapper);
		
	}

	@Override
	public void run() {
		System.out.println("Listening on port " + this.serversocket.getLocalPort());
        while (!Thread.interrupted()) {
            try {
                final int bufsize = 8 * 1024;
                // Set up incoming HTTP connection
                final Socket insocket = this.serversocket.accept();
                final DefaultBHttpServerConnection inconn = new DefaultBHttpServerConnection(bufsize);
                System.out.println("Incoming connection from " + insocket.getInetAddress());
                inconn.bind(insocket);

                // Set up outgoing HTTP connection
                final Socket outsocket = new Socket(this.target.getHostName(), this.target.getPort());
                final DefaultBHttpClientConnection outconn = new DefaultBHttpClientConnection(bufsize);
                outconn.bind(outsocket);
                System.out.println("Outgoing connection to " + outsocket.getInetAddress());

                // Start worker thread
                final Thread t = new ProxyThread(this.httpService, inconn, outconn);
                t.setDaemon(true);
                t.start();
            } catch (final InterruptedIOException ex) {
                break;
            } catch (final IOException e) {
                System.err.println("I/O error initialising connection thread: "
                        + e.getMessage());
                break;
            }
        }
    }

}
