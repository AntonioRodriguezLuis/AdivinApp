package dad.javafx.adivinApp;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AdivinAppController extends Application {

	private Label intervaloLabel = new Label();
	private TextField numIntroducidoText = new TextField();
	private Button comprobarButton = new Button();
	private int numeroAAdivinar = Adivinar();
	private int intentos = 1;
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {

		
		intervaloLabel.setText("Introduce un número del 1 al 100");
		intervaloLabel.setAlignment(Pos.CENTER);	
		
	
		numIntroducidoText.setText("0");
		numIntroducidoText.setAlignment(Pos.CENTER);	
		numIntroducidoText.setMaxWidth(100);
		
	
		comprobarButton.setText("Comprobar");
		comprobarButton.setDefaultButton(true);
		comprobarButton.setAlignment(Pos.CENTER);
		comprobarButton.setOnAction(e ->onComprobarButtonAction(e));

		
		VBox root = new VBox();
		root.setSpacing(5);
		root.setAlignment(Pos.CENTER);
		root.getChildren().addAll(intervaloLabel, numIntroducidoText, comprobarButton);

		Scene escena = new Scene(root, 320, 200);
		
		primaryStage.setScene(escena);
		primaryStage.setTitle("AdivinApp");
		primaryStage.setResizable(true);
		primaryStage.show();
	}

	private void onComprobarButtonAction(ActionEvent e) {
		
		try {
			int numero = Integer.parseInt(numIntroducidoText.getText());
			
			if(numeroAAdivinar == numero) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("AdivinApp");
				alert.setHeaderText("¡Has ganado!");
				alert.setContentText("Solo has necesitado "+intentos+" intentos.\n\nVuelve a jugar y hazlo mejor.");
				alert.showAndWait();
				reiniciar();
			}else if(numeroAAdivinar > numero) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("AdivinApp");
				alert.setHeaderText("¡Has fallado!");
				alert.setContentText("El número a adivinar es mayor que "+numero+".");
				alert.showAndWait();
				
			}else if(numeroAAdivinar < numero){
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("AdivinApp");
				alert.setHeaderText("¡Has fallado!");
				alert.setContentText("El número a adivinar es menor que "+numero+".");
				alert.showAndWait();
			}
		
		} catch (NumberFormatException e2) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("AdivinApp");
			alert.setHeaderText("ERROR");
			alert.setContentText("El número introducido no es valído.");
			alert.showAndWait();
		}
		intentos++;
	}

	private void reiniciar() {
		numeroAAdivinar = Adivinar();
		intentos = 0;
	}

	public static int Adivinar() {
		int numeroAAdivinar = (int)(Math.random() * 100) + 1;
		//borrar luego
		System.out.println(numeroAAdivinar);
		return numeroAAdivinar;
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
