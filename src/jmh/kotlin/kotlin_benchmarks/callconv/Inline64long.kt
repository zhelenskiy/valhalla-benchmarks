package kotlin_benchmarks.callconv

import kotlin_benchmarks.types.Q64long
import org.openjdk.jmh.annotations.*
import java.util.concurrent.TimeUnit

@Fork(3)
@Warmup(iterations = 5, time = 1)
@Measurement(iterations = 5, time = 1)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@BenchmarkMode(Mode.AverageTime)
@State(Scope.Thread)
open class Inline64long {

    companion object {
        const val SIZE = 96 // must be divisible by 2 and 3 and around 100

        @JvmStatic
        private fun getImpl(i: Int, targets: Int): InvocationLogic = when (i % targets) {
            0 -> InvokeImpl1()
            1 -> InvokeImpl2()
            2 -> InvokeImpl3()
            else -> throw IllegalArgumentException("Invalid implementation")
        }
    }

    abstract class InvocationLogic {
        abstract fun compute(v1: Q64long): Q64long
        abstract fun compute(v1: Q64long, v2: Q64long): Q64long
        abstract fun compute(v1: Q64long, v2: Q64long, v3: Q64long, v4: Q64long): Q64long
        abstract fun compute(
            v1: Q64long, 
            v2: Q64long, 
            v3: Q64long, 
            v4: Q64long, 
            v5: Q64long, 
            v6: Q64long, 
            v7: Q64long, 
            v8: Q64long
        ): Q64long
    }

    class InvokeImpl1 : InvocationLogic() {
        override fun compute(v1: Q64long): Q64long = v1
        override fun compute(v1: Q64long, v2: Q64long): Q64long = v1
        override fun compute(v1: Q64long, v2: Q64long, v3: Q64long, v4: Q64long): Q64long = v1
        override fun compute(
            v1: Q64long, 
            v2: Q64long, 
            v3: Q64long, 
            v4: Q64long, 
            v5: Q64long, 
            v6: Q64long, 
            v7: Q64long, 
            v8: Q64long
        ): Q64long = v1
    }

    class InvokeImpl2 : InvocationLogic() {
        override fun compute(v1: Q64long): Q64long = v1
        override fun compute(v1: Q64long, v2: Q64long): Q64long = v1
        override fun compute(v1: Q64long, v2: Q64long, v3: Q64long, v4: Q64long): Q64long = v1
        override fun compute(
            v1: Q64long, 
            v2: Q64long, 
            v3: Q64long, 
            v4: Q64long, 
            v5: Q64long, 
            v6: Q64long, 
            v7: Q64long, 
            v8: Q64long
        ): Q64long = v1
    }

    class InvokeImpl3 : InvocationLogic() {
        override fun compute(v1: Q64long): Q64long = v1
        override fun compute(v1: Q64long, v2: Q64long): Q64long = v1
        override fun compute(v1: Q64long, v2: Q64long, v3: Q64long, v4: Q64long): Q64long = v1
        override fun compute(
            v1: Q64long, 
            v2: Q64long, 
            v3: Q64long, 
            v4: Q64long, 
            v5: Q64long, 
            v6: Q64long, 
            v7: Q64long, 
            v8: Q64long
        ): Q64long = v1
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

    private val a0 = Q64long(42)
    private val a1 = Q64long(43)
    private val a2 = Q64long(44)
    private val a3 = Q64long(45)
    private val a4 = Q64long(46)
    private val a5 = Q64long(47)
    private val a6 = Q64long(48)
    private val a7 = Q64long(49)

    @CompilerControl(CompilerControl.Mode.INLINE)
    fun args1(logic: Array<InvocationLogic>?): Long = logic!!.sumOf { it.compute(a0).longValue() }

    @CompilerControl(CompilerControl.Mode.INLINE)
    fun args2(logic: Array<InvocationLogic>?): Long = logic!!.sumOf { it.compute(a0, a1).longValue() }

    @CompilerControl(CompilerControl.Mode.INLINE)
    fun args4(logic: Array<InvocationLogic>?): Long = logic!!.sumOf { it.compute(a0, a1, a2, a3).longValue() }

    @CompilerControl(CompilerControl.Mode.INLINE)
    fun args8(logic: Array<InvocationLogic>?): Long = logic!!.sumOf { it.compute(a0, a1, a2, a3, a4, a5, a6, a7).longValue() }

    @Benchmark
    @OperationsPerInvocation(SIZE)
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    fun Q64long_args1_targets0(st: StateTargets0): Long = st.arr!!.sumOf { it.compute(a0).longValue() }

    @Benchmark
    @OperationsPerInvocation(SIZE)
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    fun Q64long_args2_targets0(st: StateTargets0): Long = st.arr!!.sumOf { it.compute(a0, a1).longValue() }

    @Benchmark
    @OperationsPerInvocation(SIZE)
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    fun Q64long_args4_targets0(st: StateTargets0): Long = st.arr!!.sumOf { it.compute(a0, a1, a2, a3).longValue() }

    @Benchmark
    @OperationsPerInvocation(SIZE)
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    fun Q64long_args8_targets0(st: StateTargets0): Long = st.arr!!.sumOf { it.compute(a0, a1, a2, a3, a4, a5, a6, a7).longValue() }

    @Benchmark
    @OperationsPerInvocation(SIZE)
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    fun Q64long_args1_targets1(st: StateTargets1): Long = args1(st.arr!!)

    @Benchmark
    @OperationsPerInvocation(SIZE)
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    fun Q64long_args2_targets1(st: StateTargets1): Long = args2(st.arr!!)

    @Benchmark
    @OperationsPerInvocation(SIZE)
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    fun Q64long_args4_targets1(st: StateTargets1): Long = args4(st.arr!!)

    @Benchmark
    @OperationsPerInvocation(SIZE)
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    fun Q64long_args8_targets1(st: StateTargets1): Long = args8(st.arr!!)

    @Benchmark
    @OperationsPerInvocation(SIZE)
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    fun Q64long_args1_targets2(st: StateTargets2): Long = args1(st.arr!!)

    @Benchmark
    @OperationsPerInvocation(SIZE)
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    fun Q64long_args2_targets2(st: StateTargets2): Long = args2(st.arr!!)

    @Benchmark
    @OperationsPerInvocation(SIZE)
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    fun Q64long_args4_targets2(st: StateTargets2): Long = args4(st.arr!!)

    @Benchmark
    @OperationsPerInvocation(SIZE)
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    fun Q64long_args8_targets2(st: StateTargets2): Long = args8(st.arr!!)

    @Benchmark
    @OperationsPerInvocation(SIZE)
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    fun Q64long_args1_targets3(st: StateTargets3): Long = args1(st.arr!!)

    @Benchmark
    @OperationsPerInvocation(SIZE)
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    fun Q64long_args2_targets3(st: StateTargets3): Long = args2(st.arr!!)

    @Benchmark
    @OperationsPerInvocation(SIZE)
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    fun Q64long_args4_targets3(st: StateTargets3): Long = args4(st.arr!!)

    @Benchmark
    @OperationsPerInvocation(SIZE)
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    fun Q64long_args8_targets3(st: StateTargets3): Long = args8(st.arr!!)
}
