package com.a2.demo30.playlistyoutube;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

/**
 * Created by duchuy on 1/20/2017.
 */
public class Listview_Adapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<VideoYoutuBe> videoYouTubes;
    private ArrayList<VideoYoutuBe> arrayList;

    public Listview_Adapter(Context context, int layout, List<VideoYoutuBe> videoYouTubes) {
        this.context = context;
        this.layout = layout;
        this.videoYouTubes = videoYouTubes;

        this.arrayList = new ArrayList<VideoYoutuBe>();
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

    private class ViewHolder {
        ImageView imgThumb;
        TextView txtTitle;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder holder;

        if (view == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            holder.imgThumb = (ImageView) view.findViewById(R.id.imageView);
            holder.txtTitle = (TextView) view.findViewById(R.id.textviewTitle);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        VideoYoutuBe video = videoYouTubes.get(i);
        holder.txtTitle.setText(video.title);
        Picasso.with(context).load(video.UrlImage).into(holder.imgThumb);

        return view;
    }

    //cat ki tu tieng viet
    public static String removeAccent(String s) {
        String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(temp).replaceAll("");
    }

    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());

        videoYouTubes.clear();
        if (charText.length() == 0) {
            videoYouTubes.addAll(arrayList);
        } else {
            for (VideoYoutuBe video : arrayList) {
                if (video.title.toLowerCase(Locale.getDefault()).contains(charText)) {

                    videoYouTubes.add(video);
                }
            }
        }
        notifyDataSetChanged();
    }
}
