/*
 * Copyright (c) 2020, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */
package kotlin_benchmarks.callconv

import kotlin_benchmarks.types.Q64byte
import org.openjdk.jmh.annotations.*
import java.util.concurrent.TimeUnit

@Fork(3)
@Warmup(iterations = 5, time = 1)
@Measurement(iterations = 5, time = 1)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@BenchmarkMode(Mode.AverageTime)
@State(Scope.Thread)
open class Inline64byte {

    companion object {
        const val SIZE = 96 // Must be divisible by 2 and 3 and around 100

        @JvmStatic
        private fun getImpl(i: Int, targets: Int): InvocationLogic? = when (i % targets) {
            0 -> InvokeImpl1()
            1 -> InvokeImpl2()
            2 -> InvokeImpl3()
            else -> null
        }
    }

    abstract class InvocationLogic {
        abstract fun compute(v1: Q64byte): Q64byte
        abstract fun compute(v1: Q64byte, v2: Q64byte): Q64byte
        abstract fun compute(v1: Q64byte, v2: Q64byte, v3: Q64byte, v4: Q64byte): Q64byte
        abstract fun compute(
            v1: Q64byte, v2: Q64byte, v3: Q64byte, v4: Q64byte,
            v5: Q64byte, v6: Q64byte, v7: Q64byte, v8: Q64byte
        ): Q64byte
    }

    class InvokeImpl1 : InvocationLogic() {
        override fun compute(v1: Q64byte) = v1
        override fun compute(v1: Q64byte, v2: Q64byte) = v1
        override fun compute(v1: Q64byte, v2: Q64byte, v3: Q64byte, v4: Q64byte) = v1
        override fun compute(
            v1: Q64byte, v2: Q64byte, v3: Q64byte, v4: Q64byte,
            v5: Q64byte, v6: Q64byte, v7: Q64byte, v8: Q64byte
        ) = v1
    }

    class InvokeImpl2 : InvocationLogic() {
        override fun compute(v1: Q64byte) = v1
        override fun compute(v1: Q64byte, v2: Q64byte) = v1
        override fun compute(v1: Q64byte, v2: Q64byte, v3: Q64byte, v4: Q64byte) = v1
        override fun compute(
            v1: Q64byte, v2: Q64byte, v3: Q64byte, v4: Q64byte,
            v5: Q64byte, v6: Q64byte, v7: Q64byte, v8: Q64byte
        ) = v1
    }

    class InvokeImpl3 : InvocationLogic() {
        override fun compute(v1: Q64byte) = v1
        override fun compute(v1: Q64byte, v2: Q64byte) = v1
        override fun compute(v1: Q64byte, v2: Q64byte, v3: Q64byte, v4: Q64byte) = v1
        override fun compute(
            v1: Q64byte, v2: Q64byte, v3: Q64byte, v4: Q64byte,
            v5: Q64byte, v6: Q64byte, v7: Q64byte, v8: Q64byte
        ) = v1
    }

    @State(Scope.Thread)
    open class StateTargets0 {
        @JvmField var arr: Array<InvokeImpl1>? = null

        @Setup
        fun setup() {
            arr = Array(SIZE) { InvokeImpl1() }
        }
    }

    @State(Scope.Thread)
    abstract class StateTargets {
        @JvmField var arr: Array<InvocationLogic>? = null

        fun init(targets: Int) {
            arr = Array(SIZE) { getImpl(it, targets)!! }
        }
    }

    open class StateTargets1 : StateTargets() {
        @Setup
        fun setup() {
            init(1)
        }
    }

    open class StateTargets2 : StateTargets() {
        @Setup
        fun setup() {
            init(2)
        }
    }

    open class StateTargets3 : StateTargets() {
        @Setup
        fun setup() {
            init(3)
        }
    }

    private val a0 = Q64byte(42)
    private val a1 = Q64byte(43)
    private val a2 = Q64byte(44)
    private val a3 = Q64byte(45)
    private val a4 = Q64byte(46)
    private val a5 = Q64byte(47)
    private val a6 = Q64byte(48)
    private val a7 = Q64byte(49)

    @CompilerControl(CompilerControl.Mode.INLINE)
    fun args1(logic: Array<InvocationLogic>?): Long {
        var r = 0L
        for (t in logic!!) {
            r += t.compute(a0).longValue()
        }
        return r
    }

    @CompilerControl(CompilerControl.Mode.INLINE)
    fun args2(logic: Array<InvocationLogic>?): Long {
        var r = 0L
        for (t in logic!!) {
            r += t.compute(a0, a1).longValue()
        }
        return r
    }

    @CompilerControl(CompilerControl.Mode.INLINE)
    fun args4(logic: Array<InvocationLogic>?): Long {
        var r = 0L
        for (t in logic!!) {
            r += t.compute(a0, a1, a2, a3).longValue()
        }
        return r
    }

    @CompilerControl(CompilerControl.Mode.INLINE)
    fun args8(logic: Array<InvocationLogic>?): Long {
        var r = 0L
        for (t in logic!!) {
            r += t.compute(a0, a1, a2, a3, a4, a5, a6, a7).longValue()
        }
        return r
    }

    @Benchmark
    @OperationsPerInvocation(SIZE)
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    fun Q64byte_args1_targets0(st: StateTargets0): Long {
        var r = 0L
        for (t in st.arr!!) {
            r += t.compute(a0).longValue()
        }
        return r
    }

    @Benchmark
    @OperationsPerInvocation(SIZE)
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    fun Q64byte_args2_targets0(st: StateTargets0): Long {
        var r = 0L
        for (t in st.arr!!) {
            r += t.compute(a0, a1).longValue()
        }
        return r
    }

    @Benchmark
    @OperationsPerInvocation(SIZE)
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    fun Q64byte_args4_targets0(st: StateTargets0): Long {
        var r = 0L
        for (t in st.arr!!) {
            r += t.compute(a0, a1, a2, a3).longValue()
        }
        return r
    }

    @Benchmark
    @OperationsPerInvocation(SIZE)
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    fun Q64byte_args8_targets0(st: StateTargets0): Long {
        var r = 0L
        for (t in st.arr!!) {
            r += t.compute(a0, a1, a2, a3, a4, a5, a6, a7).longValue()
        }
        return r
    }

    @Benchmark
    @OperationsPerInvocation(SIZE)
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    fun Q64byte_args1_targets1(st: StateTargets1): Long {
        return args1(st.arr)
    }

    @Benchmark
    @OperationsPerInvocation(SIZE)
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    fun Q64byte_args2_targets1(st: StateTargets1): Long {
        return args2(st.arr)
    }

    @Benchmark
    @OperationsPerInvocation(SIZE)
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    fun Q64byte_args4_targets1(st: StateTargets1): Long {
        return args4(st.arr)
    }

    @Benchmark
    @OperationsPerInvocation(SIZE)
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    fun Q64byte_args8_targets1(st: StateTargets1): Long {
        return args8(st.arr)
    }

    @Benchmark
    @OperationsPerInvocation(SIZE)
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    fun Q64byte_args1_targets2(st: StateTargets2): Long {
        return args1(st.arr)
    }

    @Benchmark
    @OperationsPerInvocation(SIZE)
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    fun Q64byte_args2_targets2(st: StateTargets2): Long {
        return args2(st.arr)
    }

    @Benchmark
    @OperationsPerInvocation(SIZE)
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    fun Q64byte_args4_targets2(st: StateTargets2): Long {
        return args4(st.arr)
    }

    @Benchmark
    @OperationsPerInvocation(SIZE)
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    fun Q64byte_args8_targets2(st: StateTargets2): Long {
        return args8(st.arr)
    }

    @Benchmark
    @OperationsPerInvocation(SIZE)
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    fun Q64byte_args1_targets3(st: StateTargets3): Long {
        return args1(st.arr)
    }

    @Benchmark
    @OperationsPerInvocation(SIZE)
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    fun Q64byte_args2_targets3(st: StateTargets3): Long {
        return args2(st.arr)
    }

    @Benchmark
    @OperationsPerInvocation(SIZE)
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    fun Q64byte_args4_targets3(st: StateTargets3): Long {
        return args4(st.arr)
    }

    @Benchmark
    @OperationsPerInvocation(SIZE)
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    fun Q64byte_args8_targets3(st: StateTargets3): Long {
        return args8(st.arr)
    }
}
