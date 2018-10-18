//package practice;
import util.*;
import java.util.*;

public class decryption
{
	public static void main (String [] arg)
	{
		util.ReadTextFile rf = new util.ReadTextFile("ciphertext.txt");
		String cipher_text = rf.readLine();

		util.ReadTextFile rf2 = new util.ReadTextFile("key.txt");
		String key = rf2.readLine();
        cipher_text = cipher_text.toUpperCase();

        cipher_text = cipher_text.replaceAll(" ", "");
        System.out.println(cipher_text.length());

		System.out.println("CipherText" + "\n" + cipher_text);

        ArrayList<String> characters = new ArrayList<String>();

        //Finding repetations.
        //I needed to hardcore the assumption of lengths
        //To find an assumption of lenghts, I needed to do the bruteforce search.
        int assumption_lengths = 3;
        for(int i = 0; i < cipher_text.length() - (assumption_lengths - 1); i++)
        {
            String s = cipher_text.substring(i, i + assumption_lengths);
            characters.add(s);
        }

        //Finding duplicates of repetation substrings
        ArrayList<String> assumption_repetationSubstring = new ArrayList<String>();

        for(int i = 0; i < characters.size(); i++)
        {
            String str = characters.get(i);
            int count = 0;

            for(int j = 0; j < cipher_text.length() - (assumption_lengths - 1); j++)
            {            
                    String str2 = cipher_text.substring(j, j + assumption_lengths);
                    if(str.equals(str2))
                    {
                        count = count + 1;
                    }
                    
                    if(str.equals(str2) && count > 1 )
                    {
                        assumption_repetationSubstring.add(str);
                    }
            }
        }

        //Clear duplicates
        Set<String> repetationWODuplicates = new LinkedHashSet<String>(assumption_repetationSubstring);
        assumption_repetationSubstring.clear();
        assumption_repetationSubstring.addAll(repetationWODuplicates);

        //Print out All repetation substring in the cipher text.
        System.out.println("\n");
        for(String s : assumption_repetationSubstring)
        {
            System.out.println("repetation of letters:" + s);
        }
        
        System.out.println("\n");
        
        ArrayList<Integer> lengths = new ArrayList<Integer>();

        for(String s : assumption_repetationSubstring)
        {
                int first_postion = 0;
                int second_position = 0;
                int repetation_lengths = 0;

            for(int i = 0; i < characters.size(); i++)
            {
                String compare = characters.get(i);
                if(s.equals(compare) && first_postion == 0)
                {
                    first_postion = i;
                    System.out.println("First position of " + s + " is : " + first_postion);
                }

                else if(s.equals(compare) && first_postion != i)
                {
                    second_position = i;
                    repetation_lengths = second_position - first_postion;
                    lengths.add(repetation_lengths);
                    System.out.println("Secound position of " + s + " is : " + second_position);
                    System.out.println("Lengths between repeattino is : " + repetation_lengths);
                    break;
                }
            }
            System.out.println("\n");
        }

        // Depends on IC, you may change GCD as key period
        int key_period = findGCD(lengths.get(0), lengths.get(1));

        System.out.println("GCD of " + lengths.get(0) + " and " + lengths.get(1) + " is : " + key_period);

        IndexOfCoincidence IC = new IndexOfCoincidence(cipher_text, key_period);

        IC.display();
        IC.calculate();

       
        String res = "";


        for (int i = 0, j = 0; i < cipher_text.length(); i++)
        {
            char c = cipher_text.charAt(i);
            if (c < 'A' || c > 'Z') // Skipping some character that is not withn A-Z
                continue;

            res += (char) ((c - key.charAt(j) + 26) % 26 + 'A');
            j = ++j % key.length();
        }

        System.out.println("\n" + "Result " + "\n" + res + "\n");
	}








    private static int findGCD(int number1, int number2) 
    {
        if(number2 == 0)
        {
           return number1; 
        }                

        return findGCD(number2, number1%number2);
    }
}