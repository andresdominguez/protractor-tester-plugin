package com.karateca.protractor;

import com.intellij.codeInsight.hint.HintManager;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.util.Pair;
import com.intellij.psi.PsiFile;

public class TestLocatorAction extends AnAction {
  @Override
  public void update(AnActionEvent e) {
    e.getPresentation().setEnabled(canEnableAction(e));
  }

  boolean canEnableAction(AnActionEvent e) {
    Editor editor = e.getData(PlatformDataKeys.EDITOR);
    PsiFile file = e.getData(LangDataKeys.PSI_FILE);

    // Need the following objects.
    return editor != null &&
        file != null
        && e.getProject() != null &&
        editor.getSelectionModel().hasSelection();
  }

  public void actionPerformed(AnActionEvent actionEvent) {
    // No selection? Bail out.
    Editor editor = actionEvent.getData(PlatformDataKeys.EDITOR);
    if (editor == null || !editor.getSelectionModel().hasSelection()) {
      return;
    }

    JsonReader reader = new ElementExplorerReader();
    LocatorTester tester = new LocatorTester(
        reader
    );

    String selectedText = editor.getSelectionModel().getSelectedText();
    if (selectedText == null) {
      HintManager
          .getInstance()
          .showErrorHint(editor, "Selection is empty");
      return;
    }
    Pair<String, String> pair = tester.testLocator(selectedText);

    String hint = pair == null ?
        "No response" : String.format("%s", pair.second);

    HintManager.getInstance().showErrorHint(editor, hint);
  }
}
