/**
 * Copyright (C) 2012 Microsoft Corporation
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
package com.microsoft.wake.remote.transport.netty;

import com.microsoft.wake.remote.transport.Link;

import java.util.concurrent.atomic.AtomicBoolean;

final class LinkReference {

  private Link<?> link;
  private AtomicBoolean connectInProgress = new AtomicBoolean(false);

  LinkReference() {
  }

  LinkReference(final Link<?> link) {
    this.link = link;
  }

  synchronized void setLink(final Link<?> link) {
    this.link = link;
  }

  synchronized Link<?> getLink() {
    return this.link;
  }

  AtomicBoolean getConnectInProgress() {
    return this.connectInProgress;
  }
}