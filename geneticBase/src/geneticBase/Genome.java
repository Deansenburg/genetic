package geneticBase;

import helper.StringToBinary;

import java.util.ArrayList;
import java.util.List;

public class Genome {
	
	String genString;
	List<String> parents;
	int score;
	int id, pid1, pid2;

	
	public Genome(String s, String p1, String p2, int id, int pid1, int pid2)
	{
		genString = s;
		parents = new ArrayList<>();
		parents.add(p1);
		parents.add(p2);
		this.id = id;
		this.pid1 = pid1;
		this.pid2 = pid2;
	}
	
	public void setScore(int i)
	{
		score = i;
	}
	public int Score()
	{
		return score;
	}
	public int getId()
	{
		return id;
	}
	public int getParentId(int i)
	{
		if(i == 0)
		{
			return pid1;
		}
		else
		{
			return pid2;
		}
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
		StringToBinary b = new StringToBinary();
		return b.getString(genString);
	}

}
