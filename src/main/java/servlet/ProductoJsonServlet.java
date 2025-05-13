package servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Producto;
import service.ProductoService;
import service.ProductoServiceImplement;

import java.io.IOException;
import java.util.List;

@WebServlet("/productojson")
public class ProductoJsonServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductoService service = new ProductoServiceImplement();
        List<Producto> productos = service.listar();
        //JSON=JavaScript Object Notation(Notación de objeto de JAvaScript)        Strin
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(productos);
        resp.setContentType("application/json");
        resp.getWriter().write(json);
    }
}
