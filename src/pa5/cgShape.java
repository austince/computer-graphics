package pa5;
//
//  cgShape.java
//
//  Class that includes routines for tessellating a number of basic shapes.
//
//  Students are to supply their implementations for the functions in
//  this file using the function "addTriangle()" to do the tessellation.
//

import pa5.shapes.*;


public class cgShape extends SimpleShape
{
    /**
     * constructor
     */
    public cgShape()
    {
    }

    /**
     * makeCube - Create a unit cube, centered at the origin, with a given
     * number of subdivisions in each direction on each face.
     *
     * @param subdivisions - number of equal subdivisons to be made in each
     *        direction along each face
     *
     * Can only use calls to addTriangle()
     */
    public void makeCube (int subdivisions)
    {
        CgCube cube = new CgCube(subdivisions);
        setFromShape(cube);
    }

    /**
     * makeCylinder - Create polygons for a cylinder with unit height, centered
     * at the origin, with separate number of radial subdivisions and height 
     * subdivisions.
     *
     * @param radius - Radius of the base of the cylinder
     * @param radialDivisions - number of subdivisions on the radial base
     * @param heightDivisions - number of subdivisions along the height
     *
     * Can only use calls to addTriangle()
     */
    public void makeCylinder (float radius, int radialDivisions, int heightDivisions)
    {
        CgCylinder cylinder = new CgCylinder(radius, radialDivisions, heightDivisions);
        setFromShape(cylinder);
    }

    /**
     * makeCone - Create polygons for a cone with unit height, centered at the
     * origin, with separate number of radial subdivisions and height 
     * subdivisions.
     *
     * @param radius - Radius of the base of the cone
     * @param radialDivisions - number of subdivisions on the radial base
     * @param heightDivisions - number of subdivisions along the height
     *
     * Can only use calls to addTriangle()
     */
    public void makeCone (float radius, int radialDivisions, int heightDivisions)
    {
        CgCone cone = new CgCone(radius, radialDivisions, heightDivisions);
        setFromShape(cone);
    }


    /**
     * makeSphere - Create sphere of a given radius, centered at the origin, 
     * using spherical coordinates with separate number of thetha and 
     * phi subdivisions.
     *
     * @param radius - Radius of the sphere
     * @param slices - number of subdivisions in the theta direction
     * @param stacks - Number of subdivisions in the phi direction.
     *
     * Can only use calls to addTriangle
     */
    public void makeSphere (float radius, int slices, int stacks)
    {
        CgSphere sphere = new CgSphere(radius, slices, stacks);
        setFromShape(sphere);
    }
}
