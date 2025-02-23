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

data class R32int(val v0: Int = 0) : Int32, ByInt {
    override fun longValue(): Long = v0.toLong()

    override fun intValue(): Int = v0

    override fun neg(): Int32 = R32int(-v0)

    override fun add(o: Int32): Int32 = R32int(v0 + o.intValue())

    fun add(o: R32int): Int32 = R32int(v0 + o.v0)

    override fun sub(o: Int32): Int32 = R32int(v0 - o.intValue())

    fun sub(o: R32int): Int32 = R32int(v0 - o.v0)

    override fun mult(o: Int32): Int32 = R32int(v0 * o.intValue())

    fun mult(o: R32int): Int32 = R32int(v0 * o.v0)

    override fun compareTo(other: Int32): Int = v0.compareTo(other.intValue())

    fun compareTo(o: R32int): Int = v0.compareTo(o.v0)

    override fun intSum(): Int = v0
}