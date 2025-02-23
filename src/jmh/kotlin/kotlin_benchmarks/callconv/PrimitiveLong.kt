package kotlin_benchmarks.callconv

import org.openjdk.jmh.annotations.*
import java.util.concurrent.TimeUnit

@Fork(3)
@Warmup(iterations = 5, time = 1)
@Measurement(iterations = 5, time = 1)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@BenchmarkMode(Mode.AverageTime)
@State(Scope.Thread)
open class PrimitiveLong {

    companion object {
        const val SIZE = 96 // Must be divisible by 2 and 3 and around 100

        @JvmStatic
        private fun getImpl(i: Int, targets: Int): InvocationLogic = when (i % targets) {
            0 -> InvokeImpl1()
            1 -> InvokeImpl2()
            2 -> InvokeImpl3()
            else -> throw IllegalArgumentException("Invalid target index: ${i % targets}")
        }
    }

    abstract class InvocationLogic {
        abstract fun compute(v1: Long): Long
        abstract fun compute(v1: Long, v2: Long): Long
        abstract fun compute(v1: Long, v2: Long, v3: Long, v4: Long): Long
        abstract fun compute(
            v1: Long, v2: Long, v3: Long, v4: Long,
            v5: Long, v6: Long, v7: Long, v8: Long
        ): Long
    }

    class InvokeImpl1 : InvocationLogic() {
        override fun compute(v1: Long): Long = v1
        override fun compute(v1: Long, v2: Long): Long = v1
        override fun compute(v1: Long, v2: Long, v3: Long, v4: Long): Long = v1
        override fun compute(
            v1: Long, v2: Long, v3: Long, v4: Long,
            v5: Long, v6: Long, v7: Long, v8: Long
        ): Long = v1
    }

    class InvokeImpl2 : InvocationLogic() {
        override fun compute(v1: Long): Long = v1
        override fun compute(v1: Long, v2: Long): Long = v1
        override fun compute(v1: Long, v2: Long, v3: Long, v4: Long): Long = v1
        override fun compute(
            v1: Long, v2: Long, v3: Long, v4: Long,
            v5: Long, v6: Long, v7: Long, v8: Long
        ): Long = v1
    }

    class InvokeImpl3 : InvocationLogic() {
        override fun compute(v1: Long): Long = v1
        override fun compute(v1: Long, v2: Long): Long = v1
        override fun compute(v1: Long, v2: Long, v3: Long, v4: Long): Long = v1
        override fun compute(
            v1: Long, v2: Long, v3: Long, v4: Long,
            v5: Long, v6: Long, v7: Long, v8: Long
        ): Long = v1
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
            arr = Array(SIZE) { getImpl(it, targets) }
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

    private val a0 = 42L
    private val a1 = 43L
    private val a2 = 44L
    private val a3 = 45L
    private val a4 = 46L
    private val a5 = 47L
    private val a6 = 48L
    private val a7 = 49L

    @CompilerControl(CompilerControl.Mode.INLINE)
    fun args1(logic: Array<InvocationLogic>?): Long =
        logic!!.sumOf { it.compute(a0) }

    @CompilerControl(CompilerControl.Mode.INLINE)
    fun args2(logic: Array<InvocationLogic>?): Long =
        logic!!.sumOf { it.compute(a0, a1) }

    @CompilerControl(CompilerControl.Mode.INLINE)
    fun args4(logic: Array<InvocationLogic>?): Long =
        logic!!.sumOf { it.compute(a0, a1, a2, a3) }

    @CompilerControl(CompilerControl.Mode.INLINE)
    fun args8(logic: Array<InvocationLogic>?): Long =
        logic!!.sumOf { it.compute(a0, a1, a2, a3, a4, a5, a6, a7) }

    @Benchmark
    @OperationsPerInvocation(SIZE)
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    fun long_args1_targets0(st: StateTargets0): Long =
        st.arr!!.sumOf { it.compute(a0) }

    @Benchmark
    @OperationsPerInvocation(SIZE)
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    fun long_args2_targets0(st: StateTargets0): Long =
        st.arr!!.sumOf { it.compute(a0, a1) }

    @Benchmark
    @OperationsPerInvocation(SIZE)
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    fun long_args4_targets0(st: StateTargets0): Long =
        st.arr!!.sumOf { it.compute(a0, a1, a2, a3) }

    @Benchmark
    @OperationsPerInvocation(SIZE)
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    fun long_args8_targets0(st: StateTargets0): Long =
        st.arr!!.sumOf { it.compute(a0, a1, a2, a3, a4, a5, a6, a7) }

    @Benchmark
    @OperationsPerInvocation(SIZE)
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    fun long_args1_targets1(st: StateTargets1): Long = args1(st.arr)

    @Benchmark
    @OperationsPerInvocation(SIZE)
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    fun long_args2_targets1(st: StateTargets1): Long = args2(st.arr)

    @Benchmark
    @OperationsPerInvocation(SIZE)
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    fun long_args4_targets1(st: StateTargets1): Long = args4(st.arr)

    @Benchmark
    @OperationsPerInvocation(SIZE)
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    fun long_args8_targets1(st: StateTargets1): Long = args8(st.arr)

    @Benchmark
    @OperationsPerInvocation(SIZE)
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    fun long_args1_targets2(st: StateTargets2): Long = args1(st.arr)

    @Benchmark
    @OperationsPerInvocation(SIZE)
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    fun long_args2_targets2(st: StateTargets2): Long = args2(st.arr)

    @Benchmark
    @OperationsPerInvocation(SIZE)
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    fun long_args4_targets2(st: StateTargets2): Long = args4(st.arr)

    @Benchmark
    @OperationsPerInvocation(SIZE)
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    fun long_args8_targets2(st: StateTargets2): Long = args8(st.arr)

    @Benchmark
    @OperationsPerInvocation(SIZE)
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    fun long_args1_targets3(st: StateTargets3): Long = args1(st.arr)

    @Benchmark
    @OperationsPerInvocation(SIZE)
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    fun long_args2_targets3(st: StateTargets3): Long = args2(st.arr)

    @Benchmark
    @OperationsPerInvocation(SIZE)
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    fun long_args4_targets3(st: StateTargets3): Long = args4(st.arr)

    @Benchmark
    @OperationsPerInvocation(SIZE)
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    fun long_args8_targets3(st: StateTargets3): Long = args8(st.arr)
}
