package com.LHSP.FragmentSMS;

import java.util.ArrayList;
import java.util.List;

public class SMSList {
	List<Tuple<String, ArrayList<SMS>>> smsByPerson = new ArrayList<Tuple<String, ArrayList<SMS>>>();

	public boolean add(SMS sms)
	{
		String phone = sms.getAddress();
		Tuple<String, ArrayList<SMS>> t = null;
		for (Tuple<String, ArrayList<SMS>> tuple : smsByPerson) {
			if(phone.equals(tuple.x))
			{
				t = tuple;
				break;
			}
		}
		if(t != null)
			t.y.add(sms);
		else
		{
			ArrayList<SMS> newSMSList = new ArrayList<SMS>();
			newSMSList.add(sms);
			smsByPerson.add(new Tuple<String, ArrayList<SMS>>(phone, newSMSList));
		}
		return true;
	}

	public int size()
	{
		return smsByPerson.size();
	}

	public ArrayList<SMS> get(int id)
	{
		return smsByPerson.get(id).y;
	}
	
	public ArrayList<SMS> get(String phone)
	{
		ArrayList<SMS> smsList = null;
		for (Tuple<String, ArrayList<SMS>> tuple : smsByPerson) {
			if(phone.equals(tuple.x))
			{
				smsList = new ArrayList<SMS>(tuple.y);
				return smsList;
			}
		}
		return smsList;
	}
}
