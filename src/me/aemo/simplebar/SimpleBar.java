package me.aemo.simplebar;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.runtime.*;
import com.google.appinventor.components.runtime.util.MediaUtil;

import com.irozon.justbar.BarItem;
import com.irozon.justbar.JustBar;
import com.irozon.justbar.interfaces.OnBarItemClickListener;

import java.io.IOException;

public class SimpleBar extends AndroidNonvisibleComponent implements Component {

    private final ComponentContainer container;
    private JustBar jb;


    private final int default_unselected_color = Color.parseColor("#E0E0E0");
    private final int default_selected_color = Color.parseColor("#E53935");
    private final int default_unselected_icon_color = Color.parseColor("#000000");
    private final int default_selected_icon_color = Color.parseColor("#FFFFFF");
    private final int default_radius = (int) DpToPixel(25);

    public SimpleBar(ComponentContainer container) {
        super(container.$form());
        this.container = container;
    }


    @SimpleFunction
    public void Initialize(AndroidViewComponent view) {
        jb = new JustBar((Context) container);
        jb.setOnBarItemClickListener(new OnBarItemClickListener() {
            @Override
            public void onBarItemClick(BarItem barItem, int position) {
                OnItemClick((position + 1) / 2, (String) barItem.getTag());
            }
        });

        ViewGroup vg = (ViewGroup) view.getView();
        if (vg.getChildCount() > 0) {
            vg.removeAllViews();
        }
        vg.addView(jb, new ViewGroup.LayoutParams(-1, -1));
    }

    @SimpleEvent
    public void OnItemClick(int position, String tag) {
        EventDispatcher.dispatchEvent(this, "OnItemClick", position, tag);
    }


    @SimpleFunction
    public void AddBarItem(String tag, String imgPath) throws IOException {
        BarItem barItem = new BarItem((Context) container);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(0, 0);
        barItem.setLayoutParams(params);
        barItem.setIcon(MediaUtil.getBitmapDrawable(form, imgPath));
        jb.addBarItemWith(barItem, tag);
    }

    @SimpleFunction
    public int ItemsCount(){
        return ((jb.getChildCount() -1)  / 2);
    }

    @SimpleFunction
    public void SelectedByPosition(int position) {
        jb.setSelected(position);
    }

    @SimpleFunction
    public void SelectedByTag(String tag){
        jb.setSelected(tag);
    }




    //////////////////////// Properties \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

    // RADIUS
    @SimpleFunction
    public void SetRadiusByPosition(int position, int radius) {
        jb.getItemAt((position * 2) - 1).setRadius(radius);
    }
    @SimpleFunction
    public void SetRadiusByTag(String tag, int radius) {
        jb.getItemBy(tag).setRadius(radius);
    }

    // ICON
    @SimpleFunction
    public void SetIconByPosition(int position, String imgPath) throws IOException {
        jb.getItemAt((position * 2) - 1).setIcon(MediaUtil.getBitmapDrawable(form, imgPath));
    }
    @SimpleFunction
    public void SetIconByTag(String  tag, String imgPath) throws IOException {
        jb.getItemBy(tag).setIcon(MediaUtil.getBitmapDrawable(form, imgPath));
    }

    // IS SELECTED
    @SimpleFunction
    public void SetSelectedByPosition(int position, boolean selected) {
        jb.getItemAt((position * 2) - 1).setSelected(selected);
    }
    @SimpleFunction
    public void SetSelectedByTag(String tag, boolean selected) {
        jb.getItemBy(tag).setSelected(selected);
    }

    // SELECTED COLOR
    @SimpleFunction
    public void SetSelectedColorByPosition(int position, int color) {
        jb.getItemAt((position * 2) - 1).setSelectedColor(color);
    }
    @SimpleFunction
    public void SetSelectedColorByTag(String tag, int color) {
        jb.getItemBy(tag).setSelectedColor(color);
    }

    // UN SELECTED COLOR
    @SimpleFunction
    public void SetUnSelectedColorByPosition(int position, int color) {
        jb.getItemAt((position * 2) - 1).setUnSelectedColor(color);
    }
    @SimpleFunction
    public void SetUnSelectedColorByTag(String tag, int color) {
        jb.getItemBy(tag).setUnSelectedColor(color);
    }

    // SELECTED ICON COLOR
    @SimpleFunction
    public void SetSelectedIconColorByPosition(int position, int color) {
        jb.getItemAt((position * 2) - 1).setSelectedIconColor(color);
    }
    @SimpleFunction
    public void SetSelectedIconColorByTag(String tag, int color) {
        jb.getItemBy(tag).setSelectedIconColor(color);
    }

    // UN SELECTED ICON COLOR
    @SimpleFunction
    public void SetUnSelectedIconColorByPosition(int position, int color) {
        jb.getItemAt((position * 2) - 1).setUnSelectedIconColor(color);
    }
    @SimpleFunction
    public void SetUnSelectedIconColorByTag(String tag, int color) {
        jb.getItemBy(tag).setUnSelectedIconColor(color);
    }

    // DP TO PIX
    @SimpleFunction
    public float DpToPixel(int dp) {
        Resources resources = Resources.getSystem();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        return dp * ((float) metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }







    @SimpleProperty
    public int DefaultUnselectedColor() {
        return default_unselected_color;
    }
    @SimpleProperty
    public int DefaultSelectedColor() {
        return default_selected_color;
    }
    @SimpleProperty
    public int DefaultUnselectedIconColor() {
        return default_unselected_icon_color;
    }
    @SimpleProperty
    public int DefaultSelectedIconColor() {
        return default_selected_icon_color;
    }
    @SimpleProperty
    public int DefaultRadius() {
        return default_radius;
    }

}
