package com.tata.commonadapter;

import android.content.Context;



public class MyAdapter extends CommonAdapter<SimpleModel> {

    public MyAdapter(Context mContext) {
        super(mContext);
    }

    @Override
    public void convertView(ViewHolder helper, SimpleModel item, int position) {
        //set title
        helper.setText(R.id.textView,item.getTitle());

        //set image
        helper.setImageResource(R.id.imageView,item.getResId());
    }

    @Override
    public int getItemLayoutId() {
        return R.layout.adapter_item_simple;
    }
}
