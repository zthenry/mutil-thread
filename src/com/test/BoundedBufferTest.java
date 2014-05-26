/*
 * 文 件 名:  BoundedBufferTest.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  root
 * 修改时间:  2014-5-25
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.henry.Semaphone.BoundedBuffer;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  root
 * @version  [版本号, 2014-5-25]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class BoundedBufferTest
{
    
    @Test
    public void testIsEmpty(){
        BoundedBuffer<Integer> bbBoundedBuffer = new BoundedBuffer<Integer>(10);
        assertTrue(bbBoundedBuffer.isEmpty());
        assertFalse(bbBoundedBuffer.isFull());
    }
}
