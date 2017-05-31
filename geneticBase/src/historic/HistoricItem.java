package historic;

import geneticBase.Genome;

import java.util.ArrayList;
import java.util.List;

public class HistoricItem {
	
	//should only be 2
	List<HistoricItem> parents;
	//can be many
	List<HistoricItem> children;
	
	Genome gene;
	int genId;

	public HistoricItem(Genome g, int gId)
	{
		gene = g;
		genId = gId;
		parents = new ArrayList<>();
		children = new ArrayList<>();
	}
	
	public void addParents(HistoricItem p1, HistoricItem p2)
	{
		parents.clear();
		parents.add(p1);
		parents.add(p2);
	}
	
	public void addChild(HistoricItem child)
	{
		children.add(child);
	}

	@Override
	public String toString() {
		return gene.String();
	}
}
