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
package com.basicProject.client.navigator;

import com.basicProject.client.mainWindow.MainWindowPresenter;
import com.basicProject.client.registrationWindow.RegistrationWindowPresenter;
import com.google.gwt.user.client.ui.SimpleLayoutPanel;
import com.google.gwtmockito.GwtMockitoTestRunner;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;

/**
 * @author Dmitry Shnurenko
 */
@RunWith(GwtMockitoTestRunner.class)
public class MainNavigatorTest {

    @Mock
    private RegistrationWindowPresenter registrationWindowPresenter;
    @Mock
    private MainWindowPresenter         mainWindowPresenter;
    @Mock
    private SimpleLayoutPanel           simpleLayoutPanel;

    @InjectMocks
    private MainNavigator navigator;

    @Test
    public void mainWindowShouldBeAppeared() throws Exception {
        navigator.go(simpleLayoutPanel);

        reset(mainWindowPresenter);

        navigator.showMainWindow();

        verify(mainWindowPresenter).go((SimpleLayoutPanel)anyObject());
    }

    @Test
    public void registerWindowShouldBeAppeared() throws Exception {
        navigator.go(simpleLayoutPanel);

        reset(mainWindowPresenter);

        navigator.showRegistrationWindow();

        verify(registrationWindowPresenter).go((SimpleLayoutPanel)anyObject());
    }

    @Test
    public void mainWindowShouldBeAppearedWithFirstStartProgramme() throws Exception {
        navigator.go(simpleLayoutPanel);

        verify(mainWindowPresenter).go(simpleLayoutPanel);
    }
}
