package com.github.mperry

import fj.F

/**
 * Created by MarkPerry on 30/12/13.
 */
abstract class Monad<M> {

    abstract <A, B> M<B> flatMap(M<A> ma, F<A, M<B>> f)

    abstract <B> M<B> unit(B b)

    def <B> F<B, M<B>> unit() {
        { B b ->
            unit(b)
        } as F
    }

    def <A> M<A> join(M<M<A>> mma) {
        flatMap(mma, {M<A> ma -> ma} as F)
    }

}
