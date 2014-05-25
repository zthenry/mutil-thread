/**
 * 搬箱子谜题抽象接口
 */
package com.henry.immutable;

import java.util.Set;
/**
 * 
 * 搬箱子谜题抽象类
 * <功能详细描述>
 * 
 * @author  root
 * @version  [版本号, 2014-5-24]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface Puzzle<P, M>
{
    P initialPosition();
    boolean isGoal(P position);
    Set<M> legalMoves(P position);
    P move(P position,M move);
}
