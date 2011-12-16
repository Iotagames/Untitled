package iotagames.cybergame.utilities;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

import org.newdawn.slick.Animation;
import org.newdawn.slick.util.ResourceLoader;

public class AnimationMap extends HashMap<String, Animation> {
	private static final long serialVersionUID = 1L;
	private String current = "";

	public AnimationMap() {}
	
	public AnimationMap(String start) {
		this.current = start;
	}

	public static AnimationMap fromFile(String file, String start) {
		return fromStream(ResourceLoader.getResourceAsStream(file), start);
	}
	
	public static AnimationMap fromString(String str, String start) {
		return fromStream(new ByteArrayInputStream(str.getBytes()), start);
	}
	
	public static AnimationMap fromStream(InputStream stream, String start) {
		AnimationMap map = new AnimationMap();
		Properties props = new Properties();
		try {
			props.load(stream);
			for (Object k : props.keySet()) {
				String key = (String)k;
				map.put(key, props.getProperty(key));
			}
			if (map.containsKey(start))
				map.current = start;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return map;
	}
	
	public static Animation createAnimation(String prefix, String locations) {
		String[] files = locations.split(",");
		Animation anim = new Animation(false);
		ImageManager images = ImageManager.getInstance();
		for (String value : files) {
			String[] other = value.split(":");
			String file = other[0].trim();
			if (prefix.length() > 0) file = prefix + "_" + file;
			int duration = 150;
			if (other.length > 1) duration = Integer.parseInt(other[1].trim());
			anim.addFrame(images.get(file), duration);
		}
		return anim;
	}
	
	public static Animation createAnimation(String locations) {
		return createAnimation("", locations);
	}
	
	public void put(String key, String locations) {
		put(key, AnimationMap.createAnimation(locations));
	}
	
	public void put(String key, String prefix, String locations) {
		put(key, AnimationMap.createAnimation(prefix, locations));
	}
	
	public Animation get() {
		return get(current);
	}
	
	public void set(String key) {
		if (containsKey(key))
			current = key;
	}
	
	public void update(int delta) {
		get().update(delta);
	}
	
	public void draw(float x, float y) {
		Animation anim = get();
		if (anim != null)
			anim.draw(x, y);
	}
	
	public void draw(float x, float y, float w, float h) {
		Animation anim = get();
		if (anim != null)
			anim.draw(x, y, w, h);
	}
}
