package com.card.test.dp.chain;

/**
 * Created by cuihp on 2020/7/21.
 */
public class ChainBuildFactory {

    public IChain add(IChain now,IChain last) {
        if (last == null) {
            return now;
        }
        last.setNext(now);
        return last;
    }

}
