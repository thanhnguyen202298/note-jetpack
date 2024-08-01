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
import org.mongodb.kbson.ObjectId

class NoteRepository(private val realm: Realm) : INoteBinding<NoteModel> {
    override suspend fun findNote(noteId: ObjectId): Note? {
        return realm.query(Note::class, "id == $0", noteId).find().firstOrNull()
    }

    val flows = realm.query(Note::class).sort("createdDate", Sort.DESCENDING).find().asFlow()

    suspend fun getAll() = realm.query(Note::class).sort("createdDate", Sort.DESCENDING).find()
        .map { it.toNoteModel() }

    override suspend fun insertNote(note: NoteModel) {

        realm.write {
            note.id = org.mongodb.kbson.ObjectId()
            val noteNew = note.toNote()
            copyToRealm(noteNew)
        }
    }

    override suspend fun updateNote(newNote: NoteModel) {
        newNote.id?.let { id ->
            val old = findNote(id)
            old?.let {
                realm.write {
                    val note = findLatest(old)
                    note?.note = newNote.note
                    note?.title = newNote.title
                }
            }
        }
    }

    override suspend fun deleteNote(note: NoteModel) {
        note.id?.let { id ->
            val old = findNote(id)
            old?.let {
                realm.write {
                    val obj = findLatest(old)
                    obj?.let { delete(obj) }
                }
            }

        }
    }
}