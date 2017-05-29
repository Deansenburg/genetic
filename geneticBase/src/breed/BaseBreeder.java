package breed;

import geneticBase.Genome;

import java.util.ArrayList;
import java.util.Random;

public abstract class BaseBreeder implements IBreeder {

	protected Random r = new Random();

	@Override
	public ArrayList<Genome> breed(int num, ArrayList<Genome> genes) {
		ArrayList<Genome> children = new ArrayList<Genome>();
		
		for (int i = 0; i < num; i++) {

			int num1 = r.nextInt(genes.size()), num2 = r.nextInt(genes.size()-1);
			if (num1 == num2)
				num2++;
			String p1 = genes.get(num1).String();
			String p2 = genes.get(num2).String();
			children.add(breed(p1, p2));
		}
		return children;
	}
	
	protected abstract Genome breed(String g1, String g2);
}
