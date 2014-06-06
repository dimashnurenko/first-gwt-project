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
package com.basicProject.client.mainWindow;

import com.basicProject.client.dialogWindow.DialogWindowPresenter;
import com.basicProject.client.entity.Employee;
import com.basicProject.client.mvp.CallBack;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


/**
 * @author Dmitry Shnurenko
 */
@RunWith(MockitoJUnitRunner.class)
public class MainWindowPresenterTest {

    @Mock
    private MainWindowView        view;
    @Mock
    private DialogWindowPresenter dialogWindowPresenter;
    @Mock
    private Employee              employee;
    @Mock
    private List<Employee>        list;

    @InjectMocks
    private MainWindowPresenter presenter;

    @Test
    public void testDialogWindowAppearAfterAddButtonClicked() throws Exception {

        presenter.onAddButtonClicked();

        verify(dialogWindowPresenter).showWindow((CallBack)anyObject());
    }

    @Test
    public void testDialogWindowAppearAfterEditButtonClicked() throws Exception {


        final Employee empl = new Employee("1", "2", "3");

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Object[] arguments = invocation.getArguments();
                Employee answerEmployee = (Employee)arguments[1];

                assertEquals(answerEmployee.getFirstName(), empl.getFirstName());
                assertEquals(answerEmployee.getMiddleName(), empl.getMiddleName());
                assertEquals(answerEmployee.getLastName(), empl.getLastName());
                return null;
            }
        }).when(dialogWindowPresenter).showWindowForEdit((CallBack)anyObject(), eq(empl));

        presenter.onSelectedEmployee(empl);
        presenter.onEditButtonClicked();

        verify(dialogWindowPresenter).showWindowForEdit((CallBack)anyObject(), (Employee)anyObject());

    }

    @Test
    public void testRemoveEmployeeFromTable() throws Exception {

        presenter.onRemoveButtonClicked();
        verify(view).setEmployeesList((List)anyObject());
    }

    @Test
    public void testAddEmployeeCallBackInvocation() {

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                CallBack addCallBack = (CallBack)args[0];
                addCallBack.onChangeTableOfEmployee((Employee)anyObject());
                return null;
            }
        }).when(dialogWindowPresenter).showWindow((CallBack)anyObject());

        presenter.onAddButtonClicked();

        verify(view).setEmployeesList((List)anyObject());

    }

    @Test
    public void testEditEmployeeCallBackInvocation() {

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                CallBack editCallBack = (CallBack) args[0];
                editCallBack.onChangeTableOfEmployee((Employee)anyObject());
                return null;
            }
        }).when(dialogWindowPresenter).showWindowForEdit((CallBack)anyObject(),(Employee)anyObject());

        presenter.onEditButtonClicked();

        verify(view).setEmployeesList((List)anyObject());
    }


}
