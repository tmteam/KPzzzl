
package FxApp;

import ConfigReader.PzzlReader;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        System.out.println("Hello");
        var cfg = new PzzlReader().readResource(getClass().getResource("/Base.pzzl"));
        System.out.println(cfg);

        loadCustom(primaryStage);
        //Parent root = FXMLLoader.load(getClass().getResource("/sample.fxml"));
        //primaryStage.setTitle("Hello World");
        //primaryStage.setScene(new Scene(root, 300, 275));
        //primaryStage.show();
    }
    private void  loadCustom(Stage stage){
        var label = new Label("Hello");               // текстовая метка
        Button button = new Button("Button");           // кнопка
        button.setOnAction(event -> button.setText("You've clicked!"));
        button.setTooltip(new Tooltip("Test tooltip"));
        // button.setBackground(Color.rgb(255,0,0)) new Background({new BackgroundFill()}));
        // button.getOnMouseClicked().handle(s => System.out.println("Clicked"));
        Group group = new Group(button);                // вложенный узел Group

        FlowPane root = new FlowPane(label, group);       // корневой узел
        Scene scene = new Scene(root, 300, 150);        // создание Scene

        /*
        // установка надписи
        Text text = new Text("Hello from JavaFX!");
        text.setLayoutY(80);    // установка положения надписи по оси Y
        text.setLayoutX(100);   // установка положения надписи по оси X

        Group group = new Group(text);

        Scene scene = new Scene(group);
        */
        stage.setScene(scene);
        stage.setTitle("First Application");
        stage.setWidth(300);
        stage.setHeight(250);
        stage.show();
        stage.sizeToScene();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
/*
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello");
    }
}*/