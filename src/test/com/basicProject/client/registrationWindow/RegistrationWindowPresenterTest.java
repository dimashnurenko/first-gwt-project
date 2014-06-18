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
package com.basicProject.client.registrationWindow;

import com.basicProject.client.Localization;
import com.basicProject.client.Styles;
import com.basicProject.client.entity.User;
import com.basicProject.client.eventbus.event.BackButtonEvent;
import com.basicProject.client.eventbus.event.RegistrationEvent;
import com.basicProject.client.eventbus.event.ShowRegisterUsersEvent;
import com.basicProject.client.eventbus.event.ShowTextEvent;
import com.basicProject.client.navigator.MainNavigator;
import com.basicProject.client.registrationWindow.RegistrationWindowPresenter;
import com.basicProject.client.registrationWindow.RegistrationWindowView;
import com.basicProject.client.showRegisterUsers.ShowRegisterUsersView;
import com.google.gwt.event.shared.EventBus;
import com.google.gwtmockito.GwtMockitoTestRunner;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyList;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Dmitry Shnurenko
 */
@RunWith(GwtMockitoTestRunner.class)
public class RegistrationWindowPresenterTest {

    @Mock
    private RegistrationWindowView registrationWindowView;
    @Mock
    private RegistrationEvent      registrationEvent;
    @Mock
    private BackButtonEvent        backButtonEvent;
    @Mock
    private ShowRegisterUsersEvent showRegisterUsersEvent;
    @Mock
    private ShowTextEvent          showTextEvent;
    @Mock
    private ShowRegisterUsersView  registerUsersView;
    @Mock
    private MainNavigator          mainNavigator;
    @Mock
    private EventBus               eventBus;
    @Mock
    private List<User>             users;
    @Mock
    private Styles                 styles;
    @Mock
    private Localization           localization;

    @InjectMocks
    RegistrationWindowPresenter presenter;

    @Test
    public void userShouldBeAddedToDataBase() throws Exception {
        final String LOGIN = "login";
        final String EMAIL = "email";
        final String PASSWORD = "password";

        when(registrationWindowView.getLogin()).thenReturn(LOGIN);
        when(registrationWindowView.getEmail()).thenReturn(EMAIL);
        when(registrationWindowView.getPassword()).thenReturn(PASSWORD);

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                List<User> list = (List<User>)args[0];

                assertEquals(list.size(), 0);

                return null;
            }
        }).when(registerUsersView).showRegisterUsers(anyList());

        presenter.showAllUsersFromDataBase(showRegisterUsersEvent);
        presenter.saveEmployeeToDataBase(registrationEvent);

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                List<User> list = (List<User>)args[0];
                User testUser = list.get(0);

                assertEquals(list.size(), 1);
                assertEquals(testUser.getLogin(), LOGIN);
                assertEquals(testUser.getEmail(), EMAIL);
                assertEquals(testUser.getPassword(), PASSWORD);

                return null;
            }
        }).when(registerUsersView).showRegisterUsers(anyList());

        presenter.showAllUsersFromDataBase(showRegisterUsersEvent);

        verify(registrationWindowView).getLogin();
        verify(registrationWindowView).getEmail();
        verify(registrationWindowView).getPassword();

        verify(registrationWindowView).setLogin(anyString());
        verify(registrationWindowView).setEmail(anyString());
        verify(registrationWindowView).setPassword(anyString());
    }

    @Test
    public void mainWindowShouldBeAppearedAfterRegisterWindow() throws Exception {
        presenter.setMainNavigator(mainNavigator);
        presenter.backToMainPage(backButtonEvent);

        verify(mainNavigator).showMainWindow();
    }

    @Test
    public void registeredUsersFromDataBaseShouldBeShown() throws Exception {
        presenter.showAllUsersFromDataBase(showRegisterUsersEvent);

        verify(registerUsersView).showRegisterUsers(anyList());
        verify(registerUsersView).showWindow();
    }

    @Test
    public void textFromExternalTextResourceShouldBeShown() throws Exception {
        final String TEXT = "Hello external text...";

        /*doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Object []args = invocation.getArguments();
                ResourceCallback callback = (ResourceCallback)args[0];
                doAnswer(new Answer() {
                    @Override
                    public Object answer(InvocationOnMock invocation) throws Throwable {
                        Object [] args = invocation.getArguments();
                        TextResource textResource = (TextResource)args[0];

                        assertEquals(textResource.getText(),TEXT);

                        return null;
                    }
                }).when(callback).onSuccess((TextResource)anyObject());

                callback.onSuccess((TextResource)anyObject());
                return null;
            }
        }).when(styles).getExternalText().getText((ResourceCallback)anyObject());*/

        presenter.showTextFromExternalTextResource(showTextEvent);

        verify(registrationWindowView).setText(anyString());
    }
}
