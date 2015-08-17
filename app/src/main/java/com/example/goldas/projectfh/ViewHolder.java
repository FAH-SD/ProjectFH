package com.example.goldas.projectfh;

import android.widget.TextView;

/**
 * Created by roy on 2015/8/16.
 */
public class ViewHolder {
    TextView txtitem, txtquan, txtunit, txtld;

    public ViewHolder(TextView txtitem, TextView txtquan, TextView txtunit, TextView txtld) {
        this.txtitem = txtitem;
        this.txtquan = txtquan;
        this.txtunit = txtunit;
        this.txtld = txtld;
    }

    public TextView getItem() {
        return txtitem;
    }

    public void setItem(TextView txtitem) {
        this.txtitem = txtitem;
    }

    public TextView getQuan() {
        return txtquan;
    }

    public void setQuan(TextView txtquan) {
        this.txtquan = txtquan;
    }

    public TextView getUnit() {
        return txtunit;
    }

    public void setUnit(TextView txtunit) {
        this.txtunit = txtunit;
    }

    public TextView getLd() {
        return txtld;
    }

    public void setLd(TextView txtld) {
        this.txtld = txtld;
    }

}