# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in C:\Users\santosh\AppData\Local\Android\Sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}
-keep class android.support.v4.** { *; }
-dontwarn android.support.v4.**
-dontwarn javax.activation.**
-dontwarn javax.security.**
-dontwarn java.awt.**
-libraryjars <java.home>/lib/rt.jar
-keep class javax.** {*;}
-keep class com.sun.** {*;}
-keep class myjava.** {*;}
-keep class org.apache.harmony.** {*;}
-keep public class Mail {*;}
-keep class * {
    public private *;}
-keep class com.google.android.gms.** { *; }
-dontwarn com.google.android.gms.**
-dontwarn okio.**

-dontshrink