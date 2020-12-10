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

public class Game extends Application {

    public static int level = 1;
    public static int WIDTH = 20;
    public static int HEIGHT = 15;

    private GraphicsContext graphicsContext;
    private Canvas canvas;

    public static void main(String[] args) {
        Application.launch(Game.class);
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
                Management.bomberman.goLeft();
            }
            if (keyEvent.getCode().toString().equals("DOWN")) {
                Management.bomberman.goDown();
            }
            if (keyEvent.getCode().toString().equals("UP")) {
                Management.bomberman.goUp();
            }
            if (keyEvent.getCode().toString().equals("RIGHT")) {
                Management.bomberman.goRight();
            }
            if (keyEvent.getCode().toString().equals("SPACE")) {
                Management.bomberman.putBomb();
            }
            if (keyEvent.getCode() == KeyCode.B) {
                Management.ballooms.clear();
            }
            if (keyEvent.getCode() == KeyCode.O) {
                Management.oneals.clear();
            }
        });

    }


    public void update() {
        Management.bomberman.update();
        Management.ballooms.forEach(Balloom::update);
        Management.oneals.forEach(Oneal::update);
        Management.dolls.forEach(Doll::update);
        Management.kondorias.forEach(Kondoria::update);
        Management.bomberman.bombs.forEach(Bomb::update);
        Management.bricks.forEach(Brick::update);
        Management.bomberman.bombs.forEach(bomb -> bomb.getUp().forEach(Flame::update));
        Management.bomberman.bombs.forEach(bomb -> bomb.getDown().forEach(Flame::update));
        Management.bomberman.bombs.forEach(bomb -> bomb.getLeft().forEach(Flame::update));
        Management.bomberman.bombs.forEach(bomb -> bomb.getRight().forEach(Flame::update));
        Management.items.forEach(item -> {
            if (item.isVisible()) item.update();
        });
        Management.bomberman.update();
        Management.portals.forEach(Entity::update);
    }

    public void render() {
        graphicsContext.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        Management.grasses.forEach(g -> g.render(graphicsContext));
        Management.portals.forEach(g -> g.render(graphicsContext));
        Management.walls.forEach(g -> g.render(graphicsContext));
        Management.bricks.forEach(g -> g.render(graphicsContext));
        Management.ballooms.forEach(g -> g.render(graphicsContext));
        Management.oneals.forEach(g -> g.render(graphicsContext));
        Management.dolls.forEach(g -> g.render(graphicsContext));
        Management.kondorias.forEach(g -> g.render(graphicsContext));
        Management.bomberman.bombs.forEach(g -> g.render(graphicsContext));
        Management.bombers.forEach(g -> g.render(graphicsContext));
        Management.bomberman.bombs.forEach(g -> g.flames.forEach(g1 -> g1.render(graphicsContext)));
        Management.items.forEach(g -> {
            if (g.isVisible()) g.render(graphicsContext);
        });

    }
}
