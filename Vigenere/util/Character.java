package util;

//import .*;

public class Character
{
    private char c;
    private int frequency;
    private int original_position;

    public Character(char c, int f)
    {
        this.c = c;
        frequency = f;
        original_position = ((int) c) - 65;
    }

	public void display()
    {
    	System.out.println(c + ": " + frequency +  " times.");
    }

    public char get()
    {
    	return c;
    }

    public int getOriginal()
    {
        return original_position;
    }

    public int getFrequency()
    {
        return frequency;
    }


    public boolean isDuplicated(Character ch)
    {
    	return this.c == ch.get();
    }
} 