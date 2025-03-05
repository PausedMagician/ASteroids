package PawzdCO.common.data;

import java.util.EnumMap;


public class GameData {
    public int width = 800;
    public int height = 600;
    public enum Keys {
        LEFT,
        RIGHT,
        UP,
        SPACE
    }
    
    EnumMap<Keys, Boolean> keys = new EnumMap<>(Keys.class);
    
    public void setPressed(Keys key, boolean pressed) {
        this.keys.put(key, pressed);
    }
    public boolean isPressed(Keys key) {
        return keys.get(key);
    }

    public GameData() {
        this.keys.put(Keys.UP, false);
        this.keys.put(Keys.RIGHT, false);
        this.keys.put(Keys.LEFT, false);
        this.keys.put(Keys.SPACE, false);
    }

}
