package models.player;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import static java.lang.Thread.sleep;

public class Granade extends Application {
    private Group root;
    private ImageView imageView;

    public static void main (String... args){
        //ejecutar app(new constructor)
        launch (args);
    }

    public Granade(Group root){
        this.root = root;
    }

    public void setRoot(Group root) {
        this.root = root;
    }


    @Override
    public void start(Stage primaryStage) throws Exception {

        imageView=new ImageView();
        imageView.setImage(new Image(getClass().getResource("dragon.gif").toExternalForm()));




        imageView.setLayoutX(10);
        imageView.setLayoutY(24);


        root.getChildren().add(imageView);
        for (int i=1;i<20;i++){
            //imageView.setLayoutX(imageView.getLayoutX()+10);
            //root.getChildren().add(imageView);
            //primaryStage.show();
            try{
                sleep(100);
                imageView.setLayoutX(imageView.getLayoutX()+10);
                //primaryStage.show();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }



    }


}
