package krasa.editorGroups;

import com.intellij.openapi.extensions.Extensions;
import com.intellij.openapi.fileEditor.FileEditor;
import com.intellij.openapi.fileEditor.impl.EditorTabTitleProvider;
import com.intellij.openapi.fileEditor.impl.FileEditorManagerImpl;
import com.intellij.openapi.project.DumbService;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import krasa.editorGroups.model.EditorGroup;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class EditorGroupTabTitleProvider implements EditorTabTitleProvider {

	/**
	 * TODO bad EP, no way to distinguishe between windows
	 */
	@Nullable
	@Override
	public String getEditorTabTitle(@NotNull Project project, @NotNull VirtualFile virtualFile) {
		String presentableNameForUI = getPresentableNameForUI(project, virtualFile);

		FileEditor textEditor = FileEditorManagerImpl.getInstanceEx(project).getSelectedEditor(virtualFile);
		EditorGroup group = null;
		if (textEditor != null) {
			group = textEditor.getUserData(EditorGroupPanel.EDITOR_GROUP);
		}


		if (group!=null && group .valid()) {
			int size = group.size();
			if (group.getTitle() != null) {
				return "[" + size + " "+group.getTitle()+ "] " + presentableNameForUI;
			}
			return "[" + size + "] " + presentableNameForUI;
		}
		return presentableNameForUI;
	}

	@NotNull
	public static String getPresentableNameForUI(@NotNull Project project, @NotNull VirtualFile file) {
		List<EditorTabTitleProvider> providers = DumbService.getInstance(project).filterByDumbAwareness(
			Extensions.getExtensions(EditorTabTitleProvider.EP_NAME));
		for (EditorTabTitleProvider provider : providers) {
			if (provider instanceof EditorGroupTabTitleProvider) {
				continue;
			}
			String result = provider.getEditorTabTitle(project, file);
			if (result != null) {
				return result;
			}
		}

		return file.getPresentableName();
	}


}
