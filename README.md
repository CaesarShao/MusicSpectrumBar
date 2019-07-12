# MusicSpectrumBar
音乐频谱进度条
更多详细介绍，可以参考我的这篇博客：https://blog.csdn.net/Shaojihan/article/details/95069896
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
直接跑应用，就可以了。
