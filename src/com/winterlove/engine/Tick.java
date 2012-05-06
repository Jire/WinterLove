package com.winterlove.engine;

public abstract class Tick implements Task {

	private final int ticks;
	private int countdown = 0;

	private boolean running = true;

	public boolean isRunning() {
		return running;
	}

	public void stop() {
		running = false;
	}

	public Tick(int ticks) {
		this.ticks = ticks;
		this.countdown = ticks;
	}

	public Tick() {
		this(1);
	}

	@Override
	public boolean run() {
		if (countdown-- == 0) {
			execute();
			countdown = ticks; // reset ticks
			return isRunning();
		}
		return true;
	}

	public abstract void execute();

}