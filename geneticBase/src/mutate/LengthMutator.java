package mutate;

import geneticBase.Genome;

import java.util.ArrayList;



public class LengthMutator extends BaseMutator{

	public LengthMutator(IMutator m) {
		super(m);
	}

	@Override
	public void mutate(double percentage, ArrayList<Genome> genes) {
		for (Genome g : mutateList(percentage, genes)) {
			if (r.nextInt(2) == 0)
			{
				if(g.String().length() > 8) {
					for (int i = 0; i < 8; i++) {
						g.removeAt(r.nextInt(g.String().length()));
					}
				}
			}
			else
			{
				for (int i = 0; i < 8; i++) {
					g.add('0');
				}
			}
		}
		super.mutate(percentage, genes);
	}
}
