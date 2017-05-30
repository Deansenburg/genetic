package historic;

import geneticBase.Genome;

import java.util.ArrayList;
import java.util.List;

public class Generation {

	int generation;
	List<HistoricItem> generationMembers;
	int id = 0;

	public Generation(int gen)
	{
		generation = gen;
		generationMembers = new ArrayList<>();
	}
	
	public void add(HistoricItem member)
	{
		generationMembers.add(member);
		id++;
	}
	public void add(List<Genome> members)
	{
		for(Genome i:members)
		{
			add(new HistoricItem(i, id));
		}
	}

	public void match(Generation parentGen)
	{
		for (HistoricItem child:generationMembers)
		{
			//get historic record for this childs first parent
			HistoricItem p1 = parentGen.find(child.gene.getParentString(0));
			HistoricItem p2 = parentGen.find(child.gene.getParentString(1));
			//this is not a parent child situation, the previous gene still exists
			if (p1 == null)
			{
				p1 = parentGen.find(child.gene.String());
			}
			if (p2 == null)
			{
				p2 = parentGen.find(child.gene.String());
			}
			child.addParents(p1, p2);
			p1.addChild(child);
			p2.addChild(child);
		}
	}

	private HistoricItem find(String geneString)
	{
		for (HistoricItem i:generationMembers)
		{
			if (geneString.equals(i.gene.String()))
			{
				return i;
			}
		}
		return null;
	}

}
