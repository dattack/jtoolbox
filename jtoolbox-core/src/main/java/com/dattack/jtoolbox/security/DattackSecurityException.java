/*
 * Copyright (c) 2017, The Dattack team (http://www.dattack.com)
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
package com.dattack.jtoolbox.security;

import com.dattack.jtoolbox.exceptions.DattackNestableException;

/**
 * A marker exception launched when there is a problem related to security management.
 *
 * @author cvarela
 * @since 0.2
 */
public class DattackSecurityException extends DattackNestableException {

    private static final long serialVersionUID = -2035018806388891255L;

    public DattackSecurityException(final Throwable cause) {
        super(cause);
    }
}
