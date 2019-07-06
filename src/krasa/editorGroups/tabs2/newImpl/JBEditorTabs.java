// Copyright 2000-2019 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
package krasa.editorGroups.tabs2.newImpl;

import com.intellij.openapi.Disposable;
import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.IdeFocusManager;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Deprecated
@ApiStatus.ScheduledForRemoval(inVersion = "2019.3")
public class JBEditorTabs extends krasa.editorGroups.tabs2.impl.JBEditorTabs {
	public JBEditorTabs(@Nullable Project project,
						@NotNull ActionManager actionManager,
						IdeFocusManager focusManager,
						@NotNull Disposable parent) {
		super(project, actionManager, focusManager, parent);
	}
}
