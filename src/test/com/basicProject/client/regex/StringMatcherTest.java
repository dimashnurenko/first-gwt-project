/*
 * Copyright 2014 Codenvy, S.A.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.basicProject.client.regex;

import com.google.gwtmockito.GwtMockitoTestRunner;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Dmitry Shnurenko
 */
@RunWith(GwtMockitoTestRunner.class)
public class StringMatcherTest {

    @Test
    public void loginRegexShouldBeTested() throws Exception {
        //"^[a-z0-9_-]{3,12}$"
        final String CORRECT_LOGIN = "log_in-38";

        final String SHORT_LOGIN = "lo";
        final String LONG_LOGIN = "asdfghjklpoiuytr";
        final String START_WITH_UPPER = "A34ghg";
        final String HAVE_ANY_SYMBOLS = "gfkgk:, ";

        assertTrue(StringMatcher.match(Regex.LOGIN, CORRECT_LOGIN));

        assertFalse(StringMatcher.match(Regex.LOGIN, SHORT_LOGIN));
        assertFalse(StringMatcher.match(Regex.LOGIN, LONG_LOGIN));
        assertFalse(StringMatcher.match(Regex.LOGIN, START_WITH_UPPER));
        assertFalse(StringMatcher.match(Regex.LOGIN, HAVE_ANY_SYMBOLS));
    }

    @Test
    public void emailRegexShouldBeTested() throws Exception {
        //"^([a-z0-9_\\.-]+)@([\\da-z\\.-]+)\\.([a-z\\.]{2,6})$"
        final String CORRECT_EMAIL = "email@com.ua";

        final String INCORRECT_ONE = "email.com.ua";
        final String INCORRECT_TWO = "email@com";
        final String INCORRECT_THREE = "enmt.com@com.ua-ua";

        assertTrue(StringMatcher.match(Regex.EMAIL, CORRECT_EMAIL));

        assertFalse(StringMatcher.match(Regex.EMAIL, INCORRECT_ONE));
        assertFalse(StringMatcher.match(Regex.EMAIL, INCORRECT_TWO));
        assertFalse(StringMatcher.match(Regex.EMAIL, INCORRECT_THREE));
    }

    @Test
    public void passwordRegexShouldBeTested() throws Exception {
        //"^[a-z0-9_-]{6,18}$"
        final String CORRECT_PASSWORD = "passw_25-hh";

        final String SHORT_PASSWORD = "dfe4";
        final String LONG_PASSWORD = "dhtidlmt_f-258fg4gf";
        final String HAVE_UPPER = "AsdsDgg";
        final String HAVE_ANY_SYMBOLS = "dfdf:ddd,";

        assertTrue(StringMatcher.match(Regex.PASSWORD, CORRECT_PASSWORD));

        assertFalse(StringMatcher.match(Regex.PASSWORD, SHORT_PASSWORD));
        assertFalse(StringMatcher.match(Regex.PASSWORD, LONG_PASSWORD));
        assertFalse(StringMatcher.match(Regex.PASSWORD, HAVE_UPPER));
        assertFalse(StringMatcher.match(Regex.PASSWORD, HAVE_ANY_SYMBOLS));
    }
}
