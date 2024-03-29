package com.react_native;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;

import com.facebook.react.PackageList;
import com.facebook.react.ReactApplication;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.ReactPackage;
import com.facebook.react.config.ReactFeatureFlags;
import com.facebook.soloader.SoLoader;
import com.google.android.exoplayer2.util.Log;
import com.origins.onrewind.OnRewind;
import com.originsdigital.hbswidgets.core.HbsSdk;
import com.react_native.newarchitecture.MainApplicationReactNativeHost;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import com.origins.onrewind.domain.CompetitionConfiguration;
import com.react_native.onrewind.OnRewindPackage;

public class MainApplication extends Application implements ReactApplication {

  private final ReactNativeHost mReactNativeHost =
      new ReactNativeHost(this) {
        @Override
        public boolean getUseDeveloperSupport() {
          return BuildConfig.DEBUG;
        }

        @Override
        protected List<ReactPackage> getPackages() {
          @SuppressWarnings("UnnecessaryLocalVariable")
          List<ReactPackage> packages = new PackageList(this).getPackages();
          // Packages that cannot be autolinked yet can be added manually here, for example:
           packages.add(new OnRewindPackage());
          return packages;
        }

        @Override
        protected String getJSMainModuleName() {
          return "index";
        }
      };

  private final ReactNativeHost mNewArchitectureNativeHost =
      new MainApplicationReactNativeHost(this);

  @Override
  public ReactNativeHost getReactNativeHost() {
    if (BuildConfig.IS_NEW_ARCHITECTURE_ENABLED) {
      return mNewArchitectureNativeHost;
    } else {
      return mReactNativeHost;
    }
  }

  @Override
  public void onCreate() {
    super.onCreate();
    String accountKey = "uZknQc_1h";
    String preferredCompetition = PreferenceUtils.getCompetition(this);
    String preferredSeason = PreferenceUtils.getSeason(this);
    String competitionId = preferredCompetition != null ? preferredCompetition : "fu17wwc";
    String season = preferredSeason != null ? preferredSeason : "2022";
    String baseUrl = "https://hbs-web-fwc2022-sdk.akamaized.net/";
    HbsSdk.init(
            this,
            baseUrl,
            accountKey,
            competitionId,
            season
    );
    HbsSdk.closeMatchCenterWhenPlayerStarted(true);


    OnRewind.initialize(
           new OnRewind.InitParams.Builder()
                    .setApplicationContext(this)
                    .setBaseUrl(baseUrl)
                    .setAccountKey(accountKey)
                    .setCompetitionConfiguration(
                            new CompetitionConfiguration(competitionId, season)
                    )
                    .setAkamaiPrivateKey("PUT_YOUR_AKAMAI_KEY")
                    .build()
    );
    // If you opted-in for the New Architecture, we enable the TurboModule system
    ReactFeatureFlags.useTurboModules = BuildConfig.IS_NEW_ARCHITECTURE_ENABLED;
    SoLoader.init(this, /* native exopackage */ false);
    initializeFlipper(this, getReactNativeHost().getReactInstanceManager());
  }

  /**
   * Loads Flipper in React Native templates. Call this in the onCreate method with something like
   * initializeFlipper(this, getReactNativeHost().getReactInstanceManager());
   *
   * @param context
   * @param reactInstanceManager
   */
  private static void initializeFlipper(
      Context context, ReactInstanceManager reactInstanceManager) {
    if (BuildConfig.DEBUG) {
      try {
        /*
         We use reflection here to pick up the class that initializes Flipper,
        since Flipper library is not available in release mode
        */
        Class<?> aClass = Class.forName("com.react_native.ReactNativeFlipper");
        aClass
            .getMethod("initializeFlipper", Context.class, ReactInstanceManager.class)
            .invoke(null, context, reactInstanceManager);
      } catch (ClassNotFoundException e) {
        e.printStackTrace();
      } catch (NoSuchMethodException e) {
        e.printStackTrace();
      } catch (IllegalAccessException e) {
        e.printStackTrace();
      } catch (InvocationTargetException e) {
        e.printStackTrace();
      }
    }
  }
}
