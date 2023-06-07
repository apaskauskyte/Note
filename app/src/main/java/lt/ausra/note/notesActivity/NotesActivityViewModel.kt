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

    fun filterNotesList(enteredText: String) {
        val filteredList = mutableListOf<Note>()
        NoteRepository.instance.notes.forEach {
            if (
                it.id.toString().contains(enteredText, true) ||
                it.name.contains(enteredText, true) ||
                it.details.contains(enteredText, true)
            ) {
                filteredList.add(it)
            }
        }
        _notesLiveData.value = filteredList
    }
}