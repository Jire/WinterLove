package com.winterlove.event.events;

import com.winterlove.event.Event;
import com.winterlove.event.EventListener;

public class ListenerRegistrationEvent implements Event {

	private final EventListener listener;

	public ListenerRegistrationEvent(EventListener listener) {
		this.listener = listener;
	}

	public EventListener getListener() {
		return listener;
	}

}