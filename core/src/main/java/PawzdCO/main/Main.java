package PawzdCO.main;

import PawzdCO.common.data.Entity;
import PawzdCO.common.data.GameData;
import PawzdCO.common.data.World;
import PawzdCO.common.data.GameData.Keys;
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
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {

    GameData gd = new GameData();
    World w = new World();

    
    public static void main(String[] args) {
        launch(args);
    }
    
    
    Pane gameWindow = new Pane();
    Text debugText = new Text(10,10, "");

    @Override
    public void start(Stage primaryStage) {
        gameWindow.setPrefSize(gd.width, gd.height);
        gameWindow.getChildren().add(debugText);


        Scene scene = new Scene(gameWindow);

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

    void startTicking() {
        new AnimationTimer() {
            long lastTick = 0;
            @Override
            public void handle(long now) {
                update();
                render();
                debugText.setText("Tick time: " + (now - lastTick)/1000);
                lastTick = now;
            }
        }.start();
    }


    void update() {
        for (IEntityProcessingService service : getEntityProcessingServices()) {
            service.process(w, gd);
        }
    }

    void render() {
        for (Entity e : this.w.getEntities()) {
            if (!gameWindow.getChildren().contains(e.getPolygon())) {
                gameWindow.getChildren().add(e.getPolygon());
            }
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
    
    
    private Collection<? extends IGamePlugin> getPlugins() {
        return ServiceLoader.load(IGamePlugin.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }

}