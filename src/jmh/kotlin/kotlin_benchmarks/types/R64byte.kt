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
package kotlin_benchmarks.types

data class R64byte(
    val v0: Byte,
    val v1: Byte,
    val v2: Byte,
    val v3: Byte,
    val v4: Byte,
    val v5: Byte,
    val v6: Byte,
    val v7: Byte
) : Int64, ByByte {

    constructor(v: Long) : this(
        (v ushr 56).toByte(),
        (v ushr 48).toByte(),
        (v ushr 40).toByte(),
        (v ushr 32).toByte(),
        (v ushr 24).toByte(),
        (v ushr 16).toByte(),
        (v ushr 8).toByte(),
        v.toByte()
    )

    companion object {
        private const val MASK = 0xFFL
        private const val IMASK = 0xFF
    }

    override fun longValue(): Long =
        ((v0.toLong() and MASK shl 56) or
         (v1.toLong() and MASK shl 48) or
         (v2.toLong() and MASK shl 40) or
         (v3.toLong() and MASK shl 32) or
         (v4.toLong() and MASK shl 24) or
         (v5.toLong() and MASK shl 16) or
         (v6.toLong() and MASK shl 8) or
         (v7.toLong() and MASK))

    override fun intValue(): Int =
        ((v4.toInt() and IMASK shl 24) or
         (v5.toInt() and IMASK shl 16) or
         (v6.toInt() and IMASK shl 8) or
         (v7.toInt() and IMASK))

    override fun byteSum(): Byte =
        (v0 + v1 + v2 + v3 + v4 + v5 + v6 + v7).toByte()
}