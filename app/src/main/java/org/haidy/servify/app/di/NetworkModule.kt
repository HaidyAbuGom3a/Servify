package org.haidy.servify.app.di


import android.content.Context
import com.facebook.CallbackManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import org.haidy.servify.R
import org.haidy.servify.data.remote.network.AuthInterceptor
import org.haidy.servify.data.remote.network.ServifyApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import com.google.android.gms.auth.api.identity.Identity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val BASE_URL = "http://54.196.133.53/"

    @Singleton
    @Provides
    fun provideApiService(authInterceptor: AuthInterceptor): ServifyApiService {
        val client = OkHttpClient.Builder().addInterceptor(authInterceptor).build()
        return Retrofit.Builder()
            .baseUrl(BASE_URL).client(client).addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ServifyApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideFireBaseAuth(): FirebaseAuth = Firebase.auth

    @Singleton
    @Provides
    fun provideFireBaseFireStore(): FirebaseFirestore = Firebase.firestore

    @Singleton
    @Provides
    fun provideWebClientId(@ApplicationContext context: Context) =
        context.getString(R.string.web_client_id)

    @Singleton
    @Provides
    fun provideSignInClient(@ApplicationContext context: Context) =
        Identity.getSignInClient(context)

    @Singleton
    @Provides
    fun provideCallbackManager(): CallbackManager = CallbackManager.Factory.create()
}

