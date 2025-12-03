package model;

public class Curso {
    private Long id;
    private String titulo;
    private String descripcion;
    private Long categoriaId;

    public Curso(String titulo, String descripcion, Long categoriaId) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.categoriaId = categoriaId;
    }

    public Curso(Long id, String titulo, String descripcion, Long categoriaId) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.categoriaId = categoriaId;
    }

    public Long getId() { 
        return id; 
    }

    public String getTitulo() { 
        return titulo; 
    }

    public String getDescripcion() { 
        return descripcion; 
    }

    public Long getCategoriaId() { 
        return categoriaId; 
    }

    public void setId(Long id) { 
        this.id = id; 
    }

    public void setTitulo(String titulo) { 
        this.titulo = titulo; 
    }

    public void setDescripcion(String descripcion) { 
        this.descripcion = descripcion; 
    }

    public void setCategoriaId(Long categoriaId) { 
        this.categoriaId = categoriaId; 
    }
}

