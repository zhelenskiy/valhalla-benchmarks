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

value class QComplex(
    @JvmField
    val re: Double,
    @JvmField
    val im: Double
) : Complex {
    override fun re(): Double = re

    override fun im(): Double = im

    override fun add(that: Complex): QComplex =
        QComplex(
            this.re + that.re(),
            this.im + that.im()
        )

    fun add(that: QComplex): QComplex =
        QComplex(
            this.re + that.re,
            this.im + that.im
        )

    override fun mul(that: Complex): QComplex =
        QComplex(
            this.re * that.re() - this.im * that.im(),
            this.re * that.im() + this.im * that.re()
        )

    fun mul(that: QComplex): QComplex =
        QComplex(
            this.re * that.re - this.im * that.im,
            this.re * that.im + this.im * that.re
        )
}