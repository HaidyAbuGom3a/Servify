package org.haidy.servify.presentation.screens.signup.compsable


import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import org.haidy.servify.app.resources.Resources
import org.haidy.servify.domain.model.Gender
import org.haidy.servify.presentation.composable.ServifyDropDownMenu
import org.haidy.servify.presentation.composable.ServifyTextField
import org.haidy.servify.presentation.screens.signup.SignupInteractionListener
import org.haidy.servify.presentation.screens.signup.SignupUiState

@Composable
fun ColumnScope.SecondScreenFields(state: SignupUiState, listener: SignupInteractionListener) {

    ServifyTextField(
        text = state.signUpForm.phoneNumber,
        hint = Resources.strings.phoneNumber,
        onValueChange = { listener.onPhoneNumberChanged(it) },
        errorMessage = state.errors.phoneNumberError,
        keyboardType = KeyboardType.Phone
    )

    val genders = listOf(Resources.strings.male, Resources.strings.female)
    ServifyDropDownMenu(
        onClickItem = { gender ->
            val selectedGender = when (gender) {
                genders[0] -> Gender.MALE
                genders[1] -> Gender.FEMALE
                else -> Gender.MALE
            }
            listener.onClickGender(selectedGender)
        },
        options = genders,
        hint = Resources.strings.gender,
        selectedText = when (state.signUpForm.selectedGender) {
            Gender.MALE -> Resources.strings.male
            Gender.FEMALE -> Resources.strings.female
            else -> ""
        },
        modifier = Modifier.padding(top = 16.dp)

    )

    ServifyDropDownMenu(
        onClickItem = { country -> listener.onClickCountry(country) },
        options = state.signUpForm.countries.map { it.name },
        hint = Resources.strings.country,
        selectedText = state.signUpForm.selectedCountry ?: "",
        modifier = Modifier.padding(top = 16.dp)

    )

    ServifyDropDownMenu(
        onClickItem = { governorate -> listener.onClickGovernorate(governorate) },
        options = state.signUpForm.governorates.map { it.name },
        hint = Resources.strings.governorate,
        selectedText = state.signUpForm.selectedGovernorate ?: "",
        modifier = Modifier.padding(top = 16.dp)

    )
}