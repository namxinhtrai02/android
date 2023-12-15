package com.example.kethtph09802_assignment1.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kethtph09802_assignment1.MainActivity;
import com.example.kethtph09802_assignment1.Model.LoaiChi;
import com.example.kethtph09802_assignment1.Model.LoaiThu;
import com.example.kethtph09802_assignment1.R;
import com.example.kethtph09802_assignment1.SQLite.Database;

import java.util.ArrayList;
import java.util.List;

public class LoaiThuAdapter extends BaseAdapter {
    Context context;
    int layout;
    List<LoaiThu> loaiThuList;

    public LoaiThuAdapter(Context context, int layout, ArrayList<LoaiThu> loaiThuList) {
        this.context = context;
        this.layout = layout;
        this.loaiThuList = loaiThuList;
    }

    @Override
    public int getCount() {
        return loaiThuList.size();
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
        TextView txtloaithu;
        ImageView loaithusua;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        ViewHolder holder;
        if (view == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout,null);
            holder.txtloaithu = (TextView)view.findViewById(R.id.txtloaithu);
            holder.loaithusua = (ImageView) view.findViewById(R.id.loaithusua);
            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }
        final LoaiThu lt = loaiThuList.get(i);
        holder.txtloaithu.setText(lt.getLoaithu());

        holder.loaithusua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(context);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_sua);

                final EditText edtsua = (EditText) dialog.findViewById(R.id.edt_sua);
                edtsua.setText(lt.getLoaithu());
                Button btnsua = (Button) dialog.findViewById(R.id.btn_sua);
                btnsua.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String a = edtsua.getText().toString();
                        int b = lt.getIdthu();
                        if (a.isEmpty()){
                            Toast.makeText(context, "vui long khong de trong loai thu ",
                                    Toast.LENGTH_SHORT).show();
                        }else {
                            Database database = new Database(context);
                            database.SendData("UPDATE CHI SET LOAITHU = '"+a+"' WHERE IDTHU = "+b+"  ");
                            Toast.makeText(context , " update thanh cong",Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                            ((MainActivity)context).recreate();
                        }
                    }
                });
                dialog.show();
            }
        });



        return view;
    }
}
