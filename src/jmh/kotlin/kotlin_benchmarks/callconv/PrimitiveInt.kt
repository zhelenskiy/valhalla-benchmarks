package kotlin_benchmarks.callconv

import org.openjdk.jmh.annotations.*
import java.util.concurrent.TimeUnit

@Fork(3)
@Warmup(iterations = 5, time = 1)
@Measurement(iterations = 5, time = 1)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@BenchmarkMode(Mode.AverageTime)
@State(Scope.Thread)
open class PrimitiveInt {

    companion object {
        const val SIZE = 96 // Must be divisible by 2 and 3 and around 100

        @JvmStatic
        private fun getImpl(i: Int, targets: Int): InvocationLogic = when (i % targets) {
            0 -> InvokeImpl1()
            1 -> InvokeImpl2()
            2 -> InvokeImpl3()
            else -> throw IllegalArgumentException("Invalid target index")
        }
    }

    abstract class InvocationLogic {
        abstract fun compute(v1: Int): Int
        abstract fun compute(v1: Int, v2: Int): Int
        abstract fun compute(v1: Int, v2: Int, v3: Int, v4: Int): Int
        abstract fun compute(
            v1: Int, v2: Int, v3: Int, v4: Int,
            v5: Int, v6: Int, v7: Int, v8: Int
        ): Int
    }

    class InvokeImpl1 : InvocationLogic() {
        override fun compute(v1: Int): Int = v1
        override fun compute(v1: Int, v2: Int): Int = v1
        override fun compute(v1: Int, v2: Int, v3: Int, v4: Int): Int = v1
        override fun compute(
            v1: Int, v2: Int, v3: Int, v4: Int,
            v5: Int, v6: Int, v7: Int, v8: Int
        ): Int = v1
    }

    class InvokeImpl2 : InvocationLogic() {
        override fun compute(v1: Int): Int = v1
        override fun compute(v1: Int, v2: Int): Int = v1
        override fun compute(v1: Int, v2: Int, v3: Int, v4: Int): Int = v1
        override fun compute(
            v1: Int, v2: Int, v3: Int, v4: Int,
            v5: Int, v6: Int, v7: Int, v8: Int
        ): Int = v1
    }

    class InvokeImpl3 : InvocationLogic() {
        override fun compute(v1: Int): Int = v1
        override fun compute(v1: Int, v2: Int): Int = v1
        override fun compute(v1: Int, v2: Int, v3: Int, v4: Int): Int = v1
        override fun compute(
            v1: Int, v2: Int, v3: Int, v4: Int,
            v5: Int, v6: Int, v7: Int, v8: Int
        ): Int = v1
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

    private val a0 = 42
    private val a1 = 43
    private val a2 = 44
    private val a3 = 45
    private val a4 = 46
    private val a5 = 47
    private val a6 = 48
    private val a7 = 49

    @CompilerControl(CompilerControl.Mode.INLINE)
    fun args1(logic: Array<InvocationLogic>?): Int =
        logic!!.sumOf { it.compute(a0) }

    @CompilerControl(CompilerControl.Mode.INLINE)
    fun args2(logic: Array<InvocationLogic>?): Int =
        logic!!.sumOf { it.compute(a0, a1) }

    @CompilerControl(CompilerControl.Mode.INLINE)
    fun args4(logic: Array<InvocationLogic>?): Int =
        logic!!.sumOf { it.compute(a0, a1, a2, a3) }

    @CompilerControl(CompilerControl.Mode.INLINE)
    fun args8(logic: Array<InvocationLogic>?): Int =
        logic!!.sumOf { it.compute(a0, a1, a2, a3, a4, a5, a6, a7) }

    @Benchmark
    @OperationsPerInvocation(SIZE)
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    fun int_args1_targets0(st: StateTargets0): Int =
        st.arr!!.sumOf { it.compute(a0) }

    @Benchmark
    @OperationsPerInvocation(SIZE)
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    fun int_args2_targets0(st: StateTargets0): Int =
        st.arr!!.sumOf { it.compute(a0, a1) }

    @Benchmark
    @OperationsPerInvocation(SIZE)
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    fun int_args4_targets0(st: StateTargets0): Int =
        st.arr!!.sumOf { it.compute(a0, a1, a2, a3) }

    @Benchmark
    @OperationsPerInvocation(SIZE)
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    fun int_args8_targets0(st: StateTargets0): Int =
        st.arr!!.sumOf { it.compute(a0, a1, a2, a3, a4, a5, a6, a7) }

    @Benchmark
    @OperationsPerInvocation(SIZE)
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    fun int_args1_targets1(st: StateTargets1): Int = args1(st.arr!!)

    @Benchmark
    @OperationsPerInvocation(SIZE)
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    fun int_args2_targets1(st: StateTargets1): Int = args2(st.arr!!)

    @Benchmark
    @OperationsPerInvocation(SIZE)
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    fun int_args4_targets1(st: StateTargets1): Int = args4(st.arr!!)

    @Benchmark
    @OperationsPerInvocation(SIZE)
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    fun int_args8_targets1(st: StateTargets1): Int = args8(st.arr!!)

    @Benchmark
    @OperationsPerInvocation(SIZE)
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    fun int_args1_targets2(st: StateTargets2): Int = args1(st.arr!!)

    @Benchmark
    @OperationsPerInvocation(SIZE)
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    fun int_args2_targets2(st: StateTargets2): Int = args2(st.arr!!)

    @Benchmark
    @OperationsPerInvocation(SIZE)
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    fun int_args4_targets2(st: StateTargets2): Int = args4(st.arr!!)

    @Benchmark
    @OperationsPerInvocation(SIZE)
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    fun int_args8_targets2(st: StateTargets2): Int = args8(st.arr!!)

    @Benchmark
    @OperationsPerInvocation(SIZE)
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    fun int_args1_targets3(st: StateTargets3): Int = args1(st.arr!!)

    @Benchmark
    @OperationsPerInvocation(SIZE)
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    fun int_args2_targets3(st: StateTargets3): Int = args2(st.arr!!)

    @Benchmark
    @OperationsPerInvocation(SIZE)
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    fun int_args4_targets3(st: StateTargets3): Int = args4(st.arr!!)

    @Benchmark
    @OperationsPerInvocation(SIZE)
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    fun int_args8_targets3(st: StateTargets3): Int = args8(st.arr!!)
}
