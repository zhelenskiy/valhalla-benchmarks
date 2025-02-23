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

value class Q32byte(
    val v0: Byte,
    val v1: Byte,
    val v2: Byte,
    val v3: Byte
) : Int32, ByByte {

    constructor() : this(0)

    constructor(v: Int) : this(
        (v ushr 24).toByte(),
        (v ushr 16).toByte(),
        (v ushr 8).toByte(),
        v.toByte()
    )

    companion object {
        private const val MASK = 0xFF
    }

    override fun longValue(): Long = intValue().toLong()

    override fun intValue(): Int =
        ((v0.toInt() and MASK shl 24) or
         (v1.toInt() and MASK shl 16) or
         (v2.toInt() and MASK shl 8) or
         (v3.toInt() and MASK))

    override fun neg(): Int32 = Q32byte(-intValue())

    override fun add(o: Int32): Int32 = Q32byte(intValue() + o.intValue())

    override fun sub(o: Int32): Int32 = Q32byte(intValue() - o.intValue())

    override fun mult(o: Int32): Int32 = Q32byte(intValue() * o.intValue())

    override fun compareTo(other: Int32): Int = intValue().compareTo(other.intValue())

    override fun byteSum(): Byte = (v0 + v1 + v2 + v3).toByte()
}