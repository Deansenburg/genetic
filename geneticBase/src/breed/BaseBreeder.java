package breed;

import geneticBase.Genome;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class BaseBreeder implements IBreeder {

	protected Random r = new Random();

	@Override
	public List<Genome> breed(int num, List<Genome> genes) {
		ArrayList<Genome> children = new ArrayList<Genome>();
		
		for (int i = 0; i < num; i++) {

			int num1 = r.nextInt(genes.size()), num2 = r.nextInt(genes.size()-1);
			if (num1 == num2)
				num2++;
			String p1 = genes.get(num1).String();
			String p2 = genes.get(num2).String();
			children.add(breed(p1, p2, genes.get(num1).getId(), genes.get(num2).getId()));
		}
		return children;
	}
	
	protected abstract Genome breed(String g1, String g2, int id1, int id2);
}
