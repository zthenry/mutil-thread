/*
 * 文 件 名:  CancellableTask.java
 * 描    述:  <描述>
 * 修 改 人:  Administrator
 * 修改时间:  2014-5-13
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.henry.service;

import java.util.concurrent.Callable;
import java.util.concurrent.RunnableFuture;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2014-5-13]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface CancellableTask<T> extends Callable<T> {

  void cancel();
  RunnableFuture<T> newTask();
}
