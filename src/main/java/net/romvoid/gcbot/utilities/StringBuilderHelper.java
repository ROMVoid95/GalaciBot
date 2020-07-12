/**
 * Copyright (C) 2020 ROMVoid95
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package net.romvoid.gcbot.utilities;

public class StringBuilderHelper {
	public final StringBuilder s;

	/**
	 * Constructs a string builder with no characters in it and an initial capacity
	 * of 16 characters.
	 */
	public StringBuilderHelper() {
		s = new StringBuilder();
	}

	/**
	 * Constructs a string builder with no characters in it and an initial capacity
	 * specified by the <code>capacity</code> argument.
	 *
	 * @param capacity the initial capacity.
	 * @throws NegativeArraySizeException if the <code>capacity</code> argument is
	 *                                    less than <code>0</code>.
	 */
	public StringBuilderHelper(final int capacity) {
		s = new StringBuilder(capacity);
	}

	/**
	 * Constructs a string builder initialized to the contents of the specified
	 * string. The initial capacity of the string builder is <code>16</code> plus
	 * the length of the string argument.
	 *
	 * @param str the initial contents of the buffer.
	 * @throws NullPointerException if <code>str</code> is <code>null</code>
	 */
	public StringBuilderHelper(final String str) {
		s = new StringBuilder(str);
	}

	/**
	 * Constructs a string builder that contains the same characters as the
	 * specified <code>CharSequence</code>. The initial capacity of the string
	 * builder is <code>16</code> plus the length of the <code>CharSequence</code>
	 * argument.
	 *
	 * @param seq the sequence to copy.
	 * @throws NullPointerException if <code>seq</code> is <code>null</code>
	 */
	public StringBuilderHelper(final CharSequence seq) {
		s = new StringBuilder(seq);
	}

	/**
	 * @see String#valueOf(Object)
	 * @see #append(String)
	 */
	public StringBuilder append(final Object obj) {
		return this.s.append(obj);
	}

	public StringBuilder append(final String str) {
		return this.s.append(str);
	}

	/**
	 * Appends the specified <tt>StringBuffer</tt> to this sequence.
	 * <p/>
	 * The characters of the <tt>StringBuffer</tt> argument are appended, in order,
	 * to this sequence, increasing the length of this sequence by the length of the
	 * argument. If <tt>sb</tt> is <tt>null</tt>, then the four characters
	 * <tt>"null"</tt> are appended to this sequence.
	 * <p/>
	 * Let <i>n</i> be the length of this character sequence just prior to execution
	 * of the <tt>append</tt> method. Then the character at index <i>k</i> in the
	 * new character sequence is equal to the character at index <i>k</i> in the old
	 * character sequence, if <i>k</i> is less than <i>n</i>; otherwise, it is equal
	 * to the character at index <i>k-n</i> in the argument <code>sb</code>.
	 *
	 * @param sb the <tt>StringBuffer</tt> to append.
	 * @return a reference to this object.
	 */
	public StringBuilder append(final StringBuffer sb) {
		return this.s.append(sb);
	}

	/**
	 * @throws IndexOutOfBoundsException {@inheritDoc}
	 */
	public StringBuilder append(final CharSequence s) {
		return this.s.append(s);
	}

	/**
	 * @throws IndexOutOfBoundsException {@inheritDoc}
	 */
	public StringBuilder append(final CharSequence s, final int start, final int end) {
		return this.s.append(s, start, end);
	}

	public StringBuilder append(final char[] str) {
		return this.s.append(str);
	}

	public StringBuilder append(final char[] str, final int offset, final int len) {
		return this.s.append(str, offset, len);
	}

	/**
	 * @see String#valueOf(boolean)
	 * @see #append(String)
	 */
	public StringBuilder append(final boolean b) {
		return this.s.append(b);
	}

	public StringBuilder append(final char c) {
		return this.s.append(c);
	}

	/**
	 * @see String#valueOf(int)
	 * @see #append(String)
	 */
	public StringBuilder append(final int i) {
		return this.s.append(i);
	}

	/**
	 * @see String#valueOf(long)
	 * @see #append(String)
	 */
	public StringBuilder append(final long lng) {
		return this.s.append(lng);
	}

	/**
	 * @see String#valueOf(float)
	 * @see #append(String)
	 */
	public StringBuilder append(final float f) {
		return this.s.append(f);
	}

	/**
	 * @see String#valueOf(double)
	 * @see #append(String)
	 */
	public StringBuilder append(final double d) {
		return this.s.append(d);
	}

	/**
	 * @since 1.5
	 */
	public StringBuilder appendCodePoint(final int codePoint) {
		return this.s.appendCodePoint(codePoint);
	}

	/**
	 * @throws StringIndexOutOfBoundsException {@inheritDoc}
	 */
	public StringBuilder delete(final int start, final int end) {
		return this.s.delete(start, end);
	}

	/**
	 * @throws StringIndexOutOfBoundsException {@inheritDoc}
	 */
	public StringBuilder deleteCharAt(final int index) {
		return this.s.deleteCharAt(index);
	}

	/**
	 * @throws StringIndexOutOfBoundsException {@inheritDoc}
	 */
	public StringBuilder replace(final int start, final int end, final String str) {
		return this.s.replace(start, end, str);
	}

	/**
	 * @throws StringIndexOutOfBoundsException {@inheritDoc}
	 */
	public StringBuilder insert(final int index, final char[] str, final int offset, final int len) {
		return this.s.insert(index, str, offset, len);
	}

	/**
	 * @throws StringIndexOutOfBoundsException {@inheritDoc}
	 * @see String#valueOf(Object)
	 * @see #insert(int, String)
	 */
	public StringBuilder insert(final int offset, final Object obj) {
		return this.s.insert(offset, obj);
	}

	/**
	 * @throws StringIndexOutOfBoundsException {@inheritDoc}
	 */
	public StringBuilder insert(final int offset, final String str) {
		return this.s.insert(offset, str);
	}

	/**
	 * @throws StringIndexOutOfBoundsException {@inheritDoc}
	 */
	public StringBuilder insert(final int offset, final char[] str) {
		return this.s.insert(offset, str);
	}

	/**
	 * @throws IndexOutOfBoundsException {@inheritDoc}
	 */
	public StringBuilder insert(final int dstOffset, final CharSequence s) {
		return this.s.insert(dstOffset, s);
	}

	/**
	 * @throws IndexOutOfBoundsException {@inheritDoc}
	 */
	public StringBuilder insert(final int dstOffset, final CharSequence s, final int start, final int end) {
		return this.s.insert(dstOffset, s, start, end);
	}

	/**
	 * @throws StringIndexOutOfBoundsException {@inheritDoc}
	 * @see String#valueOf(boolean)
	 * @see #insert(int, String)
	 */
	public StringBuilder insert(final int offset, final boolean b) {
		return this.s.insert(offset, b);
	}

	/**
	 * @throws IndexOutOfBoundsException {@inheritDoc}
	 */
	public StringBuilder insert(final int offset, final char c) {
		return this.s.insert(offset, c);
	}

	/**
	 * @throws StringIndexOutOfBoundsException {@inheritDoc}
	 * @see String#valueOf(int)
	 * @see #insert(int, String)
	 */
	public StringBuilder insert(final int offset, final int i) {
		return this.s.insert(offset, i);
	}

	/**
	 * @throws StringIndexOutOfBoundsException {@inheritDoc}
	 * @see String#valueOf(long)
	 * @see #insert(int, String)
	 */
	public StringBuilder insert(final int offset, final long l) {
		return this.s.insert(offset, l);
	}

	/**
	 * @throws StringIndexOutOfBoundsException {@inheritDoc}
	 * @see String#valueOf(float)
	 * @see #insert(int, String)
	 */
	public StringBuilder insert(final int offset, final float f) {
		return this.s.insert(offset, f);
	}

	/**
	 * @throws StringIndexOutOfBoundsException {@inheritDoc}
	 * @see String#valueOf(double)
	 * @see #insert(int, String)
	 */
	public StringBuilder insert(final int offset, final double d) {
		return this.s.insert(offset, d);
	}

	/**
	 * @throws NullPointerException {@inheritDoc}
	 */
	public int indexOf(final String str) {
		return this.s.indexOf(str);
	}

	/**
	 * @throws NullPointerException {@inheritDoc}
	 */
	public int indexOf(final String str, final int fromIndex) {
		return this.s.indexOf(str, fromIndex);
	}

	/**
	 * @throws NullPointerException {@inheritDoc}
	 */
	public int lastIndexOf(final String str) {
		return this.s.lastIndexOf(str);
	}

	/**
	 * @throws NullPointerException {@inheritDoc}
	 */
	public int lastIndexOf(final String str, final int fromIndex) {
		return this.s.lastIndexOf(str, fromIndex);
	}

	public StringBuilder reverse() {
		return this.s.reverse();
	}

	@Override
	public String toString() {
		return this.s.toString();
	}

	/**
	 * Inserts java newline tag.
	 *
	 * @return this
	 */
	public StringBuilderHelper ln() {
		s.append("\n");
		return this;
	}

	/**
	 * Inserts java newline and then the String To Be Appended
	 *
	 * @param stringToBeAppended String To Be Appended
	 * @return this
	 */
	public StringBuilderHelper appendln(final String stringToBeAppended) {
		this.append(stringToBeAppended);
		return this;
	}

	/**
	 * Inserts html BR tag.
	 *
	 * @return this
	 */
	public StringBuilderHelper br() {
		this.append("<br/>");
		return this;
	}

	/**
	 * Inserts html BR tag and then the String To Be Appended
	 *
	 * @param stringToBeAppended String To Be Appended
	 * @return this
	 */
	public StringBuilderHelper appendbr(final String stringToBeAppended) {
		br().append(stringToBeAppended);
		return this;
	}
}
