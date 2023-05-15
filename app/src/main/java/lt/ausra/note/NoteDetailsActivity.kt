package lt.ausra.note

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class NoteDetailsActivity : AppCompatActivity() {

    lateinit var idEditText: EditText
    lateinit var nameEditText: EditText
    lateinit var detailsEditText: EditText
    lateinit var saveButton: Button
    lateinit var closeButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.note_details)

        idEditText = findViewById(R.id.idEditText)
        nameEditText = findViewById(R.id.nameEditText)
        detailsEditText = findViewById(R.id.detailsEditText)
        saveButton = findViewById(R.id.saveButton)
        closeButton = findViewById(R.id.closeButton)

        getIntentExtra()
        setClickListenerOfSaveButton()
        setClickListenerOfCloseButton()

    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.run {
            putString(NOTE_DETAILS_ID, idEditText.text.toString())
            putString(NOTE_DETAILS_NAME, nameEditText.text.toString())
            putString(NOTE_DETAILS_DETAILS, detailsEditText.text.toString())
        }
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        with(savedInstanceState) {
            idEditText.setText(getString(NOTE_DETAILS_ID))
            nameEditText.setText(getString(NOTE_DETAILS_NAME))
            detailsEditText.setText(getString(NOTE_DETAILS_DETAILS))
        }
    }

    private fun getIntentExtra() {
        idEditText.setText(
            intent.getIntExtra(NotesActivity.NOTE_ID, -1).toString()
        )
        nameEditText.setText(
            intent.getStringExtra(NotesActivity.NOTE_NAME)
        )
        detailsEditText.setText(
            intent.getStringExtra(NotesActivity.NOTE_DETAILS)
        )
    }

    private fun setClickListenerOfSaveButton() {
        saveButton.setOnClickListener {
            val finishIntent = Intent()

            finishIntent.putExtra(NOTE_DETAILS_ID, (idEditText.text.toString()).toInt())
            finishIntent.putExtra(NOTE_DETAILS_NAME, nameEditText.text.toString())
            finishIntent.putExtra(NOTE_DETAILS_DETAILS, detailsEditText.text.toString())
            setResult(RESULT_OK, finishIntent)
            finish()
        }
    }

    private fun setClickListenerOfCloseButton() {
        closeButton.setOnClickListener {
            val close = Intent(this, NotesActivity::class.java)
            startActivity(close)
        }
    }

    companion object {
        const val NOTE_DETAILS_ID = "note_Id"
        const val NOTE_DETAILS_NAME = "note_name"
        const val NOTE_DETAILS_DETAILS = "note_details"
    }
}