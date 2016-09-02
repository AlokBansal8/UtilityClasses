package com.github.alokagrawal8.utilityclasses;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.telephony.TelephonyManager;

@SuppressWarnings({ "unused", "WeakerAccess" }) public final class DeviceInfo {

  private DeviceInfo() {
    throw new AssertionError("No instance please");
  }

  /**
   * The user-visible SDK version of the framework; its possible values are defined in {@link
   * Build.VERSION_CODES}.
   */
  @IntRange(from = 1, to = 24) public static int getSystemVersion() {
    return Build.VERSION.SDK_INT;
  }

  /** Returns the name of Operating System */
  @NonNull public static String getSystemName() {
    return "Android OS";
  }

  /** The manufacturer and the end-user-visible name for the product/hardware. */
  @NonNull public static String getDeviceModel() {
    return String.format("mfr=%s,model=%s", Build.MANUFACTURER, Build.MODEL);
  }

  @NonNull public static String getSdkVersion() {
    return String.format(".%s-android", "cf6e19e891637298d5995f1f877b10199f23b2ba".substring(0, 8));
  }

  /** The consumer-visible brand with which the product/hardware will be associated, if any. */
  @NonNull public static String getCarrierName() {
    return Build.BRAND;
  }

  /**
   * Returns the version string for the radio firmware.  May return null (if, for instance, the
   * radio is not currently on).
   */
  @Nullable @RequiresApi(api = 14) public static String getNetworkStatus() {
    return Build.getRadioVersion();
  }

  /**
   * Return the package name of the Application as specified in Manifest or build.gradle file.
   *
   * @param context Android's {@link Context} object.
   */
  @NonNull public static String getApplicationIdentifier(@NonNull final Context context) {
    final Context appContext = context.getApplicationContext();
    return appContext.getPackageName();
  }

  /**
   * Returns the version name of the Application as specified in Manifest or build.gradle file.
   *
   * @param context Android's {@link Context} object.
   */
  @NonNull public static String getApplicationVersion(@NonNull final Context context) {
    final Context appContext = context.getApplicationContext();
    try {
      return appContext.getPackageManager()
          .getPackageInfo(appContext.getPackageName(), 0).versionName;
    } catch (final PackageManager.NameNotFoundException ignored) {
      return "unknown";
    }
  }

  /**
   * Returns the unique device ID, for example, the IMEI for GSM and the MEID or ESN for CDMA
   * phones. Return null if device ID is not available.
   *
   * @param context Android's {@link Context} object.
   */
  @Nullable @SuppressLint({ "HardwareIds", "CommitPrefEdits" })
  public static String getDeviceIdentifier(@NonNull final Context context) {
    final Context appContext = context.getApplicationContext();
    final SharedPreferences prefs =
        appContext.getSharedPreferences("Package.$.A." + getApplicationIdentifier(appContext), 0);
    if (null == prefs.getString("uuid", null)) {
      final String imei =
          ((TelephonyManager) appContext.getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
      prefs.edit().putString("uuid", imei).commit();
    }
    return prefs.getString("uuid", null);
  }
}
