import be.tarsos.dsp.AudioDispatcher;
import be.tarsos.dsp.AudioEvent;
import be.tarsos.dsp.io.TarsosDSPAudioFormat;
import be.tarsos.dsp.io.jvm.AudioDispatcherFactory;
import be.tarsos.dsp.io.jvm.AudioPlayer;
import be.tarsos.dsp.io.jvm.JVMAudioInputStream;
import be.tarsos.dsp.pitch.PitchDetectionHandler;
import be.tarsos.dsp.pitch.PitchDetectionResult;
import be.tarsos.dsp.pitch.PitchProcessor;
import jm.util.Read;
import jm.util.Write;

import javax.sound.sampled.LineUnavailableException;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");
//        AudioDispatcher adp = AudioDispatcherFactory.fromPipe("sample.au", 44100, 4096, 0);
//        TarsosDSPAudioFormat format = adp.getFormat();
//        try {
//            adp.addAudioProcessor(new AudioPlayer(JVMAudioInputStream.toAudioFormat(format)));
//        } catch (LineUnavailableException e) {
//            e.printStackTrace();
//        }
//        new Thread(adp).start();


        PitchDetectionHandler handler = new PitchDetectionHandler() {
            @Override
            public void handlePitch(PitchDetectionResult pitchDetectionResult,
                                    AudioEvent audioEvent) {
                System.out.println(audioEvent.getTimeStamp() + " " + pitchDetectionResult.getPitch());
            }
        };
        AudioDispatcher adp = null;
        try {
            adp = AudioDispatcherFactory.fromDefaultMicrophone(2048, 0);
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
        adp.addAudioProcessor(new PitchProcessor(PitchProcessor.PitchEstimationAlgorithm.YIN, 44100, 2048, handler));
        adp.run();


//        float[] data = Read.audio("sample.au");
//        float[] reversed = new float[data.length];
//        for (int i=0; i< data.length; i++) {
//            reversed[data.length - i - 1] = data[i];
//        }
//        Write.audio(reversed, "ReversedAudio.wav");
//        Write.audio(data, "SavedAudio.aif");
    }
}
