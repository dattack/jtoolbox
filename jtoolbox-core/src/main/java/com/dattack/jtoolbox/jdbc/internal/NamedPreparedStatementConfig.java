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
package com.dattack.jtoolbox.jdbc.internal;

import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * A representation of a PreparedStatement using parameter-name instead of parameter-index. The parameter-name is
 * identified by the format <i>:param_name</i>.
 *
 * @author cvarela
 * @since 0.5
 */
public final class NamedPreparedStatementConfig {

    private final transient Map<String, List<Integer>> parameterName2IndexMap;
    private final transient String compiledSql;

    /* default */ NamedPreparedStatementConfig(final Builder builder) {
        this.parameterName2IndexMap = builder.parameterName2IndexMap;
        this.compiledSql = builder.compiledSql;
    }

    /* default */ static String normalizeParameterName(final String parameterName) {
        return StringUtils.lowerCase(parameterName, Locale.getDefault());
    }

    /**
     * Parse the query string containing named parameter markers and result a parse result, which holds the parsed sql
     * (named
     * parameters replaced by standard '?' parameters and an ordered list of the named parameters.
     *
     * @param query Query containing named parameters
     * @return ParseResult
     */
    public static NamedPreparedStatementConfig parse(final String query) {
        return new Builder().withSql(query).build();
    }

    public String getCompiledSql() {
        return compiledSql;
    }

    public Collection<Integer> getParameterIndexes(final String parameterName) {

        final String normalizedName = normalizeParameterName(parameterName);
        final List<Integer> indexes = parameterName2IndexMap.get(normalizedName);

        if (indexes.isEmpty()) {
            throw new IllegalArgumentException(String.format("Parameter not found: '%s'", normalizedName));
        }
        return Collections.unmodifiableList(indexes);
    }

    public boolean hasNamedParameter(final String parameterName) {
        return parameterName2IndexMap.containsKey(normalizeParameterName(parameterName));
    }

    public boolean hasNamedParameters() {
        return !parameterName2IndexMap.isEmpty();
    }

    /**
     * .
     */
    /* default */ static class Builder {

        private final transient Map<String, List<Integer>> parameterName2IndexMap;
        private transient int parameterIndex;
        private transient String compiledSql;

        /* default */ Builder() {
            this.parameterIndex = 1;
            this.parameterName2IndexMap = new HashMap<>();
        }

        public NamedPreparedStatementConfig build() {
            return new NamedPreparedStatementConfig(this);
        }

        /**
         * Parse the query string containing named parameters and result a parse result, which holds the parsed sql
         * (named
         * parameters replaced by standard '?' parameters and an ordered list of the named parameters.
         *
         * @param query Query containing named parameters
         * @return ParseResult
         */
        @SuppressWarnings({"PMD.AvoidLiteralsInIfCondition", "PMD.CyclomaticComplexity"})
        public Builder withSql(final String query) {

            final int length = query.length();
            boolean inSingleQuote = false;
            boolean inDoubleQuote = false;
            boolean inSingleLineComment = false;
            boolean inMultiLineComment = false;

            final StringBuilder compiledSql = new StringBuilder(length);

            int index = 0;
            while (index < length) {
                char currentChar = query.charAt(index);
                if (inSingleQuote) {
                    if (currentChar == '\'') {
                        inSingleQuote = false; // '...'
                    }
                } else if (inDoubleQuote) {
                    if (currentChar == '"') {
                        inDoubleQuote = false; // "..."
                    }
                } else if (inMultiLineComment) {
                    if (currentChar == '*' && index < length - 1 && query.charAt(index + 1) == '/') {
                        inMultiLineComment = false; // end of block comment
                    }
                } else if (inSingleLineComment) {
                    if (currentChar == '\n') {
                        inSingleLineComment = false;
                    }
                } else {
                    if (currentChar == '\'') { // '...
                        inSingleQuote = true;
                    } else if (currentChar == '"') {
                        inDoubleQuote = true;
                    } else if (currentChar == '/' && index < length - 1 && query.charAt(index + 1) == '*') { // /*
                        inMultiLineComment = true;
                    } else if (currentChar == '-' && index < length - 1 && query.charAt(index + 1) == '-') { // --
                        inSingleLineComment = true;
                    } else if (currentChar == ':' //
                            && index + 1 < length //
                            && Character.isJavaIdentifierStart(query.charAt(index + 1))) {
                        int skipCharacters = index + 2;
                        while (skipCharacters < length && Character.isJavaIdentifierPart(
                                query.charAt(skipCharacters))) {
                            skipCharacters++;
                        }
                        final String name = query.substring(index + 1, skipCharacters);
                        addParameter(name);
                        currentChar = '?';
                        index += name.length();
                    }
                }
                compiledSql.append(currentChar);
                index++;
            }

            this.compiledSql = compiledSql.toString();
            return this;
        }

        private void addParameter(final String parameterName) {
            final String normalizedName = normalizeParameterName(parameterName);
            final List<Integer> list = parameterName2IndexMap.computeIfAbsent(normalizedName, k -> new ArrayList<>());
            list.add(parameterIndex++);
        }
    }
}
