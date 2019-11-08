package com.example.oct25;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class AndroidAdapter extends ArrayAdapter<AndroidVersion> {
    private Context context;
    private int resource;

    public AndroidAdapter(@NonNull Context context, int resource, @NonNull List<AndroidVersion> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;

    }
    @NonNull
    @Override
    public View getView(int i, @Nullable View convertView, @NonNull ViewGroup parent){
        int logo = getItem(i).getLogo();
        String name = getItem(i).getName();
        String ceo = getItem(i).getCeo();
        String industry = getItem(i).getIndustry();
        String country = getItem(i).getCountry();

        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(resource, parent, false);

        ImageView img = convertView.findViewById(R.id.cLogo);
        TextView cname = convertView.findViewById(R.id.cName);
        TextView ccountry = convertView.findViewById(R.id.cCountry);
        TextView cind = convertView.findViewById(R.id.cIndustry);
        TextView cceo = convertView.findViewById(R.id.cCeo);

        img.setImageResource(logo);
        cname.setText(name);
        cceo.setText(ceo);
        cind.setText(industry);
        ccountry.setText(country);
        return convertView;
    }
}
