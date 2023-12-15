package com.example.kethtph09802_assignment1.Fragment;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.kethtph09802_assignment1.Adapter.KhoanThuAdapter;
import com.example.kethtph09802_assignment1.Adapter.LoaiThuAdapter;
import com.example.kethtph09802_assignment1.Model.KhoanThu;
import com.example.kethtph09802_assignment1.Model.LoaiThu;
import com.example.kethtph09802_assignment1.R;
import com.example.kethtph09802_assignment1.SQLite.Database;

import java.util.ArrayList;

public class FMLT extends Fragment {

    public FMLT(){
    }
    private View rootview;
    LoaiThuAdapter adapter;
    ArrayList<LoaiThu> list;
    ListView lv_loaithu;
    Database database;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootview = inflater.inflate(R.layout.fragment_loai_thu, container,false);
        initView();
        return rootview;
    }
    private void initView(){
        database = new Database(getActivity());
        lv_loaithu = rootview.findViewById(R.id.lv_loaithu);
        list = new ArrayList<>();
        adapter = new LoaiThuAdapter(getActivity(), R.layout.list_item_loai_thu,list);
        Cursor dataloaithu = database.GetData("SELECT * FROM THU");
        list.clear();
        while (dataloaithu.moveToNext()){
            String a = dataloaithu.getString(1);
            int b = dataloaithu.getInt(3);
            list.add(new LoaiThu(a,b));
        }
        adapter.notifyDataSetChanged();
        lv_loaithu.setAdapter(adapter);
    }

}
