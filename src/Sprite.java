import java.awt.geom.Rectangle2D.Double;
import processing.core.PApplet;
import processing.core.PImage;

public class Sprite extends Double {
   private PImage image;
   private double vx;
   private double vy;

   public Sprite(PImage img, int x, int y, int w, int h) {
      super((double)x, (double)y, (double)w, (double)h);
      this.image = img;
      this.vx = 0.0D;
      this.vy = 0.0D;
   }

   public void moveToLocation(double x, double y) {
      super.x = x;
      super.y = y;
   }

   public void moveByAmount(double x, double y) {
      super.x += x;
      super.y += y;
   }

   public void moveByVelocities() {
      super.x += this.vx;
      super.y += this.vy;
   }

   public void setVelocity(double vx, double vy) {
      this.vx = vx;
      this.vy = vy;
   }

   public void accelerate(double ax, double ay) {
      this.vx += ax;
      this.vy += ay;
   }

   public void applyWindowLimits(int windowWidth, int windowHeight) {
      this.x = Math.min(this.x, (double)windowWidth - this.width);
      this.y = Math.min(this.y, (double)windowHeight - this.height);
      this.x = Math.max(0.0D, this.x);
      this.y = Math.max(0.0D, this.y);
   }

   public void draw(PApplet g) {
      g.image(this.image, (float)((int)this.x), (float)((int)this.y), (float)((int)this.width), (float)((int)this.height));
   }
}
