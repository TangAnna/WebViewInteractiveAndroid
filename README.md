# WebViewInteractiveAndroid
Android使用WebView实现与js交互
现在很多App中使用了Web的方式实现其中的一些功能，这样的开发模式避免不了需要Android和Web要进行数据交互，
在Android中提供了WebView控件来完成加载web页面的加载，也能通过它来实现两者之间的数据交互；<br/>
面试中也会被问到这方面的知识，所以学习Android和web的交互是开发人员必不可少的一项技能；<br/>

首先是WebView经常用到的一些方法：<br/>
//TODO :下周总结

**Js调用Android的实现方式：**<br/>
方法一：<br/>

步骤：<br/>
1.设置webView可以加载Js;<br/>
2.创建Js接口类；
3.设置Js接口；<br/>
4.加载Web页面；<br/>

代码实现如下：
    
    //设置webView可以加载Js
    mWebView.getSettings().setJavaScriptEnabled(true);
创建Js接口类，创建一个类，根据业务写方法，在该方法上添加注解 "@JavascriptInterface"(注解很重要)


    //设置js接口
    //参数1：创建的js接口类的对象
    //参数2：js方法中用if判断的值，这两个值要保持一致  if (window.imoocLauncher)
    mWebView.addJavascriptInterface(new ImoocInterface(this), "imoocInterface");
    
设置要加载的网页
    
    //我的是本地的html页面路径写法
     mWebView.loadUrl("file:///android_asset/index.html");

**Android调用Js中的方法：**<br/>

方法一：
