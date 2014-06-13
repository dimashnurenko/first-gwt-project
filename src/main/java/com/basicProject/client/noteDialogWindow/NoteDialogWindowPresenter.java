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
import com.google.inject.Inject;

/**
 * @author Dmitry Shnurenko
 */
public class NoteDialogWindowPresenter implements NoteDialogWindowView.ActionDelegate {

    private final NoteDialogWindowView noteDialogWindowView;
    private       CallBackForNote      callBackForNote;

    @Inject
    public NoteDialogWindowPresenter(NoteDialogWindowView noteDialogWindowView) {
        this.noteDialogWindowView = noteDialogWindowView;
        this.noteDialogWindowView.setDelegate(this);
    }

    @Override
    public void onClickAddNote() {
        String title = noteDialogWindowView.getTitle();
        String text = noteDialogWindowView.getText();

        Note note = new Note(title, text);

        callBackForNote.onChangeTableOfNotes(note);

        noteDialogWindowView.hideWindow();
    }

    @Override
    public void onClickCancel() {
        noteDialogWindowView.hideWindow();
    }

    public void showWindow(CallBackForNote callBackForNote) {
        this.callBackForNote = callBackForNote;

        noteDialogWindowView.setTitle("");
        noteDialogWindowView.setText("");

        noteDialogWindowView.showWindow();

    }
}
