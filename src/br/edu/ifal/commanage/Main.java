package br.edu.ifal.commanage;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import java.io.IOException;

public class Main extends Application {
	
	@Override
	public void start (Stage stage) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("view/layout/LayoutMain.fxml"));
		
		Scene scene = new Scene(root);
		
		stage.setScene(scene);
		stage.setTitle("Commanage - Sistema de Gestão Comercial");
		stage.setResizable(false);
		stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}