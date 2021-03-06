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
package com.microsoft.wake.examples.p2p;

/**
 * The pull side of the interface: Clients implement this and register it with
 * the PullToPush class.
 *
 * @param <T> the type of the event
 */
public interface EventSource<T> {

    /**
     *
     * @return a event or null if no more messages are available.
     */
    public T getNext();
}
