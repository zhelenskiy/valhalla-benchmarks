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
package kotlin_benchmarks.array.read

import kotlin_benchmarks.array.util.StatesQ128int
import kotlin_benchmarks.types.Int128
import kotlin_benchmarks.types.Q128int
import org.openjdk.jmh.annotations.Benchmark
import org.openjdk.jmh.annotations.CompilerControl

open class Inline128int : StatesQ128int() {

    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    fun val_consume(v: Q128int?) {
    }

    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    fun ref_consume(v: Q128int?) {
    }

    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    fun int_consume(v: Int128?) {
    }

    @Benchmark
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    fun Val_as_Val_to_Val_read(st: Val_as_Val) {
        val arr = st.arr
        for (i in arr!!.indices) {
            val_consume(arr[i])
        }
    }

    @Benchmark
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    fun Val_as_Val_to_Ref_read(st: Val_as_Val) {
        val arr = st.arr
        for (i in arr!!.indices) {
            ref_consume(arr[i])
        }
    }

    @Benchmark
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    fun Val_as_Val_to_Int_read(st: Val_as_Val) {
        val arr = st.arr
        for (i in arr!!.indices) {
            int_consume(arr[i])
        }
    }

    @Benchmark
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    fun Val_as_Ref_to_Val_read(st: Val_as_Ref) {
        val arr = st.arr
        for (i in arr!!.indices) {
            val_consume(arr[i])
        }
    }

    @Benchmark
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    fun Val_as_Ref_to_Ref_read(st: Val_as_Ref) {
        val arr = st.arr
        for (i in arr!!.indices) {
            ref_consume(arr[i])
        }
    }

    @Benchmark
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    fun Val_as_Ref_to_Int_read(st: Val_as_Ref) {
        val arr = st.arr
        for (i in arr!!.indices) {
            int_consume(arr[i])
        }
    }

    @Benchmark
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    fun Val_as_Int_to_Int_read(st: Val_as_Int) {
        val arr = st.arr
        for (i in arr!!.indices) {
            int_consume(arr[i])
        }
    }
}