package com.xn.materialdesignpro1.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.xn.materialdesignpro1.ChartViewActivity;
import com.xn.materialdesignpro1.CoverFlow.CoverFlowActivity;
import com.xn.materialdesignpro1.R;
import com.xn.materialdesignpro1.anim_behavior.SixteenActivity;
import com.xn.materialdesignpro1.banner.MyBannerActivity;
import com.xn.materialdesignpro1.bessel_curve.BesselCurveActivity;
import com.xn.materialdesignpro1.bessel_curve.BesselTouchActivity;
import com.xn.materialdesignpro1.bessel_curve.PathMeasureSearchActivity;
import com.xn.materialdesignpro1.bessel_curve.WaveBallActivity;
import com.xn.materialdesignpro1.bessel_curve.WaveShipViewActivity;
import com.xn.materialdesignpro1.bessel_curve.indicator.DropIndicatorActivity;
import com.xn.materialdesignpro1.canvas.AnimSearchViewActivity;
import com.xn.materialdesignpro1.canvas.CanvasActivity;
import com.xn.materialdesignpro1.cardviewandfat.ForteenActivity;
import com.xn.materialdesignpro1.cardviewandfat.ThirteenActivity;
import com.xn.materialdesignpro1.coordinatelayout.EighteenActivity;
import com.xn.materialdesignpro1.coordinatelayout.NinteenActivity;
import com.xn.materialdesignpro1.coordinatelayout.TwentithActivity;
import com.xn.materialdesignpro1.coordinatelayout.TwentyFirstActivity;
import com.xn.materialdesignpro1.coordinatelayout.TwentyFourActivity;
import com.xn.materialdesignpro1.coordinatelayout.TwentyThreeActivity;
import com.xn.materialdesignpro1.coordinatelayout.TwentyTwoActivity;
import com.xn.materialdesignpro1.coordinatelayout.uc_demo.UcMainActivity;
import com.xn.materialdesignpro1.custom_anim_frame.CustomAnimActivity;
import com.xn.materialdesignpro1.custom_anim_frame2.CustomAnim2Activity;
import com.xn.materialdesignpro1.custom_view.FlowLayoutActivity;
import com.xn.materialdesignpro1.custom_view.LeafLoadingActivity;
import com.xn.materialdesignpro1.custom_view.MyFirstViewGroupActivity;
import com.xn.materialdesignpro1.custom_view.MyLoadingActivity;
import com.xn.materialdesignpro1.custom_view.MyLoadingView2Activity;
import com.xn.materialdesignpro1.custom_view.MyLoadingView3Activity;
import com.xn.materialdesignpro1.custom_view.PieChartActivity;
import com.xn.materialdesignpro1.custom_view.RadarNetActivity;
import com.xn.materialdesignpro1.custom_view.StepViewActivity;
import com.xn.materialdesignpro1.drawerlayout.ThirdActivity;
import com.xn.materialdesignpro1.event_conflict.ScrollAndListActivity;
import com.xn.materialdesignpro1.event_dispatch.CustomSlidingItemActivity;
import com.xn.materialdesignpro1.event_dispatch.CustomSlidingMenuActivity;
import com.xn.materialdesignpro1.event_dispatch.CustomVpActivity;
import com.xn.materialdesignpro1.event_dispatch.EventDispatchActivity;
import com.xn.materialdesignpro1.fab_anim.FifteenActivity;
import com.xn.materialdesignpro1.headerandfooter.MainActivity;
import com.xn.materialdesignpro1.itemtouchhelper.SecondActivity;
import com.xn.materialdesignpro1.maskFilter.FilterActivity;
import com.xn.materialdesignpro1.md_animator.MaterialDesignAnimatorActivity;
import com.xn.materialdesignpro1.navigationview.ForthActivity;
import com.xn.materialdesignpro1.paint.GradientActivity;
import com.xn.materialdesignpro1.paint.MyCustomActivity;
import com.xn.materialdesignpro1.paint.PaintActivity;
import com.xn.materialdesignpro1.paint.RaDarActivity;
import com.xn.materialdesignpro1.paint.WaveActivity;
import com.xn.materialdesignpro1.paint.ZoomImageViewActivity;
import com.xn.materialdesignpro1.pannel.PannelViewActivity;
import com.xn.materialdesignpro1.parallel_universe.SeventeenActivity;
import com.xn.materialdesignpro1.pathmeasure.PathMeasureActivity;
import com.xn.materialdesignpro1.searchview.SixthActivity;
import com.xn.materialdesignpro1.snackbar.FifthActivity;
import com.xn.materialdesignpro1.svg.SVGActivity;
import com.xn.materialdesignpro1.toolbar.SeventhActivity;
import com.xn.materialdesignpro1.translusentLayout.NinthActivity;
import com.xn.materialdesignpro1.translusentLayout.TenthActivity;
import com.xn.materialdesignpro1.translusentLayout.TwelveActivity;
import com.xn.materialdesignpro1.translusenttoolbar.EighthActivity;
import com.xn.materialdesignpro1.value_anim.TransitionActivity;
import com.xn.materialdesignpro1.value_anim.ValueAnimatorActivity;
import com.xn.materialdesignpro1.value_anim.ValueAnimatordemo2Activity;
import com.xn.materialdesignpro1.vercode.VercodeActivity;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }

    public void recyAddHeader(View view) {
      startActivity(new Intent(this, MainActivity.class));
    }

    public void recyhelper(View view) {
        startActivity(new Intent(this, SecondActivity.class));
    }

    public void drawerlayout(View view) {
        startActivity(new Intent(this, ThirdActivity.class));
    }

    public void navigationV(View view) {
        startActivity(new Intent(this, ForthActivity.class));
    }

    public void snackAndToas(View view) {
        startActivity(new Intent(this, FifthActivity.class));
    }

    public void toolbarAndSearch(View view) {
        startActivity(new Intent(this, SixthActivity.class));
    }

    public void toToolbar(View view) {
        startActivity(new Intent(this, SeventhActivity.class));
    }

    public void translusentToolBar(View view) {
        startActivity(new Intent(this, EighthActivity.class));
    }

    public void translusentStatusBar(View view) {
        startActivity(new Intent(this, NinthActivity.class));
    }

    public void translusentStatusBar2(View view) {
        startActivity(new Intent(this, TenthActivity.class));
    }

    public void translucentNavigation(View view) {
        startActivity(new Intent(this, EighteenActivity.class));
    }


    public void translucentNavigationCompat(View view) {
        startActivity(new Intent(this, TwelveActivity.class));
    }

    public void cardViewAndFloatActionBtn(View view) {
        startActivity(new Intent(this, ThirteenActivity.class));
    }

    public void FloatActionBtn(View view) {
        startActivity(new Intent(this, ForteenActivity.class));
    }
    public void FloatActionBtnAndAnim(View view) {
        startActivity(new Intent(this, FifteenActivity.class));
    }

    public void behaviorforanim(View view) {
        startActivity(new Intent(this, SixteenActivity.class));
    }

    /**
     * 平行空间
     * @param view
     */
    public void pingxRoom(View view) {
        startActivity(new Intent(this, SeventeenActivity.class));
    }

    /**
     * CoordinateLayout
     * @param view
     */
    public void coordinateLayout(View view) {
        startActivity(new Intent(this, EighteenActivity.class));
    }
    public void coordinateLayout_nestedscrollview(View view) {
        startActivity(new Intent(this, NinteenActivity.class));
    }
    public void coordinateLayout_viewpager(View view) {
        startActivity(new Intent(this, TwentithActivity.class));
    }
    public void coordinateLayout_collapsingtoolbarlayout(View view) {
        startActivity(new Intent(this, TwentyFirstActivity.class));
    }

    public void coordinateLayout_behavior(View view) {
        startActivity(new Intent(this, TwentyTwoActivity.class));
    }
    public void coordinateLayout_behavior2(View view) {
        startActivity(new Intent(this, TwentyThreeActivity.class));
    }
    public void coordinateLayout_behavior3(View view) {
        startActivity(new Intent(this, TwentyFourActivity.class));
    }
    public void coordinateLayout_behavior4(View view) {
        startActivity(new Intent(this, UcMainActivity.class));
    }
    public void value_animator(View view) {
        startActivity(new Intent(this, ValueAnimatorActivity.class));
    }
    public void value_animator_demo(View view) {
        startActivity(new Intent(this, ValueAnimatordemo2Activity.class));
    }
    public void materialds_1(View view) {
        startActivity(new Intent(this, MaterialDesignAnimatorActivity.class));
    }
    public void transitionactivity(View view) {
        startActivity(new Intent(this, TransitionActivity.class));
    }
    public void svg(View view) {
        startActivity(new Intent(this, SVGActivity.class));
    }
    public void customAnim(View view) {
        startActivity(new Intent(this, CustomAnimActivity.class));
    }
    public void customAnim2(View view) {
        startActivity(new Intent(this, CustomAnim2Activity.class));
    }
    public void eventConfict(View view) {
        startActivity(new Intent(this, EventDispatchActivity.class));
    }
    public void eventConfict2(View view) {
        startActivity(new Intent(this, ScrollAndListActivity.class));
    }
    public void custom_vp(View view) {
        startActivity(new Intent(this, CustomVpActivity.class));
    }
    public void custom_qqSliding(View view) {
        startActivity(new Intent(this, CustomSlidingMenuActivity.class));
    }
    public void custom_qqItemSliding(View view) {
        startActivity(new Intent(this, CustomSlidingItemActivity.class));
    }
    public void paint(View view) {
        startActivity(new Intent(this, PaintActivity.class));
    }
    public void custom_progress(View view) {
        startActivity(new Intent(this, MyCustomActivity.class));
    }
    public void paint_gradient(View view) {
        startActivity(new Intent(this, GradientActivity.class));
    }
    public void paint_zoomImage(View view) {
        startActivity(new Intent(this, ZoomImageViewActivity.class));
    }
    public void paint_radar(View view) {
        startActivity(new Intent(this, RaDarActivity.class));
    }
    public void paint_wave(View view) {
        startActivity(new Intent(this, WaveActivity.class));
    }
    public void pannel_view(View view) {
        startActivity(new Intent(this, PannelViewActivity.class));

    }
    public void filter(View view) {
        startActivity(new Intent(this, FilterActivity.class));
    }
    public void canvas_draw(View view) {
        startActivity(new Intent(this, CanvasActivity.class));
    }
    public void canvas_searchview(View view) {
        startActivity(new Intent(this, AnimSearchViewActivity.class));
    }
    public void mybanner(View view) {
        startActivity(new Intent(this, MyBannerActivity.class));
    }
    public void coverfolw(View view) {
        startActivity(new Intent(this, CoverFlowActivity.class));
    }
    public void bessel_curve(View view) {
        startActivity(new Intent(this, BesselCurveActivity.class));
    }
    public void bessel_curve2(View view) {
        startActivity(new Intent(this, WaveBallActivity.class));
    }
    public void pathmeasure(View view) {
        startActivity(new Intent(this, PathMeasureActivity.class));
    }
    public void wave_ship(View view) {
        startActivity(new Intent(this, WaveShipViewActivity.class));
    }
    public void vercode(View view) {
        startActivity(new Intent(this, VercodeActivity.class));
    }
    public void pathMeasure_search(View view) {
        startActivity(new Intent(this, PathMeasureSearchActivity.class));
    }
    public void customview_myviewgroup(View view) {
        startActivity(new Intent(this, MyFirstViewGroupActivity.class));
    }
    public void customview_flowlayout(View view) {
        startActivity(new Intent(this, FlowLayoutActivity.class));
    }
    public void piechart(View view) {
        startActivity(new Intent(this, PieChartActivity.class));
    }
    public void stepview(View view) {
        startActivity(new Intent(this, StepViewActivity.class));
    }
    public void loadingView(View view) {
        startActivity(new Intent(this, MyLoadingActivity.class));
    }
    public void loadingView2(View view) {
        startActivity(new Intent(this, MyLoadingView2Activity.class));
    }
    public void loadingView3(View view) {
        startActivity(new Intent(this, MyLoadingView3Activity.class));
    }
    public void leafLoading(View view) {
        startActivity(new Intent(this, LeafLoadingActivity.class));
    }
    public void radarnet(View view) {
        startActivity(new Intent(this, RadarNetActivity.class));
    }
    public void besseltouch(View view) {
        startActivity(new Intent(this, BesselTouchActivity.class));
    }
    public void dropindicator(View view) {
        startActivity(new Intent(this, DropIndicatorActivity.class));
    }

    public void drawLineView(View view) {
        startActivity(new Intent(this,ChartViewActivity.class));
    }
}
