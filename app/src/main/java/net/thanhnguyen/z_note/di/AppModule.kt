package net.thanhnguyen.z_note.di

import net.thanhnguyen.z_note.repository.NoteRepository
import net.thanhnguyen.z_note.repository.provideNoteDatabase
import net.thanhnguyen.z_note.viewmodel.NoteViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val AppModule = module {
    single { provideNoteDatabase(get()) }
    single { NoteRepository(get()) }
    single { NoteViewModel(get()) }
}