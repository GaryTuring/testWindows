import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import org.junit.Test;

public class TCPTest1 {
  //客户端
  @Test
  public void client()  {
    //创建一个Socket
    InetAddress byName = null;
    try {
      byName = InetAddress.getByName("10.44.77.16");
    } catch (UnknownHostException e) {
      e.printStackTrace();
    }
    int port = 8989;
    Socket socket = null;
    try {
      socket = new Socket(byName, port);
    } catch (IOException e) {
      e.printStackTrace();
    }
    //发送数据
    OutputStream outputStream = null;
    try {
      outputStream = socket.getOutputStream();
    } catch (IOException e) {
      e.printStackTrace();
    }
    try {
      outputStream.write("你好，这是来自客户端的第一条信息".getBytes(StandardCharsets.UTF_8));
    } catch (IOException e) {
      e.printStackTrace();
    }

    //关闭Socket;
    try {
      if (socket != null) {
        socket.close();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    try {
      if (outputStream != null) {
        outputStream.close();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  //服务端
  @Test
  public void server()  {
  //创建一个ServerSocket
    ServerSocket serverSocket = null;
    Socket socket = null;//阻塞式方法
    InputStream inputStream = null;
    try {
      int port = 8989;
      serverSocket = new ServerSocket(port);

      //调用accept()，接受用户Socket
      socket = serverSocket.accept();
      System.out.println("服务器已开启");

      //接收数据
      inputStream = socket.getInputStream();
      byte[] buffer = new byte[5];
      int len;
      //为了防止文字被切断出现乱码,内部维护一个byte数组，其实就是write到了内存
      ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
      while ((len = inputStream.read(buffer)) != -1){
//        String str = new String(buffer,0,len);
//        System.out.println(str);
        byteArrayOutputStream.write(buffer,0,len);//就收到的数据全部都拼接起来
      }
      System.out.println(byteArrayOutputStream.toString());
      System.out.println("\n数据接收完毕");
    } catch (IOException e) {
      e.printStackTrace();
    }

    //关闭Socket
    try {
      if (socket != null) {
        socket.close();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    try {
      if (serverSocket != null) {
        serverSocket.close();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    try {
      if (inputStream != null) {
        inputStream.close();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
