package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import uet.oop.bomberman.bomb.Bomb;
import uet.oop.bomberman.bomb.Flame;
import uet.oop.bomberman.enemy.Balloom;
import uet.oop.bomberman.enemy.Doll;
import uet.oop.bomberman.enemy.Kondoria;
import uet.oop.bomberman.enemy.Oneal;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.graphics.Sprite;

public class BombermanGame extends Application {

    public static int level = 1;
    public static int WIDTH = 20;
    public static int HEIGHT = 15;

    private GraphicsContext graphicsContext;
    private Canvas canvas;

    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) {
        CreateMap.createMapByLevel(1);
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        graphicsContext = canvas.getGraphicsContext2D();

        Group root = new Group();
        root.getChildren().add(canvas);

        Scene scene = new Scene(root);

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

        scene.setOnKeyPressed(keyEvent -> {
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
                EntityArr.ballooms.clear();
            }
            if (keyEvent.getCode() == KeyCode.O) {
                EntityArr.oneals.clear();
            }
        });

    }


    public void update() {
        EntityArr.bomberman.update();
        EntityArr.ballooms.forEach(Balloom::update);
        EntityArr.oneals.forEach(Oneal::update);
        EntityArr.dolls.forEach(Doll::update);
        EntityArr.kondorias.forEach(Kondoria::update);
        EntityArr.bomberman.bombs.forEach(Bomb::update);
        EntityArr.bricks.forEach(Brick::update);
        EntityArr.bomberman.bombs.forEach(g -> g.getUp().forEach(Flame::update));
        EntityArr.bomberman.bombs.forEach(g -> g.getDown().forEach(Flame::update));
        EntityArr.bomberman.bombs.forEach(g -> g.getLeft().forEach(Flame::update));
        EntityArr.bomberman.bombs.forEach(g -> g.getRight().forEach(Flame::update));
        EntityArr.items.forEach(item -> {
            if (item.isVisible()) item.update();
        });
        EntityArr.bomberman.update();
        EntityArr.portals.forEach(Entity::update);
    }

    public void render() {
        graphicsContext.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        EntityArr.grasses.forEach(g -> g.render(graphicsContext));
        EntityArr.portals.forEach(g -> g.render(graphicsContext));
        EntityArr.walls.forEach(g -> g.render(graphicsContext));
        EntityArr.bricks.forEach(g -> g.render(graphicsContext));
        EntityArr.ballooms.forEach(g -> g.render(graphicsContext));
        EntityArr.oneals.forEach(g -> g.render(graphicsContext));
        EntityArr.dolls.forEach(g -> g.render(graphicsContext));
        EntityArr.kondorias.forEach(g -> g.render(graphicsContext));
        EntityArr.bomberman.bombs.forEach(g -> g.render(graphicsContext));
        EntityArr.bombers.forEach(g -> g.render(graphicsContext));
        EntityArr.bomberman.bombs.forEach(g -> g.flames.forEach(g1 -> g1.render(graphicsContext)));
        EntityArr.items.forEach(g -> {
            if (g.isVisible()) g.render(graphicsContext);
        });

    }
}
