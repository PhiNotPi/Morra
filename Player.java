import java.util.Random;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;
/**
 * This class is used to create players, either by using commands or by being extended by a submission's class
 * 
 * @author PhiNotPi
 * @version 2/10/15 8:00 PM
 */
public class Player
{
    String name;
    String command;
    public Player()
    {
        this.name = "Unnamed";
        this.command = "";
    }
    public Player(String name, String command)
    {
        this.name = name;
        this.command = command;
    }
    
    public int[] getMove(String [] args)
    {
        if(this.command != "")
        {
            Scanner response = new Scanner(getResponse(String.format(command,args[0],args[1],args[2],args[3],args[4],args[5]),500)); //yeah, it's stupid
            int[] pair = new int[2];
            pair[0] = response.nextInt();  //no error checking yet.  I'll throw out stupid bots, while the Game object checks for valid moves.
            pair[1] = response.nextInt();
            return pair;
        }
        return move(args); //easier than reflection or whatever it's called :)
    }
    
    public static int[] move(String [] args)
    {
        System.out.print("!");  //should never reach here
        return new int[] {0,0};
    }
    
    public String getResponse(String command, long timeout) { //all credit to Rainbolt
        String response = "";

        try {
            // Start the process
            Process proc = Runtime.getRuntime().exec(command);

            // Attach a reader to the process
            BufferedReader pin = new BufferedReader(new InputStreamReader(proc.getInputStream()));

            // Allow the process to time out
            proc.waitFor(timeout, TimeUnit.MILLISECONDS);
            if (proc.isAlive())
                proc.destroy();

            // Read the output of the process
            while (pin.ready())
                response += pin.readLine() + System.lineSeparator();

        } catch (IOException | InterruptedException e) {
            System.out.println("An error occurred while attempted to get a response.");
        }

        return response;
    }
}
