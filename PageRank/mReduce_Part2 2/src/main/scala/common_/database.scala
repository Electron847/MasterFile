package common_
import java.sql.{DriverManager, ResultSet}; // Import necessary SQL libraries

class database {

    val connection_string = "jdbc:h2:tcp://localhost//tmp/UXDemo;user=sa;password=admin"

    Class.forName("org.h2.Driver")  // Make a call to H2 Driver so it can be used

    def read = {

      // Create a Connection Object
      val connection = DriverManager.getConnection(connection_string)

      try {

        // Ensure SQL Statements are Read-Only
        val statement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY)

        // Execute Given SQL Query
        val results = statement.executeQuery(""" SELECT "text" FROM "event" """)

        while (results.next) {
          println(results.getString("text"))
        }

      } finally {

        connection.close

      }


    }

  }







