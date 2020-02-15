//Seth Weber

package csc402examsolutions;

import stdlib.*;

public class PlayChordsFromFile {
	
	public static void main(String [] args) {
		
		StdIn.fromFile("data/risingchords.txt");
		while (!StdIn.isEmpty()) {
			
			double[] chords = new double[3];
			String data_1 = StdIn.readLine();
			String[] data_2 = data_1.split("\\s+");
			for (int i = 0; i < data_2.length; i++) {
				chords[i] = Double.parseDouble(data_2[i]);
				playChord(chords);
				
			}
		}	
	}
	
	
	public static void playChord(double... frequencies) {
		double duration = 0.5;
		final int sliceCount = (int) (StdAudio.SAMPLE_RATE * duration);
		final double[] slices = new double[sliceCount+1];
		for (int i = 0; i <= sliceCount; i++) {
			for (double frequency: frequencies) {
				slices[i] += Math.sin(2 * Math.PI * i * frequency / StdAudio.SAMPLE_RATE);
			}
			slices[i] /= frequencies.length;
		}
		StdAudio.play(slices);
	}

}
