# MusicSpectrumBar
音乐频谱进度条
更多详细介绍，可以参考我的这篇博客：https://blog.csdn.net/Shaojihan/article/details/95069896
前段时间，公司项目有个UI需求，要做一个这么的东西 ：



一个仿音乐频率谱线进度条(这是什么鬼?)，那好，就先将这个控件命名为：音乐频谱进度条：）

废话不多说，先来看一下效果图：



附上在GitHub上的地址，点击地址，就可以获取源码了。

如何依赖？

首先在项目的build.gradle中加入：

allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
然后添加依赖：

dependencies {
	        implementation 'com.github.CaesarShao:MusicSpectrumBar:1.1.2'
	}
接着，在布局文件中，这么使用：

<com.caesar.musicspectrumbarlibrary.MusicSpectrumBar
            android:layout_width="300dp"
            android:layout_height="100dp"
            />
直接跑应用，就可以了，效果图：



音乐频谱进度条显示的模式：
1.居中模式（默认）
效果如上图，这个是默认的显示模式，在布局文件中，加入属性：

<com.caesar.musicspectrumbarlibrary.MusicSpectrumBar
            android:layout_marginTop="50dp"
            android:layout_width="300dp"
            android:layout_height="100dp"
            app:poseType="1"
            />
2.底部对齐模式（poseType = 1）
<com.caesar.musicspectrumbarlibrary.MusicSpectrumBar
           ..
            app:poseType="1"/>


3.顶部对齐模式(poseType = 2)
<com.caesar.musicspectrumbarlibrary.MusicSpectrumBar
           ..
            app:poseType="2"/>


4.等长条显示模式（poseType = 3）
<com.caesar.musicspectrumbarlibrary.MusicSpectrumBar
           ..
            app:poseType="3"/>


颜色渲染的模式：
1.颜色渐变模式（默认）
2.固定变色模式
使用方式：

<com.caesar.musicspectrumbarlibrary.MusicSpectrumBar
      ..
        app:colorGradient="0" />

    <com.caesar.musicspectrumbarlibrary.MusicSpectrumBar
      ..
        app:colorGradient="1" />
默认的colorGradient属性为0，当设置为1时，渲染模式就变成了固定变色模式。

解释下这2种颜色渲染模式：



这张图是当进度条拉到100%时，每个条目上要显示的颜色值，（后面再讲如何自定义替换颜色显示和条目数），可以发现，颜色的总体是从蓝色渐变成粉色，下面是2种模式下，进度到30%左右和70%左右的图：

                   

上面的是默认颜色模式，下面的是，固定变色模式，可以发现，在颜色模式一下，不管你进度调到多少，你选中的进度的颜色，会从所有颜色中，按照当前百分比的形式，有整体渐变效果。就是说，在颜色模式一下，你都能够看到所有颜色的渐变渲染效果。而在颜色二模式下，每个条目能改变的颜色已经是决定好了的，例如最中间的条目，它只有2种颜色选择，选取时的“6d56e3”颜色值和未选取时的白色，无渐变效果。

好,接下来再来介绍其他的属性。

进度条未选中部分的颜色：
<com.caesar.musicspectrumbarlibrary.MusicSpectrumBar
       ..
        app:unSelectColor="@color/colorAccent" />
进度条每个条目的圆角弧度：
 <com.caesar.musicspectrumbarlibrary.MusicSpectrumBar
        ..
        app:roundAngle="5"/>
默认是5弧度，数值越大，越圆滑，对了，在api21以下的android版本，无法显示圆弧，只能显示矩形。



条目之间的距离：
<com.caesar.musicspectrumbarlibrary.MusicSpectrumBar
        ..
        app:gapMultiple="2"/>
就是每跟条目之间的距离，默认是每个条目的2倍，可以设置小数值。



可以看到，每个条目中间的空格的距离，是每个条目宽度的2倍，如果这个值越大，中间的距离就按照倍数增加。

显示3模式的条目高度：
在app:poseType = 3的模式下，再设置app:SpectMultiple这个属性。

<com.caesar.musicspectrumbarlibrary.MusicSpectrumBar
      ..
        app:poseType="3"
        app:SpectMultiple="0.5" />
这个值是设置，在3模式下，每个条目的高度占总体控件高度的百分比高度，默认取0.5，也就是一半的高度，取值范围是0~1。这个属性，必须只有在poseType为3情况下，才有用。



这张图上面的控件是设置为0.7，下面的控件是设置为0.5。

接下来到了大家最关心的问题点，如何改变选中的色彩和每个条目的高度和数目。这个目前只能在代码中设置。


这边先看这张图，其实进度条在拖满的时候，每个颜色的值都已经是决定好的了，比如第一个颜色是“0050dc”,第二个是“0650dc”，最后一个颜色是“ee5deb”,然后总共有29个条目。

接下来，我们再看目前有几种高度的条目，是有7种高度的条目，我将第一个条目的高度设置为1倍数，那第二个条目是第一个的3倍，第三个条目是5倍数，第四个是4倍数，最后一个条目是1倍数。这样，就可以得出2个数组。

private int[] highD = {1, 3, 5, 4, 6, 2, 7, 5, 6, 3, 2, 1, 2, 1, 2, 6, 5, 4, 2, 7, 5, 2, 3, 1, 2, 1, 3, 2, 1};
private String[] ColorStr = {"#0050dc", "#0650dc", "#0b51dd", "#1151dd", "#1951de", "#2052de", "#2852df", "#3153df", "#3a53e0", "#4454e0", "#4e54e1", "#5855e2", "#6255e2", "#6d56e3", "#7756e3", "#8257e4", "#8c58e5", "#9758e5", "#a159e6", "#ab59e7", "#b45ae7", "#be5ae8", "#c65be8", "#ce5be9", "#d65ce9", "#dd5cea", "#e45cea", "#e95deb", "#ee5deb"};
然后在初始化的时候，调用：

MusicSpectrumBar mb = findViewById(R.id.mb_type);
mb.setDatas(highD, ColorStr);
就可以了，这边要注意，高度数组和颜色数组的个数要相同。

然后，在poseType = 3的情况下，这个高度数组的表达意思又有点不同了，在poseType = 3时，每个条目的高度都是相同的，所以这个高度的数组，就表达为每个条目，距离顶部的倍数值。所以上面的高度数组，在poseType = 3的情况下，显示的效果是：



可以看出，这张图的每个条目，距离顶部有7种距离，我将控件的高度减去条目的高度，然后除以6，就可以得出1倍的距离，在高度数组中，如果当前值为1，那这个条目，距离顶部为0，如果当前值为2，那距离顶部高度为（2-1）*倍数。所以距离顶部的高度就是（高度值-1）*倍数得出来的值。

好，至此，音乐频谱进度条的属性介绍完了，如果大家在看了源码之后，还有什么不了解的，可以给我留言，项目我会慢慢地更新优化的。希望大家支持。

最后，转载的话，请标明出处。

 ----------古诚欺
