package net.thanhnguyen.z_note.di

import net.thanhnguyen.z_note.data.api.IService
import net.thanhnguyen.z_note.data.db.NoteDAO
import net.thanhnguyen.z_note.data.model.NoteItem
import net.thanhnguyen.z_note.data.repository.note.datasourceImpl.NoteApiDataImpl
import net.thanhnguyen.z_note.data.repository.note.datasourceImpl.NoteCacheDataImpl
import net.thanhnguyen.z_note.data.repository.note.datasourceImpl.NoteLocalDataImpl

fun provideCacheData() = NoteCacheDataImpl()

fun provideLocalData(noteDB: NoteDAO) = NoteLocalDataImpl(noteDB)

fun provideApiData(iService: IService) = NoteApiDataImpl(iService)