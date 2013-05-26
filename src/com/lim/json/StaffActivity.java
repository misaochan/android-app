package com.lim.json;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.lim.assignment.R;
import com.lim.json.CoursesActivity;
import com.lim.rss.EventsActivity;
import com.lim.rss.ListListener;
import com.lim.rss.RssItem;

import android.app.ListActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class StaffActivity extends ListActivity {

    private static String url = "http://redsox.tcs.auckland.ac.nz/734A/CSService.svc/people";
    JSONArray staff = null;
    static JSONObject jObj = null;
    private String json = "";
    ArrayList<String> items = new ArrayList<String>();
    private StaffActivity local;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff);
        local = this;
        
        new GetStaffTask().execute();
        
    }

    private class GetStaffTask extends AsyncTask<URL, Void, JSONObject> {
    		
        @Override
        protected JSONObject doInBackground(URL... urls) {
           	JSONparser jsonParser = new JSONparser(url);
           	json = jsonParser.parse();
           	
        	try {
        		staff = new JSONArray(json);

        		for (int i = 0; i < staff.length(); i++) {
        			JSONObject p = staff.getJSONObject(i);
        			String map = p.getString("uPIField");
                    items.add(map);
                    
                    //map variable contains the UPI needed. Append to http://www.cs.auckland.ac.nz/our_staff/vcard.php?upi=
        		}
        	} 
        	catch (JSONException e) {
        		Log.e("JSON Parser", "Error parsing data " + e.toString());
        	} 

        	return jObj;
        }

        protected void onPostExecute(JSONObject json) {
        	
        	ListView myListView = (ListView)findViewById(android.R.id.list);
            myListView.setAdapter(new ArrayAdapter<String>(getApplicationContext(), R.layout.list_black_text, R.id.list_content, items));       	
            
            myListView.setOnItemClickListener(new StaffListListener(items, local));
        }
    }
}
