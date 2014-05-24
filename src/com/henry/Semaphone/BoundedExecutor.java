/*
 * 文 件 名:  BoundedExecutor.java
 * 描    述:  <描述>
 * 修 改 人:  root
 * 修改时间:  2014-5-24
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.henry.Semaphone;

import java.util.concurrent.Executor;
import java.util.concurrent.Semaphore;

/**
 * 使用semaphore控制任务提交的速率
 * <功能详细描述>
 * 
 * @author  zhangtao 
 * @version  [版本号, 2014-5-24]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class BoundedExecutor
{
    private final Executor exec;
    
    private final Semaphore semaphore;
    
    public BoundedExecutor(Executor executor, int bound)
    {
        this.exec = executor;
        this.semaphore = new Semaphore(bound);
    }
    
    public void submitTask(final Runnable command)
        throws InterruptedException
    {
        semaphore.acquire();
        try
        {
            //exec.execute(command);
            exec.execute(new Runnable()
            {
                
                public void run()
                {
                    try
                    {
                        command.run();
                        
                    }
                    finally
                    {
                        semaphore.release();
                    }
                    
                }
            });
        }
        catch (Exception e)
        {
            semaphore.release();
        }
    }
}
