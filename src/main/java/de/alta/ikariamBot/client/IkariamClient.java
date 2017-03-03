package de.alta.ikariamBot.client;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * Kappslet den Http-Zugriff auf Ikariam.
 * @author alta
 *
 */
public class IkariamClient {

	HttpURLConnection connection;
	
	/**
	 * Einloggen
	 * @param cookies
	 * @return
	 */
	public String login(Map<String, List<String> > cookies) {
		String  content = "";
		
		try {
			final URL url = new URL("http://s17-de.ikariam.gameforge.com/index.php?action=loginAvatar&function=login&name=phobos&password=hale88");
			
			connection = (HttpURLConnection)url.openConnection();
			connection.setRequestMethod("POST");
			final InputStream input = url.openStream();
//			printHeader(connection.getHeaderFields());
			cookies = extractIkariamCookies(connection);
//			System.out.println("\n\n\nIkariam Cookies:");
//			printHeader(cookies);
			int c;
			final StringBuilder source = new StringBuilder();
			while ((c = input.read()) != -1)
				source .append((char)c);
			content = source.toString();
			final boolean erfolgreich = content.toLowerCase().contains("create dataset for city");
		    System.out.println("Einloggen war" + (!erfolgreich ? " nicht" : "") + " erfolgreich");
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
