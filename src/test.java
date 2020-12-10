import javafx.application.Application;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;


public class test extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        // TODO Auto-generated method stub
        //Initialising path of the media file, replace this with your file path

        //Instantiating Media class

        stage.setTitle("Playing Audio");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
