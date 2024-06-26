package org.haidy.servify.app.resources.strings.en

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import org.haidy.servify.R
import org.haidy.servify.app.resources.strings.IStringResources

data class English(
    override val emailOrUsername: String = "Email or username",
    override val password: String = "Password",
    override val welcomeBack: String = "Welcome back!",
    override val doYouHaveAccount: String = "Welcome back, Do you have account?",
    override val doNotHaveAccount: String = "Don't have an account?",
    override val forgotPassword: String = "Forgot password?",
    override val login: String = "Login",
    override val signup: String = "Sign up",
    override val signUp: String = "Sign Up",
    override val user: String = "User",
    override val specialist: String = "Specialist",
    override val registerNewAccount: String = "Register new account",
    override val registerAs: String = "Register as",
    override val phoneNumber: String = "Phone number",
    override val register: String = "Register",
    override val username: String = "Username",
    override val email: String = "Email",
    override val skip: String = "Skip",
    override val facebook: String = "facebook",
    override val google: String = "google",
    override val confirm: String = "Confirm",
    override val confirmNewPassword: String = "Confirm new password",
    override val enterFourDigitCode: String = "Enter the 4-digit code",
    override val enterTheNewPassword: String = "Enter The New Password",
    override val enterYourEmailBelow: String = "Enter your email below, we’ll send you a verify code",
    override val forgetPassword: String = "Forget password",
    override val newPassword: String = "New password",
    override val resendCode: String = "Resend code",
    override val resetPassword: String = "Reset password",
    override val send: String = "Send",
    override val theVerifyCodeWillExpireIn: String = "The verification code will be expired in",
    override val emailIsRequired: String = "Please enter the email",
    override val invalidEmail: String = "Please enter a valid email",
    override val invalidPassword: String = "Please enter a password with a minimum length of 8 characters",
    override val invalidPhoneNumber: String = "Please enter a valid phone number with at least 11 digits",
    override val passwordIsRequired: String = "Please enter the password",
    override val phoneNumberIsRequired: String = "Please enter the phone number",
    override val invalidCredentials: String = "Invalid credentials",
    override val requestFailed: String = "Request failed",
    override val enterTheVerifyCode: String = "Enter the verify code",
    override val explore: String = "Explore",
    override val verified: String = "Verified",
    override val weSentVerificationCodeTo: String = "We sent a verification code to",
    override val yourAccountIsVerified: String = "Your account is verified",
    override val hello: String = "Hello!",
    override val helloPageDescription: String = "Welcome!!!",
    override val arrangement: String = "Arrangement",
    override val arrangementPageDescription: String = "Easily arrange work order for you to easily manage",
    override val solving: String = "Solving",
    override val solvingPageDescription: String = "It has never been easier to complete tasks. Get started with us!",
    override val getStarted: String = "Get Started",
    override val addPhoto: String = "Add Photo",
    override val addPhotoDescription: String = "Add photo and show face to complete your account details ",
    override val addYourPhoto: String = "Add Your Photo",
    override val continueSteps: String = "Continue",
    override val contactUs: String = "Contact us",
    override val editProfile: String = "Edit profile",
    override val helpAndSupport: String = "Help & Support",
    override val language: String = "Language",
    override val logout: String = "Logout",
    override val notifications: String = "Notifications",
    override val privacyPolicy: String = "Privacy policy",
    override val settings: String = "Settings",
    override val theme: String = "Theme",
    override val updatePassword: String = "Update password",
    override val address: String = "Address",
    override val arabic: String = "Arabic",
    override val country: String = "Country",
    override val darkMode: String = "Dark mode",
    override val egyptian: String = "Egyptian",
    override val english: String = "English",
    override val gender: String = "Gender",
    override val lightMode: String = "Light mode",
    override val off: String = "OFF",
    override val on: String = "ON",
    override val suggested: String = "Suggested",
    override val systemMode: String = "System mode",
    override val chooseTheme: String = "Choose Theme",
    override val cancel: String = "Cancel",
    override val oldPassword: String = "Old password",
    override val passwordDoesNotMatch: String = "Password and confirm password doesn't match",
    override val bestSpecialists: String = "Best specialists",
    override val helloUser: String = "Hello, ",
    override val howCanWeHelpYou: String = "How can we help you?",
    override val offers: String = "Offers",
    override val search: String = "Search....",
    override val services: String = "Services",
    override val showMore: String = "Show More",
    override val usernameShouldBeAtLeastFiveLength: String = "Username should have a minimum length of 5 characters",
    override val pleaseSelectAnImage: String = "Please select an image",
    override val profile: String = "Profile",
    override val confirmPassword: String = "Confirm password",
    override val governorate: String = "Governorate",
    override val alreadyHaveAccount: String = "Do you have an account?",
    override val signIn: String = "Sign in",
    override val female: String = "Female",
    override val male: String = "Male",
    override val countryIsRequired: String = "Please select the country",
    override val governorateIsRequired: String = "Please select the governorate",
    override val imageUpdatedSuccessfully: String = "Image updated successfully",
    override val nearestSpecialists: String = "Nearest specialists",
    override val canceled: String = "Canceled",
    override val completed: String = "Completed",
    override val upcoming: String = "Upcoming",
    override val booking: String = "Booking",
    override val addRating: String = "Add Rating",
    override val rebook: String = "Re-Book",
    override val reschedule: String = "Reschedule",
    override val addReasonHere: String = "Add reason here",
    override val anotherReason: String = "Another reason",
    override val average: String = "Average",
    override val bookingCancellation: String = "Booking Cancellation",
    override val didYouEncounterProblemWithService: String = "Did you encounter a problem with the service ?",
    override val excellent: String = "Excellent",
    override val fair: String = "Fair",
    override val feedback: String = "Feedback",
    override val good: String = "Good",
    override val haveYouDiscoveredBetterOptions: String = "Have you discovered better options for you ?",
    override val haveYouExperiencedChanges: String = "Have you experienced changes in your personal circumstances ?",
    override val howWouldYouRateExperienceAndService: String = "How would you rate the experience and service ?",
    override val poor: String = "Poor",
    override val submitFeedback: String = "Submit feedback",
    override val travelPlans: String = "Travel plans",
    override val unexpectedFinancialProblems: String = "Are you facing unexpected financial problems?",
    override val whyDoYouWantToCancel: String = "Why do you want to cancel this specialist's reservation?",
    override val writeFeedbackHere: String = "Write feedback here",
    override val feedbackSubmittedSuccessfully: String = "Feedback submitted successfully",
    override val orderCancelled: String = "Order Cancelled!",
    override val addCard: String = "Add Card",
    override val addPaymentMethod: String = "Add Payment Method",
    override val addService: String = "Add service",
    override val am: String = "Am",
    override val pm: String = "Pm",
    override val bookAppointment: String = "Book Appointment",
    override val bookingSuccessful: String = "Booking Successful!",
    override val cardHolder: String = "Card Holder",
    override val cards: String = "Cards",
    override val cash: String = "Cash",
    override val chooseService: String = "Choose Service",
    override val day: String = "Day",
    override val done: String = "Done",
    override val expires: String = "Expires",
    override val message: String = "Message",
    override val paymentOption: String = "Payment Option",
    override val paymentSuccess: String = "Payment Success",
    override val proceed: String = "Proceed",
    override val requiredTasks: String = "Required tasks",
    override val save: String = "Save",
    override val thePrice: String = "The price",
    override val time: String = "Time",
    override val totalPay: String = "Total Pay",
    override val viewBooking: String = "View Booking",
    override val writeHere: String = "Write here",
    override val price: String = "Price",
    override val bookNow: String = "Book now",
    override val expiryDate: String = "Expiry Date",
    override val securityCode: String = "Security Code",
    override val name: String = "Name",
    override val discount: String = "Discount",
    override val egp: String = "EGP",
    override val great: String = "Great",
    override val payDate: String = "Pay Date",
    override val payTime: String = "Pay Time",
    override val totalAmount: String = "Total Amount",
    override val paymentMode: String = "Payment Mode",
    override val applyFilter: String = "Apply filter",
    override val filter: String = "Filter",
    override val noResultPleaseTryAgain: String = "No result, please try again !",
    override val resetFilter: String = "Reset filter",
    override val result: String = "Result",
    override val titleSearch: String = "Search",
    override val rating: String = "Rating",
    override val chats: String = "Chats",
    override val typeMessageHere: String = "Type message here...",

) : IStringResources {
    override fun bookingSuccessMessage(
        name: String,
        serviceName: String,
        serviceDate: String
    ): AnnotatedString {
        return buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    fontFamily = FontFamily(Font(R.font.sf_pro_display_regular)),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                )
            ) {
                append("Dear $name you have successfully scheduled booking of ")
            }
            withStyle(
                style = SpanStyle(
                    fontFamily = FontFamily(Font(R.font.sf_pro_display_regular)),
                    fontWeight = FontWeight.Bold,
                    fontSize =  16.sp,
                )
            ) {
                append(serviceName)
            }
            withStyle(
                style = SpanStyle(
                    fontFamily = FontFamily(Font(R.font.sf_pro_display_regular)),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                )
            ) {
                append(" for the upcoming ")
            }
            withStyle(
                style = SpanStyle(
                    fontFamily = FontFamily(Font(R.font.sf_pro_display_regular)),
                    fontWeight = FontWeight.Bold,
                    fontSize =  16.sp,
                )
            ) {
                append("$serviceDate.")
            }
        }
    }
}
