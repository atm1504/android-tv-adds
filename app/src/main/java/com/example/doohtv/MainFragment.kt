package com.example.doohtv

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import android.widget.Toast
import android.widget.VideoView
import androidx.fragment.app.Fragment

/**
 * Loads a grid of cards with movies to browse.
 */
class MainFragment : Fragment() {

    val videoUrl = "https://c1x-digitalooh.s3.us-west-2.amazonaws.com/Coca-Cola+-+Happiness+Factory+(2006%2C+Netherlands).mp4"
    private lateinit var videoView: VideoView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_main, container, false)
        videoView = rootView.findViewById(R.id.videoView)
        return rootView
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Toast.makeText(context, "Testing it", Toast.LENGTH_LONG).show();

        val uri: Uri = Uri.parse(videoUrl)
        videoView.setVideoURI(uri)

        val mediaController = MediaController(context)
        mediaController.setAnchorView(videoView)
        mediaController.setMediaPlayer(videoView)
        videoView.setMediaController(mediaController)

        videoView.start()

    }


    companion object {
        private val TAG = "MainFragment"

        private val BACKGROUND_UPDATE_DELAY = 300
        private val GRID_ITEM_WIDTH = 200
        private val GRID_ITEM_HEIGHT = 200
        private val NUM_ROWS = 6
        private val NUM_COLS = 15
    }
}