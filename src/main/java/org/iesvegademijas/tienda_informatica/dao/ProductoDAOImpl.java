package org.iesvegademijas.tienda_informatica.dao;

import org.iesvegademijas.tienda_informatica.modelo.Fabricante;
import org.iesvegademijas.tienda_informatica.modelo.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductoDAOImpl implements ProductoDAO {


    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    FabricanteDAOImpl fabricanteDAO;

    @Override
    public void create(Producto producto) {
        Optional<Fabricante> optionalFab = fabricanteDAO.find(producto.getId_fabricante());
        if (optionalFab.isPresent()) {
            jdbcTemplate.update("INSERT INTO producto (nombre, precio, id_fabricante) VALUES (? ,? ,?)",
                    producto.getNombre(), producto.getPrecio(), producto.getId_fabricante());
        } else {
            throw new RuntimeException("El fabricante con ID " + producto.getId_fabricante() + " no existe.");
        }
    }

    @Override
    public List<Producto> getAll() {

        List<Producto> listProd = jdbcTemplate.query("SELECT * FROM producto",
                (rs, rowNum) ->
                        new Producto(
                                rs.getInt("codigo"),
                                rs.getString("nombre"),
                                rs.getDouble("precio"),
                                rs.getInt("id_fabricante")
                        )
        );

        return listProd;
    }

    @Override
    public Optional<Producto> find(int codigo) {
        return Optional.empty();
    }

    @Override
    public void update(Producto producto) {

    }

    @Override
    public void delete(int codigo) {

    }
}
