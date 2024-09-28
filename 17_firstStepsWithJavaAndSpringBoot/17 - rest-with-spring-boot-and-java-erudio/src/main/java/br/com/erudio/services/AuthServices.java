package br.com.erudio.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.erudio.data.vo.v1.security.AccountCredentialsVO;
import br.com.erudio.data.vo.v1.security.TokenVO;
import br.com.erudio.repositories.UserRepository;
import br.com.erudio.security.Jwt.JwtTokenProvider;

@Service
public class AuthServices {

	@Autowired
	private JwtTokenProvider tokenProvider;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserRepository repository;
	
	@SuppressWarnings("rawtypes")
	public ResponseEntity signin(AccountCredentialsVO data) { //Recebe o accountCredentials
		try {
			var username = data.getUsername();
					var password = data.getPassword();//Extrai o usuário e a senha
					
					authenticationManager.authenticate(
							new UsernamePasswordAuthenticationToken(username, password));
					//Invoca o authentication e tenta fazer o login desses caras
					
					var user = repository.findByUsername(username);//Busca pelo username no repositório
					
					var tokenResponse = new TokenVO();
					if (user != null) {
						tokenResponse = tokenProvider.createAccessToken(username, user.getRoles());
						//Se a busca retornar um usuário e, logo, este for diferente de null geramos um token de acesso
					}else {
						throw new UsernameNotFoundException("Username "+ username+ " not found!");
						//caso contário joga a exception
					}
			return ResponseEntity.ok(tokenResponse);//Caso ok: retorna um responseEntity com o token
		} catch (Exception e) {
			
			throw new BadCredentialsException("Invalid username/password supplied!");
			//Caso bobson: retorna um erro
		}
		
	}
}
