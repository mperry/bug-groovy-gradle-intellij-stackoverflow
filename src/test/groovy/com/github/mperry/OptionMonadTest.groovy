package com.github.mperry

import fj.F
import fj.F2
import fj.F3
import fj.P1
import fj.data.Option
import fj.test.Arbitrary
import groovy.transform.TypeChecked
import groovy.transform.TypeCheckingMode
import org.junit.Test

import static fj.Function.compose
import static fj.data.Option.*
import static fj.test.Arbitrary.arbF
import static fj.test.Arbitrary.arbF
import static fj.test.Arbitrary.arbF
import static fj.test.Arbitrary.arbInteger
import static fj.test.Arbitrary.arbList
import static fj.test.Arbitrary.arbLong
import static fj.test.Arbitrary.arbOption
import static fj.test.Arbitrary.arbString
import static fj.test.Coarbitrary.coarbInteger
import static fj.test.Coarbitrary.coarbLong
import static fj.test.Property.prop
import static fj.test.Property.property
import static fj.test.Property.property
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
