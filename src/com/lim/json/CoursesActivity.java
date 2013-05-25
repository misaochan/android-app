package com.lim.json;

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

import com.lim.assignment.R;
import com.lim.assignment.R.id;
import com.lim.assignment.R.layout;

import android.app.ListActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;


public class CoursesActivity extends ListActivity{

		ArrayList<HashMap<String, String>> courseList = new ArrayList<HashMap<String, String>>();
        //static InputStream is = null;
        private static String url = "http://redsox.tcs.auckland.ac.nz/734A/CSService.svc/courses";
        JSONArray courses = null;
        static JSONObject jObj = null;
        //static String json = "";
        private String json = "";
        
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_courses);
            new GetCoursesTask().execute();
        }

        private class GetCoursesTask extends AsyncTask<URL, Void, JSONObject> {
        		
            @Override
            protected JSONObject doInBackground(URL... urls) {
               	JSONparser jsonParser = new JSONparser(url);
               	json = jsonParser.parse();
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