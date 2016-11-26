
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.FileReader;

public class Connect
{
	private TrieNode root;
	public long stime,etime;
	public Connect()throws IOException
	{
		BufferedReader in=new BufferedReader(new FileReader("words.txt"));
		
		root=new TrieNode();
		String line=null;
		stime=System.currentTimeMillis();
		while((line=in.readLine()) != null)
		{
			String word=line.trim();
			if(word.length() >= 4)
			{
				root.add(line.trim());
			}
		}
		etime=System.currentTimeMillis();
	
	}
	
	public boolean isWord(String word)
	{
		return root.isWord(word);
	}
	
	public String getChar(String prefix)
	{
		return root.getChar(prefix);
	}
}