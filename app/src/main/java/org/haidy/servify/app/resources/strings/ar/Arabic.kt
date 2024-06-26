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

data class Arabic(
    override val emailOrUsername: String = "البريد الالكتروني أو اسم المستخدم",
    override val password: String = "كلمة السر",
    override val welcomeBack: String = "أهلاً بعودتك!",
    override val doYouHaveAccount: String = "مرحباً، هل لديك حساب؟",
    override val doNotHaveAccount: String = "ليس لديك حساب؟",
    override val forgotPassword: String = "هل نسيت كلمة السر؟",
    override val login: String = "تسجيل دخول",
    override val signup: String = "إنشاء حساب",
    override val signUp: String = "إنشاء حساب",
    override val user: String = "مستخدم",
    override val specialist: String = "متخصص",
    override val registerNewAccount: String = "سجل جساب جديد",
    override val registerAs: String = "سجل كـ",
    override val phoneNumber: String = "رقم الهاتف",
    override val register: String = "تسجيل",
    override val email: String = "البريد الإلكتروني",
    override val username: String = "اسم المستخدم",
    override val skip: String = "تخطي",
    override val google: String = "جوجل",
    override val facebook: String = "فيسبوك",
    override val confirm: String = "تأكيد",
    override val confirmNewPassword: String = "أعد كتابة كلمة السر الجديدة",
    override val enterFourDigitCode: String = "أدخل الرمز المكون من أربع أرقام",
    override val enterTheNewPassword: String = "أدخل كلمة السر الجديدة",
    override val enterYourEmailBelow: String = "أدخل بريدك الإلكتروني، سنرسل لك رمز تأكيد",
    override val forgetPassword: String = "نسيت كلمة السر",
    override val newPassword: String = "كلمة السر الجديدة",
    override val resendCode: String = "أعد إرسال الرمز",
    override val resetPassword: String = "إعادة تعيين كلمة السر",
    override val send: String = "إرسال",
    override val theVerifyCodeWillExpireIn: String = "ستنتهي صلاحية رمز التأكيد خلال",
    override val emailIsRequired: String = "من فضلك ادخل البريد الالكتروني",
    override val invalidEmail: String = "البريد الالكتروني غير صالح",
    override val invalidPassword: String = "من فضلك أدخل كلمه سر مكونة من ثمانية أحرف على الأقل",
    override val invalidPhoneNumber: String = "من فضلك أخل رقم هاتف مكون من أحد عشر رقماً",
    override val passwordIsRequired: String = "من فضلك أدخل كلمة السر",
    override val phoneNumberIsRequired: String = "من فضلك أدخل رقم الهاتف",
    override val requestFailed: String = "حدث خطأ في الإرسال",
    override val invalidCredentials: String = "خطأ في البريد الإلكتروني أو كلمة السر",
    override val enterTheVerifyCode: String = "أدخل رمز التحقق",
    override val explore: String = "إستكشف",
    override val verified: String = "تم التحقق",
    override val weSentVerificationCodeTo: String = "أرسلنا رمز التحقق إلى",
    override val yourAccountIsVerified: String = "لقد تم التحقق من حسابك",
    override val hello: String = "مرحباً",
    override val solving: String = "حل",
    override val arrangement: String = "تنظيم",
    override val helloPageDescription: String = "أهلا بك!!!",
    override val arrangementPageDescription: String = "نظمنا ترتيب العمل من أجلك، لتسهل إدراته بسهولة",
    override val solvingPageDescription: String = "أصبح إنجاز المهمام أسهل من أي وقت مضى، فلتبدأ معنا!",
    override val getStarted: String = "فلنبدأ",
    override val addPhoto: String = "إضافة صورة",
    override val addPhotoDescription: String = "أضف صورة و اظهر وجهك لإكمال تفاصيل حسابك",
    override val addYourPhoto: String = "أضف صورتك",
    override val continueSteps: String = "استمرار",
    override val contactUs: String = "تواصل معنا",
    override val editProfile: String = "تعديل الحساب",
    override val helpAndSupport: String = "المساعدة و الدعم",
    override val language: String = "اللغة",
    override val logout: String = "تسجيل الخروج",
    override val notifications: String = "الإشعارات",
    override val privacyPolicy: String = "سياسة الخصوصية",
    override val settings: String = "الإعدادات",
    override val theme: String = "السِمة",
    override val updatePassword: String = "تحديث كلمة السر",
    override val arabic: String = "العربية",
    override val country: String = "البلد",
    override val darkMode: String = "الوضع الليلي",
    override val egyptian: String = "مصري",
    override val english: String = "الإنجليزية",
    override val gender: String = "النوع",
    override val lightMode: String = "الوضع النهاري",
    override val off: String = "غير مٌفعل",
    override val on: String = "مٌفعل",
    override val suggested: String = "المٌقترح",
    override val systemMode: String = "وضع النظام",
    override val address: String = "العنوان",
    override val cancel: String = "إلغاء",
    override val chooseTheme: String = "اختر السمة",
    override val oldPassword: String = "كلمة السر القديمة",
    override val passwordDoesNotMatch: String = "كلمة السر و تأكيد كلمة السر ليسا متطابقين",
    override val bestSpecialists: String = "أفضل المتخصصين",
    override val services: String = "الخدمات",
    override val offers: String = "العروض",
    override val howCanWeHelpYou: String = "كيف يمكننا مساعدتك؟",
    override val helloUser: String = "مرحباً، ",
    override val search: String = "بحث...",
    override val showMore: String = "عرض المزيد",
    override val usernameShouldBeAtLeastFiveLength: String = "اسم المستخدم يجب أن يكون على الأقل خمسة أحرف",
    override val pleaseSelectAnImage: String = "اختر صورة من فضلك",
    override val profile: String = "الحساب",
    override val confirmPassword: String = "تأكيد كلمة السر",
    override val governorate: String = "المحافظة",
    override val alreadyHaveAccount: String = "لديك حساب بالفعل؟",
    override val signIn: String = "سجل الدخول",
    override val female: String = "أنثى",
    override val male: String = "ذكر",
    override val countryIsRequired: String = "من فضلك اختر البلد",
    override val governorateIsRequired: String = "من فضلك اختر المحافظة",
    override val imageUpdatedSuccessfully: String = "تم تحديث الصورة بنجاح",
    override val nearestSpecialists: String = "أقرب المتخصصين",
    override val canceled: String = "الملغاة",
    override val completed: String = "المكتملة",
    override val upcoming: String = "القادمة",
    override val booking: String = "الحجز",
    override val addRating: String = "اضف تقييم",
    override val rebook: String = "احجز مجدداً",
    override val reschedule: String = "اعد تنظيم الموعد",
    override val addReasonHere: String = "أضف السبب هنا",
    override val anotherReason: String = "سبب آخر",
    override val average: String = "متوسط",
    override val bookingCancellation: String = "إلغاء الحجز",
    override val didYouEncounterProblemWithService: String = "هل واجهت مشكلة مع الخدمة؟",
    override val excellent: String = "ممتاز",
    override val fair: String = "مقبول",
    override val feedback: String = "ملاحظات",
    override val good: String = "جيد",
    override val haveYouDiscoveredBetterOptions: String = "هل اكتشفت خيارات أفضل لك؟",
    override val haveYouExperiencedChanges: String = "هل واجهت تغييرات في ظروفك الشخصية؟",
    override val howWouldYouRateExperienceAndService: String = "كيف تقيم تجربتك مع الخدمة؟",
    override val poor: String = "ضعيف",
    override val submitFeedback: String = "إرسال الملاحظات",
    override val travelPlans: String = "خطط السفر",
    override val unexpectedFinancialProblems: String = "هل تواجه مشاكل مالية غير متوقعة؟",
    override val whyDoYouWantToCancel: String = "لماذا تريد إلغاء حجز هذا المتخصص؟",
    override val writeFeedbackHere: String = "اكتب ملاحظاتك هنا",
    override val feedbackSubmittedSuccessfully: String = "لقد تم إرسال ملاحظاتك بنجاح",
    override val orderCancelled: String = "لقد تم إلغاء الحجز بنجاح",
    override val addCard: String = "اضف بطاقة",
    override val addPaymentMethod: String = "إضافة طريقة الدفع",
    override val addService: String = "اضف خدمة",
    override val am: String = "صباحاً",
    override val pm: String = "مساءاً",
    override val bookAppointment: String = "حجز موعد",
    override val bookingSuccessful: String = "تم الحجز بنجاح!",
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
    override val proceed: String = "إكمال",
    override val requiredTasks: String = "المهام المطلوبة",
    override val save: String = "حفظ",
    override val thePrice: String = "السعر",
    override val time: String = "الوقت",
    override val totalPay: String = "إجمالي المبلغ",
    override val viewBooking: String = "اعرض الحجز",
    override val writeHere: String = "اكتب هنا",
    override val price: String = "السعر",
    override val bookNow: String = "احجز الآن",
    override val expiryDate: String = "تاريخ الإنتهاء",
    override val securityCode: String = "الرقم السري",
    override val name: String = "الاسم",
    override val discount: String = "خصم",
    override val egp: String = "جنيهاً مصرياً",
    override val great: String = "عظيم",
    override val payDate: String = "تاريخ الدفع",
    override val payTime: String = "موعد الدفع",
    override val totalAmount: String = "إجمالي الدفع",
    override val paymentMode: String = "وسيلة الدفع",
    override val applyFilter: String = "تطبيق الفلترة",
    override val filter: String = "الفلترة",
    override val noResultPleaseTryAgain: String = "لا يوجد نتيجة، حاول مجدداً من فضلك",
    override val resetFilter: String = "إعادة تعيين",
    override val result: String = "النتائج",
    override val titleSearch: String = "بحث",
    override val rating: String = "التقييم",
    override val chats: String = "المحادثات",
    override val typeMessageHere: String = "اكتب رسالتك هنا...",
    ) : IStringResources{
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
                append("عزيزنا $name لقد تم بنجاح حجز موعد لخدمة ")
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
                append(" تحديداً في ")
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