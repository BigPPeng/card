package com.card.test.dp.chain;

/**
 * Created by cuihp on 2020/7/21.
 */
public interface IChain {

    void handle(ChainContext chainContext);

    void setNext(IChain next);
}

