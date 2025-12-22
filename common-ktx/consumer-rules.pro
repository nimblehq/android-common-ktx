# Add project specific ProGuard rules for library consumers here.
# Rules in this file will be applied to library consumers.

# Keep extension functions
-keepclassmembers class co.nimblehq.common.extensions.** {
    public *;
}
