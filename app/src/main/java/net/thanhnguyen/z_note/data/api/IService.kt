package net.thanhnguyen.z_note.data.api

import net.thanhnguyen.z_note.data.model.NoteItem
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Url

interface IService {

    @GET
    suspend fun getQuery(@Url url: String): Response<List<NoteItem>>

    @POST
    suspend fun sendData(@Url url: String, @Body data: NoteItem): Response<Any>
}
