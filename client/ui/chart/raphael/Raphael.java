package com.hexa.client.ui.chart.raphael;

import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Raphael extends Panel {

  private RaphaelJS overlay;
  
  //private final ArrayList<Shape> shapes = new ArrayList<Shape>();
  ShapeCollection shapes = new ShapeCollection( this );
  
  /**
   * Added by saurabh tripahti
   */
  List<TextListener> textListeners = new ArrayList<TextListener>();

  public Raphael(int width, int height)
  {
	    Element raphaelDiv = DOM.createDiv();
	    setElement(raphaelDiv);
	    
	    // loads RaphaelJS script if not done already
	    if(!RaphaelJS.isDefined())
	    {
	    	Window.alert( "ERROR : RaphaelJS javascript file has not been loaded." );
//			Document doc = Document.get();
//		    ScriptElement raphaeljs = doc.createScriptElement();
//		    raphaeljs.setAttribute("type", "text/javascript");
//		    raphaeljs.setAttribute("language", "javascript");
//		    raphaeljs.setInnerText(RaphaelJsResources.RAPHAEL.raphaelText().getText());
//		    doc.getDocumentElement().getFirstChildElement().appendChild(raphaeljs);
	    }
	    
	    overlay = RaphaelJS.create( raphaelDiv, width, height );
  }
  
  @Override
  public boolean remove(Widget w) {
    // Validate.
    if (w.getParent() != this) {
      return false;
    }
    // Orphan.
    try {
      orphan(w);
    } finally {
    	Shape shape = shapes.getShape( w );
    	
    	// Physical detach
    	shape.removeFromDOM();
  
      // Logical detach.
      shapes.remove(w);
    }
    return true;
  }

@Override
public Iterator<Widget> iterator() {
	return shapes.iterator();
}

  public static boolean isSupported() {
    return RaphaelJS.isDefined();
  }

  public void clear() {
    overlay.clear();
  }

  public void setSize(int width, int height) {
    overlay.setSize(width, height);
  }

  public void addTextListener(TextListener textListener){
	  textListeners.add(textListener);
  }
  

  public class Shape extends Widget implements RaphaelObject{
    protected RaphaelJS.Element el;
    protected double rot = 0;
    protected Shape(RaphaelJS.Element obj) {
      setElement(obj.node());
      el = obj;
      
   // the DOM element has already been physically attached to the DOM (by RaphaelJS)
	// so we only need to do the logical attach
	
      // just to be clean and framework friendly. In fact since in the constructor, we already know that parent is null !
		removeFromParent();
		
		shapes.add( this );
		
		adopt( this );
    }

    public Shape animate(JSONObject newAttrs, int duration) {
      el.animate(newAttrs.getJavaScriptObject(), duration);
      return this;
    }
    public Shape animate(JSONObject newAttrs, int duration, AnimationCallback callback) {
      el.animate(newAttrs.getJavaScriptObject(), duration, callback);
      return this;
    }
    public Shape animate(JSONObject newAttrs, int duration, String easing) {
      el.animate(newAttrs.getJavaScriptObject(), duration, easing);
      return this;
    }
    public Shape animate(JSONObject newAttrs, int duration, String easing, AnimationCallback callback) {
      el.animate(newAttrs.getJavaScriptObject(), duration, easing, callback);
      return this;
    }

    public Shape animateWith(Shape shape, JSONObject newAttrs, int duration) {
      el.animateWith(shape.el, newAttrs.getJavaScriptObject(), duration);
      return this;
    }
    public Shape animateWith(Shape shape, JSONObject newAttrs, int duration, AnimationCallback callback) {
      el.animateWith(shape.el, newAttrs.getJavaScriptObject(), duration, callback);
      return this;
    }
    public Shape animateWith(Shape shape, JSONObject newAttrs, int duration, String easing) {
      el.animateWith(shape.el, newAttrs.getJavaScriptObject(), duration, easing);
      return this;
    }
    public Shape animateWith(Shape shape, JSONObject newAttrs, int duration, String easing, AnimationCallback callback) {
      el.animateWith(shape.el, newAttrs.getJavaScriptObject(), duration, easing, callback);
      return this;
    }

    public Shape animateAlong(Path path, int duration) {
      el.animateAlong(path.el, duration);
      return this;
    }
    public Shape animateAlong(Path path, int duration, boolean rotate) {
      el.animateAlong(path.el, duration, rotate);
      return this;
    }
    public Shape animateAlong(Path path, int duration, boolean rotate, AnimationCallback callback) {
      el.animateAlong(path.el, duration, rotate, callback);
      return this;
    }

    public Shape animateAlongBack(Path path, int duration) {
      el.animateAlongBack(path.el, duration);
      return this;
    }
    public Shape animateAlongBack(Path path, int duration, boolean rotate) {
      el.animateAlongBack(path.el, duration, rotate);
      return this;
    }
    public Shape animateAlongBack(Path path, int duration, boolean rotate, AnimationCallback callback) {
      el.animateAlongBack(path.el, duration, rotate, callback);
      return this;
    }

    public Shape attr(String attributeName, String value) {
      el.attr(attributeName, value);
      return this;
    }
    public Shape attr(String attributeName, double value) {
      el.attr(attributeName, value);
      return this;
    }
    public Shape attr(JSONObject params) {
      el.attr(params.getJavaScriptObject());
      return this;
    }
    public double attrAsDouble(String name) {
      return el.attrAsDouble(name);
    }
    public String attrAsString(String name) {
      return el.attrAsString(name);
    }
    public JSONObject attr(JSONArray attributeNames) {
      return new JSONObject(el.attr(attributeNames.getJavaScriptObject()));
    }

    public BBox getBBox() {
      return el.getBBox();
    }

    public void hide() {
      el.hide();
    }

    public void removeFromDOM() {
      el.remove();
    }

    public Shape rotate(double degree) {
      rot = degree;
      el.rotate(degree);
      return this;
    }
    public Shape rotate(double degree, boolean isAbsolute) {
      if (isAbsolute)
        rot = degree;
      else
        rot += degree;
      el.rotate(degree, isAbsolute);
      return this;
    }
    public Shape rotate(double degree, double cx, double cy) {
      rot = degree;
      el.rotate(degree, cx, cy);
      return this;
    }
    public Shape rotate(double degree, double cx, double cy, boolean isAbsolute) {
      if (isAbsolute)
        rot = degree;
      else
        rot += degree;
      el.rotate(rot, cx, cy);
      return this;
    }

    public Shape scale(double sx, double sy) {
      el.scale(sx, sy);
      return this;
    }
    public Shape scale(double sx, double sy, double cx, double cy) {
      el.scale(sx, sy, cx, cy);
      return this;
    }

    public void show() {
      el.show();
    }

    public Shape toFront() {
      el.toFront();
      return this;
    }

    public Shape toBack() {
      el.toBack();
      return this;
    }

    public Shape translate(double dx, double dy) {
      el.translate(dx, dy);
      return this;
    }
    

	@Override
	public HandlerRegistration addMouseOverHandler(MouseOverHandler handler) {
		return addDomHandler(handler, MouseOverEvent.getType());
	}

	@Override
	public int compareTo(RaphaelObject o) {
		BBox thisBox = this.getBBox();
		BBox oBox = o.getBBox();
		if(thisBox.width() != oBox.width()){
			return new Double(thisBox.width()).compareTo(new Double(oBox.width()));
		}
		else if(thisBox.height() != oBox.height()){
			return new Double(thisBox.height()).compareTo(new Double(oBox.height()));			
		}
		else if(thisBox.x() != oBox.x()){
			return new Double(thisBox.x()).compareTo(new Double(oBox.x()));			
		}
		else if(thisBox.y() != oBox.y()){
			return new Double(thisBox.y()).compareTo(new Double(oBox.y()));			
		}
		
		return 0;
	}
	
	public Shape clone(){
		return new Shape(el.clone());
	}

  }

  public class Circle extends Shape {
    public Circle(double x, double y, double r) {
      super(overlay.circle(x, y, r));
    }
  }

  public class Text extends Shape {
    public Text(double x, double y, String text) {
      super(overlay.text(x, y, text));
      for(TextListener textListener : textListeners){
    	  textListener.onText(this);
      }
    }
  }
  

  public class Rect extends Shape {
    public Rect(double x, double y, double w, double h) {
      super(overlay.rect(x, y, w, h));
    }
    public Rect(double x, double y, double w, double h, double r) {
      super(overlay.rect(x, y, w, h, r));
    }
  }

  public class Ellipse extends Shape {
    public Ellipse(double x, double y, double rx, double ry) {
      super(overlay.ellipse(x, y, rx, ry));
    }
  }

  public class Image extends Shape {
    public Image(String src, double x, double y, double width, double height) {
      super(overlay.image(src, x, y, width, height));
    }
  }

  public class Path extends Shape {
    public Path() {
      super(overlay.path());
    }

    public Path(String pathString) {
      super(overlay.path(pathString));
    }

    public Path(PathBuilder builder) {
      this(builder.toString());
    }

    public int getTotalLength() {
      return ((RaphaelJS.Path)el).getTotalLength();
    }

    public Point getPointAtLength(int length) {
      return ((RaphaelJS.Path)el).getPointAtLength(length);
    }

    public void safari(){
    	overlay.safari();
    }
    /**
     * doesn't seem to work
     *
    public String getSubpath(int from, int to) {
      return ((RaphaelJS.Path)el).getSubpath(from, to);
    }
     */
  }
  

}

