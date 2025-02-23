package kotlin_benchmarks.callconv

import org.openjdk.jmh.annotations.*
import java.util.concurrent.TimeUnit

@Fork(3)
@Warmup(iterations = 5, time = 1)
@Measurement(iterations = 5, time = 1)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@BenchmarkMode(Mode.AverageTime)
@State(Scope.Thread)
open class PrimitiveByte {

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
        abstract fun compute(v1: Byte): Byte
        abstract fun compute(v1: Byte, v2: Byte): Byte
        abstract fun compute(v1: Byte, v2: Byte, v3: Byte, v4: Byte): Byte
        abstract fun compute(
            v1: Byte, v2: Byte, v3: Byte, v4: Byte, 
            v5: Byte, v6: Byte, v7: Byte, v8: Byte
        ): Byte
    }

    class InvokeImpl1 : InvocationLogic() {
        override fun compute(v1: Byte): Byte = v1
        override fun compute(v1: Byte, v2: Byte): Byte = v1
        override fun compute(v1: Byte, v2: Byte, v3: Byte, v4: Byte): Byte = v1
        override fun compute(
            v1: Byte, v2: Byte, v3: Byte, v4: Byte, 
            v5: Byte, v6: Byte, v7: Byte, v8: Byte
        ): Byte = v1
    }

    class InvokeImpl2 : InvocationLogic() {
        override fun compute(v1: Byte): Byte = v1
        override fun compute(v1: Byte, v2: Byte): Byte = v1
        override fun compute(v1: Byte, v2: Byte, v3: Byte, v4: Byte): Byte = v1
        override fun compute(
            v1: Byte, v2: Byte, v3: Byte, v4: Byte, 
            v5: Byte, v6: Byte, v7: Byte, v8: Byte
        ): Byte = v1
    }

    class InvokeImpl3 : InvocationLogic() {
        override fun compute(v1: Byte): Byte = v1
        override fun compute(v1: Byte, v2: Byte): Byte = v1
        override fun compute(v1: Byte, v2: Byte, v3: Byte, v4: Byte): Byte = v1
        override fun compute(
            v1: Byte, v2: Byte, v3: Byte, v4: Byte, 
            v5: Byte, v6: Byte, v7: Byte, v8: Byte
        ): Byte = v1
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

    private val a0: Byte = 42
    private val a1: Byte = 43
    private val a2: Byte = 44
    private val a3: Byte = 45
    private val a4: Byte = 46
    private val a5: Byte = 47
    private val a6: Byte = 48
    private val a7: Byte = 49

    @CompilerControl(CompilerControl.Mode.INLINE)
    fun args1(logic: Array<InvocationLogic>?): Byte = logic!!.sumOf { it.compute(a0).toInt() }.toByte()

    @CompilerControl(CompilerControl.Mode.INLINE)
    fun args2(logic: Array<InvocationLogic>?): Byte = logic!!.sumOf { it.compute(a0, a1).toInt() }.toByte()

    @CompilerControl(CompilerControl.Mode.INLINE)
    fun args4(logic: Array<InvocationLogic>?): Byte = logic!!.sumOf { it.compute(a0, a1, a2, a3).toInt() }.toByte()

    @CompilerControl(CompilerControl.Mode.INLINE)
    fun args8(logic: Array<InvocationLogic>?): Byte = logic!!.sumOf { it.compute(a0, a1, a2, a3, a4, a5, a6, a7).toInt() }.toByte()

    @Benchmark
    @OperationsPerInvocation(SIZE)
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    fun byte_args1_targets0(st: StateTargets0): Byte = st.arr!!.sumOf { it.compute(a0).toInt() }.toByte()

    @Benchmark
    @OperationsPerInvocation(SIZE)
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    fun byte_args2_targets0(st: StateTargets0): Byte = st.arr!!.sumOf { it.compute(a0, a1).toInt() }.toByte()

    @Benchmark
    @OperationsPerInvocation(SIZE)
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    fun byte_args4_targets0(st: StateTargets0): Byte = st.arr!!.sumOf { it.compute(a0, a1, a2, a3).toInt() }.toByte()

    @Benchmark
    @OperationsPerInvocation(SIZE)
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    fun byte_args8_targets0(st: StateTargets0): Byte = st.arr!!.sumOf { it.compute(a0, a1, a2, a3, a4, a5, a6, a7).toInt() }.toByte()

    @Benchmark
    @OperationsPerInvocation(SIZE)
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    fun byte_args1_targets1(st: StateTargets1): Byte = args1(st.arr!!)

    @Benchmark
    @OperationsPerInvocation(SIZE)
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    fun byte_args2_targets1(st: StateTargets1): Byte = args2(st.arr!!)

    @Benchmark
    @OperationsPerInvocation(SIZE)
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    fun byte_args4_targets1(st: StateTargets1): Byte = args4(st.arr!!)

    @Benchmark
    @OperationsPerInvocation(SIZE)
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    fun byte_args8_targets1(st: StateTargets1): Byte = args8(st.arr!!)

    @Benchmark
    @OperationsPerInvocation(SIZE)
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    fun byte_args1_targets2(st: StateTargets2): Byte = args1(st.arr!!)

    @Benchmark
    @OperationsPerInvocation(SIZE)
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    fun byte_args2_targets2(st: StateTargets2): Byte = args2(st.arr!!)

    @Benchmark
    @OperationsPerInvocation(SIZE)
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    fun byte_args4_targets2(st: StateTargets2): Byte = args4(st.arr!!)

    @Benchmark
    @OperationsPerInvocation(SIZE)
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    fun byte_args8_targets2(st: StateTargets2): Byte = args8(st.arr!!)

    @Benchmark
    @OperationsPerInvocation(SIZE)
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    fun byte_args1_targets3(st: StateTargets3): Byte = args1(st.arr!!)

    @Benchmark
    @OperationsPerInvocation(SIZE)
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    fun byte_args2_targets3(st: StateTargets3): Byte = args2(st.arr!!)

    @Benchmark
    @OperationsPerInvocation(SIZE)
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    fun byte_args4_targets3(st: StateTargets3): Byte = args4(st.arr!!)

    @Benchmark
    @OperationsPerInvocation(SIZE)
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    fun byte_args8_targets3(st: StateTargets3): Byte = args8(st.arr!!)
}
