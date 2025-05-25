package PawzdCO.enemy;

import java.util.List;

import PawzdCO.common.data.GameData;
import PawzdCO.common.data.Vector2;
import PawzdCO.common.data.World;
import PawzdCO.common.services.IEntityProcessingService;

public class EnemyControlSystem implements IEntityProcessingService {

    @Override
    public void process(World world, GameData gameData) {
        List<Enemy> enemies = world.getEntities(Enemy.class);

        if (enemies.stream().count() < 5) {
            spawnEnemy(world, gameData);
        }

        for (Enemy enemy : enemies) {
            if (!enemy.isAlive()) {
                world.removeEntity(enemy);
                continue;
            }
            if (enemy.getLocation().getX() < 0 || enemy.getLocation().getX() > gameData.width
                    || enemy.getLocation().getY() < 0 || enemy.getLocation().getY() > gameData.height) {
                enemy.setAlive(false);
                continue;
            }

            enemy.accelerate();

            if (Math.random() < 0.5) {
                enemy.rotateLeft();
            } else {
                enemy.rotateRight();
            }


            enemy.getLocation().add(enemy.getVelocity());

            enemy.getVelocity().lerp(new Vector2(0,0), 0.02f);
        }

    }
    private void spawnEnemy(World world, GameData gameData) {
        Enemy enemy = new Enemy();

        Vector2 location = new Vector2(1, 1);

        if (Math.random() < 0.5) {
            location.setX(Math.random() * gameData.width);
            if (Math.random() < 0.5) {
                location.setY(0);
            } else {
                location.setY(gameData.height);
            }
        } else {
            location.setY(Math.random() * gameData.height);
            if (Math.random() < 0.5) {
                location.setX(0);
            } else {
                location.setX(gameData.width);
            }
        }

        // Point towards middle of the screen with variation
        Vector2 direction = new Vector2(gameData.width / 2, gameData.height / 2).subtract(location);
        direction = direction.normalize();
        direction.rotate(Math.random() * 40 - 20); // Random rotation between -20 and 20 degrees
        enemy.setVelocity(direction.multiply(Math.random() + 1)); // Random speed between 1 and 2

        enemy.setLocation(location);
        enemy.setRotation(new Vector2(direction).normalize().rotate(90));
        enemy.setHealth(2);
        enemy.setAlive(true);

        world.addEntity(enemy);
    }
}
