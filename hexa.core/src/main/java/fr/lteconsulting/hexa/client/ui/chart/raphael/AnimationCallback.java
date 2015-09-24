package fr.lteconsulting.hexa.client.ui.chart.raphael;

public abstract class AnimationCallback {
    static public void fire(AnimationCallback c) {
        c.onComplete();
    }

    public abstract void onComplete();
}
