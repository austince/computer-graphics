package pa5.shapes;

import java.util.ArrayList;

/**
 * Created by austin on 3/29/17.
 */
public class CgSphere extends SimpleShape {
    public float radius;
    private static final float A = (float) (2 / (1 + Math.sqrt(5)));
    private ArrayList<Float[]> vertices;
    /**
     * Create sphere of a given radius, centered at the origin,
     * using spherical coordinates with separate number of theta and
     * phi subdivisions.
     *
     * @param radius - Radius of the sphere
     * @param slices - number of subdivisions in the theta direction
     * @param stacks - Number of subdivisions in the phi direction.
     *               <p>
     *               Can only use calls to addTriangle
     */
    public CgSphere(float radius, int slices, int stacks) {
        if (slices < 3) slices = 3;
        if (stacks < 3) stacks = 3;
        this.radius = radius;

        createBase();
        tessellate();
    }

    private void createBase() {

    }

    @Override
    public void tessellate() {
        super.tessellate();


    }

//    private class Face {
//        public Face(float x0, float y0, float z0, float x1, float y1, float)
//    }
}
