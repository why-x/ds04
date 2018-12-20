package com.why.rk1220;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.why.rk1220.bean.Goods;
import com.why.rk1220.bean.Result;
import com.why.rk1220.core.DataCall;
import com.why.rk1220.presenter.Presenter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements DataCall<List<Goods>> {
    List<Goods> list=new ArrayList<>();
    private RecyclerView recyler;
    private MyAdapter myadapter;
    private Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初始化控件
        recyler = findViewById(R.id.recyler);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        myadapter = new MyAdapter(list,this);
        recyler.setLayoutManager(linearLayoutManager);
        recyler.setAdapter(myadapter);
        // 删除的方法
        myadapter.onRecycler(new MyAdapter.OnRecyclerViewItemClickListter() {
            @Override
            public void onItemClick(final int position) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("确定删除吗?");
                //取消
                builder.setNegativeButton("取消",null);
                //确定
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        list.remove(position);// 删除
                        myadapter.notifyDataSetChanged();// 刷新适配器
                    }
                }).show();
            }
        });

        // 得到P层
        presenter = new Presenter(this);
        presenter.get("http://www.zhaoapi.cn/product/getCatagory");
    }


    @Override
    public void success(List<Goods> result) {
        if (result!= null) {
            list.addAll(result);//添加集合数据
            myadapter.notifyDataSetChanged();//更新适配器
        }
    }

    @Override
    public void fail(Result result) {

    }
}
