# crazydragon
开发工具（android studio）
## 简单的天气界面第一阶段效果图
![](https://github.com/crazyegg4/weatherpicture/blob/master/%E6%8D%95%E8%8E%B7.PNG)
### 1.1顶部工具栏

*效果图

![](https://github.com/crazyegg4/weatherpicture/blob/master/top.PNG)

采用`<LinearLayout>`中嵌套`<LinearLayout>`布局实现

*左--水平方向（1/6）

`<LinearLayout>`存放1个`<ImageView>`组件

![](https://github.com/crazyegg4/weatherpicture/blob/master/head1.PNG)

*中--水平方向（3/6）

`<LinearLayout>`存放1个`<TextView>`组件

![](https://github.com/crazyegg4/weatherpicture/blob/master/head2.PNG)

*右--水平方向（2/6）

`<LinearLayout>`存放3个`<ImageView>`组件

![](https://github.com/crazyegg4/weatherpicture/blob/master/head3.PNG)

### 1.2主界面上部（moudle_1）

*效果图

![](https://github.com/crazyegg4/weatherpicture/blob/master/moudle1.PNG)

采用`<LinearLayout>`中嵌套`<RelativeLayout>`布局实现

*左--水平方向（1/2）

![](https://github.com/crazyegg4/weatherpicture/blob/master/moudle1_left.PNG)

`<RelativeLayout>`存放3个`<TextView>`组件

*右--水平方向（1/2）

![](https://github.com/crazyegg4/weatherpicture/blob/master/moudle1_right.PNG)

`<RelativeLayout>`存放2个`<LinearLayout>`,`<LinearLayout>`中存放相关组件

### 1.2主界面中部（moudle_2）

`<LinearLayout>`中存放相关组件,左右占比1：2

![](https://github.com/crazyegg4/weatherpicture/blob/master/moudle2.PNG)

### 1.3主界面底部（moudle_3）

`<LinearLayout>`中存放相关组件，左中右占比1：1：1

![](https://github.com/crazyegg4/weatherpicture/blob/master/moudle3.PNG)

