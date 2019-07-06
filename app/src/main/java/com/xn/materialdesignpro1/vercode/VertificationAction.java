package com.xn.materialdesignpro1.vercode;

import android.support.annotation.ColorRes;

/**
 * 验证码事件监听
 */
public interface VertificationAction {
    //验证码位数
    void setFigures(int figures);
    //验证码各位之间的距离
    void setCodeMargins(int margin);
    //选中状态的底部颜色
    void setBottomSelectedColor(@ColorRes int selectedColor);
    //正常状态下底部线的颜色
    void setNormalLineColor(@ColorRes int normalLineColor);
    //选中状态下背景颜色
    void setSelectedBackgroundColor(@ColorRes int selectedBackgroundColor);
    //底部线条的高度
    void setBottomLineHeight(int bottomLineHeight);


    /**
     * 验证码发生变化时监听事件
     */
    interface  onVerficationCodeChangeListener{
        void onVerCodeChanged(CharSequence code,int start,int before,int count);
        void onInputCompleted(CharSequence code);
    }
}
