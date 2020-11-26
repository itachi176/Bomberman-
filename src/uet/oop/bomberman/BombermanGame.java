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
import uet.oop.bomberman.bomb.Bomb;
import uet.oop.bomberman.enemy.Oneal;
import uet.oop.bomberman.enemy.Ballom;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.entities.CreateMap;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

public class BombermanGame extends Application {

    protected int level;
    public static  int WIDTH = 20;
    public static  int HEIGHT = 15;
    
    private GraphicsContext gc;
    private Canvas canvas;


//    public static Bomber bomberman = new Bomber(1, 1, Sprite.player_right.getFxImage());

    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) {
        CreateMap.createMapByLevel(1);
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


        /**
         * di chuyen.
         */
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode().toString().equals("LEFT")) {
                    EntityArr.bomberman.goLeft();
                }
                if (keyEvent.getCode().toString().equals("DOWN")) {
                    EntityArr.bomberman.goDown();
                }
                if (keyEvent.getCode().toString().equals("UP")) {
                    EntityArr.bomberman.goUp();
                }
                if (keyEvent.getCode().toString().equals("RIGHT")) {
                    EntityArr.bomberman.goRight();
                }
                if (keyEvent.getCode().toString().equals("SPACE")) {
                    Bomb bomb = new Bomb(EntityArr.bomberman.getX() / Sprite.SCALED_SIZE,
                            EntityArr.bomberman.getY() / Sprite.SCALED_SIZE, Sprite.bomb.getFxImage());
                    boolean duplicate = false;
                    for (Bomb b : EntityArr.bomberman.bombs) {
                        if (b.getX() == bomb.getX() && b.getY() == bomb.getY()) {
                            duplicate = true;
                            break;
                        }
                    }
                    TimerTask task = new TimerTask() {
                        @Override
                        public void run() {
                            bomb.setImg(Sprite.bomb_exploded.getFxImage());
                            bomb.setExploded(true);
                            bomb.addFlame();
                        }
                    };
                    TimerTask task1 = new TimerTask() {
                        @Override
                        public void run() {
                            Iterator<Brick> brickIterator = EntityArr.bricks.listIterator();
                            while (brickIterator.hasNext()) {
                                Brick brick = brickIterator.next();
                                if (brick.isBroken()) {
                                    brickIterator.remove();
                                }
                            }
                            EntityArr.bomberman.removeBomb(bomb);
                            EntityArr.removeEnemy();
                        }
                    };
                    if (!duplicate && EntityArr.bomberman.getNumBombs() >= EntityArr.bomberman.bombs.size() + 1) {
                        EntityArr.bomberman.bombs.add(bomb);
                        Timer timerEx = new Timer();
                        timerEx.schedule(task, 2000);
                        Timer timerRev = new Timer();
                        timerRev.schedule(task1, 3000L);
                    }

                }
            }
        });
    }


    public void update() {
        EntityArr.bombers.forEach(Entity::update);
        EntityArr.balloms.forEach(Ballom::update);
        EntityArr.oneals.forEach(Oneal::update);
        EntityArr.bomberman.bombs.forEach(Bomb::update);
        EntityArr.bricks.forEach(Brick::update);
        // update flame
        EntityArr.bomberman.bombs.forEach(g -> g.getfUp().forEach(g1 -> g1.update()));
        EntityArr.bomberman.bombs.forEach(g -> g.getfDown().forEach(g1 -> g1.update()));
        EntityArr.bomberman.bombs.forEach(g -> g.getfLeft().forEach(g1 -> g1.update()));
        EntityArr.bomberman.bombs.forEach(g -> g.getfRight().forEach(g1 -> g1.update()));
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        EntityArr.grasses.forEach(g -> g.render(gc));
        EntityArr.walls.forEach(g -> g.render(gc));
        EntityArr.bricks.forEach(g -> g.render(gc));
        EntityArr.portals.forEach(g -> g.render(gc));
        EntityArr.balloms.forEach(g -> g.render(gc));
        EntityArr.oneals.forEach(g -> g.render(gc));
        EntityArr.bomberman.bombs.forEach(g -> g.render(gc));
        EntityArr.bombers.forEach(g -> g.render(gc));
        EntityArr.bomberman.bombs.forEach(g -> g.flames.forEach(g1 -> g1.render(gc)));
    }





}
