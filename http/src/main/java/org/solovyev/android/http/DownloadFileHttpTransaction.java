/*
 * Copyright 2013 serso aka se.solovyev
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Contact details
 *
 * Email: se.solovyev@gmail.com
 * Site:  http://se.solovyev.org
 */

package org.solovyev.android.http;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.solovyev.common.Converter;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

public class DownloadFileHttpTransaction<R> extends AbstractHttpTransaction<R> {

	@Nonnull
	private final Converter<InputStream, R> fileConverter;

	public DownloadFileHttpTransaction(@Nonnull String uri, @Nonnull HttpMethod httpMethod, @Nonnull Converter<InputStream, R> fileConverter) {
		super(uri, httpMethod);
		this.fileConverter = fileConverter;
	}

	@Nonnull
	@Override
	public List<NameValuePair> getRequestParameters() {
		return Collections.emptyList();
	}

	@Override
	public R getResponse(@Nonnull HttpResponse response) {
		try {
			return fileConverter.convert(response.getEntity().getContent());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
