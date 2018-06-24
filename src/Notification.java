import javafx.application.Platform;
import javafx.util.Duration;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

public class Notification {
    private static int a = 0; // переменная, с помощью которой реализован выбор между видами уведомлений

    public void choice1() {
        a = 1;
    }

    public void choice2() {
        a = 0;
    }

    public void choice3() {
        a = 2;
    }

    public void choice4() {
        a = 3;
    }

    private Sound sound = new Sound();
    private TrayNotification tray = new TrayNotification("Use both hands!",
            "WE ARE WATCHING YOU!", NotificationType.WARNING);

    public void showNotification() {
        switch (a) {
            case 0:
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        tray.showAndDismiss(Duration.seconds(3));
                    }
                });
                Platform.setImplicitExit(false);
                break;
            case 1:
                sound.play();
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        tray.showAndDismiss(Duration.seconds(3));
                    }
                });
                Platform.setImplicitExit(false);
                break;
            case 2:
                sound.play();
                break;
            default:
                break;
        }
    }
}
