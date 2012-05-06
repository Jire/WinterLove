package com.winterlove.model.update;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import com.winterlove.model.Entity;

public abstract class UpdateService<T extends Entity> {

	private final BlockingQueue<T> queue;

	public UpdateService(int initialQueueSize) {
		this.queue = new ArrayBlockingQueue<T>(initialQueueSize);
	}

	public boolean hasNext() {
		return !queue.isEmpty();
	}

	public void update(List<T> entities) {
		queue.addAll(entities);
	}

	public abstract void update(T entity);

}