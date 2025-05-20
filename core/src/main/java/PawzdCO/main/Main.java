package PawzdCO.main;

import PawzdCO.common.data.Entity;
import PawzdCO.common.data.GameData;
import PawzdCO.common.data.World;
import PawzdCO.common.data.GameData.Keys;
import PawzdCO.common.services.IEntityPostProcessingService;
import PawzdCO.common.services.IEntityProcessingService;
import PawzdCO.common.services.IGamePlugin;
import PawzdCO.common.services.IWorldAware;

import java.util.Collection;
import java.util.List;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;

import java.lang.module.ModuleFinder;
import java.nio.file.Paths;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.input.KeyEvent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {

    GameData gameData = new GameData();
    World w = new World();

    
    public static void main(String[] args) {
        launch(args);
    }
    
    
    Pane gameWindow = new Pane();
    Canvas canvas = new Canvas(gameData.width, gameData.height);
    Text debugText = new Text(10,10, "");

    @Override
    public void start(Stage primaryStage) {

        gameWindow.setPrefSize(gameData.width, gameData.height);
        gameWindow.getChildren().add(debugText);
        gameWindow.getChildren().add(canvas);
        gameWindow.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));

        Scene scene = new Scene(gameWindow);

        primaryStage.widthProperty().addListener((obs, oldVal, newVal) -> {
            gameData.width = (int)((double) newVal);
            canvas.setWidth(gameData.width);
            System.out.println(newVal);
        });
        primaryStage.heightProperty().addListener((obs, oldVal, newVal) -> {
            gameData.height = (int)((double) newVal);
            canvas.setHeight(gameData.height);
            System.out.println(newVal);
        });
        
        scene.setOnKeyPressed(event -> setupKeys(event, true));
        scene.setOnKeyReleased(event -> setupKeys(event, false));

        primaryStage.setScene(scene);
        primaryStage.setTitle("ASteroids");
        primaryStage.setResizable(true);
        primaryStage.setOnCloseRequest(event -> {
            for(IGamePlugin plugin : getPlugins()) {
                System.out.println("Goodbye " + plugin.getClass().getName());
                plugin.stop(gameData, w);
            }
            System.out.println("Goodbye all!");
            System.exit(0);
        });
        primaryStage.show();

        for(IWorldAware module : ServiceLoader.load(IWorldAware.class)) {
            module.provideWorld(w);
        }

        for(IGamePlugin plugin : getPlugins()) {
            plugin.start(gameData, w);
        }

        startTicking();

    }

    void setupKeys(KeyEvent event, boolean pressed) {
        switch(event.getCode()) {
            case UP:
            case W:
                this.gameData.setDown(Keys.UP, pressed);
                break;
            case RIGHT:
            case D:
                this.gameData.setDown(Keys.RIGHT, pressed);
                break;
            case LEFT:
            case A:
                this.gameData.setDown(Keys.LEFT, pressed);
                break;
            case SPACE:
                this.gameData.setDown(Keys.SPACE, pressed);
                break;
            default:
                break;
        }
    }

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
                    update();
                    gameData.updateKeys();
                    draw();
                    accumulator -= fixedDelta;
                }
            }
        }.start();
    }


    void update() {
        for (IEntityProcessingService service : getEntityProcessingServices()) {
            service.process(w, gameData);
        }
        for (IEntityPostProcessingService service : getEntityPostProcessingServices()) {
            service.process(w, gameData);
        }
    }


    private void draw() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, gameData.width, gameData.height);

        for (Entity e : this.w.getEntities()) {
            if (!e.isAlive()) {
                Polygon removedPolygon = e.getPolygon();
                this.w.removeEntity(e);
                gameWindow.getChildren().remove(removedPolygon);
                continue;
            }

            if (!gameWindow.getChildren().contains(e.getPolygon())) {
                gameWindow.getChildren().add(e.getPolygon());
            }

            e.Render(gc);
        }
    }


    // Services

    Collection<? extends IEntityProcessingService> processingServices;
    Collection<? extends IEntityPostProcessingService> postProcessingServices;

    ModuleLayer layer = getModuleLayer(2);


    private ModuleLayer getModuleLayer(int _layer) {
        ModuleFinder finder = ModuleFinder.of(Paths.get(String.format("mods-mvn-%,d", _layer)));
        ModuleLayer parent = ModuleLayer.boot();
        List<String> modules = finder.findAll().stream().map(m -> m.descriptor().name()).collect(toList());
        java.lang.module.Configuration config = parent.configuration().resolve(finder, ModuleFinder.of(), modules);
        ModuleLayer layer = parent.defineModulesWithOneLoader(config, ClassLoader.getPlatformClassLoader());
        return layer;
    }

    private Collection<? extends IEntityProcessingService> getEntityProcessingServices() {
        if (processingServices == null) {
            processingServices = ServiceLoader.load(layer, IEntityProcessingService.class).stream().map(ServiceLoader.Provider::get).collect(toList());
        }
        return processingServices;
    }

    private Collection<? extends IEntityPostProcessingService> getEntityPostProcessingServices() {
        if (postProcessingServices == null) {
            postProcessingServices = ServiceLoader.load(layer, IEntityPostProcessingService.class).stream().map(ServiceLoader.Provider::get).collect(toList());
        }
        return postProcessingServices;
    }
    
    private Collection<? extends IGamePlugin> getPlugins() {
        return ServiceLoader.load(layer, IGamePlugin.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }

}