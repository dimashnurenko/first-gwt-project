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
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.reset;
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
    private Employee              employee;
    @Mock
    private DialogWindowView      dialogWindowView;
    @InjectMocks
    private DialogWindowPresenter presenter;

    @Test
    public void testEmployeeShouldBeAddedInTable() throws Exception {

        when(dialogWindowView.getFirstName()).thenReturn("1");
        when(dialogWindowView.getMiddleName()).thenReturn("2");
        when(dialogWindowView.getLastName()).thenReturn("3");

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Object[] arguments = invocation.getArguments();
                Employee employee = (Employee)arguments[0];
                assertEquals(dialogWindowView.getFirstName(),employee.getFirstName());
                assertEquals(dialogWindowView.getMiddleName(),employee.getMiddleName());
                assertEquals(dialogWindowView.getLastName(),employee.getLastName());
                return null;
            }
        }).when(callBack).onChangeTableOfEmployee((Employee)anyObject());

        presenter.showWindow(callBack);
        reset(dialogWindowView);

        presenter.onClickAddEmployee();

        verify(callBack).onChangeTableOfEmployee((Employee)anyObject());
        verify(dialogWindowView).hideWindow();
    }

    @Test
    public void testDialogWindowShouldDisapear() throws Exception {

        presenter.onClickCancel();

        verify(dialogWindowView).hideWindow();

    }

    @Test
    public void testWindowForAddEmployeeWillBeDisplayed() {

        presenter.showWindow(callBack);

        verify(dialogWindowView).showWindow();

    }

    @Test
    public void testEmployeeShouldBeAddedInTableAfterEdit() {

        Employee empl = new Employee("1","2","3");

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Object[] arguments = invocation.getArguments();
                Employee employee = (Employee)arguments[0];
                assertEquals("1",employee.getFirstName());
                assertEquals("2",employee.getMiddleName());
                assertEquals("3",employee.getLastName());
                return null;
            }
        }).when(callBack).onChangeTableOfEmployee(empl);

        presenter.showWindowForEdit(callBack,empl);
        presenter.onClickAddEmployee();

        InOrder oredr = inOrder(callBack,dialogWindowView);

        oredr.verify(dialogWindowView).showWindow();
        oredr.verify(callBack).onChangeTableOfEmployee((Employee)anyObject());
        oredr.verify(dialogWindowView).hideWindow();

    }
}
