package org.iesvegademijas.tienda_informatica.servicio;

import java.util.List;
import java.util.Optional;

import org.iesvegademijas.tienda_informatica.dao.FabricanteDAO;
import org.iesvegademijas.tienda_informatica.dao.ProductoDAO;
import org.iesvegademijas.tienda_informatica.modelo.Fabricante;
import org.iesvegademijas.tienda_informatica.modelo.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FabricanteService {

	@Autowired
	private FabricanteDAO fabricanteDAO;

	@Autowired
	private ProductoDAO productoDAO;
	
	public List<Fabricante> listAll() {
		
		return fabricanteDAO.getAll();
		
	}
	
	public Fabricante one(Integer id) {
		Optional<Fabricante> optFab = fabricanteDAO.find(id);
		if (optFab.isPresent())
			return optFab.get();
		else 
			return null;
	}
	
	public void newFabricante(Fabricante fabricante) {
		
		fabricanteDAO.create(fabricante);
		
	}
	
	public void replaceFabricante(Fabricante fabricante) {
		
		fabricanteDAO.update(fabricante);
		
	}
	
	public void deleteFabricante(int id) {

		List<Producto> productoList = productoDAO.getProdsByFabID(id);
		for (Producto producto : productoList){
			productoDAO.delete(producto.getCodigo());
		}
		fabricanteDAO.delete(id);
		
	}
	
}
