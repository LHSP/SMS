package com.LHSP.FragmentSMS;

import java.util.ArrayList;
import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
//		Contacts contacts = new Contacts(this);
//		contacts.ContactsLoader();
//		contactList = (ListView) findViewById(R.id.contactList);
//		contactList.setAdapter(Contacts.GetPhoneContacts(this));
//		ContactsArrayAdapter();
		MessageListTest();
//		ContactsArrayAdapter();
	}
	
	
	public void MessageListTest()
	{
		contactList = (ListView) findViewById(R.id.contactList);
        
        contacts = new ArrayList<Contact>();
            
        Contact contact;        

		for(int i = 0; i < 50; i++)
		{
			contact = new Contact();
			contact.setContactName("Contato" + String.format("%02d", i));
			contact.setContactPhoto(R.drawable.ic_search);
			contact.setLastMessage("Esta � uma mensagem de teste.");
			contact.setLastMessageTime("12:" + String.format("%02d", i));
			contact.setMessageCount(new Random().nextInt(1000));
			contacts.add(contact);
			Log.v("Contact", "Name: " + contact.contactName);
			Log.v("Contact", "Photo: " + contact.contactPhoto);
			Log.v("Contact", "Last Message: \"" + contact.lastMessage + "\" from " + contact.lastMessageTime);
			Log.v("Contact", "MessageCount: " + contact.messageCount);
		}
        
//        contact = new Contact();
//        contact.setContactPhoto(R.drawable.ic_launcher);
//        contact.setContactName("Bob");
//        contact.setLastMessage("Lorem ipsum dolor sit amet.");
//        contact.setLastMessageTime("12:12");
//        contact.setMessageCount(10);
//        contacts.add(contact);
//		Log.v("Contact", "Name: " + contact.contactName);
//		Log.v("Contact", "Photo: " + contact.contactPhoto);
//		Log.v("Contact", "Last Message: \"" + contact.lastMessage + "\" from " + contact.lastMessageTime);
//		Log.v("Contact", "MessageCount: " + contact.messageCount);
        
        contactList.setAdapter(new CustomAdapter(contacts , this));
	}
	
//	public void ContactsArrayAdapter()
//	{
//		contactList = (ListView) findViewById(R.id.contactList);
//		
//		contacts = new ArrayList<Contact>();
//		Contact contact;
//		
//		for(int i = 0; i < 10; i++)
//		{
//			contact = new Contact();
//			contact.setContactName("Contato" + String.format("%02d", i));
//			contact.setContactPhoto(R.drawable.ic_search);
//			contact.setLastMessage("Esta � uma mensagem de teste.");
//			contact.setLastMessageTime("12:" + String.format("%02d", i));
//			contact.setMessageCount(new Random().nextInt(1000));
//			contacts.add(contact);
//			Log.v("Contact", "Name: " + contact.contactName);
//			Log.v("Contact", "Photo: " + contact.contactPhoto);
//			Log.v("Contact", "Last Message: \"" + contact.lastMessage + "\" from " + contact.lastMessageTime);
//			Log.v("Contact", "MessageCount: " + contact.messageCount);
//		}
//		
//		contactList.setAdapter(new ContactAdapter(contacts, this));
//		Log.v("ContactAdapter", "ContactAdapter Set!");
//	}
}
