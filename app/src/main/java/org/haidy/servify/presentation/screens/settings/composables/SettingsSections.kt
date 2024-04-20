package org.haidy.servify.presentation.screens.settings.composables

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import org.haidy.servify.app.resources.Resources
import org.haidy.servify.app.utils.LanguageCode
import org.haidy.servify.app.utils.ThemeMode
import org.haidy.servify.presentation.screens.settings.SettingsInteractionListener

@Composable
fun getFirstSectionItems(
    listener: SettingsInteractionListener,
    isNotificationOn: Boolean,
    languageCode: LanguageCode
): List<@Composable () -> Unit> {
    return listOf(
        {
            SettingsCardItem(
                onClick = { listener.onClickEditProfile() },
                leadingIconPainter = painterResource(id = Resources.images.editProfile),
                text = Resources.strings.editProfile
            )
        },
        {
            val notificationStatus =
                if (isNotificationOn) Resources.strings.on else Resources.strings.off
            SettingsCardItem(
                onClick = { listener.onClickNotifications() },
                leadingIconPainter = painterResource(id = Resources.images.notification),
                text = Resources.strings.notifications,
                trailingText = notificationStatus
            )
        },
        {
            val language = when (languageCode) {
                LanguageCode.AR -> Resources.strings.arabic
                LanguageCode.EG -> Resources.strings.egyptian
                LanguageCode.EN -> Resources.strings.english
            }
            SettingsCardItem(
                onClick = { listener.onClickLanguage() },
                leadingIconPainter = painterResource(id = Resources.images.language),
                text = Resources.strings.language,
                trailingText = language
            )
        },
    )
}

@Composable
fun getSecondSectionItems(listener: SettingsInteractionListener, themeMode: ThemeMode): List<@Composable () -> Unit> {
    return listOf(
        {
            SettingsCardItem(
                onClick = { listener.onClickUpdatePassword() },
                leadingIconPainter = painterResource(id = Resources.images.updatePassword),
                text = Resources.strings.updatePassword
            )
        },
        {
            SettingsCardItem(
                onClick = { listener.onClickTheme() },
                leadingIconPainter = painterResource(id = Resources.images.themeIcon),
                text = Resources.strings.theme,
                trailingText = when (themeMode) {
                    ThemeMode.SYSTEM_THEME -> Resources.strings.systemMode
                    ThemeMode.LIGHT_THEME -> Resources.strings.lightMode
                    ThemeMode.DARK_THEME -> Resources.strings.darkMode
                }
            )
        }
    )
}

@Composable
fun getThirdSectionItems(listener: SettingsInteractionListener): List<@Composable () -> Unit> {
    return listOf(
        {
            SettingsCardItem(
                onClick = { listener.onClickEditProfile() },
                leadingIconPainter = painterResource(id = Resources.images.helpAndSupportIcon),
                text = Resources.strings.helpAndSupport
            )
        },
        {
            SettingsCardItem(
                onClick = { listener.onClickContactUs() },
                leadingIconPainter = painterResource(id = Resources.images.contactUsIcon),
                text = Resources.strings.contactUs
            )
        },
        {
            SettingsCardItem(
                onClick = { listener.onClickPrivacyPolicy() },
                leadingIconPainter = painterResource(id = Resources.images.privacyPolicyIcon),
                text = Resources.strings.privacyPolicy
            )
        },
        {
            SettingsCardItem(
                onClick = { listener.onClickLogout() },
                leadingIconPainter = painterResource(id = Resources.images.logoutIcon),
                text = Resources.strings.logout
            )
        }
    )
}