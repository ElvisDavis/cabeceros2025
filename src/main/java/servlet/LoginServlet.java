package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Optional;

@WebServlet({"/login", "/login.html"})
public class LoginServlet extends HttpServlet {

    //Declaramos e inicializamos dos variables
    //de tipo String para el usuario y el password
    final static String USERNAME = "admin";
    final static String PASSWORD = "12345";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Obtenemos la cookie
        Cookie[] cookies = req.getCookies() != null ? req.getCookies() : new Cookie[0];
        //Obtenemos la información que esta dentro de la cookie
        Optional<String> cookieOptinal = Arrays.stream(cookies)
                .filter(c -> "username".equals(c.getName()))
                //Convertimos la cookie a String
                .map(Cookie::getValue)
                .findAny();
        //Creamos una condicional preguntando si en la variable optionalCookie
        //Existe información sino existe lo que va hacer es regresar al login
        //para que vuelva a logearse
        if (cookieOptinal.isPresent()) {
            resp.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = resp.getWriter()) {
                resp.setContentType("text/html; charset=UTF-8");
                //Creo la plantilla html
                out.print("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<meta charset=\"utf-8\">");
                out.println("<title> Login " + cookieOptinal.get() + "</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Bienvenido a mi sistema!</h1>");
                out.println("<h3>Login exitoso" + cookieOptinal.get() + "has iniciado sesión con exito</h3>");
                out.println("</body>");
                out.println("</html>");
            }
        } else {
            getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Creamos las variables para procesar la información del formualario
        String username = req.getParameter("user");
        String password = req.getParameter("password");
        //Implementamos una condicional para saber si el nombre del usuario y contraseña
        //es igual a la información que tenemos guardada
        if (username.equals(USERNAME) && password.equals(PASSWORD)) {
            resp.setContentType("text/html;charset=UTF-8");
            //Creamos e instanciamos el objeto Cookie
            Cookie cookie = new Cookie("username", username);
            //Añadimos la cookie
            resp.addCookie(cookie);
            try (PrintWriter out = resp.getWriter()) {
                resp.setContentType("text/html; charset=UTF-8");
                //Creo la plantilla html
                out.print("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<meta charset=\"utf-8\">");
                out.println("<title>Login Exitoso</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Bienvenido a mi aplicación " + username +" sesión con exito</h1>");
                out.println("<a href='"+req.getContextPath() + "/index.html'>Ir al inicio</a>");
                out.println("</body>");
                out.println("</html>");
            }
            resp.sendRedirect(req.getContextPath()+"/index.html");
        }else{
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Lo sentimos no tienes acceso revisa los datos de usuario y contraseña");
        }
    }
}


