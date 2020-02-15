package csc403;
import stdlib.*;

public class PlaySingleNotes{
	
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
			
		StdIn.fromFile("data/single_notes.txt");
		while (!StdIn.isEmpty()) 
			{
			String individual_line = StdIn.readLine();
			String more_text[] = individual_line.split("\\s+");
			double duration = Double.parseDouble(more_text[0]);
			playNote(duration, musicFrequency.get(more_text[1]));
			}	
	}
	public static void playNote(double duration, double frequency) {
		final int sliceCount = (int) (StdAudio.SAMPLE_RATE * duration);
		final double[] slices = new double[sliceCount+1];
		for (int i = 0; i <= sliceCount; i++) {
			slices[i] += Math.sin(2 * Math.PI * i * frequency / StdAudio.SAMPLE_RATE);
			
		
		}
		StdAudio.play(slices);
}
}

