package com.aguggenberger.spacebound.engine;

public class EngineSettings {
	public static final String VERSION = "0.0.0.01 Pre-Alpha";
	public static final String LOG = "game";
	public static final int VIRTUAL_WIDTH = 1280;
	public static final int VIRTUAL_HEIGHT = 720;
	public static final float ASPECT_RATIO = (float)VIRTUAL_WIDTH/(float)VIRTUAL_HEIGHT;

	public int width;
	public int height;
	public boolean useGL20;
	public String preferences;
	public String version;
	public String log;
	
	public EngineSettings() {
		this(VERSION, LOG, VIRTUAL_WIDTH, VIRTUAL_HEIGHT);
	}
	public EngineSettings(String version, 
		String log, int width, int height) {
		this.version = version;
		this.log = log;
		this.width = width;
		this.height = height;
	}
}