package lt.ausra.note.noteDetailsActivity

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import lt.ausra.note.repository.Note
import lt.ausra.note.R
import lt.ausra.note.databinding.NoteDetailsBinding
import lt.ausra.note.notesActivity.NotesActivity

class NoteDetailsActivity : AppCompatActivity() {

    private lateinit var binding: NoteDetailsBinding
    private val activityViewModel: NoteDetailsActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.note_details)
        binding.noteDetailsActivity = this

        activityViewModel.noteLiveData.observe(
            this,
            Observer { note ->
                binding.note = note
            }
        )

        activityViewModel.fetchNote(getIntentExtra())
    }

    private fun getIntentExtra() =
        intent.getIntExtra(NotesActivity.NOTES_ACTIVITY_NOTE_INTENT_ID, -1)


    fun onClickOfSaveButton(view: View) {
        activityViewModel.saveNewOrUpdateExistingNote(binding.note as Note)
        finish()
    }

    fun onClickOfCloseButton(view: View) {
        finish()
    }
}