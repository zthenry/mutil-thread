/*
 * 文 件 名:  Node.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  root
 * 修改时间:  2014-5-24
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.henry.immutable;

import java.util.LinkedList;
import java.util.List;

/**
 * 代表通过一系列的移动到达的一个位置
 * <功能详细描述>
 * 
 * @author  root
 * @version  [版本号, 2014-5-24]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class Node<P, M>
{
    final P pos;
    
    //到达该位置的移动
    final M move;
    
    //前一个Node
    final Node<P, M> prev;
    public Node(P pos,M move,Node<P, M> prev)
    {
        this.pos=pos;
        this.move=move;
        this.prev=prev;
    }
    
    //遍历，获取到达该节点的全路径
    List<M> asMoveList(){
        List<M> solution = new LinkedList<M>();
        for (Node<P, M> n = this; n.move!=null; n=n.prev)
        {
            solution.add(0,n.move);
        }
        return solution;
    }
}
