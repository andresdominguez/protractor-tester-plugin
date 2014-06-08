package com.karateca.protractor;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.PsiFile;

public class TestLocator extends AnAction {
  @Override
  public void update(AnActionEvent e) {
    e.getPresentation().setEnabled(canEnableAction(e));
  }

  boolean canEnableAction(AnActionEvent e) {
    Editor editor = e.getData(PlatformDataKeys.EDITOR);
    PsiFile file = e.getData(LangDataKeys.PSI_FILE);

    // Need the following objects.
    return editor != null && file != null && e.getProject() != null;
  }

  public void actionPerformed(AnActionEvent actionEvent) {
    // No selection? Bail out.
    Editor editor = actionEvent.getData(PlatformDataKeys.EDITOR);
    if (editor == null || !editor.getSelectionModel().hasSelection()) {
      return;
    }

    String s = Tester.testLocator(editor.getSelectionModel().getSelectedText());
    System.out.println("Hey" + s);
  }
}
