/*
 * 文 件 名:  ConcurrentPuzzleSolver.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  root
 * 修改时间:  2014-5-25
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.henry.immutable;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;

/**
 * 并发谜题解答器              
 * <功能详细描述>
 * 
 * @author  root
 * @version  [版本号, 2014-5-25]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class ConcurrentPuzzleSolver<P, M>
{
    private final Puzzle<P, M> puzzle;
    
    private final ExecutorService exec;
    
    private final ConcurrentMap<P, Boolean> seen = new ConcurrentHashMap<P, Boolean>();
    
    final ValueLatch<Node<P, M>> solution = new ValueLatch<Node<P, M>>();
    
    public ConcurrentPuzzleSolver(Puzzle<P, M> puzzle, ExecutorService exec){
        this.puzzle=puzzle;
        this.exec=exec;
    }
    public List<M> solve()
        throws InterruptedException
    {
        try
        {
            P pos = puzzle.initialPosition();
            exec.execute(newTask(pos, null, null));
            Node<P, M> solnNode = solution.getValue();
            return (solnNode == null) ? null : solnNode.asMoveList();
        }
        finally
        {
            exec.shutdown();
        }
    }
    
    protected Runnable newTask(P pos, M move, Node<P, M> n)
    {
        return new SolverTask(pos, move, n);
    }
    
    class SolverTask extends Node<P, M> implements Runnable
    {
        
        public SolverTask(P pos, M move, Node<P, M> prev)
        {
            super(pos, move, prev);
        }
        
        public void run()
        {
            if (solution.isSet() || seen.putIfAbsent(pos, true)!=null)
            {
                return;
            }
            
            if (puzzle.isGoal(pos))
            {
                solution.setValue(this);
            }
            else {
                for (M m: puzzle.legalMoves(pos))
                {
                    exec.execute(newTask(puzzle.move(pos, m), m, this));
                }
            }
            
        }
        
    }
}
