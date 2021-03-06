package historic;

import geneticBase.Genome;

import java.util.ArrayList;
import java.util.List;

public class Generation {

	int generation;
	private List<HistoricItem> generationMembers;
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
			Genome g = new Genome(i.String(), i.getParentString(0), i.getParentString(1),
					i.getId(), i.getParentId(0), i.getParentId(1));
			add(new HistoricItem(g, id));
		}
	}

	public void match(Generation parentGen)
	{
		for (HistoricItem child:generationMembers)
		{
			//get historic record for this childs first parent
			HistoricItem p1 = parentGen.find(child.getGene().getParentId(0));
			HistoricItem p2 = parentGen.find(child.getGene().getParentId(1));
			//this is not a parent child situation, the previous getGene() still exists
			if (p1 == null)
			{
				p1 = parentGen.find(child.getGene().getId());
			}
			if (p2 == null)
			{
				p2 = parentGen.find(child.getGene().getId());
			}
			child.addParents(p1, p2);
			p1.addChild(child);
			p2.addChild(child);
		}
	}

	//note due to returning first that matches may result in instances where it appears that the genes breed with themselves
	//this i not true, only that two separate but identical genes breed and the find function doesn't distinguish between them
	//as there is no benefit too doing this
	private HistoricItem find(int id)
	{
		for (HistoricItem i:generationMembers)
		{
			if (i.getGene().getId() == id)
			{
				return i;
			}
		}
		return null;
	}

	public List<HistoricItem> getMembers()
	{
		return new ArrayList<>(generationMembers);
	}
	public HistoricItem getMember(int i)
	{
		//member id is based from index
		if (i >= 0 && i < generationMembers.size()) {
			return generationMembers.get(i);
		}
		return null;
	}
}
