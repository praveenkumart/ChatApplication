package com.emegra.mera;

/**
 * Created by Praveen on 7/18/2015.
 */
import java.util.List;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class CompleteListAdapter extends BaseAdapter {
    private Activity mContext;
    private List<String> mList;

    private LayoutInflater mLayoutInflater = null;
    public CompleteListAdapter(Activity context, List<String> list) {
        mContext = context;
        mList = list;
        mLayoutInflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return mList.size();
    }
    @Override
    public Object getItem(int pos) {
        return mList.get(pos);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        CompleteListViewHolder viewHolder;
        if (convertView == null) {
            LayoutInflater li = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = li.inflate(R.layout.list_layout, null);
            viewHolder = new CompleteListViewHolder(v);
            v.setTag(viewHolder);
        } else {
            viewHolder = (CompleteListViewHolder) v.getTag();
        }
        viewHolder.mTVItem.setText(mList.get(position));

        v.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Dialog d=new Dialog(mContext);
                d.setContentView(R.layout.pop);
                d.show();
             TextView tx= (TextView)v.findViewById(R.id.listTV);
                Toast.makeText(mContext,tx.getText(),Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        return v;
    }
}
class CompleteListViewHolder {
    public TextView mTVItem;
    public CompleteListViewHolder(View base) {
        mTVItem = (TextView) base.findViewById(R.id.listTV);
    }
}