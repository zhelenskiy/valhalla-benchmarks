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
package kotlin_benchmarks.array.sum

import kotlin_benchmarks.array.util.StatesQ64byte
import kotlin_benchmarks.types.ByByte
import kotlin_benchmarks.types.Q64byte
import org.openjdk.jmh.annotations.Benchmark
import org.openjdk.jmh.annotations.CompilerControl

open class Inline64byte : StatesQ64byte() {

    @Benchmark
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    fun Val_as_Val_fields(st: Val_as_Val): Byte {
        var s: Byte = 0
        val arr = st.arr
        for (i in arr!!.indices) {
            s = (s + arr[i]!!.v0.v0 +
                arr[i]!!.v0.v1 +
                arr[i]!!.v0.v2 +
                arr[i]!!.v0.v3 +
                arr[i]!!.v1.v0 +
                arr[i]!!.v1.v1 +
                arr[i]!!.v1.v2 +
                arr[i]!!.v1.v3).toByte()
        }
        return s
    }

    @Benchmark
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    fun Val_as_Ref_fields(st: Val_as_Ref): Byte {
        var s: Byte = 0
        val arr = st.arr
        for (i in arr!!.indices) {
            s = (s + arr[i]!!.v0.v0 +
                arr[i]!!.v0.v1 +
                arr[i]!!.v0.v2 +
                arr[i]!!.v0.v3 +
                arr[i]!!.v1.v0 +
                arr[i]!!.v1.v1 +
                arr[i]!!.v1.v2 +
                arr[i]!!.v1.v3).toByte()
        }
        return s
    }

    @Benchmark
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    fun Ref_as_Ref_fields(st: Ref_as_Ref): Byte {
        var s: Byte = 0
        val arr = st.arr
        for (i in arr!!.indices) {
            s = (s + arr[i]!!.v0.v0 +
                arr[i]!!.v0.v1 +
                arr[i]!!.v0.v2 +
                arr[i]!!.v0.v3 +
                arr[i]!!.v1.v0 +
                arr[i]!!.v1.v1 +
                arr[i]!!.v1.v2 +
                arr[i]!!.v1.v3).toByte()
        }
        return s
    }

    @Benchmark
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    fun Val_as_Val_sum(st: Val_as_Val): Byte {
        var s: Byte = 0
        val arr = st.arr
        for (i in arr!!.indices) {
            s = (s + arr[i]!!.byteSum()).toByte()
        }
        return s
    }

    @Benchmark
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    fun Val_as_Ref_sum(st: Val_as_Ref): Byte {
        var s: Byte = 0
        val arr = st.arr
        for (i in arr!!.indices) {
            s = (s + arr[i]!!.byteSum()).toByte()
        }
        return s
    }

    @Benchmark
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    fun Ref_as_Ref_sum(st: Ref_as_Ref): Byte {
        var s: Byte = 0
        val arr = st.arr
        for (i in arr!!.indices) {
            s = (s + arr[i]!!.byteSum()).toByte()
        }
        return s
    }

    @Benchmark
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    fun Val_as_Int_sum(st: Val_as_By): Int {
        var s = 0
        val arr = st.arr
        for (i in arr!!.indices) {
            s += arr[i]!!.byteSum()
        }
        return s
    }

    @Benchmark
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    fun Int_as_Int_sum(st: By_as_By): Int {
        var s = 0
        val arr = st.arr
        for (i in arr!!.indices) {
            s += arr[i]!!.byteSum()
        }
        return s
    }
}