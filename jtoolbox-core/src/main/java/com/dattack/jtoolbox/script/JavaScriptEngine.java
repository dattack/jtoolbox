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
package com.dattack.jtoolbox.script;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * Utility class to execute JavaScript code from Java.
 *
 * @author cvarela
 * @since 0.1
 */
@SuppressWarnings("PMD.ClassNamingConventions")
public final class JavaScriptEngine {

    private static final String JAVA_SCRIPT_ENGINE_NAME = "js";

    /**
     * Executes the specified JavaScript.
     *
     * @param script
     *            the JavaScript source to be evaluated
     * @return the value returned from the execution of the script
     * @throws ScriptException
     *             if an error occurs in script execution
     */
    public static Object eval(final String script) throws ScriptException {

        return eval(script, null);
    }

    /**
     * Executes the specified JavaScript.
     *
     * @param script
     *            the JavaScript source to be evaluated
     * @param params
     *            a key/value pairs that can be used as variables into the script
     * @return the value returned from the execution of the script
     * @throws ScriptException
     *             if an error occurs in script execution
     */
    public static Object eval(final String script, final Map<Object, Object> params) throws ScriptException {

        final ScriptEngineManager manager = new ScriptEngineManager();
        final ScriptEngine engine = manager.getEngineByName(JAVA_SCRIPT_ENGINE_NAME);
        if (params != null) {
            for (final Entry<Object, Object> entry : params.entrySet()) {
                engine.put(Objects.toString(entry.getKey()), entry.getValue());
            }
        }

        return engine.eval(script);
    }

    /**
     * Executes the specified JavaScript.
     *
     * @param script
     *            the JavaScript source to be evaluated
     * @return the value returned from the execution of the script
     * @throws ScriptException
     *             if error occurs in script execution
     */
    public static boolean evalBoolean(final String script) throws ScriptException {
        return evalBoolean(script, null);
    }

    /**
     * Executes the specified JavaScript.
     *
     * @param script
     *            the JavaScript source to be evaluated
     * @param params
     *            a key/value pairs that can be used as variables into the script
     * @return the value returned from the execution of the script
     * @throws ScriptException
     *             if an error occurs in script execution
     */
    public static boolean evalBoolean(final String script, final Map<Object, Object> params) throws ScriptException {

        final Object obj = eval(script, params);
        if (Objects.isNull(obj)) {
            throw new ScriptException("Unable to cast script result from 'null' to boolean");
        }

        if (obj instanceof Boolean) {
            return (Boolean) obj;
        }

        throw new ScriptException(
                String.format("Unable to cast script result from %s to %s", obj.getClass(), Boolean.class));
    }

    /**
     * Executes the specified JavaScript.
     *
     * @param script
     *            the JavaScript source to be evaluated
     * @return the value returned from the execution of the script
     * @throws ScriptException
     *             if an error occurs in script execution
     */
    public static Number evalNumber(final String script) throws ScriptException {
        return evalNumber(script, null);
    }

    /**
     * Executes the specified JavaScript.
     *
     * @param script
     *            the JavaScript source to be evaluated
     * @param params
     *            a key/value pairs that can be used as variables into the script
     * @return the value returned from the execution of the script
     * @throws ScriptException
     *             if an error occurs in script execution
     */
    public static Number evalNumber(final String script, final Map<Object, Object> params) throws ScriptException {

        final Object obj = eval(script, params);

        if (Objects.isNull(obj) || obj instanceof Number) {
            return (Number) obj;
        }

        throw new ScriptException(
                String.format("Unable to cast script result from %s to %s", obj.getClass(), Number.class));
    }

    private JavaScriptEngine() {
        // utility class
    }
}
