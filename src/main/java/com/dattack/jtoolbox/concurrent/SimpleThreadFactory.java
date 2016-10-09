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

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.commons.lang3.Validate;

/**
 * @author cvarela
 * @since 0.1
 */
public final class SimpleThreadFactory implements ThreadFactory {

    private static final AtomicInteger POOL_NUMBER = new AtomicInteger(1);

    private final Boolean daemon;
    private final Integer priority;
    private final ThreadGroup threadGroup;
    private final String threadNamePrefix;
    private final AtomicLong threadNumber = new AtomicLong(1);
    private final UncaughtExceptionHandler uncaughtExceptionHandler;

    public static class ThreadFactoryBuilder {

        private Boolean daemon;
        private Integer priority;
        private ThreadGroup threadGroup;
        private String threadNamePrefix;
        private UncaughtExceptionHandler uncaughtExceptionHandler;

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

            Validate.isTrue(value >= Thread.MIN_PRIORITY && value <= Thread.MAX_PRIORITY, String.format(
                    "Thread priority (%s) must be in range [%s..%s]", value, Thread.MIN_PRIORITY, Thread.MAX_PRIORITY));

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
            if (value != null) {
                this.threadNamePrefix = value.trim();
                if (this.threadNamePrefix.length() <= 0) {
                    this.threadNamePrefix = null;
                }
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

            Validate.notNull(value);
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
        if (builder.threadNamePrefix == null) {
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

        if (daemon != null) {
            thread.setDaemon(daemon);
        }

        if (priority != null) {
            thread.setPriority(priority);
        }

        return thread;
    }
}
