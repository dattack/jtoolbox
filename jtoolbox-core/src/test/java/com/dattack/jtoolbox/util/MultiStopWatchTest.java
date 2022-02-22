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

import org.junit.jupiter.api.Test;

import static com.dattack.junit.AssertionsExt.assertTrue;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test for {@link com.dattack.jtoolbox.util.MultiStopWatch}.
 *
 * @author cvarela
 * @since 0.6
 */
@SuppressWarnings("PMD.JUnitTestContainsTooManyAsserts")
/* default */ class MultiStopWatchTest {

    private static final String EVENT_1 = "event-1";
    private static final String EVENT_2 = "event-2";

    @Test /* default */ void emptyStopWatch() {
        MultiStopWatch stopWatch = new MultiStopWatch();
        long elapsedTime = stopWatch.getElapsedTimeInNanos();
        assertEquals(0, elapsedTime, "Invalid elapsed time for a stopwatch without events");
    }

    @Test /* default */ void eventWithoutStart() throws InterruptedException {
        MultiStopWatch stopWatch = new MultiStopWatch();
        doWait(1_000);
        stopWatch.stop(EVENT_1);
        doWait(1_000);
        long elapsedTime = stopWatch.getElapsedTimeInMillis();

        assertAll("elapsed time",
                () -> assertTrue(elapsedTime >= 1_000, "elapsed time should be at least 1000ms."),
                () -> assertTrue(elapsedTime < 2_000, "elapsed time should be less than 2000ms.")
        );
    }

    @Test /* default */ void eventWithoutStop() throws InterruptedException {
        MultiStopWatch stopWatch = new MultiStopWatch();
        doWait(1_000);
        stopWatch.start(EVENT_1);
        doWait(1_000);
        assertTrue(stopWatch.getElapsedTimeInMillis() >= 2_000, "elapsed time should be at least 2000ms.");
    }

    @Test /* default */ void startAndStop() throws InterruptedException {
        MultiStopWatch stopWatch = new MultiStopWatch();

        for (int i = 0; i < 3; i++) { // 1000 + 1100 + 1200 = 3300
            stopWatch.start(EVENT_1);
            doWait(1_000 + 100 * i);
            stopWatch.stop(EVENT_1);
        }

        stopWatch.start(EVENT_2);
        doWait(300);
        stopWatch.stop(EVENT_2);

        doWait(500);

        stopWatch.start(EVENT_1);
        doWait(100);
        stopWatch.stop(EVENT_1);

        long elapsedTimeEvent1 = stopWatch.getElapsedTimeInMillis(EVENT_1);
        long elapsedTimeEvent2 = stopWatch.getElapsedTimeInMillis(EVENT_2);
        long elapsedTime = stopWatch.getElapsedTimeInMillis();

        assertAll("elapsed time",
                () -> assertTrue(elapsedTime > 4_200, "elapsed time should be at least 4200ms."),
                () -> assertAll(EVENT_1,
                        () -> assertTrue(elapsedTimeEvent1 >= 3_400, "elapsed time should be at least 3400ms."),
                        () -> assertTrue(elapsedTimeEvent1 < 3_600, "elapsed time should be less than 3600ms.")
                ),
                () -> assertAll(EVENT_2,
                        () -> assertTrue(elapsedTimeEvent2 < 400, "elapsed time should be less than 400ms.")
                )
        );
    }

    private void doWait(long timeout) throws InterruptedException {
        synchronized (this) {
            wait(timeout);
        }
    }
}
