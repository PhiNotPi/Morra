import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class NonJavaPlayer implements Player {
	final String name;
	final String[] command;

	public NonJavaPlayer(String name, String[] command)
	{
		if (name == null) throw new IllegalArgumentException("name must be non-null");
		if (command == null || command.length == 0) throw new IllegalArgumentException("command must be non-empty");

		this.name = name;
		this.command = command;
	}

	public String getName()
	{
		return name;
	}

	// Adapted from http://meta.codegolf.stackexchange.com/questions/4777/java-wrapper-for-koth
	public int[] getMove(String[] args)
	{
		String[] fullCmd = new String[command.length + args.length];
		System.arraycopy(command, 0, fullCmd, 0, command.length);
		System.arraycopy(args, 0, fullCmd, command.length, args.length);

        try {
            // Start the process
            Process proc = Runtime.getRuntime().exec(fullCmd);

            // Attach a reader to the process
            Readable pin = new InputStreamReader(proc.getInputStream());

            // Allow the process to time out
            proc.waitFor(500, TimeUnit.MILLISECONDS);
            if (proc.isAlive())
                proc.destroy();

            // Read the output of the process
    		Scanner response = new Scanner(pin);
    		int[] pair = new int[2];
    		pair[0] = response.nextInt();  //no error checking yet.  I'll throw out stupid bots, while the Game object checks for valid moves.
    		pair[1] = response.nextInt();
    		response.close();
    		return pair;
        } catch (IOException | InterruptedException e) {
            System.out.println("An error occurred while attempted to get a response.");
            return null;
        }
	}
}
