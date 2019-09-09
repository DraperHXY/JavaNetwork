package client;

/**
 * @author draper_hxy
 */
public class Client {

    public static void main(String[] args) {
        NetworkInfo networkInfo = new NetworkInfo();
        networkInfo.setIp("127.0.0.1");
        networkInfo.setPort(54321);
        new Thread(new UserSocketRunnable("admin", networkInfo)).start();
        new Thread(new RandomUserSocketRunnable(networkInfo)).start();
    }

}
