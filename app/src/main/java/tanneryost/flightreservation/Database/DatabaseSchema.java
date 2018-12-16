package tanneryost.flightreservation.Database;

public class DatabaseSchema {

    public static final class GymLogTable {
        public static final String NAME = "GYMLOG";

        public static final class Cols {
            public static final String UUID     = "uuid";
            public static final String EXERCISE = "exercise";
            public static final String WEIGHT   = "weight";
            public static final String REPS     = "reps";
            public static final String DATE     = "date";
        }
    }

    public static final class AccountTable {
        public static final String NAME = "ACCOUNTS";

        public static final class Cols {
            public static final String UUID     = "uuid";
            public static final String NAME     = "name";
            public static final String PASSWD   = "password";
            public static final String DATE     = "date";

        }
    }

    public static final class FlightsTable {
        public static final String NAME = "FLIGHTS";

        public static final class Cols {
            public static final String UUID     = "uuid";
            public static final String NAME     = "name";
        }
    }
}
