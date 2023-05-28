package lt.ausra.note

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import lt.ausra.note.databinding.NoteDetailsBinding

class NoteDetailsActivity : AppCompatActivity() {

    private lateinit var binding: NoteDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.note_details)
        binding.note = getIntentExtra()
        binding.noteDetailsActivity = this

        getIntentExtra()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.run {
            val note = binding.note
            if(note != null) {
                putInt(NOTE_DETAILS_ID, note.id)
                putString(NOTE_DETAILS_NAME, note.name)
                putString(NOTE_DETAILS_DETAILS, note.details)
            }
        }
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        with(savedInstanceState) {
            binding.note = Note(
                getInt(NOTE_DETAILS_ID),
                getString(NOTE_DETAILS_NAME) ?: "",
                getString(NOTE_DETAILS_DETAILS) ?: ""
            )
        }
    }

    private fun getIntentExtra(): Note {
        return Note(
            intent.getIntExtra(NotesActivity.NOTE_ID, 0),
            intent.getStringExtra(NotesActivity.NOTE_NAME) ?: "",
            intent.getStringExtra(NotesActivity.NOTE_DETAILS) ?: ""
        )
    }

    fun onClickOfSaveButton(view: View) {
        val finishIntent = Intent()

        finishIntent.putExtra(NOTE_DETAILS_ID, (binding.idEditText.text.toString()).toInt())
        finishIntent.putExtra(NOTE_DETAILS_NAME, binding.nameEditText.text.toString())
        finishIntent.putExtra(NOTE_DETAILS_DETAILS, binding.detailsEditText.text.toString())
        finishIntent.putExtra(
            NotesActivity.NOTE_POSITION,
            intent.getIntExtra(NotesActivity.NOTE_POSITION, -1)
        )
        setResult(RESULT_OK, finishIntent)
        finish()
    }

    fun onClickOfCloseButton(view: View) {
        finish()
    }

    companion object {
        const val NOTE_DETAILS_ID = "note_Id"
        const val NOTE_DETAILS_NAME = "note_name"
        const val NOTE_DETAILS_DETAILS = "note_details"
    }
}