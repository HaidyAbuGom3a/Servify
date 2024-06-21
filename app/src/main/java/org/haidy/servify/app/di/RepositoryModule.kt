package org.haidy.servify.app.di

import android.content.Context
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import org.haidy.servify.data.local.IDataStore
import org.haidy.servify.data.remote.network.ServifyApiService
import org.haidy.servify.data.repository.AuthFacebookRepositoryImp
import org.haidy.servify.data.repository.AuthGoogleRepositoryImp
import org.haidy.servify.data.repository.AuthorizationRepositoryImp
import org.haidy.servify.data.repository.ChatRepositoryImp
import org.haidy.servify.data.repository.LocationRepositoryImp
import org.haidy.servify.data.repository.ServiceRepositoryImp
import org.haidy.servify.data.repository.SpecialistRepositoryImp
import org.haidy.servify.data.repository.UserRepositoryImp
import org.haidy.servify.data.repository.fake.FakeOrderRepository
import org.haidy.servify.data.repository.fake.FakePaymentRepository
import org.haidy.servify.data.repository.fake.FakeServicesRepository
import org.haidy.servify.data.repository.fake.FakeSpecialistsRepository
import org.haidy.servify.domain.repository.IAuthFacebookRepository
import org.haidy.servify.domain.repository.IAuthGoogleRepository
import org.haidy.servify.domain.repository.IAuthorizationRepository
import org.haidy.servify.domain.repository.IChatRepository
import org.haidy.servify.domain.repository.ILocationRepository
import org.haidy.servify.domain.repository.IOrderRepository
import org.haidy.servify.domain.repository.IPaymentRepository
import org.haidy.servify.domain.repository.IServiceRepository
import org.haidy.servify.domain.repository.ISpecialistRepository
import org.haidy.servify.domain.repository.IUserRepository
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideAuthRepository(
        apiService: ServifyApiService,
        userRepository: IUserRepository,
        @ApplicationContext context: Context
    ): IAuthorizationRepository {
        return AuthorizationRepositoryImp(apiService, userRepository, context)
    }

    @Singleton
    @Provides
    fun provideUserRepository(
        apiService: ServifyApiService,
        dataStore: IDataStore,
        @ApplicationContext context: Context
    ): IUserRepository {
        return UserRepositoryImp(apiService, dataStore, context)
    }

    @Singleton
    @Provides
    fun provideGoogleAuthRepository(
        apiService: ServifyApiService,
        webClientId: String,
        oneTapClient: SignInClient,
        auth: FirebaseAuth,
        userRepository: IUserRepository
    ): IAuthGoogleRepository {
        return AuthGoogleRepositoryImp(
            apiService,
            webClientId,
            oneTapClient,
            auth,
            userRepository
        )
    }

    @Singleton
    @Provides
    fun provideFacebookAuthRepository(
        apiService: ServifyApiService,
        auth: FirebaseAuth,
        userRepository: IUserRepository
    ): IAuthFacebookRepository {
        return AuthFacebookRepositoryImp(
            apiService,
            auth,
            userRepository
        )
    }

    @Singleton
    @Provides
    fun provideLocationRepository(
        apiService: ServifyApiService,
    ): ILocationRepository {
        return LocationRepositoryImp(
            apiService
        )
    }

    @Singleton
    @Provides
    @Named("services")
    fun provideServiceRepository(
        apiService: ServifyApiService
    ): IServiceRepository {
        return ServiceRepositoryImp(apiService)
    }

    @Singleton
    @Provides
    @Named("fakeServices")
    fun provideFakeServiceRepository(): IServiceRepository {
        return FakeServicesRepository()
    }


    @Singleton
    @Provides
    @Named("specialists")
    fun provideSpecialistRepository(
        apiService: ServifyApiService
    ): ISpecialistRepository {
        return SpecialistRepositoryImp(apiService)
    }

    @Singleton
    @Provides
    @Named("fakeSpecialists")
    fun provideFakeSpecialistsRepository(): ISpecialistRepository {
        return FakeSpecialistsRepository()
    }

    @Singleton
    @Provides
    fun provideChatRepository(
        firestore: FirebaseFirestore,
        userRepo: IUserRepository
    ): IChatRepository {
        return ChatRepositoryImp(firestore, userRepo)
    }

    @Singleton
    @Provides
    @Named("fakeOrders")
    fun provideFakeOrdersRepository(): IOrderRepository {
        return FakeOrderRepository()
    }

    @Singleton
    @Provides
    @Named("fakePayment")
    fun provideFakePaymentRepository(): IPaymentRepository {
        return FakePaymentRepository()
    }
}