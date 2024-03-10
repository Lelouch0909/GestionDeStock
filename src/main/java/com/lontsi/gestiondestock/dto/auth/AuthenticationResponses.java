package com.lontsi.gestiondestock.dto.auth;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthenticationResponses {

	private String accesstoken;
}
