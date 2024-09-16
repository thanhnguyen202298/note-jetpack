package net.thanhnguyen.z_note.di

import io.realm.kotlin.Realm
import net.thanhnguyen.z_note.data.api.IService
import net.thanhnguyen.z_note.data.db.NoteDAO
import net.thanhnguyen.z_note.data.repository.note.NoteRepositoryImpl
import net.thanhnguyen.z_note.data.repository.note.datasource.NoteApiData
import net.thanhnguyen.z_note.data.repository.note.datasource.NoteCacheData
import net.thanhnguyen.z_note.data.repository.note.datasource.NoteLocalData
import net.thanhnguyen.z_note.domain.NoteRepository
import net.thanhnguyen.z_note.domain.note.DeleteNoteUseCase
import net.thanhnguyen.z_note.domain.note.GetNoteUseCase
import net.thanhnguyen.z_note.domain.note.SaveNoteUseCase
import net.thanhnguyen.z_note.domain.note.UpdateNoteUseCase
import net.thanhnguyen.z_note.presenter.viewmodel.NoteViewModel
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit

val AppDataModule = module {
    single<Realm> { provideNoteDatabase(get()) }
    single<NoteDAO> { NoteDAO(get()) }
    single<NoteLocalData> { provideLocalData(get()) }

    single<OkHttpClient> { provideOkHttp() }
    single<Retrofit> { provideRetrofit(get()) }
    single<IService> { provideIService(get()) }
    single<NoteApiData> { provideApiData(get()) }
    single<NoteCacheData> { provideCacheData() }

}

val RepositoryModule = module {
    single<NoteRepository> { NoteRepositoryImpl(get(),get(),get()) }
}

val UseCaseModule = module {
    single<GetNoteUseCase> { GetNoteUseCase(get()) }
    single<SaveNoteUseCase> { SaveNoteUseCase(get()) }
    single<UpdateNoteUseCase> { UpdateNoteUseCase(get()) }
    single<DeleteNoteUseCase> { DeleteNoteUseCase(get()) }
}

val ViewModelModule = module {
    single { NoteViewModel(get(),get(), get(), get()) }
}