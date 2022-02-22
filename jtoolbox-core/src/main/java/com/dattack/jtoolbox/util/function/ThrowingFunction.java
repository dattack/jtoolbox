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
 * Represents a function that accepts one argument and produces a result. Unlike
 * {@link java.util.function.Function} its functional method can throw an exception.
 *
 * @param <T> the type of the input to the operation
 * @param <E> the exception thrown
 * @author cvarela
 * @see java.util.function.Function
 * @since 0.6
 */
@FunctionalInterface
public interface ThrowingFunction<T, R, E extends Exception> {

    /**
     * Applies this function to the given argument.
     *
     * @param t the function argument
     * @return the function result
     * @throws E the exception thrown when an error occurs
     */
    R apply(T t) throws E;
}
