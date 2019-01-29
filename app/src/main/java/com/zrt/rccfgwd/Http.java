package com.zrt.rccfgwd;

/**
 * Created by Administrator on 2019/1/28.
 */

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import android.os.Build;
import android.text.TextUtils;
import android.util.ArrayMap;

import com.google.gson.JsonParseException;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.zrt.rccfgwd.base.BaseResultCallBack;
import com.zrt.rccfgwd.http.RetrofitHelper;

import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLException;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Http{
    private static Http mInstance;
    private Retrofit retrofit;
    private OkHttpClient client;
    private Map<Object, List<Call>> requests;

    private Http() {
        if(Build.VERSION.SDK_INT >= 19) {
            this.requests = new ArrayMap();
        } else {
            this.requests = new HashMap();
        }

    }

    public static Http getInstance() {
        if(mInstance == null) {
            Class var0 = Http.class;
            synchronized(Http.class) {
                if(mInstance == null) {
                    mInstance = new Http();
                }
            }
        }

        return mInstance;
    }

    public Retrofit getRetrofit(String base_url) {
        if(this.retrofit == null) {
            this.retrofit = RetrofitHelper.INSTANCE.getRetrofit(this.client, base_url);
        } else {
            String url = this.retrofit.baseUrl().toString();
            if(!TextUtils.equals(base_url, url)) {
                this.retrofit.newBuilder().baseUrl(base_url).build();
            }
        }

        return this.retrofit;
    }

    public void init(OkHttpClient client) {
        Logger.addLogAdapter(new AndroidLogAdapter());
        this.client = client;
    }

    public <T> void execute(Call<T> call, BaseResultCallBack<T> callBack) {
        this.execute(call, (Object)null, callBack);
    }

    public <T> void execute(Call<T> call, final Object requestId, final BaseResultCallBack<T> callBack) {
        if(call != null) {
            if(this.requests.containsKey(requestId)) {
                List<Call> calls = (List)this.requests.get(requestId);
                if(calls != null) {
                    calls.add(call);
                }
            } else {
                List<Call> calls = new ArrayList();
                calls.add(call);
                this.requests.put(requestId, calls);
            }

            if(callBack != null) {
                callBack.onStart();
            }

            call.enqueue(new Callback<T>() {
                public void onResponse(Call<T> call, Response<T> response) {
                    if(response.isSuccessful()) {
                        if(callBack != null) {
                            callBack.onSuccess(response.body());
                        }
                    } else if(callBack != null) {
                        callBack.onFailure(response.code(), response.code() + "连接失败，请重试");
                    }

                    if(callBack != null) {
                        callBack.onFinish();
                    }

                    Http.this.removeCall(requestId, call);
                }

                public void onFailure(Call<T> call, Throwable t) {
                    if(callBack != null) {
                        callBack.onFinish();
                    }

                    if(!call.isCanceled()) {
                        if(callBack != null) {
                            if(t instanceof SocketTimeoutException) {
                                callBack.onFailure(-1, "连接超时，请重试");
                            } else if(t instanceof SSLException) {
                                callBack.onFailure(-2, "证书错误");
                            } else if(t instanceof JsonParseException) {
                                callBack.onFailure(-3, "解析异常");
                            } else {
                                callBack.onFailure(-4, "连接失败，请重试");
                            }
                        }

                        Http.this.removeCall(requestId, call);
                    }
                }
            });
        }
    }

    private <T> void removeCall(Object requestId, Call<T> call) {
        List<Call> calls = (List)this.requests.get(requestId);
        if(calls != null) {
            calls.remove(call);
            if(calls.isEmpty()) {
                this.requests.remove(requestId);
            }

        }
    }

    public void cancel(Object requestId) {
        if(this.requests != null && !this.requests.isEmpty() && this.requests.containsKey(requestId)) {
            List<Call> values = (List)this.requests.get(requestId);
            Iterator var3 = values.iterator();

            while(var3.hasNext()) {
                Call value = (Call)var3.next();
                if(value != null && !value.isCanceled()) {
                    value.cancel();
                }
            }

            this.requests.remove(requestId);
        }

    }

    public void cancelAll() {
        if(this.requests != null && !this.requests.isEmpty()) {
            Iterator iterator = this.requests.entrySet().iterator();

            while(iterator.hasNext()) {
                Map.Entry<Object, List<Call>> next = (Map.Entry)iterator.next();
                List<Call> values = (List)next.getValue();
                Iterator var4 = values.iterator();

                while(var4.hasNext()) {
                    Call value = (Call)var4.next();
                    if(value != null && !value.isCanceled()) {
                        value.cancel();
                    }
                }

                this.requests.remove(next.getKey());
            }
        }

    }
}

