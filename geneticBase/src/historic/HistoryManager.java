package historic;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Dean on 30/05/17.
 */
public class HistoryManager {
    String superPath = "history";
    String path, suffix = ".gen";

    public HistoryManager(String p)
    {
        path = p;
    }

    public void recordItem(HistoricItem i, int generation)
    {
        String dirPath = superPath+"/"+path+"/"+generation;
        String filePath = dirPath+"/"+i.genId+suffix;
        File dir = new File(dirPath);
        if(!dir.isDirectory()) {
            dir.mkdirs();
        }

        try{
            PrintWriter writer = new PrintWriter(filePath, "UTF-8");
            writer.println("G:"+i.gene.String());
            if (i.parents.size() > 1) {
                HistoricItem p1 = i.parents.get(0), p2 = i.parents.get(1);
                writer.println("P:" + (generation - 1) + " " + p1.genId);
                writer.println("P:" + (generation - 1) + " " + p2.genId);
            }
            writer.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void recordGeneration(Generation g)
    {
        for (HistoricItem i:g.getMembers())
        {
            recordItem(i, g.generation);
        }
    }
}
