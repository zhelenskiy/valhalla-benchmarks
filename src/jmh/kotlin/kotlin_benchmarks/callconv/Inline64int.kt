package kotlin_benchmarks.callconv

import kotlin_benchmarks.types.Q64int
import org.openjdk.jmh.annotations.Benchmark
import org.openjdk.jmh.annotations.BenchmarkMode
import org.openjdk.jmh.annotations.CompilerControl
import org.openjdk.jmh.annotations.Fork
import org.openjdk.jmh.annotations.Measurement
import org.openjdk.jmh.annotations.Mode
import org.openjdk.jmh.annotations.OperationsPerInvocation
import org.openjdk.jmh.annotations.OutputTimeUnit
import org.openjdk.jmh.annotations.Scope
import org.openjdk.jmh.annotations.Setup
import org.openjdk.jmh.annotations.State
import org.openjdk.jmh.annotations.Warmup
import java.util.concurrent.TimeUnit

/**
 * Converted to Kotlin and package renamed to "kotlin_benchmarks.callconv".
 */
@Fork(3)
@Warmup(iterations = 5, time = 1)
@Measurement(iterations = 5, time = 1)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@BenchmarkMode(Mode.AverageTime)
@State(Scope.Thread)
open class Inline64int {

    companion object {
        const val SIZE = 96 // must be divisible by 2 and 3 and around 100

        @JvmStatic
        private fun getImpl(i: Int, targets: Int): InvocationLogic? {
            return when (i % targets) {
                0 -> InvokeImpl1()
                1 -> InvokeImpl2()
                2 -> InvokeImpl3()
                else -> null
            }
        }
    }

    abstract class InvocationLogic {
        abstract fun compute(v1: Q64int): Q64int
        abstract fun compute(v1: Q64int, v2: Q64int): Q64int
        abstract fun compute(v1: Q64int, v2: Q64int, v3: Q64int, v4: Q64int): Q64int
        abstract fun compute(
            v1: Q64int,
            v2: Q64int,
            v3: Q64int,
            v4: Q64int,
            v5: Q64int,
            v6: Q64int,
            v7: Q64int,
            v8: Q64int
        ): Q64int
    }

    class InvokeImpl1 : InvocationLogic() {
        override fun compute(v1: Q64int): Q64int = v1
        override fun compute(v1: Q64int, v2: Q64int): Q64int = v1
        override fun compute(v1: Q64int, v2: Q64int, v3: Q64int, v4: Q64int): Q64int = v1
        override fun compute(
            v1: Q64int,
            v2: Q64int,
            v3: Q64int,
            v4: Q64int,
            v5: Q64int,
            v6: Q64int,
            v7: Q64int,
            v8: Q64int
        ): Q64int = v1
    }

    class InvokeImpl2 : InvocationLogic() {
        override fun compute(v1: Q64int): Q64int = v1
        override fun compute(v1: Q64int, v2: Q64int): Q64int = v1
        override fun compute(v1: Q64int, v2: Q64int, v3: Q64int, v4: Q64int): Q64int = v1
        override fun compute(
            v1: Q64int,
            v2: Q64int,
            v3: Q64int,
            v4: Q64int,
            v5: Q64int,
            v6: Q64int,
            v7: Q64int,
            v8: Q64int
        ): Q64int = v1
    }

    class InvokeImpl3 : InvocationLogic() {
        override fun compute(v1: Q64int): Q64int = v1
        override fun compute(v1: Q64int, v2: Q64int): Q64int = v1
        override fun compute(v1: Q64int, v2: Q64int, v3: Q64int, v4: Q64int): Q64int = v1
        override fun compute(
            v1: Q64int,
            v2: Q64int,
            v3: Q64int,
            v4: Q64int,
            v5: Q64int,
            v6: Q64int,
            v7: Q64int,
            v8: Q64int
        ): Q64int = v1
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

    private val a0 = Q64int(42)
    private val a1 = Q64int(43)
    private val a2 = Q64int(44)
    private val a3 = Q64int(45)
    private val a4 = Q64int(46)
    private val a5 = Q64int(47)
    private val a6 = Q64int(48)
    private val a7 = Q64int(49)

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
    fun Q64int_args1_targets0(st: StateTargets0): Long {
        var r = 0L
        for (t in st.arr!!) {
            r += t.compute(a0).longValue()
        }
        return r
    }

    @Benchmark
    @OperationsPerInvocation(SIZE)
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    fun Q64int_args2_targets0(st: StateTargets0): Long {
        var r = 0L
        for (t in st.arr!!) {
            r += t.compute(a0, a1).longValue()
        }
        return r
    }

    @Benchmark
    @OperationsPerInvocation(SIZE)
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    fun Q64int_args4_targets0(st: StateTargets0): Long {
        var r = 0L
        for (t in st.arr!!) {
            r += t.compute(a0, a1, a2, a3).longValue()
        }
        return r
    }

    @Benchmark
    @OperationsPerInvocation(SIZE)
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    fun Q64int_args8_targets0(st: StateTargets0): Long {
        var r = 0L
        for (t in st.arr!!) {
            r += t.compute(a0, a1, a2, a3, a4, a5, a6, a7).longValue()
        }
        return r
    }

    @Benchmark
    @OperationsPerInvocation(SIZE)
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    fun Q64int_args1_targets1(st: StateTargets1): Long {
        return args1(st.arr)
    }

    @Benchmark
    @OperationsPerInvocation(SIZE)
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    fun Q64int_args2_targets1(st: StateTargets1): Long {
        return args2(st.arr)
    }

    @Benchmark
    @OperationsPerInvocation(SIZE)
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    fun Q64int_args4_targets1(st: StateTargets1): Long {
        return args4(st.arr)
    }

    @Benchmark
    @OperationsPerInvocation(SIZE)
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    fun Q64int_args8_targets1(st: StateTargets1): Long {
        return args8(st.arr)
    }

    @Benchmark
    @OperationsPerInvocation(SIZE)
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    fun Q64int_args1_targets2(st: StateTargets2): Long {
        return args1(st.arr)
    }

    @Benchmark
    @OperationsPerInvocation(SIZE)
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    fun Q64int_args2_targets2(st: StateTargets2): Long {
        return args2(st.arr)
    }

    @Benchmark
    @OperationsPerInvocation(SIZE)
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    fun Q64int_args4_targets2(st: StateTargets2): Long {
        return args4(st.arr)
    }

    @Benchmark
    @OperationsPerInvocation(SIZE)
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    fun Q64int_args8_targets2(st: StateTargets2): Long {
        return args8(st.arr)
    }

    @Benchmark
    @OperationsPerInvocation(SIZE)
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    fun Q64int_args1_targets3(st: StateTargets3): Long {
        return args1(st.arr)
    }

    @Benchmark
    @OperationsPerInvocation(SIZE)
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    fun Q64int_args2_targets3(st: StateTargets3): Long {
        return args2(st.arr)
    }

    @Benchmark
    @OperationsPerInvocation(SIZE)
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    fun Q64int_args4_targets3(st: StateTargets3): Long {
        return args4(st.arr)
    }

    @Benchmark
    @OperationsPerInvocation(SIZE)
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    fun Q64int_args8_targets3(st: StateTargets3): Long {
        return args8(st.arr)
    }
}
