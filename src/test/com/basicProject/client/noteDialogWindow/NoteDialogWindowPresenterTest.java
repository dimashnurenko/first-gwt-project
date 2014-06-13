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
package com.basicProject.client.noteDialogWindow;

import com.basicProject.client.entity.Employee;
import com.basicProject.client.entity.Note;
import com.basicProject.client.mvp.CallBackForNote;

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
public class NoteDialogWindowPresenterTest {

    @Mock
    private NoteDialogWindowView      noteDialogWindowView;
    @Mock
    private CallBackForNote           callBackForNote;
    @Mock
    private Employee                  employee;
    @InjectMocks
    private NoteDialogWindowPresenter presenter;

    @Test
    public void notesShouldBeAddedToTableOfNotes() throws Exception {
        final String TITLE = "1";
        final String TEXT = "2";

        when(noteDialogWindowView.getTitle()).thenReturn(TITLE);
        when(noteDialogWindowView.getText()).thenReturn(TEXT);

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                Note testNote = (Note)args[0];

                assertEquals(testNote.getTitle(), TITLE);
                assertEquals(testNote.getText(), TEXT);

                return null;
            }
        }).when(callBackForNote).onChangeTableOfNotes((Note)anyObject());

        presenter.showWindow(callBackForNote);
        presenter.onClickAddNote();

        verify(noteDialogWindowView).getTitle();
        verify(noteDialogWindowView).getText();

        verify(callBackForNote).onChangeTableOfNotes((Note)anyObject());

        verify(noteDialogWindowView).hideWindow();
    }

    @Test
    public void dialogWindowShouldBeDisappeared() throws Exception {
        presenter.onClickCancel();

        verify(noteDialogWindowView).hideWindow();
    }

    @Test
    public void dialogWindowShouldBeAppearedForAddNote() throws Exception {
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                String testTitle = (String)args[0];

                assertEquals(testTitle, "");

                return null;
            }
        }).when(noteDialogWindowView).setTitle(anyString());

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                String testText = (String)args[0];

                assertEquals(testText, "");

                return null;
            }
        }).when(noteDialogWindowView).setText(anyString());

        presenter.showWindow(callBackForNote);

        verify(noteDialogWindowView).showWindow();
    }


}
