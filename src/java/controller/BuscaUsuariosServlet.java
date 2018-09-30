/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import br.csi.dao.UsuarioDAO;
import br.csi.model.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Enrico
 */
/*
 WebServlet (urlPatterns) define a url do servlet no navegador, para requisição de get ou post...
 Precisa da url
 */
@WebServlet(urlPatterns = "/buscarUsuarios")
public class BuscaUsuariosServlet extends HttpServlet {

    /**
     * Reescrita do metodo doGet para pegar requisições atraves do get e
     * responder
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // req.getParameter(...); utilizador para pegar parâmetros de get ou post
        // resp.getWritter... Utilizado para escrever na pagina web
        PrintWriter resposta = resp.getWriter();

        resposta.println("<html>");
        resposta.println("<head>");
        resposta.println("<title> Todos os usuarios</title>");
        resposta.println("</head>");
        resposta.println("<body>");
        resposta.println("<ul>");

        for (Usuario usuario : new UsuarioDAO().getUsuarios()) {
            resposta.println("<br>");
            resposta.println(usuario.getNome());

        }
        resposta.println("</ul>");

        try {

            String codigo = req.getParameter("cod");
            resposta.println("<h3>Resultado</h3>");
            resposta.println("<p>" + new UsuarioDAO().read(Integer.parseInt(codigo)) + "</p>");

        } catch (NumberFormatException e) {
            resposta.println("Codigo deve ser um numero inteiro");
        } catch (NullPointerException ex) {
            resposta.println("Nada declarado");
        } catch (Exception exception) {
            resposta.println("Problema! valor indefinido");
        }

        resposta.println("</body>");
        resposta.println("</html>");

    }

}
