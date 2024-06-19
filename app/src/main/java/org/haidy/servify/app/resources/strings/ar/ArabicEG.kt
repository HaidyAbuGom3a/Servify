package org.haidy.servify.app.resources.strings.ar

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

data class ArabicEG(
    override val emailOrUsername: String = "الإيميل أو اسم المستخدم",
    override val password: String = "كلمة السر",
    override val welcomeBack: String = "أهلاً و سهلاً!",
    override val doYouHaveAccount: String = "نورتنا، عندك حساب؟ سجل دلوقتي!",
    override val doNotHaveAccount: String = "معندكش حساب؟",
    override val forgotPassword: String = "نسيت كلمة السر؟",
    override val login: String = "سجل دخول",
    override val signup: String = "سجل حساب جديد",
    override val signUp: String = "سجل حساب جديد",
    override val user: String = "مستخدم",
    override val specialist: String = "متخصص",
    override val registerNewAccount: String = "سجل حساب جديد",
    override val registerAs: String = "سجل كـ",
    override val phoneNumber: String = "رقم الموبايل",
    override val register: String = "تسجيل",
    override val username: String = "اسم المستخدم",
    override val email: String = "الإيميل",
    override val skip: String = "تخطي",
    override val google: String = "جوجل",
    override val facebook: String = "فيسبوك",
    override val confirm: String = "تأكيد",
    override val confirmNewPassword: String = "تأكيد كلمة السر",
    override val enterFourDigitCode: String = "دخل الكود اللي من أربع أرقام",
    override val enterTheNewPassword: String = "دخل كلمة السر الجديدة",
    override val enterYourEmailBelow: String = "دخل إيميلك هنا، هنبعتلك عليه كود تأكيد",
    override val forgetPassword: String = "نسيت كلمة السر",
    override val newPassword: String = "كلمة السر الجديدة",
    override val resendCode: String = "إبعت الكود تاني",
    override val resetPassword: String = "تغيير كلمة السر",
    override val send: String = "إرسال",
    override val theVerifyCodeWillExpireIn: String = "هتنتهي صلاحية الكود ده بعد",
    override val emailIsRequired: String = "الإيميل مطلوب",
    override val invalidEmail: String = "لو سمحت دخل إيميل صحيح",
    override val invalidPassword: String = "كلمة السر لازم تكون على الأقل ٨ حروف",
    override val invalidPhoneNumber: String = "رقم الموبايل مش صالح، لازم يكون مكون من ١١ رقم على الأقل",
    override val passwordIsRequired: String = "لو سمحت دخل كلمة السر",
    override val phoneNumberIsRequired: String = "لو سمحت دخل رقم الموبايل",
    override val invalidCredentials: String = "الإيميل أو كلمة السر غلط",
    override val requestFailed: String = "حصل خطأ في الإرسال",
    override val enterTheVerifyCode: String = "دخل كود التفعيل",
    override val explore: String = "إستكشف",
    override val verified: String = "تم التحقق",
    override val weSentVerificationCodeTo: String = "بعتنا كود التفيل لـ",
    override val yourAccountIsVerified: String = "تم تفعيل حسابك",
    override val hello: String = "أهلا!",
    override val solving: String = "حل",
    override val arrangement: String = "تنظيم",
    override val helloPageDescription: String = "يا مرحب بيك!!!",
    override val arrangementPageDescription: String = "نظمنالك ترتيب شغلك، عشان تعرف تديره بسهوله",
    override val solvingPageDescription: String = "دلوقتي تقدر تنجز مهامك بأسهل شكل، ابدأ معانا!",
    override val getStarted: String = "يلا نبدأ",
    override val addPhoto: String = "إضافة صورة",
    override val addPhotoDescription: String = "ضيف صورتك و اظهر وشك عشان تكتمل تفاصيل حسابك",
    override val addYourPhoto: String = "ضيف صورتك",
    override val continueSteps: String = "كمِّل",
    override val contactUs: String = "تواصل معانا",
    override val editProfile: String = "تعديل الحساب",
    override val helpAndSupport: String = "المساعدة و الدعم",
    override val language: String = "اللغة",
    override val logout: String = "تسجيل الخروج",
    override val notifications: String = "الإشعارات",
    override val privacyPolicy: String = "سياسة الخصوصية",
    override val settings: String = "الإعدادات",
    override val theme: String = "السِمة",
    override val updatePassword: String = "تحديث كلمة السر",
    override val arabic: String = "عربي",
    override val country: String = "البلد",
    override val darkMode: String = "الوضع الليلي",
    override val egyptian: String = "مصري",
    override val english: String = "إنجليزي",
    override val gender: String = "النوع",
    override val lightMode: String = "الوضع النهاري",
    override val off: String = "مش مٌفعل",
    override val on: String = "مٌفعل",
    override val suggested: String = "الإقتراحات",
    override val systemMode: String = "وضع النظام",
    override val address: String = "العنوان",
    override val cancel: String = "إلغاء",
    override val chooseTheme: String = "اختار السمة",
    override val oldPassword: String = "كلمة السر القديمة",
    override val passwordDoesNotMatch: String = "كلمة السر و تأكيد كلمة السر مش زي بعض",
    override val showMore: String = "اظهر الباقي",
    override val offers: String = "العروض",
    override val services: String = "الخدمات",
    override val bestSpecialists: String = "احسن المتخصصين",
    override val howCanWeHelpYou: String = "ازاي نقدر نساعدك؟",
    override val helloUser: String = "أهلاً، ",
    override val search: String = "بحث...",
    override val usernameShouldBeAtLeastFiveLength: String = "اسم المستخدم لازم يكون على الأقل ٥ حروف",
    override val pleaseSelectAnImage: String = "اختار صورة لو سمحت",
    override val profile: String = "الحساب",
    override val confirmPassword: String = "تأكيد كلمة السر",
    override val governorate: String = "المحافظة",
    override val signIn: String = "سجل دخول",
    override val alreadyHaveAccount: String = "عندك حساب؟",
    override val male: String = "ذكر",
    override val female: String = "أنثى",
    override val countryIsRequired: String = "لو سمحت اختار البلد",
    override val governorateIsRequired: String = "لو سمحت اختار المحافظة",
    override val imageUpdatedSuccessfully: String = "الصورة اتحدثت بنجاح",
    override val nearestSpecialists: String = "أقرب المتخصصين",
    override val canceled: String = "الملغية",
    override val completed: String = "المكتملة",
    override val upcoming: String = "الجاية",
    override val booking: String = "الحجز",
    override val addRating: String = "ضيف تقييم",
    override val rebook: String = "احجز تاني",
    override val reschedule: String = "غير المعاد",
    override val addReasonHere: String = "اكتب السبب هنا",
    override val anotherReason: String = "سبب تاني",
    override val average: String = "متوسط",
    override val bookingCancellation: String = "إلغي الحجز",
    override val didYouEncounterProblemWithService: String = "واجهت مشكلة مع الخدمة؟",
    override val excellent: String = "ممتاز",
    override val fair: String = "مقبول",
    override val feedback: String = "ملاحظات",
    override val good: String = "كويس",
    override val haveYouDiscoveredBetterOptions: String = "اكتشفت خيارات أحسن ليك؟",
    override val haveYouExperiencedChanges: String = "واجهت تغييرات في ظروفك الشخصية؟",
    override val howWouldYouRateExperienceAndService: String = "أيه تقييمك للخدمة",
    override val poor: String = "ضعيف",
    override val submitFeedback: String = "إرسل الملاحظات",
    override val travelPlans: String = "خطط السفر",
    override val unexpectedFinancialProblems: String = "بتواجه مشاكل مالية غير متوقعة؟",
    override val whyDoYouWantToCancel: String = "ليه عايز تلغي الحجز مع المتخصص ده؟",
    override val writeFeedbackHere: String = "اكتب ملاحظاتك هنا",
    override val feedbackSubmittedSuccessfully: String = "ملاحظاتك وصلت بنجاح",
    override val orderCancelled: String = "الحجز اتلغي بنجاح",
    override val addCard: String = "ضيف البطاقة",
    override val addPaymentMethod: String = "إضافة طريقة الدفع",
    override val addService: String = "ضيف خدمة",
    override val am: String = "صباحاً",
    override val pm: String = "مساءاً",
    override val bookAppointment: String = "حجز ميعاد",
    override val bookingSuccessful: String = "الحجز تم بنجاح!",
    override val cardHolder: String = "حامل البطاقة",
    override val cards: String = "البطاقات",
    override val cash: String = "كاش",
    override val chooseService: String = "اختر خدمة",
    override val day: String = "اليوم",
    override val done: String = "إنهاء",
    override val expires: String = "تاريخ الانتهاء",
    override val message: String = "الرسالة",
    override val paymentOption: String = "طريقة الدفع",
    override val paymentSuccess: String = "طريقة دفع ناجحة",
    override val proceed: String = "كمل",
    override val requiredTasks: String = "المهام المطلوبة",
    override val save: String = "حفظ",
    override val thePrice: String = "السعر",
    override val time: String = "الوقت",
    override val totalPay: String = "إجمالي المبلغ",
    override val viewBooking: String = "اعرض الحجز",
    override val writeHere: String = "اكتب هنا",
    override val price: String = "السعر",
    override val bookNow: String = "احجز دلوقتي",
    override val expiryDate: String = "تاريخ الإنتهاء",
    override val securityCode: String = "الرقم السري",
    override val name: String = "الاسم",
    override val discount: String = "خصم",
    override val egp: String = "جنيه مصري",
    override val great: String = "عظمة",
    override val payDate: String = "تاريخ الدفع",
    override val payTime: String = "ميعاد الدفع",
    override val totalAmount: String = "إجمالي الدفع",
    override val paymentMode: String = "طريقة الدفع",
    override val applyFilter: String = "تطبيق الفلترة",
    override val filter: String = "الفلترة",
    override val noResultPleaseTryAgain: String = "مفيش نتيجة، حاول تاني من فضلك",
    override val resetFilter: String = "إعادة تعيين",
    override val result: String = "النتايج",
    override val titleSearch: String = "بحث",
    override val rating: String = "التقييم",
    override val chats: String = "المحادثات",
    override val typeMessageHere: String = "اكتب رسالتك هنا...",
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
                append(" عميلنا $name حبينا نبلغك أن تم بنجاح حجز معاد لخدمة ")
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
                append(" بميعاد ")
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