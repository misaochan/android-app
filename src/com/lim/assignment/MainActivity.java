package com.lim.assignment;

import com.lim.json.CoursesActivity;
import com.lim.rss.EventsActivity;
import com.lim.rss.NewsActivity;
import com.lim.rss.SeminarsActivity;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		Button coursesButton = (Button) findViewById(R.id.courses_button);
		coursesButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				Intent coursesIntent = new Intent(arg0.getContext(), CoursesActivity.class);
				startActivityForResult(coursesIntent, 0);
			}
		});
		
		Button seminarsButton = (Button) findViewById(R.id.seminars_button);
		seminarsButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				Intent SeminarsIntent = new Intent(arg0.getContext(), SeminarsActivity.class);
				startActivityForResult(SeminarsIntent, 0);
			}
		});
		
		Button eventsButton = (Button) findViewById(R.id.events_button);
		eventsButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				Intent EventsIntent = new Intent(arg0.getContext(), EventsActivity.class);
				startActivityForResult(EventsIntent, 0);
			}
		});
		
		Button newsButton = (Button) findViewById(R.id.news_button);
		newsButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				Intent NewsIntent = new Intent(arg0.getContext(), NewsActivity.class);
				startActivityForResult(NewsIntent, 0);
			}
		});
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
