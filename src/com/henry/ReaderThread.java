/*
 * 文 件 名:  ReaderThread.java
 * 描    述:  <描述>
 * 修 改 人:  Administrator
 * 修改时间:  2014-5-13
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.henry;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * ReaderThread 管理一个套接字连接，它采用同步的方式从该套接字读取数据
 * 并将接收到的数据进行处理。为了结束某个用户的连接或者关闭服务器，ReaderThread
 * 改写了interrupt方法，使其既能处理标准的中断，也能关闭底层的套接字。
 * 因此无论ReaderThread线程是在read方法中阻塞还是在某个可中断的阻塞方法中阻塞，
 * 都可以被中断并停止当前的工作
 * 
 * @author Administrator
 * @version [版本号, 2014-5-13]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class ReaderThread extends Thread {

  private final Socket socket;

  private final InputStream in;
  
  public ReaderThread(Socket socket) throws IOException {
    this.socket = socket;
    this.in = socket.getInputStream();
  }

  public void interrupt() {
    try {
      socket.close();
    }
    catch(IOException e) {
      e.printStackTrace();
    }
    finally {
      super.interrupt();
    }
  }

  public void run() {
    try {
      byte[] buf = new byte[1024];
      while(true) {
        int count;
        count = in.read(buf);
        if(count < 0) {
          break;
        }
        else if(count > 0) {

        }
      }
    }
    catch(IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}
