package br.com.theodorol.todolist.auth;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.theodorol.todolist.model.UserModel;
import br.com.theodorol.todolist.repository.IUserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//classe com foco com Basic Auth 
@Component
public class FilterTaskAuth extends OncePerRequestFilter {
    @Autowired
    private IUserRepository iUserRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        var verifyServlePath = request.getServletPath();
        // verifica se a rota é igual a task
        if (verifyServlePath.equals("/task")) {
            // pega o Authoriztion no header
            var authrization = request.getHeader("Authorization");
            // pega só o Basic
            var authEncode = authrization.substring("Basic".length()).trim();
            // transforma em uma array de bytes
            byte[] authDecoded = Base64.getDecoder()
                    .decode(authEncode);
            // transforma em um array de string
            String[] decode = new String(authDecoded).split(":");
            // pega o usuário
            String user = decode[0];
            System.out.println(user);
            // pega a senha
            String password = decode[1];
            UserModel verifyUser = this.iUserRepository.findByUsername(user);
            System.out.println(verifyServlePath);
            if (!isAuthenticated(password, verifyUser)) {
                response.sendError(401);
                return;
            }

        }
        filterChain.doFilter(request, response);

    }

    private boolean isAuthenticated(String password, UserModel verifyUser) {
        if (verifyUser == null)
            return false;

        return BCrypt.verifyer()
                .verify(password.toCharArray(), verifyUser.getPassword()).verified;
    }

}
