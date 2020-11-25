package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import uet.oop.bomberman.enemy.Oneal;
import uet.oop.bomberman.enemy.Ballom;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.graphics.Sprite;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BombermanGame extends Application {

    protected int level;
    public static  int WIDTH = 20;
    public static  int HEIGHT = 15;
    
    private GraphicsContext gc;
    private Canvas canvas;

    private static List<Entity> entities = new ArrayList<>();
    private static List<Entity> grasses = new ArrayList<>();
    private static List<Entity> walls = new ArrayList<>();
    private static List<Entity> bricks = new ArrayList<>();
    private static List<Entity> portals = new ArrayList<>();
    private static List<Ballom> balloms = new ArrayList<>();
    private static List<Oneal> oneals = new ArrayList<>();

    public static Bomber bomberman = new Bomber(1, 1, Sprite.player_right.getFxImage());

    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) {
        createMapByLevel(1);
        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();

        // Tao root container
        Group root = new Group();
        root.getChildren().add(canvas);

        // Tao scene
        Scene scene = new Scene(root);

        // Them scene vao stage
        stage.setScene(scene);
        stage.show();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                render();
                update();
            }
        };

        timer.start();

        //createMap();

        entities.add(bomberman);

        /**
         * di chuyen.
         */
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode().toString().equals("LEFT")) {
                    bomberman.goLeft();
                }
                if (keyEvent.getCode().toString().equals("DOWN")) {
                    bomberman.goDown();
                }
                if (keyEvent.getCode().toString().equals("UP")) {
                    bomberman.goUp();
                }
                if (keyEvent.getCode().toString().equals("RIGHT")) {
                    bomberman.goRight();
                }
            }
        });
    }


    public void update() {
        entities.forEach(Entity::update);
        balloms.forEach(Ballom::update);
        oneals.forEach(Oneal::update);
        //bomberman.bombs.forEach(Bomb::update);
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        grasses.forEach(g -> g.render(gc));
        walls.forEach(g -> g.render(gc));
        bricks.forEach(g -> g.render(gc));
        portals.forEach(g -> g.render(gc));
        balloms.forEach(g -> g.render(gc));
        oneals.forEach(g -> g.render(gc));
        //bomberman.bombs.forEach(g -> g.render(gc));
        entities.forEach(g -> g.render(gc));
       // Bomb.flames.forEach(g -> g.render(gc));
    }

    public static List<Entity> getBricks() {
        return bricks;
    }

    public List<Entity> getEntities() {
        return entities;
    }

    public static List<Entity> getGrasses() {
        return grasses;
    }

    public static List<Entity> getWalls() {
        return walls;
    }

    public static List<Entity> getPortals() {
        return portals;
    }

    public void createMapByLevel(int level) {

        try {
            String path = "res/levels/Level" + level + ".txt";
            File file = new File(path);
            Scanner sc = new Scanner(file);
            level = sc.nextInt();
            HEIGHT = sc.nextInt();
            WIDTH = sc.nextInt();

            char [][] maps = new char[HEIGHT][WIDTH];

            sc.nextLine();

            for (int i = 0; i < HEIGHT; ++i) {
                String line = sc.nextLine();
                for (int j = 0; j < WIDTH; ++j) {
                    maps[i][j] = line.charAt(j);
                }
            }

            for (int i = 0; i < WIDTH; i++) {
                for (int j = 0 ; j < HEIGHT; j++) {
                    Entity object;
                    Ballom balloom;
                    Oneal oneal;
                    // create wall and grass
                    if (j == 0 || j == HEIGHT - 1 || i == 0 || i == WIDTH - 1 || maps[j][i] == '#') {
                        object = new Wall(i, j, Sprite.wall.getFxImage());
                        walls.add(object);
                    } else {
                        object = new Grass(i, j, Sprite.grass.getFxImage());

                        grasses.add(object);
                    }
                    // create portal
                    if (maps[j][i] == 'x') {
                        object = new Portal(i, j, Sprite.portal.getFxImage());
                        grasses.add(object);
                    }
                     //create brick
                    if (maps[j][i] == 'x' || maps[j][i] == '*') {
                        object = new Brick(i, j, Sprite.brick.getFxImage());
                        bricks.add(object);
                    } else if (maps[j][i] == '1') {
                        balloom = new Ballom(i, j, Sprite.balloom_left1.getFxImage());
                        balloms.add(balloom);
                    } else if (maps[j][i] == '2') {
                        oneal = new Oneal(i, j, Sprite.oneal_right1.getFxImage());
                        oneals.add(oneal);
                    }
                }
            }
            System.out.println(walls.size());
            System.out.println(grasses.size());
            System.out.println(bricks.size());
            sc.close();
        } catch (Exception exception) {
            System.out.println("Error: " + exception);
        }
    }


}
