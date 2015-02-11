import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;
import java.util.Comparator;
/**
 * This is the main controller for the Morra KOTH
 * 
 * @author PhiNotPi
 * @version 2/10/14
 */
public class Tournament
{
    static final int rounds = 1000; //number of throws in one battle
    static final int repeats = 1; //number of times a battle is played between each pair of contestants
    public static void main(String [] args)
    {
        Player[] players = new Player[] {  //There's no separate file for contestants
            new ExampleBot(), //name
            new NonJavaPlayer("perlTest", new String[]{"perl", "perlTest.plx"}) //name and command
        };
        final Map<Player,Integer>  score = new HashMap<Player,Integer>();
        for(Player p : players)
        {
            score.put(p, 0);
        }
        for(int i = 0; i < players.length - 1; i++)
        {
            Player p1 = players[i];
            for(int j = i + 1; j < players.length; j++)
            {
                Player p2 = players[j];
                for(int r = 0; r < repeats; r++)
                {
                    Game g = new Game(p1, p2, rounds);
                    int result = g.run();
                    if(result == 2)
                    {
                        score.put(p1, score.get(p1) + 2);
                        System.out.println(p1.getName() +" "+ p2.getName());
                    }
                    else if(result == 0)
                    {
                        score.put(p2, score.get(p2) + 2);
                        System.out.println(p2.getName() +" "+ p1.getName());
                    }
                    else
                    {
                        score.put(p1, score.get(p1) + 1);
                        score.put(p2, score.get(p2) + 1);
                        System.out.println(p2.getName() +" = "+ p1.getName());
                    }
                }
            }
        }
        Arrays.sort(players, new Comparator<Player>() {
            public int compare(Player a, Player b) {
                return score.get(b).compareTo(score.get(a));
            }
        });
        for(Player p : players)
        {
            System.out.printf("%5d - %-40s%n",score.get(p),p.getName());
        }
    }
}
