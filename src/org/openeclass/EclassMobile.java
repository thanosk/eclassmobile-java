package org.openeclass;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

public class EclassMobile {

	private static final String mUsername = "xxxxxxx";
	private static final String mPassword = "yyyyyyy";

	public static void main(String[] args) {

		try {
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost("https://myeclass/modules/mobile/mlogin.php");
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			nvps.add(new BasicNameValuePair("uname", mUsername));
			nvps.add(new BasicNameValuePair("pass", mPassword));
			httppost.setEntity(new UrlEncodedFormEntity(nvps));

			System.out.println("twra tha kanei add");
			System.out.println("prin to response");

			HttpResponse response = httpclient.execute(httppost);
			StatusLine status = response.getStatusLine();

			if (status.getStatusCode() != 200)
				System.out.println("Invalid response from server: " + status.toString());

			System.out.println("meta to response");

			Header[] head = response.getAllHeaders();

			for (int i = 0; i < head.length; i++)
				System.out.println("to head exei " + head[i]);

			System.out.println("Login form get: " + response.getStatusLine());

			HttpEntity entity = response.getEntity();
			InputStream ips = entity.getContent();
			BufferedReader buf = new BufferedReader(new InputStreamReader(ips, "UTF-8"), 1024);

			StringBuilder sb = new StringBuilder();
			String s = "";

			while ((s = buf.readLine()) != null) {
				sb.append(s);
			}
			System.out.println("to sb einai " + sb.toString());

		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
