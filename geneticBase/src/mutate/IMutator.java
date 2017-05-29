package mutate;

import geneticBase.Genome;

import java.util.ArrayList;


public interface IMutator {
	public void mutate(double percentage, ArrayList<Genome> genes);
}
