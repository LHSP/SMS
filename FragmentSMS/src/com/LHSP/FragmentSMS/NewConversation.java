package com.LHSP.FragmentSMS;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AutoCompleteTextView;

public class NewConversation extends Activity {
//
//	String[] androidBooks = 
//		{
//		"Hello, Android - Ed Burnette",
//		"Professional Android 2 App Dev - Reto Meier",
//		"Unlocking Android - Frank Ableson",
//		"Android App Development - Blake Meike",
//		"Pro Android 2 - Dave MacLean",
//		"Beginning Android 2 - Mark Murphy",
//		"Android Programming Tutorials - Mark Murphy",
//		"Android Wireless App Development - Lauren Darcey",
//		"Pro Android Games - Vladimir Silva",
//	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_conversation);
		// Show the Up button in the action bar.
		setupActionBar();
		
//		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, androidBooks);
		AutoCompleteTextView acTextView = (AutoCompleteTextView) findViewById(R.id.textTo);
		acTextView.setThreshold(2);
		acTextView.setAdapter(Contacts.GetPhoneContacts(this));
	}

	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.new_conversation, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
