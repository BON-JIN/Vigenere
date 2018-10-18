package util;


import java.util.*;

public class IndexOfCoincidence
{
	private ArrayList<String> splitedCipher_texts;
	private ArrayList<ArrayList<Character>> alphas;
	private ArrayList<Character> alphas_whole;

	private ArrayList<IC> ICs;
	private double ICwhole;

	private String whole_text;

	private int period;

	public IndexOfCoincidence(String text, int p)
	{
		splitedCipher_texts = new ArrayList<String>();
		alphas = new ArrayList<ArrayList<Character>>();
		alphas_whole = new ArrayList<Character>();
		ICs = new ArrayList<IC>(); 
		whole_text = text;
		ICwhole = 0;
		period = p;
		
		for(int i = 0; i < period; i++)
		{
			String alphabet = text.substring(i, i + 1);
			splitedCipher_texts.add(alphabet);
		}


		int count = 0;

		for(int i = period; i < text.length(); i++)
		{
			if(count > period - 1)
				count = 0;

			String s = splitedCipher_texts.get(count);
			s = s + text.substring(i, i + 1);
			splitedCipher_texts.set(count , s);
			count++;
		}				
	}

	public void display()
	{
		System.out.println("\n\n" + "Splited alphabet is:");

		for(String s : splitedCipher_texts)
		{
			System.out.println(s);
		}
	}

	public void calculate()
	{	
		
		freq();
		freqWhole();
		calculateIC();
		calculateICwhole();

		for(int i = 0; i < alphas.size(); i++)
		{
			System.out.println("Alphabet " + (i + 1) + "\0");

			for (int k = 0; k < 26; k++)
			{
				Character c = alphas.get(i).get(k);
				c.display();
			}
		}
	}

	public void freq() 
	{
		for(int k = 0; k < period; k++)
		{
			String cipher_text = splitedCipher_texts.get(k);
			ArrayList<Character> characters_alpha = new ArrayList<Character>();
			
			for(int i = 0; i < 26; i++)
	        {
	            int count = 0;
	            char c = (char) (i + 65);

	            for(int j = 0; j < cipher_text.length(); j++)
	            {
	                char s = cipher_text.charAt(j);
	                if (c == s)
	                count++;
	            }

	            Character chr = new Character(c, count);
	            characters_alpha.add(chr);            
	        }

	        alphas.add(characters_alpha);

		}
	}

	public void freqWhole() 
	{		
		for(int i = 0; i < 26; i++)
	    {
	        int count = 0;
	        char c = (char) (i + 65);
 
            for(int j = 0; j < whole_text.length(); j++)
            {
                char s = whole_text.charAt(j);
                if (c == s)
                count++;
            }

            Character chr = new Character(c, count);
            alphas_whole.add(chr);            
        }
	}

	public void calculateIC()
	{
		for (int k = 0; k < alphas.size(); k++) 
		{
			String text = splitedCipher_texts.get(k);
			int n = text.length();
			double f = 0;

			for (int i = 0; i < 26; i++)
			{
				Character c = alphas.get(k).get(i);
				f = f + ( c.getFrequency()* ( c.getFrequency() - 1) );
			}

			double iC = f /( n *( n-1 ) );
			IC ic = new IC(iC);

			ICs.add(ic);

			System.out.println("Alphabet" + (k + 1) + " " + iC);
		}		
	}

	public void calculateICwhole()
	{
		int n = whole_text.length();
		double f = 0;

		for (int i = 0; i < 26; i++)
		{
			Character c = alphas_whole.get(i);
			f = f + ( c.getFrequency()* ( c.getFrequency() - 1) );
		}

		double iC = f /( n *( n-1 ) );
		ICwhole = iC;

		System.out.println("Whole IC :" + iC);
	}		
}