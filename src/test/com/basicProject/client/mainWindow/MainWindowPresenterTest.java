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

import com.basicProject.client.Localization;
import com.basicProject.client.dialogWindow.DialogWindowPresenter;
import com.basicProject.client.entity.Employee;
import com.basicProject.client.entity.Note;
import com.basicProject.client.mvp.CallBack;
import com.basicProject.client.mvp.CallBackForNote;
import com.basicProject.client.noteDialogWindow.NoteDialogWindowPresenter;
import com.google.gwt.user.client.ui.SimpleLayoutPanel;
import com.google.gwtmockito.GwtMockitoTestRunner;
import com.googlecode.gwt.test.GwtModule;
import com.googlecode.gwt.test.GwtTestWithMockito;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyList;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


/**
 * @author Dmitry Shnurenko
 */
@GwtModule("com.basicProject.BasicProject")
//@RunWith(GwtMockitoTestRunner.class)
public class MainWindowPresenterTest extends GwtTestWithMockito {

    @Mock
    private MainWindowView            view;
    @Mock
    private DialogWindowPresenter     dialogWindowPresenter;
    @Mock
    private NoteDialogWindowPresenter noteDialogWindowPresenter;
    @Mock
    private Localization              localization;
    @Mock
    private SimpleLayoutPanel         widget;
    @InjectMocks
    private MainWindowPresenter       presenter;

    private Employee testEmployee;

    @Before
    public void setUp() {
        this.testEmployee = new Employee("a", "a", "a");
    }

    @Test
    public void dialogWindowShouldBeAppearAfterAddButtonClicked() throws Exception {
        presenter.onAddButtonClicked();

        verify(dialogWindowPresenter).showWindow((CallBack)anyObject());
    }

    @Test
    public void dialogWindowShouldBeAppearAfterEditButtonClicked() throws Exception {
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                Employee actual = (Employee)args[1];

                assertEquals(testEmployee, actual);

                return null;
            }
        }).when(dialogWindowPresenter).showWindowForEdit((CallBack)anyObject(), (Employee)anyObject());

        presenter.onSelectedEmployee(testEmployee);
        presenter.onEditButtonClicked();

        verify(dialogWindowPresenter).showWindowForEdit((CallBack)anyObject(), (Employee)anyObject());
    }

    @Test
    public void employeeShouldBeRemovedFromTable() throws Exception {
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                List<Employee> testList = (List<Employee>)args[0];

                assertEquals(1, testList.size());

                return null;
            }
        }).when(view).setEmployeesList(anyList());

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                CallBack addTestCallback = (CallBack)args[0];
                addTestCallback.onChangeTableOfEmployee(testEmployee);

                return null;
            }
        }).when(dialogWindowPresenter).showWindow((CallBack)anyObject());

        presenter.onAddButtonClicked();

        reset(view);
        reset(dialogWindowPresenter);

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                List<Employee> testList = (List<Employee>)args[0];

                assertEquals(0, testList.size());

                return null;
            }
        }).when(view).setEmployeesList(anyList());

        presenter.onSelectedEmployee(testEmployee);

        presenter.onRemoveButtonClicked();

        verify(view).setEmployeesList(anyList());
    }

    @Test
    public void employeeShouldBeAddedInTableUsingAddCallBack() throws Exception {
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                CallBack addCallBack = (CallBack)args[0];
                addCallBack.onChangeTableOfEmployee(testEmployee);
                return null;
            }
        }).when(dialogWindowPresenter).showWindow((CallBack)anyObject());

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                List<Employee> list = (List)args[0];

                assertEquals(list.size(), 1);
                assertEquals(list.get(0), testEmployee);

                return null;
            }
        }).when(view).setEmployeesList((List)anyObject());

        presenter.onAddButtonClicked();

        verify(dialogWindowPresenter).showWindow((CallBack)anyObject());
        verify(view).setEmployeesList((List)anyObject());
    }

    @Test
    public void employeeShouldBeChangedInTableUsingEditCallBack() throws Exception {
        final Employee currentEmployee = new Employee("a", "a", "a");

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                CallBack addCallBack = (CallBack)args[0];
                addCallBack.onChangeTableOfEmployee(testEmployee);
                return null;
            }
        }).when(dialogWindowPresenter).showWindow((CallBack)anyObject());

        presenter.onAddButtonClicked();

        reset(dialogWindowPresenter);

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                CallBack editCallBack = (CallBack)args[0];
                editCallBack.onChangeTableOfEmployee(currentEmployee);
                return null;
            }
        }).when(dialogWindowPresenter).showWindowForEdit((CallBack)anyObject(), (Employee)anyObject());

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                List<Employee> testList = (List<Employee>)args[0];

                assertEquals(currentEmployee, testList.get(0));

                return null;
            }
        }).when(view).setEmployeesList(anyList());

        presenter.onSelectedEmployee(testEmployee);
        presenter.onEditButtonClicked();

        verify(dialogWindowPresenter).showWindowForEdit((CallBack)anyObject(), (Employee)anyObject());
        verify(view).setEmployeesList(anyList());
    }

    @Test
    public void errorMessageShouldNotBeAppearUsingAddCallBack() throws Exception {
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                CallBack addCallBack = (CallBack)args[0];
                addCallBack.onChangeTableOfEmployee((Employee)anyObject());
                return null;
            }
        }).when(dialogWindowPresenter).showWindow((CallBack)anyObject());

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                String errorString = (String)args[0];

                assertEquals("", errorString);

                return null;
            }
        }).when(view).setExceptionMessage((String)anyObject());

        presenter.onAddButtonClicked();

        verify(view).setExceptionMessage((String)anyObject());
        verify(view).setEmployeesList((List<Employee>)anyObject());
        verify(dialogWindowPresenter).showWindow((CallBack)anyObject());
    }

    @Test
    public void errorMessageShouldBeAppearUsingAddCallBack() throws Exception {
        when(localization.error()).thenReturn("error");

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                CallBack addTestCallBack = (CallBack)args[0];
                addTestCallBack.onChangeTableOfEmployee(testEmployee);
                return null;
            }
        }).when(dialogWindowPresenter).showWindow((CallBack)anyObject());

        presenter.onAddButtonClicked();

        reset(view);
        reset(dialogWindowPresenter);

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                CallBack editTestCallBack = (CallBack)args[0];
                editTestCallBack.onChangeTableOfEmployee(testEmployee);
                return null;
            }
        }).when(dialogWindowPresenter).showWindow((CallBack)anyObject());

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                String testErrorString = (String)args[0];

                assertEquals(testErrorString, "error");

                return null;
            }
        }).when(view).setExceptionMessage(anyString());

        presenter.onAddButtonClicked();

        verify(dialogWindowPresenter).showWindow((CallBack)anyObject());
        verify(view).setExceptionMessage(anyString());
    }

    @Test
    public void errorMessageShouldNotBeAppearUsingEditCallBack() throws Exception {
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                CallBack editCallBack = (CallBack)args[0];
                editCallBack.onChangeTableOfEmployee(testEmployee);
                return null;
            }
        }).when(dialogWindowPresenter).showWindowForEdit((CallBack)anyObject(), (Employee)anyObject());

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                String actual = (String)args[0];

                assertEquals("", actual);

                return null;
            }
        }).when(view).setExceptionMessage(anyString());

        presenter.onSelectedEmployee(testEmployee);
        presenter.onEditButtonClicked();

        verify(dialogWindowPresenter).showWindowForEdit((CallBack)anyObject(), (Employee)anyObject());
        verify(view).setExceptionMessage(anyString());
    }

    @Test
    public void errorMessageShouldBeAppearUsingEditCallBack() throws Exception {
        when(localization.error()).thenReturn("error");

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                CallBack addTestCallBack = (CallBack)args[0];
                addTestCallBack.onChangeTableOfEmployee(testEmployee);
                return null;
            }
        }).when(dialogWindowPresenter).showWindow((CallBack)anyObject());

        presenter.onAddButtonClicked();

        reset(view);

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                CallBack editTestCallBack = (CallBack)args[0];
                editTestCallBack.onChangeTableOfEmployee(testEmployee);
                return null;
            }
        }).when(dialogWindowPresenter).showWindowForEdit((CallBack)anyObject(), (Employee)anyObject());

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                String testErrorString = (String)args[0];

                assertEquals(testErrorString, "error");

                return null;
            }
        }).when(view).setExceptionMessage(anyString());

        presenter.onSelectedEmployee(testEmployee);
        presenter.onEditButtonClicked();

        verify(dialogWindowPresenter).showWindowForEdit((CallBack)anyObject(), (Employee)anyObject());
        verify(view).setExceptionMessage(anyString());
    }

    @Test
    public void mainWindowShouldBeAddedOnWidget() throws Exception {
        presenter.go(widget);

        verify(widget).setWidget(view);

    }

    @Test
    public void noteShouldBeAddedForCurrentEmployeeUsingAddNoteCallBack() throws Exception {
        final Employee testEmployeeOne = new Employee();
        final Note testNoteOne = new Note("1", "2");

        final Employee testEmployeeTwo = new Employee();
        final Note testNoteTwo = new Note("a", "b");

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                CallBackForNote testCallBack = (CallBackForNote)args[0];
                testCallBack.onChangeTableOfNotes(testNoteOne);

                return null;
            }
        }).when(noteDialogWindowPresenter).showWindow((CallBackForNote)anyObject());

        assertEquals(0, testEmployeeOne.getListOfNotes().size());

        presenter.onSelectedEmployee(testEmployeeOne);
        presenter.onAddNoteButtonClicked();
        reset(noteDialogWindowPresenter);
        List<Note> testListOne = testEmployeeOne.getListOfNotes();

        assertEquals(1, testListOne.size());
        assertEquals(testNoteOne, testListOne.get(0));

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                CallBackForNote testCallBack = (CallBackForNote)args[0];
                testCallBack.onChangeTableOfNotes(testNoteTwo);

                return null;
            }
        }).when(noteDialogWindowPresenter).showWindow((CallBackForNote)anyObject());

        assertEquals(0, testEmployeeTwo.getListOfNotes().size());

        presenter.onSelectedEmployee(testEmployeeTwo);
        presenter.onAddNoteButtonClicked();
        List<Note> testListTwo = testEmployeeTwo.getListOfNotes();

        assertEquals(1, testListTwo.size());
        assertEquals(testNoteTwo, testListTwo.get(0));

        verify(noteDialogWindowPresenter).showWindow((CallBackForNote)anyObject());
    }

    @Test
    public void errorMessageWithPatchersShouldBeAppear() throws Exception {

        when(localization.error()).thenReturn("error");

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                CallBack addTestCallBack = (CallBack)args[0];
                addTestCallBack.onChangeTableOfEmployee(testEmployee);
                return null;
            }
        }).when(dialogWindowPresenter).showWindow((CallBack)anyObject());

        presenter.onAddButtonClicked();

        reset(view);
        reset(dialogWindowPresenter);

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                CallBack editTestCallBack = (CallBack)args[0];
                editTestCallBack.onChangeTableOfEmployee(testEmployee);
                return null;
            }
        }).when(dialogWindowPresenter).showWindow((CallBack)anyObject());

        presenter.onAddButtonClicked();

        verify(dialogWindowPresenter).showWindow((CallBack)anyObject());
    }
}
