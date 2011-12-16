package iotagames.cybergame.utilities;

import java.util.HashMap;

import org.newdawn.slick.Font;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.HieroSettings;
import org.newdawn.slick.font.effects.ColorEffect;

public class FontManager {
	private static HashMap<String, Font> fonts = new HashMap<String, Font>();
	public static Font defaultFont;
	public static String directory = "data/";
	
	public static Font get(String key) {
		return fonts.get(key);
	}
	
	public static Font put(String key, Font font) {
		if (font instanceof UnicodeFont) 
			addOptions((UnicodeFont) font);
		fonts.put(key, font);
		if (fonts.size() == 1)
			defaultFont = font;
		return font;
	}
	
	@SuppressWarnings("unchecked")
	private static void addOptions(UnicodeFont font) {
		font.addAsciiGlyphs();
		font.getEffects().add(new ColorEffect(java.awt.Color.WHITE));
		try {font.loadGlyphs();} 
		catch (SlickException e) {e.printStackTrace();}
	}
	
	public static Font put(String key, String ref) {
		return put(key, ref, 20, true, false);
	}
	
	private static String changedRef(String ref) {
		if (ref.charAt(0) != '/')
			ref = directory + ref;
		if (!ref.contains("."))
			ref += ".ttf";
		return ref;
	}
	
	public static Font put(String key, String ref, int size, boolean bold, boolean italic) {
		if (!fonts.containsKey(key)) {
			ref = changedRef(ref);
			try {
				return put(key, new UnicodeFont(ref, 20, false, false));
			} catch (SlickException e) {
				e.printStackTrace();
				return null;
			}
		} else {
			return fonts.get(key);
		}
	}
	
	public static Font put(String key, String ref, HieroSettings settings) {
		if (!fonts.containsKey(key)) {
			ref = changedRef(ref);
			try {
				return put(key, new UnicodeFont(ref, settings));
			} catch (SlickException e) {
				e.printStackTrace();
				return null;
			}
		} else {
			return fonts.get(key);
		}
	}
}
