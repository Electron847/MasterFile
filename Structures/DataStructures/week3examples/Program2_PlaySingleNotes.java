package csc403;

import stdlib.StdAudio;
import stdlib.StdIn;

public class PlaySingleNotes {

	public static void main(String[] args) 
	{
		algs31.BinarySearchST<String, Double> note1 = new algs31.BinarySearchST<String, Double>();
		StdIn.fromFile("data/notes_frequencies.txt");
		while( !StdIn.isEmpty())
		{
			double[] music1 = new double[2];
			String chord1 = StdIn.readLine();	
			String[] chord2 = chord1.split("\\s+"); 
			double chord3 = Double.parseDouble(chord2[1]);
			note1.put(chord2[0], chord3);
		}
		StdIn.fromFile("data/single_notes.txt");
		while(!StdIn.isEmpty())
		{
			double[] singlnote = new double[2];
			String notes = StdIn.readLine();
			String[] noteandfreq = notes.split("\\s+");
			double duration = Double.parseDouble(noteandfreq[0]);
			playNote(duration, note1.get(noteandfreq[1]));
		}
		}

public static void playNote(double duration, double frequency) 
{
	final int sliceCount = (int) (StdAudio.SAMPLE_RATE * duration);
	final double[] slices = new double[sliceCount+1];
	for (int i = 0; i <= sliceCount; i++) {
		slices[i] += Math.sin(2 * Math.PI * i * frequency / StdAudio.SAMPLE_RATE);
	}
	StdAudio.play(slices);
}
}


