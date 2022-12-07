package ar.com.scienza.webservice;

import java.io.IOException;
import java.net.HttpURLConnection;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.ws.transport.http.HttpUrlConnectionMessageSender;

import sun.misc.BASE64Encoder;


public class WebServiceMessageSender extends HttpUrlConnectionMessageSender {

	@Value("${SI_OS_SCIENZA.USER}")
    String user;

	@Value("${SI_OS_SCIENZA.PASSWORD}")
    String password;
	
	
	@Override
	protected void prepareConnection(HttpURLConnection connection) throws IOException {
		
		BASE64Encoder encoder = new BASE64Encoder();
		String userpassword = user + ":" + password;
		String encodedAuthorization = encoder.encode(userpassword.getBytes());
		connection.setRequestProperty("Authorization", "Basic " + encodedAuthorization);

		super.prepareConnection(connection);
	}
}