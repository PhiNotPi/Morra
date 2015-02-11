
/**
 * This controls a single battle between two bots.
 * 
 * @author PhiNotPi 
 * @version 2/10/15 8:00 PM
 */
public class Game
{
    int p1s = 0;
    int p2s = 0;
    final Player p1;
    final Player p2;
    final int rounds;
    String p1t = "";
    String p1g = "";
    String p2t = "";
    String p2g = "";

    /**
     * Constructor for Game objects.  I could have probably used a static method.
     */
    public Game(Player A, Player B, int rounds)
    {
        p1 = A;
        p2 = B;
        this.rounds = rounds;
    }

    public int run()
    {
        boolean p1forfeit = false;
        boolean p2forfeit = false;
        for(int i = 0; i < rounds; i++)
        {
            String[] p1in = new String[] {p1t,p1g,String.valueOf(p1s),p2t,p2g,String.valueOf(p2s)};
            String[] p2in = new String[] {p2t,p2g,String.valueOf(p2s),p1t,p1g,String.valueOf(p1s)};

            long time1 = System.nanoTime();
            int[] p1out = null;
            try { p1out = p1.getMove(p1in); }
            catch (Throwable th) { System.err.println(p1 + " forfeits: " + th); }

            long time2 = System.nanoTime();
            int[] p2out = null;
            try { p2out = p2.getMove(p2in); }
            catch (Throwable th) { System.err.println(p2 + " forfeits: " + th); };
            
            long time3 = System.nanoTime();

            if(p1out == null || p1out.length != 2 || p1out[0] > 5 || p1out[1] > 5 || p1out[0] < 0 || p1out[1] < 0 || time2 - time1 > 500000000)
            {
                p1forfeit = true;
            }
            if(p2out == null || p2out.length != 2 || p2out[0] > 5 || p2out[1] > 5 || p2out[0] < 0 || p2out[1] < 0 || time3 - time2 > 500000000)
            {
                p2forfeit = true;
            }
            if(p1forfeit || p2forfeit)
            {
                break;
            }

            p1t += String.valueOf(p1out[0]);
            p2t += String.valueOf(p2out[0]);
            p1g += String.valueOf(p1out[1]);
            p2g += String.valueOf(p2out[1]);
            int roundScore = p1out[0] + p2out[0];
            //for(String s : p1in)
            //{
                //System.out.print(s + ",");
            //}
            //System.out.println();
            if(p1out[1] == p2out[0] && p2out[1] != p1out[0])
            {
                p1s += roundScore;
            }
            if(p1out[1] != p2out[0] && p2out[1] == p1out[0])
            {
                p2s += roundScore;
            }
            
            //System.out.println(p1out[0] + " " + p1out[1] + " " + p2out[0] + " " + p2out[1]);
        }
        if(p1forfeit && p2forfeit) return 1;
        if(p1forfeit) return 0;
        if(p2forfeit) return 2;
        if(p1s > p2s) return 2;
        if(p1s < p2s) return 0;
        return 1;
    }
}
