package net.thanhnguyen.z_note.repository

import io.realm.kotlin.Realm
import io.realm.kotlin.notifications.ResultsChange
import io.realm.kotlin.query.Sort
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.toList
import net.thanhnguyen.z_note.core.model.Note
import net.thanhnguyen.z_note.core.model.NoteModel
import net.thanhnguyen.z_note.core.usecase.INoteBinding
import org.mongodb.kbson.BsonObjectId

class NoteRepository(private val realm: Realm): INoteBinding<NoteModel> {
 override suspend fun findNote(noteId: String): Note? {
        return realm.query(Note::class, "id == $0", noteId).find().firstOrNull()
    }

    suspend fun getAll()=realm.query(Note::class).sort("createdDate", Sort.DESCENDING)

    override suspend fun insertNote(note: NoteModel) {

        realm.write {
            note.id = org.mongodb.kbson.ObjectId()
            val noteNew = note.toNote()
            copyToRealm(noteNew)
        }
    }

    override suspend fun updateNote(newNote: NoteModel) {
        realm.write {
            realm.query(Note::class, "id == $0", newNote.id).find().firstOrNull()?.apply {
                title = newNote.title
                note = newNote.note
            }
        }
    }

    override suspend fun deleteNote(note: NoteModel) {
        realm.write {
            delete(note.toNote())
        }
    }
}