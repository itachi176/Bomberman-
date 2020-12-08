package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import uet.oop.bomberman.bomb.Bomb;
import uet.oop.bomberman.enemy.Enemy;
import uet.oop.bomberman.enemy.Oneal;
import uet.oop.bomberman.enemy.Ballom;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.entities.CreateMap;


import java.util.*;

public class BombermanGame extends Application {

    public static int level = 1;
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
                    EntityArr.bomberman.putBomb();
                }
                if (keyEvent.getCode() == KeyCode.B) {
                    EntityArr.balloms.clear();
                }
                if (keyEvent.getCode() == KeyCode.O) {
                    EntityArr.oneals.clear();
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
        EntityArr.items.forEach(g -> {
            if (g.isVisible()) g.update();
        });

        EntityArr.bomberman.update();
//        EntityArr.enemies.forEach(Enemy::update);

        EntityArr.portals.forEach(g -> g.update());
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        EntityArr.grasses.forEach(g -> g.render(gc));
        EntityArr.portals.forEach(g -> g.render(gc));
        EntityArr.walls.forEach(g -> g.render(gc));
        EntityArr.bricks.forEach(g -> g.render(gc));
        EntityArr.balloms.forEach(g -> g.render(gc));
        EntityArr.oneals.forEach(g -> g.render(gc));
        EntityArr.bomberman.bombs.forEach(g -> g.render(gc));
        EntityArr.bombers.forEach(g -> g.render(gc));
        EntityArr.bomberman.bombs.forEach(g -> g.flames.forEach(g1 -> g1.render(gc)));
//        EntityArr.enemies.forEach(g -> {
//            if (g.isVisible()) g.render(gc);
//        });
        EntityArr.items.forEach(g -> {
            if (g.isVisible()) g.render(gc);
        });

    }
}
