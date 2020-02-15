package csc403;
import stdlib.StdAudio;
import stdlib.StdIn;

public class Play3NoteChords {
	public static void main(String[] args) {
		algs31.BinarySearchST<String, Double> musicFrequency = new algs31.BinarySearchST<String, Double>();
		StdIn.fromFile("data/notes_frequencies.txt");
		while (!StdIn.isEmpty()) 
			{
			String each_line = StdIn.readLine();
			String[] text = each_line.split("\\s+");
			double frequency = Double.parseDouble(text[1]);
			musicFrequency.put(text[0], frequency);
			}
			
		StdIn.fromFile("data/3_note_chords.txt");
		while (!StdIn.isEmpty()) 
			{
			String individual_line = StdIn.readLine();
			String frequencies[] = individual_line.split("\\s+");
			double duration = Double.parseDouble(frequencies[0]);
			
			playChord(duration, musicFrequency.get(frequencies[1]), musicFrequency.get(frequencies[2]), musicFrequency.get(frequencies[3]));
			}	
	}
		public static void playChord(double duration, double... frequencies) {
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
