package SnailLifeAnimation;

public class GraphicsRefresher extends Thread{
    private AnimationPanel animationPanel;
    public GraphicsRefresher(AnimationPanel animationPanel) {
        this.animationPanel = animationPanel;
    }
    @Override
    public void run() {
        while (!Thread.interrupted()) {
            animationPanel.refresh();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}