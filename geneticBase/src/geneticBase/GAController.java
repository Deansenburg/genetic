package geneticBase;

import java.util.ArrayList;

import cull.ICuller;
import eval.IEvaluator;

import mutate.IMutator;

import breed.IBreeder;

public class GAController {

	IBreeder breeder;
	IMutator mutater;
	ICuller culler;
	IEvaluator eval;

	ArrayList<Genome> population = new ArrayList<Genome>();
	double breedRate = 10;
	double mutRate = 0.2;
	
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
	
	public void runCycle() {
		// breed individuals
		int size = population.size();
		mutater.mutate(mutRate, population);
		population.addAll(breeder.breed((int) (size * breedRate),
				population));
		eval.evaluate(population);
		culler.cull(population.size()-maxPop, population);

		 //for (Genome g : population)
		 //System.out.println(g.String());

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
