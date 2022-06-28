package com.example.doohtv

import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.MediaController
import android.widget.Toast
import android.widget.VideoView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso

/**
 * Loads a grid of cards with movies to browse.
 */
class MainFragment : Fragment() {

    // val videoUrl = "https://c1x-digitalooh.s3.us-west-2.amazonaws.com/Coca-Cola+-+Happiness+Factory+(2006%2C+Netherlands).mp4"
    val videoUrl =
        "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
    private lateinit var videoView: VideoView
    private lateinit var imageView: ImageView
    private lateinit var adds: ArrayList<AddData>
    private lateinit var mHandler: Handler
    private lateinit var mRunnable: Runnable


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
        imageView = rootView.findViewById(R.id.imageView)
        return rootView
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mHandler = Handler()
        createData()
        Toast.makeText(context, "Testing it", Toast.LENGTH_LONG).show();

        displayAdd()
//        val addData = adds.get(0)
//        playVideo(addData.url, addData.duration)
    }

    fun displayAdd() {
        val n = adds.size
        var i = 0
        Log.d("ATM", "Inside display ad")

        var start = true
        mRunnable = Runnable {

            if (start) {
                Log.d("ATM", "Loading ads...: ")
                Toast.makeText(context, "Loading Ads.....", Toast.LENGTH_LONG).show()
                start = false
                mHandler.postDelayed(mRunnable, 1000.toLong())
                Toast.makeText(context, "Ads loaded.....", Toast.LENGTH_LONG).show()

            } else {
                val addData: AddData = adds[i]
                if (addData.type == VIDEO) {
                    Log.d("ATM", "Inside video type: ")
                    playVideo(addData.url, addData.duration)
                    Log.d("ATM", "Inside video type triggering handler: ")
                    mHandler.postDelayed(mRunnable, (addData.duration * 1000).toLong())
                    Log.d("ATM", "Inside video type  handler trigerred: ")

                } else if (addData.type == IMAGE) {
                    Log.d("ATM", "Inside image type: ")
                    playImage(addData.url, addData.duration)
                    mHandler.postDelayed(mRunnable, (addData.duration * 1000).toLong())
                    Log.d("ATM", "Going out of  image type: ")
                }

                i += 1
                i = i % n
            }
        }
        mHandler.postDelayed(mRunnable, 2000)

    }

    fun playVideo(url: String, duration: Float) {

        imageView.visibility = View.GONE
        videoView.visibility = View.VISIBLE

        val uri: Uri = Uri.parse(url)
        videoView.setVideoURI(uri)

        val mediaController = MediaController(context)
        mediaController.setAnchorView(videoView)
        mediaController.setMediaPlayer(videoView)
        videoView.setMediaController(mediaController)

        videoView.start()

    }

    fun playImage(url: String, duration: Float) {
        imageView.visibility = View.VISIBLE
        videoView.visibility = View.GONE
        Log.d("ATM","Loading image...")
        Glide.with(this).load(url).into(imageView)
//        Picasso.with()
//            .load(url)
//            .error(it)
//            .into(imageView)
    }

    fun createData() {
        adds = ArrayList<AddData>()
        adds.add(
            AddData(
                1,
                30.0f,
                "https://c1x-digitalooh.s3.us-west-2.amazonaws.com/Artura_+The+Full+Force+of+McLaren.mp4",
                "video"
            )
        )
        adds.add(
            AddData(
                2,
                10.0f,
                "https://c1x-digitalooh.s3.us-west-2.amazonaws.com/BMW1.jpeg",
                "image"
            )
        )
        adds.add(
            AddData(
                3,
                25.0f,
                "https://c1x-digitalooh.s3.us-west-2.amazonaws.com/Walt+Disney+World+Resort+50th+Anniversary+_+Come+Celebrate+Today+Commercial+(2022).mp4",
                "video"
            )
        )
        adds.add(
            AddData(
                4,
                30.0f,
                "https://c1x-digitalooh.s3.us-west-2.amazonaws.com/Famous+Visitors+_+Super+Bowl+Extended+Cut+(2020+Walmart+Commercial).mp4",
                "video"
            )
        )
        adds.add(
            AddData(
                5,
                10.0f,
                "https://c1x-digitalooh.s3.us-west-2.amazonaws.com/KFC.jpeg",
                "image"
            )
        )
        adds.add(
            AddData(
                6,
                10.0f,
                "https://c1x-digitalooh.s3.us-west-2.amazonaws.com/Netflix+games.jpeg",
                "image"
            )
        )
        adds.add(
            AddData(
                7,
                30.0f,
                "https://c1x-digitalooh.s3.us-west-2.amazonaws.com/Coca-Cola+-+Happiness+Factory+(2006%2C+Netherlands).mp4",
                "video"
            )
        )
        adds.add(
            AddData(
                8,
                10.0f,
                "https://c1x-digitalooh.s3.us-west-2.amazonaws.com/Nike1.jpeg",
                "image"
            )
        )
        adds.add(
            AddData(
                9,
                30.0f,
                "https://c1x-digitalooh.s3.us-west-2.amazonaws.com/LEGO%C2%AE+CON+2022+-+Official+Trailer.mp4",
                "video"
            )
        )
        adds.add(
            AddData(
                10,
                10.0f,
                "https://c1x-digitalooh.s3.us-west-2.amazonaws.com/Pepsi.png",
                "image"
            )
        )

    }

    override fun onDestroyView() {
        super.onDestroyView()
        mHandler.removeCallbacks(mRunnable)
    }

    companion object {
        private val TAG = "MainFragment"

        private val IMAGE = "image"
        private val VIDEO = "video"
    }
}