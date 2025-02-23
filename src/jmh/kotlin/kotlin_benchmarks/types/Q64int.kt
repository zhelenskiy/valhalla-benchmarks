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
package kotlin_benchmarks.types

value class Q64int(
    val v0: Q32int,
    val v1: Q32int
) : Int64, ByInt {

    constructor() : this(0L)

    constructor(v: Long) : this(
        (v ushr 32).toInt(),
        v.toInt()
    )

    constructor(hi: Int, lo: Int) : this(Q32int(hi), Q32int(lo))

    companion object {
        private const val MASK = 0xFFFFFFFFL
    }

    override fun longValue(): Long =
        (v0.intValue().toLong() and MASK shl 32) or
        (v1.intValue().toLong() and MASK)

    override fun intValue(): Int = v1.intValue()

    override fun intSum(): Int = v0.intSum() + v1.intSum()
}