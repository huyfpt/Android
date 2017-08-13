package com.tienminh.dmt.youtubeapiplaylist2609;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by TienMinh on 20-Jan-17.
 */

public class VideoAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<VideoYouTube> videoYouTubes;
    private ArrayList<VideoYouTube> arrayList;

    public VideoAdapter(Context context, int layout, List<VideoYouTube> videoYouTubes) {
        this.context = context;
        this.layout = layout;
        this.videoYouTubes = videoYouTubes;

        this.arrayList = new ArrayList<VideoYouTube>();
        this.arrayList.addAll(videoYouTubes);
    }

    @Override
    public int getCount() {
        return videoYouTubes.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private class ViewHolder{
        ImageView imgThumb;
        TextView txtTitle;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder holder;

        if(view == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            holder.imgThumb = (ImageView) view.findViewById(R.id.imageThumbnail);
            holder.txtTitle = (TextView) view.findViewById(R.id.textviewTitle);
            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }

        VideoYouTube video = videoYouTubes.get(i);

        holder.txtTitle.setText(video.Title);
        Picasso.with(context).load(video.UrlImage).into(holder.imgThumb);

        return view;
    }

    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        videoYouTubes.clear();
        if (charText.length() == 0) {
            videoYouTubes.addAll(arrayList);
        } else {
            for (VideoYouTube video : arrayList) {
                if (video.Title.toLowerCase(Locale.getDefault()).contains(charText)) {
                    videoYouTubes.add(video);
                }
            }
        }
        notifyDataSetChanged();
    }
}
