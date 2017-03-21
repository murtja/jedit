/*
 * jEdit - Programmer's Text Editor
 * :tabSize=8:indentSize=8:noTabs=false:
 * :folding=explicit:collapseFolds=1:
 *
 * Copyright © 2017 jEdit contributors
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */

package org.jedit.core;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackReader;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.InvocationTargetException;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.MalformedInputException;

import org.gjt.sp.jedit.BufferHistory;
import org.gjt.sp.jedit.textarea.Selection;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.gjt.sp.util.IOUtilities.closeQuietly;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.sameInstance;
public class BufferHistoryTest {

    @Test
    public void testBufferHistoryValidRange() throws IOException
    {
        BufferHistory bh = new BufferHistory();

        BufferHistory.Entry e = new BufferHistory.Entry("some path", 0, "range 2 5", "UTF-8", "a");

        Selection[] selections = e.getSelection();
        assertThat(selections[0].toString(), is(equalTo("org.gjt.sp.jedit.textarea.Selection$Range[start=2,end=5,startLine=0,endLine=0]")));
    }

    @Test
    public void testBufferHistoryInvalidRange() throws IOException
    {
        BufferHistory bh = new BufferHistory();

        BufferHistory.Entry e = new BufferHistory.Entry("some path", 0, "range 4 2", "UTF-8", "a");

        Selection[] selections = e.getSelection();
        assertThat(selections[0].toString(), is(equalTo("org.gjt.sp.jedit.textarea.Selection$Range[start=2,end=4,startLine=0,endLine=0]")));
    }

}
