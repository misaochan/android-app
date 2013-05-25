package com.lim.json;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.zip.GZIPInputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.util.Log;

public class JSONparser {
	private InputStream is = null;
	private String json = "";
	private String url;
	
	public JSONparser(String url) {
		this.url = url;
	}
	
	protected String parse() {
		try {
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet(url);
            HttpResponse httpResponse = httpClient.execute(httpGet);
            HttpEntity httpEntity = httpResponse.getEntity();
            is = httpEntity.getContent();
		}
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } 
		catch (ClientProtocolException e) {
            e.printStackTrace();
        } 
		catch (IOException e) {
            e.printStackTrace();
        }
		
		try {
			InputStream inputStream = is;
			GZIPInputStream input = new GZIPInputStream(inputStream);
			InputStreamReader reader = new InputStreamReader(input);
			BufferedReader in = new BufferedReader(reader);
			StringBuilder sb = new StringBuilder();
			String line = null;
			
			while ((line = in.readLine()) != null) {
				sb.append(line);
			}
			
			is.close();
			json = sb.toString();
			
		} 
		
		catch (Exception e) {
			Log.e("Buffer Error", "Error converting result " + e.toString());
    	}
		
		return json;
	}

}
