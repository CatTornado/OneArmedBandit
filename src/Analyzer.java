import java.util.ArrayList;
import java.util.Arrays;

public class Analyzer {
    private static ArrayList<String> RIGHT = new ArrayList<>(Arrays.asList("J", "H", "Y", "U", "I", "B", "N", "M", "K",
            "O", "L", "P", "Comma", "Period", "Semicolon", "Quote", "Open Bracket", "Close Bracket", "Slash"));
    private static ArrayList<String> LEFT = new ArrayList<>(Arrays.asList("F", "G", "T", "R", "E", "D", "C", "V", "W",
            "S", "X", "Q", "A", "Z"));
    private static double MAX_RATIO_TOLERANCE = 1.1;

    private Notification notification = new Notification();

    private boolean lastPressedRight = false;
    private boolean lastPressedLeft = false;

    private long lastKeyTime = System.currentTimeMillis();
    private long lastDelay = 0;
    private long totalDelaySame = 0;
    private long totalDelaySwitch = 0;
    private double countSame = 0;
    private double countSwitch = 0;

    private int cooldown = 6;
    private int streak = 0;


    public void getLetter(String s) {
        if (RIGHT.contains(s) || LEFT.contains(s)) {
            if (lastKeyTime + 10000 < System.currentTimeMillis()) {
                cooldown = 6;
                resetParams();
            }
            lastDelay = System.currentTimeMillis() - lastKeyTime;
            lastKeyTime = System.currentTimeMillis();

            if (lastPressedRight || lastPressedLeft) {
                cooldown--;
            }

            if (RIGHT.contains(s)) {
                if (lastPressedRight) {
                    countSame++;
                    totalDelaySame += lastDelay;
                } else if (lastPressedLeft){
                    countSwitch++;
                    totalDelaySwitch += lastDelay;
                }
                lastPressedRight = true;
                lastPressedLeft = false;
            } else if (LEFT.contains(s)) {
                if (lastPressedLeft) {
                    countSame++;
                    totalDelaySame += lastDelay;
                } else if (lastPressedRight) {
                    countSwitch++;
                    totalDelaySwitch += lastDelay;
                }
                lastPressedRight = false;
                lastPressedLeft = true;
            }
            if (cooldown <= 0 && countSame > 2 && countSwitch > 2) {
                showResults();
            }
        }
        else {
            lastPressedRight = false;
            lastPressedLeft = false;
            lastKeyTime = System.currentTimeMillis();
        }
    }

    private void showResults() {
        if ((totalDelaySwitch / countSwitch) / (totalDelaySame / countSame) > MAX_RATIO_TOLERANCE) {
            streak++;
            if (streak >= 3) {
                notification.showNotification();
                streak = 0;
            }
        } else {
            streak = 0;
        }
        cooldown = 5;
        resetParams();
    }

    private void resetParams() {
        lastPressedRight = false;
        lastPressedLeft = false;
        lastKeyTime = System.currentTimeMillis();
        lastDelay = 0;
        totalDelaySame = 0;
        totalDelaySwitch = 0;
        countSame = 0;
        countSwitch = 0;
    }
}