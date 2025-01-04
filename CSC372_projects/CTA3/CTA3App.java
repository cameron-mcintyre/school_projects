package CSC372_projects.CTA3;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.Random;

/*Order of operations: 
1.  create application, 
2.  create stage, 
3.  create scene, 
4.  create pane, 
5.  create content for pane, 
6.  populate content, 
7.  add content to pane, 
8.  add pane to scene, 
9.  set scene in stage, 
10. show stage.*/

public class CTA3App extends Application {
    //Create application
    @Override
    public void start(Stage menuApplication) throws IOException{

        menuApplication.setTitle("Critical Thinking Assignment 3 - GUI");
        
        //Create scene but leave it null until we can fill it.
        Scene scene = null;
        
        //Create pane.
        BorderPane border = new BorderPane();

        //Create content for pane, a VBox in this case.
        VBox primaryBox = new VBox(10);
        TextArea textOutput = new TextArea();
        MenuItem menuItem1 = new MenuItem("Print current date/time");
        MenuItem menuItem2 = new MenuItem("Print text field to log file");
        MenuItem menuItem3 = new MenuItem("Change background color");
        MenuItem menuItem4 = new MenuItem("Exit program");
        MenuButton menuButton = new MenuButton("Menu", null, menuItem1, menuItem2, menuItem3, menuItem4);

        //Add the content to the VBox.
        primaryBox.getChildren().addAll(menuButton, textOutput);

        //Add the VBox to the pane.
        border.setCenter(primaryBox);

        //Add the pane to the scene.
        scene = new Scene(border, 500, 500);

        //Set the scene into the stage and show it.
        menuApplication.setScene(scene);
        menuApplication.show();

        //When the menu is clicked, these next inner classes will handle the clicks.
        menuItem1.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
                String ldt = LocalDateTime.now().toString();
                textOutput.appendText("\n" + ldt);
            }
        });

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Screenshot page break~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        menuItem2.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
                try(FileOutputStream fileStream = new FileOutputStream("C:/Users/lenovo/school_projects/CSC372_projects/CTA3/log.txt", true);
                PrintWriter outFS = new PrintWriter(fileStream)) {
                    outFS.print(textOutput.getText());
                    textOutput.appendText("\nLog file created!");
                    outFS.close();
                } catch (IOException error) {
                    textOutput.appendText("\nError writing to log file!");
                    error.printStackTrace();
                }
            }
        });

        menuItem3.setOnAction(new EventHandler<ActionEvent>(){
            
            @Override
            public void handle(ActionEvent event){
                Random rand = new Random();
                int r = rand.nextInt(150);
                int g = 255;
                int b = rand.nextInt(150);

                String rgb = String.format("#%02X%02X%02X", r, g, b);
                primaryBox.setStyle("-fx-background-color: " + rgb + ";");
                textOutput.appendText("\n" + rgb + " Random green color set!");
            }
        });

        menuItem4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.exit(0);
            }
        });
    }
    //Finally, the main program.
    public static void main(String[] args) throws IOException {
        launch(args);
    }
}