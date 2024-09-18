package net.thanhnguyen.z_note.data.db

import io.realm.kotlin.Realm
import io.realm.kotlin.query.Sort
import net.thanhnguyen.z_note.data.model.Note
import net.thanhnguyen.z_note.data.model.NoteItem
import org.mongodb.kbson.ObjectId

class NoteDAO(private val realm: Realm) : IDAO<NoteItem> {
    override suspend fun findNote(noteId: ObjectId): Note? {
        return realm.query(Note::class, "id == $0", noteId).find().firstOrNull()
    }

    val flows = realm.query(Note::class).sort("createdDate", Sort.DESCENDING).find().asFlow()

    fun getAll() = realm.query(Note::class).sort("createdDate", Sort.DESCENDING).find()
        .map { it.toNoteModel() }

    override suspend fun insertNote(note: NoteItem) {

        realm.write {
            note.id = org.mongodb.kbson.ObjectId()
            val noteNew = note.toNote()
            return@write copyToRealm(noteNew)
        }
    }

    override suspend fun updateNote(newNote: NoteItem) {
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

    override suspend fun deleteNote(note: NoteItem) {
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