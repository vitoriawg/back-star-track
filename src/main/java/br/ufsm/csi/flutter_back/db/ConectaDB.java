package br.ufsm.csi.flutter_back.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConectaDB {
    private static final String URL = "jdbc:postgresql://localhost:5432/StarTrack";
    private static final String USER = "postgres";
    private static final String SENHA = "1234";

    public Connection getConnection(){
        Connection con = null;
        try {
            con = DriverManager.getConnection(ConectaDB.URL, ConectaDB.USER, ConectaDB.SENHA);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return con;
    }
}
