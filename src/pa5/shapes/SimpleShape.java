package pa5.shapes;//
//  SimpleShape.java
//
//  Class that represents a shape to be tessellated; cgShape, which includes
//  all student code, is derived from this class.
//
//  Of note is the protected method addTriangles() which is what students
//  should use to define their tessellations.
//
//  Students are not to modify this file.
//

import java.nio.*;
import java.util.*;

public class SimpleShape {
    ///
    // our vertex points
    ///
    private Vector<Float> points;

    ///
    // our array elements
    ///
    private Vector<Short> elements;
    private short nVerts;

    ///
    // constructor
    ///
    public SimpleShape() {
        points = new Vector<Float>();
        elements = new Vector<Short>();
        nVerts = 0;
    }

    protected void setFromShape(SimpleShape shape) {
        clear();
        this.points = shape.points;
        this.elements = shape.elements;
        this.nVerts = shape.nVerts;
    }

    ///
    // add a triangle to the shape
    ///
    protected void addTriangle(float x0, float y0, float z0,
                               float x1, float y1, float z1,
                               float x2, float y2, float z2) {
        points.add(x0);
        points.add(y0);
        points.add(z0);
        points.add(1.0f);
        elements.add(nVerts);
        nVerts++;

        points.add(x1);
        points.add(y1);
        points.add(z1);
        points.add(1.0f);
        elements.add(nVerts);
        nVerts++;

        points.add(x2);
        points.add(y2);
        points.add(z2);
        points.add(1.0f);
        elements.add(nVerts);
        nVerts++;
    }

    ///
    // clear the shape
    ///
    public void clear() {
        points.clear();
        elements.clear();
        nVerts = 0;
    }

    public Buffer getVertices() {
        float v[] = new float[points.size()];
        for (int i = 0; i < points.size(); i++) {
            v[i] = (points.elementAt(i)).floatValue();
        }
        return FloatBuffer.wrap(v);
    }

    public Buffer getElements() {
        short e[] = new short[elements.size()];
        for (int i = 0; i < elements.size(); i++) {
            e[i] = elements.elementAt(i);
        }

        return ShortBuffer.wrap(e);
    }

    public short getNVerts() {
        return nVerts;
    }

    public void tessellate(){};
}
