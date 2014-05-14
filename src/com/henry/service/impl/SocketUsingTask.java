/*
 * 文 件 名:  SocketUsingTask.java
 * 描    述:  <描述>
 * 修 改 人:  Administrator
 * 修改时间:  2014-5-13
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.henry.service.impl;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RunnableFuture;

import com.henry.service.CancellableTask;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2014-5-13]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public abstract class SocketUsingTask<T> implements CancellableTask<T>{

  private Socket socket;
  
  protected synchronized void setSoceket(Socket s){
    this.socket=s;
  }
  
  public synchronized void cancel(){
    
      try {
        if(socket!=null) {
          socket.close();
        }
      }
      catch(IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    
  }
  
  public RunnableFuture<T> newTask(){
    return new FutureTask<T>(this){
      @SuppressWarnings("finally")
      public boolean cancel(boolean mayInterruptIfRunning){
        try {
          SocketUsingTask.this.cancel();
        }
        catch(Exception e) {
          // TODO: handle exception
        }
        finally{
          return super.cancel(mayInterruptIfRunning);
        }
      }
    };
  }
}
