import java.util.HashMap;
import java.util.Random;

public class TrieNode
{
	//private HashMap<Character,TrieNode> child;
	public HashMap<Character,TrieNode> link;
	private boolean isWord;
	
	public TrieNode()
	{
		link=new HashMap<>();
		isWord=false;
	}
	
	public void add(String s)
	{
		HashMap<Character,TrieNode> temp=link;
		
		for(int i=0;i<s.length();i++)
		{
			char c=s.charAt(i);
			
			TrieNode trieNode;
			
			if(temp.containsKey(c))
			{
				trieNode=temp.get(c);
			}
			else
			{
				trieNode=new TrieNode();
				temp.put(c,trieNode);
			}
			
			temp=trieNode.link;
			
			if(i==s.length()-1)
			{
				trieNode.isWord=true;
			}
		}
	}
	
	public boolean isWord(String s)
	{
		TrieNode trieNode=findNode(s);
		
		if(trieNode != null && trieNode.isWord)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public TrieNode findNode(String s)
	{
		HashMap<Character,TrieNode> temp=link;
		
		TrieNode trieNode=null;
		
		for(int i=0;i<s.length();i++)
		{
			char c=s.charAt(i);
			
			if(temp.containsKey(c))
			{
				trieNode=temp.get(c);
				temp=trieNode.link;
			}
			else
			{
					return null;
			}
		}
		return trieNode;
	}
	
	public String getChar(String s)
	{
		TrieNode trieNode=findNode(s);
		String result=s;
		HashMap<Character,TrieNode> temp;
		Random r=new Random();
		
		if(trieNode==null)
		{
			return null;
		}
		else
		{
			while(!trieNode.isWord)
			{
				temp=trieNode.link;
				int sz=temp.size();
				int ri=r.nextInt(sz);
				
				if(ri>0)
				{
					ri--;
				}
	//get random word
				Character next=(Character)temp.keySet().toArray()[ri];
				result=result+next;
				trieNode=temp.get(next);
			}
		}
		return result;
		//return "hardik";
	}
}

