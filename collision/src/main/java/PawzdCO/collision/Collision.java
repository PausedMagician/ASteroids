package PawzdCO.collision;

import java.util.Map;

import PawzdCO.common.data.Entity;
import PawzdCO.common.data.GameData;
import PawzdCO.common.data.Vector2;
import PawzdCO.common.data.World;
import PawzdCO.common.services.IEntityPostProcessingService;

public class Collision implements IEntityPostProcessingService {

    @Override
    public void process(World world, GameData gameData) {
        Map<Entity, Entity> collisions = new java.util.HashMap<>();
        for (Entity e : world.getEntities()) {
            for (Entity n : world.getEntities()) {
                if (collisions.containsKey(e) && collisions.get(e).equals(n) || collisions.containsKey(n) && collisions.get(n).equals(e)) {
                    continue;
                }
                if (e.getId().equals(n.getId())) {
                    continue;
                }
                if (e.getHealth() <= 0 && n.getHealth() <= 0)
                    continue;
                if (collides(e, n)) {
                    // System.out.println("Collision!");
                    e.setHealth(e.getHealth() - 1);
                    n.setHealth(n.getHealth() - 1);
                    if (e.getHealth() > 0 && n.getHealth() > 0) {
                        // Push entities away
                        Vector2 delta = e.getLocation().difference(n.getLocation());
                        
                        double distance = delta.length();
                        double overlap = e.getRadius() + n.getRadius() - distance;
                        delta.normalize();
                        delta.multiply(overlap / 2);

                        // Adjust positions to resolve overlap
                        e.getLocation().add(delta);
                        n.getLocation().subtract(delta);

                        // Adjust velocities to simulate collision response
                        Vector2 relativeVelocity = e.getVelocity().difference(n.getVelocity());
                        double velocityAlongNormal = relativeVelocity.dot(delta);

                        if (velocityAlongNormal > 0) {
                            continue; // Entities are moving apart, no need to resolve
                        }

                        double restitution = 0.5; // Coefficient of restitution (elasticity)
                        double impulseMagnitude = -(1 + restitution) * velocityAlongNormal;
                        impulseMagnitude /= (1 / e.getMass() + 1 / n.getMass());

                        Vector2 impulse = delta.multiply(impulseMagnitude);
                        e.getVelocity().add(impulse.divide(e.getMass()));
                        n.getVelocity().subtract(impulse.divide(n.getMass()));

                        collisions.put(e, n);
                    }
                }
            }
        }
    }

    boolean collides(Entity e, Entity n) {
        double distance = e.getLocation().difference(n.getLocation()).length();
        return distance < e.getRadius() + n.getRadius();
        // return e.getPolygon().getBoundsInParent().intersects(n.getPolygon().getBoundsInParent());
    }
    
}