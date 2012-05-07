package com.winterlove.event;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.winterlove.event.events.ListenerRegistrationEvent;

public class EventManager {

	private final List<EventListener> listeners = new ArrayList<EventListener>();

	public void registerListener(EventListener listener) {
		if (!listeners.contains(listener)) {
			listeners.add(listener);
			dispatchEvent(new ListenerRegistrationEvent(listener));
		}
	}

	public void dispatchEvent(Event event) {
		// FIXME: concoct a system that registers events to a registry of methods to listeners
		// this is currently very slow
		try {
			for (EventListener listener : listeners) {
				for (Method method : listener.getClass().getMethods()) {
					if (method.isAnnotationPresent(HandlesEvent.class) && method.getParameterTypes().length == 1) {
						if (event.getClass().getName().equalsIgnoreCase(method.getParameterTypes()[0].getName())) {
							method.invoke(listener, event);
						}
					}
				}
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

}