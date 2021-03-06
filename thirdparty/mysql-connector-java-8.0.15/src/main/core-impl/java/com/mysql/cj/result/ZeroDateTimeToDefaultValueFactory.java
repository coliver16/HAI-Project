/*
 * Copyright (c) 2015, 2018, Oracle and/or its affiliates. All rights reserved.
 *
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License, version 2.0, as published by the
 * Free Software Foundation.
 *
 * This program is also distributed with certain software (including but not
 * limited to OpenSSL) that is licensed under separate terms, as designated in a
 * particular file or component or in included license documentation. The
 * authors of MySQL hereby grant you an additional permission to link the
 * program and your derivative works with the separately licensed software that
 * they have included with MySQL.
 *
 * Without limiting anything contained in the foregoing, this file, which is
 * part of MySQL Connector/J, is also subject to the Universal FOSS Exception,
 * version 1.0, a copy of which can be found at
 * http://oss.oracle.com/licenses/universal-foss-exception.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License, version 2.0,
 * for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin St, Fifth Floor, Boston, MA 02110-1301  USA
 */

package com.mysql.cj.result;

/**
 * A decorating value factory which "rounds" zero date/time values into defaults. All fields are set to their lowest permissible value. e.g. 0000-00-00 is
 * returned as 0001-01-01.
 * 
 * @param <T>
 *            value Type
 */
public class ZeroDateTimeToDefaultValueFactory<T> extends BaseDecoratingValueFactory<T> {
    public ZeroDateTimeToDefaultValueFactory(ValueFactory<T> targetVf) {
        super(targetVf);
    }

    @Override
    public T createFromDate(int year, int month, int day) {
        if (year + month + day == 0) {
            return this.targetVf.createFromDate(1, 1, 1);
        }
        return this.targetVf.createFromDate(year, month, day);
    }

    @Override
    public T createFromTime(int hours, int minutes, int seconds, int nanos) {
        if (hours + minutes + seconds + nanos == 0) {
            return this.targetVf.createFromTime(0, 0, 0, 0);
        }
        return this.targetVf.createFromTime(hours, minutes, seconds, nanos);
    }

    @Override
    public T createFromTimestamp(int year, int month, int day, int hours, int minutes, int seconds, int nanos) {
        if (year + month + day + hours + minutes + seconds + nanos == 0) {
            return this.targetVf.createFromTimestamp(1, 1, 1, 0, 0, 0, 0);
        }
        return this.targetVf.createFromTimestamp(year, month, day, hours, minutes, seconds, nanos);
    }
}
