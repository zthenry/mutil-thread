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
 * ReaderThread 管理一个套接字连接
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
