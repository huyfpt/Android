package com.demo29.jsonserver;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by duchuy on 1/10/2017.
 */
public class SanPhamAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<SanPham> sanPhamList;
    public SanPhamAdapter(Context context, int layout, List<SanPham> sanPhamList) {
        this.context = context;
        this.layout = layout;
        this.sanPhamList = sanPhamList;
    }
    @Override
    public int getCount() {
    return sanPhamList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    private class ViewHolder{
        TextView txtTen, txtGia, txtMoTa;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = new ViewHolder();
            View rowview = convertView;
        if(rowview == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowview = inflater.inflate(layout, null);
            holder.txtTen   = (TextView) rowview.findViewById(R.id.textviewTenSP);
            holder.txtGia   = (TextView) rowview.findViewById(R.id.textviewGiaSP);
            holder.txtMoTa  = (TextView) rowview.findViewById(R.id.textviewMoTa);
            rowview.setTag(holder);
        }else {
            holder = (ViewHolder) rowview.getTag();
        }
        SanPham sanPham = sanPhamList.get(position);

        holder.txtTen.setText(sanPham.Ten);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.txtGia.setText("Giá: " +  decimalFormat.format(sanPham.Gia) + "Đ");
        holder.txtMoTa.setText(sanPham.MoTa);

        return rowview;
    }

}
