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

import kotlin_benchmarks.types.Int64
import kotlin_benchmarks.types.R64int
import kotlin_benchmarks.util.SizeBase
import org.openjdk.jmh.annotations.Setup

open class StatesR64int : SizeBase() {

    abstract class ObjState : SizeState() {
        @JvmField var arr: Array<Any?>? = null
        fun fill() {
            for (i in arr!!.indices) {
                arr!![i] = R64int(i.toLong())
            }
        }
    }

    abstract class IntState : SizeState() {
        @JvmField var arr: Array<Int64?>? = null
        fun fill() {
            for (i in arr!!.indices) {
                arr!![i] = R64int(i.toLong())
            }
        }
    }

    abstract class RefState : SizeState() {
        @JvmField var arr: Array<R64int?>? = null
        fun fill() {
            for (i in arr!!.indices) {
                arr!![i] = R64int(i.toLong())
            }
        }
    }

    // naming convention: <runtime array type>_as_<static array type>

    class Obj_as_Obj : ObjState() {
        @Setup
        fun setup() {
            arr = arrayOfNulls<Any>(size)
            fill()
        }
    }

    class Int_as_Obj : ObjState() {
        @Setup
        fun setup() {
            arr = arrayOfNulls<Int64>(size) as Array<Any?>
            fill()
        }
    }

    class Ref_as_Obj : ObjState() {
        @Setup
        fun setup() {
            arr = arrayOfNulls<R64int>(size) as Array<Any?>
            fill()
        }
    }

    class Int_as_Int : IntState() {
        @Setup
        fun setup() {
            arr = arrayOfNulls<Int64>(size)
            fill()
        }
    }

    class Ref_as_Int : IntState() {
        @Setup
        fun setup() {
            arr = arrayOfNulls<R64int>(size) as Array<Int64?>
            fill()
        }
    }

    class Ref_as_Ref : RefState() {
        @Setup
        fun setup() {
            arr = arrayOfNulls<R64int>(size)
            fill()
        }
    }
}
