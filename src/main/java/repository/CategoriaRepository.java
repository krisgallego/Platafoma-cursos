package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;

import db.Conexion;
import model.Categoria;

public class CategoriaRepository {
    public void insertar(Categoria categoria) {
        String sql = "INSERT INTO categoria (nombre) VALUES (?)";

        try (Connection connection = Conexion.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, categoria.getNombre());
            preparedStatement.executeUpdate();

            System.out.println("Categoria registrada correctamente");

        } catch (Exception e) {
            System.out.println("Error al insertar categoria");
            e.printStackTrace();
        }
    }

    public List<Categoria> listar() {
        List<Categoria> categorias = new ArrayList<>();
        String sql = "SELECT * FROM categoria";

        try (Connection connection = Conexion.getConnection()) {

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                categorias.add(new Categoria(
                    resultSet.getLong("id"),
                    resultSet.getString("nombre")
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return categorias;
    }

    public Categoria buscarPorId(Long id) {
        String sql = "SELECT * FROM categoria WHERE id = ?";
        Categoria categoria = null;

        try (Connection connection = Conexion.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                categoria = new Categoria(
                    resultSet.getLong("id"),
                    resultSet.getString("nombre")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return categoria;
    }

    public void actualizar(Categoria categoria) {
        String sql = "UPDATE categoria SET nombre = ? WHERE id = ?";

        try (Connection connection = Conexion.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, categoria.getNombre());
            preparedStatement.setLong(2, categoria.getId());
            preparedStatement.executeUpdate();

            System.out.println("Categoria actualizada correctamente");

        } catch (Exception e) {
            System.out.println("Error al actualizar categoria");
            e.printStackTrace();
        }
    }

    public void eliminar(Long id) {
        String sql = "DELETE FROM categoria WHERE id = ?";

        try (Connection connection = Conexion.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();

            System.out.println("Categoria eliminada");

        } catch (Exception e) {
            System.out.println("Error al eliminar categoria");
            e.printStackTrace();
        }
    }
}
