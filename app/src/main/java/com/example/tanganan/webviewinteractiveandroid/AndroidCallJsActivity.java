package com.example.tanganan.webviewinteractiveandroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Android调用Jscript中的方法
 */
public class AndroidCallJsActivity extends AppCompatActivity implements View.OnClickListener {

    private WebView mWebView;
    private EditText mEditText;
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_call_js);
        mWebView = (WebView) findViewById(R.id.webView);
        mEditText = (EditText) findViewById(R.id.et_input);
        findViewById(R.id.btn_show).setOnClickListener(this);
        //设置webview可以加载js
        mWebView.getSettings().setJavaScriptEnabled(true);
        //设置webview加载的网页
        mWebView.loadUrl("file:///android_asset/index.html");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_show:
                String str = mEditText.getText().toString();
                if (TextUtils.isEmpty(str)) {
                    Toast.makeText(this, "请输入", Toast.LENGTH_SHORT).show();
                }else {
                    //调用js中的显示数据的方法
                    //使用loadUrl()方法，参数是一段js代码 前面拼接javascript: window.后面的是js中的方法名（先判断是否含有此方法在调用）
                    mWebView.loadUrl("javascript:if(window.androidSetValue){window.androidSetValue('"+str+"')}");
                }

                break;
        }
    }
}
