/*
 * Copyright (C) 2015 Brent Marriott
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.runfive.decoviewlib.charts;

import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Region;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.ArrayList;

/**
 * Concrete class to use for Arc based line chart type drawing
 */
public class LineArcSeries extends ArcSeries {

    public LineArcSeries(@NonNull SeriesItem seriesItem, int totalAngle, int rotateAngle) {
        super(seriesItem, totalAngle, rotateAngle);
    }

    /**
     * Draw this arc in the current position calculated by the ValueAnimator.
     *
     * @param canvas Canvas used to draw
     * @param bounds Bounds to be used to draw the arc
     */
    @Override
    public boolean draw(Canvas canvas, RectF bounds) {
        if (super.draw(canvas, bounds)) {
            return true;
        }

        drawArc(canvas);
        drawArcEdgeDetail(canvas);
        return true;
    }

    protected void drawArc(@NonNull Canvas canvas) {
        if (mArcAngleSweep == 0) {
            return;
        }
        canvas.drawArc(mBoundsInset,
                mArcAngleStart,
                mArcAngleSweep,
                false,
                mPaint);
    }

    private void drawArcEdgeDetail(@NonNull Canvas canvas) {
        ArrayList<EdgeDetail> edgeDetailList = getSeriesItem().getEdgeDetail();
        if (edgeDetailList == null) {
            return;
        }

        for (EdgeDetail edgeDetail : edgeDetailList) {
            final boolean drawInner = edgeDetail.getEdgeType() == EdgeDetail.EdgeType.EDGE_INNER;
            if (edgeDetail.getClipPath() == null) {
                float inset = (edgeDetail.getRatio() - 0.5f) * mSeriesItem.getLineWidth();
                if (drawInner) {
                    inset = -inset;
                }

                Path clipPath = new Path();
                RectF clipRect = new RectF(mBoundsInset);
                clipRect.inset(inset, inset);
                clipPath.addOval(clipRect, Path.Direction.CW);
                edgeDetail.setClipPath(clipPath);
            }
            drawClippedArc(canvas, edgeDetail.getClipPath(), edgeDetail.getColor(),
                    drawInner ? Region.Op.INTERSECT : Region.Op.DIFFERENCE);
        }
    }


    protected void drawClippedArc(@NonNull Canvas canvas, @NonNull Path path, int color, @NonNull Region.Op combine) {
        canvas.save();

        try {
            canvas.clipPath(path, combine);
        } catch (UnsupportedOperationException e) {
            Log.w(TAG, "clipPath unavailable on API 11 - 17 without disabling hardware acceleration. (EdgeDetail functionality requires clipPath). Call DecoView.enableCompatibilityMode() to enable");
            canvas.restore();
            return;
        }

        int colorOld = mPaint.getColor();
        mPaint.setColor(color);
        drawArc(canvas);
        mPaint.setColor(colorOld);
        canvas.restore();
    }
}
