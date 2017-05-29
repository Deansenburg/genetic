package mutate;

import geneticBase.Genome;

import java.util.ArrayList;

public class BinaryMutator extends BaseMutator {

	public BinaryMutator(IMutator m) {
		super(m);
	}

	public void mutate(double percentage, ArrayList<Genome> genes) {
		for (Genome g : mutateList(percentage, genes)) {
			int i = r.nextInt(g.String().length());
			char c = g.String().charAt(i);
			if (c == '0')
				c = '1';
			else
				c = '0';
			g.setCharAt(c, i);
		}
		super.mutate(percentage, genes);
	}
}
