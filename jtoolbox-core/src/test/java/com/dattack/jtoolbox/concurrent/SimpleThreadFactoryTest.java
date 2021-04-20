/*
 * Copyright (c) 2021, The Dattack team (http://www.dattack.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.dattack.jtoolbox.concurrent;

import org.junit.jupiter.api.Test;
import java.util.concurrent.ThreadFactory;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author cvarela
 * @since 0.1
 */
public class SimpleThreadFactoryTest {

    @Test
    public void testMaxPriorityDaemonThread() {

        final Thread.UncaughtExceptionHandler exceptionHandler = new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(final Thread t, final Throwable e) {
                // nothing to do
            }
        };

        final String prefix = "prefix";
        final ThreadGroup threadGroup = new ThreadGroup("ThreadGroup");
        final ThreadFactory factory = new SimpleThreadFactory.ThreadFactoryBuilder()
                .withThreadNamePrefix(prefix) //
                .withThreadGroup(threadGroup) //
                .withDaemon(true) //
                .withPriority(Thread.MAX_PRIORITY) //
                .withUncaughtExceptionHandler(exceptionHandler)
                .build();
        final Thread thread = factory.newThread(() -> { });
        assertTrue(thread.getName().startsWith(prefix), String.format("ThreadName prefix validation (prefix: " +
                "%s, thread name: %s)", prefix, thread.getName()));
        assertEquals(threadGroup, thread.getThreadGroup(), "ThreadGroup validation");
        assertTrue(thread.isDaemon(), "is daemon?");
        assertEquals(Thread.MAX_PRIORITY, thread.getPriority(), "Thread priority");
        assertEquals(exceptionHandler, thread.getUncaughtExceptionHandler(), "UncaughtExceptionHandler");
    }

    @Test
    public void testMinPriorityNonDaemonThread() {

        final Thread.UncaughtExceptionHandler exceptionHandler = new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(final Thread t, final Throwable e) {
                // nothing to do
            }
        };

        final String prefix = "prefix";
        final ThreadGroup threadGroup = new ThreadGroup("ThreadGroup");
        final ThreadFactory factory = new SimpleThreadFactory.ThreadFactoryBuilder()
                .withThreadNamePrefix(prefix) //
                .withThreadGroup(threadGroup) //
                .withDaemon(false) //
                .withPriority(Thread.MIN_PRIORITY) //
                .withUncaughtExceptionHandler(exceptionHandler)
                .build();
        final Thread thread = factory.newThread(() -> { });
        assertTrue(thread.getName().startsWith(prefix), String.format("ThreadName prefix validation (prefix: " +
                "%s, thread name: %s)", prefix, thread.getName()));
        assertEquals(threadGroup, thread.getThreadGroup(), "ThreadGroup validation");
        assertFalse(thread.isDaemon(), "is daemon?");
        assertEquals(Thread.MIN_PRIORITY, thread.getPriority(), "Thread priority");
        assertEquals(exceptionHandler, thread.getUncaughtExceptionHandler(), "UncaughtExceptionHandler");
    }
}
