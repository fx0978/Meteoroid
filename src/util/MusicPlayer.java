package util;

import java.io.IOException;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;

public class MusicPlayer {
    private static Sequencer musicSequencer;
    private static Sequencer effectSequencer;
    private static Sequencer startSequencer;
    private static Sequence sequence;

    static {
        try {
            startSequencer = MidiSystem.getSequencer();
            startSequencer.open();
            startSequencer.setTempoInBPM(120);

            musicSequencer = MidiSystem.getSequencer();
            musicSequencer.open();
            sequence = new Sequence(Sequence.PPQ, 4);
            musicSequencer.setTempoInBPM(120);

            effectSequencer = MidiSystem.getSequencer();
            effectSequencer.open();
            effectSequencer.setTempoInBPM(120);            

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * gets the music file from the path src/music
     * 
     * @param name
     * @return sequence
     */
    private static Sequence getMusic(String name) {
        try {
            // Read the MIDI file back
            return MidiSystem.getSequence(new java.io.File("src/music/" + name));

        } catch (IOException | InvalidMidiDataException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    // TODO: Combine the below play music methods into one and add an additional
    // parameter?
    /**
     * Plays the music once given the file name. This is used only for the start whistle (at beginning of start).
     * 
     * @param name
     */
    public static void playStartWhistle(String name) {
        startSequencer.stop();

        Thread thread = new Thread(() -> {
            try {
                sequence = getMusic(name);
                startSequencer.setSequence(sequence);
                startSequencer.setLoopCount(0);
                startSequencer.setTempoInBPM(120);
                startSequencer.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        thread.start();
    }

    /**
     * Plays a short effect sound (e.g. bullets)
     * @param name
     */
    public static void playEffect(String name) {

        Thread thread = new Thread(() -> {
            try {
                sequence = getMusic(name);
                effectSequencer.setSequence(sequence);
                effectSequencer.setLoopCount(0);
                effectSequencer.setTempoInBPM(120);
                effectSequencer.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        thread.start();
    }

    /**
     * Plays the music in a loop given the file name
     * 
     * @param name
     */
    public static void playMusicLoop(String name) {
        musicSequencer.stop();

        Thread thread = new Thread(() -> {
            try {
                sequence = getMusic(name);
                musicSequencer.setSequence(sequence);
                musicSequencer.setLoopCount(Sequencer.LOOP_CONTINUOUSLY);
                musicSequencer.setTempoInBPM(120);
                musicSequencer.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        thread.start();
    }

    /**
     * For external calls to top the music
     */
    public static void stop() {
        musicSequencer.stop();
    }
}
