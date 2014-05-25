/*
 * 文 件 名:  SequentialPuzzleSolver.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  root
 * 修改时间:  2014-5-25
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.henry.immutable;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 串行的谜题解决方案
 * <功能详细描述>
 * 
 * @author  root
 * @version  [版本号, 2014-5-25]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class SequentialPuzzleSolver<P, M>
{
    private final Puzzle<P, M> puzzle;
    private final Set<P> seen = new HashSet<P>();
    
    public SequentialPuzzleSolver(Puzzle<P, M> p){
        this.puzzle=p;
    }
    
    public List<M> solve(){
        P pos = puzzle.initialPosition();
        return search(new Node<P, M>(pos, null, null));
    }
    
    public List<M> search(Node<P, M> node){
        //避免对同一个位置进行重复搜索
        if (!seen.contains(node.pos))
        {
            seen.add(node.pos);
            if(puzzle.isGoal(node.pos)){
                return node.asMoveList();
            }
            
            for (M move:puzzle.legalMoves(node.pos))
            {
                P pos = puzzle.move(node.pos, move);
                Node<P, M> child = new Node<P, M>(pos, move, node);
                List<M> result = search(child);
                if (result!=null)
                {
                    return result;
                }
            }
        }
        return null;
        
    }
}
