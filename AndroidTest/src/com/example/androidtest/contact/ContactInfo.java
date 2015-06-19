package com.example.androidtest.contact;

public class ContactInfo {
	private String name;
	private String number;
	private long contactId;

	public ContactInfo(){
	}
	
	public ContactInfo(long contactId2, String name2, String number2) {
		this.contactId = contactId2;
		this.name = name2;
		this.number = number2;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public long getContactId() {
		return contactId;
	}
	public void setContactId(long contactId) {
		this.contactId = contactId;
	}
	
}
