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
package com.dattack.jtoolbox.exceptions;

/**
 * Top-level runtime exception thrown by dattack code.
 *
 * @author cvarela
 * @since 0.1
 * @deprecated Use {@link org.apache.commons.lang.exception.NestableRuntimeException}
 */
@Deprecated
public class DattackNestableRuntimeException extends RuntimeException {

    private static final long serialVersionUID = 8305544418991155317L;

    public DattackNestableRuntimeException(final String message) {
        super(message);
    }

    public DattackNestableRuntimeException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public DattackNestableRuntimeException(final Throwable cause) {
        super(cause);
    }
}
