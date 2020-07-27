import java.util.Iterator;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.io.*;

/**
  * ArcosReader is designed to ease the reading of the .tsv files from the
  * ARCOS Washington Post dataset.  Rather than forcing you to go line by line,
  * it converts each line into a Map, where the key is the column heading, and
  * the value is the contents of that column on that line.
  *
  * ArcosReader is Iterable so that it will provide you these Maps one by one.
  */
public class ArcosReader implements Iterable<Map<String, String>>{
  private String filename;

  /** Builds the reader to read from the given file
    * @param filename : the file to read from - needs to be an ARCOS .tsv file,
    * or you'll get errors.
    */
  public ArcosReader(String filename) {
    this.filename = filename;
  }

  /** Returns an Iterator, which will give you one Map at a time, where the key
    * of the Map is a column heading, and the value is the contents of the cell
    * at that column, in that line.
    *
    * @throws IllegalArgumentException if the filename the object was build with
    * does not exist.  God only knows what it throws if it's not an ARCOS file.
    */
  public Iterator<Map<String,String>> iterator() {
    try {
      return new ArcosIterator();
    } catch (FileNotFoundException fnfe) {
      throw new IllegalArgumentException(filename+" does not exist.");
    }
  }

  private class ArcosIterator implements Iterator<Map<String, String>>{
    private Scanner filescan;
    private List<String> headings;

    public ArcosIterator() throws FileNotFoundException {
      filescan = new Scanner(new File(filename));
      headings=new ArrayList<String>();
      for (String heading : filescan.nextLine().split("\t"))
        headings.add(heading);
    }

    public boolean hasNext(){return filescan.hasNext();}

    public Map<String,String> next() {
    	
      Map<String,String> map = new AVLMap<String, String>();
      String[] line = filescan.nextLine().split("\t");
      for (int i = 0; i < headings.size(); i++)
        map.set(headings.get(i),line[i]);
      return map;
    }

    public void remove() {throw new UnsupportedOperationException();}
  }
}

