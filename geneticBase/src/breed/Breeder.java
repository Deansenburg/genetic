package breed;

import geneticBase.Genome;

public class Breeder extends BaseBreeder {

	@Override
	protected Genome breed(String g1, String g2) {
		int upper = Math.min(g1.length(), g2.length());

		int split = r.nextInt(upper);

		// System.out.println(p1 +" "+p2+" : "+split);

		String child = g1.substring(0, split)
				+ g2.substring(split, g2.length());
		return new Genome(child);
	}
}
