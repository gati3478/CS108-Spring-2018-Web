package db;

public final class DbContract {

    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private DbContract() {
    }

    public static class MetropolisTable {
        public static final String TABLE_NAME = "metropolises";
        public static final String COLUMN_NAME_METROPOLIS = "metropolis";
        public static final String COLUMN_NAME_CONTINENT = "continent";
        public static final String COLUMN_NAME_POPULATION = "population";
    }

}
