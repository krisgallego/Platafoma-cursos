package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import db.Conexion;
import model.Estudiante;

public class EstudianteRepository {
    public void insertar(Estudiante estudiante) {
        String sql = "INSERT INTO estudiante (nombre, email) VALUES (?, ?)";

        try (Connection connection = Conexion.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, estudiante.getNombre());
            preparedStatement.setString(2, estudiante.getEmail());

            preparedStatement.executeUpdate();
            System.out.println("Estudiante registrado");

        } catch (Exception e) {
            System.out.println("Error al insertar estudiante");
            e.printStackTrace();
        }
    }

    public List<Estudiante> listar() {
        List<Estudiante> estudiantes = new ArrayList<>();
        String sql = "SELECT * FROM estudiante";

        try (Connection connection = Conexion.getConnection()) {

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                estudiantes.add(new Estudiante(
                    resultSet.getLong("id"),
                    resultSet.getString("nombre"),
                    resultSet.getString("email")
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return estudiantes;
    }

    public Estudiante buscarPorId(Long id) {
        String sql = "SELECT * FROM estudiante WHERE id = ?";
        Estudiante estudiante = null;

        try (Connection connection = Conexion.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                estudiante = new Estudiante(
                    resultSet.getLong("id"),
                    resultSet.getString("nombre"),
                    resultSet.getString("email")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return estudiante;
    }

    public void actualizar(Estudiante estudiante) {
        String sql = "UPDATE estudiante SET nombre = ?, email = ? WHERE id = ?";

        try (Connection connection = Conexion.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, estudiante.getNombre());
            preparedStatement.setString(2, estudiante.getEmail());
            preparedStatement.setLong(3, estudiante.getId());

            preparedStatement.executeUpdate();
            System.out.println("Estudiante actualizado");

        } catch (Exception e) {
            System.out.println("Error al actualizar estudiante");
            e.printStackTrace();
        }
    }

    public void eliminar(Long id) {
        String sql = "DELETE FROM estudiante WHERE id = ?";

        try (Connection connection = Conexion.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();

            System.out.println("Estudiante eliminado");

        } catch (Exception e) {
            System.out.println("Error al eliminar estudiante");
            e.printStackTrace();
        }
    }

    public void inscribir(Long estudianteId, Long cursoId) {
        String sql = "INSERT INTO estudiantes_cursos (estudiante_id, curso_id) VALUES (?, ?)";

        try (Connection connection = Conexion.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, estudianteId);
            preparedStatement.setLong(2, cursoId);

            preparedStatement.executeUpdate();
            System.out.println("Inscripción realizada");

        } catch (Exception e) {
            System.out.println("Error al inscribir estudiante");
            e.printStackTrace();
        }
    }
}
