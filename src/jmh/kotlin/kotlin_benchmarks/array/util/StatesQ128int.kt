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

import kotlin_benchmarks.types.ByInt
import kotlin_benchmarks.types.Int128
import kotlin_benchmarks.types.Q128int
import kotlin_benchmarks.util.SizeBase
import org.openjdk.jmh.annotations.Setup

open class StatesQ128int : SizeBase() {

    abstract class ObjState : SizeState() {
        @JvmField var arr: Array<Any?>? = null
        fun fill() {
            for (i in arr!!.indices) {
                arr!![i] = Q128int(i.toLong())
            }
        }
    }

    abstract class IntState : SizeState() {
        @JvmField var arr: Array<Int128?>? = null
        fun fill() {
            for (i in arr!!.indices) {
                arr!![i] = Q128int(i.toLong())
            }
        }
    }

    abstract class RefState : SizeState() {
        @JvmField var arr: Array<Q128int?>? = null
        fun fill() {
            for (i in arr!!.indices) {
                arr!![i] = Q128int(i.toLong())
            }
        }
    }

    abstract class ValState : SizeState() {
        @JvmField var arr: Array<Q128int?>? = null
        fun fill() {
            for (i in arr!!.indices) {
                arr!![i] = Q128int(i.toLong())
            }
        }
    }

    // naming convention: <runtime array type>_as_<static array type>

    open class Obj_as_Obj : ObjState() {
        @Setup
        fun setup() {
            arr = arrayOfNulls<Any?>(size)
            fill()
        }
    }

    open class Int_as_Obj : ObjState() {
        @Setup
        fun setup() {
            arr = arrayOfNulls<Int128>(size) as Array<Any?>
            fill()
        }
    }

    open class Ref_as_Obj : ObjState() {
        @Setup
        fun setup() {
            arr = arrayOfNulls<Q128int>(size) as Array<Any?>
            fill()
        }
    }

    open class Val_as_Obj : ObjState() {
        @Setup
        fun setup() {
            arr = arrayOfNulls<Q128int>(size) as Array<Any?>
            fill()
        }
    }

    open class Int_as_Int : IntState() {
        @Setup
        fun setup() {
            arr = arrayOfNulls<Int128>(size)
            fill()
        }
    }

    open class Ref_as_Int : IntState() {
        @Setup
        fun setup() {
            arr = arrayOfNulls<Q128int>(size) as Array<Int128?>
            fill()
        }
    }

    open class Val_as_Int : IntState() {
        @Setup
        fun setup() {
            arr = arrayOfNulls<Q128int>(size) as Array<Int128?>
            fill()
        }
    }

    open class Ref_as_Ref : RefState() {
        @Setup
        fun setup() {
            arr = arrayOfNulls<Q128int>(size)
            fill()
        }
    }

    open class Val_as_Ref : RefState() {
        @Setup
        fun setup() {
            arr = arrayOfNulls<Q128int>(size)
            fill()
        }
    }

    open class Val_as_Val : ValState() {
        @Setup
        fun setup() {
            arr = arrayOfNulls<Q128int>(size)
            fill()
        }
    }

    abstract class ByState : SizeState() {
        @JvmField var arr: Array<ByInt?>? = null
        fun fill() {
            for (i in arr!!.indices) {
                arr!![i] = Q128int(i.toLong())
            }
        }
    }

    open class Val_as_By : ByState() {
        @Setup
        fun setup() {
            arr = arrayOfNulls<Q128int>(size) as Array<ByInt?>
            fill()
        }
    }

    open class Ref_as_By : ByState() {
        @Setup
        fun setup() {
            arr = arrayOfNulls<Q128int>(size) as Array<ByInt?>
            fill()
        }
    }

    open class By_as_By : ByState() {
        @Setup
        fun setup() {
            arr = arrayOfNulls<ByInt>(size)
            fill()
        }
    }
}
