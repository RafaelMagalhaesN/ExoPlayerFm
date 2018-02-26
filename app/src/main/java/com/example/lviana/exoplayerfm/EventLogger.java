package com.example.lviana.exoplayerfm;

import android.util.Log;

import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.metadata.MetadataOutput;
import com.google.android.exoplayer2.metadata.id3.TextInformationFrame;
import com.google.android.exoplayer2.source.AdaptiveMediaSourceEventListener;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.upstream.DataSpec;

import java.io.IOException;

/**
 * Created by lviana on 26/02/18.
 */

public class EventLogger extends Player.DefaultEventListener implements Player.EventListener,
        MetadataOutput, AdaptiveMediaSourceEventListener {

    private static final String TAG = "VAGALUME.FM - LOGGERS";
    public EventLogger() {
    }

    @Override
    public void onMetadata(Metadata metadata) {
        for (int i = 0; i < metadata.length(); i++) {
            Metadata.Entry entry = metadata.get(i);
            if (entry instanceof TextInformationFrame) {
                TextInformationFrame textInformationFrame = (TextInformationFrame) entry;
                Log.e(TAG, "onMetadata: [ "+ textInformationFrame.id +" - "+ textInformationFrame.value +"]");
            }
        }

    }

    @Override
    public void onLoadStarted(DataSpec dataSpec, int dataType, int trackType, Format trackFormat, int trackSelectionReason, Object trackSelectionData, long mediaStartTimeMs, long mediaEndTimeMs, long elapsedRealtimeMs) {
        Log.e(TAG, "onLoadStarted: [ "+ dataSpec.uri.toString() +" ]");
    }

    @Override
    public void onLoadCompleted(DataSpec dataSpec, int dataType, int trackType, Format trackFormat, int trackSelectionReason, Object trackSelectionData, long mediaStartTimeMs, long mediaEndTimeMs, long elapsedRealtimeMs, long loadDurationMs, long bytesLoaded) {
        Log.e(TAG, "onLoadCompleted: [ "+ dataSpec.uri.toString() +" ]");
    }

    @Override
    public void onLoadCanceled(DataSpec dataSpec, int dataType, int trackType, Format trackFormat, int trackSelectionReason, Object trackSelectionData, long mediaStartTimeMs, long mediaEndTimeMs, long elapsedRealtimeMs, long loadDurationMs, long bytesLoaded) {
        Log.e(TAG, "onLoadCanceled: [ "+ dataSpec.uri.toString() +" ]");
    }

    @Override
    public void onLoadError(DataSpec dataSpec, int dataType, int trackType, Format trackFormat, int trackSelectionReason, Object trackSelectionData, long mediaStartTimeMs, long mediaEndTimeMs, long elapsedRealtimeMs, long loadDurationMs, long bytesLoaded, IOException error, boolean wasCanceled) {
        Log.e(TAG, "onLoadError: [ "+ dataSpec.uri.toString() +" ]");
    }

    @Override
    public void onUpstreamDiscarded(int trackType, long mediaStartTimeMs, long mediaEndTimeMs) {

    }

    @Override
    public void onDownstreamFormatChanged(int trackType, Format trackFormat, int trackSelectionReason, Object trackSelectionData, long mediaTimeMs) {

    }

    @Override
    public void onTimelineChanged(Timeline timeline, Object manifest, int reason) {
        super.onTimelineChanged(timeline, manifest, reason);
    }

    @Override
    public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {
        super.onTracksChanged(trackGroups, trackSelections);
    }

    @Override
    public void onLoadingChanged(boolean isLoading) {
        super.onLoadingChanged(isLoading);
    }

    @Override
    public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
        super.onPlayerStateChanged(playWhenReady, playbackState);
    }

    @Override
    public void onRepeatModeChanged(int repeatMode) {
        super.onRepeatModeChanged(repeatMode);
    }

    @Override
    public void onShuffleModeEnabledChanged(boolean shuffleModeEnabled) {
        super.onShuffleModeEnabledChanged(shuffleModeEnabled);
    }

    @Override
    public void onPlayerError(ExoPlaybackException error) {
        super.onPlayerError(error);
    }

    @Override
    public void onPositionDiscontinuity(int reason) {
        super.onPositionDiscontinuity(reason);
    }

    @Override
    public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {
        super.onPlaybackParametersChanged(playbackParameters);
    }

    @Override
    public void onSeekProcessed() {
        super.onSeekProcessed();
    }

    @Override
    public void onTimelineChanged(Timeline timeline, Object manifest) {
        super.onTimelineChanged(timeline, manifest);
    }
}
