import java.awt.Rectangle;
import java.awt.Shape;
import java.util.ArrayList;
import java.util.Iterator;
import processing.core.PApplet;
import processing.core.PImage;

public class DrawingSurface extends PApplet {
   public static final int DRAWING_WIDTH = 800;
   public static final int DRAWING_HEIGHT = 600;
   private Rectangle screenRect = new Rectangle(0, 0, 800, 600);
   private Mario mario;
   private Mario mario2;
   private ArrayList<Shape> obstacles = new ArrayList();
   private ArrayList<Integer> keys = new ArrayList();
   private ArrayList<PImage> assets = new ArrayList();

   public DrawingSurface() {
      this.obstacles.add(new Rectangle(200, 400, 400, 50));
      this.obstacles.add(new Rectangle(0, 250, 100, 50));
      this.obstacles.add(new Rectangle(700, 250, 100, 50));
      this.obstacles.add(new Rectangle(375, 300, 50, 100));
      this.obstacles.add(new Rectangle(300, 250, 200, 50));
   }

   public void spawnNewMario() {
      this.mario = new Mario((PImage)this.assets.get(0), 380, 50);
      this.mario2 = new Mario((PImage)this.assets.get(0), 340, 50);
   }

   public void setup() {
      this.assets.add(this.loadImage("mario.png"));
      this.spawnNewMario();
      this.spawnNewMario();
   }

   public void draw() {
      this.background(0.0F, 255.0F, 255.0F);
      this.pushMatrix();
      float ratioX = (float)this.width / 800.0F;
      float ratioY = (float)this.height / 600.0F;
      this.scale(ratioX, ratioY);
      this.fill(100);
      Iterator var4 = this.obstacles.iterator();

      while(var4.hasNext()) {
         Shape s = (Shape)var4.next();
         if (s instanceof Rectangle) {
            Rectangle r = (Rectangle)s;
            this.rect((float)r.x, (float)r.y, (float)r.width, (float)r.height);
         }
      }

      this.mario.draw(this);
      this.popMatrix();
      if (this.isPressed(37)) {
         this.mario.walk(-1);
      }

      if (this.isPressed(39)) {
         this.mario.walk(1);
      }

      if (this.isPressed(38)) {
         this.mario.jump();
      }

      this.mario.act(this.obstacles);
      if (!this.screenRect.intersects(this.mario2)) {
         this.spawnNewMario();
      }
      this.mario2.draw(this);
      if (this.isPressed(65)) {
         this.mario2.walk(-1);
      }

      if (this.isPressed(68)) {
         this.mario2.walk(1);
      }

      if (this.isPressed(87)) {
         this.mario2.jump();
      }

      this.mario2.act(this.obstacles);
      if (!this.screenRect.intersects(this.mario2)) {
         this.spawnNewMario();
      }

   }

   public void keyPressed() {
      this.keys.add(this.keyCode);
   }

   public void keyReleased() {
      while(this.keys.contains(this.keyCode)) {
         this.keys.remove(new Integer(this.keyCode));
      }

   }

   public boolean isPressed(Integer code) {
      return this.keys.contains(code);
   }
}
