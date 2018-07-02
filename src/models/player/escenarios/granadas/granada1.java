package models.player.escenarios.granadas;

import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import models.player.GameValues;
import models.player.PlayerRick.Gravedad;
import models.player.PlayerRick.Player;
import models.player.peldannos.Peldanno;

public class granada1 implements granada <granadaUno>{
    public static String[] granadaEfecto;

    @Override
    public void activar() {
        granada.setImage(new Image(this.getClass().getResource(granadaEfecto[0]).toExternalForm()));
        Thread uno = new Thread(()->{

            for (int i=1 ; i<15;i++){
                //GameValues.permitirSalto=false;

                Platform.runLater(() -> granada.setLayoutY(granada.getLayoutY() - 15));
                Platform.runLater(() -> granada.setLayoutY(granada.getLayoutY() + 5));
                Gravedad.sleeping(25);
                //}
            }//GameValues.gravedad=true;
            for (int i=1 ; i<15;i++){
                //GameValues.permitirSalto=false;

                //Platform.runLater(() -> granada.setLayoutY(granada.getLayoutY() - 15));
                Platform.runLater(() -> granada.setLayoutY(granada.getLayoutY() + 5));
                Gravedad.sleeping(25);
                //}
            }
            while(Gravedad.efectoGravedad(granada)){
                if((GameValues.dimension[1] - granadaUno.dimension[0]) > granada.getLayoutY()){
                    Platform.runLater(() -> granada.setLayoutY(granada.getLayoutY() +
                            5));

                }else {
                    break;
                }


            Gravedad.sleeping(25);



        }



            Gravedad.sleeping(2500);
            granada.setImage(new Image(this.getClass().getResource(granadaEfecto[1]).toExternalForm()));

            //g.setPosicionFinal(posicionFinal);


            //imageView.setImage(new Image(values.getClass().getResource(posicionFinal[0]).toExternalForm()));

        });
        uno.setDaemon(true);
        //thread.start();
        uno.start();
    }

    @Override
    public ImageView getGranada() {
        return null;
    }


}
