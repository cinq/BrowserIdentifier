package com.cinq.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class BrowserIdentifier {

	private static List<String> uastring;
	private static final String charset = "UTF-8";

	public static void init(){
		uastring = new ArrayList<String>();

		uastring.add("Mozilla/4.0(Windows76.1)Java/1.8.0_0");
		uastring.add("Mozilla/4.75[en](X11,U;OpenVAS)");
		uastring.add("Mozilla/5.0(compatible;MSIE9.0;WindowsNT6.1;WOW64;Trident/5.0;KTXNB573127785A48102T2665775P5");
		uastring.add("Mozilla/5.0(compatible;MSIE9.0;WindowsNT6.1;WOW64;Trident/5.0;KTXNB573127785A48102T2665775P2");
		uastring.add("Mozilla/4.0(WindowsServer2008R26.1)Java/1.7.0_4");
	}

	public static void main(String args[]) throws IOException {
		String str;
		init();
		for ( String ua : uastring ) {
			HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(Config.apiUrl).openConnection();
			httpURLConnection.setDoOutput(true); // forces the METHOD to POST
			httpURLConnection.setDoInput(true);
			httpURLConnection.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=" + charset);
			httpURLConnection.setRequestProperty("Accept-Charset", charset);
			OutputStream out = httpURLConnection.getOutputStream();
			str = Config.ukey + "=" + Config.key + "&" + Config.uagent + "=" + URLEncoder.encode(ua, charset);
			System.out.println("Posting with: " + str);
			out.write(str.getBytes(charset));
			BufferedReader in = new BufferedReader( new InputStreamReader(httpURLConnection.getInputStream()) );
			while ( (str = in.readLine() ) != null ) {
				System.out.print(str);
			}
		}
	}

}
