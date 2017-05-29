package mutate;

import geneticBase.Genome;

import java.util.ArrayList;
import java.util.Random;

public class BaseMutator implements IMutator {

	private IMutator cascade;
	protected Random r = new Random();
	
	public BaseMutator(IMutator m) {
		cascade = m;
	}

	@Override
	public void mutate(double percentage, ArrayList<Genome> genes) {
		if (cascade != null)
			cascade.mutate(percentage, genes);
	}
	
	protected ArrayList<Genome> mutateList(double percentage, ArrayList<Genome> pop)
	{
		ArrayList<Genome> mutationList = new ArrayList<Genome>();
		
		for (Genome g:pop)
		{
			double thresh = percentage*10; int max = 10;
			do
			{
				thresh *= 10;
				max *= 10;
			}
			while(thresh < 1);
			while (r.nextInt(max) <= thresh)
				mutationList.add(g);
		}
		
		return mutationList;
	}

}
