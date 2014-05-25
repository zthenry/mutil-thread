/*
 * 文 件 名:  ValueLatch.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  root
 * 修改时间:  2014-5-25
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.henry.immutable;

import java.util.concurrent.CountDownLatch;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  root
 * @version  [版本号, 2014-5-25]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class ValueLatch<T>
{
    private T value = null;
    
    private final CountDownLatch done = new CountDownLatch(1);
    
    public boolean isSet()
    {
        return (done.getCount() == 0);
    }
    
    public synchronized void setValue(T newValue)
    {
        if (!isSet())
        {
            value = newValue;
            //实现计数功能，完成一个线程任务，数据减1
            done.countDown();
        }
    }
    
    public T getValue()
        throws InterruptedException
    {
        //等待数据归0
        done.await();
        synchronized (this)
        {
            return value;
        }
    }
}
