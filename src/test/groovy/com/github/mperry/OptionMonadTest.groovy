package com.github.mperry

import fj.data.Option
import groovy.transform.TypeChecked
import org.junit.Test

import static fj.data.Option.none
import static fj.data.Option.some
import static org.junit.Assert.assertTrue

/**
 * Created by MarkPerry on 30/12/13.
 */
@TypeChecked
class OptionMonadTest {

    OptionMonad monad() {
        new OptionMonad()
    }

    @Test
    void join() {
        def m = monad()
        assertTrue(m.join(some(some(3))) == some(3))
        assertTrue(m.join(none()) == none())
        assertTrue(m.join(some(none())) == none())

        def s = some(some(3))
        assertTrue(m.join(s) == Option.join(s))
    }

}
