import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Rectangle2D.Double;
import java.util.ArrayList;
import java.util.Iterator;
import processing.core.PImage;

public class Mario extends Sprite {
   public static final int MARIO_WIDTH = 40;
   public static final int MARIO_HEIGHT = 60;
   private double xVelocity = 0.0D;
   private double yVelocity = 0.0D;
   private boolean onASurface = false;
   private double friction = 0.85D;
   private double gravity = 0.7D;
   private double jumpStrength = 15.0D;

   public Mario(PImage img, int x, int y) {
      super(img, x, y, 40, 60);
   }

   // Walk while within a certain velocity range
   public void walk(int dir) {
      if (this.xVelocity <= 10.0D && this.xVelocity >= -10.0D) {
         this.xVelocity += (double)dir;
      }

   }
   
   // Jump affected by gravity if Mario is on a surface
   public void jump() {
      if (this.onASurface) {
         this.yVelocity -= this.jumpStrength;
      }

   }

   public void act(ArrayList<Shape> obstacles) {   //ADD SOMETHING! PRESS i FOR INSERT MODE AND PRESS ESCAPE WHEN YOU ARE DONE. AFTER PRESSING ESCAPE, YOU CAN TYPE : w q ! to exit.
      double xCoord = this.getX();
      double yCoord = this.getY();
      double width = this.getWidth();
      double height = this.getHeight();
      this.yVelocity += this.gravity;
      double yCoord2 = yCoord + this.yVelocity;
      Double strechY = new Double(xCoord, Math.min(yCoord, yCoord2), width, height + Math.abs(this.yVelocity));
      this.onASurface = false;
      Shape headSurface;
      Shape s;
      Iterator var15;
      Rectangle r;
      if (this.yVelocity > 0.0D) {
         headSurface = null;
         var15 = obstacles.iterator();

         while(var15.hasNext()) {
            s = (Shape)var15.next();
            if (s.intersects(strechY)) {
               this.onASurface = true;
               headSurface = s;
               this.yVelocity = 0.0D;
            }
         }

         if (headSurface != null) {
            r = headSurface.getBounds();
            yCoord2 = r.getY() - height;
         }
      } else if (this.yVelocity < 0.0D) {
         headSurface = null;
         var15 = obstacles.iterator();

         while(var15.hasNext()) {
            s = (Shape)var15.next();
            if (s.intersects(strechY)) {
               headSurface = s;
               this.yVelocity = 0.0D;
            }
         }

         if (headSurface != null) {
            r = headSurface.getBounds();
            yCoord2 = r.getY() + r.getHeight();
         }
      }

      if (Math.abs(this.yVelocity) < 0.5D) {
         this.yVelocity = 0.0D;
      }

      this.xVelocity *= this.friction;
      double xCoord2 = xCoord + this.xVelocity;
      Double strechX = new Double(Math.min(xCoord, xCoord2), yCoord2, width + Math.abs(this.xVelocity), height);
      Shape leftSurface;
      Shape s2;
      Iterator var18;
      Rectangle r2;
      if (this.xVelocity > 0.0D) {
         leftSurface = null;
         var18 = obstacles.iterator();

         while(var18.hasNext()) {
            s = (Shape)var18.next();
            if (s.intersects(strechX)) {
               leftSurface = s;
               this.xVelocity = 0.0D;
            }
         }

         if (leftSurface != null) {
            r = leftSurface.getBounds();
            xCoord2 = r.getX() - width;
         }
      } else if (this.xVelocity < 0.0D) {
         leftSurface = null;
         var18 = obstacles.iterator();

         while(var18.hasNext()) {
            s = (Shape)var18.next();
            if (s.intersects(strechX)) {
               leftSurface = s;
               this.xVelocity = 0.0D;
            }
         }

         if (leftSurface != null) {
            r = leftSurface.getBounds();
            xCoord2 = r.getX() + r.getWidth();
         }
      }

      if (Math.abs(this.xVelocity) < 0.5D) {
         this.xVelocity = 0.0D;
      }

      this.moveToLocation(xCoord2, yCoord2);
   }
}
