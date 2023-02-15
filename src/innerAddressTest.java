import java.net.InetAddress;
import java.net.UnknownHostException;

public class innerAddressTest {

  public static void main(String[] args) {
    try {
      InetAddress byName = InetAddress.getLocalHost();
      System.out.println(byName.getHostName());
    } catch (UnknownHostException e) {
      e.printStackTrace();
    }
  }
}
