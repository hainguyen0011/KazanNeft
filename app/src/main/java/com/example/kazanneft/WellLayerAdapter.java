package com.example.kazanneft;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class WellLayerAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<WellLayer> wellLayerList;

    public WellLayerAdapter(Context context, int layout, List<WellLayer> wellLayerList) {
        this.context = context;
        this.layout = layout;
        this.wellLayerList = wellLayerList;
    }

    @Override
    public int getCount() {
        return wellLayerList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String tempTxt;
        int height;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(layout, null);
        TextView txtName = (TextView) convertView.findViewById(R.id.txtLayerName);
        TextView txtCap = (TextView) convertView.findViewById(R.id.txtLayerCap);
        WellLayer wellLayer = wellLayerList.get(position);
        txtName.setText(wellLayer.getRockTypeName());
        txtName.setBackgroundColor(Color.parseColor(wellLayer.getBackgroundColor()));
        tempTxt = wellLayer.getEndPoint();
        height = 180;
        txtName.setTextColor(Color.WHITE);
        if (position != wellLayerList.size() - 1) {
            tempTxt += "m";
            height = (Integer.parseInt(wellLayer.getEndPoint()) -
                    Integer.parseInt(wellLayer.getStartPoint())) / 2;
            txtName.setTextColor(Color.BLACK);
        }
        txtCap.setText(tempTxt);
        txtName.getLayoutParams().height = height;
        return convertView;
    }
}
