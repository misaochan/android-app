package com.lim.assignment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.zip.GZIPInputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ListActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;


public class CoursesActivity extends ListActivity{

		ArrayList<HashMap<String, String>> courseList = new ArrayList<HashMap<String, String>>();
        static InputStream is = null;
        private static String url = "http://redsox.tcs.auckland.ac.nz/734A/CSService.svc/courses";
        JSONArray courses = null;
        static JSONObject jObj = null;
        static String json = "";

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_courses);
            new Task().execute();
        }

        private class Task extends AsyncTask<URL, Void, JSONObject> {
        	
        	protected void JSONparser(String url) {
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
        	}
        	
            @Override
            protected JSONObject doInBackground(URL... urls) {
               	JSONparser(url);
               	//convert parsed string to a JSON object
            	try {
            		courses = new JSONArray(json);

            		for (int i = 0; i < courses.length(); i++) {
            			JSONObject p = courses.getJSONObject(i);

            			String code = p.getString("codeField");
            			String semester = p.getString("semesterField");
            			String title = p.getString("titleField");
            			
            			HashMap<String, String> map = new HashMap<String, String>();
            			
            			map.put("codeField", code);
            			map.put("semesterField", semester);
            			map.put("titleField", title);
            			
            			courseList.add(map);            			
            		}
            	} 
            	catch (JSONException e) {
            		Log.e("JSON Parser", "Error parsing data " + e.toString());
            	} 

            	return jObj;
            }

            protected void onPostExecute(JSONObject json) {
            	ListAdapter adapter = new SimpleAdapter(getApplicationContext(), courseList, R.layout.courses_list, new String[] {"codeField", "semesterField", "titleField" }, new int[] { R.id.code, R.id.semester, R.id.title });
                setListAdapter(adapter);
            	
            }
        }
}