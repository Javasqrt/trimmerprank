package com.byanton.trimmerprank;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;

import android.widget.TextView;
import com.google.android.gms.ads.AdLoader;

import com.google.android.gms.ads.AdRequest;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.NativeExpressAdView;
import com.google.android.gms.ads.formats.MediaView;
import com.google.android.gms.ads.formats.UnifiedNativeAd;
import com.google.android.gms.ads.formats.UnifiedNativeAdView;
public class HomeActivity extends AppCompatActivity {


    Button start;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.home_activity);
        MobileAds.initialize(this);

        AdLoader adLoader = new AdLoader.Builder(this,"ca-app-pub-3940256099942544/2247696110")
                .forUnifiedNativeAd(new UnifiedNativeAd.OnUnifiedNativeAdLoadedListener() {
                    @Override
                    public void onUnifiedNativeAdLoaded(UnifiedNativeAd unifiedNativeAd) {
                        UnifiedNativeAdView unifiedNativeAdView = (UnifiedNativeAdView) getLayoutInflater().inflate(R.layout.native_ads_view,null);
                        mapUnifiedNativeAdToLayout(unifiedNativeAd,unifiedNativeAdView);

                        FrameLayout nativeadslayout = findViewById(R.id.native_ads_position);
                        nativeadslayout.removeAllViews();
                        nativeadslayout.addView(unifiedNativeAdView);
                    }
                })
                .build();

        adLoader.loadAd(new AdRequest.Builder().build());
        start = (Button) findViewById(R.id.startmain);
        start.setVisibility(View.GONE);
                start.setVisibility(View.VISIBLE);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this,MainActivity.class);
                startActivity(intent);


            }
        });

    }
    public void mapUnifiedNativeAdToLayout(UnifiedNativeAd adFormatGoogle, UnifiedNativeAdView adView){

        adView.setMediaView((MediaView) adView.findViewById(R.id.native_media));
        adView.setBodyView(adView.findViewById(R.id.native_tv));
        adView.setCallToActionView(adView.findViewById(R.id.native_btn));
            if(adFormatGoogle.getBody() == null){
                adView.getBodyView().setVisibility(View.GONE);
            }
            else {
                ((TextView)adView.getBodyView()).setText((adFormatGoogle.getBody()));
            }
            if (adFormatGoogle.getCallToAction() == null){
                adView.getCallToActionView().setVisibility(View.GONE);
            }
            else{
                ((Button)adView.getCallToActionView()).setText(adFormatGoogle.getCallToAction());
            }
            if(adFormatGoogle.getMediaContent() == null){
                adView.getMediaView().setVisibility(View.GONE);
            }
            else{
                adView.getMediaView().setMediaContent(adFormatGoogle.getMediaContent());
            }
            adView.setNativeAd(adFormatGoogle);

    }
}
