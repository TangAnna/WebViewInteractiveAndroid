package com.example.tanganan.webviewinteractiveandroid;

import android.util.Log;
import android.webkit.JavascriptInterface;

/**
 * js接口类
 * Created by TangAnna on 2018/6/15.
 */

public class ImoocInterface {


    private CallValue mCallValue;

    public ImoocInterface(CallValue callValue) {
        mCallValue = callValue;
    }

    /**
     * 供给Jscript使用的方法
     * 方法名要和Js中调用的方法名保持一致
     *
     * @param value
     */
    @JavascriptInterface
    public void setValue(String value) {
        Log.d("TAG", "setValue: " + value);
        mCallValue.getValue(value);

    }

    public interface CallValue {
        void getValue(String value);
    }
}
