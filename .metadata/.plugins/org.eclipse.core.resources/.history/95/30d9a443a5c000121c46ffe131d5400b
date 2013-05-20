package com.LHSP.FragmentSMS;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.QuickContactBadge;
import android.widget.TextView;

public class ContactAdapter extends BaseAdapter {

	private ArrayList<Contact> _data;
    Context _c;
    
    ContactAdapter (ArrayList<Contact> data, Context c){
        _data = data;
        _c = c;
    }
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		if (v == null)
		{
			LayoutInflater vi = (LayoutInflater)_c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = vi.inflate(R.layout.list_item_contact, null);
		}

		QuickContactBadge contactPhoto = (QuickContactBadge) v.findViewById(R.id.contactPhoto);
		TextView contactName = (TextView) v.findViewById(R.id.contactName);
		TextView messageCount = (TextView) v.findViewById(R.id.messageCount);
		TextView lastMessage = (TextView) v.findViewById(R.id.lastMessage);
		TextView lastMessageTime = (TextView) v.findViewById(R.id.lastMessageTime);

		Contact contact = _data.get(position);
		contactPhoto.setImageBitmap(contact.contactPhoto);
		contactName.setText(contact.contactName);
		messageCount.setText(contact.messageCount);
		lastMessage.setText(contact.lastMessage);
		lastMessageTime.setText(contact.lastMessageTime);

		return v;
	}

}
