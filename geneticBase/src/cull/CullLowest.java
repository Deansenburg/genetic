package cull;
import geneticBase.Genome;

import java.util.ArrayList;


public class CullLowest implements ICuller{

	public void cull(int count, ArrayList<Genome> genes)
	{
		for (int i=0;i<count;i++)
		{
			Genome lowest = genes.get(0);
			for (Genome g:genes)
			{
				if (lowest.Score() > g.Score())
					lowest = g;
			}
			genes.remove(lowest);
		}
	}

	@Override
	public boolean isFinished(int score, int tScore) {
		return (score > tScore);
	}

	@Override
	public Genome bestScore(ArrayList<Genome> pop) {
		Genome lowest = pop.get(0);
		for (Genome g : pop) {
			if (lowest.Score() > g.Score())
				lowest = g;
		}
		return lowest;
	}
}
