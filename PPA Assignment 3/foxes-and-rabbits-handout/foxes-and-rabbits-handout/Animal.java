import java.util.List;
import java.util.Random;

/**
 * A class representing shared characteristics of animals.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 2016.02.29 (2)
 */
public abstract class Animal
{
    // Whether the animal is alive or not.
    private boolean alive;
    // The animals's age.
    private int age;
    // The animal's field.
    private Field field;
    // The animal's position in the field.
    private Location location;
    // The age at which the animal can start to breed.
    protected static int breeding_age; // = 5;
    // The age to which the animal can live.
    protected static int max_age; // = 40;
    // The likelihood of the animal breeding.
    protected static double breeding_probability; // = 0.12;
    // The maximum number of births.
    protected static int max_litter_size; // = 4;
    // A shared random number generator to control breeding.
    private static final Random rand = Randomizer.getRandom();
    // The animal's food level, which is increased by eating.
    private int food_level;
    
    private boolean gender;
    
    
    /**
     * Create a new animal at location in field.
     * 
     * @param field The field currently occupied.
     * @param location The location within the field.
     */
    public Animal(boolean randomAge, Field field, Location location, int breeding_age, int max_age, double breeding_probability, int max_litter_size)
    {
        alive = true;
        age = 0;
        if(randomAge) {
            age = rand.nextInt(max_age);
        }
        this.field = field;
        setLocation(location);
        this.breeding_age = breeding_age;
        this.max_age = max_age;
        this.breeding_probability = breeding_probability;
        this.max_litter_size = max_litter_size;
        gender = Math.random() < 0.5;
    }
    
    /**
     * Make this animal act - that is: make it do
     * whatever it wants/needs to do.
     * @param newAnimals A list to receive newly born animals.
     */
    abstract public void act(List<Animal> newAnimals);

    /**
     * Check whether the animal is alive or not.
     * @return true if the animal is still alive.
     */
    protected boolean isAlive()
    {
        return alive;
    }
    
    /**
     * Return animal's age.
     * @return animal's age.
     */
    protected int get_age()
    {
        return age;
    }
    
    /**
     * Increase the age.
     * This could result in the animal's death.
     */
    protected void incrementAge()
    {
        age++;
        if(age > max_age) {
            setDead();
        }
    }
    
    
    /**
     * Make this animal more hungry. This could result in the animals's death.
     */
    protected void incrementHunger()
    {
        food_level--;
        if(food_level <= 0) {
            setDead();
        }
    }
    
    /**
     * Indicate that the animal is no longer alive.
     * It is removed from the field.
     */
    protected void setDead()
    {
        alive = false;
        if(location != null) {
            field.clear(location);
            location = null;
            field = null;
        }
    }

    /**
     * Return the animal's location.
     * @return The animal's location.
     */
    protected Location getLocation()
    {
        return location;
    }
    
    /**
     * Place the animal at the new location in the given field.
     * @param newLocation The animal's new location.
     */
    protected void setLocation(Location newLocation)
    {
        if(location != null) {
            field.clear(location);
        }
        location = newLocation;
        field.place(this, newLocation);
    }
    
    /**
     * Return the animal's field.
     * @return The animal's field.
     */
    protected Field getField()
    {
        return field;
    }
    
    /**
     * Return the animal's breeding age.
     * @return The animal's breeding age.
     */
    protected int get_breeding_age()
    {
        return breeding_age;
    }
    
    /**
     * An animal can breed if it has reached the breeding age.
     * @return true if the animal can breed, false otherwise.
     */
    protected boolean canBreed()
    {
        return age >= breeding_age;
    }
    
    /**
     * Return the animal's max age.
     * @return The animal's max age.
     */
    protected int get_max_age()
    {
        return max_age;
    }
    
    /**
     * Return the animal's breeding probability.
     * @return The animal's breeding probability.
     */
    protected double get_breeding_probability()
    {
        return breeding_probability;
    }
    
    /**
     * Return the animal's max litter size.
     * @return The animal's max litter size.
     */
    protected int get_max_litter_size()
    {
        return max_litter_size;
    }
    
    /**
     * Return the animal's rand value.
     * @return The animal's rand value.
     */
    protected Random get_rand_value()
    {
        return rand;
    }
    
    /**
     * Return the animal's food level.
     * @return The animal's food level.
     */
    protected int get_food_level()
    {
        return food_level;
    }
    
    /**
     * Set the animal's food level.
     */
    protected void set_food_level(int food_level)
    {
        this.food_level = food_level;
    }
}
