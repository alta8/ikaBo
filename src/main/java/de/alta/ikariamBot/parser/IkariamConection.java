package de.alta.ikariamBot.parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.List;

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

public class IkariamConection {

	public void login() throws ClientProtocolException, IOException
	{
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
		 HttpPost loginRequest = new HttpPost("http://s21.de.ikariam.com/index.php?action=loginAvatar&function=login");
		 loginRequest.setHeader("name", "phobos");
		 loginRequest.setHeader("password", "asdfasdf");
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
	}


	private static void printCookies(AbstractHttpMessage msg) {
		Header[] cookies = msg.getHeaders("Set-Cookie");
		for (Header cookie : cookies) {
			System.out.println("cookie: " + cookie);
		}
	}

	private static void printHeader(HttpResponse response) {
		Header[] headers = response.getAllHeaders();
		for (Header header : headers) {
			System.out.println("Header: " + header);
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
		         System.out.println(source);
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
	
	
}
