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
    void testUnit() {
        def m = monad()
        def o1 = m.unit(3)
        assertTrue(o1.some() == 3)
    }

    @Test
    void testFlatMap() {
        def m = monad()
        def o1 = m.unit(3)
        def f = { Integer i -> some(2 * i) } as F
        def o2 = m.flatMap(o1, f)
        assertTrue(o2.some() == 6)
        assertTrue(m.flatMap(o1, f) == o1.bind(f))
    }

    @Test
    void testMap() {
        def m = monad()
        def f = { Integer i -> (2 * i).toString() } as F
        def o1 = m.unit(3)
        def o2 = m.map(o1, f)
        assertTrue(o2.some() == 6.toString())
        assertTrue(m.map(o1, f) == o1.map(f))
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

    @Test
    void sequence() {
        def m = monad()
        assertTrue(m.sequence(Arrays.asList(some(3), none())) == none())
        assertTrue(m.sequence(Arrays.asList(some(3), some(4))) == some([3, 4]))
        assertTrue(m.sequence([some(3), some(4)]) == some([3, 4]))

        def list = [some(3), some(4)]
//        assertTrue(m.sequence(list) == Option.sequence(list.toFJList()).map { fj.data.List l -> l.toJavaList() } )
    }

}
