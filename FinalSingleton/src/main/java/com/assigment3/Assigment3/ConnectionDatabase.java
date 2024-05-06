    package com.assigment3.Assigment3;

    import com.assigment3.Assigment3.Model.User;

    public final class ConnectionDatabase {
        private static ConnectionDatabase instance;
        private static User user;

        private ConnectionDatabase(int localhost, String username, String password, String database) {
            user = new User(localhost, username, password, database);
        }

        public static synchronized ConnectionDatabase getInstance(int localhost, String username, String password, String database) {
            if (instance == null) {
                instance = new ConnectionDatabase(localhost, username, password, database);
                System.out.println("First Object Localhost: " + user.getHostname());
            }
            System.out.println("Localhost: " + user.getHostname());
            return instance;
        }
        public static String getServerInfo() {
            return user.toString();
        }
    }
