/**
 * Copyright (C) 2014 Microsoft Corporation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.microsoft.wake.remote.transport;

import java.io.IOException;
import java.net.SocketAddress;

/**
 * Link between two end physical endpoints.
 *
 * @param <T> type of the message.
 */
public interface Link<T> extends LinkListener<T> {

  /**
   * Gets its local address.
   *
   * @return a local socket address.
   */
  SocketAddress getLocalAddress();

  /**
   * Gets its remote address.
   *
   * @return a remote socket address.
   */
  SocketAddress getRemoteAddress();

  /**
   * Writes the value to this link.
   *
   * @param value the data value.
   * @throws IOException
   */
  void write(T value) throws IOException;
}
