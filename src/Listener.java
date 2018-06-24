import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Listener implements NativeKeyListener {
    private Analyzer analyzer = new Analyzer();

    public void nativeKeyPressed(NativeKeyEvent event) {
        analyzer.getLetter(NativeKeyEvent.getKeyText(event.getKeyCode()));
    }

    public void nativeKeyReleased(NativeKeyEvent event) {
//        Analyzer.getLetter(NativeKeyEvent.getKeyText(event.getKeyCode()));
    }

    public void nativeKeyTyped(NativeKeyEvent event) {
//        System.out.println(NativeKeyEvent.getKeyText(event.getKeyCode()));
    }

    public void listen() {

        Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
        logger.setLevel(Level.WARNING);
        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException ex) {
            System.err.println("There was a problem registering the native hook.");
            System.err.println(ex.getMessage());

            System.exit(1);
        }
        GlobalScreen.addNativeKeyListener(new Listener());


    }
}