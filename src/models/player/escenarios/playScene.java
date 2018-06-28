package models.player.escenarios;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import models.player.GameValues;
import models.player.PlayerRick.Gravedad;
import models.player.PlayerRick.PlayerValuesRick;

public class playScene extends Scene {
    private ImageView imageView;
    private PlayerValuesRick values;
    private Gravedad g;
    private Pane root;

    public playScene(Pane root, double width, double height, Stage primaryStage) {

        super(root, width, height);
        this.root=root;
        primaryStage.setScene(this);
        values=PlayerValuesRick.getInstance();
        imageView=new ImageView();
        content();

    }

    public ImageView getImageView() {
        return imageView;
    }

    public void content(){
        imageView.setImage(new Image(values.getClass().getResource(PlayerValuesRick.derecha[0]).toExternalForm()));
        imageView.setFitHeight(PlayerValuesRick.sizePlayer[0]);
        imageView.setFitWidth(PlayerValuesRick.sizePlayer[1]);
        imageView.setPreserveRatio(true);
        //AnchorPane.
        imageView.setLayoutX(10);
        imageView.setLayoutY(103);
        //System.out.println("getScene");
        root.getChildren().addAll(imageView);

        this.addEventFilter(KeyEvent.KEY_PRESSED,new EventHandler<KeyEvent>(){

                    public void handle(KeyEvent ke){
                        if (ke.getCode()==KeyCode.A){
                            pasoIzquierda(values.pasoIzquierda,values.izquierda);

                            //avanzarJugador(values.pasoIzquierda,-1,values.izquierda);
                        }
                        else if(ke.getCode()==KeyCode.D){
                            //imageView.setImage(new Image(getClass().getResource(PlayerValuesRick.derecha[1]).toExternalForm()));
                            //avanzarJugador(values.pasoDerecha,1,values.derecha);
                            pasoderecha(values.pasoDerecha,values.derecha);

                            //overlaping();
                            //imageView.setLayoutX(imageView.getLayoutX()+10);

                        }
                        else if(ke.getCode()==KeyCode.W){
                            imageView.setLayoutY(imageView.getLayoutY()-10);
                        }
                        else if(ke.getCode()==KeyCode.S){

                            imageView.setLayoutY(imageView.getLayoutY()+10);}


                        else if(ke.getCode()==KeyCode.M){

                            salto(values.saltoDerecha,1,values.derecha);

                            try {
                                //new Granade(root).start(primaryStage);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            //Granade!!!!
                            //imageView.setLayoutY(imageView.getLayoutY()+10);
                        }

                        else if(ke.getCode()==KeyCode.N){

                            salto(values.saltoIzquierda,-1,values.izquierda);

                            try {
                                //new Granade(root).start(primaryStage);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            //Granade!!!!
                            //imageView.setLayoutY(imageView.getLayoutY()+10);
                        }
                    }

                }

        );

        //scenariosValues.root.getChildren().addAll(imageView);
        setGravedad();
        //return this;
    }

    private void salto(String[] pasos, int discriminante, String[] posicionFinal) {
        if (PlayerValuesRick.action){

            PlayerValuesRick.action=false;


            imageView.setImage(new Image(values.getClass().getResource(pasos[0]).toExternalForm()));

            for (int i=1;i<2;i++){


                //Movimiento en Y
                Thread thread2 = new Thread(()->{
                    for (int j =1;j<13;j++){
                        Gravedad.sleeping(25);
                        Platform.runLater(() -> imageView.setLayoutY(imageView.getLayoutY() - 10));
                        //}
                    }GameValues.gravedad=true;
                    g.setPosicionFinal(posicionFinal);


                    //imageView.setImage(new Image(values.getClass().getResource(posicionFinal[0]).toExternalForm()));

                });
                thread2.setDaemon(true);
                //thread.start();
                thread2.start();

            }}
    }



    public void avanzarJugador(String[] pasos, int discriminante, String[] posicionfinal) {

        if (PlayerValuesRick.action) { PlayerValuesRick.action=false;
            Thread thread2 = new Thread(() -> {
                for (int j = 0; j < PlayerValuesRick.pasosTotales; j++) {

                    imageView.setImage(new Image(values.getClass().getResource(pasos[j]).toExternalForm()));

                    //moviendo jugador derecha o izquierda
                    paso(pasos,discriminante,posicionfinal);


                    try { Thread.sleep(PlayerValuesRick.tiempoPaso / PlayerValuesRick.pasosTotales);} catch (InterruptedException e) {e.printStackTrace();}
                }
                //imageView.setImage(new Image(values.getClass().getResource(posicionfinal[0]).toExternalForm()));
                g.setPosicionFinal(posicionfinal);
                PlayerValuesRick.action=true;
            }); thread2.setDaemon(true); thread2.start();
        }else{
            paso(pasos,discriminante,posicionfinal);

        }

    }

    private void paso(String[] pasos, int discriminante, String[] posicionfinal){

        //moviendo jugador derecha o izquierda
        if(discriminante<1){
            if(imageView.getLayoutX()<0){
                Platform.runLater(() -> imageView.setLayoutX(0));
            }else{
                Platform.runLater(() -> imageView.setLayoutX(imageView.getLayoutX() +
                        (PlayerValuesRick.distanciaPaso*discriminante)));
            }
        }else{
            if(imageView.getLayoutX()>GameValues.dimension[0]){
                Platform.runLater(() -> imageView.setLayoutX(GameValues.dimension[0]-values.sizePlayer[0]));
            }else{
                Platform.runLater(() -> imageView.setLayoutX(imageView.getLayoutX() +
                        (PlayerValuesRick.distanciaPaso*discriminante)));
            }
        }
    }

    public void pasoderecha(String[] pasos, String[] posicionfinal){
        imageView.setImage(new Image(values.getClass().getResource(pasos[0]).toExternalForm()));

        if(imageView.getLayoutX()>GameValues.dimension[0]-values.sizePlayer[0]){
            Platform.runLater(() -> imageView.setLayoutX(GameValues.dimension[0]-values.sizePlayer[0]));
        }else{
            Platform.runLater(() -> imageView.setLayoutX(imageView.getLayoutX() +
                    values.distanciaPaso));
        }
        g.setPosicionFinal(posicionfinal);

    }

    public void pasoIzquierda(String[] pasos, String[] posicionfinal){
        imageView.setImage(new Image(values.getClass().getResource(pasos[0]).toExternalForm()));

        if(imageView.getLayoutX()<0){
            Platform.runLater(() -> imageView.setLayoutX(0));
        }else{
            Platform.runLater(() -> imageView.setLayoutX(imageView.getLayoutX() -
                    values.distanciaPaso));
        }
        g.setPosicionFinal(posicionfinal);

    }


    public void overlaping(){
        //if(imageView.getBoundsInParent().intersects(imageView2.getBoundsInParent()))
            System.out.println("overlapping :v");
    }

    public void setGravedad(){
        g=new Gravedad(imageView,values);
        g.setPosicionFinal(values.derecha);
        try {
            g.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
