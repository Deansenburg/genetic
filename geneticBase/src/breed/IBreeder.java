package breed;

import geneticBase.Genome;

import java.util.List;


public interface IBreeder {
	List<Genome> breed(int num, List<Genome> genes);
}
