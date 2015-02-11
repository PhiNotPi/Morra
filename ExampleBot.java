import java.util.Random;
/**
 * A simple example Morra bot to get you started.
 */
public class ExampleBot extends Player
{
    public ExampleBot()
    {
        name = "Unnamed";
    }
    public ExampleBot(String name)
    {
        this.name = name;
    }
    public int[] getMove(String [] args)
    {
        return move(args); //easier than reflection or whatever it's called :)
    }
    
    public static int[] move(String [] args) //all custom code goes in here
    {
        //easiest way I know to break down to create a move history (just contains their throw history)
        char[] theirThrowsC = args[3].toCharArray();
        int[] theirThrows = new int[theirThrowsC.length];
        for(int i = 0; i < theirThrowsC.length; i++)
        {
            theirThrows[i] = Integer.parseInt(Character.toString(theirThrowsC[i]));
        }
        
        //get my score
        int myScore = Integer.parseInt(args[2]);
        
        Random r = new Random();
        int guess = r.nextInt(6);
        if(theirThrows.length > 0)
        {
            guess = theirThrows[theirThrows.length-1];
        }
        
        
        return new int[] {r.nextInt(6),guess}; //throws a random number, guesses what they threw last
    }
}
