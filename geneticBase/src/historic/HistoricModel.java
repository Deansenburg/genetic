package historic;

import java.util.ArrayList;
import java.util.List;

public class HistoricModel {
	
	private List<Generation> generations;
	
	public HistoricModel()
	{
		generations = new ArrayList<>();
	}
	
	public void add(Generation gen)
	{
		generations.add(gen);
	}

	public List<Generation> getGenerations()
	{
		List<Generation> gen =new ArrayList<>();
		gen.addAll(generations);
		return gen;
	}
	
}
