package proxy;

import java.io.IOException;

import org.apache.http.HttpHost;

public class ElementalReverseProxy {
	
	public static final String HTTP_IN_CONN = "http.proxy.in-conn";
	public static final String HTTP_OUT_CONN = "http.proxy.out-conn";
	public  static final String HTTP_CONN_KEEPALIVE = "http.proxy.conn-keepalive";
	
	public static void main(String[] args) throws IOException {
		
		final HttpHost target = new HttpHost("localhost",8080);
		
		final Thread t = new RequestListenerThread(9090, target);
		t.setDaemon(false);
		t.start();
		
		
	}

}
