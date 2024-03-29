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
package com.dattack.jtoolbox.commons.configuration;

import org.apache.commons.configuration.AbstractConfiguration;
import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.EnvironmentConfiguration;
import org.apache.commons.configuration.PropertyConverter;
import org.apache.commons.configuration.SystemConfiguration;
import org.apache.commons.lang.ObjectUtils;
import java.util.Objects;

/**
 * Provides utility methods for manage to <code>Configuration</code> instances.
 *
 * @author cvarela
 * @since 0.1
 */
public final class ConfigurationUtil {

    /**
     * Create a <code>Configuration</code> based on the environment variables and system properties.
     *
     * @return a <code>Configuration</code> based on the environment variables and system properties
     */
    public static CompositeConfiguration createEnvSystemConfiguration() {
        final CompositeConfiguration configuration = new CompositeConfiguration();
        configuration.addConfiguration(new SystemConfiguration());
        configuration.addConfiguration(new EnvironmentConfiguration());
        return configuration;
    }

    /**
     * Interpolates the specified value.
     *
     * @param value
     *            the value to be interpolated
     * @param configuration
     *            the configuration object to use
     * @return the interpolated value
     */
    public static String interpolate(final Object value, final AbstractConfiguration configuration) {
        return interpolate(ObjectUtils.toString(value), configuration);
    }

    /**
     * Interpolates the specified value.
     *
     * @param value
     *            the value to be interpolated
     * @param configuration
     *            the configuration object
     * @return the interpolated value
     * @see PropertyConverter#interpolate
     */
    public static String interpolate(final String value, final AbstractConfiguration configuration) {

        String result;
        if (Objects.isNull(value) || Objects.isNull(configuration)) {
            result = value;
        } else {
            result = PropertyConverter.interpolate(value, configuration).toString();
        }
        return result;
    }

    private ConfigurationUtil() {
        // static class
    }
}
