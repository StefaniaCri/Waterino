package com.example.waterreminder;

import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_video, container, false);

        VideoView videoView = view.findViewById(R.id.video_view);
        String videoPath = "android.resource://" + view.getContext().getPackageName() + "/"+ R.raw.video_demo;

        Uri uri = Uri.parse(videoPath);
        videoView.setVideoURI(uri);

        MediaController mediaController = new MediaController(view.getContext());
        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);


        return view;
    }
}