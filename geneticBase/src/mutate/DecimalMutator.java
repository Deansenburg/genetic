package mutate;

import geneticBase.Genome;

import java.util.ArrayList;

public class DecimalMutator extends BaseMutator {

	int min, max;
	
	public DecimalMutator(int mn, int mx, IMutator m) {
		super(m);
		//System.out.println("Min:"+mn+" Max:"+mx);
		min = mn;
		max = mx;
	}

	@Override
	public void mutate(double percentage, ArrayList<Genome> genes) {
		super.mutate(percentage, genes);
		for (Genome g : mutateList(percentage, genes)) {
			int i = r.nextInt(g.String().length());
			int dec = (int)g.String().charAt(i);
			if (r.nextBoolean())
				dec-=1;
			else
				dec+=1;
			if (dec > max)dec = min;
			if (dec < min)dec = max;
			g.setCharAt((char)dec, i);
		}
	}

}
