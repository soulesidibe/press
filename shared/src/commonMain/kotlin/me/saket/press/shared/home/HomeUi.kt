package me.saket.press.shared.home

import me.saket.press.shared.AndroidParcelize
import me.saket.press.shared.db.FolderId
import me.saket.press.shared.db.NoteId
import me.saket.press.shared.editor.EditorOpenMode.ExistingNote
import me.saket.press.shared.editor.EditorScreenKey
import me.saket.press.shared.editor.PreSavedNoteId
import me.saket.press.shared.ui.ScreenKey

@AndroidParcelize
data class HomeScreenKey(
  val folder: FolderId?
) : ScreenKey {
  companion object {
    fun root() = HomeScreenKey(folder = null)
    fun isRoot(key: ScreenKey) = key is HomeScreenKey && key.folder == null
  }
}

interface HomeEvent {
  object NewNoteClicked : HomeEvent
}

data class HomeUiModel(val title: String, val rows: List<Row>) {
  val notes: List<Note> get() = rows.filterIsInstance<Note>()
  val folders: List<Folder> get() = rows.filterIsInstance<Folder>()

  interface Row {
    val id: Any
    fun screenKey(): ScreenKey

    override fun equals(other: Any?): Boolean
  }

  // TODO: Rename to NoteModel.
  data class Note(
    override val id: NoteId,
    val title: String,
    val body: String
  ) : Row {
    override fun screenKey(): ScreenKey {
      return EditorScreenKey(
        ExistingNote(PreSavedNoteId(id))
      )
    }
  }

  // TODO: Rename to FolderModel.
  data class Folder(
    override val id: FolderId,
    val title: String
  ) : Row {
    override fun screenKey(): ScreenKey {
      return HomeScreenKey(folder = id)
    }
  }
}
