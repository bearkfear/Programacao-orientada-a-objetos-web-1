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
@WebServlet(urlPatterns = "/cadastrarUsuario")
public class CadastrarUsuarioServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter resposta = resp.getWriter();
        
        
        
        if (req.getParameter("opcao").equals("cadastrar")) {
            Usuario usuario = new Usuario(req.getParameter("nome"), req.getParameter("email"), req.getParameter("senha"));

            if (new UsuarioDAO().create(usuario)) {
                resp.sendRedirect(req.getContextPath() + "/buscarUsuarios");
            } else {

                resposta.println("Erro! não foi possível cadastrar o usuario: " + usuario.getNome() + "\nTente novamente mais tarde");

            }

        }

    }

}
