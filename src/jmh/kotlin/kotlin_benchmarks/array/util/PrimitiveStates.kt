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
package kotlin_benchmarks.array.util

import kotlin_benchmarks.util.SizeBase
import org.openjdk.jmh.annotations.Setup

open class PrimitiveStates : SizeBase() {

    abstract class ByteState : SizeState() {
        @JvmField var arr: ByteArray = ByteArray(0)
        fun fill() {
            for (i in arr.indices) {
                arr[i] = i.toByte()
            }
        }
    }

    abstract class IntState : SizeState() {
        @JvmField var arr: IntArray = IntArray(0)
        fun fill() {
            for (i in arr.indices) {
                arr[i] = i
            }
        }
    }

    abstract class LongState : SizeState() {
        @JvmField var arr: LongArray = LongArray(0)
        fun fill() {
            for (i in arr.indices) {
                arr[i] = i.toLong()
            }
        }
    }

    open class Primitive32int : IntState() {
        @Setup
        fun setup() {
            arr = IntArray(size)
            fill()
        }
    }

    open class Primitive64byte : ByteState() {
        @Setup
        fun setup() {
            arr = ByteArray(size * 8)
            fill()
        }
    }

    open class Primitive64int : IntState() {
        @Setup
        fun setup() {
            arr = IntArray(size * 2)
            fill()
        }
    }

    open class Primitive64long : LongState() {
        @Setup
        fun setup() {
            arr = LongArray(size)
            fill()
        }
    }

    open class Primitive128int : IntState() {
        @Setup
        fun setup() {
            arr = IntArray(size * 4)
            fill()
        }
    }
}
