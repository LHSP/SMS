package com.LHSP.FragmentSMS;

import java.util.Date;

public class SMS{
	private String _id;
	private String _address;
	private String _msg;
	private String _readState; //"0" for have not read sms and "1" for have read sms
	private Date _time;
	private String _folderName;
	private int _person;

	public String getId(){
		return _id;
	}
	public String getAddress(){
		return _address;
	}
	public String getMsg(){
		return _msg;
	}
	public String getReadState(){
		return _readState;
	}
	public Date getTime(){
		return _time;
	}
	public String getFolderName(){
		return _folderName;
	}
	public int getPerson()
	{
		return _person;
	}


	public void setId(String id){
		_id = id;
	}
	public void setAddress(String address){
		_address = address;
	}
	public void setMsg(String msg){
		_msg = msg;
	}
	public void setReadState(String readState){
		_readState = readState;
	}
	public void setTime(long milliseconds){
		_time = new Date(milliseconds);
	}
	public void setFolderName(String folderName){
		_folderName = folderName;
	}
	public void setPerson(String person)
	{
		if(person == null)
			_person = 0;
		else
			_person = Integer.parseInt(person);
	}
	
	public String getMsg(int size){
		if(_msg.length() < size)
			return _msg.substring(0, _msg.length());
		else
			return _msg.substring(0, size);
	}
	
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		sb.append(_person);
		sb.append("(");
		sb.append(_address);
		sb.append(")");
		sb.append("]");
		sb.append(_msg);
		sb.append(" - ");
		sb.append(_time);
		return sb.toString();
	}
}
