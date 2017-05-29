package cull;

import geneticBase.Genome;

import java.util.ArrayList;

public class CullHighest implements ICuller{

	public void cull(int count, ArrayList<Genome> genes)
	{
		for (int i=0;i<count;i++)
		{
			Genome highest = genes.get(0);
			for (Genome g:genes)
			{
				if (highest.Score() < g.Score())
					highest = g;
			}
			genes.remove(highest);
		}
	}



	@Override
	public Genome bestScore(ArrayList<Genome> pop) {
		Genome highest = pop.get(0);
		for (Genome g : pop) {
			if (highest.Score() < g.Score())
				highest = g;
		}
		return highest;
	}



	@Override
	public boolean isFinished(int score, int tScore) {
		return (score < tScore);
	}

}
