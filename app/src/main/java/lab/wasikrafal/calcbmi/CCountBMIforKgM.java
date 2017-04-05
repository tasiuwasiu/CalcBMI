package lab.wasikrafal.calcbmi;

/**
 * Created by Rafał on 15.03.2017.
 */

public class CCountBMIforKgM implements ICountBMI
{
    static final float MIN_MASS=10.0f;
    static final float MAX_MASS=250.0f;
    static final float MIN_HEIGHT=0.5f;
    static final float MAX_HEIGHT =2.5f;

    @Override
    public boolean isValidMass(float mass)
    {
        return mass>=MIN_MASS && mass<=MAX_MASS;
    }

    @Override
    public boolean isValidHeight(float height)
    {
        return height>=MIN_HEIGHT && height<= MAX_HEIGHT;
    }

    @Override
    public float countBMI(float mass, float height) throws IllegalArgumentException
    {
        if (!(isValidHeight(height) && isValidMass(mass)))
            throw new IllegalArgumentException("Wrong data");

        return mass/(height*height);
    }
}
