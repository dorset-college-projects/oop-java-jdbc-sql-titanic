package com.iteachonline;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class TableExample  extends JFrame {

    public TableExample()
    {
        //headers for the table
        String[] columns = new String[] {
                "Id", "Name", "Hourly Rate", "Part Time"
        };

        //actual data for the table in a 2d array
        Object[][] data = new Object[][] {
                {1, "John", 40.0, false },
                {2, "Rambo", 70.0, false },
                {3, "Zorro", 60.0, true },
        };

        DefaultTableModel model = new DefaultTableModel();
        //create table with data
   //     JTable table = new JTable(data, columns);

        model.setColumnIdentifiers(columns);
        JTable table = new JTable(model);


        // write your code here
        //   final String DB_URL = "jdbc:mysql://localhost:3306/sakila";
        final String DB_URL = "jdbc:mysql://localhost:3306/titanicmanifest";
        final String DB_USER = "root";
        final String DB_PASSWORD = "root";

        try {
            Class.forName("com.mysql.jdbc.Driver");

            Connection con = DriverManager.getConnection(
                    DB_URL, DB_USER, DB_PASSWORD);

            Statement stmt = con.createStatement();
            String sqlQuery = "select * from passengers";

            ResultSet rs = stmt.executeQuery(sqlQuery);


            ResultSetMetaData metaData = rs.getMetaData();
            int numberOfColumns = metaData.getColumnCount();
            for (int i = 1; i <= numberOfColumns; i++) {
                System.out.printf("%-8s\t", metaData.getColumnName(i));
            }
            System.out.println();


            while (rs.next()) {
                // System.out.println(rs.getInt(1) + "\t  " + rs.getString(2) + " \t " + rs.getString(3));

                System.out.println(rs.getInt(1) + "\t  " + rs.getInt(2) + " \t " + rs.getInt(3) + " \t " + rs.getString(4));
                model.addRow(new Object[]{ rs.getInt(1), rs.getString(4), rs.getString(4)});


            }


        } catch (SQLException e) {
            System.out.println(e.toString());

        } catch (Exception e) {

        } finally {
            // System.out.println("finally");
        }













        model.addRow(new Object[]{"john","testing","again"});
        model.addRow(new Object[]{data[0][2]});

        //add the table to the frame
        this.add(new JScrollPane(table));

        this.setTitle("Table Example");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }

}
