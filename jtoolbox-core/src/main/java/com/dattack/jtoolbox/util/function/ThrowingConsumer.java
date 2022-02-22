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

/**
 * Represents an operation that accepts a single input argument and returns no result. Unlike
 * {@link java.util.function.Consumer} its functional method can throw an exception.
 *
 * @param <T> the type of the input to the operation
 * @param <E> the exception thrown
 * @author cvarela
 * @see java.util.function.Consumer
 * @since 0.6
 */
@FunctionalInterface
public interface ThrowingConsumer<T, E extends Exception> {

    /**
     * Performs this operation on the given argument.
     *
     * @param t the input argument
     * @throws E the exception thrown when an error occurs
     */
    void accept(T t) throws E;
}
