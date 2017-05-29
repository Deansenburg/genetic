package breed;

import geneticBase.Genome;

import java.util.ArrayList;


public interface IBreeder {
	public ArrayList<Genome> breed(int num, ArrayList<Genome> genes);
}
