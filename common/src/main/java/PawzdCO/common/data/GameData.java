package PawzdCO.common.data;

import java.util.EnumMap;
import java.util.ServiceLoader;

import PawzdCO.common.services.IScoreSPI;
import javafx.beans.property.SimpleIntegerProperty;

public class GameData {
    // Window data
    public int width = 800;
    public int height = 600;

    // Key data
    public enum Keys {
        LEFT,
        RIGHT,
        UP,
        SPACE
    }

    EnumMap<Keys, Boolean> keys = new EnumMap<>(Keys.class);
    EnumMap<Keys, Boolean> keysLast = new EnumMap<>(Keys.class);

    public void setDown(Keys key, boolean pressed) {
        this.keys.put(key, pressed);
    }

    public boolean isDown(Keys key) {
        return keys.get(key);
    }

    public boolean isPressed(Keys key) {
        return keys.get(key) && !keysLast.get(key);
    }

    public void updateKeys() {
        keysLast.putAll(keys);
    }


    public GameData() {
        this.keys.put(Keys.UP, false);
        this.keys.put(Keys.RIGHT, false);
        this.keys.put(Keys.LEFT, false);
        this.keys.put(Keys.SPACE, false);
    }

    // Player data
    private SimpleIntegerProperty score = new SimpleIntegerProperty(0);

    public SimpleIntegerProperty getScore() {
        // Call get
        ServiceLoader.load(IScoreSPI.class).findFirst().ifPresent(
            spi -> this.score.set(spi.getScore())
        );
        return this.score;
    }

    public void addScore(int value) {
        // Call post
        ServiceLoader.load(IScoreSPI.class).findFirst().ifPresent(
            spi -> {
                this.score.set(spi.addScore(value));
            }
        );
    }
}
