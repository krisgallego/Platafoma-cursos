package model;

public class Estudiante {
    private Long id;
    private String nombre;
    private String email;

    public Estudiante(String nombre, String email) {
        this.nombre = nombre;
        this.email = email;
    }
    
    public Estudiante(Long id, String nombre, String email) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
    }

    public Long getId() { 
        return id; 
    }

    public String getNombre() { 
        return nombre; 
    }

    public String getEmail() { 
        return email; 
    }

    public void setId(Long id) { 
        this.id = id; 
    }

    public void setNombre(String nombre) { 
        this.nombre = nombre; 
    }

    public void setEmail(String email) { 
        this.email = email; 
    }
}

