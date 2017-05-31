package historic;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

import static java.nio.file.FileVisitResult.CONTINUE;
import static java.nio.file.FileVisitResult.TERMINATE;

/**
 * Created by Dean on 30/05/17.
 */
public class HistoryManager {
    String superPath = "history";
    String path, suffix = ".gen";

    public HistoryManager(String p)
    {
        path = p;
        String dirPath = superPath+"/"+path;
        File dir = new File(dirPath);
        if (dir.isDirectory())
        {
            try {
                System.out.println("Directory already exists - deleting");
                deleteFileOrFolder(Paths.get(dirPath));
                System.out.println("Deleting Finished");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void deleteFileOrFolder(final Path path) throws IOException {
        Files.walkFileTree(path, new SimpleFileVisitor<Path>(){
            @Override public FileVisitResult visitFile(final Path file, final BasicFileAttributes attrs)
                    throws IOException {
                Files.delete(file);
                return CONTINUE;
            }

            @Override public FileVisitResult visitFileFailed(final Path file, final IOException e) {
                return handleException(e);
            }

            private FileVisitResult handleException(final IOException e) {
                e.printStackTrace(); // replace with more robust error handling
                return TERMINATE;
            }

            @Override public FileVisitResult postVisitDirectory(final Path dir, final IOException e)
                    throws IOException {
                if(e!=null)return handleException(e);
                Files.delete(dir);
                return CONTINUE;
            }
        });
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
