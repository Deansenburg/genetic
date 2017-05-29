package eval;
import geneticBase.Genome;

import java.util.ArrayList;

public class SpecificStringEvaluator implements IEvaluator {

	private String target;

	public SpecificStringEvaluator(String t) {
		target = t;
	}

	@Override
	public void evaluate(ArrayList<Genome> genes) {
		for (Genome g : genes) {
			int score = 0;
			for (int i = 0; i < g.String().length(); i++) {
				if (i < target.length())
					if (g.String().charAt(i) == target.charAt(i))
						score++;

			}
			int dif = Math.abs(target.length()-g.String().length());
			score -= dif;
			g.setScore(score);
		}
	}

}
