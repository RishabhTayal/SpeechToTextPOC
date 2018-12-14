import jm.util.Read;
import jm.util.Write;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        float[] data = Read.audio("sample.au");
        float[] reversed = new float[data.length];
        for (int i=0; i< data.length; i++) {
            reversed[data.length - i - 1] = data[i];
        }
        Write.audio(reversed, "ReversedAudio.wav");
        Write.audio(data, "SavedAudio.aif");
    }
}
