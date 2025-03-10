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
package org.openjdk.bench.valhalla.array.util;

import org.openjdk.bench.valhalla.types.ByInt;
import org.openjdk.bench.valhalla.types.Int64;
import org.openjdk.bench.valhalla.types.Q64int;
import org.openjdk.bench.valhalla.util.SizeBase;
import org.openjdk.jmh.annotations.Setup;

public class StatesQ64int extends SizeBase {

    public static abstract class ObjState extends SizeState {
        public Object[] arr;
        void fill() {
            for (int i = 0; i < arr.length; i++) {
                arr[i] = new Q64int(i);
            }
        }
    }

    public static abstract class IntState extends SizeState {
        public Int64[] arr;
        void fill() {
            for (int i = 0; i < arr.length; i++) {
                arr[i] = new Q64int(i);
            }
        }
    }

    public static abstract class RefState extends SizeState {
        public Q64int[] arr;
        void fill() {
            for (int i = 0; i < arr.length; i++) {
                arr[i] = new Q64int(i);
            }
        }
    }

    public static abstract class ValState extends SizeState {
        public Q64int[] arr;
        void fill() {
            for (int i = 0; i < arr.length; i++) {
                arr[i] = new Q64int(i);
            }
        }
    }

    // naming convention: <runtime array type>_as_<static array type>

    public static class Obj_as_Obj extends ObjState {
        @Setup
        public void setup() {
            arr = new Object[size];
            fill();
        }
    }

    public static class Int_as_Obj extends ObjState {
        @Setup
        public void setup() {
            arr = new Int64[size];
            fill();
        }
    }

    public static class Ref_as_Obj extends ObjState {
        @Setup
        public void setup() {
            arr = new Q64int[size];
            fill();
        }
    }

    public static class Val_as_Obj extends ObjState {
        @Setup
        public void setup() {
            arr = new Q64int[size];
            fill();
        }
    }

    public static class Int_as_Int extends IntState {
        @Setup
        public void setup() {
            arr = new Int64[size];
            fill();
        }
    }

    public static class Ref_as_Int extends IntState {
        @Setup
        public void setup() {
            arr = new Q64int[size];
            fill();
        }
    }

    public static class Val_as_Int extends IntState {
        @Setup
        public void setup() {
            arr = new Q64int[size];
            fill();
        }
    }

    public static class Ref_as_Ref extends RefState {
        @Setup
        public void setup() {
            arr = new Q64int[size];
            fill();
        }
    }

    public static class Val_as_Ref extends RefState {
        @Setup
        public void setup() {
            arr = new Q64int[size];
            fill();
        }
    }

    public static class Val_as_Val extends ValState {
        @Setup
        public void setup() {
            arr = new Q64int[size];
            fill();
        }
    }

    public static abstract class ByState extends SizeState {
        public ByInt[] arr;
        void fill() {
            for (int i = 0; i < arr.length; i++) {
                arr[i] = new Q64int(i);
            }
        }
    }

    public static class Val_as_By extends ByState {
        @Setup
        public void setup() {
            arr = new Q64int[size];
            fill();
        }
    }

    public static class Ref_as_By extends ByState {
        @Setup
        public void setup() {
            arr = new Q64int[size];
            fill();
        }
    }

    public static class By_as_By extends ByState {
        @Setup
        public void setup() {
            arr = new ByInt[size];
            fill();
        }
    }


}
