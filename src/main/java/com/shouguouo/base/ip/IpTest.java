package com.shouguouo.base.ip;

import java.util.Hashtable;

/**
 * 功能说明:
 *
 * @author shouguouo~
 */
public class IpTest {
  public static void main(String[] args) {
/*    try {
      InetAddress inet4Address = Inet4Address.getLocalHost();
      InetAddress inet6Address = Inet6Address.getLocalHost();
      InetAddress inetAddress = InetAddress.getLocalHost();
      System.out.println("ipv4: " + Arrays.toString(inet4Address.getAddress()) + "," + inet4Address.getHostAddress() + "," + inet4Address.getHostName());
      System.out.println("ipv6: " + Arrays.toString(inet6Address.getAddress())  + "," + inet6Address.getHostAddress() + "," + inet6Address.getHostName());
      System.out.println("default: " + Arrays.toString(inetAddress.getAddress())  + "," + inetAddress.getHostAddress() + "," + inetAddress.getHostName());
      System.out.println(Integer.valueOf('　'));

      Lock lock = new ReentrantLock();
      lock.lock();
      lock.tryLock();
      lock.unlock();
    } catch (UnknownHostException e) {
      e.printStackTrace();
    }*/
    Hashtable<String, Object> hashMap = new Hashtable<>(1);
    hashMap.put("hello", null);
    System.out.println(hashMap.get("hello"));
  }
}
