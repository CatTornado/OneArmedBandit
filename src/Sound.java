import sun.audio.AudioPlayer;
        import sun.audio.AudioStream;

        import java.io.*;
        import java.util.Random;

public class Sound {

    private String audio[] = {"audio/get_down_to_business.wav", "audio/use_both_hands.wav", "audio/i_see_you.wav",
            "audio/use_the_second_hand_1.wav", "audio/use_the_second_hand_2.wav"};

    public void play() {
        Random rand = new Random();
        int random = rand.nextInt(audio.length);
        String temp = audio[random];

        InputStream in = null;
        AudioStream out = null;
        //Get the file from the path
        try {
            in = new FileInputStream(temp);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            out = new AudioStream(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Play the file
        AudioPlayer.player.start(out);
        return;
    }
}
