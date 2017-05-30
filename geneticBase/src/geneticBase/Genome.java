package geneticBase;

import java.util.ArrayList;
import java.util.List;

public class Genome {
	
	String genString;
	List<String> parents;
	int score;

	
	public Genome(String s, String p1, String p2)
	{
		genString = s;
		parents = new ArrayList<>();
		if (p1.equals(""))
		{
			System.out.println();
		}
		parents.add(p1);
		parents.add(p2);
	}
	
	public void setScore(int i)
	{
		score = i;
	}
	public int Score()
	{
		return score;
	}
	
	public String String()
	{
		return genString;
	}
	
	public void setCharAt(char c, int i)
	{
		genString = genString.substring(0, i)+c+genString.substring(i+1, genString.length());
	}
	public void removeAt(int i)
	{
		genString = genString.substring(0, i)+genString.substring(i+1);
	}
	public void add(char c)
	{
		genString += c;
	}
	public void setString(String s)
	{
		genString = s;
	}

	public String getParentString(int pos)
	{
		return parents.get(pos);
	}
	
	@Override
	public java.lang.String toString() {
		return genString;
	}

}
