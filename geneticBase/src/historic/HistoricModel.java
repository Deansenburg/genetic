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
		return new ArrayList<>(generations);
	}

	public Generation getGeneration(int i)
	{
		for (Generation g:new ArrayList<>(generations))
		{
			if(g.generation == i)
			{
				return g;
			}
		}
		return null;
	}
	
}
