import javafx.fxml.FXMLLoader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javax.imageio.ImageIO;


public class Main extends Application {

    private boolean firstTime;
    private TrayIcon trayIcon;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("window.fxml"));
        createTrayIcon(stage);
        firstTime = true;
        Platform.setImplicitExit(false);
        stage.setTitle("One-Armed Bandit");
        stage.setScene(new Scene(root));
        stage.show();
    }

    private void createTrayIcon(final Stage stage) {
        if (SystemTray.isSupported()) {
            SystemTray tray = SystemTray.getSystemTray();
            Image image = null;
            try {
                URL url = new URL("http://icons.iconarchive.com/icons/hopstarter/malware/16/Spy-icon.png");
                image = ImageIO.read(url);
            } catch (IOException ex) {
                System.err.println(ex);
            }
            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    hide(stage);
                }
            });
            final ActionListener closeListener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);

                }
            };
            ActionListener showListener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            stage.show();
                        }
                    });

                }
            };
            PopupMenu popup = new PopupMenu();

            MenuItem showItem = new MenuItem("Show");
            showItem.addActionListener(showListener);
            popup.add(showItem);

            MenuItem closeItem = new MenuItem("Close");
            closeItem.addActionListener(closeListener);
            popup.add(closeItem);

            trayIcon = new TrayIcon(image, "One-Armed Bandit", popup);
            trayIcon.addActionListener(showListener);

            try {
                tray.add(trayIcon);
            } catch (AWTException e) {
                System.err.println(e);
            }
        }
    }

    private void showProgramIsMinimizedMsg() {
        if (firstTime) {
            trayIcon.displayMessage("Application is hidden",
                    "But we are watching you",
                    TrayIcon.MessageType.INFO);
            firstTime = false;
        }
    }

    private void hide(final Stage stage) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                if (SystemTray.isSupported()) {
                    stage.hide();
                    showProgramIsMinimizedMsg();
                } else {
                    System.exit(0);
                }
            }
        });
    }

}
