package com.mctbl.ledger.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mctbl.ledger.bean.Result;
import com.mctbl.ledger.dto.LoginDto;
import com.mctbl.ledger.security.JWTUtil;
import com.mctbl.ledger.security.LedgerUser;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class LoginController {

	private static final Log logger = LogFactory.getLog(LoginController.class);

	@Autowired
	private JWTUtil jwtu;

	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping("/login")
	public Result<Map<String, Object>> login(HttpServletRequest request, @RequestBody LoginDto dto){
		logger.info("Loging from " + request.getRemoteAddr() + " with user:" + dto.getUsername());

		Authentication authentication = null;
		try {
			authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                dto.getUsername(), dto.getPassword()));
		}catch (Exception e) {
			logger.error(e);
			return Result.error(e.getMessage());
		}
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtu.generateJwtToken(authentication);

		Map<String, Object> map = new HashMap<>();
		map.put("token", token);

		if(authentication.getPrincipal() instanceof LedgerUser lu) {
			map.put("userId", lu.getUserId());
			map.put("userName", lu.getUsername());
		}
		logger.info(dto.getUsername() + " logged in");
		return Result.success(map);
	}

}
