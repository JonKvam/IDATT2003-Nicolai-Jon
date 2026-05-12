package edu.ntnu.prog2.view;

import edu.ntnu.prog2.App;
import edu.ntnu.prog2.controller.WelcomeController;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class WelcomeView extends VBox {

  private final TextField nameField;
  private final TextField startCapital;
  private final Button startGameBtn;
  private final Label selectedFileLabel;

  private File selectedFile;

  public WelcomeView(Stage stage) {
    setSpacing(10);
    setPadding(new Insets(30));

    nameField = new TextField();
    nameField.setPromptText("Enter your name");

    startCapital = new TextField();
    startCapital.setPromptText("Enter your start capital");

    Button chooseFileBtn = new  Button("Choose File");
    selectedFileLabel = new  Label("No File selected");

    chooseFileBtn.setOnAction(e -> {
      FileChooser fileChooser = new FileChooser();
      fileChooser.setTitle("Select a File");
      fileChooser.getExtensionFilters().add(
              new FileChooser.ExtensionFilter("CSV files", "*.csv")
      );

      File file = fileChooser.showOpenDialog(stage);
      if (file != null) {
        selectedFile = file;
        selectedFileLabel.setText(file.getAbsolutePath());
      }
    });

    startGameBtn = new Button("Start Game");

    getChildren().addAll(nameField, startCapital, chooseFileBtn, selectedFileLabel, startGameBtn);

  }

  public String getPlayerName() {
    return nameField.getText();
  }

  public String getStartCapital() {
    return startCapital.getText();
  }

  public File getSelectedFile() {
    return selectedFile;
  }

  public void setStartGameBtnOnAction(
          javafx.event.EventHandler<javafx.event.ActionEvent> event
  ) {
    startGameBtn.setOnAction(event);
  }
}
