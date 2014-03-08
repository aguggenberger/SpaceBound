package com.aguggenberger.spacebound.engine.services;

import aurelienribon.tweenengine.TweenAccessor;

public class MusicTweenAccessor implements TweenAccessor<MusicWrapper> {
	 
    public static final int VOLUME = 0;
    
    @Override
    public int getValues(MusicWrapper target, int tweenType, float[] returnValues) {
        switch(tweenType) {
        case VOLUME:
            returnValues[0] = target.getVolume();
            return 1;
        }
        return 0;
    }
 
    @Override
    public void setValues(MusicWrapper target, int tweenType, float[] newValues) {
        switch(tweenType) {
        case VOLUME:
            target.setVolume(newValues[0]);
            break;
        }
    }
 
}
