package com.originsdigital.hbsonrewindplayerdemo.wrapper.exo

import android.net.Uri
import com.google.android.exoplayer2.C
import com.google.android.exoplayer2.ExoPlaybackException
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.PlaybackException
import com.google.android.exoplayer2.offline.FilteringManifestParser
import com.google.android.exoplayer2.source.BehindLiveWindowException
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.source.dash.DashMediaSource
import com.google.android.exoplayer2.source.dash.manifest.DashManifestParser
import com.google.android.exoplayer2.source.hls.HlsMediaSource
import com.google.android.exoplayer2.source.hls.playlist.DefaultHlsPlaylistParserFactory
import com.google.android.exoplayer2.source.smoothstreaming.SsMediaSource
import com.google.android.exoplayer2.source.smoothstreaming.manifest.SsManifestParser
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.util.MimeTypes
import com.google.android.exoplayer2.util.Util

object Utils {
    fun buildMediaSource(
        uri: Uri,
        dataSourceFactory: DataSource.Factory,
    ): MediaSource {
        val uriWithoutQuery = uri.buildUpon().clearQuery().build()
        val type = Util.inferContentType(uriWithoutQuery, "")

        return when (type) {
            C.CONTENT_TYPE_DASH -> {
                val item = MediaItem.Builder()
                    .setUri(uri)
                    .setMimeType(MimeTypes.APPLICATION_MPD)
                    .build()

                DashMediaSource.Factory(dataSourceFactory)
                    .setManifestParser(
                        FilteringManifestParser(
                            DashManifestParser(),
                            null
                        )
                    )
                    .createMediaSource(item)
            }
            C.CONTENT_TYPE_SS -> {
                val item = MediaItem.Builder().setUri(uri).build()
                SsMediaSource.Factory(dataSourceFactory)
                    .setManifestParser(FilteringManifestParser(SsManifestParser(), null))
                    .createMediaSource(item)
            }
            C.CONTENT_TYPE_HLS -> {
                val item =
                    MediaItem.Builder().setUri(uri).setMimeType(MimeTypes.APPLICATION_M3U8).build()
                HlsMediaSource.Factory(dataSourceFactory)
                    .setPlaylistParserFactory(DefaultHlsPlaylistParserFactory())
                    .createMediaSource(item)
            }
            C.CONTENT_TYPE_OTHER -> {
                val item = MediaItem.Builder().setUri(uri).build()
                ProgressiveMediaSource.Factory(dataSourceFactory).createMediaSource(item)
            }
            else -> {
                throw IllegalStateException("Unsupported type: $type")
            }
        }
    }

    fun isBehindLiveWindow(e: PlaybackException): Boolean {
        if (e !is ExoPlaybackException) return false

        if (e.type != ExoPlaybackException.TYPE_SOURCE) {
            return false
        }

        var cause: Throwable? = e.sourceException
        while (cause != null) {
            if (cause is BehindLiveWindowException) {
                return true
            }
            cause = cause.cause
        }

        return false
    }
}