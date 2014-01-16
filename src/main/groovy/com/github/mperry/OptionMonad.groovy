package com.github.mperry

import fj.F
import fj.data.Option

/**
 * Created by MarkPerry on 30/12/13.
 */
class OptionMonad extends Monad<Option> {

    @Override
    def <A, B> Option flatMap(Option ma, F<A, Option> f) {
        ma.bind(f)
    }

    @Override
    def <B> Option<B> unit(B b) {
        Option.<B> some(b)
    }

}
