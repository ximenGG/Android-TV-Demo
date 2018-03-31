package com.iptv.hq.ui.views.indicators;

/**
 * 　　    ()  　　  ()
 * 　　   ( ) 　　　( )
 * 　　   ( ) 　　　( )
 * 　　┏┛┻━━━┛┻┓
 * 　　┃　　　━　　　┃
 * 　　┃　┳┛　┗┳　┃
 * 　　┃　　　┻　　　┃
 * 　　┗━┓　　　┏━┛
 * 　　　　┃　　　┃
 * 　　　　┃　　　┗━━━┓
 * 　　　　┃　　　　　　　┣┓
 * 　　　　┃　　　　　　　┏┛
 * 　　　　┗┓┓┏━┳┓┏┛
 * 　　　　　┃┫┫　┃┫┫
 * 　　　　　┗┻┛　┗┻┛
 * Created by ximen on 2018/1/11.
 */
public class IndicatorHelper {
    public static Indicator createIndicator(int flag) {
        Indicator indicator = null;
        switch (flag) {
            case 0:
                indicator = new BallPulseIndicator();
                break;
            case 1:
                indicator = new BallGridPulseIndicator();
                break;
            case 2:
                indicator = new BallClipRotateIndicator();
                break;
            case 3:
                indicator = new SquareSpinIndicator();
                break;
            case 4:
                indicator = new BallClipRotateMultipleIndicator();
                break;
            case 5:
                indicator = new BallPulseRiseIndicator();
                break;
            case 6:
                indicator = new BallRotateIndicator();
                break;
            case 7:
                indicator = new CubeTransitionIndicator();
                break;
            case 8:
                indicator = new BallZigZagIndicator();
                break;
            case 9:
                indicator = new BallTrianglePathIndicator();
                break;
            case 10:
                indicator = new BallScaleIndicator();
                break;
            case 11:
                indicator = new LineScaleIndicator();
                break;
            case 12:
                indicator = new LineScalePartyIndicator();
                break;
            case 13:
                indicator = new BallScaleMultipleIndicator();
                break;
            case 14:
                indicator = new BallPulseSyncIndicator();
                break;
            case 15:
                indicator = new BallBeatIndicator();
                break;
            case 16:
                indicator = new LineScalePulseOutIndicator();
                break;
            case 17:
                indicator = new LineScalePulseOutRapidIndicator();
                break;
            case 18:
                indicator = new BallScaleRippleIndicator();
                break;
            case 19:
                indicator = new BallScaleRippleMultipleIndicator();
                break;
            case 20:
                indicator = new LineSpinFadeLoaderIndicator();
                break;
            case 21:
                indicator = new TriangleSkewSpinIndicator();
                break;
            case 22:
                indicator = new PacmanIndicator();
                break;
            case 23:
                indicator = new BallGridBeatIndicator();
                break;
            case 24:
                indicator = new SemiCircleSpinIndicator();
                break;
            default:
                break;
        }
        return indicator;
    }
}
