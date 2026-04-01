package com.educacion.inedcuchilla.Llaves;

import io.jsonwebtoken.Jwts;

import javax.crypto.SecretKey;

public class JWTLlave {
    public static final SecretKey LLAVE_ = Jwts.SIG.HS256.key().build();
    public static final String TIPO_CONTENIDO = "application/json";
    public static final String CABECERA = "Authorization";
    public static final String BEARER = "Bearer ";
}
