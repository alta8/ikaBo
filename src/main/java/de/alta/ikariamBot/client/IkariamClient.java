package de.alta.ikariamBot.client;

import java.io.IOException;
import java.io.InputStream;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
/**
 * Kappslet den Http-Zugriff auf Ikariam.
 * @author alta
 *
 */
public class IkariamClient {

	HttpURLConnection connection;
	private Map<String, List<String>> cookies;
	
	{
		CookieHandler.setDefault(new CookieManager());
	}
	public String login() throws IOException {
		final String loginUrl = "http://s17-de.ikariam.gameforge.com/index.php";
		final Map<String, String> params = new HashMap<>();
		params.put("action", "loginAvatar");
		params.put("function", "login");
		params.put("name", "phobos");
		params.put("password", "hale88");
		final InputStream inputStream = connectToUrl(loginUrl, params);
		int c;
		final StringBuilder stringBuilder = new StringBuilder();
		while ((c = inputStream.read()) != -1)
			stringBuilder.append((char)c);
		final String content = stringBuilder.toString();
		return content;
//		String  content = "";
//		
//		try {
//			final URL url = new URL("http://s17-de.ikariam.gameforge.com/index.php?action=loginAvatar&function=login&name=phobos&password=hale88");
//			connection = (HttpURLConnection)url.openConnection();
//			connection.setRequestMethod("GET");
//			final InputStream input = url.openStream();
//			printHeader(connection.getHeaderFields());
//			cookies = extractIkariamCookies(connection);
//			System.out.println("\n\n\nIkariam Cookies:");
//			printHeader(cookies);
//			int c;
//			final StringBuilder source = new StringBuilder();
//			while ((c = input.read()) != -1)
//				source .append((char)c);
//			content = source.toString();
//			final boolean erfolgreich = content.toLowerCase().contains("create dataset for city");
//		    System.out.println("Einloggen war" + (!erfolgreich ? " nicht" : "") + " erfolgreich");
//		} catch (MalformedURLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		return content;
	}
	
	public InputStream connectToUrl(final String urlStr) throws IOException {
		final URL url = new URL(urlStr);
		connection = (HttpURLConnection)url.openConnection();
		connection.setRequestMethod("GET");
		String cookieString = cookieString(cookies);
		connection.setRequestProperty("Cookie", cookieString);
		final InputStream input = connection.getInputStream();
		
		return input;
	}

	public InputStream connectToUrl(final String urlStr, Map<String, String> params) throws IOException
	{
		final String paramsStr = params.entrySet().stream().map(e->e.getKey()+"="+e.getValue()).collect(Collectors.joining("&"));
		final URL url = new URL(urlStr+"?"+paramsStr);
		final HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		final int status = conn.getResponseCode();
		System.out.println("Status: " +  status);
		return conn.getInputStream();
	}

	private static Map<String, List<String>> extractIkariamCookies(
			HttpURLConnection conn) {
		// TODO Auto-generated method stub
		
			
		Map<String, List<String> > headers = conn.getHeaderFields();
		List<String> values;
		Map<String, List<String> > cookies = new HashMap<String, List<String>>();
		
		for (String headerName : headers.keySet()) {
			if (headerName != null
					&& headerName.toLowerCase().contains(
							"Set-Cookie".toLowerCase())) {
				values = headers.get(headerName);
				for (String val : values) {
//					if (val.toLowerCase().contains("ikariam")) {
						cookies.put(headerName, values);
//					}
				}
			}
		}
		return cookies;
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
	
	private static String cookieString(Map<String, List<String> > headers) {
		return headers.values().stream().flatMap(Collection::stream).collect(Collectors.joining(";"));
	}

}
