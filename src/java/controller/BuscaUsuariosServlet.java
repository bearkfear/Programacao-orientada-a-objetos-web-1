/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import br.csi.dao.UsuarioDAO;
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



/**
 * Classe servlet protocolo http com rescrita de metodo extendendo servlet
 * 
 * Imprime todos os usuarios com foreach e também retorna a partir do codigo por get no url
 */

public class BuscaUsuariosServlet extends HttpServlet {

    /**
     * Reescrita do metodo doGet para pegar requisições atraves do get e
     * responder
     * 
     * req = pega as requisições
     * resp = retorna uma resposta a página web
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
        
        
        /*
        
        getWriter metodo que permite a escrita na pagina
        
        */
        PrintWriter resposta = resp.getWriter();

        
        
        /*
        Estrutura html
        */
        resposta.println("<html>");
        resposta.println("<head>");
        resposta.println("<title> Todos os usuarios</title>");
        resposta.println("</head>");
        resposta.println("<body>");
        resposta.println("<ul>");

        
        
        /*
         for funcional ou each imprimindo todos os usuarios cadastrados no banco de dados
         */
        new UsuarioDAO().getUsuarios().forEach((usuario) -> {
            resposta.println("<br>");
            resposta.println(usuario.getNome());
        });
        resposta.println("</ul>");

        
        /*
        tratamento de erros que podem ocorrer ao pegar o codigo do usuario por get
        */
        try {

            String codigo = req.getParameter("cod");
            resposta.println("<h3>Resultado</h3>");
            resposta.println("<p>" + new UsuarioDAO().read(Integer.parseInt(codigo)) + "</p>");

        } catch (NumberFormatException e) {
            
            /*
            trata caso o codigo não seja um numero "NumberFormatException"
            */
            resposta.println("Codigo deve ser um numero inteiro");
        } catch (NullPointerException ex) {
            
            /*
            Trata caso não exista nada declarado
            */
            resposta.println("Nada declarado");
        } catch (Exception exception) {
            /*
            Tratamento de forma generica
            */
            resposta.println("Problema! valor indefinido");
        }

        resposta.println("</body>");
        resposta.println("</html>");
        /*
        FIM DA ESTRUTURA HTML
        */
    }

}
