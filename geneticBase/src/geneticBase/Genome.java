package geneticBase;

public class Genome {
	
	String genString;
	int score;
	
	public Genome(String s)
	{
		genString = s;
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
	
	@Override
	public java.lang.String toString() {
		return genString;
	}

}
