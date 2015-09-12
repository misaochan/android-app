package com.lim.json;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.ContactsContract.Contacts;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;


/**
 * Class implements a list listener
 */
public class StaffListListener implements OnItemClickListener {

	ArrayList<String> items;
	Activity activity;
	
	public StaffListListener(ArrayList<String> items, Activity activity) {
		this.items = items;
		this.activity  = activity;
	}
	
	/**
	 * Start a browser with url from the rss item.
	 */
	public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
		Intent i = new Intent(Intent.ACTION_VIEW);
		//Uri.parse(listItems.get(pos).getLink())
		//items.get(pos) returns the UPI needed. Append to http://www.cs.auckland.ac.nz/our_staff/vcard.php?upi=
		
		Uri.Builder b = Uri.parse("http://www.cs.auckland.ac.nz/our_staff/vcard.php").buildUpon();
		b.appendQueryParameter("upi", items.get(pos));
		Uri uri = b.build();
		i.setData(uri);
		
		//insert content resolver here
		
		Log.d("URL of staff", uri.toString());
		activity.startActivity(i);		
	}
	
}
