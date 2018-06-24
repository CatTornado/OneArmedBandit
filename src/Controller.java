import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;


public class Controller {
    private Notification notification = new Notification();
    private Listener listener = new Listener();

    @FXML
    private Button button;

    @FXML
    private void onStart() {
        listener.listen();
        button.setDisable(true);
    }

    @FXML
    private void onStop() {
        System.exit(0);
    }

    @FXML
    private void onChoice1() {
        notification.choice1(); // sound&pop-up
    }

    @FXML
    private void onChoice2() {
        notification.choice2(); // pop-up
    }

    @FXML
    private void onChoice3() {
        notification.choice3(); // sound
    }

    @FXML
    private void onChoice4() {
        notification.choice4(); // no notifications
    }


    @FXML
    private void onAbout() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("About");
        alert.setContentText("We are deeply convinced that it is very important to type with both hands. We are watching you." +
                "\n\nDevelopers: CuteLittleSeal, CatTornado." +
                "\n\nStarring Andrey Dmitriev.");
        alert.show();
    }

}