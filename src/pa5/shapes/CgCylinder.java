package pa5.shapes;

/**
 * Created by austin on 3/29/17.
 */
public class CgCylinder extends SimpleShape {
    public float radius, yTop, yBottom, height;
    public int radialDivisions;
    public int heightDivisions;

    /**
     * Create polygons for a cylinder with unit height, centered
     * at the origin, with separate number of radial subdivisions and height
     * subdivisions.
     *
     * @param radius          - Radius of the base of the cylinder
     * @param radialDivisions - number of subdivisions on the radial base
     * @param heightDivisions - number of subdivisions along the height
     *                        <p>
     *                        Can only use calls to addTriangle()
     */
    public CgCylinder(float radius, int radialDivisions, int heightDivisions) {
        if (radialDivisions < 3)
            radialDivisions = 3;

        if (heightDivisions < 1)
            heightDivisions = 1;

        this.radius = radius;
        this.radialDivisions = radialDivisions;
        this.heightDivisions = heightDivisions;

        // Enclosed by two circular disks
        // centered at y +- 0.5
        this.yTop = 0.5f;
        this.yBottom = -0.5f;
        this.height = yTop - yBottom;
        tessellate();
    }

    @Override
    public void tessellate() {
        super.tessellate();

        float step = (float) (Math.PI * 2 / radialDivisions);
        float xStart = (float) (this.radius * Math.cos(0));
        float zStart = (float) (this.radius * Math.sin(0));

        // Draw top and bottom
        for (float alpha = step; alpha <= Math.PI * 2; alpha += step) {
            float xEnd = (float) (this.radius * Math.cos(alpha));
            float zEnd = (float) (this.radius * Math.sin(alpha));

            // Top disc
            addTriangle(xStart, yTop, zStart,
                    xEnd, yTop, zEnd,
                    0, yTop, 0);

            // Bottom disc
            addTriangle(xStart, yBottom, zStart,
                    xEnd, yTop, zEnd,
                    0, yBottom, 0);

            xStart = xEnd;
            zStart = zEnd;
        }

        // Draw height divisions from yBottom to yTop
        step = this.height / this.heightDivisions;

        for (float y = yBottom; y <= yTop - step; y += step) {
            // Draw rectangles along the edge of the circle
            xStart = (float) (this.radius * Math.cos(0));
            zStart = (float) (this.radius * Math.sin(0));

            for (float alpha = step; alpha <= Math.PI * 2; alpha += step) {
                float xEnd = (float) (this.radius * Math.cos(alpha));
                float zEnd = (float) (this.radius * Math.sin(alpha));

                // Need two triangle in rect (xStart, y, zStart), (xEnd, y + step, zEnd)
                // xS, y, zS -> xE, yE, zE -> xS, yE, zE
                addTriangle(xStart, y, zStart,
                        xEnd, y + step, zEnd,
                        xStart, y + step, zEnd);
                // xS, y, zS -> xE, y, zS -> xE, yE, zE
                addTriangle(xStart, y, zStart,
                        xEnd, y, zStart,
                        xEnd, y + step, zEnd);

                xStart = xEnd;
                zStart = zEnd;
            }
        }
    }
}
