package com.LHSP.FragmentSMS;

import java.io.InputStream;
import java.util.ArrayList;

import android.app.Activity;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class Contacts implements LoaderCallbacks<Cursor>{	
	
	private static final int LIST_ID = 0;
	private Activity activity;
	private static Uri uri = ContactsContract.Contacts.CONTENT_URI;
	
	// These are the Contacts rows that we will retrieve
    private static String[] PROJECTION = new String[] {ContactsContract.Contacts._ID,
            ContactsContract.Contacts.DISPLAY_NAME, ContactsContract.Contacts.HAS_PHONE_NUMBER};

    // This is the select criteria
    private static String SELECTION = "((" + 
            ContactsContract.Contacts.DISPLAY_NAME + " NOTNULL) AND (" +
            ContactsContract.Contacts.DISPLAY_NAME + " != '' ) AND (" +
            ContactsContract.Contacts.HAS_PHONE_NUMBER +" == '1'))";
    
    private static String sortOrder = ContactsContract.Contacts.DISPLAY_NAME + " COLLATE LOCALIZED ASC";
    
    public Contacts(Activity activity)
    {
    	this.activity = activity;
    }
    
    public void ContactsLoader()
    {
    	activity.getLoaderManager().initLoader(LIST_ID, null, this);
    }
	
	public static ArrayAdapter<String> GetPhoneContacts(Context context)
	{
	    ArrayList<String> listItems = new ArrayList<String>();
	    Log.v("Array adapter", "Created ListItems");
		ArrayAdapter<String> adapter;
	    Log.v("Array adapter", "Created Array Adapter");
		SELECTION = "((" + 
	            ContactsContract.Contacts.DISPLAY_NAME + " NOTNULL) AND (" +
	            ContactsContract.Contacts.DISPLAY_NAME + " != '' ) AND (" +
	            ContactsContract.Contacts.HAS_PHONE_NUMBER +" == '1'))";
	    Log.v("Array adapter", "Set SELECTION");
		
	    ContentResolver cr = context.getContentResolver();
	    Log.v("Array adapter", "Created ContentResolver");
		Cursor contactList = cr.query(ContactsContract.Contacts.CONTENT_URI, PROJECTION, SELECTION, null, sortOrder);

	    Log.v("Array adapter", "Created Cursor");
    	while(contactList.moveToNext()) {
    		int colId = contactList.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
    		String contactName = contactList.getString(colId);
        	Log.v("Array adapter", "Found Contact: " + contactName);
        	listItems.add(contactName);
		}
    	adapter = new ArrayAdapter<String>(context,
	            android.R.layout.simple_list_item_1,
	            listItems);	
	    Log.v("Array adapter", "Created adapter object");	
    	return adapter;
	}

	@Override
	public Loader<Cursor> onCreateLoader(int id, Bundle args) {
		CursorLoader loader = new CursorLoader(
				activity,
				uri,
				null,
				null,
				null,
				null);
		return loader;
	}

	@Override
	public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
		Bitmap contactPhoto;
		TextView textView = (TextView) activity.findViewById(R.id.TextView);//new TextView(activity);
		if (cursor != null && cursor.getCount() > 0) {
			cursor.moveToFirst();
			int idIndex = 
			      cursor.getColumnIndex(ContactsContract.Contacts._ID);
			int nameIndex = 
			      cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
			int id = cursor.getInt(idIndex);
			String name = cursor.getString(nameIndex);
			Uri uri = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, id);
			InputStream input = ContactsContract.Contacts.openContactPhotoInputStream(activity.getContentResolver(), uri);
			textView.setText(name);
			if (input != null) 
			{
				contactPhoto = BitmapFactory.decodeStream(input);
				textView.setCompoundDrawablesWithIntrinsicBounds(new BitmapDrawable(activity.getResources(), contactPhoto), null, null, null);
				textView.draw(new Canvas(contactPhoto));
			}
	   }		
	}

	@Override
	public void onLoaderReset(Loader<Cursor> arg0) {
		// TODO Auto-generated method stub
		
	}
}