package lt.ausra.note.notesActivity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import lt.ausra.note.repository.Note
import lt.ausra.note.repository.NoteRepository

class NotesActivityViewModel : ViewModel() {

    private val _notesLiveData = MutableLiveData<List<Note>>(mutableListOf())
    val notesLiveData: LiveData<List<Note>>
        get() = _notesLiveData

    fun fetchNotes() {

        if (_notesLiveData.value == null || _notesLiveData.value?.isEmpty() == true) {
            NoteRepository.instance.addDummyListOfNotes()
        }
        _notesLiveData.value = NoteRepository.instance.notes
    }
}