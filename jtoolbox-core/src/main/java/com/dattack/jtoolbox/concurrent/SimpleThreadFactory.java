/*
 * Copyright (c) 2016, The Dattack team (http://www.dattack.com)
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

import org.apache.commons.lang.StringUtils;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.Objects;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * A simple implementation of {@code ThreadFactory} that creates new threads on demand by assigning a sequential name
 * from a previously defined pattern.
 *
 * @author cvarela
 * @since 0.1
 */
@SuppressWarnings("PMD.LongVariable")
public final class SimpleThreadFactory implements ThreadFactory {

    private static final AtomicInteger POOL_NUMBER = new AtomicInteger(1);

    private final transient Boolean daemon;
    private final transient Integer priority;
    private final transient ThreadGroup threadGroup;
    private final transient String threadNamePrefix;
    private final transient AtomicLong threadNumber = new AtomicLong(1);
    private final transient UncaughtExceptionHandler uncaughtExceptionHandler;

    /**
     * The Builder pattern implementation.
     */
    public static class ThreadFactoryBuilder {

        private transient Boolean daemon;
        private transient Integer priority;
        private transient ThreadGroup threadGroup;
        private transient String threadNamePrefix;
        private transient UncaughtExceptionHandler uncaughtExceptionHandler;

        @SuppressWarnings("PMD.AccessorClassGeneration")
        public ThreadFactory build() {
            return new SimpleThreadFactory(this);
        }

        public ThreadFactoryBuilder withDaemon(final boolean value) {
            this.daemon = value;
            return this;
        }

        /**
         * Sets the priority for new threads.
         *
         * @param value
         *            the new priority
         * @return this builder object
         */
        public ThreadFactoryBuilder withPriority(final int value) {

            if (value < Thread.MIN_PRIORITY || value > Thread.MAX_PRIORITY) {
                throw new IllegalArgumentException(String.format("Thread priority (%s) must be in range [%s..%s]",
                        value, Thread.MIN_PRIORITY, Thread.MAX_PRIORITY));
            }

            this.priority = value;
            return this;
        }

        /**
         * Sets the ThreadGroup to use when a new Thread is instantiate.
         *
         * @param value
         *            the thread group
         * @return this builder object
         */
        public ThreadFactoryBuilder withThreadGroup(final ThreadGroup value) {
            this.threadGroup = value;
            return this;
        }

        /**
         * Sets the prefix to use as name of the threads.
         *
         * @param value
         *            the prefix value
         * @return this builder object
         */
        public ThreadFactoryBuilder withThreadNamePrefix(final String value) {
            if (StringUtils.isNotBlank(value)) {
                this.threadNamePrefix = value.trim();
            }
            return this;
        }

        /**
         * Sets the handler for uncaught exceptions.
         *
         * @param value
         *            the handler
         * @return this builder object
         */
        public ThreadFactoryBuilder withUncaughtExceptionHandler(final UncaughtExceptionHandler value) {
            this.uncaughtExceptionHandler = value;
            return this;
        }
    }

    private static String getDefaultThreadNamePrefix() {
        return String.format("pool-%d-thread", POOL_NUMBER.getAndIncrement());
    }

    private SimpleThreadFactory(final ThreadFactoryBuilder builder) {
        this.daemon = builder.daemon;
        this.threadGroup = builder.threadGroup;
        if (Objects.isNull(builder.threadNamePrefix)) {
            this.threadNamePrefix = getDefaultThreadNamePrefix();
        } else {
            this.threadNamePrefix = builder.threadNamePrefix;
        }
        this.priority = builder.priority;
        this.uncaughtExceptionHandler = builder.uncaughtExceptionHandler;
    }

    private String generateThreadName() {
        return String.format("%s-%d", threadNamePrefix, threadNumber.getAndIncrement());
    }

    @Override
    public Thread newThread(final Runnable target) {

        final Thread thread = new Thread(threadGroup, target, generateThreadName(), 0);
        thread.setUncaughtExceptionHandler(uncaughtExceptionHandler);

        if (Objects.nonNull(daemon)) {
            thread.setDaemon(daemon);
        }

        if (Objects.nonNull(priority)) {
            thread.setPriority(priority);
        }

        return thread;
    }
}
