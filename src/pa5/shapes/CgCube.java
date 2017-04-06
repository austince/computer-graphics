package pa5.shapes;

public class CgCube extends SimpleShape {
    public static final int NUM_VERTS = 8;
    public static final int NUM_FACES = 6;
    // x,y,z
    public static float VERTICES[][] = {
            // 0 front bottom left
            {-0.5f, -0.5f, -0.5f},
            // 1 front bottom right
            {0.5f, -0.5f, -0.5f},
            // 2 back bottom left
            {-0.5f, 0.5f, -0.5f},
            // 3 back bottom right
            {0.5f, 0.5f, -0.5f},
            // 4 front top left
            {-0.5f, -0.5f, 0.5f},
            // 5 back top left
            {-0.5f, 0.5f, 0.5f},
            // 6 front top right
            {0.5f, -0.5f, 0.5f},
            // 7 back top right
            {0.5f, 0.5f, 0.5f},
    };

    public int subdivisions;
    public Face faces[];


    /**
     * Create a unit cube, centered at the origin, with a given
     * number of subdivisions in each direction on each face.
     *
     * @param subdivisions - number of equal subdivisons to be made in each
     *                     direction along each face
     *                     <p>
     *                     Can only use calls to addTriangle()
     */
    public CgCube(int subdivisions) {
        if (subdivisions < 1)
            subdivisions = 1;

        this.subdivisions = subdivisions;

        this.faces = new Face[NUM_FACES];
        // Facing: x, z
        // Front
        this.faces[0] = new Face(
                VERTICES[0][0], VERTICES[0][2], // ll
                VERTICES[1][0], VERTICES[1][2], // lr
                VERTICES[4][0], VERTICES[4][2], // ul
                VERTICES[6][0], VERTICES[6][2], // ur
                VERTICES[1][1], Orientation.FACING // y in this case is constant
        );
        // Back
        this.faces[1] = new Face(
                VERTICES[2][0], VERTICES[2][2], // ll
                VERTICES[3][0], VERTICES[3][2], // lr
                VERTICES[5][0], VERTICES[5][2], // ul
                VERTICES[7][0], VERTICES[7][2], // ur
                VERTICES[2][1], Orientation.FACING // y in this case is constant
        );
        // Side: y, z
        // Right Side
        this.faces[2] = new Face(
                VERTICES[1][1], VERTICES[1][2], // ll
                VERTICES[3][1], VERTICES[3][2], // lr
                VERTICES[6][1], VERTICES[5][2], // ul
                VERTICES[7][1], VERTICES[7][2], // ur
                VERTICES[1][1], Orientation.SIDE // x in this case is constant
        );
        // Left Side
        this.faces[3] = new Face(
                VERTICES[0][1], VERTICES[0][2], // ll
                VERTICES[2][1], VERTICES[2][2], // lr
                VERTICES[4][1], VERTICES[4][2], // ul
                VERTICES[5][1], VERTICES[5][2], // ur
                VERTICES[0][1], Orientation.SIDE // x in this case is constant
        );
        // Up: x, y
        // Top
        this.faces[4] = new Face(
                VERTICES[6][0], VERTICES[6][1], // ll
                VERTICES[7][0], VERTICES[7][1], // lr
                VERTICES[4][0], VERTICES[4][1], // ul
                VERTICES[5][0], VERTICES[5][1], // ur
                VERTICES[6][2], Orientation.UP // z in this case is constant
        );
        // Bottom
        this.faces[5] = new Face(
                VERTICES[1][1], VERTICES[1][2], // ll
                VERTICES[3][1], VERTICES[3][2], // lr
                VERTICES[0][1], VERTICES[0][2], // ul
                VERTICES[2][1], VERTICES[2][2], // ur
                VERTICES[1][2], Orientation.UP // z in this case is constant
        );


        tessellate();
    }

    public void drawFace(Face face, int subdivisionsLeft) {
        // Draw Two triangles:
        // ul ll lr
        // ll lr ur
        switch (face.orientation) {
            case UP:
                // Treat face.z as z
                // Points as x,y
                addTriangle(face.ul[0], face.ul[1], face.z,
                        face.ll[0], face.ll[1], face.z,
                        face.lr[0], face.lr[1], face.z
                );
                addTriangle(
                        face.ll[0], face.ll[1], face.z,
                        face.lr[0], face.lr[1], face.z,
                        face.ur[0], face.ur[1], face.z
                );
                break;
            case SIDE:
                // Treat face.z as x
                // Points as y, z
                addTriangle(face.z, face.ul[0], face.ul[1],
                        face.z, face.ll[0], face.ll[1],
                        face.z, face.lr[0], face.lr[1]
                );
                addTriangle(
                        face.z, face.ll[0], face.ll[1],
                        face.z, face.lr[0], face.lr[1],
                        face.z, face.ur[0], face.ur[1]
                );
                break;
            case FACING:
                // Treat face.z as y
                // Points as x, z
                addTriangle(face.ul[0], face.z, face.ul[1],
                        face.ll[0], face.z, face.ll[1],
                        face.lr[0], face.z, face.lr[1]
                );
                addTriangle(
                        face.ll[0], face.z, face.ll[1],
                        face.lr[0], face.z, face.lr[1],
                        face.ur[0], face.z, face.ur[1]
                );
                break;
        }

        if (subdivisionsLeft == 0) {
            return;
        } else {
            // Keep subdividing all subfaces
            for (Face subface : face.divide()) {
                drawFace(subface, subdivisionsLeft - 1);
            }
        }


    }

    @Override
    public void tessellate() {
        super.tessellate();

        for (Face face : this.faces) {
            if (face != null)
                drawFace(face, this.subdivisions - 1);
        }
    }

    enum Orientation {
        UP,
        SIDE,
        FACING
    }

    /**
     * Treats z as just the constant variable. Not necessarily the z coordinate
     * <p>
     * Really should only need two points and a constant
     */
    private class Face {
        public float[] ul, ur, ll, lr;
        public float z;
        public Orientation orientation;

        public Face(float llx, float lly, float lrx, float lry,
                    float ulx, float uly, float urx, float ury, float z, Orientation orientation) {
            float[] ll = {llx, lly};
            float[] lr = {lrx, lry};
            float[] ul = {ulx, uly};
            float[] ur = {urx, ury};
            this.ul = ul;
            this.ur = ur;
            this.ll = ll;
            this.lr = lr;
            this.z = z;
            this.orientation = orientation;
        }


        public Face(float[] ll, float[] lr, float[] ul, float[] ur, float z, Orientation orientation) {
            this.ul = ul;
            this.ur = ur;
            this.ll = ll;
            this.lr = lr;
            this.z = z;
            this.orientation = orientation;
        }


        public Face[] divide() {
            // Make 4 new faces
//            Face divisions[] = new Face[4];
            float sideLen = (Math.abs(ur[0]) - ul[0]) / 2;
            // Brute force for the win
            Face divisions[] = {
                    // Top Left
                    new Face(
                            ll[0], ul[1] - sideLen, // ll
                            ur[0] - sideLen, ur[1] - sideLen, // lr
                            ul[0], ul[1], // ul
                            ur[0] - sideLen, ur[1], // ur
                            z, orientation
                    ),
                    // Top Right
                    new Face(
                            ur[0] - sideLen, ur[1] - sideLen, // ll
                            ur[0], ur[1] - sideLen, // lr
                            ur[0] - sideLen, ur[1], // ul
                            ur[0], ur[1], // ur
                            z, orientation
                    ),
                    // Bottom Left
                    new Face(
                            ll[0], ll[1], // ll
                            lr[0] - sideLen, lr[1], // lr
                            ll[0], ul[1] - sideLen, // ul
                            ur[0] - sideLen, ur[1] - sideLen, // ur
                            z, orientation
                    ),
                    // Bottom Right
                    new Face(
                            lr[0] - sideLen, lr[1], // ll
                            lr[0], lr[1], // lr
                            ur[0] - sideLen, ur[1] - sideLen, // ul
                            ur[0], ur[1] - sideLen, // ur
                            z, orientation
                    ),
            };

            return divisions;
        }
    }
}
