package com.example.lviana.exoplayerfm;

import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultAllocator;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

public class MainActivity extends AppCompatActivity {

    private static final Uri SOURCE = Uri.parse("https://stream.vagalume.fm/hls/14660048951600695455/aac.m3u8");

    private boolean isReady;
    private EventLogger eventLogger;
    private SimpleExoPlayer player;
    private Uri source;
    private MediaSource mediaSource;
    private final int minLoadableRetryCount = 1;
    private Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = getApplicationContext();
        set();

    }

    private void set() {
        Handler mainHandler = new Handler();
        DefaultBandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
        eventLogger = new EventLogger();
        TrackSelection.Factory videoTrackSelectionFactory = new AdaptiveTrackSelection.Factory(bandwidthMeter);
        DefaultTrackSelector trackSelector = new DefaultTrackSelector(videoTrackSelectionFactory);
        DefaultAllocator allocator = new DefaultAllocator(true, C.DEFAULT_BUFFER_SEGMENT_SIZE);
		/*
			Valores definidos para melhor configuração de buffer
			DEFAULT_MIN_BUFFER_MS = 360000
			DEFAULT_MAX_BUFFER_MS = 600000

			Valores default do ExoPlayer2
			DEFAULT_BUFFER_FOR_PLAYBACK_MS = 2500
			DEFAULT_BUFFER_FOR_PLAYBACK_AFTER_REBUFFER_MS = 5000
		*/
        DefaultLoadControl loadControl = new DefaultLoadControl(allocator, 360000, 600000, 2500, 5000, -1, true);
        player = ExoPlayerFactory.newSimpleInstance(mContext, trackSelector, loadControl);

        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(mContext,
                Util.getUserAgent(mContext, "ExoPlayer2"), bandwidthMeter);

        ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();

        mediaSource = new HlsMediaSource(SOURCE, dataSourceFactory, minLoadableRetryCount, mainHandler, eventLogger);
        player.addMetadataOutput(eventLogger);
        player.addListener(eventLogger);

        if (player != null) {
            if (!isReady) {
                player.seekTo(60000);
                player.prepare(mediaSource);
                isReady = true;
            }

            player.setPlayWhenReady(true);
        }
    }
}
