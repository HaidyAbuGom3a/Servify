package org.haidy.servify.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import org.haidy.servify.app.LocalNavController
import org.haidy.servify.presentation.screens.addPhoto.addPhotoRoute
import org.haidy.servify.presentation.screens.bookingAppointment.bookingAppointmentRoute
import org.haidy.servify.presentation.screens.bookingCancellation.bookingCancellationRoute
import org.haidy.servify.presentation.screens.bookingTrack.bookingTrackRoute
import org.haidy.servify.presentation.screens.feedback.feedBackRoute
import org.haidy.servify.presentation.screens.forgotPassword.resetPassword.resetPasswordRoute
import org.haidy.servify.presentation.screens.forgotPassword.sendOtp.sendOtpRoute
import org.haidy.servify.presentation.screens.forgotPassword.verifyCode.verifyCodeRoute
import org.haidy.servify.presentation.screens.home.homeScreenRoute
import org.haidy.servify.presentation.screens.language.languageScreenRoute
import org.haidy.servify.presentation.screens.location.locationRoute
import org.haidy.servify.presentation.screens.login.loginScreenRoute
import org.haidy.servify.presentation.screens.onBoarding.onBoardingRoute
import org.haidy.servify.presentation.screens.profile.profileRoute
import org.haidy.servify.presentation.screens.services.servicesRoute
import org.haidy.servify.presentation.screens.settings.settingsRoute
import org.haidy.servify.presentation.screens.signup.signupScreenRoute
import org.haidy.servify.presentation.screens.support.supportRoute
import org.haidy.servify.presentation.screens.updatePassword.updatePasswordRoute
import org.haidy.servify.presentation.screens.verified.verifiedRoute
import org.haidy.servify.presentation.screens.verifyEmail.verifyEmailRoute


@Composable
fun ServifyNavGraph(startDestination: String) {
    NavHost(
        navController = LocalNavController.current,
        startDestination = startDestination
    ) {
        onBoardingRoute()
        loginScreenRoute()
        signupScreenRoute()
        homeScreenRoute()
        sendOtpRoute()
        resetPasswordRoute()
        verifyCodeRoute()
        verifyEmailRoute()
        verifiedRoute()
        addPhotoRoute()
        settingsRoute()
        languageScreenRoute()
        updatePasswordRoute()
        locationRoute()
        profileRoute()
        bookingTrackRoute()
        supportRoute()
        servicesRoute()
        feedBackRoute()
        bookingCancellationRoute()
        bookingAppointmentRoute()
    }
}