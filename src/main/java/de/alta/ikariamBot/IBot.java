package de.alta.ikariamBot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.AbstractHttpMessage;
import org.apache.http.params.HttpParams;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class IBot {

	static Logger logger = LogManager.getLogger(IBot.class.getName());
	
	public static void main(String[] args) throws IOException 
	{
		URL url = Thread.currentThread().getContextClassLoader().getResource("log4j.xml");
		if (null == url)
		{
			System.out.println("asdf");
		}
		logger.error("TestTEST");
		url = Thread.currentThread().getContextClassLoader().getResource("test.xml");
		if (null == url)
		{
			System.out.println("test");
		}
		
		 einloggTest();
	     
	     // einloggen
	      
//		Environment environment = new Environment(); 
		
	     // schleife endlos, intervall, alle... 3,5 min... 
	     // ein bischen Ungnauigkeit einbauen, damit.... es zufaelliger aussieht

		 // configuration einlesen
		 
	     // daten aktuallisieren
	     // daten struktur aufbauen aus den gelesenen Daten
	     // z.B. ActionPoints gehöhren in die City-Klass 
	     
	     // alle std -> waren verschiffen
	     
	     // schlafen legen.... für 3,5 min
	}

	private static void einloggTest() throws IOException,
			ClientProtocolException {
		HttpClient httpclient = new DefaultHttpClient();
		 ((DefaultHttpClient)httpclient).getCookieStore().clearExpired(Calendar.getInstance().getTime());
		 
		 // Prepare a request object
		 HttpGet httpget = new HttpGet("http://www.ikariam.de/"); 
		 
		 // Execute the request
		 HttpResponse response = httpclient.execute(httpget);
		 
		 // Examine the response status
		 System.out.println(response.getStatusLine());
		 
		 HttpEntity ent = response.getEntity();
		 printContent(ent);
		 if (ent != null) ent.consumeContent();
		 
		 System.out.println("\n\nLOGIN:");
		 HttpPost loginRequest = new HttpPost("http://s17.de.ikariam.com/index.php?action=loginAvatar&function=login");
		 loginRequest.setHeader("name", "phobos");
		 loginRequest.setHeader("password", "asdf");
		 response = httpclient.execute(loginRequest);
		 System.out.println(response.getStatusLine());
		 printCookies((AbstractHttpMessage)response);
		 
		 List<Cookie> cookies = ((DefaultHttpClient)httpclient).getCookieStore().getCookies();
		 System.out.println("Cookies: ");
		 for (Cookie cookie : cookies) 
			 System.out.println(cookie);
		 HttpEntity loginEntity = response.getEntity();
		 if (loginEntity != null) loginEntity.consumeContent();
		 
//		 System.out.println("\n\nBlockade:");
//		 HttpPost postRequest = new HttpPost("http://s21.de.ikariam.com/index.php?view=blockade&destinationCityId=184306");
//		 HttpParams params = postRequest.getParams();
//		 params.setParameter("cityId", "143860");
//		 response = httpclient.execute(postRequest);
//		 printCookies(postRequest);
//		 ent = response.getEntity();
		 
		 try {
             printContent(ent);
         } catch (RuntimeException ex) {
			 
	        // In case of an unexpected exception you may want to abort
	        // the HTTP request in order to shut down the underlying 
	        // connection and release it back to the connection manager.
	        httpget.abort();
	        ex.printStackTrace();
         }	
         
		 if (ent != null) ent.consumeContent();
		 
		 printHeader(response);
		 printCookies((AbstractHttpMessage)response);

//		 http://s8.ikariam.de/index.php?view=blockade&destinationCityId=184306
//		 http://s8.ikariam.de/index.php?view=plunder&destinationCityId=184306
			 
		 // Get hold of the response entity
//		 HttpEntity entity = response.getEntity();
		 

	        
	     // When HttpClient instance is no longer needed, 
	     // shut down the connection manager to ensure
	     // immediate deallocation of all system resources
	     httpclient.getConnectionManager().shutdown(); 
		     
//		Map<String, List<String> > cookies = null;
//		
//		String content;
//
//		login(cookies);
//		content = request("http://s8.ikariam.de/index.php?view=island&id=1778");
//		System.out.println(content);
	}

	private static void printHeader(HttpResponse response) {
		Header[] headers = response.getAllHeaders();
		for (Header header : headers) {
			System.out.println("Header: " + header);
		}
	}

	private static void printCookies(AbstractHttpMessage msg) {
		Header[] cookies = msg.getHeaders("Set-Cookie");
		for (Header cookie : cookies) {
			System.out.println("cookie: " + cookie);
		}
	}

	private static void printContent(HttpEntity entity) throws IllegalStateException, IOException {
		 // If the response does not enclose an entity, there is no need
		 // to worry about connection release
		 if (entity != null) {
		     InputStream instream = entity.getContent();
		     try {
		         
		         BufferedReader reader = new BufferedReader(
		                 new InputStreamReader(instream));
		         // do something useful with the response
		         StringBuilder source = new StringBuilder();
		         int c;
		         while ((c = reader.read()) != -1)
						source .append((char)c);
//		         logger.debug(source);
		         logger.debug("debug info");
		     } catch (IOException ex) {
		 
		         // In case of an IOException the connection will be released
		         // back to the connection manager automatically
		         ex.printStackTrace();
		         
		     } finally {
		 
		         // Closing the input stream will trigger connection release
		         instream.close();
		         
		     }
		 }
	}

	private static String login(Map<String, List<String>> cookies) {
		String  content = "";
		URL url;
		HttpURLConnection conn;
		
		try {
			url = new URL("http://s8.ikariam.de/index.php?action=loginAvatar&function=login&name=phobos&password=asdf8asdf");

			conn = (HttpURLConnection)url.openConnection();
//			conn = (HttpURLConnection)url.openConnection(new Proxy(Proxy.Type.HTTP, new InetSocketAddress("localhost", 9000)));
//			conn.addRequestProperty("name", "phobos");
//			conn.addRequestProperty("password", "asdf8asdf");
			conn.setRequestMethod("POST");
			InputStream input = url.openStream();
			printHeader(conn.getHeaderFields());
			cookies = extractIkariamCookies(conn);
			System.out.println("\n\n\nIkariam Cookies:");
			printHeader(cookies);
			int c;
			StringBuilder source = new StringBuilder();
			while ((c = input.read()) != -1)
				source .append((char)c);
			content = source.toString();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return content;
	}

	private static String request(String urlStr) {
		String  content = "";
		URL url;
		HttpURLConnection conn;
		
		try {
			url = new URL(urlStr);

			conn = (HttpURLConnection)url.openConnection();
//			conn = (HttpURLConnection)url.openConnection(new Proxy(Proxy.Type.HTTP, new InetSocketAddress("localhost", 9000)));
//			for (String headerKey)
//			conn.addRequestProperty("name", "phobos");
//			conn.addRequestProperty("password", "asdf8asdf");
			conn.setRequestMethod("POST");
			InputStream input = url.openStream();
			int c;
			StringBuilder source = new StringBuilder();
			while ((c = input.read()) != -1)
				source .append((char)c);
			content = source.toString();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return content;
	}

	private static Map<String, List<String>> extractIkariamCookies(
			HttpURLConnection conn) {
		// TODO Auto-generated method stub
		Map<String, List<String> > headers = conn.getHeaderFields();
		Map<String, List<String> > ikariamHeaders;
		List<String> values;
		boolean isIkariamCookie;
		
		ikariamHeaders = new HashMap<String, List<String>>();
		
		for (String headerName : headers.keySet()) {
			isIkariamCookie = false;
			if (headerName != null
					&& headerName.toLowerCase().contains(
							"Set-Cookie".toLowerCase())) {
				values = headers.get(headerName);
				for (String val : values) {
					if (val.toLowerCase().contains("ikariam")) {
						ikariamHeaders.put(headerName, values);
					}
				}
			}
		}
		return ikariamHeaders;
	}

	private static void printHeader(Map<String, List<String> > headers) {
		// TODO Auto-generated method stub
		for (String headerName : headers.keySet()) {
			System.out.println("name: " + headerName);
			List<String> values = headers.get(headerName);
			for (String val : values) {
				System.out.print("  " + val);
			}
			System.out.println();
		}
	}
	
}
