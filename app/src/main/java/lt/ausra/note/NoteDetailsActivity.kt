package lt.ausra.note

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import lt.ausra.note.databinding.NoteDetailsBinding

class NoteDetailsActivity : AppCompatActivity() {

    private lateinit var binding: NoteDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = NoteDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getIntentExtra()
        setClickListenerOfSaveButton()
        setClickListenerOfCloseButton()

    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.run {
            putString(NOTE_DETAILS_ID, binding.idEditText.text.toString())
            putString(NOTE_DETAILS_NAME, binding.nameEditText.text.toString())
            putString(NOTE_DETAILS_DETAILS, binding.detailsEditText.text.toString())
        }
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        with(savedInstanceState) {
            binding.idEditText.setText(getString(NOTE_DETAILS_ID))
            binding.nameEditText.setText(getString(NOTE_DETAILS_NAME))
            binding.detailsEditText.setText(getString(NOTE_DETAILS_DETAILS))
        }
    }

    private fun getIntentExtra() {
        binding.idEditText.setText(
            intent.getIntExtra(NotesActivity.NOTE_ID, 0).toString()
        )
        binding.nameEditText.setText(
            intent.getStringExtra(NotesActivity.NOTE_NAME)
        )
        binding.detailsEditText.setText(
            intent.getStringExtra(NotesActivity.NOTE_DETAILS)
        )
    }

    private fun setClickListenerOfSaveButton() {
        binding.saveButton.setOnClickListener {
            val finishIntent = Intent()

            finishIntent.putExtra(NOTE_DETAILS_ID, (binding.idEditText.text.toString()).toInt())
            finishIntent.putExtra(NOTE_DETAILS_NAME, binding.nameEditText.text.toString())
            finishIntent.putExtra(NOTE_DETAILS_DETAILS, binding.detailsEditText.text.toString())
            finishIntent.putExtra(NotesActivity.NOTE_POSITION, intent.getIntExtra(NotesActivity.NOTE_POSITION, -1))
            setResult(RESULT_OK, finishIntent)
            finish()
        }
    }

    private fun setClickListenerOfCloseButton() {
        binding.closeButton.setOnClickListener {
            finish()
        }
    }

    companion object {
        const val NOTE_DETAILS_ID = "note_Id"
        const val NOTE_DETAILS_NAME = "note_name"
        const val NOTE_DETAILS_DETAILS = "note_details"
    }
}