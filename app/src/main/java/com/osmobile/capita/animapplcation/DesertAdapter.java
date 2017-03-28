package com.osmobile.capita.animapplcation;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import static android.R.attr.resource;

/**
 * Created by JKidd on 16/02/2017.
 */

public class DesertAdapter extends ArrayAdapter<String>
{
    /**
     * Constructor
     *
     * @param context  The current context.
     * @param resource The resource ID for a layout file containing a TextView to use when
     */
    public DesertAdapter(Context context, String[] stuff) {
        super(context, R.layout.adapt_row, stuff);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View custom = inflater.inflate(R.layout.adapt_row, parent,false);

        String desc = getItem(position);

        TextView ttl = (TextView)custom.findViewById(R.id.txtLstTitle);
        TextView foot = (TextView)custom.findViewById(R.id.txtLstFooter);
        TextView descrip = (TextView)custom.findViewById(R.id.txtLstDesc);

        descrip.setText(desc);
        foot.setText("Item: " + String.valueOf(position));

        return custom;
    }
}
