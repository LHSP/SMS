package com.LHSP.FragmentSMS;

import java.util.ArrayList;
import java.util.List;

public class SMSList {
	List<Tuple<Integer, ArrayList<SMS>>> smsByPerson = new ArrayList<Tuple<Integer, ArrayList<SMS>>>();

	public boolean add(SMS sms)
	{
		int phone = Integer.parseInt(sms.getAddress());
		Tuple<Integer, ArrayList<SMS>> t = null;
		for (Tuple<Integer, ArrayList<SMS>> tuple : smsByPerson) {
			if(tuple.x == phone)
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
			smsByPerson.add(new Tuple<Integer, ArrayList<SMS>>(phone, newSMSList));
		}
		return true;
	}

	public int size()
	{
		return smsByPerson.size();
	}

	public ArrayList<SMS> get(MESSAGEGETTYPE getType, int phone)
	{
		switch (getType) {
		case SEQUENCE:
			return smsByPerson.get(phone).y;

		case PHONE:
			for (Tuple<Integer, ArrayList<SMS>> tuple : smsByPerson) {
				if(tuple.x == phone)
					return tuple.y;
			}
			return null;

		default:
			return null;
		}

	}
}
