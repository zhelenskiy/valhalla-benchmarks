/*
 * Copyright (c) 2020, 2024, Oracle and/or its affiliates. All rights reserved.
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
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */
package kotlin_benchmarks.array.fill

import kotlin_benchmarks.array.util.StatesR32int
import kotlin_benchmarks.types.R32int
import org.openjdk.jmh.annotations.Benchmark
import org.openjdk.jmh.annotations.CompilerControl
import org.openjdk.jmh.annotations.Scope
import org.openjdk.jmh.annotations.State
import java.util.Arrays

open class Identity32int : StatesR32int() {

    object RefStaticField {
        @JvmField
        var f = R32int(42)
    }

    @State(Scope.Thread)
    open class RefInstanceField {
        @JvmField
        val f = R32int(42)
    }

    @Benchmark
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    fun Def_to_Ref_as_Ref_fill(st: Ref_as_Ref) {
        val arr = st.arr
        for (i in arr!!.indices) {
            arr[i] = R32int()
        }
    }

    @Benchmark
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    fun New_to_Ref_as_Ref_fill(st: Ref_as_Ref) {
        val arr = st.arr
        val v = R32int(42)
        for (i in arr!!.indices) {
            arr[i] = v
        }
    }

    @Benchmark
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    fun Ref_to_Ref_as_Ref_fill_fillstat(st: Ref_as_Ref) {
        val arr = st.arr
        for (i in arr!!.indices) {
            arr[i] = RefStaticField.f
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
    fun Def_to_Ref_as_Ref_arrayfill(st: Ref_as_Ref) {
        Arrays.fill(st.arr, R32int())
    }

    @Benchmark
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    fun New_to_Ref_as_Ref_arrayfill(st: Ref_as_Ref) {
        Arrays.fill(st.arr, R32int(42))
    }

    @Benchmark
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    fun Ref_to_Ref_as_Ref_arrayfillstat(st: Ref_as_Ref) {
        Arrays.fill(st.arr, RefStaticField.f)
    }

    @Benchmark
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    fun Ref_to_Ref_as_Ref_arrayfillinst(st: Ref_as_Ref, f: RefInstanceField) {
        Arrays.fill(st.arr, f.f)
    }
}
