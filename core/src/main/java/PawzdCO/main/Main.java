package PawzdCO.main;

import PawzdCO.common.data.Entity;
import PawzdCO.common.data.GameData;
import PawzdCO.common.data.World;
import PawzdCO.common.data.GameData.Keys;
import PawzdCO.common.services.IEntityPostProcessingService;
import PawzdCO.common.services.IEntityProcessingService;
import PawzdCO.common.services.IGamePlugin;

import java.util.Collection;
import java.util.ServiceLoader;
import static java.util.stream.Collectors.toList;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.input.KeyEvent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {

    GameData gd = new GameData();
    World w = new World();

    
    public static void main(String[] args) {
        launch(args);
    }
    
    
    Pane gw = new Pane();
    Text debugText = new Text(10,10, "");

    @Override
    public void start(Stage primaryStage) {

        gw.setPrefSize(gd.width, gd.height);
        gw.getChildren().add(debugText);


        Scene scene = new Scene(gw);

        primaryStage.widthProperty().addListener((obs, oldVal, newVal) -> {
            gd.width = (int)((double) newVal);
            System.out.println(newVal);
        });
        primaryStage.heightProperty().addListener((obs, oldVal, newVal) -> {
            gd.height = (int)((double) newVal);
            System.out.println(newVal);
        });
        
        scene.setOnKeyPressed(event -> setupKeys(event, true));
        scene.setOnKeyReleased(event -> setupKeys(event, false));

        primaryStage.setScene(scene);
        primaryStage.setTitle("Hello World!");
        primaryStage.show();

        for(IGamePlugin plugin : getPlugins()) {
            plugin.start(gd, w);
        }

        startTicking();

    }

    void setupKeys(KeyEvent event, boolean pressed) {
        switch(event.getCode()) {
            case UP:
            case W:
                this.gd.setPressed(Keys.UP, pressed);
                break;
            case RIGHT:
            case D:
                this.gd.setPressed(Keys.RIGHT, pressed);
                break;
            case LEFT:
            case A:
                this.gd.setPressed(Keys.LEFT, pressed);
                break;
            case SPACE:
                this.gd.setPressed(Keys.SPACE, pressed);
                break;
                default:
                break;
        }
    }

    public String entString = ""; 

    void startTicking() {
        new AnimationTimer() {
            long lastTick = System.nanoTime();
            double accumulator = 0.0;
            final double fixedDelta = 1.0 / 60.0; // 60 updates per second

            @Override
            public void handle(long now) {
                double elapsedTime = (now - lastTick) / 1_000_000_000.0;
                lastTick = now;
                accumulator += elapsedTime;

                while (accumulator >= fixedDelta) {
                    debugText.setText(entString);
                    update();
                    accumulator -= fixedDelta;
                }
                draw();
            }
        }.start();
    }


    void update() {
        for (IEntityProcessingService service : getEntityProcessingServices()) {
            service.process(w, gd);
        }
        for (IEntityPostProcessingService service : getEntityPostProcessingServices()) {
            service.process(w, gd);
        }
    }


    private void draw() {
        entString = "";
        entString += "Score: " + gd.getScore() + "\n";
        entString += "Entities: " + this.w.getEntities().size() + "\n";
        for (Entity e : this.w.getEntities()) {
            entString += e.toString() + "\n";
            if (!e.isAlive()) {
                Polygon removedPolygon = e.getPolygon();
                this.w.removeEntity(e);
                gw.getChildren().remove(removedPolygon);
                continue;
            }
            if (!gw.getChildren().contains(e.getPolygon())) {
                gw.getChildren().add(e.getPolygon());
            }
            e.getLocation().lerp(e.getPrefferedLocation(), 0.1f); // Adjust lerping speed

            e.Render();
        }
    }


    Collection<? extends IEntityProcessingService> processingServices;

    private Collection<? extends IEntityProcessingService> getEntityProcessingServices() {
        if (processingServices == null) {
            processingServices = ServiceLoader.load(IEntityProcessingService.class).stream().map(ServiceLoader.Provider::get).collect(toList());
        }
        return processingServices;
    }
    
    Collection<? extends IEntityPostProcessingService> postProcessingServices;

    private Collection<? extends IEntityPostProcessingService> getEntityPostProcessingServices() {
        if (postProcessingServices == null) {
            postProcessingServices = ServiceLoader.load(IEntityPostProcessingService.class).stream().map(ServiceLoader.Provider::get).collect(toList());
        }
        return postProcessingServices;
    }
    
    private Collection<? extends IGamePlugin> getPlugins() {
        return ServiceLoader.load(IGamePlugin.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }

}