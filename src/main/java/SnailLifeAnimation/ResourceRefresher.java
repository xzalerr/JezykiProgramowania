package SnailLifeAnimation;

public class ResourceRefresher extends Thread{
    private Leaf leaf;
    public ResourceRefresher(Leaf leaf, AnimationPanel animationPanel) {
        this.leaf = leaf;
    }
    @Override
    public synchronized void run() {
            while(!Thread.interrupted()) {
                leaf.incrementLeafCells();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
    }
}