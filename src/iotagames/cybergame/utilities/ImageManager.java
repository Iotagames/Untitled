package iotagames.cybergame.utilities;

import java.util.*;
import org.newdawn.slick.Image;
import org.newdawn.slick.opengl.*;
import org.newdawn.slick.SlickException;

public class ImageManager {
	private static final ImageManager instance = new ImageManager();
	
	private ImageManager() {
		images = new HashMap<String,Texture>();
	}
	
	public static ImageManager getInstance() {
		return instance;
	} 
	
	private Map<String,Texture> images;
	public static String directory="data/";
	
	public void load(String file) {
		try {
			String origFile = file;
			file = addDefaults(file);
			Image image = new Image(file);
			if (image.getWidth() * image.getHeight() == 0)
				jpgFallback(origFile);
			else
				images.put(origFile,image.getTexture());
		} catch (SlickException e) {
		} catch (RuntimeException e) {
			jpgFallback(file);
		}
	}
	
	public String addDefaults(String file) {
		if (file.charAt(0) != '/')
			file = directory + file;
		if (!file.contains("."))
			file += ".png";
		return file;
	}
	
	public void jpgFallback(String file) {
		try {
			String newFile = directory + file + ".jpg";
			Image image = new Image(newFile);
			images.put(file,image.getTexture());
		} catch (SlickException e1) {
		}
	}
	
	public void erase(String file) {
		images.put(file,null);
	}
	
	public void clear() {
		System.out.println("Clearing images...");
		images.clear();
	}
	
	public Texture getTexture(String file) {
		if (!exists(file)) {
			load(file);
		}
		return images.get(file);
	}
	
	public Image get(String file) {
		Texture tex = getTexture(file);
		if (tex!=null) {
			return new Image(tex);
		} else {
			try {
				return new Image(0,0);
			} catch (SlickException e) {
			}
		}
		return null;
	}
	
	public boolean exists(String file) {
		return (images.containsKey(file));
	}
	
	public Set<String> getKeys() {
		return images.keySet();
	}
}

