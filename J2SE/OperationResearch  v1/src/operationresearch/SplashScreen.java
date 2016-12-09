package operationresearch;

import operationresearch.MainStage;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

/**
 *
 * @author Amr
 */
public class SplashScreen extends Application {

    private final int SPLASH_WIDTH = 300;
    private final int SPLASH_HEIGHT = 300;
    private Pane splashLayout;

    public static void main(String[] args) {
        launch();
    }

    private void showSplash(Stage initStage) throws IOException {
        ImageView splash = new ImageView(new Image("/source/loading.gif"));
        splashLayout = new Pane();
        splashLayout.getChildren().addAll(splash);
        splashLayout.setEffect(new DropShadow());
        
        Scene splashScene = new Scene(splashLayout);
        
        initStage.initStyle(StageStyle.UNDECORATED);
        
         Rectangle2D bounds = Screen.getPrimary().getBounds();
        
        initStage.setScene(splashScene); 
        
       
        // Set Splash Screeen in the Center of Screen 
        initStage.setX(bounds.getMinX() + bounds.getWidth() / 2 - SPLASH_WIDTH / 2);     
        initStage.setY(bounds.getMinY() + bounds.getHeight() / 2 - SPLASH_HEIGHT / 2);
        
        initStage.show();

    }

    @Override
    public void start(final Stage initStage) throws Exception {
        showSplash(initStage);
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                FadeTransition fadeSplash = new FadeTransition(Duration.seconds(1), splashLayout);
                fadeSplash.setFromValue(10.0);
                fadeSplash.setToValue(0.0);
                fadeSplash.setOnFinished((ActionEvent actionEvent) -> {
                    initStage.hide();
                    try {
                        new MainStage().showMainStage();
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }
                });
                fadeSplash.play();
                timer.cancel();
            }
        };
        timer.scheduleAtFixedRate(task, 4000, 100);
    }


}
