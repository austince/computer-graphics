package pa5.shapes;

/**
 * Created by austin on 3/29/17.
 */
public class CgCone extends SimpleShape {
    public float radius, baseY, vertexY, height;
    public int radialDivisions;
    public int heightDivisions;


    /**
     * Create polygons for a cone with unit height, centered at the
     * origin, with separate number of radial subdivisions and height
     * subdivisions.
     *
     * @param radius          - Radius of the base of the cone
     * @param radialDivisions - number of subdivisions on the radial base
     * @param heightDivisions - number of subdivisions along the height
     *                        <p>
     *                        Can only use calls to addTriangle()
     */
    public CgCone(float radius, int radialDivisions, int heightDivisions) {
        if (radius < 0)
            radius = 0;

        if (radialDivisions < 3)
            radialDivisions = 3;

        if (heightDivisions < 1)
            heightDivisions = 1;

        this.radius = radius;
        this.radialDivisions = radialDivisions;
        this.heightDivisions = heightDivisions;
        this.baseY = -0.5f;
        this.vertexY = 0.5f;
        this.height = vertexY - baseY;
        tessellate();
    }

    @Override
    public void tessellate() {
        super.tessellate();

        float step = (float) (Math.PI * 2 / radialDivisions);
        float xStart = (float) (this.radius * Math.cos(0));
        float zStart = (float) (this.radius * Math.sin(0));

        // Draw Radial Sections
        for (float alpha = step; alpha <= Math.PI * 2; alpha += step) {
            float xEnd = (float) (this.radius * Math.cos(alpha));
            float zEnd = (float) (this.radius * Math.sin(alpha));

            // One radial
            addTriangle(0, this.vertexY, 0,
                    xStart, this.baseY, zStart,
                    xEnd, this.baseY, zEnd);

            xStart = xEnd;
            zStart = zEnd;
        }

        // Draw height divisions from yBottom to yTop
        step = this.height / this.heightDivisions;
        float radiusStep = this.radius / this.heightDivisions;
        for (float y = baseY, currentRadius = this.radius;
             y <= vertexY - step;
            // Need to notch radius in
             y += step, currentRadius -= radiusStep) {

            // Draw rectangles along the edge of the circle
            xStart = (float) (currentRadius * Math.cos(0));
            zStart = (float) (currentRadius * Math.sin(0));

            for (float alpha = step; alpha <= Math.PI * 2; alpha += step) {
                float xEnd = (float) (currentRadius * Math.cos(alpha));
                float zEnd = (float) (currentRadius * Math.sin(alpha));

                // Need two triangle in rect
                // Upper
//                addTriangle(xStart, y, zStart,
//                        xEnd, y + step, zEnd,
//                        xStart, y + step, zEnd);

                addTriangle(xStart, y + step, zStart,
                        xStart, y, zStart,
                        xEnd, y, zEnd);

                // Lower
//                addTriangle(xStart, y + step, zStart,
//                        xEnd, y, zStart,
//                        xEnd, y, zEnd);

                xStart = xEnd;
                zStart = zEnd;
            }
        }
    }
}
