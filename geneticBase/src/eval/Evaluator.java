package eval;
import geneticBase.Genome;

import java.util.ArrayList;


public class Evaluator implements IEvaluator{

	public void evaluate(ArrayList<Genome> genes)
	{
		for (Genome g:genes)
		{
			int score = 0;
			for (char c:g.String().toCharArray())
			{
				if (c=='1')
					score++;
			}
			g.setScore(score);
		}
	}
}
