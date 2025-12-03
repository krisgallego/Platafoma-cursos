package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import db.Conexion;
import model.Curso;

public class CursoRepository {
    public void insertar(Curso curso) {
        String sql = "INSERT INTO curso (titulo, descripcion, categoria_id) VALUES (?, ?, ?)";

        try (Connection connection = Conexion.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, curso.getTitulo());
            preparedStatement.setString(2, curso.getDescripcion());
            preparedStatement.setLong(3, curso.getCategoriaId());

            preparedStatement.executeUpdate();
            System.out.println("Curso registrado correctamente");

        } catch (Exception e) {
            System.out.println("Error al insertar curso");
            e.printStackTrace();
        }
    }

    public List<Curso> listar() {
        List<Curso> cursos = new ArrayList<>();
        String sql = "SELECT * FROM curso";

        try (Connection connection = Conexion.getConnection()) {

            Statement Statement = connection.createStatement();
            ResultSet resultSet = Statement.executeQuery(sql);

            while (resultSet.next()) {
                cursos.add(new Curso(
                    resultSet.getLong("id"),
                    resultSet.getString("titulo"),
                    resultSet.getString("descripcion"),
                    resultSet.getLong("categoria_id")
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return cursos;
    }

    public Curso buscarPorId(Long id) {
        String sql = "SELECT * FROM curso WHERE id = ?";
        Curso curso = null;

        try (Connection connection = Conexion.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                curso = new Curso(
                    resultSet.getLong("id"),
                    resultSet.getString("titulo"),
                    resultSet.getString("descripcion"),
                    resultSet.getLong("categoria_id")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return curso;
    }

    public void actualizar(Curso curso) {
        String sql = "UPDATE curso SET titulo = ?, descripcion = ?, categoria_id = ? WHERE id = ?";

        try (Connection connection = Conexion.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, curso.getTitulo());
            preparedStatement.setString(2, curso.getDescripcion());
            preparedStatement.setLong(3, curso.getCategoriaId());
            preparedStatement.setLong(4, curso.getId());

            preparedStatement.executeUpdate();
            System.out.println("Curso actualizado correctamente");

        } catch (Exception e) {
            System.out.println("Error al actualizar curso");
            e.printStackTrace();
        }
    }

    public void eliminar(Long id) {
        String sql = "DELETE FROM curso WHERE id = ?";

        try (Connection connection = Conexion.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();

            System.out.println("Curso eliminado");

        } catch (Exception e) {
            System.out.println("Error al eliminar curso");
            e.printStackTrace();
        }
    }
}
