/*
 * 文 件 名:  CancellingExecutor.java
 * 描    述:  <描述>
 * 修 改 人:  Administrator
 * 修改时间:  2014-5-13
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.henry;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

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
public class CancellingExecutor extends ThreadPoolExecutor {

  public CancellingExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
      BlockingQueue<Runnable> workQueue) {
    super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
  }
  
  @SuppressWarnings("unchecked")
  protected<T> RunnableFuture<T> newTaskFor(Callable<T> callable){
    if(callable instanceof CancellableTask) {
      return ((CancellableTask) callable).newTask();
    }
    else {
      return super.newTaskFor(callable);
    }
  } 

}
