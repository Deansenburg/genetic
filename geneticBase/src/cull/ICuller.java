package cull;
import geneticBase.Genome;

import java.util.ArrayList;


public interface ICuller {
	public void cull(int count, ArrayList<Genome> pop);
	public boolean isFinished(int score, int tScore);//target score
	public Genome bestScore(ArrayList<Genome> pop);
}
