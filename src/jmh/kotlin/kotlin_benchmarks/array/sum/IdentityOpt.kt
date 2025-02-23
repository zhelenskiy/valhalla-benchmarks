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

import kotlin_benchmarks.array.util.StatesROpt
import kotlin_benchmarks.types.Int32
import kotlin_benchmarks.types.Opt
import kotlin_benchmarks.types.ROpt
import org.openjdk.jmh.annotations.Benchmark
import org.openjdk.jmh.annotations.CompilerControl

open class IdentityOpt : StatesROpt() {

    @Benchmark
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    fun Ref_as_Ref_fields(st: Ref_as_Ref): Int {
        var s = 0
        val arr = st.arr
        for (i in arr!!.indices) {
            s += arr[i]!!.value!!.intValue()
        }
        return s
    }

    @Benchmark
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    fun Ref_as_Ref_sum(st: Ref_as_Ref): Int {
        var s = 0
        val arr = st.arr
        for (i in arr!!.indices) {
            s += arr[i]!!.get().intValue()
        }
        return s
    }

    @Benchmark
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    fun Int_as_Int_sum(st: Int_as_Int): Int {
        var s = 0
        val arr = st.arr
        for (i in arr!!.indices) {
            s += arr[i]!!.get().intValue()
        }
        return s
    }
}