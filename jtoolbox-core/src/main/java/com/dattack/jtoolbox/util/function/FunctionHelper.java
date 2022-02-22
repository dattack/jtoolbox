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
package com.dattack.jtoolbox.util.function;

import com.dattack.jtoolbox.util.CollectionUtils;

import java.util.Collection;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Helper class with utility methods to handle {@link java.util.function.Consumer} and
 * {@link java.util.function.Function} objects.
 *
 * @author cvarela
 * @since 0.6
 */
@SuppressWarnings("unused")
public final class FunctionHelper {

    private FunctionHelper() {
        // helper class
    }

    /**
     * Executes a ThrowingConsumer handling the specified exception.
     *
     * @param throwingConsumer the operation to be executed
     * @param <T>              the type of the input to the operation
     * @return a Consumer
     */
    @SuppressWarnings({
        "PMD.AvoidCatchingGenericException", "PMD.AvoidThrowingRawExceptionTypes", //
        "PMD.AvoidRethrowingException"
    })
    public static <T> Consumer<T> throwingConsumerWrapper(ThrowingConsumer<T, Exception> throwingConsumer) {
        return i -> {
            try {
                throwingConsumer.accept(i);
            } catch (final RuntimeException e) {
                throw e;
            } catch (final Exception e) {
                throw new RuntimeException(e);
            }
        };
    }

    /**
     * Executes a ThrowingFunction handling the specified exception.
     *
     * @param throwingFunction the function to be executed
     * @param <T> the type of the input to the function
     * @param <R> the type of the result of the function
     * @return a Consumer
     */
    @SuppressWarnings({
        "PMD.AvoidCatchingGenericException", "PMD.AvoidThrowingRawExceptionTypes", //
        "PMD.AvoidRethrowingException"
    })
    public static <T, R> Function<T, R> throwingFunctionWrapper(ThrowingFunction<T, R, Exception> throwingFunction) {
        return i -> {
            R result;
            try {
                result = throwingFunction.apply(i);
            } catch (final RuntimeException e) {
                throw e;
            } catch (final Exception e) {
                throw new RuntimeException(e);
            }
            return result;
        };
    }

    /**
     * Executes the specified function when the provided value is not null.
     *
     * @param value    the value to check. It can be null.
     * @param function the function to execute
     * @param <T>      generic data type
     */
    public static <T> void executeIfNotNull(T value, Consumer<T> function) {
        if (value != null) {
            function.accept(value);
        }
    }

    /**
     * Executes the specified function when the provided collection is not empty (null or zero elements).
     *
     * @param value    the value to check. It can be null
     * @param function the function to execute
     * @param <T>      generic data type
     */
    public static <T extends Collection<?>> void executeIfNotEmpty(T value, Consumer<T> function) {
        if (CollectionUtils.isNotEmpty(value)) {
            function.accept(value);
        }
    }
}
