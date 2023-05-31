package lt.ausra.note.repository

class NoteRepository {

    val notes = mutableListOf<Note>()

    fun getNote(id: Int) = notes.find { it.id == id }

    fun addNote(note: Note): Note {
        var newId = 1

        if (notes.isNotEmpty()) {
            newId = notes.maxBy { it.id }.id.inc()
        }

        val newNote = Note(id = newId, note.name, note.details)
        notes.add(newNote)
        return newNote
    }

    fun updateNote(note: Note?) {
        if (note != null) {
            val index = notes.indexOfFirst { it.id == note.id }
            if (index >= 0) {
                notes[index] = note
            }
        }
    }

    fun addDummyListOfNotes() {
        notes.addAll(generateListOfNotes())
    }

    private fun generateListOfNotes(): List<Note> {
        val list = mutableListOf<Note>()

        list.add(Note(
            1, "Shopping list", "bananas, bread, milk"
        ))
        list.add(Note(
            2, "Movies to watch", "Blade, Casablanca"
        ))
        list.add(Note(
            3, "To do for Amsterdam trip", "Book hotel, pack, learn to ride a bike"
        ))

        return list
    }

    companion object {
        val instance: NoteRepository = NoteRepository()
    }
}