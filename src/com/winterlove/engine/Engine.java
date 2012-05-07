package com.winterlove.engine;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Engine extends Thread {

	private final int cycleLength;
	private volatile boolean running;

	private final List<Task> tasks = new ArrayList<Task>(10_000);
	private Iterator<Task> it;

	protected Engine(int cycleLength) {
		super("Engine");
		this.cycleLength = cycleLength;
	}

	public int cycleLength() {
		return cycleLength;
	}

	public boolean isRunning() {
		return running;
	}

	protected void setRunning(boolean running) {
		this.running = running;
	}

	@Override
	public void start() {
		if (isRunning()) {
			throw new IllegalStateException("Cannot start engine while already running");
		}

		setRunning(true);

		super.start();
	}

	@Override
	public void run() {
		long lastUpdate = 0;
		long systemTime;
		while (isRunning()) {
			systemTime = System.currentTimeMillis();
			if ((systemTime - lastUpdate) > cycleLength()) {
				lastUpdate = systemTime;
				try {
					if (tasks.size() > 0) {
						it = tasks.iterator();
						while (it.hasNext()) if (!it.next().run()) it.remove();
					}
					long timeTaken = System.currentTimeMillis() - systemTime;
					if (timeTaken > cycleLength()) {
						System.out.println("Engine overloaded by " + ((timeTaken / cycleLength()) * 100) + "%");
					} else {
						sleep(cycleLength() - timeTaken);
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void submit(Task task) {
		tasks.add(task);
	}

	public static Engine getEngine(int cycleLength) {
		return new Engine(cycleLength);
	}

}