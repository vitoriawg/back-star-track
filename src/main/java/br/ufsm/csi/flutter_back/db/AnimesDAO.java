package br.ufsm.csi.flutter_back.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import br.ufsm.csi.flutter_back.model.Animes;

public class AnimesDAO {
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;
    private String sql;

    public boolean addAnime(Animes animes){
        try(Connection connection = new ConectaDB().getConnection()){
            this.sql = "INSERT INTO animes (nome, descricao) VALUES (?, ?)";
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setString(1, animes.getNome().toUpperCase());
            this.preparedStatement.setString(2, animes.getDescricao());
            this.preparedStatement.execute();
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateAnime(Animes animes, int codigo_anime){
        try(Connection connection = new ConectaDB().getConnection()){
            this.sql = "UPDATE animes SET nome = ?, valor = ? WHERE codigo_anime = ?";
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setString(1, animes.getNome().toUpperCase());
            this.preparedStatement.setString(2, animes.getDescricao());
            this.preparedStatement.setInt(3, codigo_anime);
            this.preparedStatement.execute();
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteAnime(int codigo_anime){
        try(Connection connection = new ConectaDB().getConnection()){
            this.sql = "DELETE FROM animes WHERE codigo_anime = ?";
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setInt(1, codigo_anime);
            this.preparedStatement.execute();
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<Animes> getAnimes(){
        try(Connection connection = new ConectaDB().getConnection()){
            this.sql = "SELECT * FROM animes";
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.resultSet = this.preparedStatement.executeQuery();
            ArrayList<Animes> animes = new ArrayList<>();
            while(this.resultSet.next()){
                int codigo_anime = this.resultSet.getInt("codigo_anime");
                String nome = this.resultSet.getString("nome");
                String descricao = this.resultSet.getString("descricao");
                animes.add(Animes.builder().codigo_anime(codigo_anime).nome(nome).descricao(descricao).build());
            }
            return animes;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public Animes getAnime(int codigo_anime){
        try(Connection connection = new ConectaDB().getConnection()){
            this.sql = "SELECT * FROM animes WHERE codigo_anime = ?";
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setInt(1, codigo_anime);
            this.resultSet = this.preparedStatement.executeQuery();
            if(this.resultSet.next()){
                String nome = this.resultSet.getString("nome");
                String descricao = this.resultSet.getString("descricao");
                return Animes.builder().codigo_anime(codigo_anime).nome(nome).descricao(descricao).build();
            }
            return null;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
