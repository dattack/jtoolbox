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

import org.apache.commons.lang.exception.NestableException;

/**
 * Exception thrown when it is not possible to parse a data.
 *
 * @author cvarela
 * @since 0.1
 */
public class DattackParserException extends NestableException {

    private static final long serialVersionUID = 4118277771421918250L;

    public DattackParserException(final String message) {
        super(message);
    }

    public DattackParserException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public DattackParserException(final Throwable cause) {
        super(cause);
    }
}
