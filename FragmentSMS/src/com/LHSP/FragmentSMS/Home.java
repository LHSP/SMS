package com.LHSP.FragmentSMS;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

public class Home extends Activity {

	ListView contactList;
	ArrayList<Contact> contacts;
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		Initialize();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch(item.getItemId()){
		case R.id.action_compose:
			Intent intent = new Intent(this, NewConversation.class);
			this.startActivity(intent);
			break;
		}
		return true;
	}
	
	/**
	 * 
	 */
	public void Initialize()
	{
		MessageList();
	}
	
	public void MessageList()
	{
		contactList = (ListView) findViewById(R.id.contactList);        
        contacts = Contacts.GetNewMessageList(this);        
        contactList.setAdapter(new ContactAdapter(contacts , this));
	}
}
