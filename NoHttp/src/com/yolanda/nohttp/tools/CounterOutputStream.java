/*
 * Copyright © YOLANDA. All Rights Reserved
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.yolanda.nohttp.tools;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.atomic.AtomicLong;

/**
 * </br>
 * Created in Dec 17, 2015 2:57:46 PM
 * 
 * @author YOLANDA;
 */
public class CounterOutputStream extends OutputStream {

	private final AtomicLong lenght = new AtomicLong(0L);

	public CounterOutputStream() {
	}

	public void addFile(File file) {
		lenght.addAndGet(file.length());
	}

	public void addStream(InputStream inputStream) {
		long length = getInputStreamLength(inputStream);
		if (length > 0) {
			lenght.addAndGet(length);
		}
	}

	public void write(long count) {
		if (count > 0)
			lenght.addAndGet(count);
	}

	public long get() {
		return lenght.get();
	}

	@Override
	public void write(int oneByte) throws IOException {
		lenght.addAndGet(1);
	}

	@Override
	public void write(byte[] buffer) throws IOException {
		lenght.addAndGet(buffer.length);
	}

	@Override
	public void write(byte[] buffer, int offset, int count) throws IOException {
		lenght.addAndGet(count);
	}

	public static long getInputStreamLength(InputStream inputStream) {
		try {
			if (inputStream instanceof FileInputStream || inputStream instanceof ByteArrayInputStream) {
				return inputStream.available();
			}
		} catch (Throwable e) {
		}
		return 0;
	}
}
