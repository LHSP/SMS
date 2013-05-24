package com.LHSP.FragmentSMS;

import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

import android.app.Activity;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Loader;
import android.content.res.AssetFileDescriptor;
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
            ContactsContract.Contacts.HAS_PHONE_NUMBER + " == '1')" +
//            " AND (" +ContactsContract.Contacts._ID + " IN (0, 1289))" +
            		")";
    
    private static String sortOrder = ContactsContract.Contacts.DISPLAY_NAME + " COLLATE LOCALIZED ASC";
    
    public Contacts(Activity activity)
    {
    	this.activity = activity;
    }
    
    public void ContactsLoader()
    {
    	activity.getLoaderManager().initLoader(LIST_ID, null, this);
    }
    
	public static SMSList GetMessages(Context context)
    {
    	Log.v("Contacts.GetMessages", "Entrou");
    	SMSList lstSms = new SMSList();
    	
    	SMS objSms = new SMS();
        Uri message = Uri.parse("content://sms/");
        ContentResolver cr = context.getContentResolver();
        
        Cursor c = cr.query(message, null, null, null, null);
        Log.v("Contacts.GetMessages", "Abriu cursor");
        int totalSMS = c.getCount();
        Log.v("Contacts.GetMessages", "Contou");

        if (c.moveToFirst()) {
        	for (int j = 0; j < c.getColumnCount(); j++)
        	{
        		Log.v("Cursor Contents", c.getColumnName(j) + " : " + c.getString(j));
        	}
            for (int i = 0; i < totalSMS; i++) {

                objSms = new SMS();
                objSms.setId(c.getString(c.getColumnIndexOrThrow("_id")));
                objSms.setAddress(c.getString(c.getColumnIndexOrThrow("address")));
                objSms.setMsg(c.getString(c.getColumnIndexOrThrow("body")));
                objSms.setReadState(c.getString(c.getColumnIndex("read")));
                objSms.setTime(Long.parseLong(c.getString(c.getColumnIndexOrThrow("date"))));
                objSms.setPerson(c.getString(c.getColumnIndex("person")));
                if (c.getString(c.getColumnIndexOrThrow("type")).contains("1")) {
                    objSms.setFolderName("inbox");
                } else {
                    objSms.setFolderName("sent");
                }
                
                lstSms.add(objSms);
                c.moveToNext();
            }
        }
        // else {
        // throw new RuntimeException("You have no SMS");
        // }
        c.close();
        Log.v("wtv", "" + lstSms.size());
        for(int i = 0; i < (lstSms.size() < 10 ? lstSms.size() : 10); i++)
        {
        	Log.v("SMSs", lstSms.get(i).toString());
        }
        return lstSms;
    }
    
    public static ArrayList<Contact> GetMessageList(Context context)
    {
    	ArrayList<Contact> contacts = new ArrayList<Contact>();
	    ContentResolver cr = context.getContentResolver();
    	
    	// Gets the URI of the db
    	Uri uri = ContactsContract.Contacts.CONTENT_URI;
    	// What to grab from the db
    	String[] projection = new String[] {
    	        ContactsContract.Contacts._ID,
    	        ContactsContract.Contacts.DISPLAY_NAME,
    	        ContactsContract.Contacts.PHOTO_THUMBNAIL_URI,
    	        ContactsContract.Contacts.HAS_PHONE_NUMBER
    	        };
    	
    	String sortOrder = ContactsContract.Contacts.DISPLAY_NAME + " COLLATE LOCALIZED ASC";

    	Cursor cursor = cr.query(uri, projection, SELECTION, null, sortOrder);
    	
    	if(cursor != null)
    	{
    		Contact contact;
            AssetFileDescriptor afd = null;
	        FileDescriptor fileDescriptor;
    		int i = 0;
    		Log.v("Cursor", "Cursor has " + cursor.getCount() + " results.");
    		while(cursor.moveToNext())
    		{
    			i++;
    			contact = new Contact();
    			contact.contactName = cursor.getString(1);
//    			Log.v("Cursor", contact.contactName);
//    			Log.v("Cursor", cursor.getString(2) == null ? "Nada" : cursor.getString(2));
    			contact.contactPhoto = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_launcher);
    			if(cursor.getString(2) != null)
    			{
	    			try {
						afd = cr.openAssetFileDescriptor(Uri.parse(cursor.getString(2)), "r");
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
	    			if(afd != null)
	    			{
						fileDescriptor = afd.getFileDescriptor();
		    			contact.contactPhoto = BitmapFactory.decodeFileDescriptor(fileDescriptor, null, null);
	    			}
    			}
    			
    			contact.lastMessage = "blablabla";
    			contact.lastMessageTime = "12:00";
    			contact.messageCount = i;
    			contacts.add(contact);
    		}
    	}
    	
    	cursor.close();
    	
    	return contacts;
    }
	
	public static ArrayAdapter<String> GetPhoneContacts(Context context)
	{
	    ArrayList<String> listItems = new ArrayList<String>();
//	    Log.v("Array adapter", "Created ListItems");
		ArrayAdapter<String> adapter;
//	    Log.v("Array adapter", "Created Array Adapter");
		SELECTION = "((" + 
	            ContactsContract.Contacts.DISPLAY_NAME + " NOTNULL) AND (" +
	            ContactsContract.Contacts.DISPLAY_NAME + " != '' ) AND (" +
	            ContactsContract.Contacts.HAS_PHONE_NUMBER +" == '1'))";
//	    Log.v("Array adapter", "Set SELECTION");
		
	    ContentResolver cr = context.getContentResolver();
//	    Log.v("Array adapter", "Created ContentResolver");
		Cursor contactList = cr.query(ContactsContract.Contacts.CONTENT_URI, PROJECTION, SELECTION, null, sortOrder);

//	    Log.v("Array adapter", "Created Cursor");
    	while(contactList.moveToNext()) {
    		int colId = contactList.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
    		String contactName = contactList.getString(colId);
//        	Log.v("Array adapter", "Found Contact: " + contactName);
        	listItems.add(contactName);
		}
    	adapter = new ArrayAdapter<String>(context,
	            android.R.layout.simple_list_item_1,
	            listItems);	
//	    Log.v("Array adapter", "Created adapter object");	
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
		TextView textView = (TextView) activity.findViewById(R.id.textMessage);//new TextView(activity);
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
