package lt.ausra.note.noteDetailsActivity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import lt.ausra.note.repository.Note
import lt.ausra.note.repository.NoteRepository

class NoteDetailsActivityViewModel : ViewModel() {

    private val _noteLiveData = MutableLiveData<Note>()
    val noteLiveData: LiveData<Note>
        get() = _noteLiveData

    fun fetchNote(noteId: Int) {

        if (_noteLiveData.value == null) {
            if (noteId > 0) {
                _noteLiveData.value = NoteRepository.instance.getNote(noteId)
            } else {
                _noteLiveData.value = Note(-1, "", "")
            }
        }
    }

    fun saveNewOrUpdateExistingNote(note: Note) {
        if (note.id > 0) {
            NoteRepository.instance.updateNote(note)
            note.setNewName(note.name)
            note.setNewDetails(note.details)
        } else {
            NoteRepository.instance.addNote(note)
        }
    }
}