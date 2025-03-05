package PawzdCO.common.data;

import java.util.ArrayList;

public class World {
    ArrayList<Entity> entities = new ArrayList<>();

    public ArrayList<Entity> getEntities() {
        return entities;
    }
    public <E extends Entity> ArrayList<E> getEntities(Class<E> type) {
        ArrayList<E> arrayList = new ArrayList<>();
        for (Entity e : this.entities) {
            if (type.isInstance(e)) {
                arrayList.add(type.cast(e));
            }
        }
        return arrayList;
    }
    public void setEntities(ArrayList<Entity> entities) {
        this.entities = entities;
    }
    public void addEntity(Entity e) {
        this.entities.add(e);
    }
    public void removeEntity(Entity e) {

    }
}
