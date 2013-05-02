package com.LHSP.FragmentSMS;

import java.util.ArrayList;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.QuickContactBadge;
import android.widget.TextView;

public class CustomAdapter extends BaseAdapter {

	private ArrayList<Contact> _data;
	Context _c;

	CustomAdapter (ArrayList<Contact> data, Context c){
		_data = data;
		_c = c;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return _data.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return _data.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		if (v == null)
		{
			LayoutInflater vi = (LayoutInflater)_c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = vi.inflate(R.layout.list_item_contact, null);
		}
		Log.v("GetView", "Inflated");

		QuickContactBadge contactPhoto = (QuickContactBadge) v.findViewById(R.id.contactPhoto);
		TextView contactName = (TextView) v.findViewById(R.id.contactName);
		TextView messageCount = (TextView) v.findViewById(R.id.messageCount);
		TextView lastMessage = (TextView) v.findViewById(R.id.lastMessage);
		TextView lastMessageTime = (TextView) v.findViewById(R.id.lastMessageTime);
		Log.v("GetView", "Got Views");

		Contact contact = _data.get(position);
		Log.v("GetView", "Got position: " + position);
		Log.v("GetView", "ContactPhoto: " + contact.contactPhoto);
		contactPhoto.setImageResource(contact.contactPhoto);
		Log.v("GetView", "Loaded Photo");
		contactName.setText(contact.contactName);
		Log.v("GetView", "Loaded Name");
		messageCount.setText(Integer.toString(contact.messageCount));
		Log.v("GetView", "Loaded Message Count");
		lastMessage.setText(contact.lastMessage);
		Log.v("GetView", "Loaded Last Message");
		lastMessageTime.setText(contact.lastMessageTime);
		Log.v("GetView", "Loaded Last Message Time");
		Log.v("GetView", "Filled Views");

		return v;
	}
}
