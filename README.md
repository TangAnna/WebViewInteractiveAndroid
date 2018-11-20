# WebViewInteractiveAndroid
Android使用WebView实现与js交互<br/>

现在很多App中使用了Web的方式实现其中的一些功能，这样的开发模式避免不了需要Android和Web要进行数据交互，
在Android中提供了WebView控件来完成加载web页面的加载，也能通过它来实现两者之间的数据交互；<br/>
面试中也会被问到这方面的知识，所以学习Android和web的交互是开发人员必不可少的一项技能；<br/>

**首先是WebView经常用到的一些方法**<br/>
//TODO :下周总结

**Js调用Android的实现方式**<br/>

方法一：<br/>

步骤：<br/>
1.设置webView可以加载Js;<br/>
2.创建Js接口类；<br/>
3.设置Js接口；<br/>
4.加载Web页面；<br/>

代码实现如下：
    
    //设置webView可以加载Js
    mWebView.getSettings().setJavaScriptEnabled(true);
创建Js接口类，创建一个类，根据业务写方法，在该方法上添加注解 "@JavascriptInterface"(注解很重要)


    //设置js接口
    //参数1：创建的js接口类的对象
    //参数2：js方法中用if判断的值，这两个值要保持一致  if (window.android) 这里可以认为设置android就是ImoocInterface接口实例的代理人
    mWebView.addJavascriptInterface(new ImoocInterface(this), "android");
    
设置要加载的网页
    
    //我的是本地的html页面路径写法
     mWebView.loadUrl("file:///android_asset/index.html");

**Android调用Js中的实现方式**<br/>

方法一：<br/>

步骤：<br/>
1.设置webView可以加载Js;<br/>
2.加载Web页面；<br/>
3.在需要的地方调用loadUrl(String url)方法实现交互；<br/>

代码实现：
    
    //设置webview可以加载js
    mWebView.getSettings().setJavaScriptEnabled(true);
    
    //设置webview加载的网页
    mWebView.loadUrl("file:///android_asset/index.html");
实现点击Button将EditText上输入的内容显示到Html的input上<br/>
    
    //使用loadUrl()方法，参数是一段js代码 前面拼接javascript: window.后面的是js中的方法名（先判断是否含有此方法在调用）
    WebView.loadUrl("javascript:if(window.androidSetValue){window.androidSetValue('" + str + "')}");
    
方法二：

步骤：<br/>
1.设置webView可以加载Js;<br/>
2.加载Web页面；<br/>
3.在需要的地方调用loadUrl(String url)方法实现交互；<br/>

代码实现：<br/>
前两步都是一样的，只有在交互的时候不一样<br/>
    
    //方法二：（只适用于Android4.4及以上）使用evaluateJavascript()方法
    //第一个参数与上一种方法一致，第二个参数是获取js返回值得方法
    mWebView.evaluateJavascript("javascript:if(window.androidSetValue){window.androidSetValue('" + str + "')}", new ValueCallback<String>() {
           @Override
            public void onReceiveValue(String value) {
                 //获取js的返回值
                Log.d("TAG", "onReceiveValue: " + value);
            }
    });
    
两种方法发的比较：<br/>
1.loadUrl()方式方便简洁，evaluateJavascript()方式效率更高；<br/>
2.evaluateJavascript()方式版本兼容性较差只适合在4.4版本及以上使用，所以推荐两种方式根据Android版本结合使用;<br/>
3.evaluateJavascript()方式可以方便的获取js的返回值，loadUrl()方式获取返回值麻烦；<br/>

两种方式的适用场景：<br/>
loadUrl()没有返回值，对性能要求不高；<br/>
evaluateJavascript() Android 4.4版本及以上，有返回值，对性能要求高的情况；<br/>

js部分代码：

    <script type="text/javascript">
        var btnEle = document.getElementById("btn");
        var inputEle = document.getElementById("input");
        btnEle.addEventListener("click", function () {
            var value = inputEle.value;
            //window.后面的值要与Android中的 addJavascriptInterface（）第二个实参保持一致  相当于android就是接口的实例了
            // mWebView.addJavascriptInterface(new imoocInterface(), "android")；
            if (window.android) {
                android.setValue(value);
            } else {
                alert("imoocInterface not found")
            }
        })
        var androidSetValue = function (str) {
            inputEle.value = str;
            return "调用成功";
        }
    </script>

