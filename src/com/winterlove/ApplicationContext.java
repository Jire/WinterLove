package com.winterlove;

import com.winterlove.engine.Engine;
import com.winterlove.model.World;
import com.winterlove.network.Server;

public interface ApplicationContext {

	public Engine getEngine();
	public Server getServer();
	public World getWorld();

}