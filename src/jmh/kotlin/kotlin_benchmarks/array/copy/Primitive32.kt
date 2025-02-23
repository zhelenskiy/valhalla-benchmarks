/*
 * Copyright (c) 2020, Oracle and/or its affiliates. All rights reserved.
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
package kotlin_benchmarks.array.copy

import kotlin_benchmarks.array.util.PrimitiveStates
import org.openjdk.jmh.annotations.Benchmark
import org.openjdk.jmh.annotations.CompilerControl

open class Primitive32 : PrimitiveStates() {

    @Benchmark
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    fun p32int_copy(s: Primitive32int, d: Primitive32int) {
        val src = s.arr
        val dst = d.arr
        for (i in src.indices) {
            dst[i] = src[i]
        }
    }

    @Benchmark
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    fun p32int_arraycopy(s: Primitive32int, d: Primitive32int) {
        System.arraycopy(s.arr, 0, d.arr, 0, s.arr.size)
    }
}