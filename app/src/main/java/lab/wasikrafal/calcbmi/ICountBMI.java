package lab.wasikrafal.calcbmi;

/**
 * Created by Rafał on 15.03.2017.
 */

public interface ICountBMI {

    boolean isValidMass(float mass);
    boolean isValidHeight(float height);
    float countBMI(float mass, float height);

}
