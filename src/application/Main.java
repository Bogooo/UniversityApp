package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

@SuppressWarnings("unused")
public class Main extends Application {
	public static Scene scene;
	Connection connection = null;

	@SuppressWarnings("deprecation")
	@Override
	public void start(Stage primaryStage) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
		} catch (Exception ex) {
			System.err.println("An Exception occured during JDBC Driver loading." + " Details are provided below:");
			ex.printStackTrace(System.err);
		}
		ResultSetMetaData rsmd = null;
		try {
			connection = DriverManager
					.getConnection("jdbc:mysql://localhost/proiect?user=Proiect&password=Proiect2022!");
		} catch (SQLException sqlex) {
			System.err.println("An SQL Exception occured. Details are provided below:");
			sqlex.printStackTrace(System.err);
		}
		try {
			primaryStage.setTitle("UTCN");
			scene = Login.getStartScene(primaryStage, connection);
			// scene = Admin.getAdminScene(false);
			// scene = Profesor.getProfScene();
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}

		primaryStage.setOnCloseRequest(event -> {
			System.out.println("Stage is closing");
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
				}
				connection = null;
			}
			// Save file
		});

	}

	public static void main(String[] args) {
		launch(args);
	}
}
