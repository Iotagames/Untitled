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
	
	public void load(String file) {
		try {
			Image image = new Image(file);
			images.put(file,image.getTexture());
		} catch (SlickException e) {
		} catch (RuntimeException e) {
			if (file.contains(".png")) {
				try {
					String newFile = file.substring(0, file.lastIndexOf(".")) + ".jpg";
					Image image = new Image(newFile);
					images.put(file,image.getTexture());
				} catch (SlickException e1) {
				}
			}
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

