package com.chapter04;

enum KeyType {
	PADLOCK, BUTTON, DIAL, FINGER;
}

public class StrongBox<T> {

	private T item;
	private int count = 0;
	private KeyType keytype;

	public StrongBox(KeyType keytype) {
		this.keytype = keytype;
		this.count = 0;
	}
	
	public void put(T item) {
		this.item = item;
	}
	
	public T get() {
		this.count--;
		if(this.count > 0) {
			return null;
		}else {
			return this.item;
		}
	}
	
	public T get(KeyType keytype) {
		this.keytype = keytype;

		switch (this.keytype) {
		case PADLOCK:
			this.count = 1024;
			break;
		case BUTTON:
			this.count = 10000;
			break;
		case DIAL:
			this.count = 30000;
			break;
		case FINGER:
			this.count = 1000000;
			break;
		}
		return this.item;
	}

}
