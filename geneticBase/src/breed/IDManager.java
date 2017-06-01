package breed;

/**
 * Created by Dean on 01/06/17.
 */
public class IDManager {
    private static int id = 0;
    public static int getId()
    {
        int i = id;
        id++;
        return i;
    }
}
