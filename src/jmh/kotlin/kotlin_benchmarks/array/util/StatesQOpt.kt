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
package kotlin_benchmarks.array.util

import kotlin_benchmarks.types.Int32
import kotlin_benchmarks.types.Opt
import kotlin_benchmarks.types.QOpt
import kotlin_benchmarks.types.R32int
import kotlin_benchmarks.util.SizeBase
import org.openjdk.jmh.annotations.Setup

open class StatesQOpt : SizeBase() {

    abstract class ObjState : SizeState() {
        @JvmField var arr: Array<Any?>? = null
        fun fill() {
            for (i in arr!!.indices) {
                arr!![i] = QOpt.of(R32int(i))
            }
        }
    }

    abstract class IntState : SizeState() {
        @JvmField var arr: Array<Opt<Int32>?>? = null
        fun fill() {
            for (i in arr!!.indices) {
                arr!![i] = QOpt.of(R32int(i))
            }
        }
    }

    abstract class RefState : SizeState() {
        @JvmField var arr: Array<QOpt<Int32>?>? = null
        fun fill() {
            for (i in arr!!.indices) {
                arr!![i] = QOpt.of(R32int(i))
            }
        }
    }

    abstract class ValState : SizeState() {
        @JvmField var arr: Array<QOpt<Int32>?>? = null
        fun fill() {
            for (i in arr!!.indices) {
                arr!![i] = QOpt.of(R32int(i))
            }
        }
    }

    // naming convention: <runtime array type>_as_<static array type>

    open class Obj_as_Obj : ObjState() {
        @Setup
        fun setup() {
            arr = arrayOfNulls<Any>(size)
            fill()
        }
    }

    open class Int_as_Obj : ObjState() {
        @Setup
        fun setup() {
            arr = arrayOfNulls<Opt<*>>(size) as Array<Any?>
            fill()
        }
    }

    open class Ref_as_Obj : ObjState() {
        @Setup
        fun setup() {
            arr = arrayOfNulls<QOpt<*>>(size) as Array<Any?>
            fill()
        }
    }

    open class Val_as_Obj : ObjState() {
        @Setup
        fun setup() {
            arr = arrayOfNulls<QOpt<*>>(size) as Array<Any?>
            fill()
        }
    }

    open class Int_as_Int : IntState() {
        @Setup
        fun setup() {
            arr = arrayOfNulls<Opt<*>>(size) as Array<Opt<Int32>?>
            fill()
        }
    }

    open class Ref_as_Int : IntState() {
        @Setup
        fun setup() {
            arr = arrayOfNulls<QOpt<*>>(size) as Array<Opt<Int32>?>
            fill()
        }
    }

    open class Val_as_Int : IntState() {
        @Setup
        fun setup() {
            arr = arrayOfNulls<QOpt<*>>(size) as Array<Opt<Int32>?>
            fill()
        }
    }

    open class Ref_as_Ref : RefState() {
        @Setup
        fun setup() {
            arr = arrayOfNulls<QOpt<*>>(size) as Array<QOpt<Int32>?>
            fill()
        }
    }

    open class Val_as_Ref : RefState() {
        @Setup
        fun setup() {
            arr = arrayOfNulls<QOpt<*>>(size) as Array<QOpt<Int32>?>
            fill()
        }
    }

    open class Val_as_Val : ValState() {
        @Setup
        fun setup() {
            arr = arrayOfNulls<QOpt<*>>(size) as Array<QOpt<Int32>?>
            fill()
        }
    }
}
