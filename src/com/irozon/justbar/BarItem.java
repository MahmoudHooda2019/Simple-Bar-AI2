package com.irozon.justbar;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import static com.irozon.justbar.Utils.dpToPixel;


public class BarItem extends RelativeLayout {

    private static final String default_unselected_color = "#E0E0E0";
    private static final String default_selected_color = "#E53935";
    private static final String default_unselected_icon_color = "#000000";
    private static final String default_selected_icon_color = "#FFFFFF";
    private static final int default_radius = (int) dpToPixel(25);

    private final Context context;
    private ImageView imageView;
    private boolean selected;

    private int selectedColor;
    private int unSelectedColor;

    private int selectedIconColor;
    private int unSelectedIconColor;

    private int diameter;

    @Override
    public boolean isSelected() {
        return selected;
    }

    @Override
    public void setSelected(boolean selected) {
        this.selected = selected;

        if (selected)
            makeSelected();
        else
            makeUnSelected();
    }

    public BarItem(Context context) {
        super(context);

        this.context = context;
        init();
    }

    public BarItem(Context context, AttributeSet attrs) {
        super(context, attrs);

        this.context = context;
        init();
    }

    public BarItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        this.context = context;
        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        // Get Radius
        getLayoutParams().height = diameter;
        getLayoutParams().width = diameter;
    }

    private void init() {

        // Get Radius
        diameter = getRadius() * 2;

        // Get Selected Status
        selected = getSelectedStatus();

        // Get icon from attributes
        Drawable icon = getIcon();

        // Get selected/unselected color
        unSelectedColor = getUnSelectedColor();
        selectedColor = getSelectedColor();

        // Get selected/unselected color for icon
        unSelectedIconColor = getUnSelectedIconColor();
        selectedIconColor = getSelectedIconColor();

        // Add background to item
        setBackground(round_background());

        // Add imageView
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT); // A position in layout.

        imageView = new ImageView(context);
        imageView.setLayoutParams(layoutParams);

        if (icon != null)
            imageView.setImageDrawable(icon);

        addView(imageView);

        if (selected)
            makeSelected();

        setInitialColors();
    }

    public static GradientDrawable round_background() {
        GradientDrawable shapeDrawable = new GradientDrawable();

        // Set the shape to rectangle
        shapeDrawable.setShape(GradientDrawable.RECTANGLE);

        // Set corner radius
        shapeDrawable.setCornerRadius(Utils.dpToPixel(50));

        // Set solid color
        shapeDrawable.setColor(ColorStateList.valueOf(Color.parseColor(default_unselected_color)));

        return shapeDrawable;
    }


    /**
     * Set initial color of the BarItem according to the attributes
     */
    private void setInitialColors() {
        if (selected) {
            getBackground().setColorFilter(selectedColor, PorterDuff.Mode.SRC_IN);
            imageView.setColorFilter(selectedIconColor);
        } else {
            getBackground().setColorFilter(unSelectedColor, PorterDuff.Mode.SRC_IN);
            imageView.setColorFilter(unSelectedIconColor);
        }

    }


    /**
     * Get initial state of the BarItem (selected/unselected)
     *
     * @return selected or unselected
     */
    private boolean getSelectedStatus() {
        return false;
    }







    /**
     * Set the radius for the BarItem.
     *
     * @param radius Radius in pixels
     */
    public void setRadius(int radius) {
        diameter = radius * 2;
        getLayoutParams().height = diameter;
        getLayoutParams().width = diameter;
    }

    /**
     * Get radius from the attributes
     *
     * @return radius of the BarItem
     *
     */
    private int getRadius() {
        return default_radius;
    }






    /**
     * Set the unselected color for the BarItem.
     *
     * @param color Color for the unselected state
     */
    public void setUnSelectedColor(int color) {
        unSelectedColor = color;
        setInitialColors(); // Update the colors immediately
    }

    /**
     * Get unselected color for BarItem from the attribute
     *
     * @return Color for unselected state for BarItem
     */
    private int getUnSelectedColor() {
        return Color.parseColor(default_unselected_color);
    }





    /**
     * Set the selected color for the BarItem.
     *
     * @param color Color for the selected state
     */
    public void setSelectedColor(int color) {
        selectedColor = color;
        setInitialColors(); // Update the colors immediately
    }
    /**
     * Get selected color for BarItem from the attribute
     *
     * @return Color for selected state for BarItem
     */
    private int getSelectedColor() {
        return Color.parseColor(default_selected_color);
    }








    /**
     * Set the unselected icon color for the BarItem.
     *
     * @param color Color for the unselected state of the icon
     */
    public void setUnSelectedIconColor(int color) {
        unSelectedIconColor = color;
        setInitialColors(); // Update the colors immediately
    }

    /**
     * Get unselected color for icon from the attribute
     *
     * @return Color for unselected state for icon
     */
    private int getUnSelectedIconColor() {
        return Color.parseColor(default_unselected_icon_color);
    }




    /**
     * Set the selected icon color for the BarItem.
     *
     * @param color Color for the selected state of the icon
     */
    public void setSelectedIconColor(int color) {
        selectedIconColor = color;
        setInitialColors(); // Update the colors immediately
    }

    /**
     * Get Selected color for icon from the attribute
     *
     * @return Color for selected state for icon
     */
    private int getSelectedIconColor() {
        return Color.parseColor(default_selected_icon_color);
    }



    /**
     * Set Icon from the attributes
     *
     * @param drawable icon image drawable
     */
    public void setIcon(Drawable drawable){
        if (drawable != null){
            imageView.setImageDrawable(drawable);
        }
    }

    /**
     * Get Icon from the attributes
     *
     * @return Icon from the attributes provided
     */
    private Drawable getIcon() {
        return null;
    }




    /**
     * Make BarItem unselected
     */
    private void makeSelected() {
        ResizeWidthAnimation anim = new ResizeWidthAnimation(this, (diameter + (diameter * 40) / 100));
        anim.setDuration(250);
        anim.setInterpolator(new BounceInterpolator(1, 1));

        startAnimation(anim);


        animateColor(this, unSelectedColor, selectedColor);

        animateColor(imageView, unSelectedIconColor, selectedIconColor);
    }

    /**
     * Make BarItem unselected
     */
    private void makeUnSelected() {
        ResizeWidthAnimation reverse = new ResizeWidthAnimation(this, diameter);
        reverse.setDuration(250);
        reverse.setInterpolator(new BounceInterpolator(1, 1));

        startAnimation(reverse);

        animateColor(this, selectedColor, unSelectedColor);

        animateColor(imageView, selectedIconColor, unSelectedIconColor);
    }

    /**
     * Animate Color on the view
     *
     * @param view      The view that's color going to change
     * @param fromColor Start color
     * @param toColor   End color
     */
    public void animateColor(final View view, int fromColor, int toColor) {
        ValueAnimator valueAnimator;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            valueAnimator = ValueAnimator.ofArgb(fromColor, toColor);
        } else {
            valueAnimator = ValueAnimator.ofObject(new ArgbEvaluator(), fromColor, toColor);
        }
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                if (view instanceof ImageView) {
                    ((ImageView) view).setColorFilter((Integer) valueAnimator.getAnimatedValue());
                } else {
                    getBackground().setColorFilter((Integer) valueAnimator.getAnimatedValue(), PorterDuff.Mode.SRC_IN);
                }
            }
        });

        valueAnimator.setDuration(300);
        valueAnimator.start();
    }
}