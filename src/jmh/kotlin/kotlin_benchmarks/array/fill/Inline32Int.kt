package kotlin_benchmarks.array.fill

import kotlin_benchmarks.array.util.StatesQ32int
import kotlin_benchmarks.types.Q32int
import org.openjdk.jmh.annotations.*
import java.util.Arrays

@State(Scope.Thread)
open class Inline32int : StatesQ32int() {

    object RefStaticField {
        @JvmField
        val f = Q32int(42)
    }

    object ValStaticField {
        @JvmField
        val f = Q32int(42)
    }

    @State(Scope.Thread)
    open class RefInstanceField {
        @JvmField
        val f: Q32int = Q32int(42)
    }

    @State(Scope.Thread)
    open class ValInstanceField {
        @JvmField
        val f: Q32int = Q32int(42)
    }

    @Benchmark
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    fun Def_to_Val_as_Val_fill(st: Val_as_Val) {
        val arr = st.arr
        for (i in arr!!.indices) {
            arr[i] = Q32int()
        }
    }

    @Benchmark
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    fun New_to_Val_as_Val_fill(st: Val_as_Val) {
        val arr = st.arr
        val v = Q32int(42)
        for (i in arr!!.indices) {
            arr[i] = v
        }
    }

    @Benchmark
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    fun Ref_to_Val_as_Val_fillstat(st: Val_as_Val) {
        val arr = st.arr
        for (i in arr!!.indices) {
            arr[i] = RefStaticField.f
        }
    }

    @Benchmark
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    fun Val_to_Val_as_Val_fillstat(st: Val_as_Val) {
        val arr = st.arr
        for (i in arr!!.indices) {
            arr[i] = ValStaticField.f
        }
    }

    @Benchmark
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    fun Ref_to_Val_as_Val_fillinst(st: Val_as_Val, f: RefInstanceField) {
        val arr = st.arr
        for (i in arr!!.indices) {
            arr[i] = f.f
        }
    }

    @Benchmark
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    fun Val_to_Val_as_Val_fillinst(st: Val_as_Val, f: ValInstanceField) {
        val arr = st.arr
        for (i in arr!!.indices) {
            arr[i] = f.f
        }
    }

    @Benchmark
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    fun Def_to_Ref_as_Ref_fill(st: Ref_as_Ref) {
        val arr = st.arr
        for (i in arr!!.indices) {
            arr[i] = Q32int()
        }
    }

    @Benchmark
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    fun New_to_Ref_as_Ref_fill(st: Ref_as_Ref) {
        val arr = st.arr
        val v = Q32int(42)
        for (i in arr!!.indices) {
            arr[i] = v
        }
    }

    @Benchmark
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    fun Ref_to_Ref_as_Ref_fillstat(st: Ref_as_Ref) {
        val arr = st.arr
        for (i in arr!!.indices) {
            arr[i] = RefStaticField.f
        }
    }

    @Benchmark
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    fun Val_to_Ref_as_Ref_fillstat(st: Ref_as_Ref) {
        val arr = st.arr
        for (i in arr!!.indices) {
            arr[i] = ValStaticField.f
        }
    }

    @Benchmark
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    fun Ref_to_Ref_as_Ref_fillinst(st: Ref_as_Ref, f: RefInstanceField) {
        val arr = st.arr
        for (i in arr!!.indices) {
            arr[i] = f.f
        }
    }

    @Benchmark
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    fun Val_to_Ref_as_Ref_fillinst(st: Ref_as_Ref, f: ValInstanceField) {
        val arr = st.arr
        for (i in arr!!.indices) {
            arr[i] = f.f
        }
    }

    @Benchmark
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    fun Def_to_Val_as_Val_arrayfill(st: Val_as_Val) {
        Arrays.fill(st.arr, Q32int())
    }

    @Benchmark
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    fun New_to_Val_as_Val_arrayfill(st: Val_as_Val) {
        Arrays.fill(st.arr, Q32int(42))
    }

    @Benchmark
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    fun Ref_to_Val_as_Val_arrayfillstat(st: Val_as_Val) {
        Arrays.fill(st.arr, RefStaticField.f)
    }

    @Benchmark
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    fun Val_to_Val_as_Val_arrayfillstat(st: Val_as_Val) {
        Arrays.fill(st.arr, ValStaticField.f)
    }

    @Benchmark
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    fun Ref_to_Val_as_Val_arrayfillinst(st: Val_as_Val, f: RefInstanceField) {
        Arrays.fill(st.arr, f.f)
    }

    @Benchmark
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    fun Val_to_Val_as_Val_arrayfillinst(st: Val_as_Val, f: ValInstanceField) {
        Arrays.fill(st.arr, f.f)
    }

    @Benchmark
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    fun Def_to_Ref_as_Ref_arrayfill(st: Ref_as_Ref) {
        Arrays.fill(st.arr, Q32int())
    }

    @Benchmark
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    fun New_to_Ref_as_Ref_arrayfill(st: Ref_as_Ref) {
        Arrays.fill(st.arr, Q32int(42))
    }

    @Benchmark
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    fun Ref_to_Ref_as_Ref_arrayfillstat(st: Ref_as_Ref) {
        Arrays.fill(st.arr, RefStaticField.f)
    }

    @Benchmark
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    fun Val_to_Ref_as_Ref_arrayfillstat(st: Ref_as_Ref) {
        Arrays.fill(st.arr, ValStaticField.f)
    }

    @Benchmark
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    fun Ref_to_Ref_as_Ref_arrayfillinst(st: Ref_as_Ref, f: RefInstanceField) {
        Arrays.fill(st.arr, f.f)
    }

    @Benchmark
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    fun Val_to_Ref_as_Ref_arrayfillinst(st: Ref_as_Ref, f: ValInstanceField) {
        Arrays.fill(st.arr, f.f)
    }
}