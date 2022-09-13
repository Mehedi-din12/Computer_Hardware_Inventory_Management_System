package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ErrorWindowController {
  
  @FXML
  private Label showErrorlabel;
  
  @FXML
  private Button closeButton;
  
  @FXML
  void handleCloseButtonForError(ActionEvent event) {
    Stage currentStage=(Stage) this.closeButton.getScene().getWindow();
    currentStage.close();
  }
  public void setShowErrorlabel(String errorMessage){
    this.showErrorlabel.setText(errorMessage);
  }
}
