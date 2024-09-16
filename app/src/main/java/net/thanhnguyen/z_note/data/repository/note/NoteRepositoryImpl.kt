package net.thanhnguyen.z_note.data.repository.note

import io.realm.kotlin.notifications.ResultsChange
import kotlinx.coroutines.flow.Flow
import net.thanhnguyen.z_note.data.model.Note
import net.thanhnguyen.z_note.data.model.NoteItem
import net.thanhnguyen.z_note.data.repository.note.datasource.NoteApiData
import net.thanhnguyen.z_note.data.repository.note.datasource.NoteCacheData
import net.thanhnguyen.z_note.data.repository.note.datasource.NoteLocalData
import net.thanhnguyen.z_note.domain.NoteRepository

class NoteRepositoryImpl(
    private val apiData: NoteApiData,
    private val localDB: NoteLocalData,
    private val cachData: NoteCacheData
): NoteRepository {
    override suspend fun getNote(): List<NoteItem> {
        return getCacheData()
    }

    override suspend fun updateNote(list: List<NoteItem>) {
        localDB.updateNoteToDB(list)
    }

    override suspend fun deleteNote(noteItem: NoteItem) {
        localDB.deleteNoteD(noteItem)
    }

    override suspend fun insertNote(noteItem: NoteItem) {
        localDB.insertNoteToDB(listOf(noteItem))
    }

    override fun getNoteFlow(): Flow<ResultsChange<Note>> = localDB.getNoteFlow()

    override suspend fun refreshData(): List<NoteItem> {
        val noteList = getDataFromApi()
        // sometime need clear local
        // localDB.clear()
        localDB.updateNoteToDB(noteList)
        cachData.saveNoteToCache(noteList)
        return noteList
    }

    suspend fun getDataFromApi(): List<NoteItem> {
        val response = apiData.getNoteData()
        return response.body() ?: listOf()
    }

    suspend fun getDataFromDB(): List<NoteItem>{

        var notelist = localDB.getNoteFromDB()
        if (notelist.size > 0){
            return notelist
        } else {
            notelist = getDataFromApi()
            localDB.insertNoteToDB(notelist)
        }
        return notelist
    }

    suspend fun getCacheData():List<NoteItem>{
        var notelist = cachData.getNoteFromCache()
        if (notelist.size > 0){
            return notelist
        } else {
            notelist = getDataFromDB()
            cachData.saveNoteToCache(notelist)
        }
        return notelist
    }
}