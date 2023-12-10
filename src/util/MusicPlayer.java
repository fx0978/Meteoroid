package util;

import java.io.IOException;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;

public class MusicPlayer {
    private Sequencer sequencer;
    private Sequence sequence;

    public MusicPlayer() {
        try {
            sequencer = MidiSystem.getSequencer();
            sequencer.open();
            sequence = new Sequence(Sequence.PPQ, 4);
            sequencer.setTempoInBPM(120);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * gets the music file from the path src/music
     * @param name
     * @return sequence
     */
    private Sequence getMusic(String name) {
        try {
            // Read the MIDI file back
            return MidiSystem.getSequence(new java.io.File("src/music/" + name));

        } catch (IOException | InvalidMidiDataException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    //TODO: Combine the below play music methods into one and add an additional parameter?
    /**
     * Plays the music once given the file name
     * @param name
     */
    public void playMusic(String name) {
        try {
            sequence = getMusic(name);
            sequencer.setSequence(sequence);
            sequencer.setLoopCount(0);
            sequencer.setTempoInBPM(120);
            sequencer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Plays the music in a loop given the file name
     * @param name
     */
    public void playMusicLoop(String name) {
        try {
            sequence = getMusic(name);
            sequencer.setSequence(sequence);
            sequencer.setLoopCount(sequencer.LOOP_CONTINUOUSLY);
            sequencer.setTempoInBPM(120);
            sequencer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * For external calls to top the music
     */
    public void stop() {
        sequencer.stop();
    }
}
