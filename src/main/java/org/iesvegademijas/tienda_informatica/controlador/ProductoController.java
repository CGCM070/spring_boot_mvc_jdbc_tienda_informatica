package org.iesvegademijas.tienda_informatica.controlador;


import org.iesvegademijas.tienda_informatica.modelo.Fabricante;
import org.iesvegademijas.tienda_informatica.modelo.Producto;
import org.iesvegademijas.tienda_informatica.servicio.FabricanteService;
import org.iesvegademijas.tienda_informatica.servicio.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Optional;

@Controller
public class ProductoController {

    @Autowired
    ProductoService productoService;

    @Autowired
    FabricanteService fabricanteService;

    @GetMapping("/productos")
    public String listar (Model model) {
        List<Producto> listaProds = productoService.listAll();
        model.addAttribute("listaProds", listaProds);
        return "productos";
    }

    @GetMapping("/productos/crear")
    public String crear (Model model) {

        Producto producto = new Producto();
        List<Fabricante> listaFab = fabricanteService.listAll();

        model.addAttribute("producto", producto);
        model.addAttribute("listaFab", listaFab);

        return "crear-producto";
    }



    @PostMapping("/productos/crear")
    public RedirectView submitCrear(@ModelAttribute("productos") Producto producto) {

        productoService.newProduct(producto);

        return new RedirectView("/productos") ;

    }

    @GetMapping("/productos/editar/{id}")
    public String editar(Model model, @PathVariable Integer id) {

     Producto producto = productoService.findOnebyID(id);
        List<Fabricante>listaFab = fabricanteService.listAll();
        model.addAttribute("listaFab", listaFab);
        model.addAttribute("producto", producto);

        return "editar-producto";

    }

    @PostMapping("/productos/editar/{codigo}")
    public RedirectView editarSubmit(@ModelAttribute ("producto") Producto producto) {

        productoService.replaceProducto(producto);

        return new RedirectView("/productos");

    }

    @PostMapping("/productos/borrar/{codigo}")
    public RedirectView submitBorrar (@PathVariable Integer codigo){

        productoService.deleteProducto(codigo);

        return new RedirectView("/productos");
    }



    @GetMapping("/productos/{codigo}")
    public String detalle(Model model, @PathVariable Integer codigo ) {

        Producto producto = productoService.findOnebyID(codigo);

        Fabricante optFab = fabricanteService.one(producto.getId_fabricante());

        model.addAttribute("producto", producto);
        model.addAttribute("optFab",optFab);

        return "detalle-producto";

    }


}
