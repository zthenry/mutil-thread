/*
 * 文 件 名:  TimingThreadPool.java
 * 描    述:  <描述>
 * 修 改 人:  root
 * 修改时间:  2014-5-24
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.henry.threadpool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

/**
 * 自定义的ThreadPool
 * <功能详细描述>
 * 
 * @author  zhangtao
 * @version  [版本号, 2014-5-24]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class TimingThreadPool extends ThreadPoolExecutor
{
    
    //任务开始执行时间
    private final ThreadLocal<Long> startTime = new ThreadLocal<Long>();
    
    private final Logger log = Logger.getLogger("TimingThreadPool");
    
    private final AtomicLong numTasks = new AtomicLong();
    
    private final AtomicLong totalTime = new AtomicLong();
    
    public TimingThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
        BlockingQueue<Runnable> workQueue)
    {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
        // TODO Auto-generated constructor stub
    }
    
    //任务执行前会调用
    protected void beforeExecute(Thread t, Runnable r)
    {
        super.beforeExecute(t, r);
        log.fine(String.format("Thread %s: start %s", t, r));
        startTime.set(System.nanoTime());
    }
    
    //任务执行后会调用
    protected void afterExecute(Runnable r, Throwable t)
    {
        
        try
        {
            long endtime = System.nanoTime();
            long tasktime = endtime - startTime.get();
            numTasks.incrementAndGet();
            totalTime.addAndGet(tasktime);
            log.fine(String.format("Thread %s: end %s, time=%dns", t, r, tasktime));
        }
        finally
        {
            super.afterExecute(r, t);
        }
    }
    
    protected void terminated()
    {
        try
        {
            log.info(String.format("Terminated: avg time=%dns", totalTime.get() / numTasks.get()));
        }
        catch (Exception e)
        {
            // TODO: handle exception
        }
    }
}
