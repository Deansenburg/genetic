package geneticBase;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cull.ICuller;
import eval.IEvaluator;

import mutate.IMutator;

import breed.IBreeder;

public class GAController {

	IBreeder breeder;
	IMutator mutater;
	ICuller culler;
	IEvaluator eval;

	Random r = new Random();

	ArrayList<Genome> population = new ArrayList<Genome>();
	double breedRate = 10;
	double mutRate = 0.2;

	double poolPerc = 0.1;
	double poolMinSize = 2;
	
	int maxPop = 2;

	public GAController(IBreeder b, IMutator m, ICuller c, IEvaluator e) {
		breeder = b;
		mutater = m;
		culler = c;
		eval = e;
	}
	
	public void setMaxPop(int pop)
	{
		maxPop = pop;
	}
	public void setBreedRate(double br)
	{
		breedRate = br;
	}
	public void setMutationRate(double mr)
	{
		mutRate = mr;
	}
	public void setBreedingPoolSize(double size){ poolMinSize = size; }
	public void setBreedingPoolPercentage(double perc){ poolPerc = perc; }

	private List<Genome> getBreedingPool()
	{
		List<Genome> pool = new ArrayList<>();
		ArrayList<Genome> copy = new ArrayList<>(population);

		double size = Math.ceil(Math.max(maxPop * poolPerc, poolMinSize));
		for (int i=0;i<size;i++)
		{
			if (copy.size() == 0)
			{
				System.out.println("Invalid Breeding Pool Config");
				break;
			}
			int pos = r.nextInt(copy.size());
			pool.add(copy.get(pos));
			copy.remove(pos);
		}

		return pool;
	}

	public void runCycle() {
		// breed individuals
		int size = population.size();
		mutater.mutate(mutRate, population);
		population.addAll(breeder.breed((int) (size * breedRate),
				getBreedingPool()));
		eval.evaluate(population);
		culler.cull(population.size()-maxPop, population);
	}

	public Genome getBest()
	{
		return culler.bestScore(population);
	}
	
	public Genome runUntil(int score) {
		Genome g;
		do {
			runCycle();
			g = getBest();
			System.out.println(g.Score());
		} while (culler.isFinished(score, g.Score()));
		return g;
	}

	public void addGene(Genome g) {
		population.add(g);
	}

	public ArrayList<Genome> getPopulation()
	{
		return population;
	}
}
