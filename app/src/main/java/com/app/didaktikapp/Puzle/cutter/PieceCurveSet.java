package com.app.didaktikapp.Puzle.cutter;

/**
 * @author Vladimir Shevchenko vladimir@pantrylabs.com 05/04/16.
 */
public class PieceCurveSet {

    public final HorizontalCurve top;
    public final VerticalCurve left;
    public final VerticalCurve right;
    public final HorizontalCurve bottom;

    public PieceCurveSet(HorizontalCurve top, VerticalCurve left, VerticalCurve right, HorizontalCurve bottom) {
        this.top = top;
        this.left = left;
        this.right = right;
        this.bottom = bottom;
    }
}
