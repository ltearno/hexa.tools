package fr.lteconsulting.hexa.client.tools;

/*
 * This class is useful when wanting to wait for multiple callbacks
 */

public abstract class CallCounter {
    private int count = 0;

    protected abstract void onFinish();

    public void add() {
        count++;
    }

    public void rem() {
        count--;

        if (count == 0)
            onFinish();
    }

    public void reset() {
        count = 0;
    }

    public int count() {
        return count;
    }
}
