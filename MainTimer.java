import java.util.List;
public class MainTimer{

  public static void usage() {
    System.out.println("Usage: MainTimer historicFilename newFilename");
    System.exit(1);
  }

  public static void main(String[] args) {

    String historicFilename=null;
    String newFilename=null;

    try {
      historicFilename = args[0];
      newFilename = args[1];
    } catch (ArrayIndexOutOfBoundsException aioobe) {
      usage();
    }

    long start=System.currentTimeMillis();
    DEA dea = new DEA();
    dea.readHistoricData(historicFilename);
    long middle=System.currentTimeMillis();
    Set<String> suspicious=dea.flagSuspiciousBuys(newFilename);
    List<String> suspiciousList=suspicious.toList();
    long end=System.currentTimeMillis();

    for (String s : suspiciousList)
      System.out.println(s);

    double elapsed=(end-start)/1000F;
    System.out.println(elapsed+" seconds");
  }
}

