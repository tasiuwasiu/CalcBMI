package lab.wasikrafal.calcbmi;

/**
 * Created by Rafał on 28.03.2017.
 */

public class CCountBMIforLbIn implements ICountBMI
{
    static final float MIN_MASS=22.0f;
    static final float MAX_MASS=551.0f;
    static final float MIN_HEIGHT=20.0f;
    static final float MAX_HEIGHT =99.0f;

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

        return (mass*703)/(height*height);
    }
}
