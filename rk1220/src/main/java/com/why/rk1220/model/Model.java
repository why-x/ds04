package com.why.rk1220.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.why.rk1220.bean.Goods;
import com.why.rk1220.bean.Result;
import com.why.rk1220.core.Utils;

import java.lang.reflect.Type;
import java.util.List;


public class Model {
        public static String getResult(String s) {
            String json = Utils.get(s);
            return json;
        }
    }

