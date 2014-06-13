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
package com.basicProject.client.dialogWindow;

import com.basicProject.client.entity.Employee;
import com.basicProject.client.mvp.CallBack;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Dmitry Shnurenko
 */
@RunWith(MockitoJUnitRunner.class)
public class DialogWindowPresenterTest {

    @Mock
    private CallBack              callBack;
    @Mock
    private DialogWindowView      dialogWindowView;
    @InjectMocks
    private DialogWindowPresenter presenter;

    @Test
    public void employeeShouldBeAddedInTable() throws Exception {

        final String FIRST_NAME = "1";
        final String MIDDLE_NAME = "2";
        final String LAST_NAME = "3";

        when(dialogWindowView.getFirstName()).thenReturn(FIRST_NAME);
        when(dialogWindowView.getMiddleName()).thenReturn(MIDDLE_NAME);
        when(dialogWindowView.getLastName()).thenReturn(LAST_NAME);

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                Employee testEmployee = (Employee)args[0];

                assertEquals(testEmployee.getFirstName(), FIRST_NAME);
                assertEquals(testEmployee.getMiddleName(), MIDDLE_NAME);
                assertEquals(testEmployee.getLastName(), LAST_NAME);

                return null;
            }
        }).when(callBack).onChangeTableOfEmployee((Employee)anyObject());

        presenter.showWindow(callBack);
        presenter.onClickAddEmployee();

        verify(dialogWindowView).getFirstName();
        verify(dialogWindowView).getMiddleName();
        verify(dialogWindowView).getLastName();

        verify(callBack).onChangeTableOfEmployee((Employee)anyObject());

        verify(dialogWindowView).hideWindow();
    }

    @Test
    public void dialogWindowShouldBeDisappeared() throws Exception {

        presenter.onClickCancel();

        verify(dialogWindowView).hideWindow();
    }

    @Test
    public void dialogWindowForAddEmployeeShouldBeAppeared() throws Exception {

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                String firstName = (String)args[0];

                assertEquals(firstName, "");

                return null;
            }
        }).when(dialogWindowView).setFirstName(anyString());

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                String middleName = (String)args[0];

                assertEquals(middleName, "");

                return null;
            }
        }).when(dialogWindowView).setMiddleName(anyString());

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                String lastName = (String)args[0];

                assertEquals(lastName, "");

                return null;
            }
        }).when(dialogWindowView).setLastName(anyString());

        presenter.showWindow(callBack);

        verify(dialogWindowView).showWindow();

        verify(dialogWindowView).setFirstName(anyString());
        verify(dialogWindowView).setMiddleName(anyString());
        verify(dialogWindowView).setLastName(anyString());

    }

    @Test
    public void dialogWindowForEditEmployeeShouldBeAppeared() throws Exception {

        Employee testEmployee = new Employee("a", "b", "c");

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                String firstName = (String)args[0];

                assertEquals(firstName, "a");

                return null;
            }
        }).when(dialogWindowView).setFirstName(anyString());

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                String middleName = (String)args[0];

                assertEquals(middleName, "b");

                return null;
            }
        }).when(dialogWindowView).setMiddleName(anyString());

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                String lastName = (String)args[0];

                assertEquals(lastName, "c");

                return null;
            }
        }).when(dialogWindowView).setLastName(anyString());

        presenter.showWindowForEdit((CallBack)anyObject(), testEmployee);

        verify(dialogWindowView).setFirstName(anyString());
        verify(dialogWindowView).setMiddleName(anyString());
        verify(dialogWindowView).setLastName(anyString());

        verify(dialogWindowView).showWindow();

    }
}
