package tanneryost.flightreservation.Database;

public class DatabaseSchema {
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
            public static final String NUMBER     = "number";
            public static final String DEPARTURE = "departure";
            public static final String ARRIVAL = "arrival";
            public static final String TIME = "time";
            public static final String CAPACITY = "capacity";
            public static final String PRICE = "price";
        }
    }
}
