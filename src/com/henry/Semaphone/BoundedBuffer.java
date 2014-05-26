/*
 * 文 件 名:  BoundedBuffer.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  root
 * 修改时间:  2014-5-25
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.henry.Semaphone;

import java.util.concurrent.Semaphore;

/**
 * 使用semaphore实现有界队列和阻塞方法
 * <功能详细描述>
 * 
 * @author  root
 * @version  [版本号, 2014-5-25]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class BoundedBuffer<E>
{
    //表示从缓存中可以删除的元素个数
    private final Semaphore availableItems;
    
    //表示可以插入到缓存中的元素个数
    private final Semaphore availableSpaces;
    
    private final E[] items;
    
    private int putPosition = 0;
    
    private int takePosition = 0;
    
    @SuppressWarnings("unchecked")
    public BoundedBuffer(int capacity)
    {
        availableItems = new Semaphore(0);
        availableSpaces = new Semaphore(capacity);
        items = (E[])new Object[capacity];
    }
    
    public boolean isEmpty()
    {
        return availableItems.availablePermits() == 0;
    }
    
    public boolean isFull()
    {
        return availableSpaces.availablePermits() == 0;
    }
    
    public void put(E e)
        throws InterruptedException
    {
        availableSpaces.acquire();
        doInsert(e);
        availableItems.release();
    }
    
    private synchronized void doInsert(E e)
    {
        int i = putPosition;
        items[i] = e;
        putPosition = (++i == items.length) ? 0 : i;
    }
    
    public E take()
        throws InterruptedException
    {
        availableItems.acquire();
        E item = doExtract();
        availableSpaces.release();
        return item;
    }
    
    private synchronized E doExtract()
    {
        int i = takePosition;
        E e = items[i];
        items[i] = null;
        takePosition = (++i == items.length) ? 0 : i;
        return e;
    }
    
    public static void main(String[] args)
    {
        BoundedBuffer<Integer> bb = new BoundedBuffer<Integer>(10);
        System.out.println(bb.isEmpty());
        System.out.println(bb.isFull());
    }
}
