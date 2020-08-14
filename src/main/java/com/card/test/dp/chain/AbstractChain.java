package com.card.test.dp.chain;

/**
 * Created by cuihp on 2020/7/21.
 */
public abstract class AbstractChain implements IChain {
    protected IChain next;

    public void setNext(IChain next) {
        this.next = next;
    }
}
