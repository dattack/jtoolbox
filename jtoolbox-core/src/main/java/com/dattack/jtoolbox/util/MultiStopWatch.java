/*
 * Copyright (c) 2022, The Dattack team (http://www.dattack.com)
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
package com.dattack.jtoolbox.util;

import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

/**
 * A basic implementation of a stopwatch to measure the timing of several related events.
 *
 * @author cvarela
 * @since 0.6
 */
public class MultiStopWatch {

    private final transient List<Event> events;
    private transient long startTimeInNanos;
    private transient long lastStopTimeInNanos;
    private transient int activeEvents;

    public MultiStopWatch() {
        this.events = new ArrayList<>();
        this.startTimeInNanos = System.nanoTime();
        this.activeEvents = 0;
    }

    public long getElapsedTimeInMillis() {
        return getTimeInMillis(getElapsedTimeInNanos());
    }

    public long getElapsedTimeInMillis(String eventName) {
        return getTimeInMillis(getElapsedTimeInNanos(eventName));
    }

    public long getElapsedTimeInNanos(String eventName) {
        AtomicLong value = new AtomicLong(-1);
        findEvent(eventName).ifPresent(event -> value.set(event.getElapsedTimeInNanos()));
        return value.longValue();
    }

    public long getElapsedTimeInNanos() {

        if (events.isEmpty()) {
            return 0;
        }

        long now = lastStopTimeInNanos;
        if (activeEvents > 0) {
            now = System.nanoTime();
        }
        return now - startTimeInNanos;
    }

    /**
     * Clears all events registered so far.
     */
    public void reset() {
        this.startTimeInNanos = System.nanoTime();
        this.events.clear();
        this.activeEvents = 0;
    }

    /**
     * Registers the start of an event.
     *
     * @param name the name of the event
     */
    public void start(String name) {

        Optional<Event> event = findEvent(name);
        if (event.isPresent()) {
            event.get().start();
        } else {
            events.add(new Event(name).start());
        }
        activeEvents++;
    }

    /**
     * Registers the end of an event.
     *
     * @param name the name of the event
     */
    public void stop(String name) {

        Optional<Event> event = findEvent(name);
        if (event.isPresent()) {
            if (event.get().isActive()) {
                activeEvents--;
            }
            event.get().stop();
        } else {
            events.add(new Event(name, startTimeInNanos).stop());
        }
        this.lastStopTimeInNanos = System.nanoTime();
    }

    @Override
    public String toString() {

        StringBuilder buffer = new StringBuilder();
        buffer.append(String.format("%,d ms.[ ", getElapsedTimeInMillis()));
        for (Event event : events) {
            buffer.append(event.getName()) //
                    .append(String.format(": %,d ms. ", getTimeInMillis(event.getElapsedTimeInNanos())));
        }
        buffer.append(']');
        return buffer.toString();
    }

    /**
     * A timed event.
     */
    private static final class Event {

        private final String name;
        private transient long startTimeInNanos;
        private transient long elapsedTimeInNanos;
        private transient boolean active;

        /**
         * Constructor.
         *
         * @param name the name of the event
         */
        /* default */ Event(String name) {
            this(name, System.nanoTime());
        }

        /**
         * Constructor.
         *
         * @param name             the name of the event
         * @param startTimeInNanos the start time in nanoseconds
         */
        /* default */ Event(String name, long startTimeInNanos) {
            this.name = name;
            this.elapsedTimeInNanos = 0;
            start(startTimeInNanos);
        }

        /* default */ boolean isActive() {
            return active;
        }

        /* default */ long getElapsedTimeInNanos() {
            long delta = 0;
            if (active) {
                delta = System.nanoTime() - startTimeInNanos;
            }
            return elapsedTimeInNanos + delta;
        }

        public String getName() {
            return name;
        }

        /* default */ Event stop() {
            long now = System.nanoTime();
            this.elapsedTimeInNanos += now - startTimeInNanos;
            this.startTimeInNanos = now;
            this.active = false;
            return this;
        }

        /* default */ Event start() {
            return start(System.nanoTime());
        }

        /* default */ Event start(long startTimeInNanos) {
            this.startTimeInNanos = startTimeInNanos;
            this.active = true;
            return this;
        }
    }

    private Optional<Event> findEvent(String name) {
        return events.stream().filter(event -> StringUtils.equalsIgnoreCase(name, event.getName())).findFirst();
    }

    private long getTimeInMillis(long nanos) {
        return nanos / 1_000_000;
    }
}
