package com.chapter12;

import java.util.ArrayList;
import java.util.List;

interface OnClickEvent {
	void onClick();
}

class Button {
	OnClickEvent listener;

	public Button(OnClickEvent listener) {
		this.listener = listener;
	}

	void click() {
		listener.onClick();
		listener.onClick();
	}

}

public class Main {

	public static void main(String[] args) throws InterruptedException {
		Button addButton = new Button(new OnClickEvent() {

			@Override
			public void onClick() {
				System.out.println("Click!!");
			}
		});

		Button button2 = new Button(new OnClickEvent() {

			@Override
			public void onClick() {
				System.out.println("클릭!!!");
			}
		});

		List<Button> buttons = new ArrayList<>();
		buttons.add(addButton);
		buttons.add(button2);

		buttons.stream().forEach(button -> button.click());
	}

}
