package br.ufsm.csi.flutter_back.db;

import br.ufsm.csi.flutter_back.model.Categoria;
import br.ufsm.csi.flutter_back.model.Animes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CategoriaDAO {
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;
    private String sql;

    public ArrayList<Categoria> getCategorias() {
        ArrayList<Categoria> categorias = new ArrayList<>();
        try(Connection con = new ConectaDB().getConnection()){
            this.sql= "SELECT * FROM categoria";
            this.preparedStatement = con.prepareStatement(this.sql);
            this.resultSet = this.preparedStatement.executeQuery();

            while (this.resultSet.next()){
                int codigo = this.resultSet.getInt("codigo");
                Animes animes = new AnimesDAO().getAnime(this.resultSet.getInt("codigo_anime"));
                categorias.add(Categoria.builder().codigo(codigo).animes(animes).build());

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categorias;
    }

    public boolean addCategoria(Categoria categoria){
        try(Connection con = new ConectaDB().getConnection()){
            this.sql = "INSERT INTO categoria (codigo_anime, descricao) VALUES (?, ?)";
            this.preparedStatement = con.prepareStatement(this.sql);
            this.preparedStatement.setInt(1, categoria.getAnimes().getCodigo_anime());
            this.preparedStatement.execute();
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public Categoria getCategoria(int id){
        try(Connection con = new ConectaDB().getConnection()){
            this.sql = "SELECT * FROM categoria WHERE codigo = ?";
            this.preparedStatement = con.prepareStatement(this.sql);
            this.preparedStatement.setInt(1, id);
            this.resultSet = this.preparedStatement.executeQuery();

            if(this.resultSet.next()){
                int codigo = this.resultSet.getInt("codigo");
                Animes animes = new AnimesDAO().getAnime(this.resultSet.getInt("codigo_anime"));
                String descricao = this.resultSet.getString("descricao");
                return Categoria.builder().codigo(codigo).animes(animes).descricao(descricao).build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
