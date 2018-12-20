package com.why.rk1220.presenter;

import android.os.Handler;
import android.os.Message;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.why.rk1220.bean.Goods;
import com.why.rk1220.bean.Result;
import com.why.rk1220.core.DataCall;
import com.why.rk1220.model.Model;

import java.lang.reflect.Type;
import java.util.List;

public class Presenter  {
    DataCall dataCall;

    public Presenter(DataCall dataCall) {
        this.dataCall=dataCall;
    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            String s= (String) msg.obj;// 得到JSON数据

            // 解析
            Type type = new TypeToken<Result<List<Goods>>>() {
            }.getType();
            Result  result = new Gson().fromJson(s, type);
            // 将解析出的数据传回View层  进行使用
            dataCall.success(result.getData());

        }
    };

    public void get(final String s) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String json = Model.getResult(s);
                Message message = handler.obtainMessage();
                message.obj=json;
                handler.sendMessage(message);
            }
        }).start();
    }
}
