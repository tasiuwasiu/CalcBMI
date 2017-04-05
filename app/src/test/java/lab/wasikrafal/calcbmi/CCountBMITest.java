package lab.wasikrafal.calcbmi;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Rafał on 15.03.2017.
 */

public class CCountBMITest
{
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void isMassUnderZeroInvalid() throws Exception
    {
        //GIVEN
        float mass=-1.0f;
        //WHEN
        ICountBMI countBMI = new CCountBMIforKgM();
        //THEN
        assertFalse(countBMI.isValidMass(mass));
    }

    @Test
    public void isHeightOverMaxInvalid() throws Exception
    {
        //GIVEN
        float height=3.0f;
        //WHEN
        ICountBMI countBMI = new CCountBMIforKgM();
        //THEN
        assertFalse(countBMI.isValidHeight(height));
    }

    @Test
    public void isMyMassValid() throws Exception
    {
        //GIVEN
        float mass=66.0f;
        //WHEN
        ICountBMI countBMI = new CCountBMIforKgM();
        //THEN
        assertTrue(countBMI.isValidMass(mass));
    }

    @Test
    public void isMyHeightValid() throws Exception
    {
        //GIVEN
        float height=1.85f;
        //WHEN
        ICountBMI countBMI = new CCountBMIforKgM();
        //THEN
        assertTrue(countBMI.isValidHeight(height));
    }

    @Test(expected= IllegalArgumentException.class)
    public void isMassThrowinExceptionBMI()
    {
        //GIVEN
        float height=2.0f;
        float mass=666.0f;
        //WHEN
        ICountBMI countBMI = new CCountBMIforKgM();
        //THEN
        countBMI.countBMI(mass, height);
    }

    @Test(expected= IllegalArgumentException.class)
    public void isHeightThrowinExceptionBMI()
    {
        //GIVEN
        float height=3.0f;
        float mass=66.0f;
        //WHEN
        ICountBMI countBMI = new CCountBMIforKgM();
        //THEN
        countBMI.countBMI(mass, height);
    }

    @Test(expected= IllegalArgumentException.class)
    public void isBothThrowinExceptionBMI()
    {
        //GIVEN
        float height=3.0f;
        float mass=666.0f;
        //WHEN
        ICountBMI countBMI = new CCountBMIforKgM();
        //THEN
        countBMI.countBMI(mass, height);
    }
}
