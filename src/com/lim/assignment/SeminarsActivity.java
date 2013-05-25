package com.lim.assignment;

import java.util.List;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class SeminarsActivity extends Activity {

	private SeminarsActivity local;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_seminar);
		local = this;
		
		GetRSSDataTask task = new GetRSSDataTask();
		task.execute("http://www.cs.auckland.ac.nz/uoa/home/template/events_feed.rss?category=seminars/");
		
		// Debug the thread name
		//Log.d("MyRssReader", Thread.currentThread().getName());
	}
	
	private class GetRSSDataTask extends AsyncTask<String, Void, List<RssItem> > {
		@Override
		protected List<RssItem> doInBackground(String... urls) {
			
			// Debug the task thread name
			//Log.d("MyRssReader", Thread.currentThread().getName());
			
			try {
				RssReader rssReader = new RssReader(urls[0]);
				return rssReader.getItems();
			
			} catch (Exception e) {
				Log.e("MyRssReader", e.getMessage());
			}
			
			return null;
		}
		
		@Override
		protected void onPostExecute(List<RssItem> result) {
			
			ListView itcItems = (ListView) findViewById(R.id.rssList);
			ArrayAdapter<RssItem> adapter = new ArrayAdapter<RssItem>(local,android.R.layout.simple_list_item_1, result);
			itcItems.setAdapter(adapter);
			
			itcItems.setOnItemClickListener(new ListListener(result, local));
		}
	}
	
	
}