package com.example.tanganan.webviewinteractiveandroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.webkit.WebView;
import android.widget.TextView;

public class JsCallAndroidActivity extends AppCompatActivity implements ImoocInterface.CallValue {

    private WebView mWebView;
    private TextView mTvShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_js_call_android);
        mWebView = (WebView) findViewById(R.id.webView);
        mTvShow = (TextView) findViewById(R.id.tv_show);
        //第一步：设置webview可以加载js
        mWebView.getSettings().setJavaScriptEnabled(true);
        //第二步：设置js接口
        // 参数1：创建的js接口类的对象
        //参数2：js方法中用if判断的值，这两个值要保持一致  if (window.imoocLauncher)
        mWebView.addJavascriptInterface(new ImoocInterface(this), "imoocInterface");
        mWebView.loadUrl("file:///android_asset/index.html");

    }


    @Override
    public void getValue(String value) {
        if (!TextUtils.isEmpty(value)) {
            mTvShow.setText(value);
        }
    }
}
