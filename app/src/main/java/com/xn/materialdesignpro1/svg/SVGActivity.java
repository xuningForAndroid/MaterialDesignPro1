package com.xn.materialdesignpro1.svg;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.xn.materialdesignpro1.R;

/**
 * SVG技术：微信上的技术
 * scalable vertor graphics可伸缩的矢量图形
 * 矢量图像，采用xml来描述二维图形的语言
 *
 * Vector,在Android中指的是Vector Drawable，也就是Android中的矢量图，可以兼容2.1以上
 *
 * 使用步骤：
 *      1.http://editor.method.ac/ 实现绘制svg图片
 *      2.http://inloop.github.io/svg2android/实现将svg图片转换为xml
 *      (也可以使用AS插件来转换 drawable---new Vector,导入svg)
 *      3.再将该xml导入drawable
 *      4.引用资源
 *
 */
public class SVGActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_svg);
        ImageView imageView=findViewById(R.id.iv_svg);
        imageView.setImageResource(R.drawable.svg_img);
    }
}
