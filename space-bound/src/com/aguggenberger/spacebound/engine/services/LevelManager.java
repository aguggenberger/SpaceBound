package com.aguggenberger.spacebound.engine.services;

import com.aguggenberger.spacebound.engine.Level;

public class LevelManager {
	private final Level[] levels;
	
	public LevelManager() {
        levels = new Level[] {
    		new Level(0, "SpaceBound", new SpaceBoundLevelLoader())
        };
	}
	
	public Level[] getLevels() {
		return levels;
	}
	
	public Level get(int id) {
		return levels[id];
	}
}
