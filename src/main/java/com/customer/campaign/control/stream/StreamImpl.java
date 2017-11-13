package com.customer.campaign.control.stream;

public class StreamImpl implements Stream {

	private String value;

	private int index;
	
	public StreamImpl(String value) {
		this.value = value;
		this.index = 0;
	}

	@Override
	public char getNext() {
		char next = value.charAt(index);

		index++;

		return next;
	}

	@Override
	public boolean hasNext() {
		return index < value.length();
	}

	public String getValue() {
		return value;
	}
}
