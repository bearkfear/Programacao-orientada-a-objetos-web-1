/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.csi.controller;

import br.csi.dao.UsuarioDAO;
import br.csi.model.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/*
    url para requisição do formulário de cadastro
*/
@WebServlet(urlPatterns = "/cadastrarUsuario")


/**
 * Classe que recebe requisição do cliente para cadastrar um Usuarion no banco de dados
 * recebe por post informações
 * @author enrico
 */
public class CadastrarUsuarioServlet extends HttpServlet {

    
    /**
     * Reescrita do metodo dopost que recebe as informações do cliente para cadastrar o usuario
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException 
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        /*
        ferramenta de escrita
        */
        PrintWriter resposta = resp.getWriter();
        
        
        /**
         * Comparação se a opcão realmente é cadastrar, comparando com equals o valor do atributo name do formulario
         */
        if (req.getParameter("opcao").equals("cadastrar")) {
            /*
            Monta o usuario recebendo os parametros por post
            */
            Usuario usuario = new Usuario(req.getParameter("nome"), req.getParameter("email"), req.getParameter("senha"));

            
            /*
            cadastra o usuario no banco de dados, se tiver sucesso redireciona para a pagina que exibe todos os usuarios
            se tiver falha apenas, retorna mensagem de erro
            */
            if (new UsuarioDAO().create(usuario)) {
                resp.sendRedirect(req.getContextPath() + "/buscarUsuarios");
            } else {

                resposta.println("Erro! não foi possível cadastrar o usuario: " + usuario.getNome() + "\nTente novamente mais tarde");

            }

        }

    }

}
