package org.haidy.servify.data.remote.network

import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.haidy.servify.data.dto.BaseResponse
import org.haidy.servify.data.dto.CountryDto
import org.haidy.servify.data.dto.GovernoratesDto
import org.haidy.servify.data.dto.MessageResponse
import org.haidy.servify.data.dto.ServiceDto
import org.haidy.servify.data.dto.SpecialistResponse
import org.haidy.servify.data.dto.UserDataDto
import org.haidy.servify.data.dto.UserDto
import org.haidy.servify.data.dto.UserProfileDto
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

interface ServifyApiService {

    @Multipart
    @POST("/api/register")
    suspend fun signupUser(
        @Part("name") username: RequestBody,
        @Part("email") email: RequestBody,
        @Part("password") password: RequestBody,
        @Part("password_confirmation") passwordConfirmation: RequestBody,
        @Part("phone") phone: RequestBody,
        @Part("country_id") countryId: RequestBody,
        @Part("governorate_id") governorateId: RequestBody,
        @Part("latitude") latitude: RequestBody,
        @Part("longitude") longitude: RequestBody,
        @Part("gender") gender: RequestBody,
        @Part image: MultipartBody.Part?
    ): Response<BaseResponse<UserDto>>

    @FormUrlEncoded
    @POST("/api/login")
    suspend fun loginUser(
        @Field("email") email: String,
        @Field("password") password: String,
    ): Response<BaseResponse<UserDto>>

    @FormUrlEncoded
    @POST("/api/forget")
    suspend fun sendOtpToEmail(
        @Field("email") email: String,
    ): Response<MessageResponse>

    @POST("/api/otp/{otp_code}")
    suspend fun confirmOtp(
        @Path("otp_code") otp: String
    ): Response<MessageResponse>

    @FormUrlEncoded
    @POST("/api/reset/{otp_code}")
    suspend fun resetPassword(
        @Path("otp_code") otp: String,
        @Field("password") password: String,
        @Field("password_confirmation") passwordConfirmation: String
    ): Response<MessageResponse>


    @FormUrlEncoded
    @POST("api/send-verification-code")
    suspend fun sendVerificationCode(
        @Field("email") email: String
    ): Response<MessageResponse>

    @POST("/api/verify-email/{otp_code}")
    suspend fun verifyEmail(
        @Path("otp_code") otp: String,
    ): Response<MessageResponse>

    @FormUrlEncoded
    @POST("api/login/google/callback/{uid}")
    suspend fun loginWithGoogle(
        @Path("uid") userId: String,
        @Field("country_id") countryId: String = "1",
        @Field("governorate_id") governorateId: String = "1"
    ): Response<BaseResponse<UserDto>>

    @FormUrlEncoded
    @POST("api/login/facebook/callback/{uid}")
    suspend fun loginWithFacebook(
        @Path("uid") userId: String,
        @Field("country_id") countryId: String = "1",
        @Field("governorate_id") governorateId: String = "1"
    ): Response<BaseResponse<UserDto>>

    @GET("api/profile")
    suspend fun getUserProfile(): Response<BaseResponse<UserProfileDto>>

    @POST("api/user/image/update")
    @Multipart
    suspend fun updateImage(
        @Part image: MultipartBody.Part?
    ): Response<MessageResponse>


    @GET("api/county/{id}/governorate")
    suspend fun getGovernorates(
        @Path("id") countryId: Int
    ): Response<BaseResponse<GovernoratesDto>>

    @GET("api/countries")
    suspend fun getCountries(): Response<BaseResponse<List<CountryDto>>>

    @GET("api/services")
    suspend fun getAllServices(): Response<BaseResponse<List<ServiceDto>>>

    @GET("api/services/filter-specialists")
    suspend fun getFilteredSpecialists(
        @Query("service") serviceName: String?,
        @Query("name") name: String?,
        @Query("rating") rating: String?
    ): Response<BaseResponse<List<SpecialistResponse>>>

    @GET("api/services/best-specialists")
    suspend fun getBestSpecialists(): Response<BaseResponse<List<SpecialistResponse>>>

    @GET("api/user/{id}")
    suspend fun getUerData(
        @Path("id") userId: String
    ): Response<BaseResponse<UserDataDto>>

}