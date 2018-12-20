package com.why.rk1220.core;

import com.why.rk1220.bean.Goods;
import com.why.rk1220.bean.Result;

import java.util.List;

public interface DataCall<T> {
    void success(T result);
    void fail(Result result);
}
